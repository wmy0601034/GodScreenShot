package com.nanningzhuanqian.vscreenshot.m01_wechat.wechat.transfer;

import android.os.Build;
import android.view.Window;
import android.view.WindowManager;

import com.nanningzhuanqian.vscreenshot.R;

/**
 * 扫码转账成功 商户
 * Created by lenovo on 2018/10/7.
 */

public class WechatTransferResultActivity1 extends WechatTransferResultBaseActivity {
    @Override
    protected int getLayoutId() {
        return R.layout.activity_wechat_transfer_result1;
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
    protected void initData() {
        initAvatarRes();
        initName();
        initAmount();
        setName(name);
    }
}
