package com.nanningzhuanqian.vscreenshot.m01_wechat_main;

import com.nanningzhuanqian.vscreenshot.R;
import com.nanningzhuanqian.vscreenshot.base.BaseActivity;

public class WechatRedPacketActivity extends BaseActivity {
    @Override
    protected int getLayoutId() {
        return R.layout.activity_wechat_red_packet;
    }

    @Override
    protected void initView() {
        initWechatTopBar();
        initStatusBar();
    }

    @Override
    protected void initEvent() {

    }

    @Override
    protected void initData() {

    }
}
