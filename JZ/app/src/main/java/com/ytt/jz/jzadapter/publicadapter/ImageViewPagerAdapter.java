package com.ytt.jz.jzadapter.publicadapter;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

/**
 * 图片轮播适配器
 */

public class ImageViewPagerAdapter extends PagerAdapter{
    private int[] imageResource;
    private Context context;

    public ImageViewPagerAdapter(int[] imageResource, Context context) {
        this.imageResource = imageResource;
        this.context = context;
    }

    @Override
    public int getCount() {
        return imageResource.length;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view==object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        ImageView iv = new ImageView(context);
        iv.setScaleType(ImageView.ScaleType.CENTER_CROP);
        iv.setImageResource(imageResource[position]);
        container.addView(iv);
        return iv;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
       container.removeView((View)object);
    }
}
