package com.ch96.ex86retrofitimageupload;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.loader.content.CursorLoader;

import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.ch96.ex86retrofitimageupload.databinding.ActivityMainBinding;

import java.io.File;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;

    //업로드할 파일의 주소를 저장하는 문자열 변수
    String imgPath = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_main);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.btnSelect.setOnClickListener(v -> clickSelect());
        binding.btnUpload.setOnClickListener(v -> clickUpload());

    }

    void clickSelect() {
        //이미지를 선택할 수 있는 앱(Activity) 실행
        Intent intent = new Intent(MediaStore.ACTION_PICK_IMAGES);
        resultLauncher.launch(intent);
    }

    ActivityResultLauncher<Intent> resultLauncher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), result -> {
        if(result.getResultCode()==RESULT_CANCELED) return;

        //선택한 사진의 콘텐츠 주소 URI 얻어오기
        Uri uri = result.getData().getData(); //앞의 getData는 택배기사 부르기, 뒤에 getData는 실제 uri
        Glide.with(this).load(uri).into(binding.iv);
        
        //Retrofit을 이용하여 서버에 파일을 전송하려면 파일의 Uri(콘텐츠 DB 주소)가 아니라 파일의 주소 필요함
        //Uri는 실제주소가 아니라 콘텐츠 Database의 경로
        //Database의 한 record의 위치가 Uri (record 내에 no,display_name,path..."data" --> 얘가 진짜 주소)
        //DB를 읽어들여서 이 안에 있는 파일의 경로를 찾아내야 서버작업이 가능해짐
        //new AlertDialog.Builder(this).setMessage(uri.toString()).create().show();

        //uri --> 파일주소 변환
        imgPath = getFilePathFromUri(uri);
        new AlertDialog.Builder(this).setMessage(imgPath).create().show();
    });

    //uri --> 파일경로로 바꿔서 리턴해주는 메소드
    String getFilePathFromUri(Uri uri){
        String[] proj = new String[]{MediaStore.Images.Media.DATA};
        CursorLoader loader = new CursorLoader(this, uri, proj, null, null, null);
        //커서(ResultSet)가 이미 하나의 레코드를 잡고 있기때문에 selection 이후는 WHERE절과 같은 의미이므로 필요하지않음
        Cursor cursor = loader.loadInBackground();
        int columnIndex = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA); //커서가 String으로 가져올 인덱스번호
        cursor.moveToFirst();
        String result = cursor.getString(columnIndex);

        return result;
    }

    void clickUpload() {
        //Retrofit 라이브러리를 이용하여 이미지 업로드

        //1) Retrofit 객체 생성
        Retrofit.Builder builder = new Retrofit.Builder();
        builder.baseUrl("http://testhue96.dothome.co.kr");
        builder.addConverterFactory(ScalarsConverterFactory.create());
        Retrofit retrofit = builder.build();

        //2) Service 인터페이스와 추상메소드 설계 - 요구 명세

        //3) Service 인터페이스 객체
        RetrofitService retrofitService = retrofit.create(RetrofitService.class);

        //4) 보낼파일을 택배상자로 포장 - 이미지 파일[고등어는]은 스티로폼 택배 상자[MultipartBody.Part]에 넣어서 전송
        File file = new File(imgPath);
        //이미지 파일[고등어]은 바로 택배상자에 넣으면 난리남, 먼저 진공팩[RequestBody]에 넣어서 포장해야함 + 포장하는게 무슨종류인지 써줘야함
        //진공팩 만들기
        RequestBody body = RequestBody.create(MediaType.parse("image/*"), file);
        //택배상자 만들기
        MultipartBody.Part part = MultipartBody.Part.createFormData("img", file.getName(), body); //파라미터 : 식별자, 파일명(바꿀 수 있음), 진공팩

        Call<String> call = retrofitService.uploadImage(part); //택배상자

        //5) 작업시작
        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                String result = response.body();
                new AlertDialog.Builder(MainActivity.this).setMessage(result).show(); //show만 해도 create까지 해줌
                //파일의 실제 데이터는 임시저장소에 보관되고 PHP에는 파일정보(송장)만 전달됨
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                Toast.makeText(MainActivity.this, "ERROR : " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}