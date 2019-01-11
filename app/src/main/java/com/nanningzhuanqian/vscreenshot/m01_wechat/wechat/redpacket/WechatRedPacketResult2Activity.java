package com.nanningzhuanqian.vscreenshot.m01_wechat.wechat.redpacket;

import android.view.View;
import android.widget.TextView;

import com.makeramen.roundedimageview.RoundedImageView;
import com.nanningzhuanqian.vscreenshot.R;

/**
 * 对方已领取
 */
public class WechatRedPacketResult2Activity extends WechatRedPacketBaseResultActivity {

    private TextView tvOtherName;
    private TextView tvTime;
    private RoundedImageView imgOtherAvatar;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_wechat_red_packet_result2;
    }

    @Override
    protected void initView() {
        tvOtherName = findViewById(R.id.tvOtherName);
        tvTime = findViewById(R.id.tvTime);
        imgOtherAvatar = findViewById(R.id.imgOtherAvatar);
        setAvatar();
        setAmount();
        setName();
        setMark();
        String desciption = getString(R.string.wechat_red_packet_time_out,amount);
        setDescription(desciption);
    }

    @Override
    protected void initEvent() {
        tvOtherName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        tvTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        imgOtherAvatar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

    @Override
    protected void initData() {

    }
}
