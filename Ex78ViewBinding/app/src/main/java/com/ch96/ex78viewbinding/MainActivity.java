package com.ch96.ex78viewbinding;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Toast;

import com.ch96.ex78viewbinding.databinding.ActivityMainBinding;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    //ViewBinding은 라이브러리가 아니고 안드로이드 아키텍쳐 구성요소임
    //그냥 기능만 ON 하면 됨

    //activity_main.xml 과 연결되어 뷰들의 참조변수를 멤버로 가지고 있는 Binding 클래스
    ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //Binding 객체가 만든 뷰를 액티비티가 보여줘야 하기에 setContentView로 만든 뷰는 지워야함
        //setContentView(R.layout.activity_main);

        //Binding 객체 생성 - activity_main.xml을 객체로 생성하여 액티비티에 뷰로 설정
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        //MainActivity의 setContentView(R.layout.activity_main); 기능 대신함
        setContentView(binding.getRoot());

        //1) TextView 글씨변경 - 이미 binding 객체가 TextView를 참조하고 있음
        binding.tv.setText("Nice to meet you");

        //2) Button click event
        binding.btn.setOnClickListener(v -> {
            binding.tv.setText("Have a good day");
        });

        //2.1) Button long~ click event -- 리턴이 있는 람다식 이용
        binding.btn.setOnLongClickListener(view -> {
            Toast.makeText(this, "long~click", Toast.LENGTH_SHORT).show();
            return true;
        });

        //3) 사용자 입력받아 TextView에 보이기
        binding.btn2.setOnClickListener(v -> {
            binding.tvResult.setText(binding.et.getText().toString());
            binding.et.setText("");
        });

        //4) 화면의 일부분을 별도로 설계하여 관리하는 Fragment에서 ViewBinding 사용해보기

        //5) 앱개발에서 가장 많이 사용되는 View 중 하나, RecyclerView에서 ViewBinding 사용해보기

    }

    MyAdapter adapter;

    //대량의 데이터들
    ArrayList<ItemVO> items = new ArrayList<>();

    @Override
    protected void onResume() {
        super.onResume();

        //임의의 대량의 데이터 추가
        items.add(new ItemVO("HOOT", R.drawable.zzang_eottae));
        items.add(new ItemVO("액션가면 코스튬", R.drawable.zzang_action));
        items.add(new ItemVO("빼꼼", R.drawable.zzang_bbaeggom));
        items.add(new ItemVO("귀여운 나에게 사주세요", R.drawable.zzang_buyitforcuteme));
        items.add(new ItemVO("컴퓨터로 머리바꾸기", R.drawable.zzang_comstyle));
        items.add(new ItemVO("버섯 코스튬", R.drawable.zzang_cutemushroom));

        //리사이클러에 아답터 설정
        adapter = new MyAdapter(this, items);
        binding.recycler.setAdapter(adapter);

    }
}