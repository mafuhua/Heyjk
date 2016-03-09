package com.yuenkeji.heyjk.activity;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.yuenkeji.heyjk.R;
import com.yuenkeji.heyjk.fragment.CurveFragment;
import com.yuenkeji.heyjk.fragment.FragmentFractory;
import com.yuenkeji.heyjk.fragment.HistoryFragment;
import com.yuenkeji.heyjk.fragment.HomeFragment;
import com.yuenkeji.heyjk.fragment.SettingFragment;
import com.yuenkeji.heyjk.homefragment.BloodFragment;
import com.yuenkeji.heyjk.homefragment.BloodPressureFragment;
import com.yuenkeji.heyjk.homefragment.HomeFragment2;
import com.yuenkeji.heyjk.homefragment.TempFragment;
import com.yuenkeji.heyjk.homefragment.Weight2Fragment;
import com.yuenkeji.heyjk.homefragment.WeightComFragment;

/**
 * 打开程序主界面
 */
public class MainActivity extends FragmentActivity implements View.OnClickListener {
    private LinearLayout llMeasure;
    private ImageView ivMeasure;
    private TextView tvMeasure;
    private LinearLayout llHistory;
    private ImageView ivHistory;
    private TextView tvHistory;
    private LinearLayout llCurve;
    private ImageView ivCurve;
    private TextView tvCurve;
    private LinearLayout llSetting;
    private ImageView ivSetting;
    private TextView tvSetting;
    private FragmentManager fm;
    private FragmentTransaction ft;
    private HomeFragment homeFragment;
    private HistoryFragment historyFragment;
    private CurveFragment curveFragment;
    private SettingFragment settingFragment;
    private HomeFragment2 homeFragment2;
    private TempFragment tempFragment;
    private BloodFragment bloodFragment;
    private BloodPressureFragment bloodPressureFragment;
    private Weight2Fragment weightFragment;
    private WeightComFragment weightComFragment;


    private void assignViews() {
        llMeasure = (LinearLayout) findViewById(R.id.ll_measure);
        ivMeasure = (ImageView) findViewById(R.id.iv_measure);
        tvMeasure = (TextView) findViewById(R.id.tv_measure);
        llHistory = (LinearLayout) findViewById(R.id.ll_history);
        ivHistory = (ImageView) findViewById(R.id.iv_history);
        tvHistory = (TextView) findViewById(R.id.tv_history);
        llCurve = (LinearLayout) findViewById(R.id.ll_curve);
        ivCurve = (ImageView) findViewById(R.id.iv_curve);
        tvCurve = (TextView) findViewById(R.id.tv_curve);
        llSetting = (LinearLayout) findViewById(R.id.ll_setting);
        ivSetting = (ImageView) findViewById(R.id.iv_setting);
        tvSetting = (TextView) findViewById(R.id.tv_setting);

        llCurve.setOnClickListener(this);
        llSetting.setOnClickListener(this);
        llMeasure.setOnClickListener(this);
        llHistory.setOnClickListener(this);

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

   /*     mHandler = new Handler();

        final BluetoothManager bluetoothManager =
                (BluetoothManager) getSystemService(Context.BLUETOOTH_SERVICE);
        mBluetoothAdapter = bluetoothManager.getAdapter();
        mBluetoothAdapter.enable();
        scanLeDevice(true);*/


        assignViews();
        fm = getSupportFragmentManager();
        ft = fm.beginTransaction();
        homeFragment2 = new HomeFragment2();
        //  homeFragment = (HomeFragment) FragmentFractory.getInstance().createFragment(0);
        ft.replace(R.id.ll_content, homeFragment2);
        ft.commit();
        homeFragment2.setOnBTClickListener(new HomeFragment2.OnBTClickListener() {
            @Override
            public void onSubClick(int position) {
                subhomepager(position);
            }
        });
        historyFragment = (HistoryFragment) FragmentFractory.getInstance().createFragment(8);
        curveFragment = (CurveFragment) FragmentFractory.getInstance().createFragment(7);
        settingFragment = (SettingFragment) FragmentFractory.getInstance().createFragment(9);
    }

    public void subhomepager(int position) {
        ft = fm.beginTransaction();
        switch (position) {
            case 0:


                ft.replace(R.id.ll_content, homeFragment2);


                break;
            case 1:
                flag = true;
                weightFragment = new Weight2Fragment();
                ft.replace(R.id.ll_content, weightFragment);


                break;
            case 2:
                flag = true;
                weightComFragment = new WeightComFragment();
                ft.replace(R.id.ll_content, weightComFragment);

                break;
            case 3:
                flag = true;
                bloodFragment = new BloodFragment();
                ft.replace(R.id.ll_content, bloodFragment);

                break;
            case 4:
                flag = true;
                bloodFragment = new BloodFragment();
                ft.replace(R.id.ll_content, bloodFragment);

                break;
            case 5:
                flag = true;
                bloodPressureFragment = new BloodPressureFragment();
                ft.replace(R.id.ll_content, bloodPressureFragment);

                break;
            case 6:
                flag = true;
                tempFragment = new TempFragment();
                ft.replace(R.id.ll_content, tempFragment);

                break;


        }
        ft.commit();

    }

    @Override
    public void onClick(View view) {
        ft = fm.beginTransaction();
        switch (view.getId()) {
            case R.id.ll_measure:
                resetTabBottom();
                ivMeasure.setImageDrawable(getResources().getDrawable(
                        R.drawable.tab_01_s2x));
                tvMeasure.setTextColor(getResources().getColor(R.color.blue));
                ft.replace(R.id.ll_content, this.homeFragment2);
                break;
            case R.id.ll_history:
                flag = true;
                resetTabBottom();
                ivHistory.setImageDrawable(getResources().getDrawable(
                        R.drawable.tab_02_s2x));
                tvHistory.setTextColor(getResources().getColor(R.color.blue));
                ft.replace(R.id.ll_content, historyFragment);
                break;
            case R.id.ll_curve:
                flag = true;
                resetTabBottom();
                ivCurve.setImageDrawable(getResources().getDrawable(
                        R.drawable.tab_03_s2x));
                tvCurve.setTextColor(getResources().getColor(R.color.blue));
                ft.replace(R.id.ll_content, curveFragment);
                break;
            case R.id.ll_setting:
                flag = true;
                resetTabBottom();
                ivSetting.setImageDrawable(getResources().getDrawable(
                        R.drawable.tab_04_s2x));
                tvSetting.setTextColor(getResources().getColor(R.color.blue));
                ft.replace(R.id.ll_content, settingFragment);
                break;

            default:
                break;
        }
        ft.commit();
    }

    public void resetTabBottom() {
        ivMeasure.setImageDrawable(getResources().getDrawable(
                R.drawable.tab_01_n2x));
        tvMeasure.setTextColor(getResources().getColor(R.color.black));
        ivCurve.setImageDrawable(getResources().getDrawable(
                R.drawable.tab_03_n2x));
        tvCurve.setTextColor(getResources().getColor(R.color.black));
        ivHistory.setImageDrawable(getResources().getDrawable(
                R.drawable.tab_02_n2x));
        tvHistory.setTextColor(getResources().getColor(R.color.black));
        ivSetting.setImageDrawable(getResources().getDrawable(
                R.drawable.tab_04_n2x));
        tvSetting.setTextColor(getResources().getColor(R.color.black));
    }
    public boolean flag = false;
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {

        if (KeyEvent.KEYCODE_BACK == event.getKeyCode() && flag == true) {
           subhomepager(0);
            flag = false;
            return true;
        } else {
            return super.onKeyDown(keyCode, event);

        }
    }




/*


    private BluetoothAdapter mBluetoothAdapter;
    private boolean mScanning;
    private Handler mHandler;

    // Stops scanning after 10 seconds.
    private static final long SCAN_PERIOD = 10000;








    private final static String TAG ="mafuhua";
    public static final String EXTRAS_DEVICE_NAME = "DEVICE_NAME";
    public static final String EXTRAS_DEVICE_ADDRESS = "DEVICE_ADDRESS";
    private String mDeviceAddress;
    private BluetoothLeService mBluetoothLeService;
    private BluetoothGattCharacteristic mNotifyCharacteristic;
    private final String LIST_UUID = "UUID";
    private final ServiceConnection mServiceConnection = new ServiceConnection() {

        @Override
        public void onServiceConnected(ComponentName componentName,
                                       IBinder service) {
            mBluetoothLeService = ((BluetoothLeService.LocalBinder) service)
                    .getService();
            if (!mBluetoothLeService.initialize()) {
                Log.e(TAG, "Unable to initialize Bluetooth");
                finish();
            }
            mBluetoothLeService.connect(mDeviceAddress);
        }
        @Override
        public void onServiceDisconnected(ComponentName componentName) {
            mBluetoothLeService = null;
        }
    };
    // Handles various events fired by the Service.
    // ACTION_GATT_CONNECTED: connected to a GATT server.
    // ACTION_GATT_DISCONNECTED: disconnected from a GATT server.
    // ACTION_GATT_SERVICES_DISCOVERED: discovered GATT services.
    // ACTION_DATA_AVAILABLE: received data from the device. This can be a
    // result of read
    // or notification operations.
    private final BroadcastReceiver mGattUpdateReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            final String action = intent.getAction();
            if (BluetoothLeService.ACTION_GATT_CONNECTED.equals(action)) {

            } else if (BluetoothLeService.ACTION_GATT_DISCONNECTED
                    .equals(action)) {
            } else if (BluetoothLeService.ACTION_GATT_SERVICES_DISCOVERED
                    .equals(action)) {
                displayGattServices(mBluetoothLeService
                        .getSupportedGattServices());
            } else if (BluetoothLeService.ACTION_DATA_AVAILABLE.equals(action)) {
				*/
/*displayData(intent
                        .getStringExtra(BluetoothLeService.EXTRA_DATA));*//*

            }
        }
    };
    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbindService(mServiceConnection);
        unregisterReceiver(mGattUpdateReceiver);
        mBluetoothLeService = null;
    }
    // Demonstrates how to iterate through the supported GATT
    // Services/Characteristics.
    // In this sample, we populate the data structure that is bound to the
    // ExpandableListView
    // on the UI.
    private void displayGattServices(List<BluetoothGattService> gattServices) {
        if (gattServices == null)
            return;
        String uuid = null;
        for (BluetoothGattService gattService : gattServices) {
            HashMap<String, String> currentServiceData = new HashMap<String, String>();
            uuid = gattService.getUuid().toString();
            Log.d(TAG, "服务 "+uuid);
            currentServiceData.put(LIST_UUID, uuid);
            List<BluetoothGattCharacteristic> gattCharacteristics = gattService
                    .getCharacteristics();

            // Loops through available Characteristics.
            for (BluetoothGattCharacteristic gattCharacteristic : gattCharacteristics) {
                HashMap<String, String> currentCharaData = new HashMap<String, String>();
                uuid = gattCharacteristic.getUuid().toString();
                Log.d(TAG, "特征 " + uuid);
                if (uuid.equals("00002a1c-0000-1000-8000-00805f9b34fb")){
                    final int charaProp = gattCharacteristic.getProperties();
                    if ((charaProp | BluetoothGattCharacteristic.PROPERTY_READ) > 0) {
                        // If there is an active notification on a characteristic,
                        // clear
                        // it first so it doesn't update the data field on the user
                        // interface.
                        if (mNotifyCharacteristic != null) {
                            mBluetoothLeService.setCharacteristicNotification(
                                    mNotifyCharacteristic, false);
                            mNotifyCharacteristic = null;
                        }
                        mBluetoothLeService.readCharacteristic(gattCharacteristic);
                    }
                    if ((charaProp | BluetoothGattCharacteristic.PROPERTY_NOTIFY) > 0) {
                        mNotifyCharacteristic = gattCharacteristic;
                        mBluetoothLeService.setCharacteristicNotification(
                                gattCharacteristic, true);
                    }
                }
            }
        }
    }

    private void scanLeDevice(final boolean enable) {
        if (enable) {
            // Stops scanning after a pre-defined scan period.
            mHandler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    mScanning = false;
                    mBluetoothAdapter.stopLeScan(mLeScanCallback);
                }
            }, SCAN_PERIOD);

            mScanning = true;
            mBluetoothAdapter.startLeScan(mLeScanCallback);
        } else {
            mScanning = false;
            mBluetoothAdapter.stopLeScan(mLeScanCallback);
        }
    }

    // Device scan callback.
    private BluetoothAdapter.LeScanCallback mLeScanCallback =
            new BluetoothAdapter.LeScanCallback() {

                @Override
                public void onLeScan(final BluetoothDevice device, int rssi, byte[] scanRecord) {
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            if (device == null) return;

                            mDeviceAddress = device.getAddress();

                            if (device.getName().equals("DoouYa Thermometer")) {
                                Log.e(TAG, "111111");
                                // ((TextView) findViewById(R.id.device_address)).setText(device.getAddress());
                                Intent gattServiceIntent = new Intent(MainActivity.this, BluetoothLeService.class);
                                Log.e(TAG, "2");
                               // ((TextView) findViewById(R.id.device_address)).setText(device.getAddress());
                                bindService(gattServiceIntent, new ServiceConnection() {

                                    @Override
                                    public void onServiceConnected(ComponentName componentName,
                                                                   IBinder service) {
                                        mBluetoothLeService = ((BluetoothLeService.LocalBinder) service)
                                                .getService();
                                        if (!mBluetoothLeService.initialize()) {
                                            Log.e(TAG, "Unable to initialize Bluetooth");
                                            finish();
                                        }



                                        mBluetoothLeService.connect(mDeviceAddress);
                                    }

                                    @Override
                                    public void onServiceDisconnected(ComponentName componentName) {
                                        mBluetoothLeService = null;
                                    }
                                }, BIND_AUTO_CREATE);
                                registerReceiver(mGattUpdateReceiver, makeGattUpdateIntentFilter());
                                Log.e(TAG, "3");
                                startService(gattServiceIntent);
                                Log.e(TAG, "4");
                                if (mBluetoothLeService != null) {
                                    final boolean result = mBluetoothLeService.connect(device.getAddress());
                                    Log.d(TAG, "Connect request result=" + result);
                                }

                            }

                            if (mScanning) {
                                mBluetoothAdapter.stopLeScan(mLeScanCallback);
                                mScanning = false;
                            }
                        }
                    });
                }
            };
    private static IntentFilter makeGattUpdateIntentFilter() {
        final IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(BluetoothLeService.ACTION_GATT_CONNECTED);
        intentFilter.addAction(BluetoothLeService.ACTION_GATT_DISCONNECTED);
        intentFilter
                .addAction(BluetoothLeService.ACTION_GATT_SERVICES_DISCOVERED);
        intentFilter.addAction(BluetoothLeService.ACTION_DATA_AVAILABLE);
        return intentFilter;
    }


*/


}
