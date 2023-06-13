package com.ch96.ex91kotlinhello

fun main() {
    //코틀린 언어의 주요 특징 중 하나
    //Null Pointer Exception[NPE]에 대한 안정성 보유한 언어

    //코틀린은 null값을 저장할 수 있는 타입을 명시적으로 구분하여 사용하도록 함
    //var s1:String = null //ERROR - non nullable 변수
    var s2:String? = null //nullable 변수
    println(s2)

    //nullable 변수들을 사용할때 특이한 멤버접근 연산자들
    var str1:String = "Hello" //non nullable variable
    var str2:String? = "Nice" //nullable variable

    //멤버 사용법의 차이
    println("글자수 : ${str1.length}")
    //println("글자수 : ${str2.length}") //ERROR - dot연산자에서 에러, null일 수도 있으니 마구잡이로 사용할 수 없음 (=멤버로 length가 없을 수도 있음)
    
    //해결방법 : null이 아닐때만 하도록 함
    if (str2 != null) println("글자수 : ${str2.length}") //이 영역은 null이 아님이 확실해짐 -- ERROR가 뜨지 않음

    //if문 처리로 해결은 되나 번거로움 --> null 안정성 접근 연산자들 탄생
    //1) ?. 연산자 : null safe 연산자
    println("글자수 : ${str2?.length}") //만약 null이 아니면 length 사용
    str2 = null
    println("글자수 : ${str2?.length}") //만약 null이면 length 수행없이 null값으로 나옴

    //객체가 null일때 그냥 null로 값이 전달되는것이 싫고 원하는 값으로 나왔으면 한다면..?
    //객체가 null이면 길이값이 -1이 나왔으면...
    val len:Int = if (str2 != null) str2.length else -1
    println(len)

    //if else문이 번거롭다면...
    //2) ?: 연산자 : 엘비스[Elvis] 연산자
    val len2:Int = str2?.length?:-1 //앞에가 null이면 뒤의 값을 해라
    println(len)

    //이런식으로 NPE에 안전한 연산자 기능이 있지만 이 기능을 사용하지 않고 그냥 null이면 예외가 났으면 좋겠다
    //즉, 원래 java처럼 쓰고 싶다면 --> null이 아님을 확신할때 사용하는 연산자
    //3) !! 연산자 : non-null asserted call 연산자
    var ss:String? = "Hello"
    println("글자수 : ${ss!!.length}")

    //만약 진짜 null이면?
    //var sss:String? = null
    //println(sss!!.length) //exception NPE

    var mmm:String ?= "Nice"
    //var nnn:String = mmm //null일 수도 있는데 null을 넣을 수 없는 변수에 넣으면 ERROR
    var nnn:String = mmm!! //이러한 경우 주로 사용 (non-nullable 변수에 nullable 변수를 대입할때)
    
    var ttt:String = "Good"
    var xxx:String? = ttt //nullable 변수는 null이어도 아니어도 OKAY

    //4) as? : 안전한 캐스팅(형변환) 연산자 - 자료형이 맞지 않는 타입을 억지로 형변환하는 경우
    val yyy:YYY? = YYY()
    //val zzz:ZZZ? = yyy //ERROR - 아무런 상관이 없는데 넣을 수 없음
    //val zzz:ZZZ? = yyy as ZZZ //억지로 형변환할 경우 ERROR 표시는 없지만 Exception 발생

    //안전하게 형변환을 수행하는 연산자
    val zzz:ZZZ? = yyy as? ZZZ //형변환이 불가하면 에러가 아니라 null값을 전달
    println(zzz)
}

class YYY {
    var a = 10
}

class ZZZ {
    var a = 20
}