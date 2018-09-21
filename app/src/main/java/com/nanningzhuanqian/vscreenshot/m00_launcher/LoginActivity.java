package com.nanningzhuanqian.vscreenshot.m00_launcher;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.nanningzhuanqian.vscreenshot.R;
import com.nanningzhuanqian.vscreenshot.base.BaseActivity;
import com.nanningzhuanqian.vscreenshot.base.Util;
import com.nanningzhuanqian.vscreenshot.base.net.CallbackListener;
import com.nanningzhuanqian.vscreenshot.base.net.HttpUtil;
import com.nanningzhuanqian.vscreenshot.base.util.SPUtils;

public class LoginActivity extends BaseActivity implements View.OnClickListener {

    static public final String EXTRA_JUMP_TO = "extra.JUMP_TO";
    private TextView tvBack;
    private TextView tvTitle;
    private Button btnLogin;
    private TextView tvRegister;
    private TextView tvReset;
    private EditText edMobile;
    private EditText edPwd;
    private Intent jumpIntent = null;
    private String cls;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_login;
    }

    protected void initEvent() {
        tvBack.setOnClickListener(this);
        btnLogin.setOnClickListener(this);
        tvRegister.setOnClickListener(this);
        tvReset.setOnClickListener(this);
    }

    @Override
    protected void initData() {
        cls = getIntent().getStringExtra(EXTRA_JUMP_TO);
        if(cls!=null && cls.length()>1){
            jumpIntent = new Intent();
            jumpIntent.setClassName(this,cls);
        }
    }

    protected void initView() {
        tvBack = (TextView) findViewById(R.id.tvBack);
        tvTitle = (TextView) findViewById(R.id.tvTitle);
        tvTitle.setText("登录");
        btnLogin = (Button) findViewById(R.id.btnLogin);
        tvRegister = (TextView) findViewById(R.id.tvRegister);
        tvReset = (TextView) findViewById(R.id.tvReset);
        edMobile = (EditText) findViewById(R.id.edMobile);
        edPwd = (EditText) findViewById(R.id.edPwd);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tvBack:
                finish();
                break;
            case R.id.btnLogin:
                login();
                break;
            case R.id.tvRegister:
                register();
                break;
            case R.id.tvReset:
                resetPwd();
                break;
        }
    }

    private void login() {
        final String mobile = edMobile.getText().toString();
        String password = edPwd.getText().toString();
        if(TextUtils.isEmpty(mobile)){
            toast("手机号码不能为空");
            return;
        }
        if(mobile.length()!=11){
            toast("需要输入11位数的手机号码");
            return;
        }
        if(!Util.isPhone(mobile)){
            toast("手机号码不规范");
            return;
        }
        if(TextUtils.isEmpty(password)){
            toast("密码不能为空");
            return;
        }
        if(password.length()<6){
            toast("密码不能小于6位数字");
            return;
        }
        showLoadingDialog();
        HttpUtil.getInstance().login(mobile, password, new CallbackListener() {
            @Override
            public void onSuccess() {
                SPUtils.put(getThis(),"mobile",mobile);
                hideLoadingDialog();
                if(jumpIntent!=null)startActivity(jumpIntent);
                finish();
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

    private void register() {
        Intent intent = new Intent(getThis(),RegisterActivity.class);
        intent.putExtra(EXTRA_JUMP_TO,cls);
        startActivity(intent);
    }

    private void resetPwd() {
//        Intent intent = new Intent(getThis(),ResetPwdActivity.class);
//        intent.putExtra(EXTRA_JUMP_TO,cls);
//        startActivity(intent);
        toast("暂不支持，请联系客服申请重置密码");
    }
}
