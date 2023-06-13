package com.ch96.ex22listview2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    //대량의 데이터를 저장할 리스트 객체
    ArrayList<String> datas = new ArrayList<>();
    //대량의 데이터(String)를 적절한 View(TextView)객체로 만들어주는 Adapter객체 참조변수
    ArrayAdapter adapter;
    ListView listView;
    EditText et;
    Button btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //대량의 데이터들 추가
        datas.add(new String("aaa"));
        datas.add(new String("bbb"));
        datas.add("ccc"); //자동 new String()

        //아답터 객체 생성
        adapter = new ArrayAdapter(this, R.layout.listview_item, datas);
        listView = findViewById(R.id.listView);
        listView.setAdapter(adapter);

        //리스트의 항목을 클릭했을때 반응
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Toast.makeText(MainActivity.this, datas.get(i), Toast.LENGTH_SHORT).show();
            }
        });

        //리스트의 항목을 길게 클릭했을때 반응 -- 해당 data 삭제
        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                //대량의 데이터 datas 리스트객체에서 현재 롱클릭한 아이템의 위치 요소를 제거
                datas.remove(i);
                adapter.notifyDataSetChanged();

                //리턴을 true로 해야 이벤트가 여기서 끝나게 되어 onClick()가 이어서 발동되지 않음.
                return true;
            }
        });


        //새로운 데이터를 추가하기
        et = findViewById(R.id.et);
        btn = findViewById(R.id.btn);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //EditText에 써있는 글씨를 얻어와서
                String data = et.getText().toString();
                et.setText("");

                //대량의 데이터인 datas(리스트객체)에 추가하기
                datas.add(data);

                //adapter에게 데이터의 갯수가 변경되었다는 것을 공지해주어야 화면이 갱신됨
                adapter.notifyDataSetChanged();

                //리스트뷰의 스크롤 위치 지정
                listView.setSelection(datas.size()-1);
                //listView.setSelection(listView.getBottom());
            }
        });

    }
}