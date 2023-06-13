package com.ch96.ex91kotlinhello

fun main() {
    //7. 함수
    //7.1 함수호출
    show()

    //7.2 파라미터 전달
    output(100, "Hello")
    
    //7.3 리턴을 하는 함수 호출
    var z:Int = sum(50,30)
    println("sum함수의 결과값 : $z")
    
    //7.4 참고 - 리턴이 없는 함수의 리턴을 받으면?
    //리턴이 없는 함수는 자바처럼 void가 아니라 Unit이라는 자료형 객체가 리턴됨
    var x = display()
    println(x)

    //7.5 함수선언의 단순화 : 리턴 키워드를 할당연산자[=]로 바꿀 수 있음
    val data:String = getData()
    println(data)
    val data2:String = getData2()
    println(data2)
    val data3 = getData3(5)
    println(data3)
    val data4 = getData4(15)
    println(data4)

    //7.6 익명함수
    aaa() //함수의 이름을 통해 호출
    bbb() //익명함수를 가진 변수를 함수 이름처럼 호출가능
    ccc() //함수의 자료형[()->리턴타입]이 명시된 변수로 함수호출
    ddd() //익명함수 축약표현
    eee() //익명함수 축약형의 자동추론
    fff("Hello") //파라미터가 있는 익명함수
    ggg("Nice") //자료형 명시
    hhh("Cool") //축약표현
    iii("Fun") //축약표현 - 파라미터명 지정
    jjj("Sam",20)
    val n = kkk()
    val n2 = lll()
    val n3 = mmm()
    val n4 = nnn()
    println(n4)
    val len = ooo("Android")
    println(len)

    //7.7 고차함수
    var a = 10
    var b = a

    var f:()->Unit = fun() {
        println("익명함수")
    }

    f() //변수이름으로 함수호출
    //익명함수는 변수에 대입되므로... 당연히 다른 변수에 저장도 가능
    var g:()->Unit = f
    g() //전달받은 함수를 변수명으로 대신 호출 가능

    //함수를 다른변수에 저장할 수 있다면... 다른 함수의 파라미터(매개변수)에도 전달이 가능함
    ppp("sss", g)

    val xx:(String)->Int={
        it.length
    }
    ttt("Android", xx)
    ttt("Android",{it.indexOf("d")}) //전달되는 함수에 따라 전혀 다른 동작을 함
    ttt("Android", {s -> s.hashCode()})
    //android setOnclickListner의 모습 (람다식)

    //7.7 함수 파라미터의 default 값
    www(10)
    www(3)
    www(10,20)

    zzz("Korea", "Seoul")
    //파라미터를 지정하면서 대입 가능
    zzz(city = "NewYork", nation = "USA")
    zzz(city = "Pusan")
}

fun zzz(nation:String="Korea", city:String) { //값은 전달받지 않았을때만 들어가는 값, 이미 들어가있는 값이 아님
    println(nation)
    println(city)
}

fun www(a:Int=3, b:Int=5) { //기본값 지정가능
    println("a:$a b:$b")
}

//조금 더 응용된 고차함수
fun ttt(s:String, ff:(String)->Int) {
    val n = ff(s)
    println(n)
}

//고차함수 - 함수의 파라미터로 다른 함수를 전달받는 함수
fun ppp(s:String, f:()->Unit) {
    println("string : $s")
    f() //매개변수명으로 전달받은 함수를 대신 호출
}

//마무리 연습
val ooo:(String)->Int = { s->s.length }

//축약형의 {}안에 값이 많으면 - 마지막 값
val nnn:()->Int = {
    30
    40
    //println("nnn") //마지막이 리턴값 Int 리턴하겠다고 하고 Int 아님 ERROR
}

//익명함수의 축약형 - 축약형을 쓰면 return 키워드도 생략해야만 함!!!
val mmm:()->Int = {
    //return 20 //ERROR
    20
}

//리턴이 있는 익명함수의 자료형 명시
val lll:()->Int = fun():Int {
    return 20
}

//리턴이 있는 익명함수
val kkk = fun():Int {
    return 20
}

//파라미터 여러 개짜리 - it이 자동으로 생기지 않음
val jjj:(String,Int)->Unit = {
    name, age -> println("name : $name - age : $age")
}

//익명함수의 축약표현 - 파라미터 이름 정하기
val iii:(String)->Unit = {
    s ->  println("글자수 : ${s.length}")
}

//익명함수의 축약표현
val hhh:(String)->Unit = {
    //축약하면 자동으로 it이라는 특별한 키워드의 변수가 생김
    //it이 파라미터
    println("글자수 : ${it.length}")
}

//자료형 명시해보기
val ggg:(String)->Unit = fun(s:String) {
    println("글자수 : ${s.length}")
}

val fff = fun(s:String) {
    println("글자수 : ${s.length}")
}

//익명함수의 자료형은 자동추론가능
var eee = { //익명함수(변수에 함수대입),fun()생략,자료형생략
    println("익명함수4")
}

//아래 익명함수의 축약표현 ...어디까지 줄어드는거예요?
var ddd:()->Unit = {
    println("익명함수3")
}

//익명함수를 가진 변수에 자료형을 명시해보기
//아래 bbb는 자동추론됨, 그럼 함수가 대입되어있는 변수는 자료형이 무엇?
//함수를 쓸때가 가장 중요한 것은 파라미터와 리턴이 있느냐 없느냐
//함수의 자료형 ==> 람다식으로 표기됨 "(파라미터)->리턴값"
var ccc:()->Unit = fun() {
    println("익명함수2")
}

//익명함수 - 이름이 없는 함수
//fun () {} //ERROR
//익명함수를 사용하려면 반드시 함수를 변수에 대입해야함
var bbb = fun() {
    println("익명함수")
}

//기본적인 함수
fun aaa() {
    println("I am aaa function")
}

//아래 리턴값을 가진 getData3() 함수의 단순화 표기
fun getData4(num:Int):String = if (num<10) "Good" else "bad"

//조금 더 복잡한 리턴값 가진 함수 단순화
fun getData3(num:Int):String {
    if(num<10) return "Good"
    else return "bad"
}

//아래 리턴값을 가진 getData() 함수의 단순화 표기
fun getData2():String = "Hello"

fun getData():String {
    return "Hello"
}

fun display() { //안쓰면 자동 Unit, 리턴타입 Unit이라고 명시 가능
    println("display")
}

//리턴타입을 작성하는 위치가 함수() 다음에 : 쓴 후 작성
fun sum(a:Int, b:Int):Int {
    return a+b
}

fun output(a:Int, b:String) { //파라미터에 변수 키워드(var,val) 사용불가, 변수명과 자료형만 명시
    println(a)
}

fun show() {
    println("show function")
    println()
}