package com.ch96.ex42activity;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class ThirdActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_third);

        //제목줄 제목글씨 변경
        getSupportActionBar().setTitle("세번째 액티비티");
        //제목 왼쪽에 돌아가는 버튼 [업버튼] 보이도록
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    //[업버튼]이 클릭되었을때 자동으로 발동하는 콜백메소드
    @Override
    public boolean onSupportNavigateUp() {
        //끄는 방법 두가지
        //finish();
        super.onBackPressed(); //스마트폰의 뒤로가기를 눌렀을때 버튼
        return super.onSupportNavigateUp();
    }
}
