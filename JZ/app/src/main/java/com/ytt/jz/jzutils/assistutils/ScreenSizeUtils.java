package com.ytt.jz.jzutils.assistutils;

import android.app.Activity;
import android.content.Context;
import android.util.DisplayMetrics;
import android.view.WindowManager;

/**
 * 屏幕尺寸工具类
 */

public class ScreenSizeUtils {
    //屏幕尺寸
    private static int[] screenSize;
    //获取屏幕尺寸方法
    public static synchronized int[] getScreenSize(Activity activity){
        if (null!=screenSize){
            return screenSize;
        }
        //获得窗体管理者
        WindowManager manager = activity.getWindowManager();
        //创建展示指标
        DisplayMetrics metrics = new DisplayMetrics();
        //取得当前屏幕展示数据
        manager.getDefaultDisplay().getMetrics(metrics);
        screenSize = new int[]{metrics.widthPixels,metrics.heightPixels};
        return screenSize;
    }
    //sp-->px转换工具
    public static int sp2px(int value,Context context) {
        float v = context.getResources().getDisplayMetrics().scaledDensity;
        return (int) (value * v + 0.5f);
    }
    //dp-->px转换工具
    public static int dp2px(int value,Context context) {
        float v = context.getResources().getDisplayMetrics().density;
        return (int) (value * v + 0.5f);
    }
}
