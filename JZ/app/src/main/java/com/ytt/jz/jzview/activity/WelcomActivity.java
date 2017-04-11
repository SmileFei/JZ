package com.ytt.jz.jzview.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.widget.ImageView;

import com.ytt.jz.R;
import com.ytt.jz.jzbase.BaseActivity;
import com.ytt.jz.jzresource.ConstantResource;
import com.ytt.jz.jzutils.assistutils.InstanceUtils;

import butterknife.BindView;
import butterknife.ButterKnife;


/**
 * 欢迎页
 */
public class WelcomActivity extends BaseActivity {
    @BindView(R.id.activity_welcom)
    ImageView iv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcom);
        //依赖注初绑定
        ButterKnife.bind(this);
        //初始化
        init();
    }

    //初始化
    private void init() {
        //首页倒计时
        new CountDownTimer(ConstantResource.WELCOM_TIME_TOTAL, ConstantResource.WELCOM_TIME_INTERVAL) {

            @Override
            public void onTick(long l) {
            }

            @Override
            public void onFinish() {
                //倒计时完成后，判断是否首次安装
                judgeIsFirstInstall();
            }
        }.start();
    }

    //判断是否首次安装
    private void judgeIsFirstInstall() {
        Intent intent = InstanceUtils.getIntentInstance();
        SharedPreferences sp = InstanceUtils.getSharedPreferencesInstance(this);
//        如果首次安装
        if (sp.getBoolean(ConstantResource.IS_FIRST_INSTALL, true)) {
            SharedPreferences.Editor editor = sp.edit();
            editor.putBoolean(ConstantResource.IS_FIRST_INSTALL, false).commit();
            intent.setClass(this, GuideActivity.class);
        } else {
            intent.setClass(this, FirstPageActivity.class);
        }
        startActivity(intent);
        finish();
    }

}
