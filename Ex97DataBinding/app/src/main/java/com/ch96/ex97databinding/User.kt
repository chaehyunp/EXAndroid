package com.ch96.ex97databinding

import android.database.Observable
import android.view.View
import android.widget.CompoundButton
import android.widget.Toast
import androidx.databinding.ObservableBoolean
import androidx.databinding.ObservableField
import androidx.databinding.ObservableInt

class User {

    //값 변경이 관찰되는 멤버변수 ObservableXXX - primitive type && List or Map && Reference type --> ObservableField<>
    var name: ObservableField<String> = ObservableField()
    var age: ObservableInt= ObservableInt(0)
    var favor: ObservableBoolean= ObservableBoolean()

    constructor(name:String, age:Int, favor:Boolean= true){ //default 값 - 전달을 못받으면 기본값
        this.name.set(name)
        this.age.set(age)
        this.favor.set(favor)
    }
    
    //change name 버튼을 클릭하는것에 대한 callback method - 클릭 콜백 메소드가 되려면 반드시 파라미터가 있어야 함
    fun changeName(view:View) {
        name.set("Robin")
    }

    //age 변수값을 1증가 시키는 콜백 메소드
    fun increaseAge(view:View) {
        age.set(age.get()+1)
    }

    //좋아요 true/false 값 변경 기능 메소드 - 콜백용 메소드가 아닌 일반 메소드
    //이 메소드를 xml 버튼의 onClick 속성으로 지정한 익명 콜백함수에서 대신 호출
    fun toggleFavor() { //파라미터가 없음!!!
        favor.set(!favor.get())
    }

    //체크 상태가 변경되는 것에 반응하는 콜백 메소드 - 파라미터 중요
    fun changeFavor(v:CompoundButton, isChecked:Boolean) {
        Toast.makeText(v.context, "$isChecked", Toast.LENGTH_SHORT).show()
        //체크 상태 값을 관리하는 favor 변수값 변경
        favor.set(isChecked)
    }
    
    //EditText의 글씨 변화값을 가지고 있을 관찰가능한 변수
    val message:ObservableField<String> = ObservableField("")
    
    //EditText의 글씨 변화 이벤트에 반응하는 콜백 메소드 - 파라미터 중요
    fun onTextChanged(s:CharSequence, start:Int, end:Int, count:Int){
        message.set(s.toString())
    }

    //EditText에 글씨를 입력하고 버튼을 클릭하여 TextView에 보여주기
    private var inputValue:String = ""
    val value: ObservableField<String> = ObservableField(inputValue)
    
    //EditText의 글씨 변경 이벤트 콜백 메소드에 의해 호출될 일반 메소드
    fun changeInputValue(s:CharSequence){
        inputValue = s.toString()
    }

    //작성완료버튼 클릭 콜백메소드에 의해 호출될 일반 메소드
    fun clickBtn(){
        value.set(inputValue)
    }
}