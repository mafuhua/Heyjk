package com.yuenkeji.heyjk.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.google.gson.Gson;
import com.yuenkeji.heyjk.bean.MouDayBloopBean;
import com.yuenkeji.heyjk.bean.MouDayBodyBean;
import com.yuenkeji.heyjk.bean.MouDayTemBean;
import com.yuenkeji.heyjk.bean.MouDayWeightBean;
import com.yuenkeji.heyjk.bean.MouMonthBloodpBean;
import com.yuenkeji.heyjk.bean.MouMonthBodyBean;
import com.yuenkeji.heyjk.bean.MouMonthTemBean;
import com.yuenkeji.heyjk.bean.MouWeekBloodpBean;
import com.yuenkeji.heyjk.bean.MouWeekBody;
import com.yuenkeji.heyjk.bean.MouWeekTemBean;
import com.yuenkeji.heyjk.bean.MouWeekWeight;
import com.yuenkeji.heyjk.fragment.CurveFragment;
import com.yuenkeji.heyjk.fragment.HistoryFragment;
import com.yuenkeji.heyjk.utils.SortUtil;
import com.yuenkeji.heyjk.utils.WEBUtils;
import com.yuenkeji.heyjk.utils.XUtils;

import org.achartengine.ChartFactory;
import org.achartengine.chart.PointStyle;
import org.achartengine.model.XYMultipleSeriesDataset;
import org.achartengine.model.XYSeries;
import org.achartengine.renderer.XYMultipleSeriesRenderer;
import org.achartengine.renderer.XYSeriesRenderer;
import org.xutils.common.Callback;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class CurveActivity extends Activity {
    public Gson gson;
    private Context context;
    private SortUtil sortUtil;
    private String day;
    private String month;
    private String year;
    private String week;
    private List<String> XLabel = new ArrayList<>();
    private List<String> dataXList = new ArrayList<>();
    private List<String> dataYList = new ArrayList<>();
    private List<String> dataBotList = new ArrayList<>();
    private String[] titles;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = this;
        sortUtil = HistoryFragment.sortUtil;
        gson = new Gson();
        Intent intent = getIntent();
        year = intent.getStringExtra("year");
        month = intent.getStringExtra("month");
        if (CurveFragment.sortUtil.getHISTORY_BOT() == 1) {
            day = intent.getStringExtra("day");
            if (CurveFragment.sortUtil.getHISTORY_TOP() == 1) {
                getDayChild(WEBUtils.MouDayWeightUrl);
            } else if (CurveFragment.sortUtil.getHISTORY_TOP() == 2) {
                getDayChild(WEBUtils.MouDayBodyUrl);
            } else if (CurveFragment.sortUtil.getHISTORY_TOP() == 4) {
                getDayChild(WEBUtils.MouDayBloodpUrl);
            } else if (CurveFragment.sortUtil.getHISTORY_TOP() == 5) {
                getDayChild(WEBUtils.MouDayTemperatureUrl);
            }

        } else if (CurveFragment.sortUtil.getHISTORY_BOT() == 2) {
            week = intent.getStringExtra("week");
            if (CurveFragment.sortUtil.getHISTORY_TOP() == 1) {
                getWeekChild(WEBUtils.MouWeekWeightUrl);
            } else if (CurveFragment.sortUtil.getHISTORY_TOP() == 2) {
                getWeekChild(WEBUtils.MouWeekBodyUrl);
            } else if (CurveFragment.sortUtil.getHISTORY_TOP() == 4) {
                getWeekChild(WEBUtils.MouWeekBloodpUrl);
            } else if (CurveFragment.sortUtil.getHISTORY_TOP() == 5) {
                getWeekChild(WEBUtils.MouWeekTemperatureUrl);
            }
        } else if (CurveFragment.sortUtil.getHISTORY_BOT() == 3) {
            if (CurveFragment.sortUtil.getHISTORY_TOP() == 1) {
                getMonthChild(WEBUtils.MouMonthWeightUrl);
            } else if (CurveFragment.sortUtil.getHISTORY_TOP() == 2) {
                getMonthChild(WEBUtils.MouMonthBodyUrl);
            } else if (CurveFragment.sortUtil.getHISTORY_TOP() == 4) {
                getMonthChild(WEBUtils.MouMonthBloodpUrl);
            } else if (CurveFragment.sortUtil.getHISTORY_TOP() == 5) {
                getMonthChild(WEBUtils.MouMonthTemperatureUrl);
            }
        }
        creatCurveTu();
    }

    private void creatCurveTu() {
        String[] titles = new String[]{"脂肪率", "水分率", "菇凉", "体温"};
        List x = new ArrayList();
        List y = new ArrayList();
        x.add(new double[]{5, 9, 4, 6, 3, 15});
        x.add(new double[]{1, 3, 5, 7, 9, 11});
        x.add(new double[]{9, 3, 5, 7, 9, 1});
        x.add(new double[]{0, 2, 4, 6, 8, 10});

        y.add(new double[]{3, 14, 5, 30, 20, 25});
        y.add(new double[]{18, 9, 21, 15, 10, 6});
        y.add(new double[]{13, 4, 15, 3, 25, 20});
        y.add(new double[]{12, 9, 21, 19, 15, 16});
        XYMultipleSeriesDataset dataset = buildDataset(titles, x, y);

        int[] colors = new int[]{Color.BLUE, Color.BLACK, Color.RED, Color.GREEN};
        PointStyle[] styles = new PointStyle[]{PointStyle.CIRCLE, PointStyle.DIAMOND, PointStyle.SQUARE, PointStyle.TRIANGLE};
        XYMultipleSeriesRenderer renderer = buildRenderer(colors, styles, true);

        setChartSettings(renderer, "Line Chart Demo", "X", "Y", -1, 20, 0, 40, Color.WHITE, Color.WHITE);

        View chart = ChartFactory.getLineChartView(this, dataset, renderer);

        setContentView(chart);
    }

    protected XYMultipleSeriesDataset buildDataset(String[] titles,
                                                   List xValues,
                                                   List yValues) {
        XYMultipleSeriesDataset dataset = new XYMultipleSeriesDataset();

        int length = titles.length;                  //有几条线
        for (int i = 0; i < length; i++) {
            XYSeries series = new XYSeries(titles[i]);    //根据每条线的名称创建
            double[] xV = (double[]) xValues.get(i);                 //获取第i条线的数据
            double[] yV = (double[]) yValues.get(i);
            int seriesLength = xV.length;                 //有几个点

            for (int k = 0; k < seriesLength; k++)        //每条线里有几个点
            {
                series.add(xV[k], yV[k]);
            }

            dataset.addSeries(series);
        }

        return dataset;
    }

    protected XYMultipleSeriesRenderer buildRenderer(int[] colors, PointStyle[] styles, boolean fill) {
        XYMultipleSeriesRenderer renderer = new XYMultipleSeriesRenderer();
    /*    XYSeriesRenderer seriesRenderer = new XYSeriesRenderer();//创建你需要在图层上显示的具体内容的图层
        renderer.addSeriesRenderer(seriesRenderer);//添加进去
        seriesRenderer.setLineWidth(3.0f);*/
        int length = colors.length;
        for (int i = 0; i < length; i++) {
            XYSeriesRenderer r = new XYSeriesRenderer();
            r.setColor(colors[i]);
            r.setPointStyle(styles[i]);
            r.setFillPoints(fill);
            r.setLineWidth(3.0f);
            renderer.addSeriesRenderer(r);
        }
        return renderer;
    }

    protected void setChartSettings(XYMultipleSeriesRenderer renderer, String title,
                                    String xTitle, String yTitle, double xMin,
                                    double xMax, double yMin, double yMax,
                                    int axesColor, int labelsColor) {
        // renderer.setAxisTitleTextSize(16); // 设置坐标轴标题文本大小
        // renderer.setChartTitleTextSize(20); // 设置图表标题文本大小
        // renderer.setChartTitle("当日测量血压数据值");
        renderer.setLabelsTextSize(35); // 设置轴标签文本大小
        renderer.setLegendTextSize(35); // 设置图例文本大小
        renderer.setMargins(new int[]{50, 50, 300, 50}); // 设置4边留白
        renderer.setPanEnabled(false, false); // 设置x,y坐标轴不会因用户划动屏幕而移动
        renderer.setMarginsColor(Color.argb(0, 0xff, 0, 0));// 设置4边留白透明
        renderer.setBackgroundColor(Color.TRANSPARENT); // 设置背景色透明
        renderer.setApplyBackgroundColor(true); // 使背景色生效
        renderer.setXLabels(0);// 设置X轴显示12个点，根据setChartSettings的最大值和最小值自动计算点的间隔
        renderer.setYLabels(12);// 设置y轴显示10个点,根据setChartSettings的最大值和最小值自动计算点的间隔
        renderer.setXLabelsAlign(Paint.Align.CENTER);// 刻度线与刻度标注之间的相对位置关系
        renderer.setYLabelsAlign(Paint.Align.CENTER);// 刻度线与刻度标注之间的相对位置关系
        // render.setZoomButtonsVisible(true);// 是否显示放大缩小按钮
        renderer.setShowGrid(true);// 是否显示网格
        renderer.setGridColor(Color.GRAY);// 设置网格颜色
        renderer.setAxesColor(Color.RED);// 设置X.y轴颜色
        renderer.setFitLegend(true);// 设置自动按比例缩放
        // renderer.setYAxisMax(200.0); // 设置Y轴最大值
        //  renderer.setYAxisMin(40.0); // 设置Y轴最小值
        XLabel.add("11.00");
        XLabel.add("11.00");
        XLabel.add("11.00");
        XLabel.add("11.00");
        XLabel.add("11.00");
        XLabel.add("11.00");
        XLabel.add("11.00");
        XLabel.add("11.00");
        XLabel.add("11.00");
        for (int i = 0; i < XLabel.size(); i++) {
            renderer.addXTextLabel(i * 5, XLabel.get(i));
            //  mRenderer.addXTextLabel(i, XLabel.get(i));//这边是自定义自己的标签,显示自己想要的X轴的标签,需要注意的是需要setXLabels(0)放在标签重叠(就是自定义的标签与图表默认的标签)
        }

        // renderer.setChartTitle(title);
        renderer.setXTitle(xTitle);
        renderer.setYTitle(yTitle);
        renderer.setXAxisMin(xMin);
        renderer.setPointSize(10);
        renderer.setXAxisMax(xMax);
        renderer.setYAxisMin(yMin);
        renderer.setYAxisMax(yMax);
        renderer.setAxesColor(Color.BLACK);
        renderer.setLabelsColor(Color.BLACK);

        renderer.setAxisTitleTextSize(35); //设置轴标题文字的大小
        renderer.setChartTitleTextSize(35);//?设置整个图表标题文字大小
        renderer.setLabelsTextSize(35);//设置刻度显示文字的大小(XY轴都会被设置)
        renderer.setLegendTextSize(35);//图例文字大小
        renderer.setPanEnabled(false);//设置是否可以移动
        renderer.setShowCustomTextGrid(true);//是否显示X轴和Y轴网格.
        renderer.setZoomEnabled(false);//是否支持放大缩小.

    }

    public void getDayChild(String url) {
        HashMap<String, String> map = new HashMap<String, String>();
        map.put("user_id", MainActivity.userid);
        map.put("day", day);
        map.put("month", month);
        map.put("year", year);
        XUtils.xUtilsPost(url, map, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                Log.d("maha", "----getDayChild------CommonCallback---" + result.toString());
                if (sortUtil.getHISTORY_TOP() == 1) {
                    parseDayWeight(result);
                } else if (sortUtil.getHISTORY_TOP() == 2) {
                    parseDayBody(result);
                } else if (sortUtil.getHISTORY_TOP() == 4) {
                    parseDayBloodp(result);
                } else if (sortUtil.getHISTORY_TOP() == 5) {
                    parseDayTem(result);
                }
            }

            @Override
            public void onError(Throwable throwable, boolean b) {
                Log.d("maha", "b1:" + b);
            }

            @Override
            public void onCancelled(CancelledException e) {

            }

            @Override
            public void onFinished() {
                Log.d("maha", "wan1cheng");
            }
        });
    }


    public void getWeekChild(String url) {
        HashMap<String, String> map = new HashMap<String, String>();
        map.put("user_id", MainActivity.userid);
        map.put("week", week);
        Log.d("maha", week + month + year);
        map.put("month", month);
        map.put("year", year);
        XUtils.xUtilsPost(url, map, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                Log.d("maha", "-----getWeekChild-----CommonCallback---" + result.toString());
                if (sortUtil.getHISTORY_TOP() == 1) {
                    parseWeekWeight(result);
                } else if (sortUtil.getHISTORY_TOP() == 2) {
                    parseWeekBody(result);
                } else if (sortUtil.getHISTORY_TOP() == 4) {
                    parseWeekBloodp(result);
                } else if (sortUtil.getHISTORY_TOP() == 5) {
                    parseWeekTem(result);
                }
            }

            @Override
            public void onError(Throwable throwable, boolean b) {
                Log.d("maha", "b1:" + b);
            }

            @Override
            public void onCancelled(CancelledException e) {

            }

            @Override
            public void onFinished() {
                Log.d("maha", "wan1cheng");
            }
        });
    }

    public void getMonthChild(String url) {
        HashMap<String, String> map = new HashMap<String, String>();
        map.put("user_id", MainActivity.userid);
        map.put("month", month);
        map.put("year", year);
        XUtils.xUtilsPost(url, map, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                Log.d("maha", "------getMonthChild----CommonCallback---" + result.toString());
                if (sortUtil.getHISTORY_TOP() == 1) {
                    parseMonthWeight(result);
                } else if (sortUtil.getHISTORY_TOP() == 2) {
                    parseMonthBody(result);
                } else if (sortUtil.getHISTORY_TOP() == 4) {
                    parseMonthBloodp(result);
                } else if (sortUtil.getHISTORY_TOP() == 5) {
                    parseMonthTem(result);
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


    private void parseDayWeight(String result) {
        MouDayWeightBean mouDayWeightBean = gson.fromJson(result, MouDayWeightBean.class);
        List<MouDayWeightBean.DataBean> data = mouDayWeightBean.data;
        titles = new String[]{"体重", "BMI"};
        int[] xbmi = new int[]{};
        int[] xtizhong = new int[]{};
        List xbmilist = new ArrayList();
        List ybmilist = new ArrayList();
        for (int i = 0; i < data.size(); i++) {
            String hour = data.get(i).hour;
            String minute = data.get(i).minute;
            XLabel.add(hour + ":" + minute);
            xbmi[i] = Integer.parseInt(data.get(i).bmi, 10);
            xtizhong[i] = Integer.parseInt(data.get(i).tizhongcheng, 10);
        }
        xbmilist.add(xtizhong);
        xbmilist.add(xbmi);

    }

    private void parseDayBody(String result) {
        MouDayBodyBean mouDayBodyBean = gson.fromJson(result, MouDayBodyBean.class);
        List<MouDayBodyBean.DataBean> data = mouDayBodyBean.data;
        titles = new String[]{"肌肉率", "水分量", "脂肪率", "骨量", "BMR", "内脏等级"};
        for (int i = 0; i < data.size(); i++) {
            String bmr = data.get(i).bmr;
            String guliang = data.get(i).guliang;
            String hour = data.get(i).hour;
            String jiroulv = data.get(i).jiroulv;
            String minute = data.get(i).minute;
            String neizang = data.get(i).neizang;
            String zhifanglv = data.get(i).zhifanglv;
            String shuifenlv = data.get(i).shuifenlv;
            XLabel.add(hour + ":" + minute);

        }

    }

    private void parseDayBloodp(String result) {
        MouDayBloopBean mouDayBloopBean = gson.fromJson(result, MouDayBloopBean.class);
        List<MouDayBloopBean.DataBean> data = mouDayBloopBean.data;
        titles = new String[]{"脉搏", "高压", "低压"};
        for (int i = 0; i < data.size(); i++) {
            String maibo = data.get(i).maibo;
            String hour = data.get(i).hour;
            String minute = data.get(i).minute;
            String shousuoya = data.get(i).shousuoya;
            String shuzhangya = data.get(i).shuzhangya;
            XLabel.add(hour + ":" + minute);
        }
    }

    private void parseDayTem(String result) {
        MouDayTemBean mouDayTemBean = gson.fromJson(result, MouDayTemBean.class);
        List<MouDayTemBean.DataBean> data = mouDayTemBean.data;
        titles = new String[]{"体温"};
        for (int i = 0; i < data.size(); i++) {
            String tiwen = data.get(i).tiwen;
            String hour = data.get(i).hour;
            String minute = data.get(i).minute;
            XLabel.add(hour + ":" + minute);
        }

    }

    private void parseWeekWeight(String result) {
        MouWeekWeight mouWeekWeight = gson.fromJson(result, MouWeekWeight.class);
        List<MouWeekWeight.DataBean> data = mouWeekWeight.data;
        titles = new String[]{"体重"};
        for (int i = 0; i < data.size(); i++) {
            String bmi = data.get(i).bmi;
            String day = data.get(i).day;
            String month = data.get(i).month;
            String year = data.get(i).year;
            String tizhongcheng = data.get(i).tizhongcheng;
            XLabel.add(year + "." + month + "." + day);

        }

    }

    private void parseWeekBody(String result) {
        MouWeekBody mouWeekBody = gson.fromJson(result, MouWeekBody.class);
        List<MouWeekBody.DataBean> data = mouWeekBody.data;
        titles = new String[]{"肌肉率", "水分量", "脂肪率", "骨量", "BMR", "内脏等级"};
        for (int i = 0; i < data.size(); i++) {
            String bmr = data.get(i).bmr;
            String guliang = data.get(i).guliang;
            String jiroulv = data.get(i).jiroulv;
            String neizang = data.get(i).neizang;
            String zhifanglv = data.get(i).zhifanglv;
            String shuifenlv = data.get(i).shuifenlv;
            String day = data.get(i).day;
            String month = data.get(i).month;
            String year = data.get(i).year;
            XLabel.add(year + "." + month + "." + day);

        }

    }

    private void parseWeekBloodp(String result) {
        MouWeekBloodpBean mouWeekBloodpBean = gson.fromJson(result, MouWeekBloodpBean.class);
        List<MouWeekBloodpBean.DataBean> data = mouWeekBloodpBean.data;
        titles = new String[]{"脉搏", "高压", "低压"};
        for (int i = 0; i < data.size(); i++) {
            String maibo = data.get(i).maibo;
            String day = data.get(i).day;
            String month = data.get(i).month;
            String year = data.get(i).year;
            String shousuoya = data.get(i).shousuoya;
            String shuzhangya = data.get(i).shuzhangya;
            XLabel.add(year + "." + month + "." + day);
        }

    }

    private void parseWeekTem(String result) {
        MouWeekTemBean mouWeekTemBean = gson.fromJson(result, MouWeekTemBean.class);
        List<MouWeekTemBean.DataBean> data = mouWeekTemBean.data;
        titles = new String[]{"体温"};
        for (int i = 0; i < data.size(); i++) {
            String tiwen = data.get(i).tiwen;
            String day = data.get(i).day;
            String month = data.get(i).month;
            String year = data.get(i).year;
            XLabel.add(year + "." + month + "." + day);

        }

    }

    private void parseMonthWeight(String result) {
        MouDayWeightBean mouDayWeightBean = gson.fromJson(result, MouDayWeightBean.class);
        List<MouDayWeightBean.DataBean> data = mouDayWeightBean.data;
        titles = new String[]{"体重"};
        for (int i = 0; i < data.size(); i++) {
            String bmi = data.get(i).bmi;
            String hour = data.get(i).hour;
            String minute = data.get(i).minute;
            String tizhongcheng = data.get(i).tizhongcheng;
            XLabel.add(hour + ":" + minute);
        }

    }

    private void parseMonthBody(String result) {
        MouMonthBodyBean mouMonthBodyBean = gson.fromJson(result, MouMonthBodyBean.class);
        List<MouMonthBodyBean.DataBean> data = mouMonthBodyBean.data;
        titles = new String[]{"肌肉率", "水分量", "脂肪率", "骨量", "BMR", "内脏等级"};
        for (int i = 0; i < data.size(); i++) {
            String bmr = data.get(i).bmr;
            String guliang = data.get(i).guliang;
            String jiroulv = data.get(i).jiroulv;
            String neizang = data.get(i).neizang;
            String zhifanglv = data.get(i).zhifanglv;
            String shuifenlv = data.get(i).shuifenlv;
            String day = data.get(i).day;
            String month = data.get(i).month;
            String year = data.get(i).year;
            XLabel.add(year + "." + month + "." + day);


        }

    }

    private void parseMonthBloodp(String result) {
        MouMonthBloodpBean mouMonthBloodpBean = gson.fromJson(result, MouMonthBloodpBean.class);
        List<MouMonthBloodpBean.DataBean> data = mouMonthBloodpBean.data;
        titles = new String[]{"脉搏", "高压", "低压"};
        for (int i = 0; i < data.size(); i++) {
            String maibo = data.get(i).maibo;
            String day = data.get(i).day;
            String month = data.get(i).month;
            String year = data.get(i).year;
            String shousuoya = data.get(i).shousuoya;
            String shuzhangya = data.get(i).shuzhangya;
            XLabel.add(year + "." + month + "." + day);
        }

    }

    private void parseMonthTem(String result) {
        MouMonthTemBean mouMonthTemBean = gson.fromJson(result, MouMonthTemBean.class);
        List<MouMonthTemBean.DataBean> data = mouMonthTemBean.data;
        titles = new String[]{"体温"};
        for (int i = 0; i < data.size(); i++) {
            String tiwen = data.get(i).tiwen;
            String day = data.get(i).day;
            String month = data.get(i).month;
            String year = data.get(i).year;
            XLabel.add(year + "." + month + "." + day);
        }

    }

}
   /* public static final String TYPE = "type";
    private XYMultipleSeriesDataset mDataset = new XYMultipleSeriesDataset();
    private XYMultipleSeriesRenderer mRenderer = new XYMultipleSeriesRenderer();
    private XYSeries mCurrentSeries;
    private XYSeriesRenderer mCurrentRenderer;
    private String mDateFormat;
    private Button mNewSeries;
    private Button mAdd;
    private EditText mX;
    private EditText mY;

    private GraphicalView mChartView;

    private int index = 0;

    @Override
    protected void onRestoreInstanceState(Bundle savedState) {
        super.onRestoreInstanceState(savedState);
        mDataset = (XYMultipleSeriesDataset) savedState.getSerializable("dataset");
        mRenderer = (XYMultipleSeriesRenderer) savedState.getSerializable("renderer");
        mCurrentSeries = (XYSeries) savedState.getSerializable("current_series");
        mCurrentRenderer = (XYSeriesRenderer) savedState.getSerializable("current_renderer");
        mDateFormat = savedState.getString("date_format");
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putSerializable("dataset", mDataset);
        outState.putSerializable("renderer", mRenderer);
        outState.putSerializable("current_series", mCurrentSeries);
        outState.putSerializable("current_renderer", mCurrentRenderer);
        outState.putString("date_format", mDateFormat);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.xy_chart);
        mX = (EditText) findViewById(R.id.xValue);
        mY = (EditText) findViewById(R.id.yValue);
        mRenderer.setPanEnabled(false);//设置是否可以移动
        mRenderer.setShowCustomTextGrid(true);//是否显示X轴和Y轴网格.
        mRenderer.setZoomEnabled(false);//是否支持放大缩小.
        mRenderer.setApplyBackgroundColor(true);//设置是否显示背景色
        mRenderer.setBackgroundColor(Color.argb(100, 50, 50, 50));//设置表格背景色
        mRenderer.setAxisTitleTextSize(35); //设置轴标题文字的大小
        mRenderer.setChartTitleTextSize(35);//?设置整个图表标题文字大小
        mRenderer.setLabelsTextSize(35);//设置刻度显示文字的大小(XY轴都会被设置)
        mRenderer.setLegendTextSize(35);//图例文字大小
        mRenderer.setMargins(new int[]{30, 70, 0, 10});//设置图表的外边框(上/左/下/右)
        mRenderer.setZoomButtonsVisible(false);//是否显示放大缩小按钮
        mRenderer.setPointSize(15);//设置点的大小(图上显示的点的大小和图例中点的大小都会被设置)
        mAdd = (Button) findViewById(R.id.add);
        mNewSeries = (Button) findViewById(R.id.new_series);
        mNewSeries.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                String seriesTitle = "Series " + (mDataset.getSeriesCount() + 1);//图例
                XYSeries series = new XYSeries(seriesTitle);//定义XYSeries
                mDataset.addSeries(series);//在XYMultipleSeriesDataset中添加XYSeries
                mCurrentSeries = series;//设置当前需要操作的XYSeries
                XYSeriesRenderer renderer = new XYSeriesRenderer();//定义XYSeriesRenderer
                mRenderer.addSeriesRenderer(renderer);//将单个XYSeriesRenderer增加到XYMultipleSeriesRenderer
                renderer.setPointStyle(PointStyle.CIRCLE);//点的类型是圆形
                renderer.setFillPoints(true);//设置点是否实心
                mCurrentRenderer = renderer;
                setSeriesEnabled(false);
            }
        });

        //增加一个点后重画图
        mAdd.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                double x = 0;
                double y = 0;
                try {
                    x = Double.parseDouble(mX.getText().toString());
                } catch (NumberFormatException e) {
                    // TODO
                    mX.requestFocus();
                    return;
                }
                try {
                    y = Double.parseDouble(mY.getText().toString());
                } catch (NumberFormatException e) {
                    // TODO
                    mY.requestFocus();
                    return;
                }
                mCurrentSeries.add(x, y);
                mX.setText("");
                mY.setText("");
                mX.requestFocus();
                if (mChartView != null) {
                    mChartView.repaint();//重画图表
                }
                //生成图片保存,注释掉下面的代码不影响图表的生成.
                //-->start
                Bitmap bitmap = mChartView.toBitmap();
                try {
                    File file = new File(Environment.getExternalStorageDirectory(), "test" + index++ + ".png");
                    FileOutputStream output = new FileOutputStream(file);
                    bitmap.compress(Bitmap.CompressFormat.PNG, 100, output);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                //-->end
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (mChartView == null) {
            LinearLayout layout = (LinearLayout) findViewById(R.id.chart);
            mChartView = ChartFactory.getLineChartView(this, mDataset, mRenderer);
            mRenderer.setClickEnabled(false);//设置图表是否允许点击
            mRenderer.setSelectableBuffer(100);//设置点的缓冲半径值(在某点附件点击时,多大范围内都算点击这个点)
            mChartView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //这段代码处理点击一个点后,获得所点击的点在哪个序列中以及点的坐标.
                    //-->start
                    SeriesSelection seriesSelection = mChartView.getCurrentSeriesAndPoint();
                    double[] xy = mChartView.toRealPoint(0);
                    if (seriesSelection == null) {
                        Toast.makeText(CurveActivity.this, "No chart element was clicked", Toast.LENGTH_SHORT)
                                .show();
                    } else {
                        Toast.makeText(
                                CurveActivity.this,
                                "Chart element in series index " + seriesSelection.getSeriesIndex()
                                        + " data point index " + seriesSelection.getPointIndex() + " was clicked"
                                        + " closest point value X=" + seriesSelection.getXValue() + ", Y=" + seriesSelection.getValue()
                                        + " clicked point value X=" + (float) xy[0] + ", Y=" + (float) xy[1], Toast.LENGTH_SHORT).show();
                    }
                    //-->end
                }
            });
            mChartView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    SeriesSelection seriesSelection = mChartView.getCurrentSeriesAndPoint();
                    if (seriesSelection == null) {
                        Toast.makeText(CurveActivity.this, "No chart element was long pressed",
                                Toast.LENGTH_SHORT);
                        return false; // no chart element was long pressed, so let something
                        // else handle the event
                    } else {
                        Toast.makeText(CurveActivity.this, "Chart element in series index "
                                + seriesSelection.getSeriesIndex() + " data point index "
                                + seriesSelection.getPointIndex() + " was long pressed", Toast.LENGTH_SHORT);
                        return true; // the element was long pressed - the event has been
                        // handled
                    }
                }
            });
            //这段代码处理放大缩小
            //-->start
            mChartView.addZoomListener(new ZoomListener() {
                public void zoomApplied(ZoomEvent e) {
                    String type = "out";
                    if (e.isZoomIn()) {
                        type = "in";
                    }
                    System.out.println("Zoom " + type + " rate " + e.getZoomRate());
                }

                public void zoomReset() {
                    System.out.println("Reset");
                }
            }, true, true);
            //-->end
            //设置拖动图表时后台打印出图表坐标的最大最小值.
            mChartView.addPanListener(new PanListener() {
                public void panApplied() {
                    System.out.println("New X range=[" + mRenderer.getXAxisMin() + ", " + mRenderer.getXAxisMax()
                            + "], Y range=[" + mRenderer.getYAxisMax() + ", " + mRenderer.getYAxisMax() + "]");
                }
            });
            layout.addView(mChartView, new LinearLayout.LayoutParams(LinearLayout.LayoutParams.FILL_PARENT,
                    LinearLayout.LayoutParams.FILL_PARENT));
            boolean enabled = mDataset.getSeriesCount() > 0;
            setSeriesEnabled(enabled);
        } else {
            mChartView.repaint();
        }
    }

    private void setSeriesEnabled(boolean enabled) {
        mX.setEnabled(enabled);
        mY.setEnabled(enabled);
        mAdd.setEnabled(enabled);
    }
*/
