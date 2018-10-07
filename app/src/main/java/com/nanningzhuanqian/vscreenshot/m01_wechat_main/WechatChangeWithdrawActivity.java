package com.nanningzhuanqian.vscreenshot.m01_wechat_main;

import android.app.Dialog;
import android.content.Intent;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import com.nanningzhuanqian.vscreenshot.MainActivity;
import com.nanningzhuanqian.vscreenshot.R;
import com.nanningzhuanqian.vscreenshot.base.BaseActivity;
import com.nanningzhuanqian.vscreenshot.base.util.SPUtils;
import com.nanningzhuanqian.vscreenshot.common.Constant;
import com.nanningzhuanqian.vscreenshot.item.ConversationItems;
import com.nanningzhuanqian.vscreenshot.m01_wechat_main.change.WechatChangeEditActivity;
import com.nanningzhuanqian.vscreenshot.m02_add_conversation.AddCustomConversationActivity;
import com.nanningzhuanqian.vscreenshot.model.ConversationLite;
import com.nanningzhuanqian.vscreenshot.widget.NewActionSheetDialog;

import org.litepal.LitePal;

public class WechatChangeWithdrawActivity extends BaseActivity implements View.OnClickListener {
    private ImageButton btnRight;
    private TextView tvBankCard;
    private TextView tvTime;
    private TextView tvBalance;
    private TextView tvWithdrawAll;
    private TextView tvWithdraw;
    private EditText edAmount;
    private String bank;
    private String bankCardNo;
    private String time;
    private String change;
    private String fee;
    private String amount;


    @Override
    protected int getLayoutId() {
        return R.layout.activity_wechat_change_withdraw;
    }

    @Override
    protected void initView() {
        initWechatTopBar();
        setWechatTitle("零钱提现");
        tvBankCard = findViewById(R.id.tvBankCard);
        tvTime = findViewById(R.id.tvTime);
        tvBalance = findViewById(R.id.tvBalance);
        tvWithdrawAll = findViewById(R.id.tvWithdrawAll);
        tvWithdraw = findViewById(R.id.tvWithdraw);
        edAmount = findViewById(R.id.edAmount);
        btnRight = findViewById(R.id.btnRight);
    }

    @Override
    protected void initEvent() {
        tvBankCard.setOnClickListener(this);
        tvWithdrawAll.setOnClickListener(this);
        tvWithdraw.setOnClickListener(this);
        btnRight.setOnClickListener(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        bank = (String) SPUtils.get(getApplicationContext(), Constant.KEY_BANK, "");
        bankCardNo = (String) SPUtils.get(getApplicationContext(), Constant.KEY_BANK_CARD_NO, "");
        tvBankCard.setText(getString(R.string.wechat_change_bank_card_no, bank) + "{" + bankCardNo + ")");
        time = (String) SPUtils.get(getApplicationContext(), Constant.KEY_BANK_TIME, "");
        tvTime.setText(time);
        change = (String) SPUtils.get(getApplicationContext(), Constant.KEY_CHANGE, "");
        tvBalance.setText(getString(R.string.wechat_change_withdraw_balance, change));
        fee = (String) SPUtils.get(getApplicationContext(), Constant.KEY_WITHDRAW_FEE, "");
//        edFee.setText(fee);
    }

    @Override
    protected void initData() {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tvBankCard:
                Intent intent = new Intent(getThis(), WechatChangeEditActivity.class);
                startActivity(intent);
                break;
            case R.id.tvWithdrawAll:
                edAmount.setText(change);
                amount = change;
                break;
            case R.id.tvWithdraw:
                amount = edAmount.getText().toString();
                if (TextUtils.isEmpty(amount)) {
                    toast("请输入提现金额");
                    return;
                }
                showWithDrawSheetDialog();
                break;
            case R.id.btnRight:
                Intent intent1 = new Intent(getThis(), WechatChangeEditActivity.class);
                startActivity(intent1);
                break;

        }
    }

    private void showWithDrawSheetDialog() {

        NewActionSheetDialog.Builder builder = new NewActionSheetDialog.Builder(WechatChangeWithdrawActivity.this);

        builder.setCancelable(false);
        builder.setCancelButtonVisiable(true);
        builder.setCanceledOnTouchOutside(true);
        builder.setTitle(getString(R.string.action_settings));
        builder.addSheetItem("提现详情页面", NewActionSheetDialog
                .SheetItemColor.Blue, new NewActionSheetDialog.Builder
                .OnSheetItemClickListener() {
            @Override
            public void onClick(int which) {
                // TODO: 2018/10/6 提现详情页面
                Intent intent = new Intent(getThis(), WechatWithdrawDetailActivity.class);
                intent.putExtra(Constant.INTENT_KEY_AMOUNT, amount);
                intent.putExtra(Constant.INTENT_KEY_WITHDRAW_RESULT, Constant.INTENT_VALUE_WITHDRAW_FINISH);
                startActivity(intent);
            }
        });
        builder.addSheetItem("发起提现通知", NewActionSheetDialog
                .SheetItemColor.Blue, new NewActionSheetDialog.Builder
                .OnSheetItemClickListener() {
            @Override
            public void onClick(int which) {
                // TODO: 2018/10/6 发起提现通知
                Intent intent = new Intent(getThis(), WechatWithdrawDetailActivity.class);
                intent.putExtra(Constant.INTENT_KEY_AMOUNT, amount);
                intent.putExtra(Constant.INTENT_KEY_WITHDRAW_RESULT, Constant.INTENT_VALUE_WITHDRAW_WAIT);
                startActivity(intent);
            }
        });
        Dialog dialog = builder.create();
        dialog.show();
    }
}
