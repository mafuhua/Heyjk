package com.yuenkeji.heyjk.activity;

import android.app.Activity;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.view.View;

import org.achartengine.ChartFactory;
import org.achartengine.chart.PointStyle;
import org.achartengine.model.XYMultipleSeriesDataset;
import org.achartengine.model.XYSeries;
import org.achartengine.renderer.XYMultipleSeriesRenderer;
import org.achartengine.renderer.XYSeriesRenderer;

import java.util.ArrayList;
import java.util.List;

public class CurveActivity extends Activity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        String[] titles = new String[]{"脂肪率", "水分率","菇凉","体温"};

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

        int[] colors = new int[]{Color.BLUE, Color.BLACK,Color.RED,Color.GREEN};
        PointStyle[] styles = new PointStyle[]{PointStyle.CIRCLE, PointStyle.DIAMOND,PointStyle.SQUARE,PointStyle.TRIANGLE};
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
private List<String> XLabel = new ArrayList<>();


    protected void setChartSettings(XYMultipleSeriesRenderer renderer, String title,
                                    String xTitle, String yTitle, double xMin,
                                    double xMax, double yMin, double yMax,
                                    int axesColor, int labelsColor) {
        // renderer.setAxisTitleTextSize(16); // 设置坐标轴标题文本大小
        // renderer.setChartTitleTextSize(20); // 设置图表标题文本大小
        renderer.setChartTitle("当日测量血压数据值");
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
        for (int i = 0; i < XLabel.size(); i++)
        {
            renderer.addXTextLabel(i*5, XLabel.get(i));
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
