package com.nanningzhuanqian.vscreenshot.m01_wechat.wechat.main.fragment;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

import com.nanningzhuanqian.vscreenshot.R;
import com.nanningzhuanqian.vscreenshot.adapter.ConversationAdapter;
import com.nanningzhuanqian.vscreenshot.base.bean.Conversation;
import com.nanningzhuanqian.vscreenshot.base.bean.Conversations;
import com.nanningzhuanqian.vscreenshot.base.util.DBManager;
import com.nanningzhuanqian.vscreenshot.base.util.SPUtils;
import com.nanningzhuanqian.vscreenshot.common.Constant;
import com.nanningzhuanqian.vscreenshot.m01_wechat.custom.AddCustomConversationActivity;
import com.nanningzhuanqian.vscreenshot.widget.NewActionSheetDialog;


import java.util.List;

public class ConversationListFragment extends Fragment {
    private View rootView;
    private ListView lvConversation;
    private ConversationAdapter conversationAdapter;

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable
            Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_conversation_list, container, false);
        initView();
        initEvent();
        initData();
        return rootView;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onResume() {
        super.onResume();
        initData();
    }

    private void initView() {
        lvConversation = rootView.findViewById(R.id.lvConversation);
        conversationAdapter = new ConversationAdapter(getActivity());
        lvConversation.setAdapter(conversationAdapter);
    }

    private void initEvent() {
        lvConversation.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Conversation conversation = Conversations.getInstance().get(position);
                int type = conversation.getType();
                Log.i(getActivity().getLocalClassName(),"onItemClick "+conversation.toString());
                switch (type) {
                    case Conversation.TYPE_SINGLE_CHAT:

                        break;
                    case Conversation.TYPE_GROUP_CHAT:

                        break;
                    case Conversation.TYPE_WECHAT_SERVICE:

                        break;
                    case Conversation.TYPE_WECHAT_SYSTEM:

                        break;
                    case Conversation.TYPE_WECHAT_SUBCRIBE:

                        break;
                }
            }
        });
        lvConversation.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long
                    id) {
                return false;
            }
        });
    }

    public void initData() {
        Conversations.getInstance().clear();
        List<Conversation> conversations = DBManager.getConversations(getActivity());
        Conversations.getInstance().add(conversations);
        conversationAdapter.notifyDataSetChanged();
    }

    private void showConversationOptionSheetDialog(final int position) {
        NewActionSheetDialog.Builder builder = new NewActionSheetDialog.Builder(getActivity());

        builder.setCancelable(false);
        builder.setCancelButtonVisiable(true);
        builder.setCanceledOnTouchOutside(true);
        builder.setTitle(getString(R.string.action_settings));
        builder.addSheetItem("编辑", NewActionSheetDialog
                .SheetItemColor.Blue, new NewActionSheetDialog.Builder
                .OnSheetItemClickListener() {
            @Override
            public void onClick(int which) {
                //编辑
                Intent intent = new Intent(getActivity(), AddCustomConversationActivity.class);
                startActivity(intent);
            }
        });
        builder.addSheetItem("删除", NewActionSheetDialog
                .SheetItemColor.Blue, new NewActionSheetDialog.Builder
                .OnSheetItemClickListener() {
            @Override
            public void onClick(int which) {
                //删除
                Conversation conversation = Conversations.getInstance().get(position);
                Conversations.getInstance().remove(position);
                conversationAdapter.notifyDataSetChanged();
            }
        });
        Dialog dialog = builder.create();
        dialog.show();
    }

}
