package com.nanningzhuanqian.vscreenshot.m01_wechat.wechat.redpacket;

import com.nanningzhuanqian.vscreenshot.R;

/**
 * 微信红包我已领取状态的界面
 */
public class WechatRedPacketResult3Activity extends WechatRedPacketBaseResultActivity {
    @Override
    protected int getLayoutId() {
        return R.layout.activity_wechat_red_packet_result3;
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
