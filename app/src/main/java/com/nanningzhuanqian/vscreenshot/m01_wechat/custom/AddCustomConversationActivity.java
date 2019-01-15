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
import com.nanningzhuanqian.vscreenshot.base.bean.Conversation;
import com.nanningzhuanqian.vscreenshot.base.bean.Conversations;
import com.nanningzhuanqian.vscreenshot.base.net.CallbackListener;
import com.nanningzhuanqian.vscreenshot.base.net.HttpUtil;
import com.nanningzhuanqian.vscreenshot.base.util.SPUtils;
import com.nanningzhuanqian.vscreenshot.common.Constant;
import com.nanningzhuanqian.vscreenshot.m00_base.LocalAvatarSelectActivity;
import com.nanningzhuanqian.vscreenshot.widget.NewActionSheetDialog;
import com.suke.widget.SwitchButton;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * 添加自定义对话界面
 * Created by WMY on 2018/9/14.
 */

public class AddCustomConversationActivity extends BaseActivity implements View.OnClickListener {

    private TextView tvBack;
    private TextView tvTitle;
    private TextView tvSubmit;
    private LinearLayout llType;
    private LinearLayout llAvatar;
    private ImageView imgIcon;
    private EditText edName;
    private EditText edContent;
    private SwitchButton switchPublic;
    private SwitchButton switchIgnore;
    private SwitchButton switchRedIndicator;
    private EditText edBadge;
    private LinearLayout llTime;
    private TextView tvTime;
    private Button btnRandom;
    private Button btnSubmit;
    private TextView tvType;
    private int type = Conversation.TYPE_SINGLE_CHAT;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_add_conversation;
    }

    protected void initView() {
        tvBack = (TextView) findViewById(R.id.tvBack);
        tvTitle = (TextView) findViewById(R.id.tvTitle);
        tvSubmit = (TextView) findViewById(R.id.tvSubmit);
        llType = (LinearLayout) findViewById(R.id.llType);
        llAvatar = (LinearLayout) findViewById(R.id.llAvatar);
        imgIcon = (ImageView) findViewById(R.id.imgIcon);
        edName = (EditText) findViewById(R.id.edName);
        edContent = (EditText) findViewById(R.id.edContent);
        switchPublic = (SwitchButton) findViewById(R.id.switchPublic);
        switchIgnore = (SwitchButton) findViewById(R.id.switchIgnore);
        switchRedIndicator = (SwitchButton) findViewById(R.id.switchRedIndicator);
        edBadge = (EditText) findViewById(R.id.edBadge);
        llTime = (LinearLayout) findViewById(R.id.llTime);
        tvTime = (TextView) findViewById(R.id.tvTime);
        btnRandom = (Button) findViewById(R.id.btnRandom);
        btnSubmit = (Button) findViewById(R.id.btnSubmit);
    }

    protected void initEvent() {
        tvBack.setOnClickListener(this);
        tvSubmit.setOnClickListener(this);
        llType.setOnClickListener(this);
        llAvatar.setOnClickListener(this);
        llTime.setOnClickListener(this);
        btnRandom.setOnClickListener(this);
        btnSubmit.setOnClickListener(this);
        switchPublic.setOnCheckedChangeListener(new SwitchButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(SwitchButton view, boolean isChecked) {
                isPublic = isChecked;
            }
        });
        switchIgnore.setOnCheckedChangeListener(new SwitchButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(SwitchButton view, boolean isChecked) {
                isIgnore = isChecked;
            }
        });
        switchRedIndicator.setOnCheckedChangeListener(new SwitchButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(SwitchButton view, boolean isChecked) {
                isBadge = isChecked;
            }
        });
    }

    protected void initData() {
        tvTitle.setText("添加自定义对话");
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tvBack:
                finish();
                break;
            case R.id.tvSubmit:
                save();
                break;
            case R.id.llType:
                showTypeSelectionDialog();
                break;
            case R.id.llAvatar:
                showAvatarSheetDialog();
                break;
            case R.id.llTime:
                showDateDialog();
                break;
            case R.id.btnRandom:
                randomCreate();
                break;
            case R.id.btnSubmit:
                save();
                break;
        }
    }

    private void showTypeSelectionDialog(){
        NewActionSheetDialog.Builder builder = new NewActionSheetDialog.Builder(AddCustomConversationActivity.this);
        builder.setCancelable(false);
        builder.setCancelButtonVisiable(true);
        builder.setCanceledOnTouchOutside(true);
        builder.addSheetItem("单人聊天", NewActionSheetDialog.SheetItemColor.Blue, new NewActionSheetDialog.Builder.OnSheetItemClickListener() {
            @Override
            public void onClick(int which) {
                //选择单人聊天
                type = Conversation.TYPE_SINGLE_CHAT;
                tvType.setText("单人聊天");
            }
        });
        builder.addSheetItem("群聊", NewActionSheetDialog.SheetItemColor.Blue, new NewActionSheetDialog.Builder.OnSheetItemClickListener() {
            @Override
            public void onClick(int which) {
                //选择群聊
                type = Conversation.TYPE_GROUP_CHAT;
                tvType.setText("群聊");
            }
        });
        builder.addSheetItem("服务号", NewActionSheetDialog.SheetItemColor.Blue, new NewActionSheetDialog.Builder.OnSheetItemClickListener() {
            @Override
            public void onClick(int which) {
                //服务号
                type = Conversation.TYPE_WECHAT_SERVICE;
                tvType.setText("服务号");
            }
        });
        builder.addSheetItem("微信系统功能", NewActionSheetDialog.SheetItemColor.Blue, new NewActionSheetDialog.Builder.OnSheetItemClickListener() {
            @Override
            public void onClick(int which) {
                //微信系统功能
                type = Conversation.TYPE_WECHAT_SYSTEM;
                tvType.setText("微信系统功能");

            }
        });
    }

    private void showAvatarSheetDialog() {
        NewActionSheetDialog.Builder builder = new NewActionSheetDialog.Builder(AddCustomConversationActivity.this);

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
                Intent intent = new Intent(AddCustomConversationActivity.this,LocalAvatarSelectActivity.class);
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

    private void randomCreate() {

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

    private int imgRes;
    private String name;
    private String content;
    private boolean isPublic;
    private boolean isIgnore;
    private boolean isBadge;
    private int badge;
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
        if(!isBadge){
            String number = edBadge.getText().toString();
            if(TextUtils.isEmpty(number)){
                toast("请输入角标数字");
                return;
            }else{
                badge = Integer.valueOf(number);
            }
        }
        String time = tvTime.getText().toString();
        if(TextUtils.isEmpty(time)){
            toast("请选择时间");
            return;
        }else{
            timeMillis = getTimeMillis(time);
            Log.i(TAG,"timeMillis = "+timeMillis);
        }
        Conversation item = new Conversation();
        item.setBadgeCount(badge);
        item.setName(name);
        item.setDisplayContent(content);
        item.setUpdateTime(timeMillis);
        item.setIconRes(imgRes);
        item.setIgnore(isIgnore);
//        item.setBadge(isBadge);
//        item.setPublic(isPublic);
        String mobile = (String) SPUtils.get(getThis(), Constant.KEY_MOBILE,"");
//        item.setPointToUser(mobile);
        Conversations.getInstance().addFirst(item);

        //保存到本地
//        item.save();


        toast("添加成功");
        setResult(999);
        finish();
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


}
