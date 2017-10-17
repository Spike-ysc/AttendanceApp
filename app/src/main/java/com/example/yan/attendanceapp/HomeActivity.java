package com.example.yan.attendanceapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.ashokvarma.bottomnavigation.BottomNavigationBar;
import com.ashokvarma.bottomnavigation.BottomNavigationItem;

public class HomeActivity extends AppCompatActivity {
    private BottomNavigationBar mMainBtnBar;
    private BottomNavigationBar bar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

//        mMainBtnBar = (BottomNavigationBar) findViewById(R.id.main_bottom_Bar);
//        mMainBtnBar.setMode(BottomNavigationBar.MODE_FIXED)
//                .setBackgroundStyle(BottomNavigationBar.BACKGROUND_STYLE_DEFAULT);
//        mMainBtnBar.setAutoHideEnabled(true);
//        mMainBtnBar.addItem(new BottomNavigationItem(R.drawable.item3,"").setActiveColorResource(R.color.blue))
//                .addItem(new BottomNavigationItem(R.drawable.item4,"").setActiveColorResource(R.color.blue))
//                .addItem(new BottomNavigationItem(R.drawable.item5,"").setActiveColorResource(R.color.blue))
//                .setFirstSelectedPosition(0)
//                .initialise();

    }
}
