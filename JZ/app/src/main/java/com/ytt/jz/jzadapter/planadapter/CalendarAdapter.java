package com.ytt.jz.jzadapter.planadapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.ytt.jz.R;

import java.util.List;

/**
 * 计划--日历适配器
 */

public class CalendarAdapter extends RecyclerView.Adapter<CalendarAdapter.CalendarHolder>{
    private List<String> list;
    private Context context;
    private View view;
    private LayoutInflater inflater;
    private int height;
    private int width;
//    private RelativeLayout.LayoutParams params;
    public CalendarAdapter(List<String> list, Context context, int height,int width) {
        this.list = list;
        this.context = context;
        this.height = height;
        this.width = width;
        inflater = LayoutInflater.from(context);
//        params = new RelativeLayout.LayoutParams(width,height);
    }

    @Override
    public CalendarHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        view = inflater.inflate(R.layout.plan_calendar_item,parent,false);
        return new CalendarHolder(view);
    }

    @Override
    public void onBindViewHolder(CalendarHolder holder, int position) {
        ViewGroup.LayoutParams layoutParams = holder.txt.getLayoutParams();
        layoutParams.height = height;
        layoutParams.width = width;
        holder.txt.setLayoutParams(layoutParams);
        holder.txt.setGravity(Gravity.CENTER);
        holder.txt.setText(list.get(position));
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
    class CalendarHolder extends RecyclerView.ViewHolder{
        private TextView txt;
        public CalendarHolder(View itemView) {
            super(itemView);
            txt = (TextView) itemView.findViewById(R.id.test_item);
        }
    }
}


