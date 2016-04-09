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

            } else if (position == 7) {
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
