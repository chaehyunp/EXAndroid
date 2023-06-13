package com.ch96.mvvm.viewmodel

import android.content.Context
import android.database.Observable
import android.view.View
import androidx.databinding.ObservableField
import com.ch96.mvvm.model.Item
import com.ch96.mvvm.model.ItemModel

class MyViewModel(context: Context) {

    //view와 연결할 model 역할 클래스 참조변수
    var itemModel = ItemModel(context)
    
    //값 변경이 관찰되는 데이터 멤버변수 ObservableXXX
    var model = ObservableField<Item>()
    
    //주생성자가 사용하는 초기화 영역
    init {
        model.set(Item("이름 없음","이메일 없음"))
    }

    //EditText의 글씨를 가지고 있을 일반 변수
    private var name = ""
    private var email = ""

    //EditText의 글씨가 변경될때마다 반응하도록 하는 메소드
    fun changeName(s:CharSequence, start:Int, end:Int, count:Int) {
        this.name = s.toString()
    }

    fun changeEmail(s:CharSequence, start:Int, end:Int, count:Int) {
        this.email = s.toString()
    }

    //view의 이벤트에 반응하여 model을 제어하도록 요청하는 기능 메소드
    fun clickSave(view: View) {
        itemModel.saveData(name, email)
    }

    fun clickLoad(view: View) {
        val item = itemModel.loadData()
        model.set(item)
    }
}