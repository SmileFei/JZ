package com.ytt.jz.jzutils.assistutils;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;

import com.ytt.jz.jzresource.ConstantResource;


/**
 * 常用实例获取
 */

public class InstanceUtils {
    //意图
    private static Intent intent;
    //意图单实例获取方法
    public static synchronized Intent getIntentInstance(){
        if (null!=intent){
            return intent;
        }
        intent = new Intent();
        return intent;
    }
    //SharedPreferences
    private static SharedPreferences sharedPreferences;
    //SharedPreferences单实例获取方法
    public static synchronized SharedPreferences getSharedPreferencesInstance(Context context){
        if (null!=sharedPreferences){
            return sharedPreferences;
        }
        sharedPreferences = context.getSharedPreferences(ConstantResource.SYSTEM_TAG,Context.MODE_PRIVATE);
        return sharedPreferences;
    }
}
