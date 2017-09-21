package com.example.yan.attendanceapp;

import android.content.Intent;
import android.graphics.Color;
import android.support.v4.view.ViewCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;

import com.githang.statusbar.StatusBarCompat;

import cn.bmob.sms.BmobSMS;

public class LoginActivity extends AppCompatActivity {
    private Button loginUpButton;
    private Button registerButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        BmobSMS.initialize(getBaseContext(),"87588623b9a255fcf1cf1345b9962650" );


//        功能：更改系统状态栏的颜色
//        来源：http://blog.csdn.net/zbh_1042354552/article/details/53215316
//        http://blog.csdn.net/jdsjlzx/article/details/41643587
        int myColor = getResources().getColor(R.color.top_bar);
        Window window = this.getWindow();
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.setStatusBarColor(myColor);
        loginUpButton = (Button) findViewById(R.id.login_up_button);
        registerButton = (Button) findViewById(R.id.register_button);
        loginUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, LoginUpActivity.class);
                startActivity(intent);

            }
        });
        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(intent);
            }
        });




    }
}

