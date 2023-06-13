package com.ch96.ex41bottomnavigationview;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class Tab3Fragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_tab3, container, false);
    }

    ArrayList<Tab3RecyclerItem> items = new ArrayList<>();
    RecyclerView recyclerView;
    Tab3RecyclerAdapter adapter;

    ArrayList<Tab3RecyclerItem> items2 = new ArrayList<>();
    RecyclerView recyclerView2;
    Tab3RecyclerAdapter adapter2;

    ArrayList<Tab3RecyclerItem> items3 = new ArrayList<>();
    RecyclerView recyclerView3;
    Tab3RecyclerAdapter adapter3;

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        recyclerView = view.findViewById(R.id.recyclerview1);
        adapter = new Tab3RecyclerAdapter(getActivity(), items);
        recyclerView.setAdapter(adapter);

        recyclerView2 = view.findViewById(R.id.recyclerview2);
        adapter2 = new Tab3RecyclerAdapter(getActivity(), items2);
        recyclerView2.setAdapter(adapter2);

        recyclerView3 = view.findViewById(R.id.recyclerview3);
        adapter3 = new Tab3RecyclerAdapter(getActivity(), items3);
        recyclerView3.setAdapter(adapter3);
    }

    //프레그먼트가 화면을 만들기 전에 한 번 실행되는 메소드
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //대량의 데이터 추가
        items.add(new Tab3RecyclerItem("NALDARAMGEE", R.drawable.zzang_nal));
        items.add(new Tab3RecyclerItem("NALDARAMGEE", R.drawable.zzang_nal));
        items.add(new Tab3RecyclerItem("NALDARAMGEE", R.drawable.zzang_nal));
        items.add(new Tab3RecyclerItem("NALDARAMGEE", R.drawable.zzang_nal));
        items.add(new Tab3RecyclerItem("NALDARAMGEE", R.drawable.zzang_nal));
        items.add(new Tab3RecyclerItem("NALDARAMGEE", R.drawable.zzang_nal));
        items.add(new Tab3RecyclerItem("NALDARAMGEE", R.drawable.zzang_nal));
        items.add(new Tab3RecyclerItem("NALDARAMGEE", R.drawable.zzang_nal));
        items.add(new Tab3RecyclerItem("NALDARAMGEE", R.drawable.zzang_nal));
        items.add(new Tab3RecyclerItem("NALDARAMGEE", R.drawable.zzang_nal));
        items.add(new Tab3RecyclerItem("NALDARAMGEE", R.drawable.zzang_nal));
        items.add(new Tab3RecyclerItem("NALDARAMGEE", R.drawable.zzang_nal));

        items2.add(new Tab3RecyclerItem("NALDARAMGEE", R.drawable.zzang_nal));
        items2.add(new Tab3RecyclerItem("NALDARAMGEE", R.drawable.zzang_nal));
        items2.add(new Tab3RecyclerItem("NALDARAMGEE", R.drawable.zzang_nal));
        items2.add(new Tab3RecyclerItem("NALDARAMGEE", R.drawable.zzang_nal));
        items2.add(new Tab3RecyclerItem("NALDARAMGEE", R.drawable.zzang_nal));
        items2.add(new Tab3RecyclerItem("NALDARAMGEE", R.drawable.zzang_nal));
        items2.add(new Tab3RecyclerItem("NALDARAMGEE", R.drawable.zzang_nal));
        items2.add(new Tab3RecyclerItem("NALDARAMGEE", R.drawable.zzang_nal));
        items2.add(new Tab3RecyclerItem("NALDARAMGEE", R.drawable.zzang_nal));
        items2.add(new Tab3RecyclerItem("NALDARAMGEE", R.drawable.zzang_nal));
        items2.add(new Tab3RecyclerItem("NALDARAMGEE", R.drawable.zzang_nal));
        items2.add(new Tab3RecyclerItem("NALDARAMGEE", R.drawable.zzang_nal));

        items3.add(new Tab3RecyclerItem("NALDARAMGEE", R.drawable.zzang_nal));
        items3.add(new Tab3RecyclerItem("NALDARAMGEE", R.drawable.zzang_nal));
        items3.add(new Tab3RecyclerItem("NALDARAMGEE", R.drawable.zzang_nal));
        items3.add(new Tab3RecyclerItem("NALDARAMGEE", R.drawable.zzang_nal));
        items3.add(new Tab3RecyclerItem("NALDARAMGEE", R.drawable.zzang_nal));
        items3.add(new Tab3RecyclerItem("NALDARAMGEE", R.drawable.zzang_nal));
        items3.add(new Tab3RecyclerItem("NALDARAMGEE", R.drawable.zzang_nal));
        items3.add(new Tab3RecyclerItem("NALDARAMGEE", R.drawable.zzang_nal));
        items3.add(new Tab3RecyclerItem("NALDARAMGEE", R.drawable.zzang_nal));
        items3.add(new Tab3RecyclerItem("NALDARAMGEE", R.drawable.zzang_nal));
        items3.add(new Tab3RecyclerItem("NALDARAMGEE", R.drawable.zzang_nal));
        items3.add(new Tab3RecyclerItem("NALDARAMGEE", R.drawable.zzang_nal));

    }
}
