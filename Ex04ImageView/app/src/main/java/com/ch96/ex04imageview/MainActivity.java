package com.ch96.ex04imageview;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    //액티비티가 보여주는 뷰들의 참조변수는 가급적 멤버변수에 선언
    ImageView iv;
    Button btnKill, btnSorry, btnTired, btnAlone;
    int num = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        iv = findViewById(R.id.iv);
        btnKill = findViewById(R.id.btn01);
        btnSorry = findViewById(R.id.btn02);
        btnTired = findViewById(R.id.btn03);
        btnAlone = findViewById(R.id.btn04);

        btnKill.setOnClickListener(listener);
        btnSorry.setOnClickListener(listener);
        btnTired.setOnClickListener(listener);
        btnAlone.setOnClickListener(listener);

        //이미지뷰를 클릭했을때 그림을 차례대로 변경
        iv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //R.drawable.pms_mind는 숫자!
                iv.setImageResource(R.drawable.pms_alone + num);
                num++;
                if (num > 4) num = 0;
            }
        });

    }

    //멤버변수는 객체가 생성될때 만들어지고 메소드는 객체생성 이후에 실행됨
    //따라서 멤버변수가 메소드보다 아래줄에 있어도 먼저 생성됨
    View.OnClickListener listener = new View.OnClickListener() {
        @Override
        public void onClick(View view) { //파라미터 view : 클릭을 한 버튼을 참조하는 참조변수
            //왜 파라미터가 View일까? View는 전부 클릭이 가능함
            
            //어떤 뷰(버튼)을 클릭했는지 알아내기
            int id = view.getId();

            //id값에 따라 해당버튼 구현
//            switch (id){
//                case R.id.btn01
//            } Giraffe 버전부터 case R.id 가 상수가 아니라 switch문을 쓸 수 없음
            if (id == R.id.btn01) iv.setImageResource(R.drawable.pms_kill);
            else if (id == R.id.btn02) iv.setImageResource(R.drawable.pms_sorry);
            else if (id == R.id.btn03) iv.setImageResource(R.drawable.pms_tired);
            else if (id == R.id.btn04) iv.setImageResource(R.drawable.pms_alone);

//            //이미지뷰의 이미지를 변경
//            iv.setImageResource(R.drawable.pms_kill);
        }
    };
    
}