package com.nanningzhuanqian.vscreenshot.m01_wechat_main;

import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;

import com.nanningzhuanqian.vscreenshot.R;
import com.nanningzhuanqian.vscreenshot.adapter.WechatChatAdapter;
import com.nanningzhuanqian.vscreenshot.adapter.WechatChatItemAdapter;
import com.nanningzhuanqian.vscreenshot.base.BaseActivity;
import com.nanningzhuanqian.vscreenshot.base.util.SPUtils;
import com.nanningzhuanqian.vscreenshot.common.Constant;
import com.nanningzhuanqian.vscreenshot.item.WechatChatItem;
import com.nanningzhuanqian.vscreenshot.item.WechatChatItems;
import com.nanningzhuanqian.vscreenshot.widget.ArrowView;
import com.nanningzhuanqian.vscreenshot.widget.CustomListview;

import java.util.List;

public class WechatSingeChatActivity extends BaseActivity implements View.OnClickListener {

    private RecyclerView rcvConversation;
//    private CustomListview lvChat;
    private WechatChatAdapter wechatChatAdapter;
//    private WechatChatItemAdapter wechatChatItemAdapter;
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
        rcvConversation.setLayoutManager(new LinearLayoutManager(getThis()));
//        lvChat = (CustomListview)findViewById(R.id.lvChat);
        btnVoice = (ImageButton) findViewById(R.id.btnVoice);
        btnFace = (ImageButton) findViewById(R.id.btnFace);
        btnAdd = (ImageButton) findViewById(R.id.btnAdd);
        edContent = (EditText) findViewById(R.id.edContent);
        wechatChatAdapter = new WechatChatAdapter(getThis());
//        wechatChatItemAdapter = new WechatChatItemAdapter(getThis());
//        lvChat.setAdapter(wechatChatItemAdapter);
        rcvConversation.setAdapter(wechatChatAdapter);
    }

    @Override
    protected void initEvent() {
        btnVoice.setOnClickListener(this);
        btnFace.setOnClickListener(this);
        btnAdd.setOnClickListener(this);
    }

    @Override
    protected void initData() {
        String mobile = (String) SPUtils.get(getThis(), Constant.KEY_MOBILE,"");
        WechatChatItem item1 = new WechatChatItem(WechatChatAdapter.TYPE_SELF,"吴MoonMoon",R.mipmap
                .app_images_role_10000,"呵呵呵呵呵呵\n呵呵呵呵呵呵呵呵呵呵呵呵呵呵\n呵呵呵呵呵呵呵呵呵呵呵呵呵呵呵呵呵呵呵呵呵呵呵\n呵呵呵呵呵呵呵呵呵呵呵呵呵呵\n呵呵呵呵呵呵呵呵呵呵呵呵呵呵呵呵呵呵呵呵呵呵呵呵呵呵呵呵呵1", mobile);
        WechatChatItem item2 = new WechatChatItem(WechatChatAdapter.TYPE_OTHER,"吴MoonMoon",R.mipmap
                .app_images_role_10001,
                "呵呵呵呵呵呵呵呵呵呵呵呵呵呵呵呵呵呵呵呵呵呵呵呵呵呵\n呵呵呵呵呵呵呵呵呵呵呵呵呵呵呵呵呵呵呵呵呵呵呵呵呵呵\n呵呵呵呵呵呵呵呵呵呵呵呵呵呵呵呵呵呵呵呵呵呵呵呵呵呵呵呵呵呵呵呵呵呵2", mobile);
        WechatChatItem item3 = new WechatChatItem(WechatChatAdapter.TYPE_SELF,"吴MoonMoon",R.mipmap
                .app_images_role_10000,
                "呵呵呵呵呵呵呵呵呵呵呵呵呵呵呵呵\n呵呵呵呵呵呵呵呵呵呵呵呵呵呵呵\n呵呵呵呵呵呵呵呵呵呵呵呵呵呵呵呵呵呵呵呵呵呵呵呵\n呵呵呵呵呵呵呵呵呵呵呵呵呵呵呵呵呵呵呵呵呵呵呵呵呵呵呵呵呵呵呵3", mobile);
        WechatChatItem item4 = new WechatChatItem(WechatChatAdapter.TYPE_OTHER,"吴MoonMoon",R.mipmap
                .app_images_role_10001,
                "呵呵\n呵呵\n呵呵\n呵呵\n呵呵\n呵呵\n呵呵", mobile);
        WechatChatItem item5 = new WechatChatItem(WechatChatAdapter.TYPE_SELF,"吴MoonMoon",R.mipmap
                .app_images_role_10000,
                "呵呵\n呵呵\n呵呵\n呵呵\n呵呵\n呵呵\n呵呵", mobile);
        WechatChatItem item6 = new WechatChatItem(WechatChatAdapter.TYPE_OTHER,"吴MoonMoon",R.mipmap
                .app_images_role_10001,
                "呵呵\n呵呵\n呵呵呵呵呵呵呵呵呵呵呵呵呵呵呵呵\n呵呵\n呵呵\n呵呵\n呵呵", mobile);
        WechatChatItems.getInstance().add(item1);
        WechatChatItems.getInstance().add(item2);
        WechatChatItems.getInstance().add(item3);
        WechatChatItems.getInstance().add(item4);
        WechatChatItems.getInstance().add(item5);
        WechatChatItems.getInstance().add(item6);
//        wechatChatItemAdapter.notifyDataSetChanged();
        wechatChatAdapter.notifyDataSetChanged();
        rcvConversation.scrollToPosition(WechatChatItems.getInstance().size()-1);
//        lvChat.setSelection(WechatChatItems.getInstance().size());
    }

    private void showConversationSettingDialog() {

    }

    @Override
    public void onClick(View v) {

    }
}
