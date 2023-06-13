package com.ch96.ex65location;

import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    LocationManager locationManager;
    TextView tv, tv2, tv3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tv = findViewById(R.id.tv);
        tv2 = findViewById(R.id.tv2);
        tv3 = findViewById(R.id.tv3);

        //위치정보 관리자 객체 소환
        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);

        //디바이스에서 위치정보를 제공하는 장치가 여러 개
        //이런 것들을 위치정보 제공자 (Location Provider)라고 부름
        //디바이스에서 제공하는 프로바이더의 종류들을 먼저 확인해보기
        List<String> providers = locationManager.getAllProviders();
        StringBuffer buffer = new StringBuffer();
        for (String provider : providers) {
            buffer.append(provider + ", ");
        }
        tv.setText(buffer.toString());

        //내 위치 얻어오기
        findViewById(R.id.btn).setOnClickListener(v -> clickBtn());
        findViewById(R.id.btn2).setOnClickListener(v -> clickBtn2());
        findViewById(R.id.btn3).setOnClickListener(v -> clickBtn3());

        //위치 정보 제공에 대한 동적 퍼미션
        int checkPremission = checkSelfPermission(Manifest.permission.ACCESS_FINE_LOCATION); //FINE을 하면 COARSE도 같이 됨
        if (checkPremission == PackageManager.PERMISSION_DENIED) {
            permissionLauncher.launch(Manifest.permission.ACCESS_FINE_LOCATION);
        }

    }

    //퍼미션 요청 및 결과를 받아주는 대행사 객체 생성
    ActivityResultLauncher<String> permissionLauncher = registerForActivityResult(new ActivityResultContracts.RequestPermission(), new ActivityResultCallback<Boolean>() {
        @Override
        public void onActivityResult(Boolean result) {
            if (result) Toast.makeText(MainActivity.this, "위치정보제공허용", Toast.LENGTH_SHORT).show();
            else Toast.makeText(MainActivity.this, "위치정보제공거부", Toast.LENGTH_SHORT).show();
        }
    });

    void clickBtn() {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            return; //명시적 퍼미션
        }

        Location location = null; //해당하는 provider로 대응 : 분기코드
        if (locationManager.isProviderEnabled("fused")) { //위치정보 permission 필요
            location = locationManager.getLastKnownLocation("fused");
        } else if (locationManager.isProviderEnabled("gps")) {
            location = locationManager.getLastKnownLocation("gps");
        } else if (locationManager.isProviderEnabled("network")) {
            location = locationManager.getLastKnownLocation("network");
        }

        if(location  == null) tv2.setText("내 위치 찾을 수 없음");
        else {
            //위도, 경도 정보 얻어오기
            double latitude = location.getLatitude();
            double longitude = location.getLongitude();
            tv2.setText(latitude + " " + longitude);
        }
    }

    void clickBtn2() {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            return; //명시적 퍼미션
        }
        if (locationManager.isProviderEnabled("fused")) {
            locationManager.requestLocationUpdates("fused",5000, 2, locationListener);
        } else if (locationManager.isProviderEnabled("gps")) {
            locationManager.requestLocationUpdates("gps", 5000, 2, locationListener);
        }
    }

    LocationListener locationListener = new LocationListener() {
        @Override
        public void onLocationChanged(@NonNull Location location) {
            double latitude = location.getLatitude();
            double longitude = location.getLongitude();

            tv3.setText(latitude + " " + longitude);

        }
    };

    void clickBtn3() {
        //내위치 자동갱신 종료
        locationManager.removeUpdates(locationListener);
    }
}