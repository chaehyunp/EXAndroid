package com.ch96.ex47activitysystemintent;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.btn_dial).setOnClickListener(view -> {
            //다이얼화면(전화앱) 실행
            Intent intent = new Intent();
            intent.setAction(Intent.ACTION_DIAL);

            //미리 전화번호까지 전달하려면
            intent.setData(Uri.parse("tel:0101235678"));

            startActivity(intent);
        });

        findViewById(R.id.btn_sms).setOnClickListener(view -> {
            Intent intent = new Intent().setAction(Intent.ACTION_SENDTO);
            intent.setData(Uri.parse("smsto:0101235678"));

            //문자내용을 프로그래밍
            intent.putExtra("sms_body", "How are you");

            startActivity(intent);
        });

        findViewById(R.id.btn_web).setOnClickListener(view -> {
            //웹페이지 보는 화면 [크롬브라우저]
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://m.naver.com")); //액션, Data
            startActivity(intent);
        });

        findViewById(R.id.btn_camera).setOnClickListener(view -> {
            Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            startActivity(intent);
        });
    }
}