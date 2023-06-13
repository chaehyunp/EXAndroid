package com.ch96.ex94architecturepattern

import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.ch96.ex94architecturepattern.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    lateinit var binding:ActivityMainBinding
    
    //Archtecture pattern 없이 그냥 작성하는 Flat Design 방식
    //1) 장점 : 구조가 간단하여 구현하기 쉽고 하나의 문서에 대부분의 기능코드와 UI 코드가 있어서 전체 기능이 한 눈에 들어옴
    //2) 단점 : Activity, Fragment에 모든 기능코드가 있어서 규모가 커질경우 파일 안에 너무 많은 코드가 작성됨 - 유지, 보수가 어려움
    //         똑같은 데이터를 제어하는 코드를 다른 화면에서 사용하게 되더라도 같은 코드를 또 작성해야함 - 재사용이 어려움

    //그래서 등장한 Architecture Pattern
    //작성하는 코드의 역할에 따라 구분하여 작성하는 방법을 규격화한 패턴
    //대표적인 패턴 : MVC, MVP, MVVM
    //차례대로 실습.. 새로운 모듈을 만들어서 실습 및 비교

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //#화면 작업
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //#이벤트처리 작업
        binding.btnSave.setOnClickListener{ clickSave() }
        binding.btnLoad.setOnClickListener { clickLoad() }

    }

    //#Data 제어 (저장/읽기/삭제/변경 : CRUD) 작업을 하는 비지니스 로직 코드 기능 메소드 2개 [ex. 네트워크통신, DB 작업 등]
    private fun clickSave(){
        //xml 파일로 HDD에 저장
        val pref:SharedPreferences = getSharedPreferences("data", MODE_PRIVATE) //외부에서 내 문서의 위치를 알아도 사용하지 못하도록
        pref.edit().apply{

            var name = binding.etName.text.toString()
            var email = binding.etEmail.text.toString()

            putString("name", name)
            putString("email", email)

            commit()
        }

    }

    private fun clickLoad(){
        val pref = getSharedPreferences("data", MODE_PRIVATE)

        var name = pref.getString("name", "")
        var email = pref.getString("email", "")

        binding.tv.text = "$name : $email"
    }
}