package com.yuenkeji.heyjk.viewpager.homesubPagers;

import android.content.Context;
import android.view.View;

import com.yuenkeji.heyjk.R;
import com.yuenkeji.heyjk.viewpager.BasePager;

/**
 * 餐前血糖
 * Created by Administrator on 2016/2/19.
 */
public class BeforeBloodPager extends BasePager {
    public BeforeBloodPager(Context context) {
        super(context);
    }

    @Override
    public View initView() {
        View view = View.inflate(context, R.layout.home_sub_lunchblood, null);
        return view;
    }

    @Override
    public void initData() {

    }
}
