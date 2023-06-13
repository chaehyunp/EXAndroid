package com.ch96.ex90firebasechatting;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

public class MessageAdapter extends RecyclerView.Adapter<MessageAdapter.VH> {

    Context context;
    ArrayList<MessageItem> messageItems;
    final int TYPE_MY = 0;
    final int TYPE_OTHER = 1;
    //숫자로만 0,1을 써주면 의미를 알기 어렵기 때문에 파이널 상수를 이용

    public MessageAdapter(Context context, ArrayList<MessageItem> messageItems) {
        this.context = context;
        this.messageItems = messageItems;
    }

    //리사이클러뷰의 항목뷰(ItemView)가 경우에 따라 다른 모양으로 보여야 할때 사용하는 콜백 메소드
    //이 메소드에서 해당 position에 따른 식별값(ViewType 번호 - int)을 정하여 리턴하면
    //그 값이 onCreateViewHolder() 메소드의 두 번째 파라미터에 전달됨
    //onCreateViewHolder() 메소드 안에서 그 값에 따라 다른 xml 문서를 inflate하면 됨
    @Override
    public int getItemViewType(int position) {
        if (messageItems.get(position).name.equals(GlobalVari.nickName)) return TYPE_MY;
        else return TYPE_OTHER;
        //viewType이 my인지 other인지 리턴

    }

    @NonNull
    @Override
    public VH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) { //viewType에 따라 뷰홀더를 다르게 줄 수 있음
        
        View itemView = null; //아직 my인지 other인지 모르므로 null;
        //서버의 닉네임과 디바이스에 저장되어있는 닉네임(GlobalVari.nickName)이 같으면 my --> getItemViewType()에 이어서 작성
        if (viewType == TYPE_MY) itemView = LayoutInflater.from(context).inflate(R.layout.my_messagebox, parent, false);
        else itemView = LayoutInflater.from(context).inflate(R.layout.other_messagebox, parent, false);
        //실제로는 3가지 - my, other, 날짜별 구분선

        return new VH(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull VH holder, int position) {
        MessageItem item = messageItems.get(position);

        holder.tvName.setText(item.name);
        holder.tvMsg.setText(item.message);
        holder.tvTime.setText(item.time);
        Glide.with(context).load(item.profileUrl).into(holder.civ);

    }

    @Override
    public int getItemCount() {

        return messageItems.size();
    }

    class VH extends RecyclerView.ViewHolder {
        //메시지 타입에 따라 뷰가 달라서 바인딩 클래스를 고정할 수 없음
        //MyMessageboxBinding Mybinding;
        //OtherMessageboxBinding otherBinding;
        //ViewHolder를 2개 만들어서 사용하기도 함 [MyVH, OtherVH]
        //홀더를 두 개 만들면 onBind에서도 분기처리가 필요 --> 뷰바인딩을 쓰지 않고 제작
        //쓰지 않을건데 이미 뷰바인딩은 설계되어있음 --> \쓰지 않을 xml에 tools:viewBindingIgnore="true"

        CircleImageView civ;
        TextView tvName;
        TextView tvMsg;
        TextView tvTime;

        public VH(@NonNull View itemView) {
            super(itemView);

            civ = itemView.findViewById(R.id.civ);
            tvName = itemView.findViewById(R.id.tv_name);
            tvMsg = itemView.findViewById(R.id.tv_msg);
            tvTime = itemView.findViewById(R.id.tv_time);

        }
    }
}
