package com.ch96.mvc.controller

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.ch96.mvc.R
import com.ch96.mvc.databinding.ActivityMainBinding
import com.ch96.mvc.model.ItemModel

class MainActivity : AppCompatActivity() {
    
    //1. MVC [ Model - View - Controller ] - 각 파일의 역할을 구분하여 작성
    //1) Model - 데이터를 저장하는 클래스나 데이터를 DB/네트워트/파일 등에서 불러오거나 저장하는 등의 작업을 하는 코드를 작성하는 파일들 [ ex. Item클래서, Person클래스, Retrofit 작업 클래스, DB 작업 클래스 ] 
    //2) View - 사용자가 볼 화면을 구현하는 목적의 코드가 있는 파일 [ activity_main.xml, MainActivity.kt, fragment_my.xml ]
    //3) Controller - 뷰와 모델 사이에서 연결하는 역할, 클릭 같은 이벤트를 처리하여 뷰의 요청에 따라 model 데이터를 제어하여 뷰에게 보여지는 역할 [ Activity.kt, Fragment.kt (이 둘은 View의 역할이기도 함) ]

    //app 모듈에서 만든 Flat Design의 MainActivity.kt에 작성한 코드들을 크게 3가지 역할로 구분
    //#1 화면 구현 (View 역할)
    //#2 클릭이벤트처리 (Controller 역할)
    //#3 SharedPreferences를 이용하여 데이터를 저장하고 불러오는 Business Logic을 가진 기능 (Model 역할)
    
    //역할별 파일에 대한 구분을 쉽게 하기 위해 java폴더 안에 파일의 역할별로 package를 나누기도 함

    //# view 참조변수
    lateinit var  binding:ActivityMainBinding

    //# model 참조변수
    lateinit var model:ItemModel
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //# view 역할
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //# model 객체 생성
        model = ItemModel(this)

        //#controller 역할 - 클릭 이벤트 처리 [ view와 model 연결 ]
        binding.btnSave.setOnClickListener {
            model.saveData(binding.etName.text.toString(), binding.etEmail.text.toString())
        }
        binding.btnLoad.setOnClickListener {
            val item = model.loadData()
            binding.tv.text = "${item.name} : ${item.email}"
        }

    }
}

//## MVC의 장점 ##
//1. 데이터를 제어하는 코드가 Activity/Fragment에 있지 않아서 간결해짐
//2. 역할별로 코드가 분리되어 있어서 가독성이 좋고 기능 코드 위치를 식별하여 찾기 수월하여 유지보수 용이함
//3. model 역할을 하는 클래스 안에 어떤 View 참조하고 있지 않아서 view를 변경해도 model의 코드는 전혀 변경되지 않기에 다른 view에서도 재사용 가능

//## MVC의 단점 ##
//1. Android에서는 View와 Controller 역할의 완전 분리가 어려움, Activity는 view이면서 controller 역할을 함
//2. view에서 model 객체를 참고하고 있어서 model이 변경되면 view도 코드 변경이 필요함
//3. 규모가 커지면 controller 역할을 하는 Activity의 코드는 여전히 비대해짐