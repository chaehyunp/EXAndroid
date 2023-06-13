package com.ch96.ex30fragment;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    TextView tv;
    Button btn;
    Button btn2;
    MyFragment myFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tv = findViewById(R.id.tv);
        btn = findViewById(R.id.btn);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tv.setText("MainActivity --> MainActivity");
            }
        });

        btn2 = findViewById(R.id.btn2);
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //MyFragment 객체를 찾아서 참조 --> 멤버변수에 참조변수만들기
                //Fragment를 관리하는 관리자 객체를 소환 : FragmentManager
                //이미 존재하고 있기때문에 new가 아닌 get
                FragmentManager manager = getSupportFragmentManager(); //androidx버전 = support 버전 사용
                myFragment = (MyFragment)manager.findFragmentById(R.id.frag_my);

                myFragment.tv.setText("MainActivity --> MyFragment");
            }
        });
    }
}