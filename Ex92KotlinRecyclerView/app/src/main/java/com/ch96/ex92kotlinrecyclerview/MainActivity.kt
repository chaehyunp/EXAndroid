package com.ch96.ex92kotlinrecyclerview

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {

    //뷰 참조 변수는 보통 참조값이 변경되지 않음
    //참조변수는 var보다는 val을 주로 사용
    val recycler:RecyclerView by lazy { findViewById(R.id.recycler) }

    //대량의 데이터 추가
    var items:MutableList<Item> = mutableListOf() //MutableList를 만들어주는 메소드

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //대량의 데이터 임의로 추가 [테스트 목적]
        items.add(Item("Sam","Hello Kotlin", R.drawable.zzang_bbaeggom))
        items.add(Item("Robin","Nice Day", R.drawable.zzang_devilmom))
        items.add(Item("Tom","Nice to meet you", R.drawable.zzang_sheep_no))
        items.add(Item("Park","Hello Kotlin", R.drawable.zzang_bbaeggom))
        items.add(Item("Rosie","Nice Day", R.drawable.zzang_devilmom))
        items.add(Item("Choi","Nice to meet you", R.drawable.zzang_sheep_no))
        items.add(Item("Dominic","Hello Kotlin", R.drawable.zzang_bbaeggom))
        items.add(Item("Coco","Nice Day", R.drawable.zzang_devilmom))
        items.add(Item("Jeny","Nice to meet you", R.drawable.zzang_sheep_no))

        //리사이클러뷰에 아답터 설정
        //참조변수를 굳이 만들 필요없음
        recycler.adapter = MyAdapter(this, items)

        //리사이클러뷰에 레이아웃 매니저 설정 (원래는 xml, 연습상 여기서)
        recycler.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
    }

    override fun onResume() {
        super.onResume()

        //보통 이곳에서 데이터들을 갱신하는 작업들이 이루어짐
        recycler.adapter?.notifyDataSetChanged() //닷연산자에 에러가 난 경우는 보통 값이 없을 때 - null safe 연산자 이용
    }

    //옵션메뉴를 만드는 작업을 하는 콜백메소드
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        //MenuInflator를 get하는 작업 필요없이 액티비티에 이미 menuInflator 객체가 멤버로 존재함
        menuInflater.inflate(R.menu.option, menu)

        return super.onCreateOptionsMenu(menu)
    }

    //옵션 메뉴 아이템을 선택하면 자동으로 발동하는 콜백 메소드
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        
        when(item.itemId) { //해야할게 한줄이면 {} 생략 가능
            R.id.menu_aa -> Toast.makeText(this, "aa", Toast.LENGTH_SHORT).show()
            R.id.menu_bb -> Toast.makeText(this, "bb", Toast.LENGTH_SHORT).show()
        }
        
        return super.onOptionsItemSelected(item)
    }
}