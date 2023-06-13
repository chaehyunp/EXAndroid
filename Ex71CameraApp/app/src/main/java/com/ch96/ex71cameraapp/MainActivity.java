package com.ch96.ex71cameraapp;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    ImageView iv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        iv = findViewById(R.id.iv);
        findViewById(R.id.btn).setOnClickListener(v -> clickBtn());
    }

    void clickBtn() {
        //카메라 앱 실행
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        resultLauncher.launch(intent);
    }

    ActivityResultLauncher<Intent> resultLauncher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), result -> {
        if (result.getResultCode() == RESULT_OK) {
            //사진 촬영 파일의 uri 얻어오기
            Uri uri = result.getData().getData(); //앞 getData() 기사 부르기, 뒤 getData() 기사의 박스 가져오기
            if (uri == null) Toast.makeText(this, "null", Toast.LENGTH_SHORT).show();
            //요즘의 스마트폰은 프로그램을 통해 실행한 카메라앱에서 촬영한 사진은 디바이스에 파일로 저장되지 않음
            //촬영한 사진 "섬네일" 정보를 Bitmap 객체로 만들어서 Extra 데이터로 전달해줌
            //섬네일이기때문에 사진의 화질이 깨짐
            Intent intent = result.getData();
            Bundle bundle = intent.getExtras();
            Bitmap bm = (Bitmap) bundle.get("data"); //Bitmap은 안드로이드에서 유일하게 직접 이미지를 가지고 있음
            iv.setImageBitmap(bm);

            //개발자가 파일로 저장되길 원한다면...
            //Intent로 카메라 앱을 실행할때 추가 데이터를 설정해야함 --> 다음 예제 Ex72
        }
    });

}