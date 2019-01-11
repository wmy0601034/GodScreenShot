package com.nanningzhuanqian.vscreenshot.m01_wechat.wechat.transfer;

import com.nanningzhuanqian.vscreenshot.R;
import com.nanningzhuanqian.vscreenshot.base.Util;

/**
 * 微信转账待对方确认收款界面
 * Created by lenovo on 2018/10/7.
 */

public class WechatTransferResultActivity3 extends WechatTransferResultBaseActivity {
    @Override
    protected int getLayoutId() {
        return R.layout.activity_wechat_transfer_result3;
    }

    @Override
    protected void initView() {
        transparentNavigationBar(R.color.white);
        initFinishButton();
        initWechatTopBar();
    }

    @Override
    protected void initEvent() {

    }

    @Override
    protected void initData() {
        initAvatarRes();
        initName();
        initAmount();
        setName(getString(R.string.wechat_transfer_wait_othoer_receive,name));
        long timeMillis = System.currentTimeMillis();
        String time = Util.stampToTransferTime(timeMillis);
        setTime(getString(R.string.wechat_transfer_time,time));
    }
}
