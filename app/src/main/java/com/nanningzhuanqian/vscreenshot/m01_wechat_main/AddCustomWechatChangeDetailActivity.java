package com.nanningzhuanqian.vscreenshot.m01_wechat_main;

import android.app.Dialog;
import android.content.Intent;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.borax12.materialdaterangepicker.date.DatePickerDialog;
import com.borax12.materialdaterangepicker.time.RadialPickerLayout;
import com.borax12.materialdaterangepicker.time.TimePickerDialog;
import com.nanningzhuanqian.vscreenshot.R;
import com.nanningzhuanqian.vscreenshot.adapter.ChangeDetailAdapter;
import com.nanningzhuanqian.vscreenshot.base.BaseActivity;
import com.nanningzhuanqian.vscreenshot.base.Util;
import com.nanningzhuanqian.vscreenshot.item.ChangeDetailItem;
import com.nanningzhuanqian.vscreenshot.item.ChangeDetailItems;
import com.nanningzhuanqian.vscreenshot.m02_add_conversation.LocalAvatarSelectActivity;
import com.nanningzhuanqian.vscreenshot.m03_add_role.AddCustomRoleActivity;
import com.nanningzhuanqian.vscreenshot.model.ChangeDetail;
import com.nanningzhuanqian.vscreenshot.widget.NewActionSheetDialog;

import java.text.NumberFormat;
import java.util.Calendar;
import java.util.Random;

public class AddCustomWechatChangeDetailActivity extends BaseActivity {

    private LinearLayout llType;
    private TextView tvType;
    private LinearLayout llTime;
    private TextView tvTime;
    private EditText edAmount;
    private Button btnRandom;
    private Button btnSubmit;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_add_custom_wechat_change_detail;
    }

    @Override
    protected void initView() {
        llType = (LinearLayout) findViewById(R.id.llType);
        tvType = (TextView) findViewById(R.id.tvType);
        llTime = (LinearLayout) findViewById(R.id.llTime);
        tvTime = (TextView) findViewById(R.id.tvTime);
        edAmount = (EditText) findViewById(R.id.edAmount);
        btnRandom = (Button) findViewById(R.id.btnRandom);
        btnSubmit = (Button) findViewById(R.id.btnSubmit);
        initCommonTopBar();
        setCommonRightContent("添加", new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                submit();
            }
        });
    }

    @Override
    protected void initEvent() {
        llType.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showTypeSelectionSheetDialog();
            }
        });
        llTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDateDialog();
            }
        });
        btnRandom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                random();
            }
        });
        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                submit();
            }
        });
    }

    private void submit() {
        String amount = edAmount.getText().toString();
        if (TextUtils.isEmpty(amount)) {
            toast("请填写金额");
            return;
        }
        String time = tvTime.getText().toString();
        if (TextUtils.isEmpty(time)) {
            toast("请选时间");
            return;
        }
        long timeMillis = Util.getTimeMillis(time);
        Log.i(TAG,"type = "+type+" amount = "+amount+" timeMillis = "+timeMillis);
        ChangeDetailItem changeDetailItem = new ChangeDetailItem(type, amount, timeMillis);
        //保存到本地
        ChangeDetail changeDetail = changeDetailItem.convertToChangeDetail();
        changeDetail.save();

        ChangeDetailItems.getInstance().addFirst(changeDetailItem);
        finish();
    }

    private void random() {
        Random random = new Random();
        type = random.nextInt(7);
        String name = Util.getChangeDetailNameByType(type);
        tvType.setText(name);
        int randomMillisHour = random.nextInt(10000);
        long randomMillis = System.currentTimeMillis() - randomMillisHour * 60 * 1000;
        String time = Util.stampToDate(randomMillis);
        tvTime.setText(time);
        int randomInt = random.nextInt(200);
//        double randomDouble = Math.random();
//
//        NumberFormat numberFormat = NumberFormat.getInstance();
//        // 设置小数位数。
//        numberFormat.setMaximumFractionDigits(2);
//        // 执行格式化转换。
//        String amount = numberFormat.format((randomInt + randomDouble));
        edAmount.setText(String.valueOf(randomInt));
    }

    private int type = 2;

    private void showTypeSelectionSheetDialog() {
        NewActionSheetDialog.Builder builder = new NewActionSheetDialog.Builder(AddCustomWechatChangeDetailActivity.this);

        builder.setCancelable(false);
        builder.setCancelButtonVisiable(true);
        builder.setCanceledOnTouchOutside(true);
        builder.setTitle("选择零钱明细类型");
        builder.addSheetItem("群收款", NewActionSheetDialog
                .SheetItemColor.Blue, new NewActionSheetDialog.Builder
                .OnSheetItemClickListener() {
            @Override
            public void onClick(int which) {
                type = ChangeDetailAdapter.TYPE_GROUP_RECEIPTS;
                tvType.setText("群收款");
            }
        });
        builder.addSheetItem("微信转账（转出）", NewActionSheetDialog
                .SheetItemColor.Blue, new NewActionSheetDialog.Builder
                .OnSheetItemClickListener() {
            @Override
            public void onClick(int which) {
                //随机添加1个对话
                type = ChangeDetailAdapter.TYPE_WECHAT_TRANSFER_PAY;
                tvType.setText("微信转账（转出）");
            }
        });
        builder.addSheetItem("微信转账（收取）", NewActionSheetDialog
                .SheetItemColor.Blue, new NewActionSheetDialog.Builder
                .OnSheetItemClickListener() {
            @Override
            public void onClick(int which) {
                type = ChangeDetailAdapter.TYPE_WECHAT_TRANSFER_RECEIVED;
                tvType.setText("微信转账（收取）");
            }
        });
        builder.addSheetItem("发微信红包", NewActionSheetDialog
                .SheetItemColor.Blue, new NewActionSheetDialog.Builder
                .OnSheetItemClickListener() {
            @Override
            public void onClick(int which) {
                type = ChangeDetailAdapter.TYPE_WECHAT_RED_PACKET_SEND;
                tvType.setText("发微信红包");
            }
        });
        builder.addSheetItem("收微信红包", NewActionSheetDialog
                .SheetItemColor.Blue, new NewActionSheetDialog.Builder
                .OnSheetItemClickListener() {
            @Override
            public void onClick(int which) {
                type = ChangeDetailAdapter.TYPE_WECHAT_RED_PACKET_RECEIVED;
                tvType.setText("收微信红包");
            }
        });
        builder.addSheetItem("扫二维码付款", NewActionSheetDialog
                .SheetItemColor.Blue, new NewActionSheetDialog.Builder
                .OnSheetItemClickListener() {
            @Override
            public void onClick(int which) {
                type = ChangeDetailAdapter.TYPE_QRCODE_PAY;
                tvType.setText("扫二维码付款");
            }
        });
        builder.addSheetItem("扫二维码收款", NewActionSheetDialog
                .SheetItemColor.Blue, new NewActionSheetDialog.Builder
                .OnSheetItemClickListener() {
            @Override
            public void onClick(int which) {
                type = ChangeDetailAdapter.TYPE_QRCODE_REWARD;
                tvType.setText("扫二维码收款");
            }
        });
        builder.addSheetItem("退款", NewActionSheetDialog
                .SheetItemColor.Blue, new NewActionSheetDialog.Builder
                .OnSheetItemClickListener() {
            @Override
            public void onClick(int which) {
                type = ChangeDetailAdapter.TYPE_REFUND;
                tvType.setText("退款");
            }
        });
        Dialog dialog = builder.create();
        dialog.show();
    }


    private String yyyy;
    private String MM;
    private String dd;

    private void showDateDialog() {
        Calendar now = Calendar.getInstance();
        DatePickerDialog dpd = DatePickerDialog.newInstance(
                new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePickerDialog dialog, int year, int monthOfYear, int dayOfMonth, int yearEnd, int monthOfYearEnd, int dayOfMonthEnd) {
                        Log.i(TAG, "year = " + year + " monthOfYear = " + monthOfYear + " dayOfMonth = " + dayOfMonth);
                        yyyy = String.valueOf(year);
                        MM = String.valueOf(monthOfYear + 1);
                        dd = String.valueOf(dayOfMonth);
                        dialog.dismiss();
                        showTimeDialog();
                    }

                },
                now.get(Calendar.YEAR),
                now.get(Calendar.MONTH),
                now.get(Calendar.DAY_OF_MONTH)
        );

        dpd.show(getFragmentManager(), "Datepickerdialog");
    }

    private String hh;
    private String mm;
    private String ss = "00";
    private String updateTime;

    private void showTimeDialog() {
        Calendar now = Calendar.getInstance();
        TimePickerDialog tpd = TimePickerDialog.newInstance(
                new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(RadialPickerLayout dialog, int hourOfDay, int minute, int hourOfDayEnd, int minuteEnd) {
                        hh = String.valueOf(hourOfDay);
                        mm = String.valueOf(minute);
                        updateTime = yyyy + "-" + MM + "-" + dd + " " + hh + ":" + mm + ":" + ss;
                        tvTime.setText(updateTime);
                        Log.i(TAG, "hourOfDay = " + hourOfDay + " minute = " + minute);
                    }
                },
                now.get(Calendar.HOUR_OF_DAY),
                now.get(Calendar.MINUTE),
                true
        );
        tpd.show(getFragmentManager(), "Timepickerdialog");
    }

    @Override
    protected void initData() {

    }
}
