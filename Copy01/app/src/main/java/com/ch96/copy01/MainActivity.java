package com.ch96.copy01;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

public class MainActivity extends AppCompatActivity {

    Fragment[] fragments = new Fragment[5];
    BottomNavigationView bnv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fragments[0] = new Tab1Fragment();
        fragments[1] = new Tab2Fragment();
        fragments[2] = new Tab3Fragment();
        fragments[3] = new Tab4Fragment();
        fragments[4] = new Tab5Fragment();

        //시작할때 보여줄 fragment
        getSupportFragmentManager().beginTransaction().add(R.id.container_fragment, fragments[0]).commit();

        //bnv 버튼을 눌렀을 경우 fragment
        bnv = findViewById(R.id.bnv);
        bnv.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                if(item.getItemId() == R.id.bvn_tab1) getSupportFragmentManager().beginTransaction().replace(R.id.container_fragment, fragments[0]).commit();
                else if (item.getItemId() == R.id.bvn_tab2) getSupportFragmentManager().beginTransaction().replace(R.id.container_fragment, fragments[1]).commit();
                else if (item.getItemId() == R.id.bvn_tab3) getSupportFragmentManager().beginTransaction().replace(R.id.container_fragment, fragments[2]).commit();
                else if (item.getItemId() == R.id.bvn_tab4) getSupportFragmentManager().beginTransaction().replace(R.id.container_fragment, fragments[3]).commit();
                else if (item.getItemId() == R.id.bvn_tab5) getSupportFragmentManager().beginTransaction().replace(R.id.container_fragment, fragments[4]).commit();

                return true;
            }
        });

    }
}