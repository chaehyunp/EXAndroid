package com.ch96.tp02numberbaseball;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

import java.util.HashSet;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    HashSetSet set;
    int comA, comB, comC;
    EditText a, b, c;
    TextView viewer;

    int strike, ball;

   @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Random rnd = new Random();

        set.add(comA = rnd.nextInt(9)+1);
        set.add(comB = rnd.nextInt(9)+1);
        set.add(comC = rnd.nextInt(9)+1);

        a = findViewById(R.id.a);
        b = findViewById(R.id.b);
        c = findViewById(R.id.c);
        viewer = findViewById(R.id.viewer);

        String numA = a.getText().toString();
        String numB = b.getText().toString();
        String numC = c.getText().toString();

        int userA = Integer.parseInt(numA);
        int userB = Integer.parseInt(numB);
        int userC = Integer.parseInt(numC);

        while(true){
            if (comA == userA) strike += 1;
            if (comB == userB) strike += 1;
            if (comC == userC) strike += 1;

            if (comA == userB) ball += 1;
            if (comA == userC) ball += 1;

            if (comB == userA ) ball += 1;
            if (comB == userC) ball += 1;

            if (comC == userA) ball += 1;
            if (comC == userC) ball += 1;

            viewer.setText(userA + userB + userC + " : " + strike + " STRIKE" + ball + " ball");

            if (comA==userB && comB==userB && comC==userC) {
                viewer.setText("HOMERUN!");
                break;
            }
            else {
                strike = 0;
                ball = 0;
            }
        }

   }
}


