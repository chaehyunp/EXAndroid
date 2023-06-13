package com.ch96.ex91kotlinhello

//코틀린 언어의 기초문법

//문법적 주요 특징!
//A. 문자의 끝을 나타내는 세미콜론(;)을 사용하지 않음. - 써도 ERROR는 아니지만 무시됨.
//B. 변수를 만들때 자료형을 먼저 쓰지 않고 var, val 키워드 사용. 단, 자료형은 존재함. 자동 형변환 안됨. 즉, 정적타입 언어.
//C. new 키워드 없이 객체생성. new String() --> String()
//D. 안전하게 null을 다룰 수 있는 문법이 제공됨. - null safety 언어
//E. 코틀린은 함수형 언어. 즉, 함수를 객체처럼 변수에 저장하고 파라미터로 넘겨주는 등의 작업 가능. - 람다식처럼


//#. 프로그램의 시작함수인 main함수가 반드시 있어야 함
fun main() {

    //.kt 코틀린 파일만 별도로 실행하려면 마우스 우클릭으로 해당파일 run 메뉴 실행 - 결과는 하단 [run]탭에서 실행됨

    //1. 화면(콘솔창 - Run탭)출력 : print(), println() 존재
    print(10)
    print(3.14)

    //print()는 자동으로 줄바꿈 안됨
    //자동 줄바꿈 하는 기능함수 println()
    println()
    println("HELLO KOTLIN")
    println(10)
    println('A')
    println(true)
    println(5+3)

    //변수명을 전달하면 변수 안의 값이 출력
    var a:Int = 10
    println(a)
    var b:String = "Hello"
    println(b)

    //==============================================================================================

    //2. 자료형과 변수
    //*코틀린 자료형 종류
    //코틀린은 소문자로 시작하는 자료형이 존재하지 않음, 번역이 그때그때 상황에 따라 다름 레포?
    //1) 기초타입 : Boolean Byte Char Short Int Long Float Double [기본적으로 코틀린은 모든 변수가 참조형. 즉, 모든 변수가 참조변수(8byte)]
    //2) 참조타입 : String Random Any(Java의 Object와 비슷 - 최상위) Unit ... 그외 Kotlin APIs, Java APIs

    //*변수의 2가지 종류 : var, val [문법 -- var 변수명:자료형, val 변수명:자료형]
    //2.1 var [값 변경 가능]
    var num:Int = 10
    println(num)

    var num2:Double = 3.14
    println(num2)

    //권장하지는 않지만 변수를 만들때 값을 초기화하지 않아도 됨. [단, 지역변수만]
    var num3:Float
    num3 = 5.23f
    println(num3)
    
    //변수이므로 변수가 가지고 있는 값 변경 가능
    num = 20
    num2 = 20.5
    num3 = 10.88f
    println(num)
    println(num2)
    println(num3)
    
    //자료형이 정해진 변수이므로 다른 자료형의 대입 ERROR
    //num = 3.14 //ERROR [Int변수에 Double 대입]
    //num2 = 50 //ERROR [Double변수에 Int 대입] - 자동형변환 불가
    //Java에서는 더 큰 자료형에 작은 자료형의 값 대입이 가능

    //명시적으로 형변환하여 값 대입해보기
    //[.toXXX()로 변환 가능 (기초타입들만 사용가능)]
    num = 3.14.toInt()
    num2 = 50.toDouble()
    println(num)
    println(num2)

    //문자열 String 객체
    var s:String = "Hello"
    println(s)
    //var s2:String = new String("Hello") //kotlin에서는 new 키워드 없음.
    //var s2:String = String("Hello") //단순 "문자열"객체를 생성할때 String()생성자 사용 불가 [String()생성자는 Buffer나 Byte배열을 String객체로 생성할때만 사용]

    //2.2 val [값 변경 불가능 - 읽기전용 변수] ##상수와는 조금 다름. - 상수는 const val 키워드 사용.
    val n1:Int = 100
    //n1=200 //ERROR
    println(n1)

    //권장하지는 않지만 지역변수 선언할때 초기화 하지 않아도 되는 특징은 val도 마찬가지
    val n3:String
    n3="NICE"
    println(n3)

    //## var, val 사용 팁
    //데이터를 가지고 있는 변수 var [ex. name, age, title...]
    //객체를 참조하는 변수 val [ex. TextView, ImageView, NotificationManager...]
    
    //2.3 자료형을 생략하면서 변수 선언 가능 - 자료형은 자동을 추론됨. [자동 추론]
    var aa = 10 //Int로 추론됨
    println(aa)

    var bb = 3.14 //Double
    println(bb)

    var cc = 3.14f //Float
    println(cc)

    var dd = true //Boolean
    println(dd)

    var ee = 'A' //Char
    println(ee)

    var ff = "Hi" //String
    println(ff)

    //주의! 변수선언시에 자료형 표기가 없지만 값을 대입하면서 자료형이 자동 추론된 것. 즉, 변수는 자료형이 있음

    //자료형 명시 생략을 통해 자동 추론하려면 변수 선언하면서 반드시 값을 대입해야함
    //var gg //ERROR : 자료형을 알 수 있는 방법이 없기때문에 변수를 만들 수 업음
    //gg = 10

    //*정수값 표기의 특이한 점 [실생활에서 숫자의 3자리마다 콤마(,) 구분과 비슷한 표기법]
    var a3 = 5_000_000
    println(a3) //출력은 구분자없이

    //2.4 Kotlin만의 자료형 타입
    //Any 타입 [자바의 Object처럼 최상위 타입]
    //최상위 타입은 어떤 객체든 참조가 가능 [편해보이지만 실제 개발시에 어떤 자료형인지 예측이 어려워서 필요한 경우에만 사용 - 어떤 값이 예상할 수 없을때]
    var v:Any
    v = 10
    println(v)

    v = 3.14
    println(v)
    
    v = "Hello"
    println(v)

    //2.5 null값에 대한 자료형 [null 안정성 - 별도로 추가 수업예정 - 여기서는 대략적인 특징만]
    //코틀린은 자료형을 명시하면 null값을 저장할 수 없음.
    //var nn:Int = null //ERROR
    //var ss:String = null //ERROR
    //기본적으로 null 저장 불가

    //null값을 가질 수 있는 변수라고 표시할 수 있음. [Nullable 변수]
    var nn:Int? = null
    var ss:String? = null
    println(nn)
    println(ss)

    //nullable 변수 사용의 특이점
    var sss:String = "Hello"
    println(sss.length)

    var ssss:String? = "Hello"
    //println(ssss.length) //nullable 변수는 그냥 "."으로 멤버 사용 불가, null일 수도 있기때문에
    println(ssss?.length) //?. 연산자 - "null이 아닐때만" length 출력
    println()

    //** 화면출력의 Format 만들기 **
    //문자열 결합에 대한 내용
    println("Hello " + "Kotlin")

    //숫자타입과 String타입은 자동결합이 안될 수 있음.
    //println(10 + "Hello") //ERORR - number타입이 string타입으로 변하지 않음
    //따라서 number타입을 string타입으로 변환해서 결합
    println(10.toString() + "Hello")
    
    //특이점. 문자열이 먼저 있으면 결합 가능... - 자동형변환이 됨
    println("Hello" + 10)
    println()

    //변수 2개의 값을 덧셈하여 출력하는 코드
    var nnn1:Int = 50
    var nnn2:Int = 30
    //"50 + 30 = 80" 모양으로 포맷팅하여 출력
    println(nnn1.toString() + " + " + nnn2 + " = " + (nnn1+nnn2))
    println("" + nnn1 + " + " + nnn2 + " = " + (nnn1+nnn2))

    //위처럼 결합연산으로 포맷팅하면 코드가 지저분함 - php처럼 "$"이용하여 변수임을 명시
    println("$nnn1 + $nnn2 = ${nnn1+nnn2}")
    //이렇게 문자열 안에 $변수명을 사용하는 것을 [문자열 템플릿]이라고 부름
    //띄어쓰기를 하지않으면 변수명과 오해할 수 있음 - 정식표현은 모두 {}로 구분, 약식표현으로 없이 사용
    //ex. $nnn1와 -- ERROR, ${nnn1}와 -- OKAY


}