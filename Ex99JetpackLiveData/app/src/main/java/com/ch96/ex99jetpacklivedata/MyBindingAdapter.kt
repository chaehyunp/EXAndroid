package com.ch96.ex99jetpacklivedata

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView

object MyBindingAdapter {

    //리사이클러뷰에서 리스트데이터를 설정하는 새로운 속성 [ 속성명 : itemList ]
    //코틀린 어노테이션 해독기 'kotlin-kapt' 필요
    @BindingAdapter("itemList")
    @JvmStatic
    fun setItemList(view:RecyclerView, items:Any){
        view.adapter=RecyclerItemAdapter(items as List<Item>)
    }
}