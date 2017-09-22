package com.example.yan.attendanceapp;

import android.content.Intent;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.InputType;
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

public class LoginUpActivity extends AppCompatActivity implements View.OnClickListener{
    private EditText mPhoneEdit, passwordEdit;
    private CheckBox passwordCheckbox;
    private ImageView mBackImg, mdeleteImg;
    private Button mLoginUpBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_up);

        initView();


        //监控输入框内容，显示和隐藏清除按钮
        mPhoneEdit.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
            @Override
            //文字改变时触发的事件
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.length() != 0){
                    mdeleteImg.setVisibility(View.VISIBLE);
                }else {
                    mdeleteImg.setVisibility(View.INVISIBLE);
                }
            }
            @Override
            public void afterTextChanged(Editable s) {}
        });


        //功能：显示和隐藏密码
        //来源：http://www.jb51.net/article/90004.htm
        passwordCheckbox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked){
                    passwordEdit.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                    passwordEdit.setSelection(passwordEdit.length());
                }else {
                    passwordEdit.setTransformationMethod(PasswordTransformationMethod.getInstance());
                    passwordEdit.setSelection(passwordEdit.length());
                }
            }
        });

//        登录功能
        mLoginUpBtn.setOnClickListener(this);
        //清除按钮功能
        mdeleteImg.setOnClickListener(this);
        //返回上一个页面功能
        mBackImg.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.login_up_button:
                //判断功能

                //跳转
                Intent intent = new Intent(LoginUpActivity.this, MainActivity.class);
                startActivity(intent);


                break;
            case R.id.delete_phone:
                mPhoneEdit.setText("");
                break;
            case R.id.back_icon:
                finish();
                break;
            default:
                break;
        }
    }

    private void initView(){
        mPhoneEdit = (EditText) findViewById(R.id.phone_edit);
        passwordEdit = (EditText) findViewById(R.id.login_password);
        passwordCheckbox = (CheckBox) findViewById(R.id.password_checkbox);
        mBackImg = (ImageView) findViewById(R.id.back_icon);
        mdeleteImg = (ImageView) findViewById(R.id.delete_phone);
        mLoginUpBtn = (Button) findViewById(R.id.login_up_button);

//        功能：更改系统状态栏的颜色
//        来源：http://blog.csdn.net/zbh_1042354552/article/details/53215316
//        http://blog.csdn.net/jdsjlzx/article/details/41643587
        if (Build.VERSION.SDK_INT >=Build.VERSION_CODES.LOLLIPOP){
            int myColor = getResources().getColor(R.color.loginButton);
            Window window = this.getWindow();
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(myColor);
        }
    }
}
