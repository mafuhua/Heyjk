package com.yuenkeji.heyjk.viewpager;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

/**
 * 曲线界面
 * Created by Administrator on 2016/2/19.
 */
public class CurvePage extends BasePager {
    private Context context;

    public CurvePage(Context context) {
        super(context);
        this.context = context;
    }

    @Override
    public View initView() {
        TextView textView = new TextView(context);
        textView.setText("我是曲线界面");
        return textView;
    }

    @Override
    public void initData() {

    }
}
