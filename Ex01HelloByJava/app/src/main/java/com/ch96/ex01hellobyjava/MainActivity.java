package com.ch96.ex01hellobyjava;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    //화면에 보이는 뷰들의 참조벼수는 가급적 멤버변수로...
    TextView tv;
    Button btn;
    LinearLayout layout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_main);

        //Java 언어만으로 화면 꾸미기
        //Acrivity는  View클래스의 능력을 가진(상속한) 클래스들만 보여줄 수 있음

        //글씨를 보여주는 TextView 객체로 생성 및 설정
        tv = new TextView(this);
        tv.setText("야호~~");

        //액티비티에 tv 붙이기
        //this.setContentView(tv);

        //버튼기능을 가진 객체 생성 및 설정
        btn = new Button(this);
        btn.setText("신나면 눌러~~");

        //액티비티에 버튼 붙이기
        //setContentView(btn);

        //액티비티는 한 번에 1개의 뷰만 놓여질 수 있음(add가 아니라 set)
        //여러 개의 뷰를 배치하려면 ...Layout ViewGroup 클래스가 필요

        //ViewGroup 중에서 가장 간단한  LinearLayout 이라는 이름의 클래스를 객체로 만들어서 사용
        layout = new LinearLayout(this);
        layout.setOrientation(LinearLayout.VERTICAL);
        //레이아웃에 뷰 추가하기
        layout.addView(tv);
        layout.addView(btn);

        //액티비티에 뷰그룹 붙이기
        setContentView(layout);

        //버튼 클릭했을때 TextView의 글씨 변경해보기
        View.OnClickListener listener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //이 리스너가 감시하는 버튼이 클릭되었을때 자동을 발동하는 메소드 영역
                tv.setText("예~ 신나~");
            }
        };
        btn.setOnClickListener(listener);

    }
}