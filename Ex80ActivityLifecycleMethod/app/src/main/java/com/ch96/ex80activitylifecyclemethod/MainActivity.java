package com.ch96.ex80activitylifecyclemethod;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

public class MainActivity extends AppCompatActivity {

    //Activity 클래스의 Lifecycle Method
    //액티비티가 객체로 만들어져서 화면에 보여지고 종료되어 메모리에서 사라질때까지 상황에 따라 자동으로 실행되는 생명주기 콜백 메소드
    //생명주기 콜백메소드 - 주요 6개 메소드


    //1. 액티비티가 처음 메모리에 만들어질때 자동으로 실행되는 메소드
    //이 메소드가 실행되는동안에는 어떤 UI도 그려지지 않은 상태
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Log.i("TEST TAG", "onCreate");

        findViewById(R.id.btn).setOnClickListener(v -> {
            startActivity(new Intent(this, SecondActivity.class));
        });
    }

    //2. 액티비티의 뷰들이 보이기 시작할때 자동호출
    //이 메소드 중에는 터치해도 반응하지 않음 (인터랙션 불가)

    @Override
    protected void onStart() {
        super.onStart();

        Log.i("TEST TAG", "onStart");
    }

    //3. 액티비티가 보이고 터치도 가능한 상태

    @Override
    protected void onResume() {
        super.onResume();

        Log.i("TEST TAG", "onResume");
    }

    //위 1,2,3 메소드가 실행된 후 액티비티는 실행중인 상태 (Running)

    //4. 어떤 이유에서든 액티비티가 안보이기 시작할때 자동 실행되는 메소드
    //화면에 UI는 아직 보이지만 터치는 안되는 상태 - 보통 이곳에서 스레드를 pause 처리
    @Override
    protected void onPause() {
        super.onPause();

        Log.i("TEST TAG", "onPause");
    }

    //5. 완전히 안보일때 자동실행되는 메소드
    @Override
    protected void onStop() {
        super.onStop();

        Log.i("TEST TAG", "onStop");
    }

    //액티비티가 다른 액티비티에 의해 가려진 상태라면 4,5 메소드까지 작동

    //6. 스마트폰의 '뒤로가기' 버튼이나 finish() 메소드로 액티비티가 종료되었을때, 액티비티가 메모리에서 소멸될때 자동으로 실행되는 메소드
    //Android 12버전 (api 31) 디바이스부터는 처음 실행되는 액티비티(intent-filter를 가지는)는 '뒤로가기' 버튼을 눌러도 finish() 되지 않음 : 속도 개선을 위해
    //만약 뒤로가기 버튼으로 종료시키고 싶다면 onBackPressed() 재정의하여 직접 finish() 하거나 최근실행목록에서 스와이프로 제거
    @Override
    protected void onDestroy() {
        super.onDestroy();

        Log.i("TEST TAG", "onDestroy");
    }
}