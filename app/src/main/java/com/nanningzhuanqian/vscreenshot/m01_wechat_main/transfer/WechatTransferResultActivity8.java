package com.nanningzhuanqian.vscreenshot.m01_wechat_main.transfer;

import com.nanningzhuanqian.vscreenshot.R;
import com.nanningzhuanqian.vscreenshot.base.Util;

/**
 * 对方已退还
 * Created by lenovo on 2018/10/7.
 */

public class WechatTransferResultActivity8 extends WechatTransferResultBaseActivity {
    @Override
    protected int getLayoutId() {
        return R.layout.activity_wechat_transfer_result8;
    }

    @Override
    protected void initView() {
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
        setTime1(time1);
    }
}
