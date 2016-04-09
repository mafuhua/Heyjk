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

import com.google.gson.Gson;
import com.yuenkeji.heyjk.R;
import com.yuenkeji.heyjk.activity.HistoryChildActivity;
import com.yuenkeji.heyjk.activity.MainActivity;
import com.yuenkeji.heyjk.bean.AllDayWeightBean;
import com.yuenkeji.heyjk.bean.AllMonthWeightBean;
import com.yuenkeji.heyjk.bean.AllWeekWeightBean;
import com.yuenkeji.heyjk.utils.SortUtil;
import com.yuenkeji.heyjk.utils.WEBUtils;
import com.yuenkeji.heyjk.utils.XUtils;

import org.xutils.common.Callback;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by hellofuhua on 2016/2/21.
 */
public class HistoryFragment extends BaseFragment implements View.OnClickListener {
    public LinearLayout appTitle;
    public ImageView btnReturn;
    public ImageView btnConfig;
    public TextView titleHome;
    public String[] titleStrings = new String[]{"体重历史数据", "体成分历史数据", "血糖历史数据", "血压历史数据", "体温历史数据"};
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
    private List<String> dateList;
    private List<String> timeList;
    public static SortUtil sortUtil;
    private ListView historyListView;
    private MyAdapter myAdapter;
    private List<AllDayWeightBean.DataEntity> allDayWeightBeandata;
    private HashMap<String, String> map;
    private List<AllMonthWeightBean.DataEntity> allMonthWeightBeandata;
    private List<AllWeekWeightBean.DataEntity> allWeekWeightBeandata;
    private int type = 0;

    private void assignViews(View view) {
        layoutHistorySectitle = (LinearLayout) view.findViewById(R.id.layout_history_sectitle);
        sortUtil = new SortUtil();
        btnDay = (Button) view.findViewById(R.id.btn_day);
        btnWeek = (Button) view.findViewById(R.id.btn_week);
        btnMonth = (Button) view.findViewById(R.id.btn_month);
        btnReturn = (ImageView) view.findViewById(R.id.btn_return);
        btnConfig = (ImageView) view.findViewById(R.id.btn_confirm);
        titleHome = (TextView) view.findViewById(R.id.tv_title_home);
        historyListView = (ListView) view.findViewById(R.id.historyListView);
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
        dateList = new ArrayList<>();
        timeList = new ArrayList<>();
        SetForTest(1, 1);
        initTitleBar(0);
        myAdapter = new MyAdapter();


    }

    public void SetForTest(int aaa, int bbb) {
        Log.d("mafuhua", aaa + "***" + bbb);
        if (aaa == 1 && bbb == 1) {
            getDayContent(map, WEBUtils.AllDayWeightUrl);
        } else if (aaa == 2 && bbb == 1) {
            getDayContent(map, WEBUtils.AllDayBodyUrl);
        } else if (aaa == 4 && bbb == 1) {
            getDayContent(map, WEBUtils.AllDayBloodpUrl);
        } else if (aaa == 5 && bbb == 1) {
            getDayContent(map, WEBUtils.AllDayTemperatureUrl);
        } else if (aaa == 1 && bbb == 2) {
            getDayContent(map, WEBUtils.AllWeekWeightUrl);
        } else if (aaa == 2 && bbb == 2) {
            getDayContent(map, WEBUtils.AllWeekBodyUrl);
        } else if (aaa == 4 && bbb == 2) {
            getDayContent(map, WEBUtils.AllWeekBloodpUrl);
        } else if (aaa == 5 && bbb == 2) {
            getDayContent(map, WEBUtils.AllWeekTemperatureUrl);
        } else if (aaa == 1 && bbb == 3) {
            getDayContent(map, WEBUtils.AllMonthWeightUrl);
        } else if (aaa == 2 && bbb == 3) {
            getDayContent(map, WEBUtils.AllMonthBodyUrl);
        } else if (aaa == 4 && bbb == 3) {
            getDayContent(map, WEBUtils.AllMonthBloodpUrl);
        } else if (aaa == 5 && bbb == 3) {
            getDayContent(map, WEBUtils.AllMonthTemperatureUrl);
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
        } else if (sortUtil.getHISTORY_TOP() == 5) {
            SetForTest(sortUtil.getHISTORY_TOP(), sortUtil.getHISTORY_BOT());
        }
    }

    @Override
    public View initView() {
        View view = View.inflate(getActivity(), R.layout.home_history, null);
        map = new HashMap<String, String>();
        map.put("user_id", MainActivity.userid);
        assignViews(view);
        return view;
    }

    @Override
    public void initData() {
        getDayContent(map, WEBUtils.AllDayWeightUrl);
        historyListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(getActivity(), "position:" + position, Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getActivity(), HistoryChildActivity.class);
                if (sortUtil.getHISTORY_BOT() == 1) {
                    AllDayWeightBean.DataEntity dataEntity = allDayWeightBeandata.get(position);
                    intent.putExtra("day", dataEntity.day);
                    intent.putExtra("month", dataEntity.month);
                    intent.putExtra("year", dataEntity.year);
                } else if (sortUtil.getHISTORY_BOT() == 2) {
                    AllWeekWeightBean.DataEntity dataEntity = allWeekWeightBeandata.get(position);
                    intent.putExtra("week", dataEntity.week);
                    intent.putExtra("month", dataEntity.month);
                    intent.putExtra("year", dataEntity.year);
                }else {
                    AllMonthWeightBean.DataEntity dataEntity = allMonthWeightBeandata.get(position);
                    intent.putExtra("month", dataEntity.month);
                    intent.putExtra("year", dataEntity.year);
                }
                startActivity(intent);
            }
        });
    }

    private void getDayContent(HashMap<String, String> map, String url) {
        XUtils.xUtilsPost(url, map, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                Log.d("mafuhua", result.toString());
                if (sortUtil.getHISTORY_BOT() == 1) {
                    parseDayWeight(result);
                } else if (sortUtil.getHISTORY_BOT() == 2) {
                    Log.d("mafuhua", "hah");
                    parseWeekWeight(result);
                } else {
                    parseMonthWeight(result);
                }
            }

            @Override
            public void onError(Throwable throwable, boolean b) {
                Log.d("mafuhua", "b1:" + b);
            }

            @Override
            public void onCancelled(CancelledException e) {

            }

            @Override
            public void onFinished() {
                Log.d("mafuhua", "wan1cheng");
            }
        });
    }

    private void parseMonthWeight(String result) {
        type = 0;
        dateList.clear();
        timeList.clear();
        Gson gson = new Gson();
        AllMonthWeightBean allMonthWeightBean = gson.fromJson(result, AllMonthWeightBean.class);
        allMonthWeightBeandata = allMonthWeightBean.data;
        for (int i = 0; i < allMonthWeightBeandata.size(); i++) {
            String month = allMonthWeightBeandata.get(i).month;
            String year = allMonthWeightBeandata.get(i).year;
            String date = year + "年" + month + "月";
            dateList.add(date);

        }
        historyListView.setAdapter(myAdapter);
        myAdapter.notifyDataSetChanged();
    }

    private void parseWeekWeight(String result) {
        dateList.clear();
        timeList.clear();
        type = 1;
        Gson gson = new Gson();
        AllWeekWeightBean allWeekWeightBean = gson.fromJson(result, AllWeekWeightBean.class);
        allWeekWeightBeandata = allWeekWeightBean.data;
        for (int i = 0; i < allWeekWeightBeandata.size(); i++) {
            String year = allWeekWeightBeandata.get(i).year;
            String month = allWeekWeightBeandata.get(i).month;
            String lastsunday = allWeekWeightBeandata.get(i).lastsunday;
            String thissaturday = allWeekWeightBeandata.get(i).thissaturday;
            String week = allWeekWeightBeandata.get(i).week;
            String date = year + "." + month + "." + lastsunday + "~" + year + "." + month + "." + thissaturday;
            timeList.add("第" + week + "周");
            dateList.add(date);


        }
        historyListView.setAdapter(myAdapter);
        myAdapter.notifyDataSetChanged();
    }

    private void parseDayWeight(String result) {
        dateList.clear();
        type = 0;
        Gson gson = new Gson();
        AllDayWeightBean allDayWeightBean = gson.fromJson(result, AllDayWeightBean.class);
        allDayWeightBeandata = allDayWeightBean.data;
        for (int i = 0; i < allDayWeightBeandata.size(); i++) {
            String day = allDayWeightBeandata.get(i).day;
            String month = allDayWeightBeandata.get(i).month;
            String year = allDayWeightBeandata.get(i).year;
            if (day.equals(null)) {
                String date = year + "年" + month + "月";
                dateList.add(date);
            } else {
                String date = year + "年" + month + "月" + day + "日";
                dateList.add(date);
            }


        }
        historyListView.setAdapter(myAdapter);
        myAdapter.notifyDataSetChanged();
    }

    public void initTitleBar(int position) {
        appTitle.setVisibility(View.GONE);
        titleHome.setText(titleStrings[position]);
        titleHome.setVisibility(View.VISIBLE);
        btnConfig.setVisibility(View.GONE);
        btnReturn.setVisibility(View.GONE);
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
                setBottom();
                initTitleBar(0);
                break;
            case R.id.icon_history_title2:
                resetTabBottom();
                iconHistoryTitle2.setImageDrawable(getResources().getDrawable(R.drawable.icon_body_s2x));
                sortUtil.setHISTORY_TOP(2);
                setBottom();
                initTitleBar(1);
                break;
            case R.id.icon_history_title3:
                resetTabBottom();
                iconHistoryTitle3.setImageDrawable(getResources().getDrawable(R.drawable.icon_bg_s2x));
                sortUtil.setHISTORY_TOP(3);
                setBottom();
                initTitleBar(2);
                break;
            case R.id.icon_history_title4:
                resetTabBottom();
                iconHistoryTitle4.setImageDrawable(getResources().getDrawable(R.drawable.icon_bp_s2x));
                sortUtil.setHISTORY_TOP(4);
                setBottom();
                initTitleBar(3);
                break;
            case R.id.icon_history_title5:
                resetTabBottom();
                iconHistoryTitle5.setImageDrawable(getResources().getDrawable(R.drawable.icon_tem_s2x));
                sortUtil.setHISTORY_TOP(5);
                setBottom();
                initTitleBar(4);
                break;
            case R.id.btn_day:
                resetButtonColor(btnDay);
                sortUtil.setHISTORY_BOT(1);
                setBottom();
                break;
            case R.id.btn_week:
                resetButtonColor(btnWeek);
                sortUtil.setHISTORY_BOT(2);
                setBottom();
                break;
            case R.id.btn_month:
                resetButtonColor(btnMonth);
                sortUtil.setHISTORY_BOT(3);
                setBottom();
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
                convertView = View.inflate(getActivity(), R.layout.elv_weight_day_parent, null);
                viewHolder = new ViewHolder(convertView);
                convertView.setTag(viewHolder);
            } else {
                viewHolder = (ViewHolder) convertView.getTag();
            }
            if (type == 1) {
                viewHolder.tvelvweightparent2.setText(dateList.get(position));
                viewHolder.tvelvweightparent22.setText(timeList.get(position));
            } else {
                viewHolder.tvelvweightparent2.setText(dateList.get(position));
            }

            return convertView;
        }

        public class ViewHolder {
            public final TextView tvelvweightparent2;
            public final TextView tvelvweightparent22;
            public final View root;

            public ViewHolder(View root) {
                this.root = root;
                tvelvweightparent2 = (TextView) root.findViewById(R.id.tv_elv_weight_parent2);
                tvelvweightparent22 = (TextView) root.findViewById(R.id.tv_elv_weight_parent22);
            }
        }
    }
}
