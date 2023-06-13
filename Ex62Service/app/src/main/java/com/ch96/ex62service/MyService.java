package com.ch96.ex62service;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Build;
import android.os.IBinder;

import androidx.annotation.Nullable;
import androidx.core.app.NotificationChannelCompat;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

public class MyService extends Service { //Service는 안드로이드의 4대 주요 구성요소임, 반드시 Manifest에 등록해야함
    // 4대 컴포넌트기때문에 context가 상속되어 있음, 단순 별도 Thread 가 아닌 별도의 !!!MainThread!!!
    MediaPlayer mp;

    //startService()로 서비스가 시작되면 자동으로 실행되는 메소드
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        //Oreo 버전부터 "Foreground Service" 라는 개념 도입
        //사용자가 Background가 돌아가고 있는것을 느낄 수가 없음 (개발자의 Service 남용으로 백그라운드에서 앱이 계속 돌아가고 있는 경우 발생)
        //따라서 백그라운드의 진행상황을 사용자가 알 수 있도록 해야겠다 --> Status Bar
        //Foreground Service : 사용자에게 서비스가 동작중임을 인식하도록 반드시 알림(Notification)을 보이도록 강제하는 서비스 개념

        //startForegroundService()로 실행된 서비스 객체는 반드시 startForeground()라는 메소드를 호출해야함, 하지않으면 운영체제가 금방 죽여버림
        //알림 객체 만들고 Foreground Service로 실행하라고 요청
        NotificationManagerCompat manager = NotificationManagerCompat.from(this);
        NotificationCompat.Builder builder = null;

        if (Build.VERSION.SDK_INT >= 26){
            NotificationChannelCompat channel= new NotificationChannelCompat.Builder("ch1", NotificationManager.IMPORTANCE_HIGH).setName("Ex62 알림채널").build();
            manager.createNotificationChannel(channel);

            builder= new NotificationCompat.Builder(this, "ch1");
        }else{
            builder= new NotificationCompat.Builder(this, "");
        }

        builder.setSmallIcon(R.drawable.ic_noti_run);
        builder.setContentTitle("Ex62 Music Service");
        builder.setContentText("뮤직서비스가 실행중입니다.");

        // 음악 재생/정지 버튼을 가진 MainActivity를 알림창이 클릭되었을때 실행되도록
        Intent i= new Intent(this, MainActivity.class);
        PendingIntent pendingIntent= PendingIntent.getActivity(this, 11, i, PendingIntent.FLAG_IMMUTABLE);
        builder.setContentIntent(pendingIntent);

        //알림객체 생성
        Notification notification= builder.build();
        //포어그라운드로 실행하도록..
        startForeground(1, notification);


        if(mp == null) {
            mp = MediaPlayer.create(this, R.raw.kalimba); //new보다 create 권장
            mp.setVolume(0.7f, 0.7f);
            mp.setLooping(true);
        }
        //없으면 만들어서 시작하고, 있으면 이어서 시작
        mp.start();

        //메모리 문제로 인해 프로세스가 강제로 Service를 kill 시켜버리는 경우
        //데스크탑과 다르게 안드로이드는 전화기의 기능이 가장 주요함
        //마지막에 뭘했든 전화가 오면 전화가 최우선, 메모리가 없다고 전화가 천천히 실행됨..? 말이 안됨
        //휴대폰의 메모리가 부족하면 Activity보다 Service를 먼저 죽임 (백그라운드기때문에)
        //다시 메모리 문제가 해결되면 자동으로 다시 Service를 실행시키도록 설정
        return START_STICKY;
    }

    //stopService를 실행하여 서비스가 종료되면 자동으로 실행되는 메소드
    @Override
    public void onDestroy() {

        if(mp != null) {
            mp.stop(); //mp.pause();
            mp.release(); //미디어(이미지, 음악, 동영상 등)는 별도의 gpu RAM에서 관리, 단순히 null을 하는 것 이상으로 release 필요
            mp = null;
        }

        super.onDestroy();
    }

    //bindService로 서비스가 실행되면 자동으로 실행되는 메소드
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}
