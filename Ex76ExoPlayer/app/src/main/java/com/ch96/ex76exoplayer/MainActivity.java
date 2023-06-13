package com.ch96.ex76exoplayer;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import com.google.android.exoplayer2.ExoPlayer;
import com.google.android.exoplayer2.MediaItem;
import com.google.android.exoplayer2.ui.PlayerView;
import com.google.android.exoplayer2.ui.StyledPlayerView;

public class MainActivity extends AppCompatActivity {

    //비디오 재생 라이브러리 : ExoPlayer - 유튜브의 재생기술
    PlayerView playerView;
    ExoPlayer exoPlayer;

    StyledPlayerView spv;
    ExoPlayer exoPlayer2;

    //동영상 주소
    Uri videoUri = Uri.parse("http://commondatastorage.googleapis.com/gtv-videos-bucket/sample/ElephantsDream.mp4");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        playerView = findViewById(R.id.player_view);
        exoPlayer = new ExoPlayer.Builder(this).build();
        playerView.setPlayer(exoPlayer);

        //영상을 CD로 굽듯이
        MediaItem mediaItem = MediaItem.fromUri(videoUri);
        exoPlayer.setMediaItem(mediaItem);
        exoPlayer.prepare();
        exoPlayer.play(); //자동으로 로딩(prepare) 완료까지 기다렸다가 재생(play)

        //플레이리스트처럼 여러 개의 영상 등록
//        Uri firstUri = Uri.parse("http://commondatastorage.googleapis.com/gtv-videos-bucket/sample/WhatCarCanYouGetForAGrand.mp4");
//        Uri secondUri = Uri.parse("http://commondatastorage.googleapis.com/gtv-videos-bucket/sample/WeAreGoingOnBullrun.mp4");
//
//        MediaItem item1 = MediaItem.fromUri(firstUri);
//        MediaItem item2 = MediaItem.fromUri(secondUri);
//        exoPlayer.addMediaItem(item1);
//        exoPlayer.addMediaItem(item2);

        exoPlayer.prepare();
        exoPlayer.play();
        exoPlayer.setRepeatMode(ExoPlayer.REPEAT_MODE_ALL);

        //컨트롤 박스 모양 별도 레이아웃으로 지정
        //layout폴더 안에 [exo_player_control_view.xml]로 레이아웃 만들면 바로 저장됨

        findViewById(R.id.btn).setOnClickListener(v -> clickBtn());

        //개선된 컨트롤바 모양을 가지 스타일드 플레이어 뷰
        spv = findViewById(R.id.spv);
        exoPlayer2 = new ExoPlayer.Builder(this).build();
        spv.setPlayer(exoPlayer2);

        Uri firstUri = Uri.parse("http://commondatastorage.googleapis.com/gtv-videos-bucket/sample/BigBuckBunny.mp4");
        MediaItem item = MediaItem.fromUri(firstUri);
        exoPlayer2.setMediaItem(item);
        exoPlayer2.prepare();
        //exoPlayer2.play();

    }

    void clickBtn() {
        //전체화면 모드로 변경 - 별도의 전체화면용 액티비티 실행
        Intent intent = new Intent(this, FullScreenActivity.class);
        //현재 재생중인 uri 데이터 전달하기
        intent.setData(videoUri);

        //현재까지 재생된 위치정보도 추가로 전달
        intent.putExtra("currPos", exoPlayer.getCurrentPosition());
        startActivity(intent); //전체화면이 종료될때 다시 재생하기 위해서는 startActivityForResult로

    }

        //화면이 안보이기 시작할때 동영상 일시정지
    @Override
    protected void onPause() {
        super.onPause();
        exoPlayer.pause();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        exoPlayer.stop();
        exoPlayer.release(); //GUI 영역 - 찌꺼기 제거 필요
        exoPlayer = null;
    }
}
