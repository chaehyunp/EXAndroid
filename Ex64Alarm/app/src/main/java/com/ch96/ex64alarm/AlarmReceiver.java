package com.ch96.ex64alarm;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

public class AlarmReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        Toast.makeText(context, "알람 받았어용🔔", Toast.LENGTH_SHORT).show();
        Log.i("EX64", "🔔");

        //보통은 이곳에서 새로운 액티비티를 실행함 --> 이것도 막힘
        //그래서 통상적으로는 Notification을 보여줌
    }
}
