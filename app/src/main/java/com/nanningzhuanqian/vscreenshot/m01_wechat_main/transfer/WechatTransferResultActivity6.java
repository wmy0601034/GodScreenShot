package com.nanningzhuanqian.vscreenshot.m01_wechat_main.transfer;

import com.nanningzhuanqian.vscreenshot.R;
import com.nanningzhuanqian.vscreenshot.base.Util;

/**
 * 我已收钱
 * Created by lenovo on 2018/10/7.
 */

public class WechatTransferResultActivity6 extends WechatTransferResultBaseActivity {
    @Override
    protected int getLayoutId() {
        return R.layout.activity_wechat_transfer_result6;
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
