package com.ch96.ex92kotlinrecyclerview

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide

class ItemDetailActivity : AppCompatActivity() {

    val iv:ImageView by lazy { findViewById(R.id.iv) }
    //참조변수의 자료형을 자동추론시키면 find할때 제네릭을 이용해야함
    //val tv by lazy { findViewById(R.id.tv) } //자동추론을 하면 본인의 자료형을 모르기때문에 찾을 수 없음
    val tv by lazy { findViewById<TextView>(R.id.tv) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_item_detail)

        //넘어온 Intent 객체가 가져온 Extra 데이터들 받기
        var name:String? = intent.getStringExtra("name") //Type mismatch. Required:String Found:String? - intent가 기본적으로 nullable을 가지고 댕김
        var msg:String = intent.getStringExtra("msg") as String //null값이 아닐 것을 확신
        var imgId:Int = intent.getIntExtra("imgId", R.drawable.zzang_sheep_no) //값이 없을때 보여줄 이미지

        //제목줄에 이름 표시
        supportActionBar!!.title = name //액션바가 없을 경우도 있기때문에 supportActionBar - nullable로 되어있음

        //메시지는 TextView에 표시
        tv.text = msg

        Glide.with(this).load(imgId).into(iv)

        //전환효과를 줄 뷰에게 별칭을 지정
        iv.transitionName = "iii"
    }
}