package com.ch96.ex85retrofit;

import java.util.ArrayList;
import java.util.Map;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;

//인터페이스는 클래스와 비슷하지만 멤버로 추상메소드(이름만 있는 메소드)만 만들 수 있음
public interface RetrofitService {
    //원하는 작업을 위한 코드를 쓰는게 아니라 요구사항을 명세

    //1. 단순하게 GET방식으로 json파일을 읽어오기
    @GET("Retrofit/board.json") //Annotations(강제주석)
    Call<Item> getBoardJson(); //Call - Retrofit이 써준 코드를 가지고 있음, 제네릭에 받을 결과 (결과를 Item 객체로 받겠다)

    //2. 경로의 이름을 위 1번처럼 고정하지 않고 사용자에게 파라미터로 전달받아서 지정할 수 있음 [@Path]
    @GET("{aaa}/{bbb}") //중괄호를 열면 변수명이 됨
    Call<Item> getBoardJsonByPath(@Path("aaa") String path, @Path("bbb") String fileName); //전달받은 문자열이 글자바꿔치기처럼 동작

    //3. GET방식으로 서버에 값 전달 [@Query]
    @GET("Retrofit/getTest.php")
    Call<Item> getMethodTest(@Query("name") String name, @Query("msg") String message);

    //4. GET방식으로 값을 보낼때 Map collection (collection - 대량의 데이터를 저장하기위한 방법, List/Set/Map)을 이용하여 한 번에 값 전달하기
    @GET("Retrofit/getTest.php")
    Call<Item> getMethodTest2(@QueryMap Map<String,String>datas); //식별자도 String, 내용도 String Map에 식별자를 썼기때문에 쿼리에 식별자를 주지않음
    
    //5. POST방식으로 값 전달 [@Body] - 객체를 전달하면 자동으로 객체 멤버 변수를 json문자열로 변환하여 서버로 전송해줌
    @POST("Retrofit/postTest.php")
    Call<Item> postMethodTest(@Body Item item); //객체이기때문에 변수의 이름이 자동으로 식별자가 됨

    //6. POST방식으로 값 하나씩 전달 [@Field]
    //단, @Field를 사용하려면 반드시 @FormUrlEncoded와 함께 지정
    //POST는 기본으로 @Body를 권장하지만 이 경우에는 GSON이 꼭 필요 이게 싫다면 @FormUrlEncoded, Multi...(이미지일때)필요
    @FormUrlEncoded
    @POST("Retrofit/postTest2.php")
    Call<Item> postMethodTest2(@Field("name") String name, @Field("msg") String message);
    
    //7. GET 방식으로 jsonArray 값 읽어와서 ArrayList<Item>로 곧바로 파싱
    @GET("Retrofit/boardArray.json")
    Call<ArrayList<Item>> getBoardArray();

    //8. GSON을 통해 자동으로 Item객체로 파싱하지 않고 문자열로 응답결과 받아오기
    @GET("Retrofit/board.json")
    Call<String> getJsonString();

}
