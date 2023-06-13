package com.ch96.ex32fragmentpager;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;


public class MyAdapter extends FragmentStateAdapter {
    //프레그먼트 참조변수 3개짜리 배열객체
    Fragment[] fragments =new Fragment[3];


    //반드시 생성자로 FragmentManager를 받아야함
    public MyAdapter(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
        fragments[0] = new Tab1Fragment();
        fragments[1] = new Tab2Fragment();
        fragments[2] = new Tab3Fragment();
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) { //position 페이지가 보여줄 인덱스번호
        return fragments[position];
    }

    @Override
    public int getItemCount() {
        return fragments.length;
    }
}
