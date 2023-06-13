package com.ch96.ex87retrofitmarketapp;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.ch96.ex87retrofitmarketapp.databinding.RecyclerItemBinding;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class MarketAdapter extends RecyclerView.Adapter<MarketAdapter.VH> {

    Context context;
    ArrayList<MarketItem> items;

    public MarketAdapter(Context context, ArrayList<MarketItem> items) {
        this.context = context;
        this.items = items;
    }

    @NonNull
    @Override
    public VH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(context).inflate(R.layout.recycler_item, parent, false);
        return new VH(itemView); //아래서 bind함
    }

    @Override
    public void onBindViewHolder(@NonNull VH holder, int position) {
        MarketItem item = items.get(position);
        holder.binding.tvTitle.setText(item.title);
        holder.binding.tvMsg.setText(item.msg);
        holder.binding.tvPrice.setText(item.price + "원");

        String address = "";
        if(item.image!=null) address="http://testhue96.dothome.co.kr/RetrofitMarket/" + item.image;
        Glide.with(context).load(address).error(R.drawable.zzang_sheep_no).into(holder.binding.iv);
        //address에 값이 없어도 Glide를 쓰면 에러나지않음
        //error - load를 읽어봐 근데 안되면 에러이미지
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    class VH extends RecyclerView.ViewHolder{ //ViewHolder는 반드시 생성자 필요
        RecyclerItemBinding binding;
        public VH(@NonNull View itemView) {
            super(itemView);
            binding=RecyclerItemBinding.bind(itemView); //itemView - RecyclerView, 여기서 만들지는 않고 bind만

            itemView.setOnLongClickListener(v -> {
                // 현재 클릭한 아이템 얻어오기
                MarketItem item = items.get(getLayoutPosition());
                //Toast.makeText(context, item.no, Toast.LENGTH_SHORT).show(); //롱클릭한 아이템의 no값

                //Retofit을 이용하여 DB에서 해당 아이템을 삭제 요청
                Retrofit retrofit = RetrofitHelper.getRetrofitInstance(); //이렇게 쓰면 효율적으로는 좋지않지만 행위에 초점
                RetrofitService retrofitService = retrofit.create(RetrofitService.class);
                Call<String> call = retrofitService.deleteItem(item.no);
                call.enqueue(new Callback<String>() {
                    @Override
                    public void onResponse(Call<String> call, Response<String> response) {
                        String result = response.body();
                        Toast.makeText(context, result, Toast.LENGTH_SHORT).show();

                        items.remove(item);
                        notifyDataSetChanged(); //데이터셋이 변화되었다고 notify
                    }

                    @Override
                    public void onFailure(Call<String> call, Throwable t) {
                        Toast.makeText(context, "ERROR : " + t.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });

                return true; //longClick이 끝나고 추가로 click작업이 되지 않게 하려면 true
            }); //Bind에서 달아도 됨
        }
    }
}
