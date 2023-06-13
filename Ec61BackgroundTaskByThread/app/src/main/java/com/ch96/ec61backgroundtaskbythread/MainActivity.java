package com.ch96.ec61backgroundtaskbythread;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    MyThread myThread; //스레드 참조변수

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.btn_start).setOnClickListener(view -> {
            //백그라운드에서 반복작업을 수행하는 별도의 Thread 객체 생성 및 실행
            if (myThread != null) return; //이미 스레드 객체가 있는데 사용자가 버튼을 계속 눌러서 객체 생성을 반복할 수도 있기 때문에

            myThread = new MyThread();
            myThread.start(); //run을 직접 부르면 MainThread가 일을 함, start를 할 경우 자동 run method 발동


        });
        findViewById(R.id.btn_stop).setOnClickListener(view -> {
            if (myThread != null) { //사용자가 start를 누르지 않고 (객체가 없는 상태에서) stop을 누를 수도 있기 때문에
                //.stop()로 thread를 멈출경우 안드로이드에서는 에러
                //스레드는  run 메소드가 종료되면 멈춤, 1회성 객체라 다시 실행불가
                //while문때문에 run메소드가 종료되지 않음 --> 멈추기위해 while 문의 조건갖ㅅ isRun을 false로 변경
                myThread.isRun = false; //누군가의 메소드를 직접 건들이는 것 좋지 않음(연습상...)
                myThread = null; //참조를 끊겠다는 말, 참조가 되지 않으면 garbage collector가 객체를 지워줌
                //null이 아니면 계속 참조되고 있기 때문에 다시 실행할 수 없음
            } else {
                Toast.makeText(this, "Thread 객체를 참조하고 있지 않습니다.", Toast.LENGTH_SHORT).show();
            }
        });
    }

    //디바이스의 뒤로가기 버튼을 클릭했을때 반응하는 콜백 메소드
    @Override
    public void onBackPressed() {
        //super.onBackPressed(); //기본 MainActivity는 종료되지 않아서 강제종료시키기
        finish();
    }

    //백그라운드 동작을 하는 별도 Thread 클래스 설계
    class MyThread extends Thread {

        boolean isRun = true;

        @Override
        public void run() {
            while(isRun) { //별도의 Thread는 Ui 작업 불가 runOnUiThread 이용!
                runOnUiThread(() -> {Toast.makeText(MainActivity.this, "백그라운드 작업...", Toast.LENGTH_SHORT).show();
                    Log.i("Ex61", "백그라운드 작업...");}); //new runnable도 람다식으로 줄여쓰기 가능

                //5초간 대기
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }
}