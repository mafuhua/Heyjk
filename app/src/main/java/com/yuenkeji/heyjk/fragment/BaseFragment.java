package com.yuenkeji.heyjk.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
/**
 * Fragment的基类
 * 共性方法：onCreateView
 * @author wangdh
 *
 */
public abstract class BaseFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = initView();
        return view;
    }
    /**
     * 初始化界面的方法
     * 在onCreateView中调用
     * 此方法，子类必须重写
     * @return
     */
    public abstract View initView();
    /**
     * 初始化数据
     * 是在Fragment挂载到Activity时调用，onActivityCreated
     * 子类必须重写
     */
    public abstract void initData();
    
    /**
     * fragment挂载到Activity时调用
     */
    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initData();
    }
    
}
