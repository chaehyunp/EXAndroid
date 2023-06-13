package com.ch96.ex91kotlinhello

//주생성자의 constructor 키워드 생략
class PtjStudent(name:String, age:Int, major:String, var task:String):Student(name, age, major) {
    init {
        println("create PtjStusent instance")
    }

    override fun show() {
        //super.show()
        println("name : $name   age : $age   major : $major   task : $task")
    }

}