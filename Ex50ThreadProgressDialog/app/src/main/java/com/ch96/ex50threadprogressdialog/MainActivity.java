package com.ch96.ex50threadprogressdialog;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //람다에서 실행문 한 줄이면 중괄호와 세미콜론 생략 가능
        findViewById(R.id.btn_wheel).setOnClickListener(view -> clickBtnWheel());
        findViewById(R.id.btn_bar).setOnClickListener(view -> clickBtnBar());

    }

    ProgressDialog dialog; //지역변수와 다르게 멤버변수는 쓰레기값을 가지지 않고 '0에 해당하는 값'을 가짐
    int guage = 0;

    void clickBtnWheel(){ //진행도를 알 수 없을때 사용
        //wheel type progress dialog...
        if (dialog != null) return; //다이얼로그가 이미 있다면 띄우지마라 (객체가 중복될까봐)

        dialog = new ProgressDialog(this);
        dialog.setTitle("wheel dialog");
        dialog.setMessage("downloading...");
        dialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        dialog.setCanceledOnTouchOutside(false);
        dialog.show();

        //테스트 목적으로 3초 후 다이얼로그 종료
        handler.sendEmptyMessageDelayed(0, 3000);
    }

    Handler handler = new Handler(Looper.getMainLooper()){
        @Override
        public void handleMessage(@NonNull Message msg) {
            dialog.dismiss();
            dialog = null;
        }
    };

    void clickBtnBar(){
        //bar type progress dialog...
        if (dialog != null) return;

        dialog = new ProgressDialog(this);
        dialog.setTitle("bar dialog");
        dialog.setMessage("downloading...");
        dialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
        dialog.setCanceledOnTouchOutside(false);
        dialog.setMax(100);
        dialog.show();

        //막대바의 guage 값을 증가시키는 별도의 thread
        new Thread(){
            @Override
            public void run() {
                guage= 0;
                while(guage < 100) {
                    guage++;
                    dialog.setProgress(guage); //dialog는 UI지만 내부적으로 다 해줌

                    //0.05호 대기
                    try {
                        Thread.sleep(50);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
                dialog.dismiss();
                dialog = null;
            }
        }.start();
    }
}