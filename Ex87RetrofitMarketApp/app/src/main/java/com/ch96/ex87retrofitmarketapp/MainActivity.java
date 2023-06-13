package com.ch96.ex87retrofitmarketapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.ch96.ex87retrofitmarketapp.databinding.ActivityMainBinding;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //etContentView(R.layout.activity_main);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        getSupportActionBar().setTitle("레트로핏 마켓");

        binding.fabEdit.setOnClickListener(v -> {
            startActivity(new Intent(this, EditActivity.class));
        });

        binding.refreshLayout.setOnRefreshListener(() -> {
            loadData();
            binding.refreshLayout.setRefreshing(false); //계속 로딩되는 상태를 제거
        });
    }

    //onCreate에 만들게 되면 data가 바뀌었을때 새로고침이 되지않음
    @Override
    protected void onResume() {
        super.onResume();
        loadData();
    }

    void loadData() {
        Retrofit retrofit = RetrofitHelper.getRetrofitInstance();
        RetrofitService retrofitService =retrofit.create(RetrofitService.class);
        Call<ArrayList<MarketItem>> call = retrofitService.loadDataFromServer();
        call.enqueue(new Callback<ArrayList<MarketItem>>() {
            @Override
            public void onResponse(Call<ArrayList<MarketItem>> call, Response<ArrayList<MarketItem>> response) {
                ArrayList<MarketItem> items = response.body();

                MarketAdapter marketAdapter = new MarketAdapter(MainActivity.this, items); //아답터 만들고 대량의 데이터주기
                binding.recycler.setAdapter(marketAdapter); //늘 기존의 아답터를 부시고 새로운 아답터를 만드는 것 (간편, 효율성은 글쎄요)
            }

            @Override
            public void onFailure(Call<ArrayList<MarketItem>> call, Throwable t) {
                Toast.makeText(MainActivity.this, "ERROR : " + t.getMessage(), Toast.LENGTH_SHORT).show();
                Log.i("TESTTAG", t.getMessage());
            }
        });
    }
}