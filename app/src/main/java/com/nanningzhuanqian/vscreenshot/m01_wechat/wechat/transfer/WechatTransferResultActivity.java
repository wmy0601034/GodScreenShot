package com.nanningzhuanqian.vscreenshot.m01_wechat.wechat.transfer;

import android.os.Build;
import android.view.Window;
import android.view.WindowManager;

import com.nanningzhuanqian.vscreenshot.R;

/**
 * 微信转账扫码转账成功界面 个人
 * Created by lenovo on 2018/10/7.
 */

public class WechatTransferResultActivity extends WechatTransferResultBaseActivity {
    @Override
    protected int getLayoutId() {
        return R.layout.activity_wechat_transfer_result;
    }

    @Override
    protected void initView() {
//        transparentNavigationBar(R.color.white);
        try {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                Window window = getWindow();
                window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
                //底部导航栏
                window.setNavigationBarColor(getResources().getColor(R.color.white));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        initFinishButton();
    }

    @Override
    protected void initEvent() {

    }

    @Override
    protected void onResume() {
        super.onResume();
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(200);
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            transparentNavigationBar(R.color.white);
                        }
                    });
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
        }).start();
    }

    @Override
    protected void initData() {
        initAvatarRes();
        initName();
        initAmount();
        initReceiver();
    }
}
