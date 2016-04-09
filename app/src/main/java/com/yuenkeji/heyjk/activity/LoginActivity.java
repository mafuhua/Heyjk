package com.yuenkeji.heyjk.activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.yuenkeji.heyjk.R;
import com.yuenkeji.heyjk.bean.LoginBean;
import com.yuenkeji.heyjk.utils.WEBUtils;
import com.yuenkeji.heyjk.utils.XUtils;

import org.xutils.common.Callback;

import java.util.HashMap;

public class LoginActivity extends AppCompatActivity {
    private SharedPreferences sharedPreferences;
    private ImageView imageView7;
    private EditText etLoginNum;
    private Button btnLogin;
    private Context context;

    private void assignViews() {
        context = this;
        sharedPreferences = getSharedPreferences("userinfo", MODE_PRIVATE);
        imageView7 = (ImageView) findViewById(R.id.imageView7);
        etLoginNum = (EditText) findViewById(R.id.et_login_num);
        btnLogin = (Button) findViewById(R.id.btn_login);
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String usernum = etLoginNum.getText().toString().trim();
                if (TextUtils.isEmpty(usernum)) {
                    Toast.makeText(context, "手机号不能为空", Toast.LENGTH_SHORT).show();
                    return;
                }
                String telRegex = "[1][358]\\d{9}";//"[1]"代表第1位为数字1，"[358]"代表第二位可以为3、5、8中的一个，"\\d{9}"代表后面是可以是0～9的数字，有9位。

                if (usernum.matches(telRegex)) {
                    sharedPreferences.edit().putString("usernum", usernum).apply();
                    HashMap<String, String> map = new HashMap<>();
                    map.put("mobile_phone", usernum);
                    XUtils.xUtilsPost(WEBUtils.LoginUrl, map, new Callback.CommonCallback<String>() {
                        @Override
                        public void onSuccess(String result) {
                            Log.d("mafuhua","LoginUrl=========="+ result);
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
                } else {
                    Toast.makeText(context, "请输入正确的手机号", Toast.LENGTH_SHORT).show();
                    return;
                }

            }
        });
    }

    private void parseJson(String result) {
        Gson gson = new Gson();
        LoginBean loginBean = gson.fromJson(result, LoginBean.class);
        if (loginBean.code.equals("0")) {
            Toast.makeText(context, "登陆成功", Toast.LENGTH_SHORT).show();
            sharedPreferences.edit().putString("user_id", loginBean.data.user_id).apply();
            Intent intent = new Intent(this,MainActivity.class);
            startActivity(intent);
            finish();
        } else if (loginBean.code.equals("1")) {
            Toast.makeText(context, "用户手机号为空", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        assignViews();
    }

    @Override
    protected void onResume() {
        super.onResume();
        if ((sharedPreferences.getString("usernum", "").length() > 1)) {
            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
            startActivity(intent);
            finish();
        }
    }


}
