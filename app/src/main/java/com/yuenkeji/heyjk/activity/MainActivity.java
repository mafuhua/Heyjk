package com.yuenkeji.heyjk.activity;

import android.content.SharedPreferences;
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
import com.yuenkeji.heyjk.fragment.SettingFragment;
import com.yuenkeji.heyjk.homefragment.BloodFragment;
import com.yuenkeji.heyjk.homefragment.BloodPressureFragment;
import com.yuenkeji.heyjk.homefragment.HomeFragment2;
import com.yuenkeji.heyjk.homefragment.TempFragment;
import com.yuenkeji.heyjk.homefragment.Weight2Fragment;
import com.yuenkeji.heyjk.homefragment.WeightComFragment;

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
    private HistoryFragment historyFragment;
    private CurveFragment curveFragment;
    private SettingFragment settingFragment;
    private HomeFragment2 homeFragment2;
    private TempFragment tempFragment;
    private BloodFragment bloodFragment;
    private BloodPressureFragment bloodPressureFragment;
    private Weight2Fragment weightFragment;
    private WeightComFragment weightComFragment;
    private SharedPreferences sharedPreferences;
    public static String userid;
    private void assignViews() {
        sharedPreferences = getSharedPreferences("userinfo", MODE_PRIVATE);
        userid = sharedPreferences.getString("user_id", "");
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
        homeFragment2 = new HomeFragment2();
        //  homeFragment = (HomeFragment) FragmentFractory.getInstance().createFragment(0);
        ft.replace(R.id.ll_content, homeFragment2);
        ft.commit();
        homeFragment2.setOnBTClickListener(new HomeFragment2.OnBTClickListener() {
            @Override
            public void onSubClick(int position) {
                subhomepager(position);
            }
        });
        historyFragment = (HistoryFragment) FragmentFractory.getInstance().createFragment(8);
        curveFragment = (CurveFragment) FragmentFractory.getInstance().createFragment(7);
        settingFragment = (SettingFragment) FragmentFractory.getInstance().createFragment(9);
    }

    public void subhomepager(int position) {
        ft = fm.beginTransaction();
        switch (position) {
            case 0:
                ft.replace(R.id.ll_content, homeFragment2);
                break;
            case 1:
                flag = true;
                weightFragment = new Weight2Fragment();
                ft.replace(R.id.ll_content, weightFragment);
                break;
            case 2:
                flag = true;
                weightComFragment = new WeightComFragment();
                ft.replace(R.id.ll_content, weightComFragment);
                break;
            case 3:
                flag = true;
                bloodFragment = new BloodFragment();
                ft.replace(R.id.ll_content, bloodFragment);
                break;
            case 4:
                flag = true;
                bloodFragment = new BloodFragment();
                ft.replace(R.id.ll_content, bloodFragment);
                break;
            case 5:
                flag = true;
                bloodPressureFragment = new BloodPressureFragment();
                ft.replace(R.id.ll_content, bloodPressureFragment);
                break;
            case 6:
                flag = true;
                tempFragment = new TempFragment();
                ft.replace(R.id.ll_content, tempFragment);
                break;
        }
        ft.commit();

    }

    @Override
    public void onClick(View view) {
        ft = fm.beginTransaction();
        switch (view.getId()) {
            case R.id.ll_measure:
                resetTabBottom();
                ivMeasure.setImageDrawable(getResources().getDrawable(
                        R.drawable.tab12x));
                tvMeasure.setTextColor(getResources().getColor(R.color.blue));
                ft.replace(R.id.ll_content, this.homeFragment2);
                break;
            case R.id.ll_history:
                flag = true;
                resetTabBottom();
                ivHistory.setImageDrawable(getResources().getDrawable(
                        R.drawable.tab_02_s2x));
                tvHistory.setTextColor(getResources().getColor(R.color.blue));
                ft.replace(R.id.ll_content, historyFragment);
                break;
            case R.id.ll_curve:
                flag = true;
                resetTabBottom();
                ivCurve.setImageDrawable(getResources().getDrawable(
                        R.drawable.tab_03_s2x));
                tvCurve.setTextColor(getResources().getColor(R.color.blue));
                ft.replace(R.id.ll_content, curveFragment);
                break;
            case R.id.ll_setting:
                flag = true;
                resetTabBottom();
                ivSetting.setImageDrawable(getResources().getDrawable(
                        R.drawable.tab4s2x));
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
                R.drawable.tab1n2x));
        tvMeasure.setTextColor(getResources().getColor(R.color.black));
        ivCurve.setImageDrawable(getResources().getDrawable(
                R.drawable.tab_03_n2x));
        tvCurve.setTextColor(getResources().getColor(R.color.black));
        ivHistory.setImageDrawable(getResources().getDrawable(
                R.drawable.tab_02_n2x));
        tvHistory.setTextColor(getResources().getColor(R.color.black));
        ivSetting.setImageDrawable(getResources().getDrawable(
                R.drawable.tab4n2x));
        tvSetting.setTextColor(getResources().getColor(R.color.black));
    }
    public boolean flag = false;
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {

        if (KeyEvent.KEYCODE_BACK == event.getKeyCode() && flag == true) {
           subhomepager(0);
            flag = false;
            return true;
        } else {
            return super.onKeyDown(keyCode, event);

        }
    }
}
