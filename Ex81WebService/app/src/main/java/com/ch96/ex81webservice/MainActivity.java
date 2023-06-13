package com.ch96.ex81webservice;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.bumptech.glide.Glide;
import com.ch96.ex81webservice.databinding.ActivityMainBinding;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

public class MainActivity extends AppCompatActivity {

    //activity_main.xml 문서와 연결되어 뷰들을 제어할 수 있도록 설계된 Binding class 참조변수
    ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_main);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.btn.setOnClickListener(v -> clickBtn());
        binding.btn2.setOnClickListener(v -> clickBtn2());
    }

    void clickBtn () {
        //웹서버에 접속하여 index.html 문서를 읽어와서 TextView에 보여주기
        //네트워크 작업은 별도의 Thread가 해야만 함
        new Thread() {
            @Override
            public void run() {
                String address = "http://testhue96.dothome.co.kr/index.html";

                //무지개로드(Stream)를 열어주는 해임달(URL)
                try {
                    URL url = new URL(address);
                    InputStream is = url.openStream();
                    InputStreamReader isr = new InputStreamReader(is); //바이트스트림 --> 문자스트림
                    BufferedReader reader = new BufferedReader(isr); //보조문자스트림(한글자 --> 한줄 단위)

                    StringBuffer buffer = new StringBuffer();
                    while (true) {
                        String line = reader.readLine();
                        if (line == null) break;
                        buffer.append(line + "\n");
                    }

                    //별도 thread는 ui변경 불가능
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            binding.tv.setText(buffer.toString());
                        }
                    });

                } catch (MalformedURLException e) {
                    throw new RuntimeException(e);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }.start();
    }

    void clickBtn2() {
        //웹서버의 이미지를 읽어오기 - 스레드와 스트림이용 해야함
        //이미지 로드 라이브러리 활용 : Glide or Picasso
        String address = "http://testhue96.dothome.co.kr/zzang_gameat.jpg";
        Glide.with(this).load(address).into(binding.iv);

    }
}