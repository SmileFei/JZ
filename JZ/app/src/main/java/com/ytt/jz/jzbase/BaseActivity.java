package com.ytt.jz.jzbase;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

/**
 * 基类Activity
 */

public class BaseActivity extends AppCompatActivity
implements View.OnClickListener //实现点击监听
{
    private final static String TAG = "BaseActivity";
    //创建
    @Override
    public void onCreate(Bundle savedInstanceState, PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
    }
    //开始
    @Override
    protected void onStart() {
        super.onStart();
    }
    //可交互
    @Override
    protected void onResume() {
        super.onResume();
    }
    //不可交互
    @Override
    protected void onPause() {
        super.onPause();
    }
    //停止
    @Override
    protected void onStop() {
        super.onStop();
    }
    //销毁
    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
    //窗体焦点改变
    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
    }
    //点击监听重写
    @Override
    public void onClick(View view) {

    }
}
