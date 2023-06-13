package com.ch96.ex44activity3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

public class SecondActivity extends AppCompatActivity {

    EditText etName, etAge;
    Button btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        etName = findViewById(R.id.et_name);
        etAge = findViewById(R.id.et_age);
        btn = findViewById(R.id.btn);

        btn.setOnClickListener(view -> {
            //EditText에 입력한 데이터들을 결과로 설정
            String name = etName.getText().toString();
            int age = Integer.parseInt(etAge.getText().toString());

            //다시 돌아갈 택배기사(Intent)에게 결과 데이터 넣기
            //액티비티간의 데이터전달은 Intent만 가능함
            Intent intent = getIntent();
            intent.putExtra("name", name);
            intent.putExtra("age", age);

            //data의 값이 올바른 값인지, 취소된 값인지
            setResult(RESULT_OK, intent);

            //작성완료됐으므로 종료
            finish();
        });

    }
}