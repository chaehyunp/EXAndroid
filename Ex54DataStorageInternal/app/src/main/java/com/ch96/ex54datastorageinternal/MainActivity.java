package com.ch96.ex54datastorageinternal;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

public class MainActivity extends AppCompatActivity {

    EditText et;
    TextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        et = findViewById(R.id.et);
        tv = findViewById(R.id.tv);

        findViewById(R.id.btn_save).setOnClickListener(view -> clickSave());
        findViewById(R.id.btn_load).setOnClickListener(view -> clickLoad());

    }

    void clickSave() {
        //저장할 데이터
        String data = et.getText().toString();
        et.setText(""); //쓰여있던 글씨 빈글씨로 바꾸기

        //내부저장소(Internal Storage)의 앱전용공간에 Data.txt 라는 파일에 EditText에 입력된 문자열 데이터(String data)를 저장
        //파일쪽으로 데이터를 내보내는(저장) 스트림 열기
        //액티비티 클래스 안에 이미 내부 저장소의 파일을 여는 기능 메소드 존재
        try {
            FileOutputStream fos = openFileOutput("Data.txt", MODE_APPEND); //APPEND 이어붙이기
            //FileOutputStream은 데이터 단위로 저장해서 Byte[]로 줘야함 --> 문자스트림으로 바꾸기 (FileWriter는 띄어쓰기 등이 안됨) --> 바로 보조스트림으로
            PrintWriter writer = new PrintWriter(fos);
            
            writer.println(data); //줄바꿈까지
            writer.flush(); //물내리기
            writer.close(); //보조스트림을 닫으면 주스트림도 닫힘

            tv.setText("SAVED");

        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    void clickLoad() {
        //내부저장소의 Data.txt 라는 파일에서 데이터 읽어오기
        try {
            FileInputStream fis = openFileInput("Data.txt");
            //FileInputStream은 Byte단위로 읽어옴 바이트스트림 --> 문자스트림
            InputStreamReader isr = new InputStreamReader(fis); //한글자씩 읽어들임
            //문자스트림 --> 보조문자스트림 [한 줄 단위로 문자열 읽기 가능], Output과는 다르게 한 번에 보조스트림으로 올 수 없음
            BufferedReader reader = new BufferedReader(isr);
            StringBuffer buffer = new StringBuffer();
            while (true) {
                String line = reader.readLine(); //readLine 줄바꿈 문자를 빼고 읽어옴 --> append 할때 줄바꿈 문자를 추가
                if (line == null) break; //읽어올 것이 없으면 멈춰라

                buffer.append(line+"\n"); //읽어온 것이 있으면 한 줄을 쌓아라
            }

            tv.setText(buffer);

        } catch (FileNotFoundException e) {
            Toast.makeText(this, "파일을 찾을 수 없습니다.", Toast.LENGTH_SHORT).show();
            //OutputStream은 파일명에 해당하는 것이 없으면 만들지만 InputStream은 없는 파일을 읽을 수가 없음
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}