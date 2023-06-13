package com.ch96.ex29viewpager;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    //<>안에서 기본형 자료형 사용 불가 --> wrapper class
    ArrayList<Integer> imgIds = new ArrayList<>();
    ViewPager2 pager;
    MyAdapter adapter;
    Button btnPrev, btnNext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnPrev = findViewById(R.id.btn_prev);
        btnPrev.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //뷰페이저의 현재 위치의 인덱스번호 얻어오기(0,1,2...)
                int position = pager.getCurrentItem();
                //현재위치 이전 번호로 지정
                pager.setCurrentItem(position-1, true);
            }
        });

        btnNext = findViewById(R.id.btn_next);
        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int position = pager.getCurrentItem();
                pager.setCurrentItem(position+1, false);
            }
        });

        //대량의 데이터 추가 [실무에서는 DB나 서버에서 데이터를 읽음]
        imgIds.add(R.drawable.bg_one01);
        imgIds.add(R.drawable.bg_one02);
        imgIds.add(R.drawable.bg_one03);
        imgIds.add(R.drawable.bg_one04);
        imgIds.add(R.drawable.bg_one05);
        imgIds.add(R.drawable.bg_one06);
        imgIds.add(R.drawable.bg_one07);
        imgIds.add(R.drawable.bg_one08);
        imgIds.add(R.drawable.bg_one09);
        imgIds.add(R.drawable.bg_one10);

        pager = findViewById(R.id.pager);
        adapter = new MyAdapter(this, imgIds);
        pager.setAdapter(adapter);

    }
}