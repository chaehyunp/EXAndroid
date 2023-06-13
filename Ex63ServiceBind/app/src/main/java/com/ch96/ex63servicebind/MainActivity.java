package com.ch96.ex63servicebind;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    MyMusicService musicService = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.btn_play).setOnClickListener(v -> clickPlay());
        findViewById(R.id.btn_pause).setOnClickListener(v -> clickPause());
        findViewById(R.id.btn_stop).setOnClickListener(v -> clickStop());

    }

    //액티비티가 화면에 보여질때 자동으로 발동하는 콜백 메소드
    @Override
    protected void onResume() {
        super.onResume();

        if(musicService == null) {
            //MusicService를 실행하고 연결하기
            Intent intent = new Intent(this, MyMusicService.class);
            startService(intent); //Service 객체가 없으면 create 하고  onStartCommand 호출, 있으면 onStartCommand()만 함

            //Service 객체와 연결(bind)0
            bindService(intent, connection, 0); //0은 만들지 않겠다, BIND_AUTO_CREATE로 하면 startService없이 자동으로 시작 가능
            //별도의 start 없이 BIND_AUTO_CREATE로 bind만 쓰면 MainAc이 죽으면 Service도 죽음, start로 만들면 독립적으로 실행되기때문에 살아있음
        }
    }

    //MyMusicService와 연결하는 터널 객체
    ServiceConnection connection = new ServiceConnection() { //인터페이스
        @Override
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) { //터널이 연결됐을때
            Toast.makeText(MainActivity.this, "binded...", Toast.LENGTH_SHORT).show();

            //두번째 파라미터 : iBinder - 터널을 통해 넘어온 객체
            MyMusicService.MyBinder binder = (MyMusicService.MyBinder) iBinder;
            musicService = binder.getServiceObject();
        }

        @Override
        public void onServiceDisconnected(ComponentName componentName) { //터널이 끊어졌을때

        }
    };

    //버튼 클릭시 메소드
    void clickPlay() {
        if (musicService != null) musicService.playMusic();
    }

    void clickPause() {
        if(musicService != null) musicService.pauseMusic();
    }

    void clickStop() {
        if (musicService != null) {
            musicService.stopMusic();
            unbindService(connection); //서비스와 연결이 종료
            musicService=null;
        }
        //완전하게 서비스를 종료하기 위해
        Intent intent = new Intent(this, MyMusicService.class);
        stopService(intent);

        //액티비티도 종료
        finish();
    }
}