package com.ytt.jz.jzbase;

import android.app.Application;
import android.content.Context;

/**
 * 基类Application
 */

public class BaseApplication extends Application{
    //全局上下文实例
    public static Context context;
    @Override
    public void onCreate() {
        super.onCreate();
        //获取全局上下文实例
        context = getApplicationContext();
    }
}
