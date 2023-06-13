package com.ch96.ex15sondpool;

import androidx.appcompat.app.AppCompatActivity;

import android.media.SoundPool;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    Button btn01, btn02, btn03, btn04;

    SoundPool sp;
    int sdStart, sdAgain, sdGoodJob; //음원 id 저장용 변수
    int sdMusic;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //SoundPool 객체를 만들어서 음원들을 등록
        //사운드풀을 만들어주는 건축가(Builder) 객체를 활용
        SoundPool.Builder builder = new SoundPool.Builder();
        builder.setMaxStreams(10); //한 번에 플레이 가능한 음원의 수(등록갯수X) - 갯수가 넘어가면 우선순위가 낮은 음원은 안들림
        sp = builder.build();

        //음원등록 --> 음원만의 고유 ID 발급받음
        sdStart = sp.load(this, R.raw.s_sijak, 0); //priority : 우선순위 - 등록할때는 모두 0 권장, 플레이할때 우선순위 등록
        sdAgain = sp.load(this, R.raw.s_again, 0);
        sdGoodJob = sp.load(this, R.raw.s_goodjob, 0);

        sdMusic = sp.load(this, R.raw.kalimba, 0);

        btn01 = findViewById(R.id.btn01);
        btn02 = findViewById(R.id.btn02);
        btn03 = findViewById(R.id.btn03);
        btn04 = findViewById(R.id.btn04);

        btn01.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sp.play(sdStart, 0.7f, 0.7f, 3, 0, 1.0f);
            }
        });

        btn02.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sp.play(sdAgain, 0.7f, 0.7f, 2, 0, 1.0f);
            }
        });

        btn03.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sp.play(sdAgain, 0.7f, 0.7f, 1, 0, 1.0f);
            }
        });

        btn04.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sp.play(sdMusic, 0.7f, 0.7f, 5, 0, 1.0f);
            }
        });
    }
}