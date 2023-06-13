package com.ch96.ex92kotlinrecyclerview

//메소드 영역에 쓸 것이 없으면 중괄호 생략 가능
//data class : 데이터만 저장하는 목적의 class - 일반 클래스와 다르게 자동으로 equals()할때 객체주소를 비교하지 않고 멤버값들을 비교해주도록 설계된 클래스
//단, 주생성자의 멤버만 비교 (ex. 다음줄에 다른 String을 만들면 걔만 빼고 비교됨)
//equals() 모든 객체가 가지고 있는 메소드
data class Item constructor(var name:String, var msg:String, var imgId:Int)