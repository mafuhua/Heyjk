package com.yuenkeji.heyjk.fragment;

import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.yuenkeji.heyjk.R;
import com.yuenkeji.heyjk.viewpager.BasePager;
import com.yuenkeji.heyjk.viewpager.MyViewPager;
import com.yuenkeji.heyjk.viewpager.homesubPagers.AfterBloodPager;
import com.yuenkeji.heyjk.viewpager.homesubPagers.BeforeBloodPager;
import com.yuenkeji.heyjk.viewpager.homesubPagers.BloodPressurePager;
import com.yuenkeji.heyjk.viewpager.homesubPagers.CompositionPager;
import com.yuenkeji.heyjk.viewpager.homesubPagers.HomePage;
import com.yuenkeji.heyjk.viewpager.homesubPagers.TemperaPager;
import com.yuenkeji.heyjk.viewpager.homesubPagers.WeightPager;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by hellofuhua on 2016/2/21.
 */
public class HomeFragment extends BaseFragment implements View.OnClickListener {
    public String[] titleStrings = new String[]{"体重测量", "体成分测量", "餐前测量", "餐后测量", "血压测量", "体温测量"};
    public MyViewPager myViewPagers;
    public ImageView btnReturn;
    public ImageView btnConfig;
    public TextView titleHome;
    public LinearLayout layoutTitleBar;
    public HomePage homePage;
    public List<BasePager> pagers = new ArrayList<BasePager>();
    public LinearLayout appTitle;
    public boolean flag = false;

    @Override
    public View initView() {
        View view = View.inflate(getActivity(), R.layout.home, null);
        myViewPagers = (MyViewPager) view.findViewById(R.id.myViewPager);
        btnReturn = (ImageView) view.findViewById(R.id.btn_return);
        btnConfig = (ImageView) view.findViewById(R.id.btn_confirm);
        titleHome = (TextView) view.findViewById(R.id.tv_title_home);
        appTitle = (LinearLayout) view.findViewById(R.id.app_title);
        layoutTitleBar = (LinearLayout) view.findViewById(R.id.layout_titlebar);
        btnConfig.setOnClickListener(this);
        btnReturn.setOnClickListener(this);
        return view;
    }

    public void initTitlebar(int position) {
        appTitle.setVisibility(View.GONE);
        titleHome.setText(titleStrings[position - 1]);
        titleHome.setVisibility(View.VISIBLE);
        btnConfig.setVisibility(View.VISIBLE);
        btnReturn.setVisibility(View.VISIBLE);

    }

    public void initHomeTitleBar() {
        flag = true;
        appTitle.setVisibility(View.VISIBLE);
        btnReturn.setVisibility(View.GONE);
        btnConfig.setVisibility(View.GONE);
        titleHome.setVisibility(View.GONE);
    }

    public void subhomepager(int position) {
        myViewPagers.setAdapter(new MyAdapter());
        myViewPagers.setCurrentItem(position);
        switch (position) {
            case 1:
                initTitlebar(position);
                break;
            case 2:
                initTitlebar(position);

                break;
            case 3:
                initTitlebar(position);

                break;
            case 4:
                initTitlebar(position);

                break;
            case 5:
                initTitlebar(position);

                break;
            case 6:
                initTitlebar(position);

                break;


        }
    }

    @Override
    public void initData() {
        homePage = new HomePage(getActivity());
        homePage.setOnBTClickListener(new HomePage.OnBTClickListener() {
            @Override
            public void onSubClick(int position) {
                subhomepager(position);
            }
        });
        pagers.add(homePage);
        pagers.add(new WeightPager(getActivity()));
        pagers.add(new CompositionPager(getActivity()));
        pagers.add(new BeforeBloodPager(getActivity()));
        pagers.add(new AfterBloodPager(getActivity()));
        pagers.add(new BloodPressurePager(getActivity()));
        pagers.add(new TemperaPager(getActivity()));
        myViewPagers.setAdapter(new MyAdapter());
        myViewPagers.setCurrentItem(0);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_confirm:
                myViewPagers.setCurrentItem(0);
                initHomeTitleBar();
                flag = false;
                Toast.makeText(getActivity(), "提交", Toast.LENGTH_SHORT).show();
                break;
            case R.id.btn_return:
                myViewPagers.setCurrentItem(0);
                initHomeTitleBar();
                flag = false;
                Toast.makeText(getActivity(), "返回", Toast.LENGTH_SHORT).show();

                break;
        }
    }

    class MyAdapter extends PagerAdapter {

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View) object);
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            BasePager currentPager = pagers.get(position);
            View currentPagerView = currentPager.initView();
            container.addView(currentPagerView);
            currentPager.initData();
            return currentPagerView;
        }

        @Override
        public int getCount() {
            return pagers.size();
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }
    }
}
