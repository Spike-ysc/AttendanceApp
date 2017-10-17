package com.example.yan.attendanceapp;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

//参考链接http://blog.csdn.net/yezhenxu1992/article/details/47953305
public class ForceOfflineReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        ActivityCollector.finishAll();
        Intent intent1 = new Intent(context, MainActivity.class);
        intent1.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);    //这里之前为intent Android5.0报错，6.0却不报错
        context.startActivity(intent1);
    }
}
