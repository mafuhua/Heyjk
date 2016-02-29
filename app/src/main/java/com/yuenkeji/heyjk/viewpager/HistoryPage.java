package com.yuenkeji.heyjk.viewpager;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.yuenkeji.heyjk.R;

/**
 * 历史界面
 * Created by Administrator on 2016/2/19.
 */
public class HistoryPage extends BasePager {

    public HistoryPage(Context context) {
        super(context);
        this.context = context;
    }
    @Override
    public View initView() {
        View view = View.inflate(context, R.layout.home_setting, null);
        return view;
    }

    @Override
    public void initData() {

    }
}
