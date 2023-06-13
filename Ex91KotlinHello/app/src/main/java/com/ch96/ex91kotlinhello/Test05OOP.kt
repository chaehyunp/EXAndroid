package com.ch96.ex91kotlinhello

fun main() {

    //코틀린의 OOP

    //1. 클래스의 선언 및 객체 생성
    //객체 생성이 아주 특이 - new 키워드가 없음
    var obj:MyClass = MyClass()
    obj.show()

    //1.1 별도의 파일로 클래스 설계 가능
    var obj2:MyKotlinClass = MyKotlinClass()
    obj2.show()

    //2. 생성자
    //코틀린의 생성자는 두 가지 종류가 있음 [주생성자(Primary), 보조생성자(Secondary)]

    //2.1 primary constructor
    var s = Simple() //주 생성자 자동호출

    //2.2 주생성자에 값 전달 - 주생성자는 오버로딩 불가!!!
    val s2 = Simple2(100,200,300)

    //2.3 secondary constructor
    val s3 = Simple3()
    val s4 = Simple3(10) //overloading

    //2.4 주생성자 + 보조생성자(오버로딩 역할)
    val s5 = Simple4()
    val s6 = Simple4(100) //보조생성자 값만 나오는것이 아니라 주생성자도 함께 나옴

    //2.5 주생성자의 constructor 키워드 생략
    Simple5()
}

class Simple5() { //constructor 생략가능 -> ()만!
    init {
        println("Simple5 primary constructor")
    }
}

class Simple4 constructor() {
    init {
        println("Simple4 primary constructor")
    }

    //보조생성자[overloading 역할]
    //주생성자와 함께 사용할때는 반드시 보조생성자에서 주생성자의 호출문을 명시적으로 불러야함 - this()생성자 호출문
    constructor(num:Int):this(){
        println("Simple4 overloading constructor : $num")
    }
}

//보조생성자 - 자바처럼 class 안에 메소드처럼 존재하는 생성자
class Simple3 {
    //보조생성자
    constructor() {
        println("Simple3 보조생성자")
    }
    //보조생성자는 오버로딩이 가능 (파라미터 달라야 함) - var 키워드를 붙이는 멤버변수는 사용불가 (주생성자만 가능)
    constructor(num:Int){
        println("Simple3 보조 생성자 : $num")
    }
}

//주생성자에 파라미터를 전달하는 클래스 - 주생성자의 파라미터에 var/val 키워드가 있으면...파라미터면서 멤버변수
class Simple2 constructor(num:Int, num2:Int, var num3:Int) { //주생성자의 파라미터에는 변수키워드 사용가능
    var num2:Int = 0 //초기화 필요
    init {
        println("Simple2 primary constructor : $num")
        println("Simple2 primary constructor : $num2")
        //멤버변수로 만들어서 전달해주기
        this.num2 = num2
        println("Simple2 primary constructor : $num3")
    }
    fun show(){
        //println("num : $num") //생성자의 매개변수는 메소드에서 사용할 수 없음
        //Adapter에서도 생성자로 받은 context를 다른 지역메소드에서 사용하고 싶으면 멤버변수로 만들어서 사용했음
        println("num : $num2") //멤버변수로 받아서 인식
        println("num3 : $num3") //파라미터에서 멤버변수로 만들면서 값을 받았기때문에 바로 사용이 가능함
    }
}

//2.1 주생성자 [클래스 이름 옆에 작성]
//주생성자는 별도의 {}가 없음
class Simple constructor(){
    //주생성자가 호출될때 같이 호출되는 초기화 블럭 - 주생성자의 내용을 작성할 수 있는 영역
    init {
        println("Simple primary Constructor!")
    }
}

class MyClass {
    //멤버변수 - propety : 반드시 초기화 해야함 (자바는 0에 해당하는 값으로 자동 초기화)
    var a:Int = 10

    //멤버함수 - method
    fun show() {
        println("show : $a")
    }
}