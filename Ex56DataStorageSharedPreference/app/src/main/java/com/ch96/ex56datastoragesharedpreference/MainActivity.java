package com.ch96.ex56datastoragesharedpreference;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

import java.util.prefs.Preferences;

public class MainActivity extends AppCompatActivity {

    EditText etName, etAge;
    TextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etName = findViewById(R.id.et_name);
        etAge = findViewById(R.id.et_age);
        tv = findViewById(R.id.tv);

        findViewById(R.id.btn_save).setOnClickListener(view -> clickSave());
        findViewById(R.id.btn_load).setOnClickListener(view -> clickLoad());

    }

    void clickSave() {
        //저장할 데이터
        String name = etName.getText().toString();
        int age = Integer.parseInt(etAge.getText().toString());

        //SharedPreferences로 저장
        //"Data.xml" 파일에 데이터를 저장하기위해 객체 얻어오기
        //Shared는 어느 액티비티에서든 사용가능(앱설정의 경우) 그냥 Preference는 사용하는 액티비티에서만 사용가능
        SharedPreferences pref = getSharedPreferences("Data", MODE_PRIVATE); //자동으로 확장자 .xml, Append 쓸거면 Internal/External

        //저장작업 시작
        SharedPreferences.Editor editor = pref.edit();
        editor.putString("name", name); //("식별자", 값)
        editor.putInt("age", age);

        //작업완료
        editor.commit(); //내부적으로 transaction

        etName.setText("");
        etAge.setText("");

        tv.setText("SAVED");
    }

    void clickLoad() {

        SharedPreferences pref = getSharedPreferences("Data", MODE_PRIVATE); //파일명만 정하고 읽고 쓸때 정해진 포맷

        String name = pref.getString("name", "NoName"); //("식별자", default value) default value [저장된 값이 없을때의 값]
        int age = pref.getInt("age", 0);

        tv.setText(name + " : " + age);

    }

}