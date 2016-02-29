package com.yuenkeji.heyjk.fragment;

import android.content.Intent;
import android.graphics.Color;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.yuenkeji.heyjk.R;
import com.yuenkeji.heyjk.activity.CurveActivity;
import com.yuenkeji.heyjk.utils.SortUtil;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * Created by hellofuhua on 2016/2/21.
 */
public class CurveFragment extends BaseFragment implements View.OnClickListener {
    public LinearLayout appTitle;
    public ImageView btnReturn;
    public ImageView btnConfig;
    public TextView titleHome;
    public String[] titleStrings = new String[]{"体重历史曲线", "体成分历史曲线", "血糖历史曲线", "血压历史曲线", "体温历史曲线"};
    private LinearLayout layoutHistoryThirdtitle;
    private ImageView iconHistoryTitle1;
    private ImageView iconHistoryTitle2;
    private ImageView iconHistoryTitle3;
    private ImageView iconHistoryTitle4;
    private ImageView iconHistoryTitle5;
    private LinearLayout layoutHistorySectitle;
    private Button btnDay;
    private Button btnWeek;
    private Button btnMonth;
    private int mHour;
    private int mMinute;
    private int mYear;
    private int mMonth;
    private int mDay;
    private List<String> dateList;
    private SortUtil sortUtil;
    private int history_bot;
    private int history_top;
    private ListView mLvCurve;
    private MyAdapter myAdapter;

    private void assignViews(View view) {
        layoutHistorySectitle = (LinearLayout) view.findViewById(R.id.layout_history_sectitle);
        sortUtil = new SortUtil();
        history_bot = sortUtil.getHISTORY_BOT();
        history_top = sortUtil.getHISTORY_TOP();
        btnDay = (Button) view.findViewById(R.id.btn_day);
        btnWeek = (Button) view.findViewById(R.id.btn_week);
        btnMonth = (Button) view.findViewById(R.id.btn_month);
        btnReturn = (ImageView) view.findViewById(R.id.btn_return);
        btnConfig = (ImageView) view.findViewById(R.id.btn_confirm);
        titleHome = (TextView) view.findViewById(R.id.tv_title_home);
        appTitle = (LinearLayout) view.findViewById(R.id.app_title);
        layoutHistoryThirdtitle = (LinearLayout) view.findViewById(R.id.layout_history_thirdtitle);
        iconHistoryTitle1 = (ImageView) view.findViewById(R.id.icon_history_title1);
        iconHistoryTitle2 = (ImageView) view.findViewById(R.id.icon_history_title2);
        iconHistoryTitle3 = (ImageView) view.findViewById(R.id.icon_history_title3);
        iconHistoryTitle4 = (ImageView) view.findViewById(R.id.icon_history_title4);
        iconHistoryTitle5 = (ImageView) view.findViewById(R.id.icon_history_title5);
        mLvCurve = (ListView) view.findViewById(R.id.lv_curve);
        iconHistoryTitle1.setOnClickListener(this);
        iconHistoryTitle2.setOnClickListener(this);
        iconHistoryTitle3.setOnClickListener(this);
        iconHistoryTitle4.setOnClickListener(this);
        iconHistoryTitle5.setOnClickListener(this);
        btnDay.setOnClickListener(this);
        btnMonth.setOnClickListener(this);
        btnWeek.setOnClickListener(this);

        sortUtil.setHISTORY_TOP(1);
        sortUtil.setHISTORY_BOT(1);
        /**BaseExpandableListAdapter实现了ExpandableListAdapter*/
        final Calendar c = Calendar.getInstance();

        mYear = c.get(Calendar.YEAR); //获取当前年份

        mMonth = c.get(Calendar.MONTH);//获取当前月份
        mMonth = mMonth + 1;
        mDay = c.get(Calendar.DAY_OF_MONTH);//获取当前月份的日期号码

        mHour = c.get(Calendar.HOUR_OF_DAY);//获取当前的小时数

        mMinute = c.get(Calendar.MINUTE);//获取当前的分钟数


        dateList = new ArrayList<>();
        SetForTest(1, 1);
        myAdapter = new MyAdapter();
        mLvCurve.setAdapter(myAdapter);
        initTitleBar(0);
        mLvCurve.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getActivity(), CurveActivity.class);
                startActivity(intent);
            }
        });
    }

    public void SetForTest(int aaa, int bbb) {
        dateList.clear();
        for (int i = 0; i < 5; i++) {

            String date = mYear + "年" + mMonth + "月" + i + "****" + aaa + "****" + bbb;
            Log.d("HistoryFragment*******", date);
            dateList.add(date);
        }
    }

    public void setTop() {
        if (sortUtil.getHISTORY_BOT() == 1) {
            SetForTest(sortUtil.getHISTORY_TOP(), sortUtil.getHISTORY_BOT());
        } else if (sortUtil.getHISTORY_BOT() == 2) {
            SetForTest(sortUtil.getHISTORY_TOP(), sortUtil.getHISTORY_BOT());
        } else if (sortUtil.getHISTORY_BOT() == 3) {
            SetForTest(sortUtil.getHISTORY_TOP(), sortUtil.getHISTORY_BOT());
        }
    }

    public void setBottom() {
        if (sortUtil.getHISTORY_TOP() == 1) {
            SetForTest(sortUtil.getHISTORY_TOP(), sortUtil.getHISTORY_BOT());
        } else if (sortUtil.getHISTORY_TOP() == 2) {
            SetForTest(sortUtil.getHISTORY_TOP(), sortUtil.getHISTORY_BOT());
        } else if (sortUtil.getHISTORY_TOP() == 3) {
            SetForTest(sortUtil.getHISTORY_TOP(), sortUtil.getHISTORY_BOT());
        } else if (sortUtil.getHISTORY_TOP() == 4) {
            SetForTest(sortUtil.getHISTORY_TOP(), sortUtil.getHISTORY_BOT());
        } else {
            SetForTest(sortUtil.getHISTORY_TOP(), sortUtil.getHISTORY_BOT());
        }
    }

    @Override
    public View initView() {
        View view = View.inflate(getActivity(), R.layout.home_curve, null);
        assignViews(view);
        return view;
    }

    @Override
    public void initData() {

    }

    public void initTitleBar(int position) {
        appTitle.setVisibility(View.GONE);
        titleHome.setText(titleStrings[position]);
        titleHome.setVisibility(View.VISIBLE);
        btnConfig.setVisibility(View.VISIBLE);
        btnReturn.setVisibility(View.VISIBLE);
    }

    public void resetTabBottom() {
        iconHistoryTitle1.setImageDrawable(getResources().getDrawable(R.drawable.icon_w_n2x));
        iconHistoryTitle2.setImageDrawable(getResources().getDrawable(R.drawable.icon_body_n2x));
        iconHistoryTitle3.setImageDrawable(getResources().getDrawable(R.drawable.icon_bg_n2x));
        iconHistoryTitle4.setImageDrawable(getResources().getDrawable(R.drawable.icon_bp_n2x));
        iconHistoryTitle5.setImageDrawable(getResources().getDrawable(R.drawable.icon_tem_n2x));

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.icon_history_title1:
                resetTabBottom();
                iconHistoryTitle1.setImageDrawable(getResources().getDrawable(R.drawable.icon_w_s2x));
                sortUtil.setHISTORY_TOP(1);
                setTop();
                setBottom();
                myAdapter = new MyAdapter();
                mLvCurve.setAdapter(myAdapter);
                initTitleBar(0);
                break;
            case R.id.icon_history_title2:
                resetTabBottom();
                iconHistoryTitle2.setImageDrawable(getResources().getDrawable(R.drawable.icon_body_s2x));
                sortUtil.setHISTORY_TOP(2);
                setTop();
                setBottom();
                myAdapter = new MyAdapter();
                mLvCurve.setAdapter(myAdapter);
                initTitleBar(1);
                break;
            case R.id.icon_history_title3:
                resetTabBottom();
                iconHistoryTitle3.setImageDrawable(getResources().getDrawable(R.drawable.icon_bg_s2x));
                sortUtil.setHISTORY_TOP(3);
                setTop();
                setBottom();
                mLvCurve.setAdapter(myAdapter);
                initTitleBar(2);
                break;
            case R.id.icon_history_title4:
                resetTabBottom();
                iconHistoryTitle4.setImageDrawable(getResources().getDrawable(R.drawable.icon_bp_s2x));
                sortUtil.setHISTORY_TOP(4);
                setTop();
                setBottom();
                mLvCurve.setAdapter(myAdapter);
                initTitleBar(3);
                break;
            case R.id.icon_history_title5:
                resetTabBottom();
                iconHistoryTitle5.setImageDrawable(getResources().getDrawable(R.drawable.icon_tem_s2x));
                sortUtil.setHISTORY_TOP(5);
                setTop();
                setBottom();
                mLvCurve.setAdapter(myAdapter);
                initTitleBar(4);
                break;
            case R.id.btn_day:
                resetButtonColor(btnDay);
                sortUtil.setHISTORY_BOT(1);
                setBottom();
                setTop();
                mLvCurve.setAdapter(myAdapter);

                break;

            case R.id.btn_week:
                resetButtonColor(btnWeek);
                sortUtil.setHISTORY_BOT(2);
                setBottom();
                setTop();
                mLvCurve.setAdapter(myAdapter);

                break;
            case R.id.btn_month:
                resetButtonColor(btnMonth);
                sortUtil.setHISTORY_BOT(3);
                setBottom();
                setTop();
                mLvCurve.setAdapter(myAdapter);

                break;
            case R.id.btn_confirm:

                Toast.makeText(getActivity(), "提交", Toast.LENGTH_SHORT).show();
                break;
            case R.id.btn_return:

                Toast.makeText(getActivity(), "返回", Toast.LENGTH_SHORT).show();

                break;
        }

    }

    public void resetButtonColor(Button btn) {
        btnDay.setBackgroundColor(Color.WHITE);
        btnMonth.setBackgroundColor(Color.WHITE);
        btnWeek.setBackgroundColor(Color.WHITE);
        btn.setBackgroundColor(Color.TRANSPARENT);
    }

    class MyAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            return dateList.size();
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ViewHolder viewHolder;
            if (convertView == null) {
                convertView = View.inflate(getActivity(), R.layout.elv_parent, null);
                viewHolder = new ViewHolder(convertView);
                convertView.setTag(viewHolder);
            } else {
                viewHolder = (ViewHolder) convertView.getTag();
            }
            viewHolder.tvelvweightparent.setText(dateList.get(position));
            return convertView;
        }

        public class ViewHolder {
            public final TextView tvelvweightparent;
            public final View root;

            public ViewHolder(View root) {
                this.root = root;
                tvelvweightparent = (TextView) root.findViewById(R.id.tv_elv_weight_parent);
            }
        }
    }

    class ViewHolder {
        public TextView textView;
    }

}
