package com.ch96.ex100coroutine

import android.graphics.BitmapFactory
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.ch96.ex100coroutine.databinding.ActivityMainBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.net.URL

class MainActivity : AppCompatActivity() {

    val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        //Coroutine [코루틴] - 경량스레드 : 스레드를 멈추지 않고 비동기처리, 하나의 스레드 안에 여러 개의 코루틴 실행
        //스레드가 요리사라면 멀티 스레드는 여러 요리사가 화구(cpu)를 번가라 사용하는 구조 - 다른 요리사가 사용중에 기존 요리사는 동작을 멈춤
        //코루틴은 하나의 요리사(스레드)가 파스타를 만들면서 스테이크를 굽는 형식 - 팬이 2개, 자리를 비켜가면서 멈추는 행동이 없어 좀 더 빠르게 동시 작업 가능

        //코루틴을 구동하는 2개의 범위(Scope)가 존재함
        //1. GlobalScope : 앱 전체의 생명주기와 함께 관리되는 범위
        //2. CoroutineScope : 버튼 클릭과 같은 특정 이벤트 순간에 해야할 Job을 위해 실행되는 범위 [ ex. network 통신, DB CRUD, 특정연산수행 등 ]

        //실습 1) GlobalScope 코드 연습
        binding.btn.setOnClickListener {
            //코루틴 없이 오래 걸리는 작업 실행
//            for(n in 0..9){
//                Log.d("TAG", "$n")
//                Thread.sleep(500)
//            }

            //비동기작업으로 위 작업을 수행 - 코루틴 사용
            GlobalScope.launch {
                for(n in 0..9){
                    Log.d("TAG", "$n - ${Thread.currentThread().name}")
                    delay(500) //코루틴 안에서만 사용할 수 있는 메소드, thread는 자면 안되기때문에 delay
                }
            }
            //동시에 여러 작업 가능하므로 Toast가 위 작업이 끝난 후가 아닌 동시에 진행됨
            Toast.makeText(this, "aaa", Toast.LENGTH_SHORT).show()
        }
        
        //실습 2) Coroutine 비동기 실행
        //CoroutineScope는 GlobalScope와 다르게 해당 작업을 어떤 스레드에게 보낼지 결정하는 Dispatcher를 지정해야 함
        //Dispatcher의 종류 (스레드의 능력치를 약간씩 다르게)
        //1. Dispatchers.Default - 기본 스레드풀의 스레드를 사용 [ cpu 작업이 많은 연산작업에 적합 ]
        //2. Dispatchers.IO - DB나 네트워크 IO 스레드를 사용 [ 파일 입출력, 서버작업에 적합, ex. open API ]
        //3. Dispatchers.Main - Main 스레드를 사용 [ UI 작업 등에 적합 ]
        //4. Dispatchers.Unconfined - 해당 코루틴을 호출하는 스레드에서 실행

        binding.btn2.setOnClickListener {
            //Dispatchers.Default 사용
            CoroutineScope(Dispatchers.Default).launch {
                for(n in 100..110){
                    Log.d("TAG", "$n - ${Thread.currentThread().name}")
                    //binding.tv.text = "n : $n" //Main이 아닌 별도 thread는 UI 작업이 불가
                    delay(500)
                }
            }
            Toast.makeText(this, "bbb", Toast.LENGTH_SHORT).show()
        }

        binding.btn3.setOnClickListener {
            CoroutineScope(Dispatchers.Main).launch {
                for(n in 200..210){
                    binding.tv.text="$n - ${Thread.currentThread().name}"
                    delay(500)
                }
            }
            Toast.makeText(this, "ccc", Toast.LENGTH_SHORT).show()
        }

        binding.btn4.setOnClickListener {
            //Main에서 서버작업 시도
            CoroutineScope(Dispatchers.Main).launch {
                //네트워크 이미지 불러오기
                val url = URL("https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQD4EDrzWXGdNZWxb8ozP8anUMWw_V48P9JwDkPqQOR&s")
                val bitmap = BitmapFactory.decodeStream(url.openStream())
                binding.iv.setImageBitmap(bitmap)
            }
        }

        binding.btn5.setOnClickListener {
            CoroutineScope(Dispatchers.IO).launch {
                val url = URL("https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQD4EDrzWXGdNZWxb8ozP8anUMWw_V48P9JwDkPqQOR&s")
                val bitmap = BitmapFactory.decodeStream(url.openStream())
                //binding.iv.setImageBitmap(bitmap)

                withContext(Dispatchers.Main){
                    binding.iv.setImageBitmap(bitmap)
                }
            }
        }

        binding.btn6.setOnClickListener {
            CoroutineScope(Dispatchers.Default).launch {

                //작업1
                launch {
                    for(n1 in 1000..1010) Log.d("TAG", "$n1")
                    delay(500)
                }

                //작업2
                launch {
                    for(n2 in 2000..2010) Log.d("TAG", "$n2")
                    delay(500)
                }
            }
        }

        binding.btn7.setOnClickListener {
            CoroutineScope(Dispatchers.Default).launch {

                //작업1
                launch {
                    for(n1 in 1000..1010) Log.d("TAG", "$n1")
                    delay(500)
                }.join() //작업1이 끝날때까지 다른 코루틴은 대기

                //작업2
                launch {
                    for(n2 in 2000..2010) Log.d("TAG", "$n2")
                    delay(500)
                }
            }
        }

        binding.btn8.setOnClickListener {
            CoroutineScope(Dispatchers.Default).launch {
                someTask()
            }
        }

        //코루틴의 제어
        var job:Job ?= null
        binding.btn9.setOnClickListener {
            job = CoroutineScope(Dispatchers.Default).launch {
                for(n in 300..310){
                    Log.d("TAG", "n : $n")
                    delay(500)
                }
            }
        }
        binding.btn10.setOnClickListener {
            job?.cancel()
        }
    }

    //코루틴 스코프 범위 밖에서 코루틴의 기능을 사용할때 suspend 함수로 만들면 사용가능
    suspend fun someTask(){
        for(n in 1000..1010){
            Log.i("TAG","someTask : $n")
            delay(500) //메소드 내에서 코루틴 기능을 사용할 수 없음
        }
    }
}
