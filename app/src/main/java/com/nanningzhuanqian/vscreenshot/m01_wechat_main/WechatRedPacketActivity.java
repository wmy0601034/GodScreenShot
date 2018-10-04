package com.nanningzhuanqian.vscreenshot.m01_wechat_main;

import com.nanningzhuanqian.vscreenshot.R;
import com.nanningzhuanqian.vscreenshot.base.BaseActivity;

// TODO: 2018/10/5 加入本页面的逻辑代码 实现其功能
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
