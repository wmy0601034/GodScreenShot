package com.nanningzhuanqian.vscreenshot.m05_user;

import android.view.View;
import android.widget.TextView;

import com.nanningzhuanqian.vscreenshot.R;
import com.nanningzhuanqian.vscreenshot.base.BaseActivity;
import com.nanningzhuanqian.vscreenshot.base.util.SPUtils;

import cn.bmob.v3.BmobUser;

public class UserInfoActivity extends BaseActivity implements View.OnClickListener {
    private TextView btnLogout;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_user_info;
    }

    @Override
    protected void initView() {
        initCommonTopBar();
        setCommonTitle("账号设置");
        btnLogout = (TextView)findViewById(R.id.btnLogout);
    }

    @Override
    protected void initEvent() {
        btnLogout.setOnClickListener(this);
    }

    @Override
    protected void initData() {

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btnLogout:
                BmobUser.logOut();
                SPUtils.clear(getThis());
                finish();
                break;
        }
    }
}
