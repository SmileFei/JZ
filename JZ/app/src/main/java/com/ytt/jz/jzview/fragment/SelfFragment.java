package com.ytt.jz.jzview.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ytt.jz.R;
import com.ytt.jz.jzbase.BaseFragment;

import butterknife.ButterKnife;

/**
 * 首页--我的
 */

public class SelfFragment extends BaseFragment{
    //父容器View
    private View view;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_self,container,false);
        ButterKnife.bind(this,view);
        return view;
    }
}
