package com.nanningzhuanqian.vscreenshot.m01_wechat.custom;

import android.app.Dialog;
import android.content.Intent;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
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
import com.nanningzhuanqian.vscreenshot.item.WechatNewFriendItem;
import com.nanningzhuanqian.vscreenshot.m00_base.LocalAvatarSelectActivity;
import com.nanningzhuanqian.vscreenshot.model.RandomManager;
import com.nanningzhuanqian.vscreenshot.model.WechatNewFriendLite;
import com.nanningzhuanqian.vscreenshot.widget.NewActionSheetDialog;
import com.suke.widget.SwitchButton;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * 微信自定义好友申请界面
 */
public class WechatCustomNewFriendActivity extends BaseActivity implements View.OnClickListener {

    private TextView tvSubmit;
    private LinearLayout llAvatar;
    private ImageView imgIcon;
    private EditText edName;
    private EditText edContent;
    private SwitchButton switchRead;
    private SwitchButton switchInvaild;
    private SwitchButton switchAdded;
    private LinearLayout llTime;
    private TextView tvTime;
    private Button btnRandom;
    private Button btnSubmit;

    private boolean isRead;
    private boolean isAdded;
    private boolean isInvaild;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_wechat_add_new_friend;
    }

    @Override
    protected void initView() {
        initCommonTopBar();
        tvSubmit = (TextView) findViewById(R.id.tvSubmit);
        llAvatar = (LinearLayout)findViewById(R.id.llAvatar);
        imgIcon = (ImageView)findViewById(R.id.imgIcon);
        edName = (EditText) findViewById(R.id.edName);
        edContent = (EditText) findViewById(R.id.edContent);
        llTime = (LinearLayout)findViewById(R.id.llTime);
        switchRead = (SwitchButton)findViewById(R.id.switchRead);
        switchInvaild = (SwitchButton)findViewById(R.id.switchInvaild);
        switchAdded = (SwitchButton)findViewById(R.id.switchAdded);
        btnRandom = (Button)findViewById(R.id.btnRandom);
        tvTime = (TextView)findViewById(R.id.tvTime);
        btnSubmit = (Button)findViewById(R.id.btnSubmit);
    }

    @Override
    protected void initEvent() {
        tvSubmit.setOnClickListener(this);
        tvSubmit.setVisibility(View.VISIBLE);
        llAvatar.setOnClickListener(this);
        llTime.setOnClickListener(this);
        btnRandom.setOnClickListener(this);
        btnSubmit.setOnClickListener(this);
        switchRead.setOnCheckedChangeListener(new SwitchButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(SwitchButton view, boolean isChecked) {
                isRead = isChecked;
            }
        });
        switchInvaild.setOnCheckedChangeListener(new SwitchButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(SwitchButton view, boolean isChecked) {
                isInvaild = isChecked;
            }
        });
        switchAdded.setOnCheckedChangeListener(new SwitchButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(SwitchButton view, boolean isChecked) {
                isAdded = isChecked;
            }
        });
    }

    @Override
    protected void initData() {
       setCommonTitle("添加自定义新的朋友");
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tvSubmit:
                save();
                break;
            case R.id.llAvatar:
                showAvatarSheetDialog();
                break;
            case R.id.llTime:
                showDateDialog();
                break;
            case R.id.btnSubmit:
                save();
                break;
            case R.id.btnRandom:
                randomCreate();
                break;
        }
    }

    private int imgRes;
    private String name;
    private String content;
    private long timeMillis;
    private void save() {
        name = edName.getText().toString();
        if(TextUtils.isEmpty(name)){
            toast("请输入昵称");
            return;
        }
        content = edContent.getText().toString();
        if(TextUtils.isEmpty(content)){
            toast("请输入内容");
            return;
        }
        String time = tvTime.getText().toString();
        if(TextUtils.isEmpty(time)){
            toast("请选择时间");
            return;
        }else{
            timeMillis = getTimeMillis(time);
            Log.i(TAG,"timeMillis = "+timeMillis);
        }
        WechatNewFriendItem item = new WechatNewFriendItem();
        item.setName(name);
        item.setContent(content);
        item.setUpdateTime(timeMillis);
        item.setImgRes(imgRes);
        item.setAdded(isAdded);
        item.setInvaild(isInvaild);
        item.setRead(isRead);

        //保存到本地
//        WechatNewFriendLite conversationLite = item.converToWechatNewFriendLite();
//        conversationLite.save();

        toast("添加成功");
        setResult(999);
        finish();
    }

    private void showAvatarSheetDialog() {
        NewActionSheetDialog.Builder builder = new NewActionSheetDialog.Builder(WechatCustomNewFriendActivity.this);

        builder.setCancelable(false);
        builder.setCancelButtonVisiable(true);
        builder.setCanceledOnTouchOutside(true);
        builder.setTitle("选择头像");
        builder.addSheetItem("拍照", NewActionSheetDialog
                .SheetItemColor.Blue, new NewActionSheetDialog.Builder
                .OnSheetItemClickListener() {
            @Override
            public void onClick(int which) {
                //拍照
                toast("暂未开放");
            }
        });
        builder.addSheetItem("相册", NewActionSheetDialog
                .SheetItemColor.Blue, new NewActionSheetDialog.Builder
                .OnSheetItemClickListener() {
            @Override
            public void onClick(int which) {
                //随机添加1个对话
                toast("暂未开放");
            }
        });
        builder.addSheetItem("头像库", NewActionSheetDialog
                .SheetItemColor.Blue, new NewActionSheetDialog.Builder
                .OnSheetItemClickListener() {
            @Override
            public void onClick(int which) {
                Intent intent = new Intent(getThis(),LocalAvatarSelectActivity.class);
                startActivityForResult(intent,999);
            }
        });
        builder.addSheetItem("在线头像库", NewActionSheetDialog
                .SheetItemColor.Blue, new NewActionSheetDialog.Builder
                .OnSheetItemClickListener() {
            @Override
            public void onClick(int which) {
                //页面设置
                toast("暂未开放");
            }
        });


        Dialog dialog = builder.create();
        dialog.show();
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent intent) {
        super.onActivityResult(requestCode, resultCode, intent);
        if(selectAvatarFinish(requestCode,resultCode)){
            imgRes = intent.getIntExtra("imgRes",R.mipmap.app_images_defaultface);
            imgIcon.setImageResource(imgRes);
        }
    }

    private boolean selectAvatarFinish(int requestCode, int resultCode) {
        return requestCode==999&&resultCode ==999;
    }

    private long getTimeMillis(String time){
        SimpleDateFormat sdf = new SimpleDateFormat("yy-MM-dd HH:mm:ss");
        Date d;
        try{
            d = sdf.parse(time);
            long l = d.getTime();
            return l;
        } catch(ParseException e){
            e.printStackTrace();
        }
        return System.currentTimeMillis();
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
                        Log.i(TAG,"year = "+year+" monthOfYear = "+monthOfYear+" dayOfMonth = "+dayOfMonth);
                        yyyy =String.valueOf(year);
                        MM = String.valueOf(monthOfYear+1);
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
    private void showTimeDialog(){
        Calendar now = Calendar.getInstance();
        TimePickerDialog tpd = TimePickerDialog.newInstance(
                new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(RadialPickerLayout dialog, int hourOfDay, int minute, int hourOfDayEnd, int minuteEnd) {
                        hh = String.valueOf(hourOfDay);
                        mm = String.valueOf(minute);
                        updateTime = yyyy+"-"+MM+"-"+dd +" "+hh+":"+mm+":"+ss;
                        tvTime.setText(updateTime);
                        Log.i(TAG,"hourOfDay = "+hourOfDay+" minute = "+minute);
                    }
                },
                now.get(Calendar.HOUR_OF_DAY),
                now.get(Calendar.MINUTE),
                true
        );
        tpd.show(getFragmentManager(), "Timepickerdialog");
    }

    private void randomCreate(){
        imgRes = RandomManager.getAvatarRes();
        imgIcon.setImageResource(imgRes);
        name = RandomManager.getInstance().getRandomName();
        content = RandomManager.getInstance().getRandomContent();
        timeMillis = RandomManager.getInstance().getRandomTime();
        String time = Util.stampToDate(timeMillis);
        tvTime.setText(time);
        edName.setText(name);
        edContent.setText(content);
        isAdded = RandomManager.getBoolean();
        isRead = RandomManager.getBoolean();
        isInvaild = RandomManager.getBoolean();
        switchAdded.setChecked(isAdded);
        switchRead.setChecked(isRead);
        switchInvaild.setChecked(isInvaild);
    }
}
