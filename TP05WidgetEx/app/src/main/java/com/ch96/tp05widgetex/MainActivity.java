package com.ch96.tp05widgetex;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    ImageView icFav, icChat, icSend, icShare, img;
    ImageView dialogImg, dialogPre, dialogNext;
    int i = 0;
    int imgArray[] = {R.drawable.img_action, R.drawable.img_bath, R.drawable.img_cat, R.drawable.img_photo, R.drawable.img_rain};
    int max = imgArray.length;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        icFav = findViewById(R.id.ic_fav);
        icChat = findViewById(R.id.ic_chat);
        icSend = findViewById(R.id.ic_send);
        icShare = findViewById(R.id.ic_share);

        //이미지를 클릭하면 커스텀 다이얼로그 
        img = findViewById(R.id.cont_img);
        img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setView(R.layout.dialog);
                AlertDialog dialog = builder.create();
                dialog.show();

                dialogImg = dialog.findViewById(R.id.dialog_img);

                //이미지의 < 버튼 클릭할 경우 이전 이미지 보여주기
                dialogPre = dialog.findViewById(R.id.dialog_pre);
                dialogPre.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dialogImg.setImageResource(imgArray[i]);
                        i--;
                        if(i < 0) i = 0;
                    }
                });

                //이미지의 > 버튼 클릭할 경우 다음 이미지 보여주기
                dialogNext = dialog.findViewById(R.id.dialog_next);
                dialogNext.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dialogImg.setImageResource(imgArray[i]);
                        i++;
                        if(i >= max) i = max - 1;
                    }
                });
            }
        });

        //Fav 버튼 클릭
        icFav.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MainActivity.this, "LIKE", Toast.LENGTH_SHORT).show();
            }
        });

        //Chat 버튼 클릭
        icChat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MainActivity.this, "Leave Your Message!", Toast.LENGTH_SHORT).show();
            }
        });

        //Send 버튼 클릭
        icSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MainActivity.this, "Send DM", Toast.LENGTH_SHORT).show();
            }
        });

        //Share 버튼 클릭
        icShare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MainActivity.this, "Share this post", Toast.LENGTH_SHORT).show();
            }
        });
    }
}