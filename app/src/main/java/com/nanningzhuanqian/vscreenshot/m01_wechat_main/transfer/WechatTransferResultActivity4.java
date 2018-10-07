package com.nanningzhuanqian.vscreenshot.m01_wechat_main.transfer;

import com.nanningzhuanqian.vscreenshot.R;
import com.nanningzhuanqian.vscreenshot.base.Util;

/**
 * 对方已收钱
 * Created by lenovo on 2018/10/7.
 */

public class WechatTransferResultActivity4 extends WechatTransferResultBaseActivity {
    @Override
    protected int getLayoutId() {
        return R.layout.activity_wechat_transfer_result4;
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
        setName(getString(R.string.wechat_transfer_wait_othoer_received,name));
        long timeMillis = System.currentTimeMillis()-60*1000;
        String time = Util.stampToTransferTime(timeMillis);
        setTime(getString(R.string.wechat_transfer_time,time));
        long timeMillis1 = System.currentTimeMillis();
        String time1 = Util.stampToTransferTime(timeMillis1);
        setTime1(time1);
    }
}
