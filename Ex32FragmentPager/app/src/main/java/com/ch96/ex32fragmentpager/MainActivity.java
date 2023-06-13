package com.ch96.ex32fragmentpager;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

import android.os.Bundle;

import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

public class MainActivity extends AppCompatActivity {

    ViewPager2 pager;
    MyAdapter adapter;
    TabLayout tabLayout;
    String[] tabTitle = new String[]{"TAB1", "TAB2", "TAB3"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        pager = findViewById(R.id.pager);
        adapter = new MyAdapter(this);
        pager.setAdapter(adapter);

        tabLayout = findViewById(R.id.tab_layout);

        //TabLayout과 ViewPager를 연동하기 - 중재자(Mediator) 객체
        TabLayoutMediator mediator = new TabLayoutMediator(tabLayout, pager, new TabLayoutMediator.TabConfigurationStrategy() {
            @Override
            public void onConfigureTab(@NonNull TabLayout.Tab tab, int position) {
                //pager가 만들 page 갯수만큼 메소드 실행 - 이곳에서 Tab의 글씨 등을 설정
                tab.setText(tabTitle[position]);
            }
        });

        //중재자 객체를 붙이기
        mediator.attach();
    }
}