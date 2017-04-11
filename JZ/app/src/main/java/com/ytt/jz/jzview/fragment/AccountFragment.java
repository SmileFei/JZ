package com.ytt.jz.jzview.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ytt.jz.R;
import com.ytt.jz.jzbase.BaseFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 首页--账本
 */

public class AccountFragment extends BaseFragment {
    //父容器View
    private View view;
    //记账按钮
    @BindView(R.id.float_button_account)
    FloatingActionButton accountButton;
    //条目容器
    @BindView(R.id.item_group)
    RecyclerView itemGroup;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_account, container, false);
        ButterKnife.bind(this, view);
        //控件初始化
        initView();
        //绑定监听
        setListener();
        return view;
    }
    //控件初始化
    private void initView() {
        //设置条目布局容器
        itemGroup.setLayoutManager(new LinearLayoutManager(getActivity(),LinearLayoutManager.VERTICAL,false));
        List<String> list = new ArrayList<>();
        for (int i = 0 ;i <30;i++){
            list.add("第"+i+"个条目");
        }

    }
    //绑定监听
    private void setListener() {
        /**
         *点击监听
         */
        accountButton.setOnClickListener(this);

    }

    //重写点击监听
    @Override
    public void onClick(View view) {

    }
}
