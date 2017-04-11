package com.ytt.jz.jzview.activity;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.annotation.RequiresApi;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.view.ViewTreeObserver;
import android.view.animation.AccelerateInterpolator;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.ytt.jz.R;
import com.ytt.jz.jzadapter.publicadapter.ImageViewPagerAdapter;
import com.ytt.jz.jzbase.BaseActivity;
import com.ytt.jz.jzresource.ConstantResource;
import com.ytt.jz.jzresource.ImageResource;
import com.ytt.jz.jzutils.assistutils.InstanceUtils;
import com.ytt.jz.jzutils.assistutils.ScreenSizeUtils;

import butterknife.BindView;
import butterknife.ButterKnife;


/**
 * 首次安装--引导页
 */
public class GuideActivity extends BaseActivity implements ViewPager.OnPageChangeListener {
    //动画效果布局
    @BindView(R.id.guide_animator)
    LinearLayout animator_Layout;
    //轮播
    @BindView(R.id.guide_viewPager)
    ViewPager viewPager;
    //focusPoint
    @BindView(R.id.guide_focusPoint)
    View focusPoint;
    //staticPointGroup
    @BindView(R.id.guide_staticPoint)
    LinearLayout staticPoint;
    //guide_start_button
    @BindView(R.id.guide_start_button)
    Button start;
    //staticPointInterval
    private int pointInterval;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guide);
        //依赖注入绑定
        ButterKnife.bind(this);
        //控件初始化
        initView();
    }

    //控件初始化
    private void initView() {
        //开始按钮设置监听
        start.setOnClickListener(this);
        //轮播
        ImageViewPagerAdapter adapter = new ImageViewPagerAdapter(ImageResource.GUIDE_IMAGE, this);
        viewPager.setAdapter(adapter);
        //轮播滑动监听
        viewPager.addOnPageChangeListener(this);
        //确认底部staticPointGroup绘制完成后，得到childPointViewInterVal;
        staticPoint.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
            @Override
            public void onGlobalLayout() {
                staticPoint.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                pointInterval = staticPoint.getChildAt(1).getLeft()
                        - staticPoint.getChildAt(0).getLeft();
            }
        });
        //开始按钮动态位置设定
        RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) start.getLayoutParams();
        int marginBottom = (int) (ScreenSizeUtils.getScreenSize(this)[1] * 0.075);
        layoutParams.bottomMargin = marginBottom;
        start.setLayoutParams(layoutParams);

    }

    //滑动中……
    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
        if (positionOffset > 0) {
            RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams) focusPoint.getLayoutParams();
            int marginLeft = (int) (positionOffset * pointInterval + pointInterval * position + 0.5f);
            params.leftMargin = marginLeft;
            focusPoint.setLayoutParams(params);
        }
        if (1 == position) {
            setIsLastPage();
        } else {
            setIsNotLastPage();
        }
    }

    //滑动完成
    @Override
    public void onPageSelected(int position) {

    }

    //滑动状态改变
    @Override
    public void onPageScrollStateChanged(int state) {

    }

    //引导页滑动至最后一页
    private void setIsLastPage() {
        staticPoint.setVisibility(View.GONE);
        focusPoint.setVisibility(View.GONE);
        start.setVisibility(View.VISIBLE);
        ObjectAnimator.ofFloat(start, "alpha", 0f, 1f)
                .setDuration(ConstantResource.GUIDE_ANIMATOR_TIME)
                .start();
    }

    //引导页滑动至非最后一页
    private void setIsNotLastPage() {
        staticPoint.setVisibility(View.VISIBLE);
        focusPoint.setVisibility(View.VISIBLE);
        start.setVisibility(View.GONE);
    }

    //点击监听重写
    @Override
    public void onClick(View view) {
        //获取意图
        final Intent intent = InstanceUtils.getIntentInstance();
        //指定Activity跳转意图
        intent.setClass(this, FirstPageActivity.class);
        //开始动画后，将开始按钮隐藏
        start.setVisibility(View.GONE);
        //判断当前BuildSDK版本是否支持扩散动画
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            animator_Layout.setVisibility(View.VISIBLE);
            //计算扩散动画圆心坐标点
            int x = ScreenSizeUtils.getScreenSize(this)[0] / 2;
            int y = (int) (ScreenSizeUtils.getScreenSize(this)[1] * 0.925);
            //开始半径
            float startR = 0f;
            //结束半径
            float endR = (float) Math.sqrt((Math.pow(x, 2) + Math.pow(y, 2)));
            //动画
            Animator animator = ViewAnimationUtils.createCircularReveal(animator_Layout, x, y, startR, endR);
            animator.setInterpolator(new AccelerateInterpolator());
            animator.setDuration(ConstantResource.GUIDE_ANIMATOR_TIME);
            animator.start();
            //设置一个计时器，当动画结束后，Activity跳转
            new CountDownTimer(ConstantResource.GUIDE_ANIMATOR_TIME, ConstantResource.GUIDE_ANIMATOR_TIME) {

                @Override
                public void onTick(long l) {

                }

                @Override
                public void onFinish() {
                    startActivity(intent);
                    finish();
                }
            }.start();
        } else {
            startActivity(intent);
            finish();
        }
    }
}
