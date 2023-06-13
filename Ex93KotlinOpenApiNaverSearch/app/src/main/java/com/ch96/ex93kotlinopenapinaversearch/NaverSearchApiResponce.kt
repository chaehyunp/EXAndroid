package com.ch96.ex93kotlinopenapinaversearch


//Int로 쓸 경우, String/null 값일 경우 ERROR
//String으로 받으면 Int형이어도 문자형태로 받고 null이어도 에러나지 않음
data class NaverSearchApiResponce constructor(var total:Int, var display:Int, var items:MutableList<ShoppigItem>)

//아이템 1개의 클래스
data class ShoppigItem (
    var title:String,
    var link:String,
    var image:String,
    var lprice:String, //읽어올 Integer 값이 빈 값으로 오면 ERROR남 --> String으로 받기
    var hprice:String,
    var mallName:String
    
    //응답값이 많지만 필요한것만 사용
)