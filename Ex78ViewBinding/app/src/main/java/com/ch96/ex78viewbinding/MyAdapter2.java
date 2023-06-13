package com.ch96.ex78viewbinding;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.ch96.ex78viewbinding.databinding.RecyclerItemBinding;

import java.util.ArrayList;

public class MyAdapter2 extends RecyclerView.Adapter<MyAdapter2.VH> {

    Context context;
    ArrayList<ItemVO> items;

    public MyAdapter2(Context context, ArrayList<ItemVO> items) {
        this.context = context;
        this.items = items;
    }

    @NonNull
    @Override
    public VH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new VH(RecyclerItemBinding.inflate(LayoutInflater.from(context), parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull VH holder, int position) {
        holder.binding.tv.setText(items.get(position).title);
        holder.binding.iv.setImageResource(items.get(position).imgResId);
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    class VH extends RecyclerView.ViewHolder {
        RecyclerItemBinding binding;
        public VH(RecyclerItemBinding binding) { //파라미터를 바인딩으로 받기
            super(binding.getRoot());
            this.binding = binding;
        }
    }

}
