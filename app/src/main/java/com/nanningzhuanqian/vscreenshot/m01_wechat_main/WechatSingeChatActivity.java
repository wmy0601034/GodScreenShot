package com.nanningzhuanqian.vscreenshot.m01_wechat_main;

import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import com.nanningzhuanqian.vscreenshot.R;
import com.nanningzhuanqian.vscreenshot.base.BaseActivity;
import com.nanningzhuanqian.vscreenshot.widget.ArrowView;

public class WechatSingeChatActivity extends BaseActivity {

    private RecyclerView rcvConversation;
    private ImageButton btnVoice;
    private ImageButton btnFace;
    private ImageButton btnAdd;
    private EditText edContent;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_single_chat;
    }

    @Override
    protected void initView() {
        initWechatTopBar();
        btnRight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showConversationSettingDialog();
            }
        });
        rcvConversation = (RecyclerView) findViewById(R.id.rcvConversation);
        btnVoice = (ImageButton) findViewById(R.id.btnVoice);
        btnFace = (ImageButton) findViewById(R.id.btnFace);
        btnAdd = (ImageButton) findViewById(R.id.btnAdd);
        edContent = (EditText) findViewById(R.id.edContent);
    }

    @Override
    protected void initEvent() {

    }

    @Override
    protected void initData() {

    }

    private void showConversationSettingDialog() {

    }
}
