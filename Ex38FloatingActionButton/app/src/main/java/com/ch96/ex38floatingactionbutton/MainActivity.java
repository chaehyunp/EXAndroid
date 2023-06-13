package com.ch96.ex38floatingactionbutton;

import androidx.appcompat.app.AppCompatActivity;
import androidx.coordinatorlayout.widget.CoordinatorLayout;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

public class MainActivity extends AppCompatActivity {

    FloatingActionButton fab;
    ExtendedFloatingActionButton extFab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Toast.makeText(MainActivity.this, "clicked", Toast.LENGTH_SHORT).show();
                Snackbar.make(view, "clicked", Snackbar.LENGTH_INDEFINITE).setAction("okay", new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {}
                }).show();
            }
        });

        extFab = findViewById(R.id.ext_fab);
        extFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (extFab.isExtended()){
                    CoordinatorLayout layout = findViewById(R.id.snackber_container);
                    Snackbar.make(layout, "clicked add", Snackbar.LENGTH_INDEFINITE).setAction("okay", new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Toast.makeText(MainActivity.this, "clicked add", Toast.LENGTH_SHORT).show();
                            extFab.shrink();
                        }
                    }).show();
                }else extFab.extend();
            }
        });

    }
}