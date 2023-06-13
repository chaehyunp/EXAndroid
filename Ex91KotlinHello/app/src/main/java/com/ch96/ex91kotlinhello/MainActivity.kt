package com.ch96.ex91kotlinhello

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast

//Kotlin에서 클래스 상속 키워드 ":"
//상속하는 클래스명 옆에 주생성자를 호출하는 ()가 필수
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //대략적인 코틀린 코딩 방식 살펴보기 - 자바와의 차이를 기반으로 소개

        //변수는 var 키워드 사용
        var btn:Button = findViewById(R.id.btn)

        //버튼에게 클릭리스너 설정 - 자바의 람다식과 비슷 SAM 변환
        btn.setOnClickListener{
            clickBtn()
        }
    }


    //Kotlin에서의 메소드(함수)는 fun 키워드 사용
    fun clickBtn() {
        //변수를 선언할때 자료형 생략 가능, 대신 찾아오게 되면 제네릭에 명시해야함
        var tv = findViewById<TextView>(R.id.tv)
        //tv.setText("NICE!")
        //Kotlin은 setXXX(),getXXX() 메소드를 권장하지 않고, 멤버변수에 값 대입을 선호함
        tv.text = "NICE!"
    }

    //Override 메소드가 Java에서는 @Override 어노테이션을 사용했지만 코틀린에서는 메소드 앞에 Override 키워드 삽입
    //Override 메소드 앞에 명시적으로 override 키워드가 없으면 ERROR
    override fun onResume() {
        super.onResume()
        
        //코틀린에서는 소문자로 toast를 써야 자동완성됨
        Toast.makeText(this, "onResume", Toast.LENGTH_SHORT).show()
    }
}