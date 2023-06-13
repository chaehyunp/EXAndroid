package com.ch96.ex78viewbinding;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ch96.ex78viewbinding.databinding.FragmentMyBinding;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link MyFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MyFragment extends Fragment {

    //fragment_my.xml 파일과 연결되어 있는 바인딩 클래스 참조변수
    FragmentMyBinding binding;


    //이 프레그먼트가 보여줄 뷰를 리턴해주는 기능 메소드 재정의(override)

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        binding = FragmentMyBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        //onViewCreated의 파라미터 view와 중복되므로 다른 이름 사용
        binding.btn.setOnClickListener(v -> binding.tv.setText("Good day"));
    }
}

