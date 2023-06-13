package com.ch96.ex91kotlinhello

//상속해주는 Person 클래스에 이미 name, age 프로퍼티가 존재 - 주생성자에서 또 name. age 프로퍼티 만들면 안됨 (변수 오버라이드 됨)
//매개변수로 name, age 받기
 open class Student constructor(name:String, age:Int, var major:String):Person(name, age) {
    init {
        println("crate Student instance")
    }

    //override 키워드가 있는 메소드는 기본 open method
    override fun show() {
        //super.show()
        println("name : $name   age : $age   major : $major")
    }
}