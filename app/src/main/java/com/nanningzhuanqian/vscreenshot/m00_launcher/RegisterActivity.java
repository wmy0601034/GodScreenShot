package com.nanningzhuanqian.vscreenshot.m00_launcher;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.nanningzhuanqian.vscreenshot.R;
import com.nanningzhuanqian.vscreenshot.base.BaseActivity;
import com.nanningzhuanqian.vscreenshot.base.Util;
import com.nanningzhuanqian.vscreenshot.base.net.CallbackListener;
import com.nanningzhuanqian.vscreenshot.base.net.HttpUtil;
import com.nanningzhuanqian.vscreenshot.base.util.SPUtils;

public class RegisterActivity extends BaseActivity implements View.OnClickListener {

    private TextView tvBack;
    private TextView tvTitle;
    private EditText edMobile;
    private EditText edPwd;
    private EditText edPwd1;
    private Button btnRegister;
    private LinearLayout llProtocol;
    private ImageView imgProtocol;
    private TextView tvProtocol;
    private boolean agree = true;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_register;
    }

    @Override
    protected void initView() {
        tvBack = (TextView) findViewById(R.id.tvBack);
        tvTitle = (TextView) findViewById(R.id.tvTitle);
        tvTitle.setText("注册");
        edMobile = (EditText) findViewById(R.id.edMobile);
        edPwd = (EditText) findViewById(R.id.edPwd);
        edPwd1 = (EditText) findViewById(R.id.edPwd1);
        llProtocol = (LinearLayout) findViewById(R.id.llProtocol);
        imgProtocol = (ImageView) findViewById(R.id.imgProtocol);
        tvProtocol = (TextView) findViewById(R.id.tvProtocol);
        btnRegister = (Button) findViewById(R.id.btnRegister);
    }

    @Override
    protected void initEvent() {
        tvBack.setOnClickListener(this);
        tvProtocol.setOnClickListener(this);
        llProtocol.setOnClickListener(this);
        btnRegister.setOnClickListener(this);
    }

    @Override
    protected void initData() {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tvBack:
                finish();
                break;
            case R.id.btnRegister:
                register();
                break;
            case R.id.tvProtocol:
                goProtocol();
                break;
            case R.id.llProtocol:
                setAgree();
                break;
        }
    }

    private void setAgree() {
        agree = !agree;
        if (agree) {
            imgProtocol.setImageResource(R.mipmap.protocol_selected);
        } else {
            imgProtocol.setImageResource(R.mipmap.protocol_not_selected);
        }
    }

    private void goProtocol() {
        Intent intent = new Intent(getThis(), ProtocolActivitity.class);
        startActivity(intent);
    }

    private void register() {
        if (!agree) {
            toast("请先阅读并同意用户协议");
            return;
        }
        String mobile = edMobile.getText().toString();
        String password = edPwd.getText().toString();
        String password1 = edPwd1.getText().toString();
        if (TextUtils.isEmpty(mobile)) {
            toast("手机号码不能为空");
            return;
        }
        if (mobile.length() != 11) {
            toast("需要输入11位数的手机号码");
            return;
        }
        if (!Util.isPhone(mobile)) {
            toast("手机号码不规范");
            return;
        }
        if (TextUtils.isEmpty(password) || TextUtils.isEmpty(password1)) {
            toast("密码不能为空");
            return;
        }
        if (password.length() < 6) {
            toast("密码不能小于6位数字");
            return;
        }
        if (!TextUtils.equals(password, password1)) {
            toast("两次输入的密码不一致");
            return;
        }
        showLoadingDialog(false);
        HttpUtil.getInstance().register(mobile, password, new CallbackListener() {
            @Override
            public void onSuccess() {
                hideLoadingDialog();
                Intent intent = new Intent(getThis(), LoginActivity.class);
                startActivity(intent);
            }

            @Override
            public void onGetSuccess(Object o) {
                hideLoadingDialog();
            }

            @Override
            public void onFailure(String message) {
                hideLoadingDialog();
                toast(message);
            }
        });
    }
}
