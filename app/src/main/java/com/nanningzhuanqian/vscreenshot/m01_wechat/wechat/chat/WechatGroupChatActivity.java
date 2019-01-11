package com.nanningzhuanqian.vscreenshot.m01_wechat.wechat.chat;

import android.view.View;

import com.nanningzhuanqian.vscreenshot.R;
import com.nanningzhuanqian.vscreenshot.base.BaseActivity;

/**
 * 微信群聊界面
 */
public class WechatGroupChatActivity extends BaseActivity {

    @Override
    protected int getLayoutId() {
        return R.layout.activity_wechat_group_chat;
    }

    @Override
    protected void initView() {
        initWechatTopBar();
        btnRight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showGroupChatDialog();
            }
        });
    }

    private void showGroupChatDialog() {

    }

    @Override
    protected void initEvent() {

    }

    @Override
    protected void initData() {

    }
}
