package com.ytt.jz.jzadapter.planadapter;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

/**
 * 日历recyclerView适配器
 */

public class RecyclerViewPagerAdapter extends PagerAdapter{
    private List<List<String>> list;
    private Context context;
    private int height;
    private int width;
    public RecyclerViewPagerAdapter(List<List<String>> list, Context context,int height,int width) {
        this.list = list;
        this.context = context;
        this.height = height;
        this.width = width;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        RecyclerView recyclerView = new RecyclerView(context);
        recyclerView.setLayoutManager(new GridLayoutManager(context,7));
        CalendarAdapter adapter = new CalendarAdapter(list.get(position),context,height,width);
        recyclerView.setAdapter(adapter);
        recyclerView.setOverScrollMode(View.OVER_SCROLL_NEVER);
        container.addView(recyclerView);
        return recyclerView;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View)object);
    }
}
