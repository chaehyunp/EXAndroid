package com.ch96.tp04compoundbuttonex;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    EditText et_name, et_num01, et_num02, et_num03;
    RadioGroup rg_gender, rg_city;
    CheckBox cb_email, cb_phone, cb_visit, cb_sms;
    Button btn;
    TextView tv;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        et_name = findViewById(R.id.et_name);
        et_num01 = findViewById(R.id.et_num01);
        et_num02 = findViewById(R.id.et_num02);
        et_num03 = findViewById(R.id.et_num03);

        rg_gender = findViewById(R.id.rg_gender);
        rg_city = findViewById(R.id.rg_city);

        cb_email = findViewById(R.id.cb_email);
        cb_phone = findViewById(R.id.cb_phone);
        cb_visit = findViewById(R.id.cb_visit);
        cb_sms = findViewById(R.id.cb_sms);

        btn = findViewById(R.id.btn);
        tv = findViewById(R.id.tv);



    }
}