package com.nanningzhuanqian.vscreenshot.m01_wechat_main;

import android.app.Dialog;
import android.content.Intent;
import android.text.Editable;
import android.text.InputFilter;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.nanningzhuanqian.vscreenshot.R;
import com.nanningzhuanqian.vscreenshot.base.BaseActivity;
import com.nanningzhuanqian.vscreenshot.base.util.CashierInputFilter;
import com.nanningzhuanqian.vscreenshot.common.Constant;
import com.nanningzhuanqian.vscreenshot.m01_wechat_main.redpacket.WechatRedPacketResult1Activity;
import com.nanningzhuanqian.vscreenshot.m01_wechat_main.redpacket.WechatRedPacketResult2Activity;
import com.nanningzhuanqian.vscreenshot.m01_wechat_main.redpacket.WechatRedPacketResult3Activity;
import com.nanningzhuanqian.vscreenshot.m01_wechat_main.redpacket.WechatRedPacketResultActivity;
import com.nanningzhuanqian.vscreenshot.m01_wechat_main.transfer.WechatTransferResultActivity;
import com.nanningzhuanqian.vscreenshot.m01_wechat_main.transfer.WechatTransferResultActivity1;
import com.nanningzhuanqian.vscreenshot.widget.NewActionSheetDialog;

// TODO: 2018/10/5 加入本页面的逻辑代码 实现其功能

/**
 * 发微信红包界面
 */
public class WechatRedPacketActivity extends BaseActivity {

    private EditText edMoney;
    private EditText edMark;
    private TextView tvAmount;
    private TextView btnSubmit;
    private String mark;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_wechat_red_packet;
    }

    @Override
    protected void initView() {
        initWechatTopBar();
        initStatusBar();
        edMoney = findViewById(R.id.edMoney);
        edMark = findViewById(R.id.edMark);
        tvAmount = findViewById(R.id.tvAmount);
        btnSubmit = findViewById(R.id.btnSubmit);
        InputFilter[] filters = {new CashierInputFilter()};
        edMoney.setFilters(filters);
    }

    @Override
    protected void initEvent() {
        edMoney.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                String amount = edMoney.getText().toString();
                if(TextUtils.isEmpty(amount)){
                    edMoney.setHint("0.00");
                    tvAmount.setText("0.00");
                }else{
                    tvAmount.setText(getDecimalAmount(amount));
                }
            }
        });
        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String amout = edMoney.getText().toString();
                if(TextUtils.isEmpty(amout)){
                    toast("请输入金额");
                    return;
                }
                showRedPacketOtionSheetDialog();
            }
        });
    }

    @Override
    protected void initData() {

    }

    private void showRedPacketOtionSheetDialog(){
        final String amout = getDecimalAmount(edMoney.getText().toString());
        mark = edMark.getText().toString();
        Log.i(TAG,"mark = "+mark);
        if(TextUtils.isEmpty(mark)){
            mark = "恭喜发财，大吉大利";
        }
        NewActionSheetDialog.Builder builder = new NewActionSheetDialog.Builder(WechatRedPacketActivity.this);

        builder.setCancelable(false);
        builder.setCancelButtonVisiable(true);
        builder.setCanceledOnTouchOutside(true);
        builder.setTitle("选择红包状态");
        builder.addSheetItem("红包已过期", NewActionSheetDialog.SheetItemColor.Blue, new NewActionSheetDialog.Builder.OnSheetItemClickListener() {
            @Override
            public void onClick(int which) {
                Intent intent = new Intent(getThis(),WechatRedPacketResultActivity.class);
                intent.putExtra(Constant.INTENT_KEY_AMOUNT,amout);
                intent.putExtra(Constant.INTENT_KEY_MARK,mark);
                startActivity(intent);
            }
        });
        builder.addSheetItem("待对方领取", NewActionSheetDialog
                .SheetItemColor.Blue, new NewActionSheetDialog.Builder
                .OnSheetItemClickListener() {
            @Override
            public void onClick(int which) {
                Intent intent = new Intent(getThis(),WechatRedPacketResult1Activity.class);
                intent.putExtra(Constant.INTENT_KEY_AMOUNT,amout);
                intent.putExtra(Constant.INTENT_KEY_MARK,mark);
                startActivity(intent);
            }
        });
        builder.addSheetItem("对方已领取", NewActionSheetDialog
                .SheetItemColor.Blue, new NewActionSheetDialog.Builder
                .OnSheetItemClickListener() {
            @Override
            public void onClick(int which) {
                Intent intent = new Intent(getThis(),WechatRedPacketResult2Activity.class);
                intent.putExtra(Constant.INTENT_KEY_AMOUNT,amout);
                intent.putExtra(Constant.INTENT_KEY_MARK,mark);
                startActivity(intent);
            }
        });
        builder.addSheetItem("我已领取", NewActionSheetDialog
                .SheetItemColor.Blue, new NewActionSheetDialog.Builder
                .OnSheetItemClickListener() {
            @Override
            public void onClick(int which) {
                Intent intent = new Intent(getThis(),WechatRedPacketResult3Activity.class);
                intent.putExtra(Constant.INTENT_KEY_AMOUNT,amout);
                intent.putExtra(Constant.INTENT_KEY_MARK,mark);
                startActivity(intent);
            }
        });
        Dialog dialog = builder.create();
        dialog.show();
    }

    private String getDecimalAmount(String amount){
        String decimalAmount = "";
        if(amount.contains(".")){
            String [] numbers = amount.split("\\.");
            if(numbers.length==1){
                decimalAmount = numbers[0]+".00";
            }else{
                if(numbers[1].length()==1){
                    decimalAmount = numbers[0]+"."+numbers[1]+"0";
                }else if(numbers[1].length()==2){
                    decimalAmount = amount;
                }else if(numbers[1].length()==0){
                    decimalAmount = amount+".00";
                }
            }
        }else{
            decimalAmount = amount+".00";
        }
        return decimalAmount;
    }

}
