package com.example.yan.attendanceapp;

import android.content.Intent;
import android.os.Build;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import cn.bmob.sms.BmobSMS;
import cn.bmob.sms.exception.BmobException;
import cn.bmob.sms.listener.VerifySMSCodeListener;

import static android.icu.lang.UCharacter.GraphemeClusterBreak.T;

public class Register2Activity extends BaseActivity {
    private ImageView mBackImg;
    private Button mNext, mAgain;
    private EditText mPinEdit;
    private String phone, password, pin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register2);

        initView();
        Intent intent = getIntent();
        phone = intent.getStringExtra("phone");
        password = intent.getStringExtra("password");
//        倒计时按钮功能
        new CountDownTimer(60000, 1000){
            @Override
            public void onTick(long millisUntilFinished) {
                mAgain.setText(millisUntilFinished/1000+"秒");
            }

            @Override
            public void onFinish() {
                mAgain.setClickable(true);
                mAgain.setText("重新发送");
            }
        }.start();
        Log.e("message", "4");

//        检验验证码是否正确
        mNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pin  = mPinEdit.getText().toString();
//                Toast.makeText(Register2Activity.this, "phone:"+phone+"\npassword:"+password,Toast.LENGTH_SHORT).show();
                Log.e("message", "5");
                if (pin.length() != 6){
                    Log.e("message", "6");
                    toast("验证码不合法");
                }
                else {

//                    BmobSMS.verifySmsCode(Register2Activity.this, phone, pin, new VerifySMSCodeListener() {
//                        @Override
//                        public void done(BmobException e) {
//                            if (e == null){
//                                Log.e("message", "7");
//                                toast("登录成功");
//                                Intent intent = new Intent(Register2Activity.this, MainActivity.class);
//                                startActivity(intent);
//                            }
//                            else {
//                                Log.e("message", "8");
//                                toast("验证码错误");
//                            }
//                        }
//                    });
                    Intent intent = new Intent(Register2Activity.this, Register3Activity.class);
                    startActivity(intent);
                }
            }
        });

//        返回上一页功能
        mBackImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }

    private void initView(){
        mBackImg = (ImageView) findViewById(R.id.back_icon);
        mNext = (Button) findViewById(R.id.next);
        mAgain = (Button) findViewById(R.id.again);
        mPinEdit = (EditText) findViewById(R.id.pin_edit);
        if (Build.VERSION.SDK_INT >=Build.VERSION_CODES.LOLLIPOP){
            int myColor = getResources().getColor(R.color.registerButton);
            Window window = this.getWindow();
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(myColor);
        }
    }
    private void toast(String msg){
        Toast.makeText(this,msg, Toast.LENGTH_SHORT).show();
    }
}
