package com.ch96.ex48thread;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    Button btn, btn2;
    TextView tv;
    int num;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tv = findViewById(R.id.tv);
        btn = findViewById(R.id.btn);
        btn.setOnClickListener(view -> {
            //오래 걸리는 작업 [ex. Network / DB 작업 등...]
            for (int i = 0; i < 20; i++) {
                num++;
                tv.setText(num+"");
                //Thread를 1초간 잠재우기
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {throw new RuntimeException(e);}
            }
        });

        btn2 = findViewById(R.id.btn2);
        btn2.setOnClickListener(view -> {
            //오래 걸리는 작업 - 별도의 Thread에게 수행하도록
            MyThread thread = new MyThread(); //직원채용
            thread.start(); //thread의 작업 시작명령 - Thread 클래스의 run() method 실행
        });

    }

    //오래 걸리는 작업을 수행할 Thread의 작업내역 설계
    class MyThread extends Thread { //모든 thread는 run() 메소드를 가지고 있음(아무것도 쓰여있지않음) -> ovreride
        @Override
        public void run() { //thread가 실행할 코드를 작성하는 영역
            for(int i = 0; i < 20; i++) {
                num++;
                //tv.setText(num+""); //ERROR : 안드로이드에서 UI 변경작업은 반드시 MainThread만 하도록 강제되어있음, 별도의 Thread는 UI작업 불가능
                //MainThread에게 UI변경작업 요청
                //방법1. Handler 객체를 이용
                //handler.sendEmptyMessage(0); //int what - 여러 Thread가 보내는 Message의 식별번호, 의미없으면 그냥 0써주자

                //방법2. Activity 클래스의 runOnUiThread() 메소드 이용
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        //이 영역 안에서는 UI작업 가능
                        tv.setText(num+"");
                    }
                });

                //Thread를 1초간 재우기
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {throw new RuntimeException(e);}
            }
        }
    }
    //별도 Thread가 MainThread에게 UI변경 작업을 요청할때 그 메시지를 전달하는 역할을 하는 객체 생성
    Handler handler = new Handler(Looper.getMainLooper()){ //객체 생성하면서 {} : 익명클래스
        @Override
        public void handleMessage(@NonNull Message msg) { 
            //sendMessage()를 통해 메시지가 전달되면 자동으로 실행되는 영역
            //이 영역에서는 UI작업 가능
            tv.setText(num+"");
        }
    };

}