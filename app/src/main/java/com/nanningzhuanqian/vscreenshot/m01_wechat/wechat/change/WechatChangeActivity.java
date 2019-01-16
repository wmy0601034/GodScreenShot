package com.nanningzhuanqian.vscreenshot.m01_wechat.wechat.change;

import android.content.Intent;
import android.view.View;

import com.gyf.barlibrary.ImmersionBar;
import com.nanningzhuanqian.vscreenshot.R;
import com.nanningzhuanqian.vscreenshot.base.BaseActivity;

/**
 * 微信零钱界面
 */
public class WechatChangeActivity extends BaseActivity {
    @Override
    protected int getLayoutId() {
        return R.layout.activity_wechat_change;
    }

    @Override
    public boolean isInMultiWindowMode() {
        return false;
    }

    @Override
    protected void initView() {
        initWechatTopBar();
        mImmersionBar = ImmersionBar.with(this).statusBarDarkFont(true);  //状态栏字体是深色，不写默认为亮色;
        mImmersionBar.init();
        btnOption.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getThis(),WechatChangeDetailActivity.class);
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