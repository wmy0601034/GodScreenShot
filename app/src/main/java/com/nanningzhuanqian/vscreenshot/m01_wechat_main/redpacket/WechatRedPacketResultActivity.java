package com.nanningzhuanqian.vscreenshot.m01_wechat_main.redpacket;

import com.nanningzhuanqian.vscreenshot.R;
import com.nanningzhuanqian.vscreenshot.base.BaseActivity;
import com.nanningzhuanqian.vscreenshot.m01_wechat_main.WechatRedPacketActivity;

/**
 * 红包已过期
 */
public class WechatRedPacketResultActivity extends WechatRedPacketBaseResultActivity {
    @Override
    protected int getLayoutId() {
        return R.layout.activity_wechat_red_packet_result;
    }

    @Override
    protected void initView() {
        setAvatar();
        setAmount();
        setName();
        setMark();
        String desciption = getString(R.string.wechat_red_packet_time_out,amount);
        setDescription(desciption);
    }

    @Override
    protected void initEvent() {

    }

    @Override
    protected void initData() {

    }
}
