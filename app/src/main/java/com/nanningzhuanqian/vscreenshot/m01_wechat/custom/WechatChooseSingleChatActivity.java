package com.nanningzhuanqian.vscreenshot.m01_wechat.custom;

import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.nanningzhuanqian.vscreenshot.R;
import com.nanningzhuanqian.vscreenshot.adapter.ChooseContactAdapter;
import com.nanningzhuanqian.vscreenshot.base.BaseActivity;
import com.nanningzhuanqian.vscreenshot.base.bean.Contact;
import com.nanningzhuanqian.vscreenshot.base.bean.Contacts;
import com.nanningzhuanqian.vscreenshot.base.bean.Conversation;
import com.nanningzhuanqian.vscreenshot.base.bean.Conversations;
import com.nanningzhuanqian.vscreenshot.base.util.DBManager;
import com.nanningzhuanqian.vscreenshot.base.util.SPUtils;
import com.nanningzhuanqian.vscreenshot.common.Constant;
import com.nanningzhuanqian.vscreenshot.m01_wechat.wechat.chat.WechatSingleChatActivity;


import java.util.List;

/**
 * 选择微信单聊联系人的界面
 * 展示用户列表
 */
public class WechatChooseSingleChatActivity extends BaseActivity {

    private ListView lvContacts;
    private ChooseContactAdapter contactAdapter;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_wechat_choose_single_chat;
    }

    @Override
    protected void initView() {
        initCommonTopBar();
        setCommonTitle("选择对话用户");
        lvContacts = (ListView) findViewById(R.id.lvContacts);
        setCommonRightContent("添加用户", new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getThis(), AddCustomContactActivityNew.class);
                startActivityForResult(intent,Constant.REQUEST_CODE_ADD_CONTACT);
            }
        });
    }

    @Override
    protected void initEvent() {
        lvContacts.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getThis(),WechatSingleChatActivity.class);
                intent.putExtra("position",position);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void initData() {
        contactAdapter = new ChooseContactAdapter(getThis());
        lvContacts.setAdapter(contactAdapter);
    }

    @Override
    protected void onResume() {
        super.onResume();
        List<Contact> contacts = DBManager.getContacts(getApplicationContext());
        contactAdapter.setContacts(contacts);
        contactAdapter.notifyDataSetChanged();
    }
}
