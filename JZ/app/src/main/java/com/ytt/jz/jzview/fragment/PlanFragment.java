package com.ytt.jz.jzview.fragment;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.LinearLayout;

import com.ytt.jz.R;
import com.ytt.jz.jzadapter.planadapter.RecyclerViewPagerAdapter;
import com.ytt.jz.jzbase.BaseFragment;
import com.ytt.jz.jzresource.ViewLocationResource;
import com.ytt.jz.jzutils.assistutils.ScreenSizeUtils;
import com.ytt.jz.jzutils.viewutils.VerticalViewPager;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 首页--计划
 */

public class PlanFragment extends BaseFragment implements ViewTreeObserver.OnGlobalLayoutListener{
    //父容器View
    private View view;
    //顶部星期容器
    @BindView(R.id.plan_TopWeek)
    LinearLayout topWeek;
    //中部日历条目容器
    @BindView(R.id.plan_CalendarViewPager)
    VerticalViewPager calendarViewPager;
    //底部日程条目容器
    @BindView(R.id.plan_ScheduleItemGroup)
    RecyclerView scheduleItemGroup;
    List<List<String>> list = new ArrayList<>();
    private RecyclerViewPagerAdapter adapter;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_plan, container, false);
        ButterKnife.bind(this, view);
        //控件初始化
        initView();
        //绑定监听
        setListener();
        return view;
    }


    //控件初始化
    private void initView() {
        /**
         *中部日历控件初始化
         */

        List<String> childList = new ArrayList<>();
        for (int i = 0 ;i <31;i++){
            childList.add(i+"");
        }
        list.add(childList);
        list.add(childList);
        list.add(childList);
        list.add(childList);
        list.add(childList);
        list.add(childList);

        /**
         *测量底部日程控件高度
         */
        LinearLayout.LayoutParams scheduleParams = (LinearLayout.LayoutParams) scheduleItemGroup.getLayoutParams();
        ViewLocationResource.PLAN_SCHEDULEITEMGROUP_HEIGHT = (int) (ScreenSizeUtils.getScreenSize(getActivity())[1] * 0.2);
        scheduleParams.height =  ViewLocationResource.PLAN_SCHEDULEITEMGROUP_HEIGHT;
        scheduleItemGroup.setLayoutParams(scheduleParams);

    }
    //绑定监听
    private void setListener() {
        topWeek.getViewTreeObserver().addOnGlobalLayoutListener(this);
        scheduleItemGroup.getViewTreeObserver().addOnGlobalLayoutListener(this);
    }
    //全局布局监听方法
    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @Override
    public void onGlobalLayout() {
        topWeek.getViewTreeObserver().removeOnGlobalLayoutListener(this);
        scheduleItemGroup.getViewTreeObserver().removeOnGlobalLayoutListener(this);
        ViewLocationResource.PLAN_CALENDAR_WIDTH = ScreenSizeUtils.getScreenSize(getActivity())[0]/7;
        ViewLocationResource.PLAN_CALENDAR_HEIGHT = (scheduleItemGroup.getTop() - topWeek.getBottom())/5;
        if (null == adapter) {
            adapter = new RecyclerViewPagerAdapter(list, getActivity(), ViewLocationResource.PLAN_CALENDAR_HEIGHT, ViewLocationResource.PLAN_CALENDAR_WIDTH);
            calendarViewPager.setAdapter(adapter);
        }
    }
}
