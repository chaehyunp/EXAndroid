package com.ch96.ex92kotlinrecyclerview

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.View.OnClickListener
import android.widget.Button

class IntroActivity : AppCompatActivity() {

    //코틀린은 멤버변수(프로퍼티)를 초기화하지 않으면 에러
    var btn:Button? = null //nullable variable

    //늦은 초기화 (위 방법보다 이렇게 만드는 것이 good)
    lateinit var btnExit:Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_intro)

        //Button 객체참조
        btn = findViewById(R.id.btn)
        //nullable 변수는 null일 수도 있기때문에 안전하게 멤버에 접근하는 연산자를 사용해야 함
        //null safe 연산자 .? null이 아니면 해라 if(btn != null)
        //자바에서 인터페이스를 만들기 위해서 중괄호를 열고 추상메소드를 썼었음
        //object 이용해서 추상메소드
        btn?.setOnClickListener(object:OnClickListener{
            override fun onClick(p0: View?) { //자바에서 이너클래스에서 바깥부를때 IntroActivity.this --> 코틀린에서는 라벨 이용
                val intent: Intent = Intent(this@IntroActivity,MainActivity::class.java) //아직은 인텐트가 자바용 클래스만 인식하기때문에 호환성을 위해 .java가 붙음
                startActivity(intent)
            }
        })

        //btnExit 참조하기
        btnExit = findViewById(R.id.btn_exit)
        //리스너에 대한 설정을 익명클래스 말고 간결하게 람다함수로
        //btnExit.setOnClickListener({v-> finish()})
        //파라미터가 1개라면 생략가능 [자동으로 it 키워드로 파라미터명 지정됨] (줄바꿔보면 보임)
        //btnExit.setOnClickListener({finish()})
        //람다함수를 더욱 축약하여 SAM(Single Abstract Method)변환
        btnExit.setOnClickListener{finish()} //리스너 뒤에 메소드임은 자명하기때문에 소괄호 생략


    }
}