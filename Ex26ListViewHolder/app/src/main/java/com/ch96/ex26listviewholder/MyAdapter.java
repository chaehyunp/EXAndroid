package com.ch96.ex26listviewholder;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class MyAdapter extends BaseAdapter {

    Context context;
    ArrayList<String> items;

    //생성자
    public MyAdapter(Context context, ArrayList<String> items) {
        this.context = context;
        this.items = items;
    }

    @Override
    public int getCount() {
        return items.size();
    }

    @Override
    public Object getItem(int i) {
        return items.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        //1. create view
        //재활용할 view가 있는지
        if (view == null) {
            //listview_item.xml 문서를 읽어와서 view 객체로 생성해주는 Inflater 객체를 소환
            LayoutInflater inflater = LayoutInflater.from(context);
            //null을 넣으면 weight가 match인데도 wrap으로 만들고, 붙일때 match면 다시 바꿈 (이중작업)
            //따라서 부모의 사이즈를 주고, attachRoot == false 로 붙이지는 않도록
            view = inflater.inflate(R.layout.listview_item, viewGroup, false);

            //만들어진 view 안에 있는 자식 뷰들의 참조값을 tag로 저장
            //자식view들의 참조변수를 멤버로 가지는 별도의 클래스를 설계하여 객체로 생성
            ViewHolder holder = new ViewHolder(view);
            view.setTag(holder);

        }

        //2. bind view
        //현재 보여줄 데이터를 얻어오기
        String item = items.get(i);;

        //아이템뷰 안에 tag로 저장되어있던 ViewHolder객체를 빼오기
        ViewHolder holder = (ViewHolder) view.getTag();
        holder.tv.setText(item);

        return view;
    }

    //항목 1개의 view(item view) 안에 있는 자식뷰들 참조변수를 멤버로 가지는 이너클래스
    class ViewHolder{
        TextView tv;

        //생성자
        public ViewHolder(View itemView) {
            tv = itemView.findViewById(R.id.tv);
        }
    }

}
