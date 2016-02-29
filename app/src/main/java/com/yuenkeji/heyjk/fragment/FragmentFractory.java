package com.yuenkeji.heyjk.fragment;

import android.support.v4.app.Fragment;

import java.util.HashMap;

/**
 * fragment工厂类
 *
 * @author wangdh
 */
public class FragmentFractory {

    private static FragmentFractory instance = new FragmentFractory();
    private HashMap<Integer, Fragment> cacheFragment = new HashMap<Integer, Fragment>();

    private FragmentFractory() {
    }

    public static FragmentFractory getInstance() {
        return instance;
    }

    //根据当前位置获取对应的fragment
    public Fragment createFragment(int position) {
        Fragment fragment = null;
        fragment = cacheFragment.get(position);
        if (fragment == null) {
            if (position == 0) {
                fragment = new HomeFragment();
            } /*else if (position == 1) {
                fragment = new WeightFragment();
            } else if (position == 2) {
                fragment = new TemeraFragment();
            } else if (position == 3) {
                fragment = new BloodFragment();
            } else if (position == 4) {
                fragment = new BloodPressureFragment();
            } else if (position == 5) {
                fragment = new HomeBeforeBloodFragment();
            } else if (position == 6) {
                fragment = new CompositionFragment();
            }*/ else if (position == 7) {
                fragment = new CurveFragment();
            } else if (position == 8) {
                fragment = new HistoryFragment();
            } else if (position == 9) {
                fragment = new SettingFragment();
            }
            cacheFragment.put(position, fragment);
        }
        return fragment;
    }
}
