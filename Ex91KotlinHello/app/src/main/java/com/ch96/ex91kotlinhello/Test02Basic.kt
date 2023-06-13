package com.ch96.ex91kotlinhello

fun main() {

    //3. 연산자 특이점
    //숫자타입들간의 산술연산은 자동형변환 수행됨 [작은것 --> 큰것]
    println(10 + 3.14) //Int --> Double

    //숫자타입이 아닌 자료형과는 자동형변환 안됨
    //println(10 + true) //ERROR - 자바에서도 안됨
    //println(10 + 'A') //ERROR - 자바에서는 아스키값으로 가능하던 연산

    //자료형을 체크하는 연산자 is
    var n = 10
    if (n is Int) println("n은 Int형 변수입니다")
    else println("변수 아닌갑네")

    //is 연산자로 nullable과 nonnullable을 구분하지 않음
    var n2:String = "Hello"
    if (n2 is String) println("n2 변수는 String")
    if (n2 is String?) println("n2 변수는 String?")

    //!is도 있음
    if (n2 !is String) println("n2 변수는 String이 아니다")

    //다른 자료형은 is로 체크하면 문법에러
    //if (n2 is Int) {} //ERROR - String은 String으로만 체크가능

    //그럼 is는 큰 의미가 없는걸까..?
    //Any 타입에 대한 식별로 많이 사용됨
    var obj:Any
    obj = 10
    if (obj is Int) println("${obj}는 Int입니다")
    if (obj is Double) println("${obj}는 Double입니다")

    //Java의 instanceof(어떤 객체가 어떤 부모의 자식인지)와 흡사

    class Person {
        var name = "Sam"
        var age = 20
    }

    var p = Person() //Person 객체 생성
    println(p.name + " " + p.age)
    
    //is 연산자의 특이기능 - is를 통해 어떤 객체인지 판별했다면 참인 영역 안에서는 그 객체로 참조변수를 인식
    var obj2:Any
    obj2 = Person()
    //obj2의 자료형이 Any타입이기때문에 멤버변수 name과 age가 자동리스트업 되지 않음 - 부모는 자식을 건들일 수 없음
    if (obj2 is Person) {
        //참인 영역 안에서는 obj2를 Person 참조변수로 인식 (Person이 맞기때문에 괄호 안으로 들어온 것이기 때문에)
        println("${obj2.name} - ${obj2.age}")
    }
    
    //비트연산가 없음 [&,|,~,^] - 논리연산자는 있음[&&,||,!]
    //대신에 그 연산기능을 가진 메소드가 존재
    //println(7|3) //ERROR
    println(7.or(3)) //OR 비트연산
    println(7 or 3) //마치 연산자처럼 or 메소드 표기 가능

    //==============================================================================================

    //4. 조건문 : if, when [switch문법이 없음]

    //4.1 if 표현식 - if문이나 else문의 마지막 실행문이 변수에 대입될 수 있음
    var ss:String
    if (10 > 5) ss = "Hello"
    else ss = "Nice"

    var sss:String = if(10 > 5) {
        "Hello"
        print("aaa")
        "Good" //마지막 값이 들어감
    }
    else {
        "Nice"
    }

    //이런 특징때문에 if문을 코틀린에서는 제어문 대신에 [if표현식]이라고 표현
    //그래서 코틀린에서는 삼항연산자가 없음 - if표현식으로 대체
    //var str:String = (10>5)? "aaa" : "bbb" //ERROR
    var str:String = if (10>5) "aaa" else "bbb"
    print(str)

    //4.2 switch문법이 없어지고 대신에 when문법이 대체
    var h:Any? = null

    //switch(h){} //ERROR - switch문법 없음

    h=10
    when(h) {
        10 -> println("ten")
        20 -> println("twenty")
        //자료형이 다른 경우를 배치해도 됨
        "Hello" -> println("Hi")
        //true -> println("true") -> 이 경우에는 true에 따른 else문이 필요
        
        //변수가 있어도 됨
        n -> println("n변수와 동일값")

        //2개 이상의 조건을 묶을 수도 있음
        30, 40 -> println("30이거나 40")
        
        //switch문의 default 역할 (위 조건에 맞는것이 하나도 없다면)
        else -> {
            //실행문이 여러 절일 경우에는 중괄호
            println("one")
            println("two")
            println("three")
        }
    }
    
    //when도 if문처럼 표현식이기때문에 결과를 변수에 저장하는것이 가능
    h = 30
    var result = when (h) {
        10 -> "a"
        20 -> "b"
        else -> {
            "c"
            "d" //마지막 결과값
            //println("abcd") //kotlin.Unit
        }
    }
    println(result)
    
    //when에 is 키워드 연산자 사용가능
    h = 50
    when(h) {
        is Int -> println("Int타입")
        is String -> println("String타입")
        else -> println("else")
    }
    
    //when을 특정 수식으로 제어 가능
    //주의!! when 사용문법이 약간 다름
    h = 85
    when { //수식을 쓸때는 괄호를 사용하면 안됨
        //h >= 90 && h <= 100 -> println("A score")
        h in 90..100 -> println("A score")
        h >= 80 -> println("B score")
        h >= 70 -> println("C score")
        h >= 60 -> println("D score")
        else -> println("F score")
    }

    //==============================================================================================

    //5. 반복문 : while, for
    //while문은 다른것이 없음

    //for문은 작성하는 문법이 완전히 다름
    //for (var i =0; i<5; i++) //이러한 복잡한 형태 없음

    //0부터 5까지 실행되는 반복문 --> 0,1,2,3,4,5 총 6번 실행(마지막 번호가 포함됨)
    for (i in 0..5) {
        println(i)
    }

    //제어용 변수인 i를 다른 이름으로 변경 가능
    for (a in 3..10){
        println(a)
    }

    //제어변수 앞에 var 키워드를 추가하면 에러
    //for(var t in 0..5) //ERROR

    //마지막 숫자 전까지 하려면 ".."대신에 until
    for (i in 0 until 10) println(i)

    //2씩 증가 (step)
    for (i in 0..10 step 2) println(i)

    //값의 감소 (downTo)
    for (i in 10 downTo 0) println(i)

    //값의 2씩 감소
    for (i in 10 downTo 0 step 2) println(i)

    //**'@'Label로 반복문의 종료영역 선택하기 ****************
    for(n in 0..5){
        if(n==3) break
        print("$n   ")
    }
    println()

    for(y in 0..5){
        print("$y : ")
        for(x in 0..10){
            if(x==6) break
            print("$x   ")
        }
        println()
    }
    println()
    println()

    //@Label로 break위치 선택
    KKK@for(y in 0..5){
        print("$y : ")
        for(x in 0..10){
            if(x==6) break@KKK
            print("$x   ")
        }
        println()
    }

    //반복문에서는 사용 권장하지않음
    //안쪽에서 바깥쪽을 부를때 많이 사용


    //***************************************************
 }