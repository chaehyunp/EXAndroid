package com.ch96.ex64alarm;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.AlarmManager;
import android.app.DatePickerDialog;
import android.app.PendingIntent;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.provider.AlarmClock;
import android.widget.DatePicker;
import android.widget.TimePicker;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.btn1).setOnClickListener(v -> clickBtn1());
        findViewById(R.id.btn2).setOnClickListener(v -> clickBtn2());
        findViewById(R.id.btn3).setOnClickListener(v -> clickBtn3());
    }
    void  clickBtn1() {
        //알람시계 시스템 앱을 통애 알람설정
        Intent intent = new Intent(AlarmClock.ACTION_SET_ALARM);
        intent.putExtra(AlarmClock.EXTRA_HOUR, 14);
        intent.putExtra(AlarmClock.EXTRA_MINUTES, 30);
        intent.putExtra(AlarmClock.EXTRA_MESSAGE, "TEST ALARM");
        intent.putExtra(AlarmClock.EXTRA_DAYS, new int[]{Calendar.MONDAY, Calendar.WEDNESDAY});
        intent.putExtra(AlarmClock.EXTRA_SKIP_UI, true); //설정화면 안보이고 바로 생성
        startActivity(intent);
    }

    void clickBtn2() {
        //알람매니저를 이용하여 직접 알람 설정해보기
        AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        //알람시간이 되었을때 실행될 컴포넌트[Activity, BroadcastReceiver, Service] 지정
        Intent intent = new Intent(this, AlarmReceiver.class);
        PendingIntent pendingIntent= PendingIntent.getBroadcast(this, 100, intent, PendingIntent.FLAG_IMMUTABLE);
        alarmManager.setAndAllowWhileIdle(AlarmManager.RTC_WAKEUP, System.currentTimeMillis()+10000, pendingIntent); //버전업이 되면서 이 설정값만 가능
    }

    void clickBtn3() {
        //사용자가 원하는 날짜와 시간을 선택하여 알람지정
        //날짜 선택 다이얼로그 보이기
        DatePickerDialog dialog = new DatePickerDialog(this);
        dialog.setOnDateSetListener(new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
                //파라미터 i, i1, i2 : year, month, day
                year = i;
                month = i1;
                day = i2;

                //시간 선택기 보이기
                //현재시간을 기준시간으로 설정
                Calendar calendar = Calendar.getInstance();
                hour = calendar.get(Calendar.HOUR_OF_DAY); //24
                minute = calendar.get(Calendar.MINUTE);
                new TimePickerDialog(MainActivity.this, timeSetListener, hour, minute, true).show();
            }
        });
        dialog.show();
    }

    int year, month, day, hour, minute;

    //시간 설정에 반응하는 리스너 객체 생성
    TimePickerDialog.OnTimeSetListener timeSetListener = new TimePickerDialog.OnTimeSetListener() {
        @SuppressLint("ScheduleExactAlarm")
        @Override
        public void onTimeSet(TimePicker timePicker, int i, int i1) {
            //파라미터 i, i1 : hour, minute
            hour = i;
            minute = i1;

            //정해진 날짜로 Calendr 객체 생성
            Calendar calendar = Calendar.getInstance(); //현재날짜 시간으로 세팅됨
            calendar.set(year, month, day, hour, minute, 0); //바꿔치기

            //정한 시간으로 알람 설정
            AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);

            //알람설정 시간에 실행할 컴포넌트[Activity, BroadcastReceiver, Service] 지정
            Intent intent = new Intent(MainActivity.this, AlarmReceiver.class);
            PendingIntent pendingIntent = PendingIntent.getBroadcast(MainActivity.this, 200, intent, PendingIntent.FLAG_IMMUTABLE);

            //알람 설정
            alarmManager.setAndAllowWhileIdle(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), pendingIntent);
        }
    };
}