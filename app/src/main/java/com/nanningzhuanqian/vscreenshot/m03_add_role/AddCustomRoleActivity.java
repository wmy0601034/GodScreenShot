package com.nanningzhuanqian.vscreenshot.m03_add_role;

import android.app.Dialog;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
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
import com.linchaolong.android.imagepicker.ImagePicker;
import com.nanningzhuanqian.vscreenshot.R;
import com.nanningzhuanqian.vscreenshot.adapter.ContractAdapter;
import com.nanningzhuanqian.vscreenshot.base.BaseActivity;
import com.nanningzhuanqian.vscreenshot.base.net.CallbackListener;
import com.nanningzhuanqian.vscreenshot.base.net.HttpUtil;
import com.nanningzhuanqian.vscreenshot.base.util.SPUtils;
import com.nanningzhuanqian.vscreenshot.common.Constant;
import com.nanningzhuanqian.vscreenshot.item.ContractItem;
import com.nanningzhuanqian.vscreenshot.item.ContractItems;
import com.nanningzhuanqian.vscreenshot.m02_add_conversation.AddCustomConversationActivity;
import com.nanningzhuanqian.vscreenshot.m02_add_conversation.LocalAvatarSelectActivity;
import com.nanningzhuanqian.vscreenshot.model.ContractBmob;
import com.nanningzhuanqian.vscreenshot.model.ContractLite;
import com.nanningzhuanqian.vscreenshot.widget.NewActionSheetDialog;
import com.suke.widget.SwitchButton;

import org.litepal.tablemanager.Connector;

import java.util.Calendar;

public class AddCustomRoleActivity extends BaseActivity implements View.OnClickListener{
    private TextView tvBack;
    private TextView tvTitle;
    private TextView tvSubmit;
    private LinearLayout llAvatar;
    private ImageView imgIcon;
    private EditText edName;
    private EditText edRealName;
    private EditText edWechatAccount;
    private EditText edAliPayAccount;
    private EditText edMark;
    private LinearLayout llLevel;
    private TextView tvLevel;
    private Button btnRandom;
    private Button btnSubmit;

    private String avatarType = "";
    private Uri avatarUri;


    @Override
    protected int getLayoutId() {
        return R.layout.activity_add_custom_role;
    }

    protected void initView() {
        tvBack = (TextView) findViewById(R.id.tvBack);
        tvTitle = (TextView) findViewById(R.id.tvTitle);
        tvSubmit = (TextView) findViewById(R.id.tvSubmit);
        llAvatar = (LinearLayout) findViewById(R.id.llAvatar);
        imgIcon = (ImageView) findViewById(R.id.imgIcon);
        edName = (EditText) findViewById(R.id.edName);
        edRealName = (EditText) findViewById(R.id.edRealName);
        edWechatAccount = (EditText) findViewById(R.id.edWechatAccount);
        edAliPayAccount = (EditText) findViewById(R.id.edAliPayAccount);
        edMark = (EditText) findViewById(R.id.edMark);
        llLevel = (LinearLayout) findViewById(R.id.llAliPayLevel);
        tvLevel = (TextView) findViewById(R.id.tvAliPayLevel);
        btnRandom = (Button) findViewById(R.id.btnRandom);
        btnSubmit = (Button) findViewById(R.id.btnSubmit);
    }

    protected void initEvent() {
        tvBack.setOnClickListener(this);
        tvSubmit.setOnClickListener(this);
        llAvatar.setOnClickListener(this);
        llLevel.setOnClickListener(this);
        btnRandom.setOnClickListener(this);
        btnSubmit.setOnClickListener(this);
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
            case R.id.llAvatar:
                showAvatarSheetDialog();
                break;
            case R.id.llAliPayLevel:
                showAliPayLevelDialog();
                break;
            case R.id.btnRandom:
                randomCreate();
                break;
            case R.id.btnSubmit:
                save();
                break;
        }
    }

    ImagePicker imagePicker = new ImagePicker();
    private void showAvatarSheetDialog() {
        NewActionSheetDialog.Builder builder = new NewActionSheetDialog.Builder(AddCustomRoleActivity.this);

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
                //相册
                imagePicker.setTitle("设置头像");
                // 设置是否裁剪图片
                imagePicker.setCropImage(true);
                imagePicker.startChooser(getThis(), new ImagePicker.Callback() {
                    // 选择图片回调
                    @Override public void onPickImage(Uri imageUri) {

                    }
                    // 裁剪图片回调
                    @Override public void onCropImage(Uri imageUri) {
                        avatarType = Constant.VALUE_PIC_LOCAL;
                        avatarUri = imageUri;
                        imgIcon.setImageURI(imageUri);
                    }
                });
            }
        });
        builder.addSheetItem("头像库", NewActionSheetDialog
                .SheetItemColor.Blue, new NewActionSheetDialog.Builder
                .OnSheetItemClickListener() {
            @Override
            public void onClick(int which) {
                Intent intent = new Intent(AddCustomRoleActivity.this,LocalAvatarSelectActivity.class);
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

    private void showAliPayLevelDialog(){

    }

    private void randomCreate() {

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent intent) {
        super.onActivityResult(requestCode, resultCode, intent);
        if(selectAvatarFinish(requestCode,resultCode)){
            imgRes = intent.getIntExtra("imgRes",R.mipmap.app_images_defaultface);
            avatarType =  Constant.VALUE_PIC_RES;
            imgIcon.setImageResource(imgRes);
        }else{
            imagePicker.onActivityResult(this, requestCode, resultCode, intent);
        }
    }

    private boolean selectAvatarFinish(int requestCode, int resultCode) {
        return requestCode==999&&resultCode ==999;
    }

    private int imgRes;
    private String name;
    private String content;
    private int badge;
    private long timeMillis;

    private void save() {
        name = edName.getText().toString();
        if(TextUtils.isEmpty(name)){
            toast("请输入昵称");
            return;
        }
        ContractItem item = new ContractItem();
        item.setName(name);
        item.setImgRes(imgRes);
        item.setType(ContractAdapter.ITEM_CONTRACT_TYPE);
        String mobile = (String) SPUtils.get(getThis(), Constant.KEY_MOBILE,"");
        item.setPointToUser(mobile);
        item.setImgType(avatarType);
        item.setAvatarUri(avatarUri);
        ContractItems.getInstance().addFirst(item);
        //保存到本地
        ContractLite contractLite = item.convertToLite();
        contractLite.save();

        //保存到服务器
        ContractBmob contractBmob = item.convertToBmob();
        HttpUtil.getInstance().saveContract(contractBmob, new CallbackListener() {
            @Override
            public void onSuccess() {

            }

            @Override
            public void onGetSuccess(Object o) {

            }

            @Override
            public void onFailure(String message) {

            }
        });
        toast("添加成功");
        setResult(999);
        finish();
    }
}
