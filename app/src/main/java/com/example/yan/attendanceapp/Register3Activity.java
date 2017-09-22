package com.example.yan.attendanceapp;

import android.content.Intent;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;

public class Register3Activity extends AppCompatActivity {
    private Button mOverBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register3);
        initView();
        mOverBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Register3Activity.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }

    private void initView(){
        mOverBtn =(Button) findViewById(R.id.register_over);

        if (Build.VERSION.SDK_INT >=Build.VERSION_CODES.LOLLIPOP){
            int myColor = getResources().getColor(R.color.registerButton);
            Window window = this.getWindow();
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(myColor);
        }
    }
}
