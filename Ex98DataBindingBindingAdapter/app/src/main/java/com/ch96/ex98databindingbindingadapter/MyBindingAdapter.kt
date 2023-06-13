package com.ch96.ex98databindingbindingadapter

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import java.util.Date

//기존 뷰들에 없는 새로운 xml 속성을 연결하는 기능메소드를 가지는 객체 [ 보통 static 메소드를 가진 class 사용 ] - 코틀린에는 static이 없음 : @JvmStatic 이용
object MyBindingAdapter { //object 키워드 - 싱글톤 패턴(여러 번 new를 해도 객체가 또 생길 수 없음)으로 객체를 만들어주는 키워드
    
    //1) 이미지뷰에 새로운 xml 속성 만들기 - [ 속성명 : imageUrl ]
    @BindingAdapter("imageUrl")
    @JvmStatic
    fun loadImage(view:ImageView, url:String){ //메소드명은 개발자 마음대로, 파라미터가 중요함 [ 뷰타입, 속성값 ]
        Glide.with(view.context).load(url).into(view)
    }

    //2) 리사이클러뷰에 리스트를 설정할 수 있는 새로운 xml 속성 만들기 - [ 속성명 : itemList ]
    @BindingAdapter("itemList")
    @JvmStatic
    fun setItemList(view:RecyclerView, items:Any){ //MutableList<Item> 컬렉션 타입은 사용 불가 - 실행시 에러
        view.adapter = RecyclerItemAdapter(view.context, items as List<Item>)
    }

}