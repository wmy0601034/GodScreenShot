package com.nanningzhuanqian.vscreenshot.m01_wechat.custom;

import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.nanningzhuanqian.vscreenshot.R;
import com.nanningzhuanqian.vscreenshot.adapter.ChooseConversationAdapter;
import com.nanningzhuanqian.vscreenshot.base.BaseActivity;
import com.nanningzhuanqian.vscreenshot.base.bean.Conversation;
import com.nanningzhuanqian.vscreenshot.base.bean.Conversations;
import com.nanningzhuanqian.vscreenshot.base.util.SPUtils;
import com.nanningzhuanqian.vscreenshot.common.Constant;
import com.nanningzhuanqian.vscreenshot.m01_wechat.wechat.chat.WechatSingeChatActivity;


import java.util.List;

/**
 * 选择微信单聊联系人的界面
 * 展示用户列表
 */
public class WechatChooseSingleChatActivity extends BaseActivity {

    private ListView lvConservation;
    private ChooseConversationAdapter chooseConversationAdapter;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_wechat_choose_single_chat;
    }

    @Override
    protected void initView() {
        initCommonTopBar();
        setCommonTitle("选择对话用户");
        lvConservation = (ListView) findViewById(R.id.lvConservation);
        setCommonRightContent("添加用户", new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getThis(), AddCustomConversationActivity.class);
                startActivityForResult(intent,999);
            }
        });
    }

    @Override
    protected void initEvent() {
        lvConservation.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getThis(),WechatSingeChatActivity.class);
                intent.putExtra("position",position);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void initData() {
        chooseConversationAdapter = new ChooseConversationAdapter(getThis());

        lvConservation.setAdapter(chooseConversationAdapter);
    }

    @Override
    protected void onResume() {
        super.onResume();
        Conversations.getInstance().clear();
        String mobile = (String) SPUtils.get(getThis(), Constant.KEY_MOBILE, "");
//        List<ConversationLite> conversationLites = LitePal.where("pointToUser", mobile).find
//                (ConversationLite.class);
//        List<Conversation> conversations = LitePal.findAll(Conversation.class);
//        Conversations.getInstance().add(conversations);
        chooseConversationAdapter.notifyDataSetChanged();
    }
}
