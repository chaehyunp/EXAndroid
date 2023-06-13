package com.ch96.ex91kotlinhello

fun main() {

    //6. 배열 Array & 컬렉션 Collection
    //6.1 배열 - 요소의 개수 변경이 불가능 - Array
    //int[] aaa = new int[3]{10,20,30}
    //var aaa = arrayOf(1,2,3) - 자동추론
    //제네릭 사용할때 주의점 : 제네릭 <> 다음에 대입연산자(=)가 붙어있으면 안됨
    var aaa:Array<Int> = arrayOf(1,2,3)
    //요소값 출력
    println(aaa[0])
    println(aaa[1])
    println(aaa[2])
    //println(aaa[3]) //ERROR - 범위에서 벗어남

    //요소값 변경도 특별할 것 없음
    aaa[0] =100
    println(aaa[0])

    //배열의 길이값 멤버변수 [Java의 배열은 length]
    println("배열의 길이 : ${aaa.size}")

    //요소값을 일일이 접근하는 것보다 반복문 이용
    for (i in 0 until 3) println(aaa[i])

    for (i in 0 until aaa.size) println(aaa[i])

    //배열은 어차피 연속된 인덱스 번호의 나열
    //0..2나 배열변수를 놓으나 같은 개념처럼 접근 가능 - 마치 자바의 확장된 for문법처럼
    for (t in aaa) { //t는 인덱스가 아니라 요소값을 의미
        println(t)
    }

    //향상된 for문법을 사용하면서도 index번호로 반복하고 싶다면
    for(i in aaa.indices) println("$i : ${aaa[i]}")

    //혹시 인덱스와 값을 동시에 가져오고 싶다면
    for((i,v) in aaa.withIndex()) println("$i : $v")

    //배열객체 멤버 안에 요소값 각각을 반복적으로 접근할때마다 {}안에 있는 코드가 자동으로 발동하는 forEach 기능
    //{}안에 it이라는 특별한 매개변수가 존재함 --> it이 각 요소의 참조변수
    aaa.forEach {//it이 Int인 이유는 aaa가 Int값이기때문
        println(it)
    }

    var bbb:Array<String> = arrayOf("aa","bb","cc")
    bbb.forEach {//it - String
        println(it)
    }

    //배열을 만들면서 자동추론을 적용할때 타입을 명시하는데, 기본형 타입에 대해서는 별도의 생성함수 존재
    var ccc = arrayOf<Int>(10,20,30)

    var ddd = intArrayOf(10,20,30) //Int전용 배열
    var eee = doubleArrayOf(3.14,5.55) //Double전용 배열
    //stringArrayOf()같은 것을 없음 - ONLY 기본형만 존재

    //빈 배열 다섯 개짜리 만드는 형태
    var fff = arrayOf<Int>() //0개짜리 배열
    var ggg = arrayOf<Int>(0,0,0,0,0)

    //배열의 요소값의 시작을 null값으로 주며 갯수 지정 - 값 나중에
    var hhh = arrayOfNulls<Double>(5)
    for(t in hhh) println(t)
    
    //즉, 배열은 요소의 갯수 변경이 불가한 특징을 가짐

    //6.2 컬렉션 - List, Set, Map 특성의 대량의 데이터들
    //1) List : 요소가 순서대로 저장. index번호가 자동 부여, 중복 데이터 허용
    //2) Set : 요소 순서 X, index번호 X, 중복 데이터 X
    //3) Map : 요소 순서 X, index번호 대신에 key값, 중복 key X 중복 값O
    
    //kotlin의 Collection들은 모두 요소의 추가/삭제 및 변경이 불가한 종류와 가능한 종류로 나뉘어짐
    //6.2.1 요소 갯수 추가/삭제 및 변경이 모두 불가한 컬렉션 : listOf(), setOf(), mapOf()
    //6.2.2 요소 갯수 추가/삭제 및 변경이 모두 가능한 컬렉션 : mutableListOf(), mutableSetOf(), mutableMapOf()

    //6.2.1
    //1) List
    val list:List<Int> = listOf(10,20,30,20) //중복값 허용
    for (i in list) println(i)

    //값의 추가/삭제/변경에 관련된 어떤 기능메소드도 없음 - .add(), .set(), .remove()
    //배열은 갯수변경이 불가능해도 값은 바꿀 수 있었음, 값을 바꾸지 않는 것이 장점으로 사용되는 경우가 있음

    //2) Set
    val set:Set<Double> = setOf(3.14, 5.55, 2.22, 5.55) //중복데이터는 자동으로 무시됨
    for (e in set) println(e)

    //3) Map
    //3.1) Pair() 객체를 이용하여 [키-밸류] 지정
    val map:Map<String,String> = mapOf(Pair("name","Sam"), Pair("msg","Nice"))
    println("요소의 갯수 : ${map.size}")
    for ((key,value) in map) println("$key : $value")

    //3.2) to 연산자를 이용하여 키-밸류 지정
    val map2:Map<String,String> = mapOf("id" to "qwer", "pw" to "qwer1234")
    for ((k,v) in map2) println("$k : $v")

    //6.2.2 요소의 추가/삭제/변경이 모두 자유로운 MutableXXX
    //1) MutableList
    val aaaa:MutableList<Int> = mutableListOf(10,20,30)
    println("요소의 갯수 : ${aaaa.size}")
    aaaa.add(40)
    aaaa.add(0, 50)
    println("요소의 갯수 : ${aaaa.size}")
    //aaaa.set(1, 200) //1번방의 값을 200으로
    aaaa[1] = 200 //마치 배열처럼 요소값에 접근하는것을 권장
    for (e in aaaa) println(e)
    
    //println("2번방의 값 : ${aaaa.get(2)}")
    println("2번방의 값 : ${aaaa[2]}") //권장되는 방법

    //2) MutableSet
    val bbbb:MutableSet<Double> = mutableSetOf()
    println("요소의 갯수 : ${bbbb.size}")
    bbbb.add(5.55)
    bbbb.add(3.14)
    bbbb.add(5.55) //중복값은 무시됨
    println("요소의 갯수 : ${bbbb.size}")
    for (e in bbbb) println(e)
    
    //3) MutableMap
    val cccc:MutableMap<String,String> = mutableMapOf("name" to "Sam", Pair("city", "Seoul"))
    println("요소의 갯수 : ${cccc.size}")
    cccc.put("job", "student")
    println("요소의 갯수 : ${cccc.size}")
    for((k,v) in cccc) println("$k : $v")
    
    //6.2.3 별외! mutable에 익숙하지 않다면... java의 ArrayList, HashSet, HashMap에 대응되는 class 존재
     var arrList:ArrayList<Any> = arrayListOf(10, "Hello", true)
    //사용법은 java나 mutable과 동일

    var hashSet:HashSet<Any> = hashSetOf(100, "HEY")
    val hashMap:HashMap<String,Any> = hashMapOf()

}