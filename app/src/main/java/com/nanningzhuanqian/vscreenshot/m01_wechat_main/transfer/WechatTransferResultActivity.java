package com.nanningzhuanqian.vscreenshot.m01_wechat_main.transfer;

import com.nanningzhuanqian.vscreenshot.R;

/**
 * 扫码转账成功 个人
 * Created by lenovo on 2018/10/7.
 */

public class WechatTransferResultActivity extends WechatTransferResultBaseActivity {
    @Override
    protected int getLayoutId() {
        return R.layout.activity_wechat_transfer_result;
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
        initReceiver();
    }
}
