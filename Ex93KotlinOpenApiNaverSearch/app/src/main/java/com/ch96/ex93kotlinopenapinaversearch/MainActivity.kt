package com.ch96.ex93kotlinopenapinaversearch

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.ch96.ex93kotlinopenapinaversearch.databinding.ActivityMainBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory

class MainActivity : AppCompatActivity() {

    val binding:ActivityMainBinding by lazy { ActivityMainBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.btn.setOnClickListener { searchData() }
    }

    fun searchData() {

        //소프트 키보드 없애기
        val imm: InputMethodManager = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(currentFocus?.windowToken, 0) //포커스를 가진 놈이 토큰을 가짐 - 얘가 소프트 키보드를 쓸 수 있음
        //현재 가지고 있는 놈을 부르기 currentFocus - 토큰 뺏어오기  - 즉시 = 0

        //Naver (쇼핑) 검색 open API 사용해보기
        
        //네트워크 작업을 편하게 대신 작성해주는 라이브러리 활용 : Retrofit
        //직접 작업을 하는 것이 아니라 코드를 대신 써주는 친구
        //1. 레트로핏 생성
        val retrofit:Retrofit = Retrofit.Builder()
            .baseUrl("https://openapi.naver.com/")
            .addConverterFactory(ScalarsConverterFactory.create()) //반드시 Scalars 먼저
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        
        //2. 레트로핏이 해줄 작업에 대한 요구명세(인터페이스 설계 및 추상메소드 정의)
        //RetrofitService.kt 설계

        //3. 레트로핏 서비스 객체 생성
        val retrofitService:RetrofitService = retrofit.create(RetrofitService::class.java)

//        //4. 원하는 작업요청하여 네트워크 작업 실행 객체 리턴받기
//        val call: Call<String> = retrofitService.searchDataByString("txD9TVR4RqDboCfF_H7r", "LfjeRQhqY9", binding.et.text.toString())
//
//        //5. 작업시작
//        call.enqueue(object:Callback<String>{
//            override fun onResponse(call: Call<String>, response: Response<String>) {
//                var s:String? = response.body()
//
//                AlertDialog.Builder(this@MainActivity).setMessage(s).show()
//            }
//
//            override fun onFailure(call: Call<String>, t: Throwable) {
//                Toast.makeText(this@MainActivity, "Error", Toast.LENGTH_SHORT).show()
//            }
//
//        })

        //json으로 값 여러 개를 한 번에 받아와서 gson이 파싱 알아서 해서 객체로 줌
        //4. 원하는 작업요청하여 네트워크 작업 실행 객체 리턴받기
        val call:Call<NaverSearchApiResponce> = retrofitService.searchDataByJson(binding.et.text.toString())

        //5. 네트워크 작업시작
        call.enqueue(object: Callback<NaverSearchApiResponce>{
            override fun onResponse(
                call: Call<NaverSearchApiResponce>,
                response: Response<NaverSearchApiResponce>
            ) {
                val naverResponce:NaverSearchApiResponce? = response.body()

                //Toast.makeText(this@MainActivity, "아이템 갯수 : ${naverResponce?.items?.size}", Toast.LENGTH_SHORT).show()
                //응답받은 객체의 Items 리스트를 리사이틀러뷰에 보이기
                //notify, 갱신 불필요
                binding.recycler.adapter = MyAdapter(this@MainActivity, naverResponce!!.items)
            }

            override fun onFailure(call: Call<NaverSearchApiResponce>, t: Throwable) {
                Toast.makeText(this@MainActivity, "Error : ${t.message}", Toast.LENGTH_SHORT).show()
            }

        })
    }

}