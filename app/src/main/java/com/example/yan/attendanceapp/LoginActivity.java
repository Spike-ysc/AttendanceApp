package com.example.yan.attendanceapp;

import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.support.v4.view.ViewCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;

import com.githang.statusbar.StatusBarCompat;
import com.readystatesoftware.systembartint.SystemBarTintManager;

import cn.bmob.sms.BmobSMS;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {
    private Button loginUpButton;
    private Button registerButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        initView(); //       初始化


        loginUpButton.setOnClickListener(this);//        跳转到登录页面
        registerButton.setOnClickListener(this);//       跳转到注册页面

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.login_up_button:
                Intent intent = new Intent(LoginActivity.this, LoginUpActivity.class);
                startActivity(intent);
                break;
            case R.id.register_button:
                Intent intent2 = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(intent2);
                break;
            default:
                break;
        }
    }

    private void initView(){
//       Bmob初始化
        BmobSMS.initialize(getBaseContext(),"87588623b9a255fcf1cf1345b9962650" );

        loginUpButton = (Button) findViewById(R.id.login_up_button);
        registerButton = (Button) findViewById(R.id.register_button);


 //        功能：更改系统状态栏的颜色
//        来源：http://blog.csdn.net/zbh_1042354552/article/details/53215316
//        http://blog.csdn.net/jdsjlzx/article/details/41643587
        if (Build.VERSION.SDK_INT >=Build.VERSION_CODES.LOLLIPOP){
            int myColor = getResources().getColor(R.color.top_bar);
            Window window = this.getWindow();
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(myColor);
        }
    }

}

