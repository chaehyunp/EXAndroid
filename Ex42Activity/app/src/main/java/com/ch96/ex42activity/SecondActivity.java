package com.ch96.ex42activity;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class SecondActivity extends AppCompatActivity { //호환성을 가진 Activity(Compat)
    //액티비티가 보여줄 뷰를 설정하기 위해 자동으로 실행되는 콜백 메소드
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second); //프레그먼트와 다르게 자동으로 내부에서 inflate함
    }
}
