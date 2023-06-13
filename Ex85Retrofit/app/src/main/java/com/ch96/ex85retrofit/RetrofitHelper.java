package com.ch96.ex85retrofit;


import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitHelper {
    public static Retrofit getRetrofitInstance() { //Retrofit을 리턴하고 아무데서나 쓸 수 있도록 public
        //이 기능을 쓰기위해서 Helper를 객체로 만들고 그 객체 안에 이 기능을 쓰려면 복잡
        //static 정적으로 만들면 객체 new할 필요없이 바로 사용 가능

        Retrofit.Builder builder = new Retrofit.Builder();
        builder.baseUrl("http://testhue96.dothome.co.kr");
        builder.addConverterFactory(GsonConverterFactory.create());
        Retrofit retrofit = builder.build();

        return retrofit;
    }
}
