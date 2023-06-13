package com.ch96.ex82httprequest;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import com.ch96.ex82httprequest.databinding.ActivityBoardBinding;
import com.ch96.ex82httprequest.databinding.ActivityMainBinding;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

public class BoardActivity extends AppCompatActivity {

    ActivityBoardBinding binding;

    ArrayList<Item> items = new ArrayList<>();
    MyAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_board);
        binding = ActivityBoardBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        adapter = new MyAdapter(this, items);
        binding.recycler.setAdapter(adapter);
    }

    //onCreate에 만들면 처음 만들때만 생기기때문에 추가적인 데이터가 갱신되지 않음
    @Override
    protected void onResume() {
        super.onResume();
        loadData(); //서버에서 데이터를 읽어오도록 요청
    }

    void loadData() {
        //테스트 데이터를 추가해보기
//        items.add(new Item(1, "Test", "Test Message", "2023-00-00 00:00:00"));
//        items.add(new Item(2, "Test", "Test Message", "2023-00-00 00:00:00"));
//        adapter.notifyDataSetChanged();

        //서버 DB에 저장된 데이터들을 읽어오기
        //네트워크 작업 스레드
        new Thread(){
            @Override
            public void run() {
                //서버 DB값을 echo해주는 php문서 실행
                String serverAddress = "http://testhue96.dothome.co.kr/Android/loadDB.php";

                try {
                    URL url = new URL(serverAddress);
                    HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                    connection.setRequestMethod("GET");
                    connection.setDoInput(true);
                    connection.setUseCaches(false);

                    InputStream is = connection.getInputStream();
                    InputStreamReader isr = new InputStreamReader(is);
                    BufferedReader reader = new BufferedReader(isr);

                    StringBuffer buffer = new StringBuffer();
                    while(true) {
                        String line = reader.readLine();
                        if (line==null) break;
                        buffer.append(line+"\n");
                    }

                    //잘 읽어왔는지 확인용
//                    runOnUiThread(() -> {
//                        new AlertDialog.Builder(BoardActivity.this).setMessage(buffer.toString()).create().show();
//                    });

                    //서버에서 echo된 문자열 데이터에서 "&" 문자를 기준으로 문자열 분리
                    //한줄 단위(Item)로 데이터를 분리
                    String[] rows = buffer.toString().split("&");
                    Log.i("TAG", rows.length + "");

                    //한줄 데이터의 콤마구분자를 분리하여 값들 분석하기[csv parsing]
                    for(String row:rows){
                        String[] datas = row.split(",");
                        if (datas.length != 4) continue;

                        int no = Integer.parseInt(datas[0]);
                        String name = datas[1];
                        String msg = datas[2];
                        String date = datas[3];

                        items.add(new Item(no, name, msg, date));
                    }

                    runOnUiThread(() -> {
                        adapter.notifyDataSetChanged();
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