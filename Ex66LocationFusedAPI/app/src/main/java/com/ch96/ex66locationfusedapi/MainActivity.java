package com.ch96.ex66locationfusedapi;

import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import android.os.Looper;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;

public class MainActivity extends AppCompatActivity {

    TextView tv;

    //Fused API : Google 지도 앱에 사용되고 있는 위치 정보 제공자 최적화 라이브러리
    //Google 기능 라이브러리 추가 : play-services-location --> Project Structure에서 추가

    FusedLocationProviderClient providerClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tv = findViewById(R.id.tv);
        findViewById(R.id.btn).setOnClickListener(v -> clickBtn());

    }

    void clickBtn() {
        //위치정보는 민감정보이므로 동적 퍼미션 필수
        int permissionResult = checkSelfPermission(Manifest.permission.ACCESS_FINE_LOCATION);
        if(permissionResult == PackageManager.PERMISSION_DENIED) {
            resultLauncher.launch(Manifest.permission.ACCESS_FINE_LOCATION);
            return;
        }

        //위치 정보 최적화 객체 소환
        providerClient = LocationServices.getFusedLocationProviderClient(this);

        //위치 정보의 최적화를 위한 기준 필요
        LocationRequest locationRequest = LocationRequest.create();
        locationRequest.setPriority(locationRequest.PRIORITY_HIGH_ACCURACY); //정확도 우선
        locationRequest.setInterval(5000); //갱신 주기

        //위치 정보 갱신 요청
        providerClient.requestLocationUpdates(locationRequest, locationCallback, Looper.getMainLooper());

    }

    //위치 정보 갱신때마다 발동하는 콜백 메소드를 가진 객체
    LocationCallback locationCallback = new LocationCallback() {
        @Override
        public void onLocationResult(@NonNull LocationResult locationResult) {
            super.onLocationResult(locationResult);

            Location location = locationResult.getLastLocation();
            double latitude = location.getLatitude();
            double longitude = location.getLongitude();

            tv.setText(latitude + " " + longitude);
        }
    };

    //액티비티가 화면에서 안보이기 시작할때 자동으로 발동하는 콜백메소드


    @Override
    protected void onPause() {
        super.onPause();

        //자동 갱신 종료
        if (providerClient != null) {
            providerClient.removeLocationUpdates(locationCallback);
        }
    }

    //퍼미션 요청 대행와 계약
    ActivityResultLauncher<String> resultLauncher = registerForActivityResult(new ActivityResultContracts.RequestPermission(), result ->{
        if(result) Toast.makeText(this, "위치 정보 허용", Toast.LENGTH_SHORT).show();
        else Toast.makeText(this, "위치 정보 거부", Toast.LENGTH_SHORT).show();
    });

}