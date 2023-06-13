package com.ch96.ex101coroutineandroid

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //코틀린 언어에서 지원하는 Coroutine은 두 종류 : GlobalScope, CoroutineScope
        //안드로이드는 액티비티 or 프래그먼트의 라이프사이클이 존재 - 이에 함께 반응하는 코루틴이 있음
        //LifecycleScope / ViewModelScope - 별도의 라이브러리

        findViewById<Button>(R.id.btn).setOnClickListener { clickBtn1() }
        findViewById<Button>(R.id.btn2).setOnClickListener { clickBtn2() }
        findViewById<Button>(R.id.btn3).setOnClickListener { clickBtn3() }

    }

    private fun clickBtn1(){
        CoroutineScope(Dispatchers.Default).launch {
            for (n in 0..20) {
                Log.d("TAG", "coroutine scope : $n")
                delay(500)
            }
        }
    }
    private fun clickBtn2(){
        //안드로이드의 라이프사이클에 같이 제어되는 코루틴 스코프 : lifecycleScope
        //this. 이 생략됨, onCreate() ~ onDestroy() 까지의 액티비티 라이프사이클 Owner
        lifecycleScope.launch {
            for (n in 0..20) {
                Log.d("TAG", "lifecycle scope : $n")
                delay(500)
            }
        }
    }

    fun clickBtn3(){
        //onResume ~ onPause()일 동안에만 코루틴 동작, onPause()되면 자동 일시정지, onResume()이면 알아서 다시 실행
        lifecycleScope.launchWhenResumed {
            loopTask()
        }
    }

    suspend fun loopTask(){
        for(n in 300..320){
            Log.d("TAG", "lifecycle scope when resume : $n")
            delay(500)
        }
    }
}