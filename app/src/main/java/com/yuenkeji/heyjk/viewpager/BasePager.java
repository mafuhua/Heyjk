package com.yuenkeji.heyjk.viewpager;

import android.content.Context;
import android.view.View;

/**
 * Created by Administrator on 2016/2/19.
 */
public abstract class BasePager {
    public Context context;
    public BasePager(Context context){
        this.context = context;
    }
    /**
     * 初始化界面方法
     * 子类必须重写
     * @return
     */
    public abstract View initView();

    /**
     * 初始化数据
     */
    public abstract void initData();
}
