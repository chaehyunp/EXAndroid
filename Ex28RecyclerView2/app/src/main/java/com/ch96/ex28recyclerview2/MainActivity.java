package com.ch96.ex28recyclerview2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.GridView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    //대량의 데이터
    ArrayList<Item> items = new ArrayList<>();
    RecyclerView recyclerView;
    MyAdapter adapter;

    Button btnAdd, btnDelete;
    Button btnLinear, btnGrid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //대량의 데이터 추가[ 실무에서는 DB or 서버에서 데이터 읽어옴 ]
        items.add(new Item("루피", "선장", R.drawable.crew_luffy, R.drawable.bg_one01));
        items.add(new Item("조로", "부선장", R.drawable.crew_zoro, R.drawable.bg_one02));
        items.add(new Item("나미", "항해사", R.drawable.crew_nami, R.drawable.bg_one03));
        items.add(new Item("우솝", "저격수", R.drawable.crew_usopp, R.drawable.bg_one04));
        items.add(new Item("상디", "요리사", R.drawable.crew_sanji, R.drawable.bg_one05));
        items.add(new Item("쵸파", "의사", R.drawable.crew_chopper, R.drawable.bg_one06));
        items.add(new Item("니코로빈", "역사학자", R.drawable.crew_nicorobin, R.drawable.bg_one07));

        items.add(new Item("루피", "선장", R.drawable.crew_luffy, R.drawable.bg_one01));
        items.add(new Item("조로", "부선장", R.drawable.crew_zoro, R.drawable.bg_one02));
        items.add(new Item("나미", "항해사", R.drawable.crew_nami, R.drawable.bg_one03));
        items.add(new Item("우솝", "저격수", R.drawable.crew_usopp, R.drawable.bg_one04));
        items.add(new Item("상디", "요리사", R.drawable.crew_sanji, R.drawable.bg_one05));
        items.add(new Item("쵸파", "의사", R.drawable.crew_chopper, R.drawable.bg_one06));
        items.add(new Item("니코로빈", "역사학자", R.drawable.crew_nicorobin, R.drawable.bg_one07));

        items.add(new Item("루피", "선장", R.drawable.crew_luffy, R.drawable.bg_one01));
        items.add(new Item("조로", "부선장", R.drawable.crew_zoro, R.drawable.bg_one02));
        items.add(new Item("나미", "항해사", R.drawable.crew_nami, R.drawable.bg_one03));
        items.add(new Item("우솝", "저격수", R.drawable.crew_usopp, R.drawable.bg_one04));
        items.add(new Item("상디", "요리사", R.drawable.crew_sanji, R.drawable.bg_one05));
        items.add(new Item("쵸파", "의사", R.drawable.crew_chopper, R.drawable.bg_one06));
        items.add(new Item("니코로빈", "역사학자", R.drawable.crew_nicorobin, R.drawable.bg_one07));

        recyclerView = findViewById(R.id.recyclerview);
        adapter = new MyAdapter(this, items);
        recyclerView.setAdapter(adapter);

        //리사이클러뷰는 아이템 클릭 리스너 처리가 없음. 아답터에서 ItemView에 직접 click 이벤트 처리

        btnAdd = findViewById(R.id.btn_add);
        btnDelete = findViewById(R.id.btn_delete);
        btnLinear = findViewById(R.id.btn_linear);
        btnGrid = findViewById(R.id.btn_grid);

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //대량의 데이터 리스트 **맨 앞에(index 0)** 새로운 데이터 추가
                items.add(0, new Item("NEW", "신입", R.drawable.bg_one08, R.drawable.bg_one09));

                //데이터가 변경되었다고 아답터에게 공지!!!
                //adapter.notifyDataSetChanged(); --> 전체 뷰 다시 갱신, 데이터 낭비 (데이터 전체가 바뀌었을때 사용 eg. items.clear()...)

                //데이터 아이템 1개가 추가되었다고 공지
                adapter.notifyItemInserted(0);
                //스크롤 위치 조정
                recyclerView.scrollToPosition(0);
            }
        });

        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //아이템 리스트에서 첫번째 데이터 제거
                items.remove(0);
                adapter.notifyItemRemoved(0);
            }
        });

        btnLinear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //리사이클뷰의 레이아웃매니저(배치관리자)를 새로이 선정
                LinearLayoutManager layoutManager = new LinearLayoutManager(MainActivity.this, LinearLayoutManager.VERTICAL, false);
                recyclerView.setLayoutManager(layoutManager);
            }
        });

        btnGrid.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                GridLayoutManager layoutManager = new GridLayoutManager(MainActivity.this, 2);
                recyclerView.setLayoutManager(layoutManager);
            }
        });
    }
}