package com.ch96.ex19searchview;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    SearchView searchView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //searchView = findViewById(R...불가 SearchView는 MainActivity에 없음!
    }
    
    //onCreat() 메소드가 실행된 후 OptionMenu를 만드는 작업을 하는 콜백메소드 자동 발동
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.option, menu);

        //SearchView 찾아오기
        MenuItem menuItem = menu.findItem(R.id.menu_search);
        //searchView = menuItem. 불가 view일때만 find~ 가능
        //searchView = menuItem.getActionView(); view가 리턴값이므로  searchView로 받을 수 없음
        searchView = (SearchView) menuItem.getActionView(); //SearchView로 형변환
        
        //searchView에 적용하는 설정
        //Query 질의하다
        searchView.setQueryHint("이름을 입력하세요");
        
        //SearchView에 글씨 변화에 반응하는 리스너 설정
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) { //소프트키보드의 [돋보기버튼]을 터치했을때(검색어입력 완료) 실행되는 콜백메소드
                Toast.makeText(MainActivity.this, "검색어 : " + query, Toast.LENGTH_SHORT).show();
                return false;
            }
            @Override
            public boolean onQueryTextChange(String newText) { //글씨가 변경될때마다 실행되는 콜백메소드
                return false;
            }
        });

        return super.onCreateOptionsMenu(menu);
    }
}