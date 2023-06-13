package com.ch96.ex59broadcastreceiver;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.btn).setOnClickListener(view -> clickBtn());
    }

    void clickBtn() {
        //Broadcast 보내기
        //원래 방송은 안드로이드라는 운영체제가 보내는 것 (이 예제에서는 실습목적)

        //'방송' : 실제로는 전파를 쏘는 것이 아니라 Intent 객체가 모든 앱에게 전달되는 것
        //즉 방송을 보낸다는 것은 Intent를 모든 앱에게 보내는 것
        
        //1.명시적 인텐트 - 특정 Receiver에게만 보내는 방송
        //SecondActivity로 이동한것처럼 정확한 이동할 곳을 지칭 ex. 홍길동에게
        //해당 프로젝트 내에서만 받을 수 있음, 본인 앱 안에 있는 리시버만 수신 (NFC나 블루투스와 같은 경우에만 사용)
//        Intent intent = new Intent(this, MyReceiver.class);
//        sendBroadcast(intent);
        
        //2.암시적 인텐트
        //정확한 이동할 곳이 없음, 불특정 다수에게 보냄 : 특별한 키워드 사용 ex. 인사담당자 앞
        //디바이스에 설치된 모든 앱 안에 있는 리시버가 듣는 방송 Intent (안드로이드 OS 에서 보내는 방송은 대부분 암식적 Intent)
        //Oreo 버전부터 암시적 인텐트 방송은 System만 할 수 있도록 제한 --> test 불가
        //그럼에도 꼭 암시적 Intent를 보내고 싶다면 Receiver를 Manifest가 아닌 java에서 동적으로 등록하면 가능 --> 멤버 함수영역
        Intent intent = new Intent();
        intent.setAction("test");//방송을 구별하는 식별자 역할[action]

        sendBroadcast(intent);
    }
    
    MyReceiver myReceiver;
    
    //액티비티가 화면에 보여질때 자동으로 실행되는 콜백 메소드
    @Override
    protected void onResume() {
        super.onResume();
        
        //리시버를 동적 등록
        //Manifest에 <>로 등록한것을 java에서 동적으로 등록한 것
        myReceiver = new MyReceiver();
        IntentFilter filter = new IntentFilter();
        filter.addAction("test");
        
        //Receiver를 등록하면서 filter도 설정
        registerReceiver(myReceiver, filter);
    }
    
    //액티비티가 화면에서 안보이기 시작할때 자동으로 실행되는 콜백메소드
    @Override
    protected void onPause() {
        super.onPause();

        //Receiver 제거
        unregisterReceiver(myReceiver);
    }
    
}