package com.yuenkeji.heyjk.viewpager.historycubPagers;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.TextView;

import com.yuenkeji.heyjk.R;
import com.yuenkeji.heyjk.viewpager.BasePager;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * Created by Administrator on 2016/2/24.
 */
public class WeightMonthPager extends BasePager{
    private ExpandableListView elvWeightDay;
    private Context context;
    private int mHour;
    private int mMinute;
    private int mYear;
    private int mMonth;
    private int mDay;
    private List<String> dateList;
    private List<String> timeList;
    public WeightMonthPager(Context context) {
        super(context);
        this.context = context;
    }
    private void assignViews(View view) {
        elvWeightDay = (ExpandableListView) view.findViewById(R.id.elv_weight);
    }
    @Override
    public View initView() {
        View view = View.inflate(context, R.layout.history_weight_day, null);
        assignViews(view);
        final Calendar c = Calendar.getInstance();
        mYear = c.get(Calendar.YEAR); //获取当前年份
        mMonth = c.get(Calendar.MONTH);//获取当前月份
        mMonth = mMonth + 1;
        mDay = c.get(Calendar.DAY_OF_MONTH);//获取当前月份的日期号码
        mHour = c.get(Calendar.HOUR_OF_DAY);//获取当前的小时数
        mMinute = c.get(Calendar.MINUTE);//获取当前的分钟数
        dateList = new ArrayList<>();
        timeList = new ArrayList<>();
        for (int i = 1; i < 13; i++) {
            String date = mYear + "年" + i + "月";
            dateList.add(date);
        }
        for (int i = 0; i < 12; i++) {
            String time = mHour + ":" + mMinute + i;
            timeList.add(time);
        }
        elvWeightDay.setAdapter(adapter);
        elvWeightDay.setGroupIndicator(null);
        elvWeightDay.setChildIndicator(null);
        return view;
    }
    /**
     * BaseExpandableListAdapter实现了ExpandableListAdapter
     */
    private ExpandableListAdapter adapter = new BaseExpandableListAdapter() {
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
            ViewHolder viewHolder = null;
            if (convertView == null) {
                viewHolder = new ViewHolder();
                convertView = View.inflate(context, R.layout.elv_weight_day_parent, null);
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
                convertView = View.inflate(context, R.layout.elv_weight_day_child, null);
                viewHolder.textView = (TextView) convertView.findViewById(R.id.tv_elv_weight_parent);
                viewHolder.textViewchild = (TextView) convertView.findViewById(R.id.tv_elv_weight_child);
                viewHolder.textView.setTextSize(20);
                viewHolder.textViewchild.setTextSize(20);
                convertView.setTag(viewHolder);
            } else {
                viewHolder = (ViewHolder) convertView.getTag();
            }
            viewHolder.textView.setText(getChild(groupPosition, childPosition).toString());
            viewHolder.textViewchild.setText("要显示的内容");
            return convertView;
        }

        //孩子在指定的位置是可选的，即：arms中的元素是可点击的
        @Override
        public boolean isChildSelectable(int groupPosition,
                                         int childPosition) {
            return true;
        }

        //表示孩子是否和组ID是跨基础数据的更改稳定
        public boolean hasStableIds() {
            return true;
        }
    };
    @Override
    public void initData() {

    }
    class ViewHolder {
        public TextView textView;
        public TextView textViewchild;
    }
}
