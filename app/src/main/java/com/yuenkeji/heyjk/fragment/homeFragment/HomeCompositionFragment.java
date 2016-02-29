package com.yuenkeji.heyjk.fragment.homeFragment;

import android.view.View;
import android.widget.TextView;

import com.yuenkeji.heyjk.fragment.BaseFragment;

/**
 * Created by Administrator on 2016/2/24.
 */
public class HomeCompositionFragment extends BaseFragment{
    @Override
    public View initView() {
        TextView textView = new TextView(getActivity());
        textView.setText("HomeCompositionFragment");
        return textView;
    }

    @Override
    public void initData() {

    }
}
