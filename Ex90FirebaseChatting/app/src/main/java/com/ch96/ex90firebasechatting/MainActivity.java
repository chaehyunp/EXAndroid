package com.ch96.ex90firebasechatting;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.ch96.ex90firebasechatting.databinding.ActivityMainBinding;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;
    //프로필 이미지 Uri
    Uri imgUri = null;
    boolean isFirst = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.civ.setOnClickListener(v -> clickCiv());
        binding.btn.setOnClickListener(v -> clickBtn());
        
        //디바이스에 저장되어있는 로그인 정보(profile)가 있는지 확인
        //SharedPreferences에 저장되어 있는 닉네임, 프로필 이미지가 있다면 읽어와라
        loadData();

        if(GlobalVari.nickName!=null) { //저장되어 있는 정보가 있다면 불러와라
            binding.et.setText(GlobalVari.nickName);
            Glide.with(this).load(GlobalVari.profileUrl).into(binding.civ);

            isFirst = false;
        }
    }

    void loadData() {
        SharedPreferences pref = getSharedPreferences("profile", MODE_PRIVATE);
        GlobalVari.nickName = pref.getString("nickName", null);
        GlobalVari.profileUrl = pref.getString("profileUrl", null);
    }

    void clickCiv() {
        Intent intent = new Intent(MediaStore.ACTION_PICK_IMAGES);
        resultLauncher.launch(intent);

    }

    ActivityResultLauncher<Intent> resultLauncher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), result -> {
        if(result.getResultCode()==RESULT_CANCELED) return;

        imgUri = result.getData().getData();
        Glide.with(this).load(imgUri).into(binding.civ);
    });

    void clickBtn() {
        //처음 실행하면 저장하고 아니면 넘어감
        if (isFirst) {
            //civ의 이미지 : 내 핸드폰에 있는 이미지는 다른 사람들이 볼 수 없음 --> 사진을 저장소에 업로드해주어야함
            //채팅화면 가기 전에 프로필 이미지와 닉네임을 서버에 저장
            saveData();
        } else startActivity(new Intent(this, ChatActivity.class));
    }

    void saveData() {
        //이미지를 선택하지 않으면 채팅불가하도록...
        if(imgUri==null) {
            Toast.makeText(this, "이미지를 선택해주세요", Toast.LENGTH_SHORT).show();
            return;
        }

        //EditText에 있는 닉네임 가져오기
        //닉네임과 이미지는 계속 사용됨
        //Application이 Activity를 가지고 있으므로 Application에 변수를 만들거나
        //C언어에 있는 Global Variable (전역변수) --> But java에는 전역변수가 없음
        //R장부는 어느 Activity에서든 부를 수 있었음 (static으로 있기 때문에) 
        //static으로 정적변수를 만들면 객체를 만들지않고도 사용이 가능 --> 이를 이용하여 전역변수처럼 사용 (GlobalVari로 만듦)
        //온전하게 좋은 방법을 아님, 메모리의 상태에 따라서 static은 남아있을 수 있음
        //String nickName = binding.et.getText().toString();
        GlobalVari.nickName = binding.et.getText().toString();
        
        //이미지 업로드가 오래 걸리기 때문에 FirebaseStorage에 먼저 업로드
        FirebaseStorage storage = FirebaseStorage.getInstance();
        //참조위치명이 중복되지 않도록 날짜를 이용
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
        String imgRefName = "IMG_" + sdf.format(new Date());
        StorageReference imgRef = storage.getReference("profileImage/" + imgRefName);

        //이미지 업로드
        imgRef.putFile(imgUri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                //업로드가 성공되었으니 업로드된 파일의 [다운로드 URL]주소를 얻어오도록
                imgRef.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                    @Override
                    public void onSuccess(Uri uri) {
                        GlobalVari.profileUrl = uri.toString();
                        Toast.makeText(MainActivity.this, "유저 저장 완료", Toast.LENGTH_SHORT).show();

                        //서버로 보내는 것
                        //1. 서버의 Firestore DB에 닉네임과 이미지 url저장
                        FirebaseFirestore firestore = FirebaseFirestore.getInstance();
                        //'profiles'라는 이름의 컬렉션 참조객체 소환
                        CollectionReference profileRef = firestore.collection("profiles");

                        //닉네임을 Document 이름으로 정하고 Field '값'으로 이미지 경로 url 저장
                        Map<String, Object> profile = new HashMap<>();
                        profile.put("profileUrl", GlobalVari.profileUrl);
                        profileRef.document(GlobalVari.nickName).set(profile);

                        //스마트폰으로 보내는 것
                        //2. 앱을 처음 실행할때만 닉네임과 사진을 입력하도록 하기 위해 디바이스에 영구적으로 데이터 저장 [SharedPreference로 저장]
                        SharedPreferences pref = getSharedPreferences("profile", MODE_PRIVATE); //모드는 프라이빗만 가능
                        SharedPreferences.Editor editor = pref.edit();
                        editor.putString("nickName", GlobalVari.nickName);
                        editor.putString("profileUrl", GlobalVari.profileUrl);

                        editor.commit(); //완료 명령 꼭 필요함

                        //저장이 완료되었으니 채팅화면으로 이동
                        Intent intent = new Intent(MainActivity.this, ChatActivity.class);
                        startActivity(intent);

                        //현재액티비티 종료하기(뒤로가기를 눌러도 돌아오지않도록)
                        finish();
                    }
                });
                    }
                });

    }
}