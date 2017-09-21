package com.example.yan.attendanceapp;

import android.Manifest;
import android.app.Activity;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.os.CountDownTimer;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import cn.bmob.sms.BmobSMS;
import cn.bmob.sms.exception.BmobException;
import cn.bmob.sms.listener.RequestSMSCodeListener;
import cn.bmob.sms.listener.VerifySMSCodeListener;

import static android.icu.lang.UCharacter.GraphemeClusterBreak.T;

public class RegisterActivity extends AppCompatActivity implements View.OnClickListener {
    private Button mSendBtn, mPinBtn;
    private EditText mPhoneEdit, mPinEdit;
    private static final int REQUEST_CODE =  1000;
    private String phoneNum, pinNum;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        int myColor = getResources().getColor(R.color.registerButton);
        Window window = this.getWindow();
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.setStatusBarColor(myColor);
        initView();
        mSendBtn.setOnClickListener(this);
        mPinBtn.setOnClickListener(this);

    }
    private void initView(){
        mSendBtn = (Button) findViewById(R.id.send_btn);
        mPinBtn = (Button) findViewById(R.id.pin_btn);
        mPhoneEdit = (EditText) findViewById(R.id.phone_edit);
        mPinEdit = (EditText) findViewById(R.id.pin_edit);
    }

    @Override
    public void onClick(View v) {
        Log.e("message", "1");
        phoneNum = mPhoneEdit.getText().toString();
        pinNum = mPinEdit.getText().toString();
        switch (v.getId()){
            case R.id.send_btn:
                if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_PHONE_STATE)
                        != PackageManager.PERMISSION_GRANTED){
                    ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_PHONE_STATE},REQUEST_CODE);
                }else {
//                    toast("动态获取权限成功");
                    sendPhone();
                }

                break;
            case R.id.pin_btn:
                Log.e("message", "5");
                if (phoneNum.length() ==0 || pinNum.length() ==0 || phoneNum.length() !=11){
                    Log.e("message", "6");
                    toast("手机号或验证码不合法");
                }
                else {
                    BmobSMS.verifySmsCode(this, phoneNum, pinNum, new VerifySMSCodeListener() {
                        @Override
                        public void done(BmobException e) {
                            if (e == null){
                               Log.e("message", "7");
                                toast("登录成功");
                            }
                            else {
                                Log.e("message", "8");
                                toast("验证码错误");
                            }
                        }
                    });
                }
                break;
        }

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode){
            case REQUEST_CODE:
                if(grantResults.length >0 &&grantResults[0]==PackageManager.PERMISSION_GRANTED){
                    //用户同意授权
                    sendPhone();
//                    toast("动态获取权限成功");
                }else{
                    //用户拒绝授权
                }
                break;
        }

    }
    private void sendPhone(){

        Log.e("message", "2");
        if (phoneNum.length() != 11){
            toast("请输入11位有效手机号码");
        }
        else{
            Log.e("message", "3");
            BmobSMS.requestSMSCode(this, phoneNum, "smsDemo", new RequestSMSCodeListener() {
                @Override
                public void done(Integer integer, BmobException e) {
                    if (e == null){
                        //发送成功
                        mSendBtn.setClickable(false);
                        mSendBtn.setBackgroundColor(Color.GRAY);
                        toast("发送成功");
                        new CountDownTimer(60000, 1000){
                            @Override
                            public void onTick(long millisUntilFinished) {
                                mSendBtn.setText(millisUntilFinished/1000+"秒");
                            }

                            @Override
                            public void onFinish() {
                                mSendBtn.setClickable(true);
                                mSendBtn.setText("重新发送");
                            }
                        }.start();
                        Log.e("message", "4");
                    }
                    else {
                        toast("验证码发送失败，请检查网络连接");
                    }
                }
            });
        }
    }

    private void toast(String msg){
        Toast.makeText(this,msg, Toast.LENGTH_SHORT).show();
    }
}
