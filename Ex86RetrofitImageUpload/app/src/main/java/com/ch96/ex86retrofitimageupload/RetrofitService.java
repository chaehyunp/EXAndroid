package com.ch96.ex86retrofitimageupload;

import okhttp3.MultipartBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;

public interface RetrofitService {

    //이미지 파일[고등어는]은 스티로폼 택배 상자[MultipartBody.Part]에 넣어서 전송
    //@Part 어노테이션 사용 단, @Mulripart 어노테이션과 함께

    @Multipart //인코딩타입을 써줌 (cf.@FormUrl...)
    @POST("Retrofit/fileUpload.php")
    Call<String> uploadImage(@Part MultipartBody.Part file);
}
