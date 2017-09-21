package com.example.yan.attendanceapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputType;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.util.Log;
import android.view.Window;
import android.view.WindowManager;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Toast;

public class LoginUpActivity extends AppCompatActivity {
    private EditText passwordEdit;
    private CheckBox passwordCheckbox;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_up);
//        功能：更改系统状态栏的颜色
//        来源：http://blog.csdn.net/zbh_1042354552/article/details/53215316
//        http://blog.csdn.net/jdsjlzx/article/details/41643587
        int myColor = getResources().getColor(R.color.loginButton);
        Window window = this.getWindow();
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.setStatusBarColor(myColor);

        //功能：显示和隐藏密码
        //来源：http://www.jb51.net/article/90004.htm
        passwordEdit = (EditText) findViewById(R.id.login_password);
        passwordCheckbox = (CheckBox) findViewById(R.id.password_checkbox);
        passwordCheckbox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked){
//                    passwordEdit.setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
                    passwordEdit.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                }else {
//                    passwordCheckbox.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                    passwordEdit.setTransformationMethod(PasswordTransformationMethod.getInstance());
                }
            }
        });
    }
}
