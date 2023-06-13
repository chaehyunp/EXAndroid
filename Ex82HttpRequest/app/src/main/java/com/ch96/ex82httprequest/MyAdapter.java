package com.ch96.ex82httprequest;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.ch96.ex82httprequest.databinding.RecyclerItemBinding;

import java.util.ArrayList;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.VH> {

    Context context;
    ArrayList<Item> items;

    public MyAdapter(Context context, ArrayList<Item> items) {
        this.context = context;
        this.items = items;
    }

    @NonNull
    @Override
    public VH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(context).inflate(R.layout.recycler_item, parent, false);
        return new VH(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull VH holder, int position) {
        Item item = items.get(position);
        holder.binding.tvNo.setText(item.no + ""); //no값은 int 형이기때문에 문자열 더해주기
        holder.binding.tvName.setText(item.name);
        holder.binding.tvMsg.setText(item.msg);
        holder.binding.tvDate.setText(item.date);
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    class VH extends RecyclerView.ViewHolder {

        RecyclerItemBinding binding;
        public VH(@NonNull View itemView) {
            super(itemView);
            binding = RecyclerItemBinding.bind(itemView); //RelativeLayout이랑 bind
        }
    }
}
