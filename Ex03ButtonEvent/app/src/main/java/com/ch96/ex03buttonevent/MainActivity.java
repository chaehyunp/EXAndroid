package com.ch96.ex03buttonevent;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    //액티비티가 보여주는 뷰들의 참조변수는 가급적 멤버변수의 위치에..!
    //썼는데 import error 나면 alt + enter --> import
    TextView tv;
    Button btn;
    EditText et;
    Button btn2;
    TextView tv2;

    //프로그램 실행 --> XML에서 객체 생성 && 멤버변수 생성 --> onCreate()
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //XML을 통해 만든 View 객체들을 찾아와서 참조변수에 대입
        tv = findViewById(R.id.testText);
        btn = findViewById(R.id.testBtn);

        //버튼이 클릭되었을때 textView가 보여주는 글씨 변경
        View.OnClickListener listener = new View.OnClickListener() {
            @Override
            public void onClick(View view) { //이 리스너가 감시하는 버튼이 클릭될 때 자동 발동
                //TextView 글씨 변경
                tv.setText("Nice to meet you!");
            }
        };
        btn.setOnClickListener(listener);

        //xml에서 만든 View들 찾아오기
        et = findViewById(R.id.et);
        btn2 = findViewById(R.id.btn);
        tv2 = findViewById(R.id.tv);

        //버튼 2번 눌렀을때 EditText에 써있는 글씨 가져와서 TextView에 보여주기
        //참조변수 만들지말고 btn2 리스너를 세팅하면서 바로 객체 생성 (어차피 리스너 하나당 객체 하나만 사용하는 경우가 많고 헷갈리지 않음)
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String s = et.getText().toString();
                tv2.setText(s);

                //작성되어있던 EditText 글씨 지우기 - 다음 입력을 편하게 하기위해
                et.setText("");
            }
        });

    }
}