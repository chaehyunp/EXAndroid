package com.ch96.ex27recyclerview;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    //대량의 데이터
    ArrayList<Item> items = new ArrayList<>();
    RecyclerView recyclerView;
    MyAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //대량의 데이터 추가
        items.add(new Item("Sam", "Hello I am Sam!"));
        items.add(new Item("Robin", "Good day;)"));
        items.add(new Item("Cindy", "zzz"));
        items.add(new Item("Park", "BTS BONGJUNO SONY JAYPARK LETSGO"));
        items.add(new Item("Lee", "KICK"));
        items.add(new Item("Kim", "freak"));

        items.add(new Item("Sam", "Hello I am Sam!"));
        items.add(new Item("Robin", "Good day;)"));
        items.add(new Item("Cindy", "zzz"));
        items.add(new Item("Park", "BTS BONGJUNO SONY JAYPARK LETSGO"));
        items.add(new Item("Lee", "KICK"));
        items.add(new Item("Kim", "freak"));

        items.add(new Item("Sam", "Hello I am Sam!"));
        items.add(new Item("Robin", "Good day;)"));
        items.add(new Item("Cindy", "zzz"));
        items.add(new Item("Park", "BTS BONGJUNO SONY JAYPARK LETSGO"));
        items.add(new Item("Lee", "KICK"));
        items.add(new Item("Kim", "freak"));

        recyclerView = findViewById(R.id.recyclerview);
        adapter = new MyAdapter(this, items);
        recyclerView.setAdapter(adapter);

        //리사이클러뷰의 아이템뷰 1개를 클릭했을때 반응하는 리스너가 없음
        //그래서 처리하려면 아이템뷰 1개를 만드는 MyAdater에서 onClick 처리해야함

    }
}