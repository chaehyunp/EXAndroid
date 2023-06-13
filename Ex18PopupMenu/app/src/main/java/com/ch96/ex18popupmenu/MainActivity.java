package com.ch96.ex18popupmenu;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    Button btn;
    TextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn = findViewById(R.id.btn);
        tv = findViewById(R.id.tv);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //PopupMenu 객체 생성
                //두번째 파라미터 : 메뉴가 보여질 뷰 (anchor 닻을 내릴 곳!)
                //PopupMenu popupMenu = new PopupMenu(MainActivity.this, btn);
                PopupMenu popupMenu = new PopupMenu(MainActivity.this, tv);
                //팝업메뉴가 보여줄 메뉴 설계 [menu 폴더 안에 popup.xml 파일]
                MenuInflater inflater = getMenuInflater();
                //menu가 popup 안에 있음
                inflater.inflate(R.menu.popup, popupMenu.getMenu());

                popupMenu.show();

                //팝업메뉴항목이 클릭되었을때 반응
                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem menuItem) {

                        if (menuItem.getItemId() == R.id.menu_info) Toast.makeText(MainActivity.this, "information", Toast.LENGTH_SHORT).show();
                        else if (menuItem.getItemId() == R.id.menu_delete) Toast.makeText(MainActivity.this, "delete", Toast.LENGTH_SHORT).show();
                        else if (menuItem.getItemId() == R.id.menu_modify) Toast.makeText(MainActivity.this, "modify", Toast.LENGTH_SHORT).show();

                        return false;
                    }
                });
            }
        });
    }
}