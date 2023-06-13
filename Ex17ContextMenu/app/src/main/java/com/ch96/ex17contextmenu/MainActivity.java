package com.ch96.ex17contextmenu;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    Button btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn = findViewById(R.id.btn);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MainActivity.this, "TOUCHED BUTTON", Toast.LENGTH_SHORT).show();
            }
        });

        //액티비티에게 btn객체를 ContextMenu로 등록
        registerForContextMenu(btn);
    }

    //ContextMenu로 등록된 View를 길게 터치하면 ContextMenu를 만드는 메소드 자동 발동
    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        //menu폴더 안 context.xml 파일을 읽어서 메뉴아이템 객체로 생성(infalte 부풀리다)
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.context, menu);

        super.onCreateContextMenu(menu, v, menuInfo);
    }
    //ContextMenu의 항목(MenuItem)을 선택했을때 자동으로 발동하는 콜백메소드
    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.menu_save) Toast.makeText(this, "SAVED", Toast.LENGTH_SHORT).show();
        else if (item.getItemId() == R.id.menu_delete) Toast.makeText(this, "DELETED", Toast.LENGTH_SHORT).show();

        return super.onContextItemSelected(item);
    }
}