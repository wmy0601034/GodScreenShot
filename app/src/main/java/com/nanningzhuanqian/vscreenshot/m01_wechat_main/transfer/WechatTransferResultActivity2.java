package com.nanningzhuanqian.vscreenshot.m01_wechat_main.transfer;

import com.nanningzhuanqian.vscreenshot.R;

/**
 * 付款成功页面
 * Created by lenovo on 2018/10/7.
 */

public class WechatTransferResultActivity2 extends WechatTransferResultBaseActivity {
    @Override
    protected int getLayoutId() {
        return R.layout.activity_wechat_transfer_result2;
    }

    @Override
    protected void initView() {
        transparentNavigationBar(R.color.white);
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
        setName(getString(R.string.wechat_transfer_wait_othoer_receive,name));
    }
}