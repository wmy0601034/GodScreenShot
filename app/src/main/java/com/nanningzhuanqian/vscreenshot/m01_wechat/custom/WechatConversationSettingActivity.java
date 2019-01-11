package com.nanningzhuanqian.vscreenshot.m01_wechat.custom;

import android.app.Dialog;
import android.content.Intent;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.nanningzhuanqian.vscreenshot.R;
import com.nanningzhuanqian.vscreenshot.base.BaseActivity;
import com.nanningzhuanqian.vscreenshot.common.Constant;
import com.nanningzhuanqian.vscreenshot.m00_base.LocalAvatarSelectActivity;
import com.nanningzhuanqian.vscreenshot.widget.NewActionSheetDialog;
import com.suke.widget.SwitchButton;

/**
 * Created by lenovo on 2018/9/28.
 */

public class WechatConversationSettingActivity extends BaseActivity implements View.OnClickListener {

    private SwitchButton tglAutoSetTitle;
    private EditText edTitle;
    private SwitchButton tglVoiceMode;
    private SwitchButton tglIgnore;
    private SwitchButton tglUseCurrentBg;
    private SwitchButton tglUseGlobalBg;
    private LinearLayout llCurrentBg;
    private ImageView imgCurrentBg;
    private LinearLayout llGlobalBg;
    private ImageView imgGlobalBg;
    private LinearLayout llTextSize;
    private TextView tvTextSize;
    private TextView tvSave;
    @Override
    protected int getLayoutId() {
        return R.layout.activity_wechat_conversation_setting;
    }

    @Override
    protected void initView() {
        initCommonTopBar();
        setCommonTitle("对话设置");
        setCommonRightContent("保存", new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                save();
            }
        });
        tglAutoSetTitle = (SwitchButton)findViewById(R.id.tglAutoSetTitle);
        edTitle = (EditText) findViewById(R.id.edTitle);
        tglVoiceMode = (SwitchButton)findViewById(R.id.tglVoiceMode);
        tglIgnore = (SwitchButton)findViewById(R.id.tglIgnore);
        tglUseCurrentBg = (SwitchButton)findViewById(R.id.tglUseCurrentBg);
        tglUseGlobalBg = (SwitchButton)findViewById(R.id.tglUseGlobalBg);
        llCurrentBg = (LinearLayout) findViewById(R.id.llCurrentBg);
        imgCurrentBg = (ImageView) findViewById(R.id.imgCurrentBg);
        llGlobalBg = (LinearLayout) findViewById(R.id.llGlobalBg);
        imgGlobalBg = (ImageView) findViewById(R.id.imgGlobalBg);
        llTextSize = (LinearLayout) findViewById(R.id.llTextSize);
        tvTextSize = (TextView) findViewById(R.id.tvTextSize);
        tvSave = (TextView) findViewById(R.id.tvSave);
    }

    private void save() {

    }

    @Override
    protected void initEvent() {
        llCurrentBg.setOnClickListener(this);
        llGlobalBg.setOnClickListener(this);
        llTextSize.setOnClickListener(this);
    }

    @Override
    protected void initData() {

    }

    private boolean isCurrent = true;
    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.llCurrentBg:
                showBgPickSheetDialog();
                break;
            case R.id.llGlobalBg:
                showBgPickSheetDialog();
                break;
            case R.id.llTextSize:

                break;
        }
    }

    private void showBgPickSheetDialog() {
        NewActionSheetDialog.Builder builder = new NewActionSheetDialog.Builder(getThis());

        builder.setCancelable(false);
        builder.setCancelButtonVisiable(true);
        builder.setCanceledOnTouchOutside(true);
        if(isCurrent){
            builder.setTitle("选择当前聊天背景");
        }else {
            builder.setTitle("选择全局聊天背景");
        }
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
                intent.putExtra(Constant.INTENT_KEY_TYPE,Constant.INTENT_VALUE_PROFILE_AVATAR);
                startActivity(intent);
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
}
