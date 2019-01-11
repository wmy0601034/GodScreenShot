package com.nanningzhuanqian.vscreenshot.m01_wechat.wechat.redpacket;

import com.nanningzhuanqian.vscreenshot.R;

/**
 * 微信红包待对方领取状态的界面
 */
public class WechatRedPacketResult1Activity extends WechatRedPacketBaseResultActivity {
    @Override
    protected int getLayoutId() {
        return R.layout.activity_wechat_red_packet_result1;
    }

    @Override
    protected void initView() {
        setAvatar();
        setAmount();
        setName();
        setMark();
        String desciption = getString(R.string.wechat_red_packet_other_not_received,amount);
        setDescription(desciption);
    }

    @Override
    protected void initEvent() {

    }

    @Override
    protected void initData() {

    }
}
