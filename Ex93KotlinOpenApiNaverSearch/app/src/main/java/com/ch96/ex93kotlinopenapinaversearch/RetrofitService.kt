package com.ch96.ex93kotlinopenapinaversearch

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Headers
import retrofit2.http.Query

interface RetrofitService {
     @GET("/v1/search/shop.json?display=100") //고정된 쿼리값은 호출할때마다 값전달하지말고 고정시켜버리기
     fun searchDataByString(@Header("X-Naver-Client-Id") clientId:String, @Header("X-Naver-Client-Secret") clientSecret: String, @Query("query") query: String): Call<String> //query변수 - 검색어, 리턴값 Call

     //헤더값이 고정적이라면 굳이 매번 파라미터로 받지말고
     //Header는 파라미터로 Headers는 바깥에
     @Headers ("X-Naver-Client-Id:txD9TVR4RqDboCfF_H7r", "X-Naver-Client-Secret:LfjeRQhqY9")
     @GET("/v1/search/shop.json?display=100")
     fun searchDataByJson(@Query("query") query: String): Call<NaverSearchApiResponce>
}