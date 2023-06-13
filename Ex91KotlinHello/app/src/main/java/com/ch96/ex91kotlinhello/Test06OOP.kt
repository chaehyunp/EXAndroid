package com.ch96.ex91kotlinhello

fun main() {
    //2) 코틀린의 상속
    val obj:Second = Second()
    obj.a = 100
    obj.b = 200
    obj.show() //show는 a만 출력하도록 만들어져있음, b까지 출력하고 싶다면 override

    //3) 업캐스팅, 다운캐스팅
    var f:First=Second() //up casting : 옛날리모컨으로 최신 텔레비전 제어, BUT 리모컨에 넷플릭스는 없음
    f.show() //실제 참조하는 객체의 show가 호출 - 다형성
    
    //형변환 연산자 as
    var s:Second = f as Second //down casting : 자식이 부모를 컨트롤 불가
    //근데 f가 실질적으로 참조하고 있는것은 Second이므로 형변환으로 참조
    s.b = 500
    s.show()

    //상속 마무리 연습 [Person - Student - Professor - PtjStudent]
    var p = Person("Sam", 20)
    p.show()
    println()
    var stu = Student("Robin", 25, "Android")
    stu.show()
    println()
    var pro = Professor("Cindy", 45, "Computer")
    pro.show()
    println()
    var ptj = PtjStudent("Kim", 25, "ios", "PC management")
    ptj.show()

}

//First를 상속하는 클래스 - 상속문법이 extends --> : 기호
//코틀린에서는 기본적으로 final, 만들자마자 상속이 불가 --> open 키워드가 있어야만 상속이 가능해짐
//상속하는 클래스명 옆에 반드시 생성자 호출문이 명시되어야함
class Second:First() { //First가 아니라 First의 객체를 상속받는다! ()필요
    //First의 멤버 a, show()를 보유한 상태
    var b:Int = 20
    //상속받은 show() 기능 개선 - override
    //코틀린은 반드시 오버라이드임을 명시해야만 함
    override fun show() { //메소드도 기본적으로 모두 final이기 때문에 open 필요
        super.show()
        println("b: $b")
    }
}

//상속해줄 클래스 - open
open class First {
    var a:Int = 10

    //기본이 final 메소드, override 안됨 --> 허용하려면 open
    open fun show() {
        println("a : $a")
    }
}