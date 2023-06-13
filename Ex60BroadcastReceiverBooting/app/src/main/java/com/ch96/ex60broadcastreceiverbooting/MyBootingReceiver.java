package com.ch96.ex60broadcastreceiverbooting;

import android.app.Notification;
import android.app.NotificationManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.util.Log;

import androidx.core.app.ActivityCompat;
import androidx.core.app.NotificationChannelCompat;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import java.nio.channels.Channel;

//4대 컴포넌트는 반드시 Manifest에 등록

public class MyBootingReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        //Android 13버전부터 액티비티가 없으면 Toast의 발동이 제한됨
        Log.i("Ex60", "Booting Receive");

        //Android N 버전(api 25)부터 부팅완료를 들으려면 앱을 설치한 후 적어도
        //사용자가 1회 직접 런처화면(앱목록 아이콘)에서 아이콘을 클릭하여 실행한 이력이 있는 앱만 부팅 완료를 들을 수 있음
        //Activity 없이 Receiver만으로도 어플리케이션을 만들 수 있음 --> 해커들이 악용 --> 따라서 사용자가 이 앱의 존재를 알아야만 리시브할 수 있도록 한 것

        //부팅이 완료되면 MainActivity 화면이 실행되도록
        String action = intent.getAction(); //action은 여러 개 등록이 가능, 어떤 action 값인지 가져올 수 있음
        if (action.equals(Intent.ACTION_BOOT_COMPLETED)) {

            //Android 10 버전(api 29)부터 리시버에서 직접 액티비티 실행하는 것을 금지함
            //이를 이용해서 앱에 대한 사용자 접근을 늘리고 광고함 가지가지한다...
            //대신 알림(Notification)을 통해 사용자에게 신호를 주고 액티비티를 실행할지 여부를 선택하도록 변경
            if (Build.VERSION.SDK_INT >= 29) {
                NotificationManagerCompat manager = NotificationManagerCompat.from(context);

                //알림객체를 만들어주는 Builder 필요
                NotificationChannelCompat channel = new NotificationChannelCompat.Builder("ch01", NotificationManagerCompat.IMPORTANCE_HIGH).setName("Ex60").build();
                manager.createNotificationChannel(channel);
                NotificationCompat.Builder builder = new NotificationCompat.Builder(context, "ch01");

                builder.setSmallIcon(R.drawable.ic_noti);
                builder.setContentTitle("부팅 완료");
                builder.setContentText("부팅이 완료되었습니다.");

                //Android 13부터 알림에 대한 동적 퍼미션 필요
                if (ActivityCompat.checkSelfPermission(context, android.Manifest.permission.POST_NOTIFICATIONS) != PackageManager.PERMISSION_GRANTED) {
                    return;
                } //동적퍼미션 하려면 intent 어쩌고 또 막 뭐 해야함 생략
                manager.notify(100, builder.build());

            } else {
                Intent i = new Intent(context, MainActivity.class); //파라미터로 받은 intent는 방송용, Activity를 얻을 intent를 생성
                i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK); //이 앱에서 액티비티가 처음 실행되는거라면 필요한 설정
                context.startActivity(i); //start는 운영체제의 능력 Receiver는 가지고 있지 않으므로 context에게 부탁
                //Backstack이 없으면 (receiver만 실행되었기때문에 activity가 없어서 백스택에 쌓이지 않음) 실행 안됨 --> FLAG_ACTIVITY_NEW_TASK 필요
            }


        }

    }
}
