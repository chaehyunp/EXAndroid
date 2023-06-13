package com.ch96.ex13edittext;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.AbsListView;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    EditText et01, et02, et03;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        et01 = findViewById(R.id.et01);
        et02 = findViewById(R.id.et02);
        et03 = findViewById(R.id.et03);

        //EditText에 글씨가 변경될때마다 감시하는 리스너 객체 생성 및 추가
        et01.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {}

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                //CharSequence = String의 부모
                //글자 수가 3글자인지 확인
                if (charSequence.length() >= 3) et02.requestFocus();}

            @Override
            public void afterTextChanged(Editable editable) {}
        });

        et02.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                //글자 수가 4글자인지 확인
                if (charSequence.length() >= 4) et03.requestFocus();

            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });


    }
}