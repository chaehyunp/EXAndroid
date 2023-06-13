package com.ch96.ex79databinding;

import android.view.View;

import androidx.databinding.ObservableBoolean;
import androidx.databinding.ObservableField;
import androidx.databinding.ObservableInt;

public class User {
    //일반 자료형은 값이 변경되어도 화면 갱신이 이루어지지 않음
    //dataBinding 기능에 의해 변수값이 바뀌면 화면이 자동갱신되는 특별한 자료형 : ObservableXXX 클래스 객체
    public ObservableField<String> name = new ObservableField<>(); //패키지명이 서로 다른곳에서 사용하기 위해서 public
    public ObservableInt age = new ObservableInt();
    public ObservableBoolean favor = new ObservableBoolean();

    public User(String name, int age, boolean favor) {
        //this.name = name 기본 자료형일 경우, Observable은 set
        this.name.set(name);
        this.age.set(age);
        this.favor.set(favor);
    }

    //버튼 클릭시 실행될 콜백 메소드 - 왜 여기에다가...? 바꾸려는 변수가 이 클래스에 있기때문
    //리스너의 콜백메소드에 있는 파라미터가 반드시 똑같이 있어야만 됨! (View view)
    public void changeName(View view) {
        this.name.set("Robin");
    }

    public void increaseAge(View view) {
        this.age.set(this.age.get() + 1);
    }
}
