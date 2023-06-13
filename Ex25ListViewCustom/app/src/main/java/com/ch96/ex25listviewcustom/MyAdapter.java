package com.ch96.ex25listviewcustom;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class MyAdapter extends BaseAdapter {

    Context context;
    ArrayList<Item> items;
    
    //생성자
    //MainActivity에서 MyAdapter 객체를 생성할때 Data가 있는 ArrayList를 주고 값을 받음
    public MyAdapter(Context context, ArrayList<Item> items) { //Adapter는 운영체제를 건드릴 수 있는 능력이 없으므로 MainActivity로부터 Context 값 받아오기
        this.context = context;
        this.items = items;
    }

    @Override
    public int getCount() { //Adapter가 만들 View 총 갯수
        return items.size();
    }

    @Override
    public Object getItem(int i) { //Data 하나
        return items.get(i);
    }

    @Override
    public long getItemId(int i) { //인덱스 번호 대신 id를 쓸 경우(굳이...) BUT 포지션과 id를 동일하게 사용
        return i;
    }

    //제일 중요한 곳
    //리스트뷰가 보여줄 아이템 1개의 View객체를 생성하여 리턴해주는 메소드
    @Override
    public View getView(int i, View view, ViewGroup viewGroup) { //ListView가 Adapter에게 View를 .getView로 받아서 화면에 보여줌

        //1. create view : xml 모양으로 View객체 생성

        //계속해서 View가 new되면 메모리적으로 비효율적
        //똑똑한 AdapterView는 무조건적으로 view를 만드는 것이 아니라 화면에 보이는 만큼만 view만듦
        //재활용할 view가 없는가?? - 이 메소드의 두번째 파라미터 view
        //view가 null이면 view를 만들어서 리턴, view가 null이 아니면 view 재활용!
        if(view == null) {
            //xml 파일을 읽어서 View 객체로 만들어주는 객체를 운영체제로부터 소환 : LayoutInflater
            //context를 부를 수 없음, Adapter는 Activity의 기능이 없기때문에!
            //.getSystemService --> 스머프 꺼내오는 기능, 너무 길어서 요새는 .from() 사용
            LayoutInflater inflater = LayoutInflater.from(context);
            view = inflater.inflate(R.layout.listview_item, null); //내가 ListView에 붙이면 알아서 붙여주는 AdapterView와 중복됨 따라서,null
            //여기 view = RelativeLayout
        }

        //2. bind view : 생성된 View 객체 안에 정보 값들을 설정(연결)
        //이 메소드의 첫 번째 파라미터 i : 현재 만들어야할 몇 번째 view인지(인덱스 번호)
        //현재 번째의 데이터 (Item 객체) 얻어오기
        Item item = items.get(i);

        //아이템뷰 안에 있는 자식뷰들을 참조하기
        //view = RelativeLayout --> view한테 찾아달라고 해야함
        ImageView iv = view.findViewById(R.id.iv);
        TextView tvName = view.findViewById(R.id.tv_name);
        TextView tvNation = view.findViewById(R.id.tv_nation);

        //각 뷰들에게 현재 번째 데이터를 연결(설정)
        tvName.setText(item.name);
        tvNation.setText(item.nation);
        iv.setImageResource(item.imgId);

        return view; //리스트뷰가 이 리턴된 뷰를 화면 목록에 추가해줌
    }
}
