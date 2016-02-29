package com.yuenkeji.heyjk.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.yuenkeji.heyjk.R;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
    }

    @Override
    protected void onStop() {
        super.onStop();
        System.exit(0);
    }

/*    @Override
    protected void onDestroy() {
        super.onDestroy();
        System.exit(0);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (KeyEvent.KEYCODE_BACK== event.getKeyCode()){
            ActivityManager am = (ActivityManager)getSystemService (Context.ACTIVITY_SERVICE);
            am.restartPackage(getPackageName());
        }
        return super.onKeyDown(keyCode, event);
    }*/
}
