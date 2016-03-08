package com.yuenkeji.heyjk.viewpager;

import android.content.Context;
import android.view.View;

/**
 * Created by Administrator on 2016/2/19.
 */
public abstract class BasePager {
    public static final long SCAN_PERIOD = 10000;
    public final static String TAG ="mafuhua";
    public String[] titleStrings = new String[]{"体重测量", "体成分测量", "餐前测量", "餐后测量", "血压测量", "体温测量"};
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
