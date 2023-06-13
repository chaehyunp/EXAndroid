package com.ch96.ex91kotlinhello

fun main() {

    //안드로이드에서 많이 사용하는 기술
    //1) 이너클래스
    //2) 인터페이스 및 익명클래스
    //3) static 키워드를 대체한 companion object [동반객체]
    //4) 늦은 초기화 - lateinit, by lazy
    //원래 멤버변수로 참조변수 만들어서 main에서 findViewById
    //onCreate보다 멤버변수가 먼저 만들어지기때문에 참조변수만 만들고 값을 줄 수 없었음 findViewById 불가
    //코틀린에서는 값을 주지않으면 에러남... 이러한 이유로 늦은 초기화 필요

    //1. 이너클래스
    val obj  = AAA()
    //inner class는 외부에서 직접 객체생성 불가
    //outer 없이 불러서 사용하지 못하도록 (ex.다이얼로그 내부의 버튼인데 다이얼로그 없이 버튼을 생성한다든가..하는 문제 방지)
    //val obj2:AAA.BBB = AAA.BBB()
    val obj2:AAA.BBB = obj.getBBBinstance()
    obj2.show()

    //2. 인터페이스
    //인터페이스는 객체생성 불가 - 기능설계가 안되어있기때문에
    //var c:Clickable = Clickable() //ERROR
    //인터페이스를 구현한 Test클래스를 객체로 생성
    var c:Clickable = Test() //upcasting - 내가 Test가 필요했던것이 아니라 Clickable이 필요했음을 명시적으로 알 수 있음
    c.onClick()
    println()

    //2.1 익명클래스
    //다른 기능을 하는 또다른 Clickable이 필요
    //또다시 Test같은 새로운 class를 명명하는 것이 불편
    //객체생성하면서 인터페이스를 그 자리에서 구현하는 이름없는 클래스 : 익명클래스
    //익명클래스 객체를 만드는 키워드 object
    var c2:Clickable = object:Clickable {
        //Clickable은 이름밖에 없는 메소드기때문에 여기서 만들어주어야함
        override fun onClick() {
            println("apple")
        }
    } //var이 변수 fun이 함수인 것처럼 object를 쓰면 객체를 만든것
    
    //3. 동반객체 (companion object) : 정적멤버 static 키워드의 대체 문법
    //클래스(설계도면)에 붙어있는 객체같은 녀석
    //별도의 객체생성없이 클래스명만으로 접근가능한 녀석
    //println(Sample.a) //ERROR
    println(Sample.title)
    Sample.show()

    //4. 늦은초기화
    //4.1 lateinit [var변수만 사용가능]
    var h:Hello = Hello()
    //println(h.name) //Exception 발생, 아직 초기화안됨
    h.onCreate()
    println(h.name)

    //4.2 by lazy [val 변수만 사용가능]
    println(h.add)
    println(h.address)
    println(h.tel)

}


class Hello {
    //4.1) lateinit
    //var name:String //ERROR - 초기화 없으면

    //만약 초기화를 나중에 하고싶다면
    lateinit var name:String

    fun onCreate() {
        name = "Sam" //이때 초기화됨
    }

    //lateinit 사용 특징
    //1) nullable 변수는 lateinit불가
    //lateinit var title:String? //ERROR

    //2) 기본자료형(8개)은 사용불가
    //lateinit var age:Int //ERROR - 어차피 기본값은 참조값이 아니기 때문에 굳이 값을 나중에 넣을 이유가 없음

    //3) val에는 사용불가

    //4.2) by lazy

    //val address:String //ERROR - 초기화 필요
    val address:String by lazy {"Seoul"} //이 변수가 처음 사용될 때 초기화됨
    val add:String = "Pusan" //만드는 순간 초기화됨

    val tel:String by lazy {
        println("늦은 초기화")
        "01012345678"
    }

    //by lazy의 특징
    //1) 기본형 자료형도 가능
    val age:Int by lazy {20}

    //2) nullable도 가능
    val message:String by lazy { "Hello" }
    val message2:String? by lazy { null }

    //3) 조건값으로 값 대입 가능
    val message3:String by lazy {
        if (age < 20) "미성년자"
        else "성인"
    }

    //4) var 사용 불가능
    //var sss:String by lazy {"Nie"} //ERROR
}

class Sample {
    var a:Int = 10 //인스턴스 변수는 객체생성할때만 사용가능
    //static var b:Int = 20 //static 키워드 없음
    companion object {
        //이 안에 있는 멤버들은 이미 객체화되었기 때문에 그냥 사용 가능 [단, Sample클래스에 동반되었기 때문에 클래스명이 요구됨]
        var title:String = "Hello"
        fun show() {
            println("동반객체의 show!")
        }
    }
}

//인터페이스의 추상메소드들을 구현하는 별도의 클래스를 설계하고 이 클래스를 객체로 만들어서 사용해야함
//모두 같은 이름을 사용함으로써 "규격"을 만들 수 있음 - 운영체제 대응에 필요
//implement 키워드 대신에 상속처럼 :기호 사용
//인터페이스를 구현할때는 상속과 다르게 인터페이스명 옆에 ()생성자 호출문 없음
class Test:Clickable{
    override fun onClick() {
        println("clicked!")
    }

}

//interface는 특별한 것이 없음 - 추상메소드만을 가진 클래스
interface Clickable {
    //abstract method - 이름만 있는 메소드
    //abstract fun onClick() //어차피 추상메소드만을 가질 수 있기때문에 abstract 생략 가능
    fun onClick()
}

class AAA {
    var a:Int = 0

    fun show() {
        println("AAA클래스의 show : $a")
    }

    //inner class 객체를 생성하여 리턴해주는 기능메소드
    fun getBBBinstance():BBB {
        return BBB()
    }
    
    //이너클래스 - 코틀린은 클래스 내부에 있다고 이너클래스가 아님
    //이너클래스가 되려면 이너키워드 필요 - inner
    inner class BBB {
        fun show() {
            //이너클래스의 장점 - 아우터의 멤버를 내것인양 호출 가능
            a = 100
            println("아우터 클래스의 멤버 a : $a")

            //아우터의 show메소드 호출
            //show() //재귀호출
            this@AAA.show() //MainActivity.this --> this@MainActivity 이너에서 아우터를 부르는 행위
        }
    }
}