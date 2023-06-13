package com.ch96.ex87retrofitmarketapp;

import java.util.ArrayList;
import java.util.Map;

import okhttp3.MultipartBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.PartMap;
import retrofit2.http.Query;

public interface RetrofitService {
    //GET - Query, POST - Filed, Part (여러 개 - Body)
    //POST방식으로 보내기
    //Field(문자열 데이터)과 Part(이미지 데이터)를 쓰면 각각 @FormUrlEncoded, @Multipart (인코딩 타입)을 둘 다 써야하는데, 서로 반대되는 인코딩 방법 - ERROR
    //이미지 파일용 Part와 나머지 문자열용 데이터들 HashMap로 구분하여 전송 --> 택배박스 2개
    @Multipart
    @POST("RetrofitMarket/insertDB.php") //문자열 데이터 (여러 개이기때문에 Map 이용), 이미지 데이터 (MultipartBody.Part)
    Call<String> postDataToServer(@PartMap Map<String, String> dataPart, @Part MultipartBody.Part filePart);
    
    //데이터 불러올때 사용하는 명세서
    //서버에서 echo한 json array를 읽어와서 자동으로 ArrayList<MarketItem>로 파싱하는 요구명세
    @GET("RetrofitMarket/loadDB.php")
    Call<ArrayList<MarketItem>> loadDataFromServer();

    //서버 DB에서 특정 아이템을 삭제하도록 요청하는 명세
    @GET("RetrofitMarket/deleteItem.php")
    Call<String> deleteItem(@Query("no") String no);

}
