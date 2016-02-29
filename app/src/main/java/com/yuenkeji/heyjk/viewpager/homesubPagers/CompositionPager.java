package com.yuenkeji.heyjk.viewpager.homesubPagers;

import android.content.Context;
import android.view.View;

import com.yuenkeji.heyjk.R;
import com.yuenkeji.heyjk.viewpager.BasePager;

/**
 * 体成分测量
 * Created by Administrator on 2016/2/19.
 */
public class CompositionPager extends BasePager {
    public CompositionPager(Context context) {
        super(context);
    }

    @Override
    public View initView() {
        View view = View.inflate(context, R.layout.home_sub_composition, null);
        return view;
    }

    @Override
    public void initData() {

    }
}
