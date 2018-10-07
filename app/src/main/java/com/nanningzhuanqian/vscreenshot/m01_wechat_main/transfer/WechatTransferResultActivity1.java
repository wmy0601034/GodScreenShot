package com.nanningzhuanqian.vscreenshot.m01_wechat_main.transfer;

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
