package com.nanningzhuanqian.vscreenshot.m00_launcher;

import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.nanningzhuanqian.vscreenshot.R;
import com.nanningzhuanqian.vscreenshot.base.BaseActivity;
import com.nanningzhuanqian.vscreenshot.base.net.CallbackListener;
import com.nanningzhuanqian.vscreenshot.base.net.HttpUtil;
import com.nanningzhuanqian.vscreenshot.base.util.SPUtils;
import com.nanningzhuanqian.vscreenshot.common.Constant;
import com.nanningzhuanqian.vscreenshot.model.Config;
import com.squareup.picasso.Picasso;

import java.lang.reflect.Field;
import java.util.Timer;
import java.util.TimerTask;

public class WelcomeActivity extends BaseActivity {

    private ImageView imgAd;
    private TextView tvSkin;
    private int after = 1;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected boolean isImmersionBarEnabled() {
        return false;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_welcome;
    }

    @Override
    protected void initView() {
        tvSkin = (TextView) findViewById(R.id.tvSkin);
        imgAd = (ImageView) findViewById(R.id.imgAd);
    }

    @Override
    protected void initEvent() {
        imgAd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        tvSkin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getThis(),LauncherActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }

    @Override
    protected void initData() {
        tvSkin.setText(getString(R.string.jump_in, after + ""));
        String adUrl = (String) SPUtils.get(getThis(), Constant.KEY_AD_URL, "");
        if (!TextUtils.isEmpty(adUrl)) {
            Picasso.with(getThis()).load(adUrl).into(imgAd);
            imgAd.setClickable(true);
        } else {
            imgAd.setClickable(false);
            Log.i(TAG,"load mipmap");
            Picasso.with(getThis()).load(R.mipmap.welcome).into(imgAd);
        }
        getConfig();
    }

    @Override
    protected void onResume() {
        super.onResume();
        tvSkin.setVisibility(View.VISIBLE);
        startDownCount();
    }

    /**
     * 开始倒计时
     */
    Timer timer;
    TimerTask task;

    private void startDownCount() {
        if (timer != null) {
            timer.cancel();
            timer = null;
        }
        if (task != null) {
            task.cancel();
            task = null;
        }
        timer = new Timer();
        task = new TimerTask() {
            @Override
            public void run() {
                if (after != 0) {
                    after--;
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            tvSkin.setText(getString(R.string.jump_in, after + ""));
                            startDownCount();
                        }
                    });
                } else {
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Intent intent = new Intent(getThis(), LauncherActivity.class);
                            startActivity(intent);
                            finish();
                        }
                    });
                }
            }
        };
        timer.schedule(task,1000);
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (timer != null) {
            timer.cancel();
            timer = null;
        }
        if (task != null) {
            task.cancel();
            task = null;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        after = 0;
        if (timer != null) {
            timer.cancel();
            timer = null;
        }
        if (task != null) {
            task.cancel();
            task = null;
        }
    }

    public void getConfig() {
        HttpUtil.getInstance().getConfig(new CallbackListener() {
            @Override
            public void onSuccess() {
//                Intent intent = new Intent(getThis(), LauncherActivity.class);
//                startActivity(intent);
            }

            @Override
            public void onGetSuccess(Object o) {
                // TODO: 2018/10/5 在这里加上对小程序icon和小程序URL的获取 并在微信的发现页面加上对url的加载
                Config config = (Config) o;
                SPUtils.put(getThis(), Constant.KEY_CONTRACT_QQ, config.getContractQQ());
                SPUtils.put(getThis(), Constant.KEY_VERSION_CODE, config.getVersionCode());
                SPUtils.put(getThis(), Constant.KEY_VERSION_NAME, config.getVersionName());
                SPUtils.put(getThis(), Constant.KEY_RIGHT, config.getRight());
                SPUtils.put(getThis(), Constant.KEY_AD_URL, config.getAdUrl());
                Log.i(TAG,config.getContractQQ()+" "+config.getVersionCode()+" "+config.getVersionName()+" "+config.getRight()+" "+config.getAdUrl());
            }

            @Override
            public void onFailure(String message) {
//                Intent intent = new Intent(getThis(), LauncherActivity.class);
//                startActivity(intent);
                Log.i(TAG,"onFailure = "+message);
            }
        });
    }

}
