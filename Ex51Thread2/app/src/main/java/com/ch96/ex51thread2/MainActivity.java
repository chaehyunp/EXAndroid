package com.ch96.ex51thread2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import java.util.Date;

public class MainActivity extends AppCompatActivity {

    MyThread thread;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //화면에 보이지 않더라도 별도 Thread는 백그라운드에서 동작하고 있다는 것을 확인
        findViewById(R.id.btn).setOnClickListener(view -> {
            thread = new MyThread();
            thread.start();
        });
    }

    //이너클래스====================================================================
    class MyThread extends Thread {

        boolean isRun = true;

        @Override
        public void run() {
            //5초마다 현재시간을 Toast로 보이도록...
            while(isRun){//while 안에 가능한 상수값 쓰지말기
                //현재시간을 문자열로
                Date now = new Date();
                String s = now.toString();

                //Toast로 현재시간(s) 보여주기
                //Toast.makeText(MainActivity.this, s, Toast.LENGTH_SHORT).show(); //ERROR : 별도의 Thread는 UI 건들일 수 없다!!!!!!!!!
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(MainActivity.this, s, Toast.LENGTH_SHORT).show();

                        //Log 기록 남기기
                        Log.i("EX51", s);
                    }
                });

                //5초간 재우기
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
            Log.i("EX51", "스레드 종료!");
        }
    }//===========================================================================

    //액티비티가 메모리에서 없어질때(종료) 자동으로 실행되는 콜백메소드
    @Override
    protected void onDestroy() { //<--> onCreate()
        super.onDestroy();

        //AndroidsStudio의 [Logcat]탭에 기록[]Log] 남기기
        Log.i("EX51", "onDestroy");

        //Thread의 작업을 그만하도록 해야할 의무가 있음...!
        thread.isRun = false; //어떤 객체의 값을 직접 건들이는 것은 좋지않지만 직관적으로 연습상...
        thread = null;
    }

    //디바이스의 뒤로가기버튼을 눌렀을때 반응하는 콜백메소드
    @Override
    public void onBackPressed() {
        //super.onBackPressed();
        //MainActivity는 뒤로가기를 눌러도 안꺼짐 (숨겨져있음)
        finish();
    }
}