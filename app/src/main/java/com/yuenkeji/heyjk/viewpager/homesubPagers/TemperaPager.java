package com.yuenkeji.heyjk.viewpager.homesubPagers;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothGattCharacteristic;
import android.bluetooth.BluetoothGattService;
import android.bluetooth.BluetoothManager;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.ServiceConnection;
import android.os.Handler;
import android.os.IBinder;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.yuenkeji.heyjk.R;
import com.yuenkeji.heyjk.fragment.HomeFragment;
import com.yuenkeji.heyjk.viewpager.BasePager;

import java.util.HashMap;
import java.util.List;

/**
 * 体温测量
 * Created by Administrator on 2016/2/19.
 */
public class TemperaPager extends BasePager implements View.OnClickListener {
    // Stops scanning after 10 seconds.
    private static final long SCAN_PERIOD = 10000;
    private final static String TAG = "mafuhua";
    private final String LIST_UUID = "UUID";
    public ImageView btnReturn;
    public ImageView btnConfig;
    public TextView titleHome;
    public LinearLayout layoutTitleBar;
    public LinearLayout appTitle;
    private Context context;
    private TextView tvTemNum;
    private BluetoothAdapter mBluetoothAdapter;
    private boolean mScanning;
    private Handler mHandler;
    private String mDeviceAddress;
    private BluetoothLeService mBluetoothLeService;
    private final ServiceConnection mServiceConnection = new ServiceConnection() {

        @Override
        public void onServiceConnected(ComponentName componentName,
                                       IBinder service) {
            mBluetoothLeService = ((BluetoothLeService.LocalBinder) service)
                    .getService();
            if (!mBluetoothLeService.initialize()) {
                Log.e(TAG, "Unable to initialize Bluetooth");
                //finish();
            }
            mBluetoothLeService.connect(mDeviceAddress);
        }

        @Override
        public void onServiceDisconnected(ComponentName componentName) {
            mBluetoothLeService = null;
        }
    };
    private BluetoothGattCharacteristic mNotifyCharacteristic;
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
             /*   mConnected = true;
                updateConnectionState(R.string.connected);
                invalidateOptionsMenu();*/
            } else if (BluetoothLeService.ACTION_GATT_DISCONNECTED
                    .equals(action)) {
          /*      mConnected = false;
                updateConnectionState(R.string.disconnected);
                invalidateOptionsMenu();
                clearUI();*/
            } else if (BluetoothLeService.ACTION_GATT_SERVICES_DISCOVERED
                    .equals(action)) {
                // Show all the supported services and characteristics on the
                // user interface.
                displayGattServices(mBluetoothLeService
                        .getSupportedGattServices());
            } else if (BluetoothLeService.ACTION_DATA_AVAILABLE.equals(action)) {
                displayData(intent
                        .getStringExtra(BluetoothLeService.EXTRA_DATA));
            }
        }
    };
    // Device scan callback.
    private BluetoothAdapter.LeScanCallback mLeScanCallback =
            new BluetoothAdapter.LeScanCallback() {

                @Override
                public void onLeScan(final BluetoothDevice device, int rssi, byte[] scanRecord) {

                    if (device == null) return;
                    mDeviceAddress = device.getAddress();
                    Log.d("mafuhua", "设备地址" + device.getAddress() + "设备名称" + device.getName());
                    //"DoouYa Thermometer"
                    if (device.getName().equals("DoouYa Thermometer")) {
                        Intent gattServiceIntent = new Intent(context, BluetoothLeService.class);
                        context.bindService(gattServiceIntent, mServiceConnection, context.BIND_AUTO_CREATE);
                        context.registerReceiver(mGattUpdateReceiver, makeGattUpdateIntentFilter());
                        Log.e(TAG, "3");
                        context.startService(gattServiceIntent);
                        Log.e(TAG, "4");
                        if (mBluetoothLeService != null) {
                            final boolean result = mBluetoothLeService.connect(device.getAddress());
                            Log.d(TAG, "Connect request result=" + result);
                        }

                        if (mScanning) {
                            mBluetoothAdapter.stopLeScan(mLeScanCallback);
                            mScanning = false;

                        }
                    } else {
                        mBluetoothAdapter.startLeScan(mLeScanCallback);
                    }

                }
            };

    public TemperaPager(Context context) {
        super(context);
        this.context = context;
    }

    public static IntentFilter makeGattUpdateIntentFilter() {
        final IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(BluetoothLeService.ACTION_GATT_CONNECTED);
        intentFilter.addAction(BluetoothLeService.ACTION_GATT_DISCONNECTED);
        intentFilter
                .addAction(BluetoothLeService.ACTION_GATT_SERVICES_DISCOVERED);
        intentFilter.addAction(BluetoothLeService.ACTION_DATA_AVAILABLE);
        return intentFilter;
    }

    @Override
    public View initView() {
        View view = View.inflate(context, R.layout.home_sub_tempera, null);
        tvTemNum = (TextView) view.findViewById(R.id.tv_tem_num);
        btnReturn = (ImageView) view.findViewById(R.id.btn_return);
        btnConfig = (ImageView) view.findViewById(R.id.btn_confirm);
        titleHome = (TextView) view.findViewById(R.id.tv_title_home);
        appTitle = (LinearLayout) view.findViewById(R.id.app_title);
        appTitle.setVisibility(View.GONE);
        titleHome.setText(titleStrings[5]);
        titleHome.setVisibility(View.VISIBLE);
        btnConfig.setVisibility(View.VISIBLE);
        btnReturn.setVisibility(View.VISIBLE);
        btnConfig.setOnClickListener(this);
        btnReturn.setOnClickListener(this);
        return view;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_confirm:
                HomeFragment.myViewPagers.setCurrentItem(0);
                /*  context.unbindService(mServiceConnection);
                  context.unregisterReceiver(mGattUpdateReceiver);
                  mBluetoothLeService = null;*/
                HomeFragment.flag = false;
                Toast.makeText(context, "提交", Toast.LENGTH_SHORT).show();
                break;
            case R.id.btn_return:
                HomeFragment.myViewPagers.setCurrentItem(0);
                /*  context.unbindService(mServiceConnection);
                  context.unregisterReceiver(mGattUpdateReceiver);
                  mBluetoothLeService = null;*/
                HomeFragment.flag = false;
                Toast.makeText(context, "返回", Toast.LENGTH_SHORT).show();

                break;
        }
    }

    @Override
    public void initData() {
        mHandler = new Handler();
        final BluetoothManager bluetoothManager =
                (BluetoothManager) context.getSystemService(Context.BLUETOOTH_SERVICE);
        mBluetoothAdapter = bluetoothManager.getAdapter();
        mBluetoothAdapter.enable();
        new Thread() {
            @Override
            public void run() {
                super.run();

                scanLeDevice(true);
            }

        }.start();
    }

    private void displayData(String data) {
        if (data != null) {
            System.out.println("DATA##########" + data);
            tvTemNum.setText(data);
          /*  mDataField.setText(data);
            textview_return_result.setText(data);*/
        }
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
            Log.d(TAG, "服务 " + uuid);
            currentServiceData.put(LIST_UUID, uuid);
            List<BluetoothGattCharacteristic> gattCharacteristics = gattService
                    .getCharacteristics();

            // Loops through available Characteristics.
            for (BluetoothGattCharacteristic gattCharacteristic : gattCharacteristics) {
                HashMap<String, String> currentCharaData = new HashMap<String, String>();
                uuid = gattCharacteristic.getUuid().toString();
                Log.d(TAG, "特征 " + uuid);

                if (uuid.equals("00002a1c-0000-1000-8000-00805f9b34fb")) {
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
}
