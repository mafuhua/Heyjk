package com.yuenkeji.heyjk.fragment;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.util.Log;
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
import android.widget.Toast;

import com.google.gson.Gson;
import com.yuenkeji.heyjk.R;
import com.yuenkeji.heyjk.activity.MainActivity;
import com.yuenkeji.heyjk.bean.LookUserBean;
import com.yuenkeji.heyjk.utils.WEBUtils;
import com.yuenkeji.heyjk.utils.XUtils;

import org.xutils.common.Callback;

import java.util.Calendar;
import java.util.HashMap;

/**
 * Created by hellofuhua on 2016/2/21.
 */
public class SettingFragment extends BaseFragment implements View.OnClickListener {

    private ListView lvSetting;
    private Button btSettingQuit;
    private String[] settingText = new String[]{"性别", "年龄", "身高", "体重", "运动类型"};
    private HashMap<Integer,String> settingMap = new HashMap();
    private MyListAdapter myListAdapter;
    private int showYear;
    private int mYear;
    private int mMonth;
    private int mDay;
    private Calendar calendar;
    private SharedPreferences sharedPreferences;
    private ImageView btn_confirm;
    private String birth;
    private String weight;

    private void assignViews(View view) {
        sharedPreferences = getActivity().getSharedPreferences("userinfo", Context.MODE_PRIVATE);
        lvSetting = (ListView) view.findViewById(R.id.lv_setting);
        btSettingQuit = (Button) view.findViewById(R.id.bt_setting_quit);
        btn_confirm = (ImageView) view.findViewById(R.id.btn_confirm);
        btn_confirm.setVisibility(View.VISIBLE);
        btn_confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String sextype = settingMap.get(0);
                if (sextype.equals("男")) {
                    sextype = "0";
                } else if (sextype.equals("女")) {
                    sextype = "1";
                }
                String sporttype = settingMap.get(4);
                if (sporttype.equals("脑力劳动者")) {
                    sporttype = "0";
                } else if (sporttype.equals("体力劳动者")) {
                    sporttype = "1";
                } else if (sporttype.equals("运动员")) {
                    sporttype = "2";
                }
                HashMap<String, String> map = new HashMap<>();
                map.put("user_id", MainActivity.userid);
                map.put("sex", sextype);
                map.put("birthday", settingMap.get(1));
                map.put("height", settingMap.get(2));
                map.put("weight", settingMap.get(3));
                map.put("sport", sporttype);
                XUtils.xUtilsPost(WEBUtils.UpdateUserUrl, map, new Callback.CommonCallback<String>() {
                    @Override
                    public void onSuccess(String result) {
                        Log.d("mafuhua", "UpdateUserUrl=====" + result);
                        Toast.makeText(getActivity(), "提交成功", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onError(Throwable ex, boolean isOnCallback) {

                    }

                    @Override
                    public void onCancelled(CancelledException cex) {

                    }

                    @Override
                    public void onFinished() {

                    }
                });
            }
        });

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
                settingMap.put(0, items[which]);
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
                settingMap.put(4, items[which]);
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
                weight = editText.getText().toString().trim();
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
                        settingMap.put(1, year + "年" + monthOfYear + "月" + dayOfMonth + "日");
                        birth = year + monthOfYear + dayOfMonth + "";
                        myListAdapter.notifyDataSetChanged();
                    }
                }, calendar.get(calendar.YEAR), calendar.get(calendar.MONTH), calendar.get(calendar.DAY_OF_MONTH));

        datePickerDialog.show();
    }

    @Override
    public View initView() {
        View view = View.inflate(getActivity(), R.layout.home_setting, null);
        assignViews(view);
        sharedPreferences = getActivity().getSharedPreferences("userinfo", Context.MODE_PRIVATE);
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
                        settingWeight(position, "年龄", "");
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
        HashMap<String, String> map = new HashMap<>();
        map.put("user_id", MainActivity.userid);
        XUtils.xUtilsPost(WEBUtils.LookUserUrl, map, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                Log.d("mafuhua", "LookUserUrl======="+result);

                parseJson(result);
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {

            }

            @Override
            public void onCancelled(CancelledException cex) {

            }

            @Override
            public void onFinished() {

            }
        });
    }

    private void parseJson(String result) {
        Gson gson = new Gson();
        LookUserBean lookUserBean = gson.fromJson(result, LookUserBean.class);
        if (lookUserBean.data.get(0).sex.equals("0")) {
            settingMap.put(0, "男");
        } else if (lookUserBean.data.get(0).sex.equals("1")) {
            settingMap.put(0, "女");
        }

        settingMap.put(1, lookUserBean.data.get(0).birthday);
        settingMap.put(2, lookUserBean.data.get(0).height);
        settingMap.put(3, lookUserBean.data.get(0).weight);
        settingMap.put(4, lookUserBean.data.get(0).sport);
        if (lookUserBean.data.get(0).sport.equals("0")) {
            settingMap.put(4, "脑力劳动者");
        } else if (lookUserBean.data.get(0).sport.equals("1")) {
            settingMap.put(4, "体力劳动者");
        } else if (lookUserBean.data.get(0).sport.equals("2")) {
            settingMap.put(4, "运动员");
        }
        Log.d("mafuhua", "settingMap:" + settingMap);
        myListAdapter.notifyDataSetChanged();


    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.bt_setting_quit:
                sharedPreferences.edit().putString("usernum", "").apply();
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
            if (settingMap.size()<1){
                viewHolder.tvsettingleft.setText(settingText[position]);
            }else {
                viewHolder.tvsettingleft.setText(settingText[position]);
                viewHolder.tvsettingright.setText(settingMap.get(position));
            }


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
