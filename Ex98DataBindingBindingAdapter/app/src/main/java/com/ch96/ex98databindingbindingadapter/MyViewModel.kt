package com.ch96.ex98databindingbindingadapter

import android.database.Observable
import androidx.databinding.ObservableField
import com.bumptech.glide.Glide
import java.util.Date

class MyViewModel {

    //1) 이미지의 URL  [ 관찰가능한 타입 - ObservableXXX ]
    var img: ObservableField<String> = ObservableField("https://cdn.pixabay.com/photo/2016/06/14/00/14/cat-1455468_640.jpg")

    //1.1) 버튼 클릭시 이벤트 콜백에서 호출하는 메소드
    fun changeImage(){
        img.set("https://media.tenor.com/wOlC5m7NikkAAAAd/%EC%A0%9C%EB%A6%AC%EC%9D%B8%EC%82%AC-%EC%A1%B4%EC%A4%91.gif")
    }

    //2) 리사이클러뷰가 보여줄 대량의 데이터 컬렉션
    //2개의 Item을 가진 리스트 객체로 초기화
    val items:ObservableField<MutableList<Item>> = ObservableField(mutableListOf(Item("Sam", "Hello"), Item("Robin", "Nice")))

    //2.1) 버튼 클릭 이벤트 콜백에서 호출하는 메소드
    fun addItem(){
        //원래는 서버에서 새로운 데이터를 읽어오는 코드
        //테스트 목적으로 그냥 Item 추가
//        val list = items.get()
//        list?.add(0,Item("NEW", Date().toString()))
//        items.set(list) //같은 객체(리스트)를 다시 설정하면 화면갱신 안됨

        val list:MutableList<Item> = mutableListOf()
        list.add(Item("NEW", Date().toString()))
        list.addAll(items.get()!!) //새 것을 만들어서 기존에 있던것에 붙임 - 기존의 것이 아니기때문에 갱신됨
        items.set(list)
    }
}

//ObservableXXX의 단점
//1. 새로 set하는 객체가 변경되지 않으면 화면 갱신이 되지않음
//2. 액티비티나 프래그먼트의 라이프사이클을 고려하지않고 데이터 변경에 반응, 화면이 종료되는 상황에서도 화면갱신 시도
//이러한 단점을 개선하기위해 JetPack 라이브러리로 배포된 LiveData가 등장함 https://developer.android.com/jetpack?hl=ko