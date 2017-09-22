package com.example.yan.attendanceapp;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.os.Build;
import android.os.CountDownTimer;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import cn.bmob.sms.BmobSMS;
import cn.bmob.sms.exception.BmobException;
import cn.bmob.sms.listener.RequestSMSCodeListener;
import cn.bmob.sms.listener.VerifySMSCodeListener;

import static android.R.attr.y;
import static android.icu.lang.UCharacter.GraphemeClusterBreak.T;

public class RegisterActivity extends BaseActivity implements View.OnClickListener {
    private Button mSendBtn, mPinBtn, mSendAndNext;
    private EditText mPhoneEdit, mPinEdit, mPhoneText, mPasswordEdit;
    private static final int REQUEST_CODE =  1000;
    private String phoneNum, pinNum, phone, password;
    private ImageView mBackImg, mDelectImg, mShowPwdImg;
    private CheckBox mShowCheckBox;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        initView();

        mSendAndNext.setOnClickListener(this);  //获取手机号和密码检验否正确并发送信息
                                                // 注意:在Android6.0以上的版本需要动态获取权限
        mDelectImg.setOnClickListener(this);    //清除按钮功能
        mBackImg.setOnClickListener(this);      //返回上个页面功能



//        显示和隐藏密码
        mShowCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked){
                    mPasswordEdit.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                    mPasswordEdit.setSelection(mPasswordEdit.length());
                }else {
                    mPasswordEdit.setTransformationMethod(PasswordTransformationMethod.getInstance());
                    mPasswordEdit.setSelection(mPasswordEdit.length());
                }
            }
        });


        //监控输入框内容，显示和隐藏清除按钮
        mPhoneText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.length() != 0){
                    mDelectImg.setVisibility(View.VISIBLE);
                }else {
                    mDelectImg.setVisibility(View.INVISIBLE);
                }
            }
            @Override
            public void afterTextChanged(Editable s) {}
        });

    }

//    初始化
    private void initView(){
        mPhoneEdit = (EditText) findViewById(R.id.phone_edit);
        mPinEdit = (EditText) findViewById(R.id.pin_edit);
        mBackImg = (ImageView) findViewById(R.id.back_icon);
        mPhoneText = (EditText) findViewById(R.id.phone_text);
        mDelectImg = (ImageView) findViewById(R.id.delete_phone);
        mPasswordEdit = (EditText) findViewById(R.id.register_password);
        mShowCheckBox = (CheckBox) findViewById(R.id.password_checkbox);
        mSendAndNext = (Button) findViewById(R.id.send_and_next);

        if (Build.VERSION.SDK_INT >=Build.VERSION_CODES.LOLLIPOP){
            int myColor = getResources().getColor(R.color.registerButton);
            Window window = this.getWindow();
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(myColor);
        }
    }

//    点击事件
    @Override
    public void onClick(View v) {
        Log.e("message", "1");
        phone = mPhoneText.getText().toString();
        password = mPasswordEdit.getText().toString();
        switch (v.getId()){
            case R.id.send_and_next:

//                动态获取权限
                if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_PHONE_STATE)
                        != PackageManager.PERMISSION_GRANTED){
                    ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_PHONE_STATE},REQUEST_CODE);
                }else {
//                    toast("动态获取权限成功");
                    send();
                }
                break;
            case R.id.back_icon:
                finish();
                break;
            case R.id.delete_phone:
                mPhoneText.setText("");
                break;
            default:
                break;
        }

    }

//    动态获取权限
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode){
            case REQUEST_CODE:
                if(grantResults.length >0 &&grantResults[0]==PackageManager.PERMISSION_GRANTED){
                    //用户同意授权
                    send();
//                    toast("动态获取权限成功");
                }else{
                    //用户拒绝授权
                }
                break;
        }

    }

//    检测手机号和密码是否正确,并发送短信
    private void send(){
        Log.e("message", "2");
//      来源：http://blog.csdn.net/qq_24956515/article/details/50910513
        String telRegex = "[1][34578]\\d{9}";//"[1]"代表第1位为数字1，"[358]"代表第二位可以为3、5、8中的一个，"\\d{9}"代表后面是可以是0～9的数字，有9位。

        if (phone.length() != 11 || !phone.matches(telRegex)){
            toast("请输入11位有效手机号码");
        }
        else if (password.length()<6){
            toast("密码格式不正确");

        }
        else{
            Log.e("message", "3");
//            BmobSMS.requestSMSCode(this, phone, "smsDemo", new RequestSMSCodeListener() {
//                @Override
//                public void done(Integer integer, BmobException e) {
//                    if (e == null){
//                        //发送成功
//
//                        toast("发送成功");
//                        Intent intent = new Intent(RegisterActivity.this, Register2Activity.class);
//                        intent.putExtra("phone",phone);
//                        intent.putExtra("password",password);
//                        startActivity(intent);
//
//                    }
//                    else {
//                        toast("验证码发送失败，请检查网络连接");
//                    }
//
//                }
//            });

            Intent intent = new Intent(RegisterActivity.this, Register2Activity.class);
            intent.putExtra("phone",phone);
            intent.putExtra("password",password);
            startActivity(intent);
        }

    }



    private void toast(String msg){
        Toast.makeText(this,msg, Toast.LENGTH_SHORT).show();
    }
}
