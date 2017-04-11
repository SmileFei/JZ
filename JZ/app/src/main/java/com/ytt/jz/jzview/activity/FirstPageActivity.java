package com.ytt.jz.jzview.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.View;

import com.ytt.jz.R;
import com.ytt.jz.jzadapter.publicadapter.FragmentViewPagerAdapter;
import com.ytt.jz.jzbase.BaseActivity;
import com.ytt.jz.jzutils.viewutils.BottomTabView;
import com.ytt.jz.jzutils.viewutils.HorizontalViewPager;
import com.ytt.jz.jzview.fragment.AccountFragment;
import com.ytt.jz.jzview.fragment.PlanFragment;
import com.ytt.jz.jzview.fragment.SelfFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


public class FirstPageActivity extends BaseActivity implements ViewPager.OnPageChangeListener{
    //VP
    @BindView(R.id.firstpage_fragment)
    HorizontalViewPager viewPager;
    //底部导航栏按钮
    @BindView(R.id.firstPage_bottomTab_account)
    BottomTabView tab_account;
    @BindView(R.id.firstPage_bottomTab_plan)
    BottomTabView tab_plan;
    @BindView(R.id.firstPage_bottomTab_self)
    BottomTabView tab_self;
    //底部按钮集合
    private List<BottomTabView> tabList = new ArrayList<>();
    //Fragment适配器
    FragmentViewPagerAdapter adapter;
    //子Fragment集合
    private List<Fragment> list = new ArrayList<>();
    //子fragment
    private Fragment accountFragment, planFragment, selfFragment;
    //底部按钮点击事件标记
    private int lastClick;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first_page);
        //依赖注入绑定
        ButterKnife.bind(this);
        //控件初始化
        initView();
        //设置监听
        setListener();
    }
    //设置监听
    private void setListener() {
        //底部按钮设置监听
        for (int i = 0 ;i <tabList.size();i++){
            tabList.get(i).setTag(i);
            tabList.get(i).setOnClickListener(this);
        }
        viewPager.addOnPageChangeListener(this);
    }

    //控件初始化
    private void initView() {
        //子framgnet初始化
        accountFragment = new AccountFragment();
        planFragment = new PlanFragment();
        selfFragment = new SelfFragment();
        //添加至集合
        list.add(accountFragment);
        list.add(planFragment);
        list.add(selfFragment);
        //适配器
        adapter = new FragmentViewPagerAdapter(getSupportFragmentManager(), list);
        viewPager.setAdapter(adapter);
        //底部导航栏按钮集合
        tabList.add(tab_account);
        tabList.add(tab_plan);
        tabList.add(tab_self);
        tabList.get(0).setIconAlpha(1f);
    }
    //点击监听重写
    @Override
    public void onClick(View view) {
        int tag = (int) view.getTag();
        if (lastClick==tag){
            return;
        }
        for (int i = 0 ;i <tabList.size();i++){
            tabList.get(i).setIconAlpha(0);
        }
        tabList.get(tag).setIconAlpha(1f);
        viewPager.setCurrentItem(tag,false);
        lastClick = tag;
    }
    //滑动中……
    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
        if (positionOffset>0){
            tabList.get(position).setIconAlpha(1-positionOffset);
            tabList.get(position+1).setIconAlpha(positionOffset);
        }
    }
    //选中后
    @Override
    public void onPageSelected(int position) {
        lastClick = position;
    }
    //滑动状态改变
    @Override
    public void onPageScrollStateChanged(int state) {

    }
}
