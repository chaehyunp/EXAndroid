package com.ch96.ex05compoundbutton;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;
import android.widget.ToggleButton;

import com.google.android.material.button.MaterialButton;

public class MainActivity extends AppCompatActivity {

    //액티비티가 보여줄 뷰들의 참조변수는 가급적 멤버변수의 위치에...
    CheckBox cb01, cb02, cb03;
    Button btn;
    TextView tv;
    ToggleButton tb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //xml에서 만든 뷰들을 찾아와서 참조변수들에 대입
        cb01 = findViewById(R.id.cb01);
        cb02 = findViewById(R.id.cb02);
        cb03 = findViewById(R.id.cb03);
        btn = findViewById(R.id.btn);
        tv = findViewById(R.id.tv);

        //cb03.setChecked(true);

        //버튼을 클릭했을때 반응하기 위해서 버튼 클릭 이벤트를 듣는 리스너 객체 생성 및 설정
        btn.setOnClickListener(listener);

        //체크박스의 체크상태가 변경될 때마다 반응하는 리스너 설정하기
        cb01.setOnCheckedChangeListener(checkedChangeListener);
        cb02.setOnCheckedChangeListener(checkedChangeListener);
        cb03.setOnCheckedChangeListener(checkedChangeListener);

        //토글버튼 찾아서 연결하기
        tb = findViewById(R.id.tb);
        tb.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                //두번째 파라미터 b : boolean값 - 체크된 상태값 true/false
                tv.setText("체크상태 : " + b);
            }
        });


    }
    View.OnClickListener listener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            //체크박스들이 체크되어 있는지 확인하여 체크된 체크박스의 글씨를 TextView에 보여주기
            String s = "";
            //if (cb01.isChecked()) tv.setText(cb01.getText().toString());
            //이렇게 쓰면 값이 누적되지 않음
            if (cb01.isChecked()) s = s + cb01.getText().toString();
            if (cb02.isChecked()) s = s + cb02.getText().toString();
            if (cb03.isChecked()) s = s + cb03.getText().toString();

            tv.setText(s);
        }
    };

    //체크 상태 변경을 듣는 리스너 객체 생성 - 멤버변수 위치
    CompoundButton.OnCheckedChangeListener checkedChangeListener = new CompoundButton.OnCheckedChangeListener() {
        @Override
        public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
            //체크박스들이 체크되어 있는지 확인하여 체크된 체크박스의 글씨를 TextView에 보여주기
            String s = "";
            //if (cb01.isChecked()) tv.setText(cb01.getText().toString());
            //이렇게 쓰면 값이 누적되지 않음
            if (cb01.isChecked()) s = s + cb01.getText().toString();
            if (cb02.isChecked()) s = s + cb02.getText().toString();
            if (cb03.isChecked()) s = s + cb03.getText().toString();

            tv.setText(s);
        }
    };
}