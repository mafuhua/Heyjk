package com.yuenkeji.heyjk.viewpager.homesubPagers.weight;

import android.content.Context;
import android.view.View;

import com.yuenkeji.heyjk.viewpager.BasePager;

/**
 * 体成分测量
 * Created by Administrator on 2016/2/19.
 */
public class CompositionPager extends BasePager implements View.OnClickListener {
    public CompositionPager(Context context) {
        super(context);
    }

    @Override
    public View initView() {
        return null;
    }

    @Override
    public void initData() {

    }

    @Override
    public void onClick(View v) {

    }

   /* private final static String TAG = "mafuhua";
    // Stops scanning after 10 seconds.
    private static final long SCAN_PERIOD = 10000;
    private final String LIST_NAME = "NAME";
    private final String LIST_UUID = "UUID";
    public ImageView btnReturn;
    public ImageView btnConfig;
    public TextView titleHome;
    public LinearLayout appTitle;
    private TextView tv_jirou;
    private TextView tv_zhifang;
    private TextView tv_shuifen;
    private TextView tv_guliang;
    private TextView tv_tizhong;
    private TextView tv_neizang;
    private TextView tv_bmr;
    private Context context;
    private TextView tv_weight;
    private TextView mConnectionState;
    private TextView mDataField;
    private String mDeviceAddress;
    private BluetoothAdapter mBluetoothAdapter;
    private boolean mScanning;
    private Handler mHandler;
    private TextView textview_return_result; // 返回结果按钮
    private BloodPressureService mBluetoothLeService;
    // Code to manage Service lifecycle.
    private final ServiceConnection mServiceConnection = new ServiceConnection() {

        @Override
        public void onServiceConnected(ComponentName componentName,
                                       IBinder service) {
            mBluetoothLeService = ((BloodPressureService.LocalBinder) service)
                    .getService();
            if (!mBluetoothLeService.initialize()) {
                Log.e(TAG, "Unable to initialize Bluetooth");
                //finish();
            }
            // Automatically connects to the device upon successful start-up
            // initialization.
            mBluetoothLeService.connect(mDeviceAddress);
        }

        @Override
        public void onServiceDisconnected(ComponentName componentName) {
            mBluetoothLeService = null;
        }
    };
    private ArrayList<ArrayList<BluetoothGattCharacteristic>> mGattCharacteristics = new ArrayList<ArrayList<BluetoothGattCharacteristic>>();
    private boolean mConnected = false;
    private BluetoothGattCharacteristic mNotifyCharacteristic;
    private final BroadcastReceiver mGattUpdateReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            final String action = intent.getAction();
            if (BloodPressureService.ACTION_GATT_CONNECTED.equals(action)) {
                mConnected = true;
             *//*   updateConnectionState(R.string.connected);
                invalidateOptionsMenu();*//*
            } else if (BloodPressureService.ACTION_GATT_DISCONNECTED
                    .equals(action)) {
                mConnected = false;
                //   updateConnectionState(R.string.disconnected);


            } else if (BloodPressureService.ACTION_GATT_SERVICES_DISCOVERED
                    .equals(action)) {
                // Show all the supported services and characteristics on the
                // user interface.
                displayGattServices(mBluetoothLeService
                        .getSupportedGattServices());
            } else if (BloodPressureService.ACTION_DATA_AVAILABLE.equals(action)) {
                displayData(intent
                        .getStringExtra(BloodPressureService.EXTRA_DATA));
            }
        }
    };
    private BluetoothAdapter.LeScanCallback mLeScanCallback =
            new BluetoothAdapter.LeScanCallback() {

                @Override
                public void onLeScan(final BluetoothDevice device, int rssi, byte[] scanRecord) {
                    mDeviceAddress = device.getAddress();
                    Log.d("mafuhua", "设备地址" + device.getAddress() + "设备名称" + device.getName());
                    //"DoouYa Thermometer"
                    if (device.getName().equals("eBody-Fat-Scale")) {
                        Intent gattServiceIntent = new Intent(context, BloodPressureService.class);
                        context.bindService(gattServiceIntent, mServiceConnection, context.BIND_AUTO_CREATE);
                        context.registerReceiver(mGattUpdateReceiver, makeGattUpdateIntentFilter());
                        Log.e(TAG, "3");
                        context.startService(gattServiceIntent);
                        Log.e(TAG, "4");
                        if (mBluetoothLeService != null) {
                            final boolean result = mBluetoothLeService.connect(device.getAddress());
                            Log.d(TAG, "Connect request result=" + result);
                            byte[] bb = {(byte) 0xfd, 0x53, 0x42, (byte) 0xbe, 0x40, (byte) 0x98, (byte) 0xb4};
                            mBluetoothLeService.writeLlsAlertLevel(2, bb);
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
    private TextView tvTemNum;

    public CompositionPager(Context context) {
        super(context);
        this.context = context;
    }

    private static IntentFilter makeGattUpdateIntentFilter() {
        final IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(BloodPressureService.ACTION_GATT_CONNECTED);
        intentFilter.addAction(BloodPressureService.ACTION_GATT_DISCONNECTED);
        intentFilter
                .addAction(BloodPressureService.ACTION_GATT_SERVICES_DISCOVERED);
        intentFilter.addAction(BloodPressureService.ACTION_DATA_AVAILABLE);
        return intentFilter;
    }

    @Override
    public View initView() {
        View view = View.inflate(context, R.layout.home_sub_composition, null);
        btnReturn = (ImageView) view.findViewById(R.id.btn_return);
        btnConfig = (ImageView) view.findViewById(R.id.btn_confirm);
        titleHome = (TextView) view.findViewById(R.id.tv_title_home);
        tv_jirou = (TextView) view.findViewById(R.id.tv_jirou);
        tv_zhifang = (TextView) view.findViewById(R.id.tv_zhifang);
        tv_shuifen = (TextView) view.findViewById(R.id.tv_shuifen);
        tv_guliang = (TextView) view.findViewById(R.id.tv_guliang);
        tv_tizhong = (TextView) view.findViewById(R.id.tv_tizhong);
        tv_neizang = (TextView) view.findViewById(R.id.tv_neizang);
        tv_bmr = (TextView) view.findViewById(R.id.tv_bmr);
        titleHome = (TextView) view.findViewById(R.id.tv_title_home);
        appTitle = (LinearLayout) view.findViewById(R.id.app_title);
        appTitle.setVisibility(View.GONE);
        titleHome.setText(titleStrings[1]);
        titleHome.setVisibility(View.VISIBLE);
        btnConfig.setVisibility(View.VISIBLE);
        btnReturn.setVisibility(View.VISIBLE);
        btnConfig.setOnClickListener(this);
        btnReturn.setOnClickListener(this);
        return view;
    }

    @Override
    public void initData() {

        mHandler = new Handler();
        final BluetoothManager bluetoothManager =
                (BluetoothManager) context.getSystemService(Context.BLUETOOTH_SERVICE);
        mBluetoothAdapter = bluetoothManager.getAdapter();
        mBluetoothAdapter.enable();
        scanLeDevice(true);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_confirm:
                HomeFragment.myViewPagers.setCurrentItem(0);
              *//*  context.unbindService(mServiceConnection);
                context.unregisterReceiver(mGattUpdateReceiver);
                mBluetoothLeService = null;*//*
                HomeFragment.flag = false;
                Toast.makeText(context, "提交", Toast.LENGTH_SHORT).show();
                break;
            case R.id.btn_return:
                HomeFragment.myViewPagers.setCurrentItem(0);
               *//* context.unbindService(mServiceConnection);
                context.unregisterReceiver(mGattUpdateReceiver);
                mBluetoothLeService = null;*//*
                HomeFragment.flag = false;
                Toast.makeText(context, "返回", Toast.LENGTH_SHORT).show();

                break;
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
                    //invalidateOptionsMenu();
                }
            }, SCAN_PERIOD);

            mScanning = true;
            mBluetoothAdapter.startLeScan(mLeScanCallback);
        } else {
            mScanning = false;
            mBluetoothAdapter.stopLeScan(mLeScanCallback);
        }
        //invalidateOptionsMenu();
    }


    public void MyonClick(View view) {
        switch (view.getId()) {
            //唤醒
           *//* case R.id.button1:
                byte[] bb = {(byte) 0xfd, 0x53, 0x42, (byte) 0xbe, 0x40, (byte) 0x98, (byte) 0xb4};
                mBluetoothLeService.writeLlsAlertLevel(2, bb);
                Toast.makeText(DeviceControlActivity.this, "数据已经发送出去",
                        Toast.LENGTH_SHORT).show();
                break;*//*

        }
    }

    private void updateConnectionState(final int resourceId) {
   *//*     runOnUiThread(new Runnable() {
            @Override
            public void run() {
                System.out.println("ResourceId##########" + resourceId);
                mConnectionState.setText(resourceId);
            }
        });*//*
    }

    private void displayData(String data) {

        if (data != null&&data.length()>15) {


            String datas = data.toString();
            String str = datas.replace(" ", "");
            Log.d("mafuhua", datas + "+++++++++++");
            String tizhong = str.substring(3, 6);
            String zhifang = str.substring(22, 24);
            String zhifang2 = str.substring(24,25);
            String shuifen = str.substring(25, 28);
            String jirou = str.substring(28, 32);
            String guliang = str.substring(32, 34);
            String neizang = str.substring(34, 36);
            String reliang = str.substring(36, 40);


            int tizhong0 = Integer.parseInt(tizhong, 16);

            int zhifang0 = Integer.parseInt(zhifang, 16);
            int zhifang3 = Integer.parseInt(zhifang2, 16);
            zhifang0 = zhifang0+zhifang3;
            int shuifen0 = Integer.parseInt(shuifen, 16);
            int jirou0 = Integer.parseInt(jirou, 16);
            int guliang0 = Integer.parseInt(guliang, 16);
            int neizang0 = Integer.parseInt(neizang, 16);
            int reliang0 = Integer.parseInt(reliang, 16);

            String substring = str.substring(3, 6);
            Log.d("mafuhua", substring + "****");
            Log.d("mafuhua", substring + "****");
            Integer valueOf = Integer.parseInt(substring, 16);
            Log.d("mafuhua", "体重********" + valueOf / 10 + "KG");

            tv_tizhong.setText((float) tizhong0 / 10 + "KG");
            tv_zhifang.setText((float) zhifang0 / 10 + "%");
            tv_jirou.setText((float)jirou0 / 10 + "%");
            tv_shuifen.setText((float)shuifen0/10 + "%");
            tv_guliang.setText(guliang0 + "%");
            tv_neizang.setText(neizang0 + "");
            tv_bmr.setText(reliang0 + "Kcal");

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
       *//* String unknownServiceString = getResources().getString(
                R.string.unknown_service);
        String unknownCharaString = getResources().getString(
                R.string.unknown_characteristic);*//*
        ArrayList<HashMap<String, String>> gattServiceData = new ArrayList<HashMap<String, String>>();
        ArrayList<ArrayList<HashMap<String, String>>> gattCharacteristicData = new ArrayList<ArrayList<HashMap<String, String>>>();
        mGattCharacteristics = new ArrayList<ArrayList<BluetoothGattCharacteristic>>();

        // Loops through available GATT Services.
        for (BluetoothGattService gattService : gattServices) {
            HashMap<String, String> currentServiceData = new HashMap<String, String>();
            uuid = gattService.getUuid().toString();
            Log.d("mafuhua", "服务------" + uuid);
            currentServiceData.put(LIST_NAME,
                    SampleGattAttributes.lookup(uuid, "weizhifuwu"));
            currentServiceData.put(LIST_UUID, uuid);
            gattServiceData.add(currentServiceData);

            ArrayList<HashMap<String, String>> gattCharacteristicGroupData = new ArrayList<HashMap<String, String>>();
            List<BluetoothGattCharacteristic> gattCharacteristics = gattService
                    .getCharacteristics();
            ArrayList<BluetoothGattCharacteristic> charas = new ArrayList<BluetoothGattCharacteristic>();

            // Loops through available Characteristics.
            for (BluetoothGattCharacteristic gattCharacteristic : gattCharacteristics) {
                charas.add(gattCharacteristic);
                HashMap<String, String> currentCharaData = new HashMap<String, String>();
                uuid = gattCharacteristic.getUuid().toString();
                Log.d("mafuhua", "特征********" + uuid);

                if (uuid.equals("0000fff4-0000-1000-8000-00805f9b34fb")) {
                    Log.d("mafuhua", "特征----" + uuid);
                    final int charaProp = gattCharacteristic.getProperties();
                    Log.d("mafuhua", charaProp + "");
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
                currentCharaData.put(LIST_NAME,
                        SampleGattAttributes.lookup(uuid, "weizhi"));
                currentCharaData.put(LIST_UUID, uuid);
                gattCharacteristicGroupData.add(currentCharaData);
            }
            mGattCharacteristics.add(charas);
            gattCharacteristicData.add(gattCharacteristicGroupData);
        }

    }*/

}
