package com.ch96.ex87retrofitmarketapp;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public class RetrofitHelper {
    public static Retrofit getRetrofitInstance(){
        Retrofit.Builder builder = new Retrofit.Builder();
        builder.baseUrl("http://testhue96.dothome.co.kr");
        builder.addConverterFactory(ScalarsConverterFactory.create()); //String과 Json Converter를 두 개 다 사용하기 위해서 Scalars 먼저 작성해주어야함
        builder.addConverterFactory(GsonConverterFactory.create()); //Gson을 먼저 쓰고 Gson이 아닌 것이 먼저 작동하면 ERROR남
        Retrofit retrofit = builder.build();

        return retrofit;
    }
}
