package com.nanningzhuanqian.vscreenshot.m01_wechat_main;

import android.view.View;
import android.widget.Space;
import android.widget.TextView;

import com.nanningzhuanqian.vscreenshot.R;
import com.nanningzhuanqian.vscreenshot.base.BaseActivity;
import com.nanningzhuanqian.vscreenshot.base.util.SPUtils;
import com.nanningzhuanqian.vscreenshot.common.Constant;

public class WechatWithdrawDetailActivity extends BaseActivity implements View.OnClickListener {

    private TextView tvFinish;
    private TextView tvAmount;
    private TextView tvFee;
    private TextView tvBank;
    private TextView tvBankCard;
    private String withdrawResult = "";

    @Override
    protected int getLayoutId() {
        return R.layout.activity_wechat_withdraw_detail;
    }

    @Override
    protected void initView() {
        tvFinish = findViewById(R.id.tvFinish);
        tvAmount = findViewById(R.id.tvAmount);
        tvFee = findViewById(R.id.tvFee);
        tvBank = findViewById(R.id.tvBank);
        tvBankCard = findViewById(R.id.tvBankCard);
    }

    @Override
    protected void initEvent() {
        tvFinish.setOnClickListener(this);
    }

    @Override
    protected void initData() {
        String amout = getIntent().getStringExtra(Constant.INTENT_KEY_AMOUNT);
        tvAmount.setText(amout);
        String fee = (String) SPUtils.get(getApplicationContext(), Constant.KEY_WITHDRAW_FEE, "");
        tvFee.setText(fee);
        String bank = (String) SPUtils.get(getApplicationContext(), Constant.KEY_BANK, "");
        String bankNo = (String) SPUtils.get(getApplicationContext(), Constant.KEY_BANK_CARD_NO, "");
        tvBank.setText(bank);
        tvBankCard.setText(bankNo);
        withdrawResult = getIntent().getStringExtra(Constant.INTENT_KEY_WITHDRAW_RESULT);
        if (withdrawResult.equals(Constant.INTENT_VALUE_WITHDRAW_FINISH)) {

        } else if (withdrawResult.equals(Constant.INTENT_VALUE_WITHDRAW_WAIT)) {

        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tvFinish:
                finish();
                break;
        }
    }
}
