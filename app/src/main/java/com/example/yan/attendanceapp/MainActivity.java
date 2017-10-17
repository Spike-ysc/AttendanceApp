package com.example.yan.attendanceapp;

import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.widget.Toast;

import com.ashokvarma.bottomnavigation.BottomNavigationBar;
import com.ashokvarma.bottomnavigation.BottomNavigationItem;

public class MainActivity extends BaseActivity {
    private long mExitTime;
    private BottomNavigationBar mMainBtnBar;
    private Fragment currentFragment = null;
    private HomeFragment mHome = new HomeFragment();
    private MessageFragment mMessage = new MessageFragment();
    private MeFragment mMe = new MeFragment();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();


        mMainBtnBar = (BottomNavigationBar) findViewById(R.id.main_bottom_Bar);
        mMainBtnBar.setMode(BottomNavigationBar.MODE_FIXED)
                .setBackgroundStyle(BottomNavigationBar.BACKGROUND_STYLE_DEFAULT);
        mMainBtnBar.setAutoHideEnabled(true);
        mMainBtnBar.addItem(new BottomNavigationItem(R.drawable.item2,"主页").setActiveColorResource(R.color.blue))
                .addItem(new BottomNavigationItem(R.drawable.item3,"消息").setActiveColorResource(R.color.blue))
                .addItem(new BottomNavigationItem(R.drawable.item5,"我").setActiveColorResource(R.color.blue))
                .setFirstSelectedPosition(0)
                .initialise();
        mMainBtnBar.setTabSelectedListener(new BottomNavigationBar.OnTabSelectedListener() {
            @Override
            public void onTabSelected(int position) {
                switch (position){
                    case 0:
                        switchFragment(mHome);
                        break;
                    case 1:
                        switchFragment(mMessage);
                        break;
                    case 2:
                        switchFragment(mMe);
                        break;
                    default:
                        break;
                }
            }

            @Override
            public void onTabUnselected(int position) {

            }

            @Override
            public void onTabReselected(int position) {

            }
        });
    }
//    底部导航栏
    private void initView(){

    }



    private void switchFragment(Fragment targetfragment) {
        FragmentTransaction transaction = getSupportFragmentManager()
                .beginTransaction();
        if (!targetfragment.isAdded()) {
            if (currentFragment != null) {
                transaction.hide(currentFragment);
            }
            transaction.add(R.id.main_fragment, targetfragment)
                    .commit();
        } else {
            transaction.hide(currentFragment)
                    .show(targetfragment)
                    .commit();
        }
        currentFragment = targetfragment;
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
