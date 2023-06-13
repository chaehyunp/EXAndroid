package com.ch96.ex08framelayout;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

public class MainActivity extends AppCompatActivity {

    Button btn01, btn02, btn03;
    LinearLayout layout01, layout02, layout03;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn01 = findViewById(R.id.btn_ny);
        btn02 = findViewById(R.id.btn_pr);
        btn03 = findViewById(R.id.btn_sd);

        layout01 = findViewById(R.id.layout01);
        layout02 = findViewById(R.id.layout02);
        layout03 = findViewById(R.id.layout03);

        btn01.setOnClickListener(listener);
        btn02.setOnClickListener(listener);
        btn03.setOnClickListener(listener);

    }
    View.OnClickListener listener = new View.OnClickListener() {
        @Override
        public void onClick(View view) { //파라미터 view : 클릭된 버튼 참조변수
            layout01.setVisibility(view.GONE);
            layout02.setVisibility(view.GONE);
            layout03.setVisibility(view.GONE);

            int id = view.getId();

            if (id == R.id.btn_ny) layout01.setVisibility(view.VISIBLE);
            else if (id == R.id.btn_pr) layout02.setVisibility(view.VISIBLE);
            else if (id == R.id.btn_sd) layout03.setVisibility(view.VISIBLE);

        }
    };
}