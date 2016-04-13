package com.yuenkeji.heyjk.fragment;

import android.app.ProgressDialog;
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
    public  ProgressDialog mypDialog;
    public  void checkgetData() {
        mypDialog = new ProgressDialog(getActivity());
        //实例化
        mypDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        //设置进度条风格，风格为圆形，旋转的
        //设置ProgressDialog 标题
        mypDialog.setMessage("正在加载中");
        //设置ProgressDialog 提示信息
        mypDialog.setIndeterminate(false);
        //设置ProgressDialog 的进度条是否不明确
        mypDialog.setCancelable(true);
        //设置ProgressDialog 是否可以按退回按键取消
        mypDialog.setCanceledOnTouchOutside(false);
        mypDialog.show();
        //让ProgressDialog显示

    }
    /**
     * fragment挂载到Activity时调用
     */
    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initData();
    }
    
}
