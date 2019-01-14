package com.nanningzhuanqian.vscreenshot.m00_launcher.user;

import android.content.Intent;
import android.graphics.Rect;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.nanningzhuanqian.vscreenshot.R;
import com.nanningzhuanqian.vscreenshot.base.BaseActivity;
import com.nanningzhuanqian.vscreenshot.base.Util;
import com.nanningzhuanqian.vscreenshot.base.bean.Contact;
import com.nanningzhuanqian.vscreenshot.base.net.CallbackListener;
import com.nanningzhuanqian.vscreenshot.base.net.HttpUtil;
import com.nanningzhuanqian.vscreenshot.base.util.SPUtils;
import com.nanningzhuanqian.vscreenshot.common.Constant;

import java.lang.reflect.Field;

/**
 * 登录界面
 */
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
    private LinearLayout parent;
    private View myLayout;
    private int bottom_status_bar_height = 0;
    private int soft_input_keyborad_height = 0;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_login;
    }

    protected void initEvent() {
        tvBack.setOnClickListener(this);
        btnLogin.setOnClickListener(this);
        tvRegister.setOnClickListener(this);
        tvReset.setOnClickListener(this);
        parent.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                Rect r = new Rect();                // 使用最外层布局填充，进行测算计算
                parent.getWindowVisibleDisplayFrame(r);
                int screenHeight = myLayout.getRootView().getHeight();
                int heightDiff = screenHeight - (r.bottom - r.top);
                if (heightDiff > 100) {
                    // 如果超过100个像素，它可能是一个键盘。获取状态栏的高度
                    statusBarHeight = 0;
                }
                try {
                    Class<?> c = Class.forName("com.android.internal.R$dimen");
                    Object obj = c.newInstance();
                    Field field = c.getField("status_bar_height");
                    int x = Integer.parseInt(field.get(obj).toString());
                    statusBarHeight = getResources().getDimensionPixelSize(x);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                int layoutHeight = heightDiff - statusBarHeight;
                if(0<layoutHeight&&layoutHeight<=180){
                    bottom_status_bar_height = layoutHeight;
                    Log.i(TAG, "虚拟导航键的高度为 height(单位像素) = " + bottom_status_bar_height);
                    SPUtils.put(getThis(), Constant.KEY_BOTTOM_STATUS_BAR_HEIGHT,bottom_status_bar_height);
                }else if(layoutHeight>180){
                    soft_input_keyborad_height = layoutHeight-bottom_status_bar_height;
                    Log.i(TAG, "键盘的高度为 height(单位像素) = " + soft_input_keyborad_height);
                    SPUtils.put(getThis(),Constant.KEY_SOFT_INPUT_KEYBORAD_HEIGHT,soft_input_keyborad_height);
                }
            }
        });
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
        tvBack.setVisibility(View.VISIBLE);
        tvTitle = (TextView) findViewById(R.id.tvTitle);
        tvTitle.setText("登录");
        btnLogin = (Button) findViewById(R.id.btnLogin);
        tvRegister = (TextView) findViewById(R.id.tvRegister);
        tvReset = (TextView) findViewById(R.id.tvReset);
        edMobile = (EditText) findViewById(R.id.edMobile);
        edPwd = (EditText) findViewById(R.id.edPwd);
        parent = (LinearLayout)findViewById(R.id.parent);
        myLayout = getWindow().getDecorView();
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
                SPUtils.put(getThis(),Constant.KEY_MOBILE,mobile);
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
        Intent intent = new Intent(getThis(),ResetPwdActivity.class);
        intent.putExtra(EXTRA_JUMP_TO,cls);
        startActivity(intent);
    }
}
