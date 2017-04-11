package com.ytt.jz.jzresource;

/**
 * 常量接口
 */

public interface ConstantResource {
    //欢迎页倒计时间隔
    long WELCOM_TIME_INTERVAL = 1000;
    //欢迎页倒计时总时长
    long WELCOM_TIME_TOTAL = 2000;
    //系统信息TAG
    String SYSTEM_TAG = "SYSTEM_TAG";
    //是否首次安装
    String IS_FIRST_INSTALL = "IS_FIRST_INSTALL";
    //首次安装圆形扩散动画时长
    long GUIDE_ANIMATOR_TIME = 700;
    //本地数据库名
    String LOCAL_SQLITE_NAME = "jz";
}
