package com.ch96.tp06listview;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    //Inflater 불러서 OptionMenu 붙이기
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.option, menu);

        return super.onCreateOptionsMenu(menu);
    }

    //OptionMenu의 항목 선택했을때
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        if (item.getItemId() == R.id.menu_search) //search 눌렀을때
            Toast.makeText(this, "검색기능 만드는 곳", Toast.LENGTH_SHORT).show();
        else if (item.getItemId() == R.id.menu_add) //add 눌렀을때, 추가할때 .addd(0, ??) --> 배열 맨 앞으로 항목이 추가됨!
            Toast.makeText(this, "데이터 추가기능(dialog) 만드는 곳", Toast.LENGTH_SHORT).show();

        return super.onOptionsItemSelected(item);
    }
}