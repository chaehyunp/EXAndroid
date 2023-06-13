package com.ch96.ex53xmlpullparsermovie;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.VH> {

    Context context;
    ArrayList<MovieItem> movieItems;

    public MyAdapter(Context context, ArrayList<MovieItem> movieItems) {
        this.context = context;
        this.movieItems = movieItems;
    }

    @NonNull
    @Override
    public VH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(context).inflate(R.layout.recycler_item, parent, false);
        return new VH(itemView); //VH로 만들어서 리턴!
    }

    @Override
    public void onBindViewHolder(@NonNull VH holder, int position) {
        //현재 번째 데이터인 MovieItem 얻어오기
         MovieItem item = movieItems.get(position);
         holder.tvRank.setText(item.rank);
         holder.tvTitle.setText(item.movieNm);
         holder.tvOpenDt.setText(item.openDt);
         holder.tvAudiAcc.setText(item.audiAcc);

    }

    @Override
    public int getItemCount() {
        return movieItems.size();
    }


    class VH extends RecyclerView.ViewHolder {
        TextView tvRank, tvTitle, tvOpenDt, tvAudiAcc;


        public VH(@NonNull View itemView) {
            super(itemView);

            tvRank = itemView.findViewById(R.id.tv_rank);
            tvTitle = itemView.findViewById(R.id.tv_title);
            tvOpenDt = itemView.findViewById(R.id.tv_open_dt);
            tvAudiAcc = itemView.findViewById(R.id.tv_audi_acc);
        }
    }
}
