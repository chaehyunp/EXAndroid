package com.ch96.ex27recyclerview;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class MyAdapter extends RecyclerView.Adapter {

    Context context;
    ArrayList<Item> items;

    public MyAdapter(Context context, ArrayList items) {
        this.context = context;
        this.items = items;
    }

    //재활용할 뷰가 없으면 뷰를 만들기위해 자동으로 호출되는 메소드
    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View itemView = inflater.inflate(R.layout.recyclerview_item, parent, false);
        VH holder = new VH(itemView);

        //여기에 setOnClickListener 가능 BUT 한 메소드에 하나의 기능이 있어야 good
        //기능단위로 작성해야 직관적이고, 이후 에러상황에도 대비하기 좋음 --> 또 cardView를 가지고 있는 VH에 작성!
//        itemView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {}
//        });

        return holder;
    }

    //현재 연결한 번째(position) 데이터를 뷰에 넣어주는 작업을 위해 호출되는 메소드
    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        //첫번째 파라미터 holder가 가진 view들 참조변수를 통해 값 설정
        VH vh = (VH)holder;

        //현재번째 아이템 요소를 얻어와서 뷰들에 설정
        Item item = items.get(position);
        vh.tvName.setText(item.name);
        vh.tvMsg.setText(item.message);

    }

    @Override
    public int getItemCount() {
        return items.size();
    }
    
    //아이템 1개 view 안에 있는 참조값을 저장하는 참조변수들을 멤버로 가지는 클래스
    class VH extends RecyclerView.ViewHolder {

        TextView tvName;
        TextView tvMsg;

        public VH(@NonNull View itemView) {
            super(itemView);

            tvName = itemView.findViewById(R.id.tv_name);
            tvMsg = itemView.findViewById(R.id.tv_msg);

            //항목뷰를 클릭했을때 반응하는 리스너 처리
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    //클릭한 아이템의 위치 인덱스 번호
                    int position = getLayoutPosition();
                    //클릭한 번째 아이템요소 데이터 얻어오기
                    Item item = items.get(position);

                    Toast.makeText(context, "CLICKED" + item.name, Toast.LENGTH_SHORT).show();
                }
            });
        }
    }
    
}
