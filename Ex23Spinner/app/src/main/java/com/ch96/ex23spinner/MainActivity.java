package com.ch96.ex23spinner;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    Spinner spinner;
    ArrayAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        spinner = findViewById(R.id.spinner);
        adapter = ArrayAdapter.createFromResource(this, R.array.city, R.layout.spinner_item);

        //드롭다운되는 아이템들의 뷰모양을 다르게 주고싶다면
        adapter.setDropDownViewResource(R.layout.spiner_dropdown_item);

        spinner.setAdapter(adapter);

        //spinner의 드롭다운되는 위치를 조정
        //offset 원래 위치에서 빗겨난 값을 말함, px값
        spinner.setDropDownVerticalOffset(160);

        //spinner의 아이템이 선택되었을때
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String[] city = getResources().getStringArray(R.array.city);
                Toast.makeText(MainActivity.this, city[i], Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }
}