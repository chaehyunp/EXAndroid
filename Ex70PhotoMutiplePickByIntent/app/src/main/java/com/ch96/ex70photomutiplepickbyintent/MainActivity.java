package com.ch96.ex70photomutiplepickbyintent;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.PickVisualMediaRequest;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

import android.content.ClipData;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.widget.TextView;

import com.tbuonomo.viewpagerdotsindicator.WormDotsIndicator;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    TextView tv;
    ArrayList<Uri> images = new ArrayList<>(); //1. 대량의 데이터
     MyAdapter adapter; //2. 아답터
     ViewPager2 pager; //3. 페이저
    WormDotsIndicator dotsIndicator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tv = findViewById(R.id.tv);
        pager = findViewById(R.id.pager);
        adapter = new MyAdapter(this, images);
        pager.setAdapter(adapter);

        dotsIndicator = findViewById(R.id.dot_indicator);
        dotsIndicator.attachTo(pager);

        findViewById(R.id.btn).setOnClickListener(v -> clickBtn());
        findViewById(R.id.btn2).setOnClickListener(v -> clickBtn2());
        findViewById(R.id.btn3).setOnClickListener(v -> clickBtn3());
    }

    //여러 사진 선택 결과를 돌려받는 계약 체결 대행사
    ActivityResultLauncher<Intent> imagePickLauncher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), result -> {
        if (result.getResultCode() != RESULT_CANCELED){
            //사진 uri들을 가져온 택배기사를 소환
            Intent intent = result.getData();
            ClipData clipData = intent.getClipData(); //다중 데이터 ClipData
            int size = clipData.getItemCount();
            //tv.setText(size+"");
            for(int i = 0; i < size; i++) {
                images.add(clipData.getItemAt(i).getUri());
            }
            tv.setText("선택갯수 : " + images.size());
            adapter.notifyDataSetChanged();
        }
    });

    void clickBtn() {
        Intent intent = new Intent(Intent.ACTION_OPEN_DOCUMENT).setType("image/*").putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true);
        imagePickLauncher.launch(intent);
    }

    void clickBtn2() {
        multiplePickLauncher.launch(new PickVisualMediaRequest());
    }

    ActivityResultLauncher<PickVisualMediaRequest> multiplePickLauncher = registerForActivityResult(new ActivityResultContracts.PickMultipleVisualMedia(), result -> {
        for(Uri uri : result) {//Pick"Multiple"VisualMedia() result --> List<Uri>
            images.add(uri);
        }
        tv.setText(images.size()+"");
        adapter.notifyDataSetChanged();
    });

    void clickBtn3() {
        Intent intent = new Intent(MediaStore.ACTION_PICK_IMAGES).putExtra(MediaStore.EXTRA_PICK_IMAGES_MAX, 10);
        imagePickLauncher.launch(intent);
    }
}