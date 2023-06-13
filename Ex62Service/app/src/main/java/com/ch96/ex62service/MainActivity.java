package com.ch96.ex62service;

import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.btn_start).setOnClickListener(view -> {
            //백그라운드 작업을 Service 컴포넌트 이용
            Intent intent = new Intent(this, MyService.class);
            //Oreo버전부터 Foreground Service 도입
            if(Build.VERSION.SDK_INT >= 26) startForegroundService(intent);
            else startService(intent);
        });
        findViewById(R.id.btn_stop).setOnClickListener(view -> {
            Intent intent = new Intent(this, MyService.class);
            stopService(intent);
        });
        
        //알림에 대한 퍼미션 체크 및 요청
        int checkResult = checkSelfPermission(Manifest.permission.POST_NOTIFICATIONS);
        if(checkResult == PackageManager.PERMISSION_DENIED) {
            //퍼미션 요청 결과를 받아주는 대행사 객체 활용
            permissionResultLauncher.launch(Manifest.permission.POST_NOTIFICATIONS);
        }
    }
    
    //퍼미션 요청 결과를 받아주는 대행사 객체 생성 및 등록
    ActivityResultLauncher<String> permissionResultLauncher = registerForActivityResult(new ActivityResultContracts.RequestPermission(), new ActivityResultCallback<Boolean>() {
        @Override
        public void onActivityResult(Boolean result) {
            if (result == true) Toast.makeText(MainActivity.this, "알림 허용", Toast.LENGTH_SHORT).show();
            else Toast.makeText(MainActivity.this, "알림 및 서비스 불가", Toast.LENGTH_SHORT).show();
        }
    });

    @Override
    public void onBackPressed() {
        finish();
    }
}