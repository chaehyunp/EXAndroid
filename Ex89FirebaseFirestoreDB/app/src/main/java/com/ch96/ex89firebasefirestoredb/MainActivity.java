package com.ch96.ex89firebasefirestoredb;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Toast;

import com.ch96.ex89firebasefirestoredb.databinding.ActivityMainBinding;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.btnSave.setOnClickListener(v -> clickSave());
        binding.btnLoad.setOnClickListener(v -> clickLoad());
    }

    void clickSave() {
        String name = binding.etName.getText().toString();
        int age = Integer.parseInt(binding.etAge.getText().toString());
        String address = binding.etAddress.getText().toString();

        //Firestore DB에 저장[Map collection 단위로 저장]
        FirebaseFirestore firestore = FirebaseFirestore.getInstance();
        
        //"Person"이라는 이름의 컬렉션을 참조하는 참조객체 소환
        CollectionReference personRef = firestore.collection("person"); //없으면 만들고 있으면 참조만

        //Field값들을 Map으로 준비
        Map<String, Object> person = new HashMap<>(); //Object를 쓰면 아무 자료형이나 사용가능
        person.put("name", name);
        person.put("age", age);
        person.put("address", address);
        
        //personRef 참조 컬렉션에 값들 넣기
        //.document() 안에 값을 주지 않으면 랜덤한 문자열이 식별자로 지정됨(단, 랜덤값이기때문에 순서가 뒤바뀔 수 있음 -> 날짜이용하면 순서대로 가능)
//        personRef.document().set(person).addOnSuccessListener(unused -> {
//            Toast.makeText(this, "Saved", Toast.LENGTH_SHORT).show();
//        });
        //.document().set(person)의 축약기능 --> .add() - 리스너의 파라미터 다름
        personRef.add(person).addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
            @Override
            public void onSuccess(DocumentReference documentReference) {
                Toast.makeText(MainActivity.this, "Saved", Toast.LENGTH_SHORT).show();
            }
        });

    }

    void clickLoad() {
        FirebaseFirestore firestore = FirebaseFirestore.getInstance();
        CollectionReference personRef = firestore.collection("person");
        personRef.get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
            @Override
            public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                //Collection 안에 여러 개의 Document가 있기에 반복문 필요 (QuerySnapshot 안에 DocumentSnapshot이 여러 개)
                StringBuffer buffer = new StringBuffer();
                for(QueryDocumentSnapshot snapshot : queryDocumentSnapshots) {
                    Map<String, Object> person = snapshot.getData();
                    String name = person.get("name").toString(); //자료형이 Object이기때문에 String으로 가져오는것을 명시해야함
                    int age = Integer.parseInt(person.get("age").toString());
                    String address = person.get("address").toString();

                    buffer.append(name + " (" + age + ") : " + address + "\n");
                }
                binding.tv.setText(buffer.toString());
            }
        });

        //** 별외) 특정 필드값에 해당하는 데이터를 검색하고 싶다면...
        //personRef.get(); //모든 데이터
//        personRef.whereEqualTo("name", "Sam").addSnapshotListener(new EventListener<QuerySnapshot>() { //where절 구글사이트 참조
//            @Override
//            public void onEvent(@Nullable QuerySnapshot value, @Nullable FirebaseFirestoreException error) {
//                
//            }
//        });
    }
}