package com.ch96.ex58notification;

import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContract;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.AudioAttributes;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.btn).setOnClickListener(view -> {

            //Android13(api 33)부터 알림에 대한 동적 permission이 추가됨 --> AndroidManifest.xml
            //이 앱이 알림에 대한 퍼미션을 허용한 상태인지 체크
            //checkSelfPermission 버전이 업그레이드되면서 사용이 안됨 --> compatibility 호환성 버전 이용
            //리턴값 허가O(PERMISSION_GRANTED) 0, 허가X -1(PackageManager.PERMISSION_DENIED)
            int checkResult = ContextCompat.checkSelfPermission(this, Manifest.permission.POST_NOTIFICATIONS);
            if(checkResult == PackageManager.PERMISSION_DENIED) { //체크 결과가 거부되어있을 경우
                //알림 허용을 요청하는 다이얼로그를 보이기 --> 개발자의 악의적인 의도를 배제하기 위해서 직접 다이얼로그를 만들어 띄울 수 없음
                //requestPermissions(); //이전 방식
                //permission 요청 결과를 받아주는 대행사 객체 이용
                permissionResultLauncher.launch(Manifest.permission.POST_NOTIFICATIONS);
                return;
            }

            //알림(Notification)을 관리하는 관리자객체 소환
            NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
            //getSyStemService의 경우 Context내에 여러 기능이 있어 리턴되는 값을 알 수 없기때문에 Object가 리턴됨 --> 형변환 필요

            //3. 알림객체를 생성해주는 건축가(Builder)객체 생성(Compat 호환성버전)
            NotificationCompat.Builder builder = null;
            //Builder를 생성하는 문법이 26버전(Oreo)부터 변경됨
            //알림채널 개념 도입됨, 26버전 이상의 스마트폰에서는 알림채널객체를 생성해야하고 그 이전버전에서는 알림채널객체 생성없이 builder를 만들어야함 제기랄
            //os의 Build에게 버전을 물어봐야함 --> "버전에 따른 분기코드"
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                //알림 채널 객체 생성
                NotificationChannel channel = new NotificationChannel("ch01", "Ex58 channel", NotificationManager.IMPORTANCE_HIGH); //IMPORTANCE에 따라 소리/팝업/상태창에 놓여지는 순서가 달라짐

                //알림 사운드, 진동
                //Uri soundUri = RingtoneManager.getActualDefaultRingtoneUri(this, RingtoneManager.TYPE_NOTIFICATION); //기본 벨소리
                Uri soundUri = Uri.parse("android.res://" + getPackageName() + "/" + R.raw.get_gem);
                //Uri를 통한 경로로 받아서 만들어줘야함, android.res: 고정되어있는 식별자 + package 이름 + R장부 고유번호 (결합연산자로 써주면 좋음)
                channel.setSound(soundUri, new AudioAttributes.Builder().build()); //예전에는 여기서 음질을 죽였으나 그냥 사용하겠다!

                //진동은 사용자 정적 퍼미션 요구됨 (Manifest에 등록만 하면 됨)
                channel.setVibrationPattern(new long[]{0,2000,1000,3000});

                //매니저에게 채널을 등록하기 전에 사운드 작업
                notificationManager.createNotificationChannel(channel);
                builder = new NotificationCompat.Builder(this, "ch01");

            } else {
                builder = new NotificationCompat.Builder(this, ""); //채널이 없으니 아이디도 빈 문자열로(null 안됨)

                Uri soundUri = Uri.parse("android.res://" + getPackageName() + "/" + R.raw.get_gem);
                builder.setSound(soundUri);
            }

            //builder에게 알림에 관련된 설정
            //상태 표시줄에 보이는 아이콘
            builder.setSmallIcon(R.drawable.ic_noti);
            //상태바를 드래그하여 아래로 내리면 보이는 알림창(확장상태바)의 설정
            builder.setContentTitle("Title");
            builder.setContentText("message...");

            Resources res = getResources();
            Bitmap bm = BitmapFactory.decodeResource(res, R.drawable.zzang_eottae); //파라미터에 리소스 객체 필요
            builder.setLargeIcon(bm); //파라미터가 리소스가 아닌 비트맵, java에서는 png를 알아들을 수 없음 Bitmap객체한테 해독을 해서 받아와야함

            //알림창을 클릭했을때 새로운 화면(Activity)가 실행되도록
            Intent intent = new Intent(this, SecondActivity.class);
            //Intent 객체에게 바로 실행하지말고 잠시 보류해달라고 요청 --> Pending intent 보류중인 인텐트 객체 생성
            PendingIntent pendingIntent = PendingIntent.getActivity(this, 10, intent, PendingIntent.FLAG_IMMUTABLE); //request code 보고서에 번호 매겨놓는것과 같음
            builder.setContentIntent(pendingIntent);

            //알림창을 클릭하여 화면이 변경되면 상태표시줄에 노티가 사라지도록
            //intent가 있을 때만 (터치해서 화면이 변경되었을때만) 사용 가능
            builder.setAutoCancel(true);

            //알림자 스타일 꾸미기 (다른 스타일의 종류 개발자 사이트 통해 확인)
            NotificationCompat.BigPictureStyle style = new NotificationCompat.BigPictureStyle(); //파라미터에 빌더를 주면 setStyle 필요없음, 안주면 setStyle 필요
            style.bigPicture(BitmapFactory.decodeResource(res, R.drawable.zzang_buyitforcuteme));
            builder.setStyle(style);
            //알림창의 클릭 액션에 의해 실행될 화면에 여러 개일 때 사용하는 기능
            builder.addAction(R.drawable.ic_noti, "setting", pendingIntent); //원래는 각각 다른 pendingIntent 필요 급하게 예제 하느라 재사용...
            builder.addAction(R.drawable.ic_noti, "information", pendingIntent);

            //2. 알림객체 생성 : 다이얼로그처럼 Builder 에게 요청해야함 --> Builder 객체 필요
            Notification notification = builder.build();

            //1. 알림 매니저에게 알림을 보이도록 요청 --> 알림객체 필요
            notificationManager.notify(100, notification);

        });

    }

    //퍼미션 요청 결과를 받아오는 작업을 대행해주는 객체 생성 및 등록
    ActivityResultLauncher<String> permissionResultLauncher = registerForActivityResult(new ActivityResultContracts.RequestPermission(), new ActivityResultCallback<Boolean>() {
        @Override
        public void onActivityResult(Boolean result) {
            if (result) Toast.makeText(MainActivity.this, "알림을 허용했습니다.", Toast.LENGTH_SHORT).show();
            else Toast.makeText(MainActivity.this, "알림을 거부했습니다.", Toast.LENGTH_SHORT).show();
        }
    });

}