package com.ch96.ex63servicebind;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Binder;
import android.os.IBinder;
import android.widget.Toast;

import androidx.annotation.Nullable;

public class MyMusicService extends Service {

    //서비스 객체가 생성되면 자동으로 발동하는 콜백메소드
    @Override
    public void onCreate() {
        super.onCreate();
        Toast.makeText(this, "onCreate", Toast.LENGTH_SHORT).show();
    }

    //startService로 실행하면 자동으로 발동하는 콜백메소드
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Toast.makeText(this, "onStartCommand", Toast.LENGTH_SHORT).show();
        return START_STICKY;
    }

    //bindService()를 실행하면 자동으로 발동하는 콜백메소드
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        //Binder 객체 생성하여 터널로 넘어가기 [서비스 객체의 주소를 리턴해주는 기능을 가진 객체]
        return new MyBinder();

    }

    //터널을 통해서 MainActivity로 넘어갈 Binder 객체 class
    class MyBinder extends Binder {
        //MyMusicService class 객체의 주소값을 리턴해주는 메소드
        public MyMusicService getServiceObject(){
            return MyMusicService.this; //this는 본인객체의 주소값을 의미, MyMusicService의 this를 리턴하기때문에 리턴타입도 MyMusicService!
        }
    }

    //음악재생 객체 및 기능 메소드
    MediaPlayer mp;

    public void playMusic() {
        if (mp == null) {
            mp = MediaPlayer.create(this, R.raw.kalimba);
            mp.setVolume(0.7f, 0.7f);
            mp.setLooping(true);
        }
        if (!mp.isPlaying()) mp.start();
    }

    public void pauseMusic() {
        if(mp != null && mp.isPlaying()) mp.pause();
    }

    public void stopMusic() {
        if(mp != null) {
            mp.stop();
            mp.release();
            mp =null;
        }
    }

}
