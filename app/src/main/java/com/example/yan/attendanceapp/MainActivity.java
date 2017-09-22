package com.example.yan.attendanceapp;

import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.widget.Toast;

public class MainActivity extends BaseActivity {
    private long mExitTime;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
//点击两次返回
//    @Override
//    public boolean onKeyDown(int keyCode, KeyEvent event) {
//        if (keyCode == KeyEvent.KEYCODE_BACK&& event.getRepeatCount() ==0){
//            exit();
//            return false;
//        }
//        return super.onKeyDown(keyCode, event);
//    }
//
//    private void exit(){
//        if ((System.currentTimeMillis() -mExitTime) > 2000){
//
//            Toast.makeText(getApplicationContext(), "再按一次", Toast.LENGTH_SHORT).show();
//            mExitTime = System.currentTimeMillis();
//        }else {
//            finish();
//            System.exit(0);
//        }
//    }
}
