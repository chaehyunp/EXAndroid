package com.ch96.ex75videoview;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.widget.MediaController;
import android.widget.VideoView;

public class MainActivity extends AppCompatActivity {

    VideoView vv;

    //비디오 파일은 용량이 크기에 앱의 res 폴더에 직접 가지고 있지 않음
    //보통은 웹서버(인터넷 경로)에 동영상을 업로드하고 이를 불러와서 재생
    String videoUrl = "http://commondatastorage.googleapis.com/gtv-videos-bucket/sample/BigBuckBunny.mp4";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        vv = findViewById(R.id.vv);

        //비디오뷰에 재생버튼,일시정지 등을 할 수 있는 '컨트롤바'를 붙이기
        vv.setMediaController(new MediaController(this));

        vv.setVideoURI(Uri.parse(videoUrl));
        //vv.start(); //VideoView(브라운관) 안에 MediaPlayer(셋업플레이어, 실질적으로 플레이)가 thread를 상속
        //동영상은 용량이 크기때문에 로딩하는 시간이 걸림 --> 바로 start가 불가하고 로딩이 완료됐을때 재생해야함
        vv.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mediaPlayer) {
                vv.start();
            }
        });

        findViewById(R.id.btn).setOnClickListener(v -> {
            startActivity(new Intent(this, SecondActivity.class));
        });

    }

    //액티비티가 안보이기 시작할때 자동으로 발동하는 콜백 메소드
    @Override
    protected void onPause() {
        super.onPause();

        //비디오를 일시정지
        if (vv != null && vv.isPlaying()) vv.pause();
    }

    @Override
    protected void onResume() {
        super.onResume();

        if(vv != null && !vv.isPlaying()) vv.start();
    }
}