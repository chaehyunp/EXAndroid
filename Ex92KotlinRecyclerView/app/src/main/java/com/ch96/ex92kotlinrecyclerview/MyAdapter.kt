package com.ch96.ex92kotlinrecyclerview

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.app.ActivityOptionsCompat
import androidx.core.util.Pair
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.bumptech.glide.Glide


class MyAdapter constructor(var context: Context, var items:MutableList<Item>):Adapter<MyAdapter.VH>() {

    inner class VH(itemView: View):ViewHolder(itemView) {
        //ViewHolder itemView 받아야함 - VH한테 받아야함 - 생성자로 받아오기 VH constructor 생략, itemView = CardView
        val tvName:TextView by lazy { itemView.findViewById(R.id.tv_name) }
        val tvMsg:TextView by lazy { itemView.findViewById(R.id.tv_msg) }
        val iv:ImageView by lazy { itemView.findViewById(R.id.iv) }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
        var itemView:View = LayoutInflater.from(context).inflate(R.layout.recycler_item, parent, false)
        return VH(itemView)
    }

//    override fun getItemCount(): Int {
//        return items.size
//    }

    //함수의 단순화 - return 실행문을 = (할당연산자)로 단순화
    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: VH, position: Int) {
        //var item:Item = items.get(position)
        //코틀린은 리스트의 get() 대신 배열처럼 []권장
        var item:Item = items[position]

        holder.tvName.setText(item.name)
        //코틀린은 get/set() 권하지 않음
        holder.tvMsg.text = item.msg

        Glide.with(context).load(item.imgId).into(holder.iv)

        //아이템뷰를 클릭했을때 화면(activity)이동
        holder.itemView.setOnClickListener {
            val intent:Intent = Intent(context, ItemDetailActivity::class.java)
            intent.putExtra("name", item.name)
            intent.putExtra("msg", item.msg)
            intent.putExtra("imgId", item.imgId)

            //액티비티 전환시에 뷰들에 효과 주기 - .ActivityOptions
            val options:ActivityOptionsCompat = ActivityOptionsCompat.makeSceneTransitionAnimation(context as MainActivity, Pair(holder.iv, "iii"))
            //context의 능력을 사용하기 위해 Activity를 전달받았음
            //Pair() 객체 : 전환효과를 줄 뷰에게 별칭 연결, 같은 별칭을 특정뷰에게 주면 둘이 연결됨
            context.startActivity(intent, options.toBundle())
        }
    }

}