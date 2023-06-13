package com.ch96.ex21listview;

import androidx.appcompat.app.AppCompatActivity;

import android.content.res.Resources;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView = findViewById(R.id.listView);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                //세 번째 파라미터 i : 클릭한 아이템의 위치 인덱스 번호(0,1,2...)
                //Toast.makeText(MainActivity.this, i + "", Toast.LENGTH_SHORT).show();

                //arrays.xml에 "datas"라는 이름으로 작성된 String배열 참조하기
                //파일이 res폴더 안에 있으므로 창고관리자 Resources 소환 먼저!
                Resources res = getResources();
                String[] datas = res.getStringArray(R.array.datas);
                Toast.makeText(MainActivity.this, datas[i], Toast.LENGTH_SHORT).show();
            }
        });
    }
}