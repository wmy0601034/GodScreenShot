package com.nanningzhuanqian.vscreenshot.m01_wechat_main;

import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.nanningzhuanqian.vscreenshot.R;
import com.nanningzhuanqian.vscreenshot.adapter.ChooseConversationAdapter;
import com.nanningzhuanqian.vscreenshot.base.BaseActivity;
import com.nanningzhuanqian.vscreenshot.base.util.SPUtils;
import com.nanningzhuanqian.vscreenshot.common.Constant;
import com.nanningzhuanqian.vscreenshot.item.ConversationItem;
import com.nanningzhuanqian.vscreenshot.item.ConversationItems;
import com.nanningzhuanqian.vscreenshot.m02_add_conversation.AddCustomConversationActivity;
import com.nanningzhuanqian.vscreenshot.model.ConversationLite;

import org.litepal.LitePal;

import java.util.List;

/**
 * 选择微信对话用户的界面
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
        ConversationItems.getInstance().clear();
        String mobile = (String) SPUtils.get(getThis(), Constant.KEY_MOBILE, "");
//        List<ConversationLite> conversationLites = LitePal.where("pointToUser", mobile).find
//                (ConversationLite.class);
        List<ConversationLite> conversationLites = LitePal.findAll(ConversationLite.class);
        for (int i = 0; i < conversationLites.size(); i++) {
            ConversationItem item = conversationLites.get(i).convertToConversationItem();
            ConversationItems.getInstance().add(item);
        }
        chooseConversationAdapter.notifyDataSetChanged();
    }
}
