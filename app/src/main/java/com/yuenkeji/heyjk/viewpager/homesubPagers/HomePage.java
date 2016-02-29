package com.yuenkeji.heyjk.viewpager.homesubPagers;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.yuenkeji.heyjk.R;
import com.yuenkeji.heyjk.viewpager.BasePager;

/**
 * 主界面
 * Created by Administrator on 2016/2/19.
 */
public class HomePage extends BasePager implements View.OnClickListener {

    private LinearLayout llWeightmeasure;
    private LinearLayout llCompisition;
    private TextView tvBeforelunch;
    private ImageView img06Homepage2x;
    private TextView tvAfterlunch;
    private LinearLayout llBloodpressure;
    private LinearLayout llTempera;
  //  private List<BasePager> subpagers = new ArrayList<BasePager>();
    private OnBTClickListener onBTClickListener;
    public HomePage(Context context) {
        super(context);
        this.context = context;
    }

    private void assignViews(View view) {
        llWeightmeasure = (LinearLayout) view.findViewById(R.id.ll_weightmeasure);
        llCompisition = (LinearLayout) view.findViewById(R.id.ll_compisition);
        tvBeforelunch = (TextView) view.findViewById(R.id.tv_beforelunch);
        img06Homepage2x = (ImageView) view.findViewById(R.id.img_06_homepage2x);
        tvAfterlunch = (TextView) view.findViewById(R.id.tv_afterlunch);
        llBloodpressure = (LinearLayout) view.findViewById(R.id.ll_bloodpressure);
        llTempera = (LinearLayout) view.findViewById(R.id.ll_tempera);
        llWeightmeasure.setOnClickListener(this);
        llBloodpressure.setOnClickListener(this);
        llCompisition.setOnClickListener(this);
        llTempera.setOnClickListener(this);
        llWeightmeasure.setOnClickListener(this);
        img06Homepage2x.setOnClickListener(this);
        tvAfterlunch.setOnClickListener(this);
        tvBeforelunch.setOnClickListener(this);
    }

/*    public List<BasePager> getSubPagers() {
        return subpagers;
    }*/

    public Context getContext() {
        return context;
    }


    @Override
    public View initView() {

        View view = View.inflate(context, R.layout.home_sub_menu, null);
        assignViews(view);
        return view;

    }

    @Override
    public void initData() {


    }

    @Override
    public void onClick(View v) {
        int position = 0;
        switch (v.getId()) {
            case R.id.ll_weightmeasure:
                position = 1;

                break;
            case R.id.ll_compisition:
                position = 2;

                break;
            case R.id.tv_beforelunch:
                position = 3;

                break;
            case R.id.tv_afterlunch:
                position = 4;

                break;
            case R.id.ll_bloodpressure:
                position = 5;

                break;
            case R.id.ll_tempera:
                position = 6;

                break;

        }
        if (onBTClickListener != null) {
            onBTClickListener.onSubClick(position);
        }
    }

    public void setOnBTClickListener(OnBTClickListener onBTClickListener) {
        this.onBTClickListener = onBTClickListener;
    }

    public interface OnBTClickListener {
        void onSubClick(int position);
    }

}
