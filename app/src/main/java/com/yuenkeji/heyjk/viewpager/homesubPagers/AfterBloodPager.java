package com.yuenkeji.heyjk.viewpager.homesubPagers;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.yuenkeji.heyjk.R;
import com.yuenkeji.heyjk.fragment.HomeFragment;
import com.yuenkeji.heyjk.viewpager.BasePager;

/**
 * 餐后血糖
 * Created by Administrator on 2016/2/19.
 */
public class AfterBloodPager extends BasePager implements View.OnClickListener{
    public ImageView btnReturn;
    public ImageView btnConfig;
    public TextView titleHome;
    public LinearLayout appTitle;
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_confirm:
                HomeFragment.myViewPagers.setCurrentItem(0);
              /*  context.unbindService(mServiceConnection);
                context.unregisterReceiver(mGattUpdateReceiver);
                mBluetoothLeService = null;*/
                HomeFragment.flag = false;
                Toast.makeText(context, "提交", Toast.LENGTH_SHORT).show();
                break;
            case R.id.btn_return:
                HomeFragment.myViewPagers.setCurrentItem(0);
               /* context.unbindService(mServiceConnection);
                context.unregisterReceiver(mGattUpdateReceiver);
                mBluetoothLeService = null;*/
                HomeFragment.flag = false;
                Toast.makeText(context, "返回", Toast.LENGTH_SHORT).show();

                break;
        }
    }
    public AfterBloodPager(Context context) {
        super(context);
    }

    @Override
    public View initView() {
        View view = View.inflate(context, R.layout.home_sub_lunchblood, null);
        btnReturn = (ImageView) view.findViewById(R.id.btn_return);
        btnConfig = (ImageView) view.findViewById(R.id.btn_confirm);
        titleHome = (TextView) view.findViewById(R.id.tv_title_home);
        appTitle = (LinearLayout) view.findViewById(R.id.app_title);
        appTitle.setVisibility(View.GONE);
        titleHome.setText(titleStrings[3]);
        titleHome.setVisibility(View.VISIBLE);
        btnConfig.setVisibility(View.VISIBLE);
        btnReturn.setVisibility(View.VISIBLE);
        btnConfig.setOnClickListener(this);
        btnReturn.setOnClickListener(this);
        return view;
    }

    @Override
    public void initData() {

    }
}
