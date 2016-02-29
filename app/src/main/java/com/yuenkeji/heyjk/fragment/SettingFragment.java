package com.yuenkeji.heyjk.fragment;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.yuenkeji.heyjk.R;
import com.yuenkeji.heyjk.activity.LoginActivity;

import java.util.Calendar;
import java.util.HashMap;

/**
 * Created by hellofuhua on 2016/2/21.
 */
public class SettingFragment extends BaseFragment implements View.OnClickListener {

    private ListView lvSetting;
    private Button btSettingQuit;
    private String[] settingText = new String[]{"性别", "生日", "身高", "体重", "运动类型"};
    private HashMap<Integer, String> settingMap = new HashMap<>();
    private MyListAdapter myListAdapter;
    private int showYear;
    private int mYear;
    private int mMonth;
    private int mDay;
    private Calendar calendar;
    private void assignViews(View view) {
        lvSetting = (ListView) view.findViewById(R.id.lv_setting);
        btSettingQuit = (Button) view.findViewById(R.id.bt_setting_quit);
    }

    public void settingSex(final int position) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("请选择您的性别：");
        /*
         * 参数1：选项
		 * 参数2：默认值，-1说明没有默认值
		 */
        final String[] items = {"男", "女"};

        builder.setSingleChoiceItems(items, -1, new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
                settingMap.put(position, items[which]);
                myListAdapter.notifyDataSetChanged();
                dialog.dismiss();
            }
        });
        builder.show();
    }

    public void settingSport(final int position) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("请选择您的性别：");
        /*
         * 参数1：选项
		 * 参数2：默认值，-1说明没有默认值
		 */
        final String[] items = {"脑力劳动者", "体力劳动者", "运动员"};

        builder.setSingleChoiceItems(items, -1, new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
                settingMap.put(position, items[which]);
                myListAdapter.notifyDataSetChanged();
                dialog.dismiss();
            }
        });
        builder.show();
    }

    public void settingWeight(final int position, String str, final String hint) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("请输入" + str);
        final EditText editText = new EditText(getActivity());
        editText.setHint(hint);
        builder.setView(editText);
        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String weight = editText.getText().toString().trim();
                settingMap.put(position, weight + hint);
                myListAdapter.notifyDataSetChanged();
            }
        });
        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        AlertDialog dialog = builder.create();
        dialog.show();
    }

    public void settingBirthday(final int position) {

        DatePickerDialog datePickerDialog = new DatePickerDialog(getActivity(), new
                DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                        //mYear = year;
                        settingMap.put(position, year + "年" + monthOfYear + "月" + dayOfMonth +"日");
                        myListAdapter.notifyDataSetChanged();
                    }
                }, calendar.get(calendar.YEAR), calendar.get(calendar.MONTH), calendar.get(calendar.DAY_OF_MONTH));

        datePickerDialog.show();
    }

    @Override
    public View initView() {
        View view = View.inflate(getActivity(), R.layout.home_setting, null);
        assignViews(view);
        calendar = Calendar.getInstance();
        btSettingQuit.setOnClickListener(this);
        myListAdapter = new MyListAdapter();
        lvSetting.setAdapter(myListAdapter);
        lvSetting.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                switch (position) {
                    case 0:
                        settingSex(position);

                        break;
                    case 1:
                        settingBirthday(position);
                        break;
                    case 2:
                        settingWeight(position, "身高", "CM");
                        break;
                    case 3:
                        settingWeight(position, "体重", "KG");
                        break;
                    case 4:
                        settingSport(position);
                        break;


                }
            }
        });
        return view;
    }

    @Override
    public void initData() {

    }

    @Override
    public void onClick(View v) {

        switch (v.getId()){
            case R.id.bt_setting_quit:
                startActivity(new Intent(getActivity(), LoginActivity.class));
                getActivity().finish();
                break;
        }
    }

    class MyListAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            return settingText.length;
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
            MyListAdapter.ViewHolder viewHolder;
            if (convertView == null) {
                convertView = View.inflate(getActivity(), R.layout.lv_setting_item, null);
                viewHolder = new ViewHolder(convertView);
                convertView.setTag(viewHolder);
            } else {
                viewHolder = (ViewHolder) convertView.getTag();
            }
            viewHolder.tvsettingleft.setText(settingText[position]);
            viewHolder.tvsettingright.setText(settingMap.get(position));
            return convertView;
        }

        public class ViewHolder {
            public final TextView tvsettingleft;
            public final TextView tvsettingright;
            public final ImageView imageView6;
            public final View root;

            public ViewHolder(View root) {
                this.root = root;
                tvsettingleft = (TextView) root.findViewById(R.id.tv_setting_left);
                tvsettingright = (TextView) root.findViewById(R.id.tv_setting_right);
                imageView6 = (ImageView) root.findViewById(R.id.imageView6);
            }
        }
    }
}
