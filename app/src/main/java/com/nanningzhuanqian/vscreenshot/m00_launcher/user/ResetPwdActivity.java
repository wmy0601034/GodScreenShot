package com.nanningzhuanqian.vscreenshot.m00_launcher.user;

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
import com.nanningzhuanqian.vscreenshot.common.Constant;

/**
 * 充值密码界面
 */
public class ResetPwdActivity extends BaseActivity implements View.OnClickListener {

    private EditText edMobile;
    private EditText edSmsCode;
    private Button btnSend;
    private EditText edPwd;
    private Button btnFinish;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_reset_pwd;
    }

    protected void initView() {
        tvTitle = (TextView) findViewById(R.id.tvTitle);
        tvTitle.setText("重置密码");
        edMobile = (EditText) findViewById(R.id.edMobile);
        edSmsCode = (EditText) findViewById(R.id.edSmsCode);
        btnSend = (Button) findViewById(R.id.btnSend);
        edPwd = (EditText) findViewById(R.id.edPwd);
        btnFinish = (Button) findViewById(R.id.btnFinish);
    }

    protected void initEvent() {
        btnFinish.setOnClickListener(this);
        btnSend.setOnClickListener(this);
    }

    protected void initData() {
        String mobile = (String) SPUtils.get(getApplicationContext(), Constant.KEY_MOBILE, "");
        if (TextUtils.isEmpty(mobile)) {
            return;
        }
        edMobile.setText(mobile);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnFinish:
                reset();
                break;
            case R.id.btnSend:
                send();
                break;
        }
    }

    private void send() {
        String mobile = edMobile.getText().toString();
        if (!Util.isPhone(mobile)) {
            toast(getString(R.string.login_tip_tel_error));
            return;
        }
    }

    private void reset() {
        String mobile = edMobile.getText().toString();
        if (!Util.isPhone(mobile)) {
            toast(getString(R.string.reset_tip_sms_error));
            return;
        }
        String smsCode = edSmsCode.getText().toString();
        if (!Util.isSmsCode(smsCode)) {
            toast(getString(R.string.reset_tip_sms_error));
            return;
        }
        String password = edPwd.getText().toString();
        if (TextUtils.isEmpty(password)) {
            toast("密码不能为空");
            return;
        }
        if (password.length() < 6) {
            toast("密码不能小于6位数字");
            return;
        }
        HttpUtil.getInstance().resetPwd(mobile, password, smsCode, new CallbackListener() {
            @Override
            public void onSuccess() {

            }

            @Override
            public void onGetSuccess(Object o) {

            }

            @Override
            public void onFailure(String message) {

            }
        });
        toast("该功能暂未实现");
    }
}
