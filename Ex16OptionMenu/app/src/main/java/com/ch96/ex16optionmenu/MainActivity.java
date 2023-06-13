package com.ch96.ex16optionmenu;

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

    //OnCreate() 메소드가 실행된 후 자동으로 Option Menu를 만드는 작업을 하는 이 콜백메소드가 발동
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //menu객체에 MenuItem을 객체를 추가

        //1. java 언어로 추가 - 아이콘이나 id같은 식별자를 지정할때 번거로움 (메뉴가 많아질 수록 더욱더)
//        menu.add("계정");
//        menu.add("설정");
        
        //2. XML언어로 menu를 설계하고 객체로 만들어서 사용

        //menu폴더 안에 option.xml 문서를 읽어와서 Menu객체로 만들어주는(부풀려주는 inflate) 객체
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.option, menu);

        return super.onCreateOptionsMenu(menu);
    }

    //Option Menu의 메뉴항목(MenuItem)이 선택되었을때 자동으로 발동하는 콜백메소드
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        if (item.getItemId() == R.id.menu_search) {
            Toast.makeText(this, "SEARCH", Toast.LENGTH_SHORT).show();
        } else if (item.getItemId() == R.id.menu_add) {
            Toast.makeText(this, "ADD", Toast.LENGTH_SHORT).show();
        } else if (item.getItemId() == R.id.menu_help) {
            Toast.makeText(this, "HELP", Toast.LENGTH_SHORT).show();
        }
        return super.onOptionsItemSelected(item);
    }
}