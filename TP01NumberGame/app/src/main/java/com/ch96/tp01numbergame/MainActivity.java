package com.ch96.tp01numbergame;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    //참조변수 만들기
    EditText et;
    Button btn;
    TextView tv;
    //사용자가 맞출 랜덤값을 담을 변수
    int com;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Random rnd = new Random();
        com = rnd.nextInt(501) + 500; //500~1000

        //xml에서 만든 뷰들을 찾아와서 참조변수에 대입
        et = findViewById(R.id.et);
        btn = findViewById(R.id.btn);
        tv = findViewById(R.id.tv);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //사용자가 입력한 숫자를 가지고 있는 EditText에게 숫자모양의 글씨 받아오기
                String s = et.getText().toString();
                //String --> int
                int num = Integer.parseInt(s);

                if (num > com) tv.setText(num + "보다 작습니다.");
                else if (num < com) tv.setText(num + "보다 큽니다.");
                else tv.setText("정답입니다.");
            }
        });

    }
}