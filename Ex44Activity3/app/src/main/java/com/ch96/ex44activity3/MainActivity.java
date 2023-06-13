package com.ch96.ex44activity3;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContract;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityOptionsCompat;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    Button btn;
    TextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn = findViewById(R.id.btn);
        tv = findViewById(R.id.tv);
        btn.setOnClickListener(view -> {
            //결과를 받기위해 SecondActivity 실행
            Intent intent = new Intent(this, SecondActivity.class);
            resultLauncher.launch(intent);
        });
    }

    //결과를 받기위한 액티비티를 대신 실행시켜 주기위한 하청업체 객체 생성 및 등록
    ActivityResultLauncher<Intent> resultLauncher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback<ActivityResult>() {
        //intent가 돌아오면 호출
        @Override
        public void onActivityResult(ActivityResult result) {
            //혹시 실행시켰던 액티비티에서 [뒤로가기 버튼]으로 취소했을 수도 있음
            if (result.getResultCode() == RESULT_OK){
                //돌아온 Intent 객체 소환
                Intent intent = result.getData();
                //택배기사에게 넣었던 Extra 데이터들 뺴오기
                String name = intent.getStringExtra("name");
                int age = intent.getIntExtra("age", 0);

                tv.setText(name + " :" + " " + age);

            } else if (result.getResultCode() == RESULT_CANCELED) {
                Toast.makeText(MainActivity.this, "입력취소", Toast.LENGTH_SHORT).show();
            }

        }
    });

}