package com.yuenkeji.heyjk.activity;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.yuenkeji.heyjk.R;
import com.yuenkeji.heyjk.fragment.CurveFragment;
import com.yuenkeji.heyjk.fragment.FragmentFractory;
import com.yuenkeji.heyjk.fragment.HistoryFragment;
import com.yuenkeji.heyjk.fragment.HomeFragment;
import com.yuenkeji.heyjk.fragment.SettingFragment;

/**
 * 打开程序主界面
 */
public class MainActivity extends FragmentActivity implements View.OnClickListener {
    private LinearLayout llMeasure;
    private ImageView ivMeasure;
    private TextView tvMeasure;
    private LinearLayout llHistory;
    private ImageView ivHistory;
    private TextView tvHistory;
    private LinearLayout llCurve;
    private ImageView ivCurve;
    private TextView tvCurve;
    private LinearLayout llSetting;
    private ImageView ivSetting;
    private TextView tvSetting;
    private FragmentManager fm;
    private FragmentTransaction ft;
    private HomeFragment homeFragment;
    private HistoryFragment historyFragment;
    private CurveFragment curveFragment;
    private SettingFragment settingFragment;


    private void assignViews() {
        llMeasure = (LinearLayout) findViewById(R.id.ll_measure);
        ivMeasure = (ImageView) findViewById(R.id.iv_measure);
        tvMeasure = (TextView) findViewById(R.id.tv_measure);
        llHistory = (LinearLayout) findViewById(R.id.ll_history);
        ivHistory = (ImageView) findViewById(R.id.iv_history);
        tvHistory = (TextView) findViewById(R.id.tv_history);
        llCurve = (LinearLayout) findViewById(R.id.ll_curve);
        ivCurve = (ImageView) findViewById(R.id.iv_curve);
        tvCurve = (TextView) findViewById(R.id.tv_curve);
        llSetting = (LinearLayout) findViewById(R.id.ll_setting);
        ivSetting = (ImageView) findViewById(R.id.iv_setting);
        tvSetting = (TextView) findViewById(R.id.tv_setting);

        llCurve.setOnClickListener(this);
        llSetting.setOnClickListener(this);
        llMeasure.setOnClickListener(this);
        llHistory.setOnClickListener(this);

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        assignViews();
        fm = getSupportFragmentManager();
        ft = fm.beginTransaction();
        homeFragment = (HomeFragment) FragmentFractory.getInstance().createFragment(0);
        ft.replace(R.id.ll_content, homeFragment);
        ft.commit();
        historyFragment = (HistoryFragment) FragmentFractory.getInstance().createFragment(8);
        curveFragment = (CurveFragment) FragmentFractory.getInstance().createFragment(7);
        settingFragment = (SettingFragment) FragmentFractory.getInstance().createFragment(9);
    }

    @Override
    public void onClick(View view) {
        ft = fm.beginTransaction();
        switch (view.getId()) {
            case R.id.ll_measure:
                resetTabBottom();
                ivMeasure.setImageDrawable(getResources().getDrawable(
                        R.drawable.tab_01_s2x));
                tvMeasure.setTextColor(getResources().getColor(R.color.blue));
                ft.replace(R.id.ll_content, this.homeFragment);
                break;
            case R.id.ll_history:
                resetTabBottom();
                ivHistory.setImageDrawable(getResources().getDrawable(
                        R.drawable.tab_02_s2x));
                tvHistory.setTextColor(getResources().getColor(R.color.blue));
                ft.replace(R.id.ll_content, historyFragment);
                break;
            case R.id.ll_curve:
                resetTabBottom();
                ivCurve.setImageDrawable(getResources().getDrawable(
                        R.drawable.tab_03_s2x));
                tvCurve.setTextColor(getResources().getColor(R.color.blue));
                ft.replace(R.id.ll_content, curveFragment);
                break;
            case R.id.ll_setting:
                resetTabBottom();
                ivSetting.setImageDrawable(getResources().getDrawable(
                        R.drawable.tab_04_s2x));
                tvSetting.setTextColor(getResources().getColor(R.color.blue));
                ft.replace(R.id.ll_content, settingFragment);
                break;

            default:
                break;
        }
        ft.commit();
    }

    public void resetTabBottom() {
        ivMeasure.setImageDrawable(getResources().getDrawable(
                R.drawable.tab_01_n2x));
        tvMeasure.setTextColor(getResources().getColor(R.color.black));
        ivCurve.setImageDrawable(getResources().getDrawable(
                R.drawable.tab_03_n2x));
        tvCurve.setTextColor(getResources().getColor(R.color.black));
        ivHistory.setImageDrawable(getResources().getDrawable(
                R.drawable.tab_02_n2x));
        tvHistory.setTextColor(getResources().getColor(R.color.black));
        ivSetting.setImageDrawable(getResources().getDrawable(
                R.drawable.tab_04_n2x));
        tvSetting.setTextColor(getResources().getColor(R.color.black));
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (KeyEvent.KEYCODE_BACK == event.getKeyCode()&&homeFragment.flag == true) {
            homeFragment.myViewPagers.setCurrentItem(0);
            homeFragment.initHomeTitleBar();
            return true;
        } else if(homeFragment.myViewPagers.getCurrentItem() == 0){
            return super.onKeyDown(keyCode, event);

        }else if (homeFragment.myViewPagers.getCurrentItem()>0){
            return true;
        }else {
            return super.onKeyDown(keyCode, event);
        }
    }
}
