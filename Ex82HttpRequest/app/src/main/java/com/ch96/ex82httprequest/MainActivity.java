package com.ch96.ex82httprequest;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.ch96.ex82httprequest.databinding.ActivityMainBinding;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.Buffer;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_main);
        binding=ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot()); //Root - LinearLayout

        binding.btnGet.setOnClickListener(v -> clickGet());
        binding.btnPost.setOnClickListener(v -> clickPost());
        binding.btnLoad.setOnClickListener(v -> clickLoad());
    }

    void clickLoad() {
        Intent intent = new Intent(this, BoardActivity.class);
        startActivity(intent);
    }

    void clickGet() {
        //네트워크작업 Thread
        new Thread(){
            @Override
            public void run() {
                //서버로 보낼 데이터들
                String name = binding.etName.getText().toString();
                String message = binding.etMsg.getText().toString();

                //GET방식으로 보낼 서버의 주소
                String serverAddress = "http://testhue96.dothome.co.kr/Android/getTest.php";
                //GET방식은 보낼 데이터(name, message)를 URL 주소 뒤에 붙여서 보내는 방식
                //단, URL에는 한글 및 특수문자 사용 불가 - 한글을 URL에 사용될 수 있도록 암호화(encoding) 필요
                try {
                    name = URLEncoder.encode(name, "utf-8");
                    message = URLEncoder.encode(message, "utf-8");
                } catch (UnsupportedEncodingException e) {
                    throw new RuntimeException(e);
                }
                String getAddress = serverAddress + "?name=" + name + "&msg=" + message;

                //서버와 연결작업
                try {
                    URL url = new URL(getAddress);
                    //url.openStream(); URL은 InputStream만 열 수 있음
                    //OutputStream까지 하는 Http 통신용 객체를 이용해야 함
                    HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                    connection.setRequestMethod("GET"); //대문자
                    connection.setDoInput(true);
                    //connection.setDoOutput(true); get method는 이미 data가 url에 붙어있기때문에 쓸 필요 없음
                    connection.setUseCaches(false); //캐시메모리 사용하지 않기

                    //GET방식은 이미 URL에 데이터가 추가되어 있어서 별도로 OutputStream이 필요하지 않음
                    //서버로부터 응답(response)된 결과를 받기
                    InputStream is = connection.getInputStream(); //DoInput 했기때문에
                    InputStreamReader isr = new InputStreamReader(is);
                    BufferedReader reader = new BufferedReader(isr);

                    StringBuffer buffer = new StringBuffer();
                    while (true) {
                        String line = reader.readLine();
                        if (line == null) break;

                        buffer.append(line + "\n");
                    }

                    runOnUiThread(() -> {
                        binding.tv.setText(buffer.toString());
                    });

                    reader.close();

                } catch (MalformedURLException e) {
                    throw new RuntimeException(e);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }.start();
    }

    void clickPost() {
        //네트워크 작업 thread
        new Thread(){
            @Override
            public void run() {
                //서버로 보낼 데이터
                String name = binding.etName.getText().toString();
                String message = binding.etMsg.getText().toString();

                //POST 방식으로 데이터를 보낼 서버 주소
                String serverAddress = "http://testhue96.dothome.co.kr/Android/postTest.php";
                //서버와 통신 연결
                try {
                    URL url = new URL(serverAddress);
                    //Http 통신용 객체 만들기
                    HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                    connection.setRequestMethod("POST");
                    connection.setDoInput(true);
                    connection.setDoOutput(true);
                    connection.setUseCaches(false);

                    //보낼 데이터들 POST방식으로 쓰기위해 [key=value]규칙에 맞게 하나의 문자열로 결합
                    String data = "name=" + name + "&msg=" + message; //url 뒤에 붙일 것이 아니기 때문에 인코딩 필요없음

                    //데이터를 OutputStream을 이용하여 직접 내보내기
                    OutputStream os = connection.getOutputStream();
                    OutputStreamWriter writer = new OutputStreamWriter(os);
                    writer.write(data, 0, data.length()); //네트워크로 보낼때 data를 잘라서 보냄 1024정도로 잘라서 반복문으로 보내는 경우가 많음
                    writer.flush();
                    writer.close();

                    //서버(postTest.php)에서 echo한 응답 문자열 읽어오기
                    InputStream is = connection.getInputStream();
                    InputStreamReader isr = new InputStreamReader(is);
                    BufferedReader reader = new BufferedReader(isr);

                    StringBuffer buffer = new StringBuffer();
                    while (true) {
                        String line = reader.readLine();
                        if (line == null) break;

                        buffer.append(line+"\n");
                    }
                    runOnUiThread(() -> {
                        binding.tv.setText(buffer.toString());
                    });

                } catch (MalformedURLException e) {
                    throw new RuntimeException(e);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }

            }
        }.start();
    }
}