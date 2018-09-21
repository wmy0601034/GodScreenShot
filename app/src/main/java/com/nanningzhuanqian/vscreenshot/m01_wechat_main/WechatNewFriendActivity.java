package com.nanningzhuanqian.vscreenshot.m01_wechat_main;

import android.content.Intent;
import android.view.View;
import android.widget.LinearLayout;

import com.nanningzhuanqian.vscreenshot.R;
import com.nanningzhuanqian.vscreenshot.base.BaseActivity;

public class WechatNewFriendActivity extends BaseActivity {

    private LinearLayout llAdd;
    @Override
    protected int getLayoutId() {
        return R.layout.activity_wechat_new_friend;
    }

    @Override
    protected void initView() {
        initWechatTopBar();
        btnOption.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getThis(),WechatAddNewFriendActivity.class);
                startActivity(intent);
            }
        });
        llAdd = (LinearLayout)findViewById(R.id.llAdd);
        llAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getThis(),WechatAddNewFriendActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void initEvent() {

    }

    @Override
    protected void initData() {

    }
}
