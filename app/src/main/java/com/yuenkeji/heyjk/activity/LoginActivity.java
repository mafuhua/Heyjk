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


}
