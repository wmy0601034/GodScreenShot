package com.nanningzhuanqian.vscreenshot.m01_wechat_main;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.nanningzhuanqian.vscreenshot.R;
import com.nanningzhuanqian.vscreenshot.base.BaseActivity;

public class WechatTransferActivity extends BaseActivity implements View.OnClickListener {

    private ImageView imgAvatar;
    private TextView tvName;
    private EditText edMoney;
    private TextView tvDescription;
    private Button btnSubmit;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_wechat_transfer;
    }

    @Override
    protected void initView() {
        initWechatTopBar();
        btnRight.setVisibility(View.GONE);
        imgAvatar = (ImageView) findViewById(R.id.imgAvatar);
        tvName = (TextView) findViewById(R.id.tvName);
        edMoney = (EditText) findViewById(R.id.edMoney);
        tvDescription = (TextView) findViewById(R.id.tvDescription);
        btnSubmit = (Button) findViewById(R.id.btnSubmit);
    }

    @Override
    protected void initEvent() {
        tvDescription.setOnClickListener(this);
        btnSubmit.setOnClickListener(this);
    }

    @Override
    protected void initData() {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tvDescription:
                // TODO: 2018/9/18 暂不做处理
                break;
            case R.id.btnSubmit:
                // TODO: 2018/9/18 点击转账后 弹出选择转账成功还是 待收取的页面 
                break;
        }
    }
}
