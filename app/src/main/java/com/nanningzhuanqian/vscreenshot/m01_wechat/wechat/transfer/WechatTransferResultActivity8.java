package com.nanningzhuanqian.vscreenshot.m01_wechat.wechat.transfer;

import com.nanningzhuanqian.vscreenshot.R;
import com.nanningzhuanqian.vscreenshot.base.Util;

/**
 * 微信转账对方已退还界面
 * Created by lenovo on 2018/10/7.
 */

public class WechatTransferResultActivity8 extends WechatTransferResultBaseActivity {
    @Override
    protected int getLayoutId() {
        return R.layout.activity_wechat_transfer_result8;
    }

    @Override
    protected void initView() {
        transparentNavigationBar(R.color.white);
        initWechatTopBar();
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
        long timeMillis = System.currentTimeMillis()-60*1000;
        String time = Util.stampToTransferTime(timeMillis);
        setTime(getString(R.string.wechat_transfer_time,time));
        long timeMillis1 = System.currentTimeMillis();
        String time1 = Util.stampToTransferTime(timeMillis1);
        setTime1(getString(R.string.wechat_transfer_time1,time1));
    }
}
