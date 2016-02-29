package com.yuenkeji.heyjk.fragment;

import android.graphics.Color;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.Button;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.yuenkeji.heyjk.R;
import com.yuenkeji.heyjk.utils.SortUtil;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * Created by hellofuhua on 2016/2/21.
 */
public class HistoryFragment extends BaseFragment implements View.OnClickListener {
    public LinearLayout appTitle;
    public ImageView btnReturn;
    public ImageView btnConfig;
    public TextView titleHome;
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
    private List<String> timeList;
    private FragmentManager fm;
    private FragmentTransaction ft;
    // private WeightFragment weightFragment;
    private SortUtil sortUtil;
    private int history_bot;
    private int history_top;
    private ExpandableListView expandableListView;
    private ExpandableListAdapter adapter;

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
        timeList = new ArrayList<>();
        SetForTest(1, 1);
        initTitleBar(0);
        adapter = new BaseExpandableListAdapter() {
/*===========组元素表示可折叠的列表项，子元素表示列表项展开后看到的多个子元素项=============*/

            /**----------得到armTypes和arms中每一个元素的ID-------------------------------------------*/

            //获取组在给定的位置编号，即armTypes中元素的ID
            @Override
            public long getGroupId(int groupPosition) {
                return groupPosition;
            }

            //获取在给定的组的儿童的ID，就是arms中元素的ID
            @Override
            public long getChildId(int groupPosition, int childPosition) {
                return childPosition;
            }

            /**----------根据上面得到的ID的值，来得到armTypes和arms中元素的个数 ------------------------*/

            //获取的群体数量，得到armTypes里元素的个数
            @Override
            public int getGroupCount() {
                return dateList.size();
            }

            //取得指定组中的儿童人数，就是armTypes中每一个种族它军种的个数
            @Override
            public int getChildrenCount(int groupPosition) {
                // return timeList.get(groupPosition).length();
                return timeList.size();
            }

            /**----------利用上面getGroupId得到ID，从而根据ID得到armTypes中的数据，并填到TextView中 -----*/

            //获取与给定的组相关的数据，得到数组armTypes中元素的数据
            @Override
            public Object getGroup(int groupPosition) {
                return dateList.get(groupPosition);
            }

            //获取一个视图显示给定组，存放armTypes
            @Override
            public View getGroupView(int groupPosition, boolean isExpanded,
                                     View convertView, ViewGroup parent) {
                ViewHolder viewHolder;
                if (convertView == null) {
                    viewHolder = new ViewHolder();
                    convertView = View.inflate(getActivity(), R.layout.elv_parent, null);
                    viewHolder.textView = (TextView) convertView.findViewById(R.id.tv_elv_weight_parent);
                    convertView.setTag(viewHolder);
                } else {
                    viewHolder = (ViewHolder) convertView.getTag();
                }
                viewHolder.textView.setText(getGroup(groupPosition).toString());
                return convertView;
            }

            /**----------利用上面getChildId得到ID，从而根据ID得到arms中的数据，并填到TextView中---------*/

            //获取与孩子在给定的组相关的数据,得到数组arms中元素的数据
            @Override
            public Object getChild(int groupPosition, int childPosition) {
                return timeList.get(childPosition);
            }

            //获取一个视图显示在给定的组 的儿童的数据，就是存放arms
            @Override
            public View getChildView(int groupPosition, int childPosition, boolean isLastChild,
                                     View convertView, ViewGroup parent) {
                ViewHolder viewHolder = null;
                if (convertView == null) {
                    viewHolder = new ViewHolder();
                    convertView = View.inflate(getActivity(), R.layout.elv_child, null);
                    viewHolder.textView = (TextView) convertView.findViewById(R.id.tv_elv_weight_parent);
                    convertView.setTag(viewHolder);
                } else {
                    viewHolder = (ViewHolder) convertView.getTag();
                }
                viewHolder.textView.setText(getChild(groupPosition, childPosition).toString());
                return convertView;
            }

            //孩子在指定的位置是可选的，即：arms中的元素是可点击的
            @Override
            public boolean isChildSelectable(int groupPosition, int childPosition) {
                return true;
            }

            //表示孩子是否和组ID是跨基础数据的更改稳定
            public boolean hasStableIds() {
                return true;
            }
        };
        expandableListView = (ExpandableListView) view.findViewById(R.id.expandableListView);
        expandableListView.setGroupIndicator(null);
        expandableListView.setAdapter(adapter);
        expandableListView.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {
            @Override
            public boolean onGroupClick(ExpandableListView parent, View v, int groupPosition, long id) {
                Toast.makeText(getActivity(), "groupPosition:" + groupPosition, Toast.LENGTH_SHORT).show();
                return false;
            }
        });
        expandableListView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {
                Toast.makeText(getActivity(), "group:" + groupPosition + "child:" + childPosition, Toast.LENGTH_SHORT).show();
                return false;
            }
        });
    }

    public void SetForTest(int aaa, int bbb) {
        dateList.clear();
        timeList.clear();
        for (int i = 0; i < 5; i++) {

            String date = mYear + "年" + mMonth + "月" + i + "****" + aaa + "****" + bbb;
            Log.d("HistoryFragment*******", date);
            dateList.add(date);
        }
        for (int i = 0; i < 3; i++) {

            String time = mHour + ":" + i + "en         t" + aaa;
            ;
            Log.d("*******", time);
            timeList.add(time);
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
        View view = View.inflate(getActivity(), R.layout.home_history, null);
        assignViews(view);

     /*   fm = getActivity().getSupportFragmentManager();
        ft = fm.beginTransaction();
        weightFragment = new WeightFragment();
        ft.replace(R.id.ll_history_content, weightFragment);
        ft.commit();*/
        return view;
    }

    @Override
    public void initData() {

    }
    public String[] titleStrings = new String[]{"体重历史数据", "体成分历史数据", "血糖历史数据", "血压历史数据", "体温历史数据"};
    public void initTitleBar(int position) {
        appTitle.setVisibility(View.GONE);
        titleHome.setText(titleStrings[position]);
        titleHome.setVisibility(View.VISIBLE);
        btnConfig.setVisibility(View.VISIBLE);
        btnReturn.setVisibility(View.VISIBLE);}
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
                expandableListView.setAdapter(adapter);
                break;
            case R.id.icon_history_title2:
                resetTabBottom();
                iconHistoryTitle2.setImageDrawable(getResources().getDrawable(R.drawable.icon_body_s2x));
                sortUtil.setHISTORY_TOP(2);
                setTop();
                setBottom();
                expandableListView.setAdapter(adapter);
                break;
            case R.id.icon_history_title3:
                resetTabBottom();
                iconHistoryTitle3.setImageDrawable(getResources().getDrawable(R.drawable.icon_bg_s2x));
                sortUtil.setHISTORY_TOP(3);
                setTop();
                setBottom();
                expandableListView.setAdapter(adapter);
                break;
            case R.id.icon_history_title4:
                resetTabBottom();
                iconHistoryTitle4.setImageDrawable(getResources().getDrawable(R.drawable.icon_bp_s2x));
                sortUtil.setHISTORY_TOP(4);
                setTop();
                setBottom();
                expandableListView.setAdapter(adapter);
                break;
            case R.id.icon_history_title5:
                resetTabBottom();
                iconHistoryTitle5.setImageDrawable(getResources().getDrawable(R.drawable.icon_tem_s2x));
                sortUtil.setHISTORY_TOP(5);
                setTop();
                setBottom();
                expandableListView.setAdapter(adapter);
                break;
            case R.id.btn_day:
                resetButtonColor(btnDay);
                sortUtil.setHISTORY_BOT(1);
                setBottom();
                setTop();
                expandableListView.setAdapter(adapter);
                break;

            case R.id.btn_week:
                resetButtonColor(btnWeek);
                sortUtil.setHISTORY_BOT(2);
                setBottom();
                setTop();
                expandableListView.setAdapter(adapter);
                break;
            case R.id.btn_month:
                resetButtonColor(btnMonth);
                sortUtil.setHISTORY_BOT(3);
                setBottom();
                setTop();
                expandableListView.setAdapter(adapter);
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

    class ViewHolder {
        public TextView textView;
    }
}
