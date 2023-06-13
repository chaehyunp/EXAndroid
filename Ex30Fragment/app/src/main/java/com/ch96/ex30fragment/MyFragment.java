package com.ch96.ex30fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class MyFragment extends Fragment {

    TextView tv;
    Button btn;
    Button btn2;

    //이 fragment가 화면에 보여줄 view를 만들어서 리턴해주면 액티비티가 보여줌
    //이를 위해 자동으로 호출되는 콜백메소드 (라이프사이클 메소드)

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
       //fragment_my.xml에 설계한 뷰를 객체로 생성하여 리턴
        View view = inflater.inflate(R.layout.fragment_my, container, false);

        //바로 find 불가(Activity가 아님), view = LinearLayot
        tv = view.findViewById(R.id.tv);
        btn = view.findViewById(R.id.btn);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tv.setText("MyFragment --> MyFragment");
            }
        });

        btn2 = view.findViewById(R.id.btn2);
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                btn2.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        //MainActivity의 TextView 제어
                        //Fragment.java 안에서 Activity 소환
                        MainActivity ma = (MainActivity) getActivity();
                        ma.tv.setText("MyFragment --> MainActivity");

                    }
                });
            }
        });

        return view;
    }

}
