package com.nanningzhuanqian.vscreenshot.m01_wechat.custom;

import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.nanningzhuanqian.vscreenshot.R;
import com.nanningzhuanqian.vscreenshot.base.BaseActivity;
import com.nanningzhuanqian.vscreenshot.base.util.SPUtils;
import com.nanningzhuanqian.vscreenshot.common.Constant;

/**
 * 编辑零钱信息界面
 */
public class WechatChangeEditActivity extends BaseActivity implements View.OnClickListener {
    private LinearLayout llBank;
    private EditText edBank;
    private EditText edBankCardNo;
    private EditText edTime;
    private LinearLayout llTime;
    private LinearLayout llChange;
    private LinearLayout llFee;
    private EditText edChange;
    private EditText edFee;
    private Button btnSubmit;
    private String bank = "";
    private String bankCardNo = "";
    private String time = "";
    private long timeMillis = 0;
    private String change = "";
    private String fee = "";

    @Override
    protected int getLayoutId() {
        return R.layout.activity_wechat_change_edit;
    }

    @Override
    protected void initView() {
        initCommonTopBar();
        setCommonTitle("编辑余额及银行卡");
        llBank = findViewById(R.id.llBank);
        edBank = findViewById(R.id.edBank);
        edBankCardNo = findViewById(R.id.edBankCardNo);
        edTime = findViewById(R.id.edTime);
        llTime = findViewById(R.id.llTime);
        llChange = findViewById(R.id.llChange);
        llFee = findViewById(R.id.llFee);
        edChange = findViewById(R.id.edChange);
        edFee = findViewById(R.id.edFee);
        btnSubmit = findViewById(R.id.btnSubmit);
    }

    @Override
    protected void initEvent() {
//        llBank.setOnClickListener(this);
        btnSubmit.setOnClickListener(this);
    }

    @Override
    public void onBackPressed() {
        finish();
    }

    @Override
    protected void initData() {
        bank = (String) SPUtils.get(getApplicationContext(), Constant.KEY_BANK, "");
        edBank.setText(bank);
        bankCardNo = (String) SPUtils.get(getApplicationContext(), Constant.KEY_BANK_CARD_NO, "");
        edBankCardNo.setText(bankCardNo);
        time = (String) SPUtils.get(getApplicationContext(), Constant.KEY_BANK_TIME, "");
        edTime.setText(time);
        change = (String) SPUtils.get(getApplicationContext(), Constant.KEY_CHANGE, "");
        edChange.setText(change);
        fee = (String) SPUtils.get(getApplicationContext(), Constant.KEY_WITHDRAW_FEE, "");
        edFee.setText(fee);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {

            case R.id.btnSubmit:
                save();
                break;
        }
    }

    private void save() {
        bank = edBank.getText().toString();
        bankCardNo = edBankCardNo.getText().toString();
        time = edTime.getText().toString();
        change = edChange.getText().toString();
        fee = edFee.getText().toString();
        if(TextUtils.isEmpty(bank)){
            toast("请填写银行");
            return;
        }
        if(TextUtils.isEmpty(bankCardNo)){
            toast("请填写银行卡号后4位");
            return;
        }
        if(TextUtils.isEmpty(time)){
            toast("请填写到账时间");
            return;
        }
        if(TextUtils.isEmpty(change)){
            toast("请填写零钱");
            return;
        }
        if(TextUtils.isEmpty(fee)){
            toast("请填写费率");
            return;
        }
        SPUtils.put(getApplicationContext(),Constant.KEY_BANK,bank);
        SPUtils.put(getApplicationContext(),Constant.KEY_BANK_CARD_NO,bankCardNo);
        SPUtils.put(getApplicationContext(),Constant.KEY_CHANGE,change);
        SPUtils.put(getApplicationContext(),Constant.KEY_BANK_TIME,time);
        SPUtils.put(getApplicationContext(),Constant.KEY_WITHDRAW_FEE,fee);
        finish();
    }

}
