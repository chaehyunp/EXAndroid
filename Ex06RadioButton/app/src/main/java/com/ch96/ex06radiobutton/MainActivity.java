package com.ch96.ex06radiobutton;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RatingBar;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity {
    RadioGroup rg;
    Button btn;
    TextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        rg = findViewById(R.id.rg);
        btn = findViewById(R.id.btn);
        tv = findViewById(R.id.tv);
        
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //RidioButton 중에 선택된 버튼의 글씨 얻어와서 TextView에 보여주기
                //Radio그룹에게 체크된 RadioButton의 id 얻어오기
                int id  = rg.getCheckedRadioButtonId();

                //체크된 id의 RadioButton 객체를 참조
                RadioButton rb = findViewById(id);

                tv.setText(rb.getText().toString());
            }
        });

        //RadioButton의 체크상태가 변경될 때마다 반응하는 리스너는 버튼들에 붙이지 말고
        //RadioGroup에 붙여서 한 번에 제어하는 것을 권장
        rg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                //두 번째 파라미터 i : 선택된 RadioButton의 id 값
                RadioButton rb = findViewById(i);
                tv.setText(rb.getText().toString());
            }
        });

        //다른 뷰들의 이벤트 처리도 동일한 방식
        RatingBar ratingBar = findViewById(R.id.rating);
        ratingBar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float v, boolean b) {
                //두 번째 파라미터 v : 레이팅 값
                tv.setText("별점 : " + v);
            }
        });

    }
}