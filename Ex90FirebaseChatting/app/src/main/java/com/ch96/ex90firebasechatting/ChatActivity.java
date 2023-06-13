package com.ch96.ex90firebasechatting;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.inputmethod.InputMethodManager;
import android.widget.Toast;

import com.ch96.ex90firebasechatting.databinding.ActivityChatBinding;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentChange;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Map;

public class ChatActivity extends AppCompatActivity {

    ActivityChatBinding binding;
    FirebaseFirestore firestore;
    CollectionReference chatRef;
    String chatName = "MY CHAT";

    MessageAdapter adapter;

    ArrayList<MessageItem> messageItems = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityChatBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        //제목줄에 채팅방 이름 표시
        getSupportActionBar().setTitle(chatName);
        getSupportActionBar().setSubtitle("상대방이름"); //채팅방 선택화면이 있어야 가능, 현재는 DB에 전부 MY CHAT, 누구나 접속 가능

        adapter = new MessageAdapter(this, messageItems);
        binding.recycler.setAdapter(adapter);

        //Firebase Firestore 관리객체 및 참조객체 소환
        firestore = FirebaseFirestore.getInstance();
        //DB의 colletion이름을 채팅방으로 만들어서 채팅방이름으로 채팅방 저장 및 관리
        chatRef = firestore.collection(chatName);

        //'채팅방름' 컬렉션에 저장되어있는 데이터들 읽어오기
        //get은 일회용 - 한 번 가져오면 메시지가 추가되어도 변경되지 않음
        //chatRef의 데이터가 변경될떄마다 반응하는 리스너 추가
        chatRef.addSnapshotListener(new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(@Nullable QuerySnapshot value, @Nullable FirebaseFirestoreException error) {
                //매번 중복된 Document 발생, clear를 하면 양이 많아질 수록 어려워짐
                //변경된 Document만 찾아달라고 요청
                List<DocumentChange> documentChanges = value.getDocumentChanges();
                for (DocumentChange documentChange : documentChanges) {
                    //변경된 문서내역의 데이터를 촬영한 Snapshot 얻어오기
                    DocumentSnapshot snapshot = documentChange.getDocument();

                    //Document에 있는 Field 값 가져오기
                    Map<String,Object> msg = snapshot.getData();
                    //물론 모두 String으로 받아오지만 snapshot은 본인 내부의 값이 String인지 알 수 없으므로 Object로 써줌
                    String name = msg.get("name").toString();
                    String message = msg.get("message").toString();
                    String profileUrl = msg.get("profileUrl").toString();
                    String time = msg.get("time").toString();

                    //읽어온 메시지를 리스트에 추가
                    messageItems.add(new MessageItem(name, message, profileUrl, time));

                    //아답터에게 데이터가 추가되었다고 공지해야 화면이 갱신됨
                    //늘 마지막 위치에 추가됨, 괄호에 insert위치
                    adapter.notifyItemInserted(messageItems.size()-1);
                    //리사이클러뷰의 스크롤 위치가 가장 최근 메시지로
                    binding.recycler.scrollToPosition(messageItems.size()-1);
                }
                Toast.makeText(ChatActivity.this, ""+messageItems.size(), Toast.LENGTH_SHORT).show();
            }
        });
        
        binding.btn.setOnClickListener(view -> clickSend());
    }

    void clickSend() {

        //Firebase DB에 저장할 데이터들 (닉네임, 메시지, 프로필 이미지 URL, 작성시간)
        String nickName = GlobalVari.nickName;
        String message = binding.et.getText().toString();
        String profileUrl = GlobalVari.profileUrl;
        //메시지 작성 시간을 문자열 [시:분]
        Calendar calendar = Calendar.getInstance();
        String time=calendar.get(Calendar.HOUR_OF_DAY) + ":" + calendar.get(Calendar.MINUTE);
        
        //해시맵 대신에 객체를 만들어서 넣으면 객체의 참조변수의 이름 Field의 이름이 됨
        //Field에 넣을 값들을 MessageItem 객체로 만들어서 한 번에 입력
        MessageItem item = new MessageItem(nickName, message, profileUrl, time);

        //'채팅방 이름'으로 된 collection에 채팅 메시지들을 저장
        //단, 시간별순으로 정렬되도록 document의 이름은 현재시간(1970년부터 카운트된 ms)으로 지정
        chatRef.document("MSG_" + System.currentTimeMillis()).set(item);

        //다음 메시지 입력이 수월하도록 EditText에 있는 글씨 없애기
        binding.et.setText("");

        //소프트 키보드 숨기기
        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        //토큰을 가지고 있는 애가 포커스를 가지고 사용하는 것, 토큰을 가지고 있는 애로부터 토큰을 가져와야함
        imm.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(),0);
    }
}