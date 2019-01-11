package com.nanningzhuanqian.vscreenshot.m00_launcher.user;

import android.view.View;
import android.widget.TextView;

import com.nanningzhuanqian.vscreenshot.R;
import com.nanningzhuanqian.vscreenshot.base.BaseActivity;

/**
 * 用户协议界面
 */
public class ProtocolActivitity extends BaseActivity {
    private TextView tvBack;
    private TextView tvTitle;
    @Override
    protected int getLayoutId() {
        return R.layout.activity_protocol;
    }

    @Override
    protected void initView() {
        tvBack = (TextView)findViewById(R.id.tvBack);
        tvBack.setVisibility(View.VISIBLE);
        tvTitle = (TextView)findViewById(R.id.tvTitle);
        tvTitle.setText("用户协议");
    }

    @Override
    protected void initEvent() {
        tvBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    @Override
    protected void initData() {

    }
}
