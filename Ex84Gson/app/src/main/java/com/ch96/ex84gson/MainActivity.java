package com.ch96.ex84gson;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.LayoutInflater;

import com.ch96.ex84gson.databinding.ActivityMainBinding;
import com.google.gson.Gson;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_main);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.btn.setOnClickListener(v -> clickbtn());
        binding.btnReverse.setOnClickListener(v -> clickbtnReverse());
        binding.btnArray.setOnClickListener(v -> clickbtnArray());
    }

    void clickbtnArray() {
        //jsonArray --> Person object Array
        String jsonStr="[{'name':'Sam','age':20},{'name':'Robin','age':25}]";

        Gson gson = new Gson();
        Person[] people = gson.fromJson(jsonStr,Person[].class);
        binding.tv.setText("객체 수 : " + people.length);
    }

    void clickbtnReverse() {
        //Person객체 --> json 문자열
        Person person = new Person("Robin", 25);

        Gson gson = new Gson();
        String jsonStr = gson.toJson(person);
        binding.tv.setText(jsonStr);
    }

    void clickbtn() {
        //GSON library를 이용하여 편하게 json문자열을 분석하여 객체로 생성
        //json문자열 임의로 만들기
        String jsonStr = "{'name':'Sam','age':20}";

        //GSON을 이용하여 name, age를 멤버로 가지는 Person 클래스 객체로 한방에 분석하여 변환
        Gson gson = new Gson();
        Person person = gson.fromJson(jsonStr, Person.class); //json 문자열과 설계도(class)
        binding.tv.setText(person.name + " (" + person.age + ")");

    }
}