package com.ch96.ex43activity2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class SecondActivity extends AppCompatActivity {

    TextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        //이 액티비티를 실행시켜 준 Intent(택배기사) 소환
        Intent intent = getIntent();
        //택배기사를 통해 전달된 Extra 데이터가 있으면 받기
        String name = intent.getStringExtra("name");
        int age = intent.getIntExtra("age", 0);
        //defaultValue : 값이 없을 경우 --> 기본형 자료형은 값을 꼭 가지고 있어야함, 참조형은 null값을 주는것이 가능하기때문에

        getSupportActionBar().setTitle(name);

        tv = findViewById(R.id.tv);
        tv.setText(age + "");
    }
}