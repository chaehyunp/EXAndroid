package com.ch96.ex91kotlinhello

import android.app.AlertDialog

fun main() {
    //scope function : apply, let, run, also

    //어떤 객체의 여러 개의 멤버를 사용해야 할 때
    val member = Member()
    member.name = "Sam"
    member.age = 20
    member.address = "Seoul"
    member.show()
    
    //멤버를 사용할때 객체명.XXX 라고 쓰는게 은근히 번거롭고 실수의 여지도 많음
    //이를 해결하기 위해 등장한 scope 함수
    val member2 = Member()
    member2.apply {
        //이 영역 안에서는 this 키워드가 member2객체
        this.name="Robin"
        //this는 생략가능
        age = 25
        address = "NewYork"
        show() //메소드 호출 가능
    }

    //위처럼 영역을 묶었기때문에 참조변수명을 잘못 기입하는 실수를 줄일 수 있음
    //개발자가 보기에 member2에 대한 설정을 하나의 영역에 묶어서 가독성이 개선됨
    
    //scope function에 분류 크게 두 가지
    //1) 영역 안에서 this 키워드로 본인을 참조하는 scope function : apply, run (apply - run 차이 : 리턴값이 다름)
    //2) 영역 안에서 it 키워드로 본인을 참조하는 scope : also, let -- 마치 람다식처럼 사용
    val member3 = Member()
    member3.also { 
        it.name = "Cindy"
        it.age = 30
        it.address = "Paris"
        it.show() //it 키워드는 생략 불가능
    }

    //it 키워드를 다른 키워드로 변경 가능
    member3.let { m->
        m.name = "Lee"
        m.age = 35
        m.address = "Tokyo"
        m.show()
    }

    //apply와 run의 차이는 리턴값이 다르다는 것
    //also와 let의 차이도 리턴값이 다름

    //실제 안드로이드에서 사용하는 모습 샘플
//    val builder:AlertDialog.Builder = AlertDialog.Builder(this)
//    builder.setTitle("Title")
//    builder.setMessage("Hello")
//    builder.setPositiveButton("OK", null)
//    builder.setPositiveButton("CANCEL", null)
//    builder.create()
//
//    val builder2:AlertDialog.Builder = AlertDialog.Builder(this)
//    //run function의 마지막 실행문의 결과가 리턴값
//    val dialog2 = builder2.run {
//        setTitle("Title")
//        setMessage("Hello")
//        setPositiveButton("OK", null)
//        setNegativeButton("CANCEL", null)
//        create()//본인이 리턴되면 apply
//    }
//    dialog2.show()
//
//    //apply function의 리턴값은 this임
//    val builder3 = builder2.run {
//        setTitle("Title")
//        setMessage("Hello")
//        setPositiveButton("OK", null)
//        setNegativeButton("CANCEL", null)
//        create()//본인이 리턴되면 apply
//    }
//    dialog2.show()
//
//    val dialog3 = builder2.run {
//        setTitle("Title")
//        setMessage("Hello")
//        setPositiveButton("OK", null)
//        setNegativeButton("CANCEL", null)
//    }.create() //create 밖에다 해서 dialog에 리턴할 수 있도록
//    dialog2.show()
    
}

class Member {
    var name:String? = null
    var age:Int? = null
    var address:String? = null

    fun show() {
        println("$name $age $address")
    }
}