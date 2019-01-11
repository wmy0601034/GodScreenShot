package com.nanningzhuanqian.vscreenshot.m01_wechat.wechat.moment;

import android.app.Dialog;
import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.borax12.materialdaterangepicker.date.DatePickerDialog;
import com.borax12.materialdaterangepicker.time.RadialPickerLayout;
import com.borax12.materialdaterangepicker.time.TimePickerDialog;
import com.nanningzhuanqian.vscreenshot.R;
import com.nanningzhuanqian.vscreenshot.base.BaseActivity;
import com.nanningzhuanqian.vscreenshot.base.Util;
import com.nanningzhuanqian.vscreenshot.common.Constant;
import com.nanningzhuanqian.vscreenshot.widget.NewActionSheetDialog;

import java.util.Calendar;

public class AddTextMomentActivity extends BaseActivity implements View.OnClickListener {
    private EditText edContent;
    private LinearLayout llLocation;
    private LinearLayout llPermission;
    private LinearLayout llPrompt;
    private LinearLayout llQQZone;
    private TextView tvLocation;
    private TextView tvPermission;
    private TextView tvPromot;
    private ImageView imgQQZone;
    private LinearLayout llTime;
    private LinearLayout llName;
    private TextView tvName;
    private TextView tvTime;
    private View divider;
    private boolean syncToQZone = false;
    private boolean isShowingSetting = false;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_wechat_add_text_moment;
    }

    @Override
    protected void initView() {
        initWechatTopBar();
        setWechatTitle("发表文字");
        tvRight = (TextView) findViewById(R.id.tvRight);
        edContent = (EditText) findViewById(R.id.edContent);
        llLocation = (LinearLayout) findViewById(R.id.llLocation);
        llPermission = (LinearLayout) findViewById(R.id.llPermission);
        llPrompt = (LinearLayout) findViewById(R.id.llPrompt);
        llQQZone = (LinearLayout) findViewById(R.id.llQQZone);
        llName = (LinearLayout) findViewById(R.id.llName);
        llTime = (LinearLayout) findViewById(R.id.llTime);
        tvName = (TextView) findViewById(R.id.tvName);
        tvTime = (TextView) findViewById(R.id.tvTime);
        divider = findViewById(R.id.divider);
        tvLocation = (TextView) findViewById(R.id.tvLocation);
        tvPermission = (TextView) findViewById(R.id.tvPermission);
        tvPromot = (TextView) findViewById(R.id.tvPromot);
        imgQQZone = (ImageView) findViewById(R.id.imgQQZone);
    }

    @Override
    protected void initEvent() {
        tvRight.setOnClickListener(this);
        llLocation.setOnClickListener(this);
        llPermission.setOnClickListener(this);
        llPrompt.setOnClickListener(this);
        llQQZone.setOnClickListener(this);
        llTime.setOnClickListener(this);
        llName.setOnClickListener(this);
    }

    @Override
    protected void initData() {
        setQZoneSyncStatus();
    }

    private void setQZoneSyncStatus() {
        if (syncToQZone) {
            imgQQZone.setImageResource(R.mipmap.qq_zone_selected);
        } else {
            imgQQZone.setImageResource(R.mipmap.qq_zone_un_select);
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tvRight:
                // TODO: 2018/9/29 在这里选择发布时间 选择
                showOptionSheetDialog();
                break;
            case R.id.llLocation:
                Intent intent = new Intent(getThis(), WechatLocationEditActivity.class);
                startActivityForResult(intent, Constant.REQUEST_CODE_LOCATION_EDIT);
                break;
            case R.id.llPermission:
                Intent intent1 = new Intent(getThis(), WechatPermissionEditActivity.class);
                startActivityForResult(intent1, Constant.REQUEST_CODE_PERMISSION_EDIT);
                break;
            case R.id.llPrompt:
                Intent intent2 = new Intent(getThis(), WechatPromptEditActivity.class);
                startActivityForResult(intent2, Constant.REQUEST_CODE_PROMPT_EDIT);
                break;
            case R.id.llQQZone:
                syncToQZone = !syncToQZone;
                setQZoneSyncStatus();
                break;
            case R.id.llName:
                Intent intent3 = new Intent(getThis(), WechatPublisherEditActivity.class);
                startActivityForResult(intent3, Constant.REQUEST_CODE_PUBLISHER_EDIT);
                break;
            case R.id.llTime:
                showDateDialog();
                break;
        }
    }

    private void showOptionSheetDialog() {
        NewActionSheetDialog.Builder builder = new NewActionSheetDialog.Builder(AddTextMomentActivity.this);

        builder.setCancelable(false);
        builder.setCancelButtonVisiable(true);
        builder.setCanceledOnTouchOutside(true);
        builder.setTitle(getString(R.string.action_settings));

        builder.addSheetItem(getShowItemContent(), NewActionSheetDialog
                .SheetItemColor.Blue, new NewActionSheetDialog.Builder
                .OnSheetItemClickListener() {
            @Override
            public void onClick(int which) {
                //隐藏时间及发送人
                isShowingSetting = !isShowingSetting;
                updateSettingLayout();
            }
        });

        builder.addSheetItem("发表", NewActionSheetDialog
                .SheetItemColor.Blue, new NewActionSheetDialog.Builder
                .OnSheetItemClickListener() {
            @Override
            public void onClick(int which) {
                //发表

            }
        });
        Dialog dialog = builder.create();
        dialog.show();
    }

    private String getShowItemContent() {
        if (isShowingSetting) {
            return "隐藏时间及发送人";
        } else {
            return "显示时间及发送人";
        }
    }

    private void updateSettingLayout() {
        if (isShowingSetting) {
            llName.setVisibility(View.VISIBLE);
            llTime.setVisibility(View.VISIBLE);
            divider.setVisibility(View.VISIBLE);
        } else {
            llName.setVisibility(View.GONE);
            llTime.setVisibility(View.GONE);
            divider.setVisibility(View.GONE);
        }
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
    private long timeMillis;

    private void showTimeDialog() {
        Calendar now = Calendar.getInstance();
        TimePickerDialog tpd = TimePickerDialog.newInstance(
                new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(RadialPickerLayout dialog, int hourOfDay, int minute, int hourOfDayEnd, int minuteEnd) {
                        hh = String.valueOf(hourOfDay);
                        mm = String.valueOf(minute);
                        updateTime = yyyy + "-" + MM + "-" + dd + " " + hh + ":" + mm + ":" + ss;
                        Log.i(TAG, "hourOfDay = " + hourOfDay + " minute = " + minute);
                        timeMillis = Util.getTimeMillis(updateTime);
                        tvTime.setText(updateTime);
                    }
                },
                now.get(Calendar.HOUR_OF_DAY),
                now.get(Calendar.MINUTE),
                true
        );
        tpd.show(getFragmentManager(), "Timepickerdialog");
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent intent) {
        switch (requestCode) {
            case Constant.REQUEST_CODE_LOCATION_EDIT:
                if (resultCode == Constant.RESULT_CODE_SUCCESS) {
                    String location = intent.getStringExtra(Constant.KEY_LOCATION);
                    tvLocation.setText(location);
                }
                break;
            case Constant.REQUEST_CODE_PERMISSION_EDIT:
                if (resultCode == Constant.RESULT_CODE_SUCCESS) {
                    String permission = intent.getStringExtra(Constant.KEY_PERMISSION);
                    tvPermission.setText(permission);
                }
                break;
            case Constant.REQUEST_CODE_PROMPT_EDIT:
                if (resultCode == Constant.RESULT_CODE_SUCCESS) {
                    String prompt = intent.getStringExtra(Constant.KEY_PROMPT);
                    tvPromot.setText(prompt);
                }
                break;
            case Constant.REQUEST_CODE_PUBLISHER_EDIT:
                if (resultCode == Constant.RESULT_CODE_SUCCESS) {
                    String publisher = intent.getStringExtra(Constant.KEY_PUBLISHER);
                    tvName.setText(publisher);
                }
                break;
            default:
                super.onActivityResult(requestCode, resultCode, intent);
                break;
        }

    }


}
