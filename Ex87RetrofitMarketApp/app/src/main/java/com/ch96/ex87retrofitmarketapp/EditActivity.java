package com.ch96.ex87retrofitmarketapp;

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
import com.ch96.ex87retrofitmarketapp.databinding.ActivityEditBinding;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.http.Multipart;

public class EditActivity extends AppCompatActivity {


    ActivityEditBinding binding;
    //이미지 경로
    String imgPath = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_edit);
        binding = ActivityEditBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        //제목줄 바꾸기
        getSupportActionBar().setTitle("새로운 글 작성");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true); //업버튼 사용여부(뒤로가기), 돌아가지는 않음(onSupportNavigateUp()필요)

        binding.btnSelect.setOnClickListener(v -> clickSelect());
        binding.btnComplete.setOnClickListener(v -> clickComplete());

    }

    void clickSelect() {
        Intent intent = new Intent(MediaStore.ACTION_PICK_IMAGES); //묵시적 Intent
        resultLauncher.launch(intent);
    }
    
    //바로 startActivity하면 결과를 못받음 --> ActivityResultLauncher 필요(Contracts(계약,퍼미션 등을 해줌),Callback(결과 리턴))
    ActivityResultLauncher<Intent> resultLauncher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), result -> {
        if (result.getResultCode()==RESULT_CANCELED) return; //결과 코드값이 캔슬이면 그냥 리턴
        Uri uri = result.getData().getData(); //캔슬이 아니면 데이터가 있기때문에 result에게 택배기사부르고 택배기사의 택배부르기
        Glide.with(this).load(uri).into(binding.iv); //운영체제의 능력을 가지고 uri를 가져와서 이미지뷰에 붙이기
        //Retrofit을 이용하여 서버에 이미지를 보낼때는 Uri주소가 아니라 실제 파일의 주소가 필요함
        imgPath = getFilePathFromUri(uri);
        new AlertDialog.Builder(this).setMessage(imgPath).show(); //원래 creat().show() 해야하지만 Builder는 자동으로 create()까지 해줌
    });

    //imgPath를 구하기위한 코드 (alt+enter - import)
    //Uri를 절대경로로 바꿔서 리턴시켜주는 메소드
    String getFilePathFromUri(Uri uri){
        String[] proj= {MediaStore.Images.Media.DATA};
        CursorLoader loader= new CursorLoader(this, uri, proj, null, null, null);
        Cursor cursor= loader.loadInBackground();
        int column_index= cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
        cursor.moveToFirst();
        String result= cursor.getString(column_index);
        cursor.close();
        return  result;
    }

    void clickComplete() {
        //사용자가 입력한 데이터를 서버에 전송하기
        //전송할 데이터 [name, title, message, price, imgPath(파일)]
        String name = binding.etName.getText().toString();
        String title = binding.etTitle.getText().toString();
        String message = binding.etMsg.getText().toString();
        String price = binding.etPrice.getText().toString();
        //레트로핏 작업 5단계
        Retrofit retrofit = RetrofitHelper.getRetrofitInstance(); //미리 작성한 RetrofitHelper를 이용하여 객체 생성
        RetrofitService retrofitService = retrofit.create(RetrofitService.class);

        //String 데이터들
        Map<String, String> dataPart = new HashMap<>();
        dataPart.put("name", name);
        dataPart.put("title", title);
        dataPart.put("msg", message);
        dataPart.put("price", price);


        //이미지 파일 택배 박스 포장
        MultipartBody.Part filePart = null;
        if (imgPath!=null) {
            File file = new File(imgPath); //고등어 손질
            RequestBody body = RequestBody.create(MediaType.parse("image/*"), file); //진공팩 준비, 진공팩에 넣을 종류 : 모든 생선 종류, 넣을 것 : 고등어
            filePart = MultipartBody.Part.createFormData("img", file.getName(), body); //박스 겉에 식별자 : 생선류, 생선이름 : 고등어, 포장 : 진공포장
        }

        Call<String> call = retrofitService.postDataToServer(dataPart, filePart);
        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                String result = response.body();
                Toast.makeText(EditActivity.this, result, Toast.LENGTH_SHORT).show();

                //게시글 업로드가 성공했으니 글 작성화면 자동 종료
                finish();
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                Toast.makeText(EditActivity.this, "upload failed : " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

    }

    //업버튼을 눌렀을때 액티비티 종료하기
    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return super.onSupportNavigateUp();
    }
}