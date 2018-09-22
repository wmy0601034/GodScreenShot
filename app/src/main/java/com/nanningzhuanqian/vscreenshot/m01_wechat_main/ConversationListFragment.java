package com.nanningzhuanqian.vscreenshot.m01_wechat_main;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.AdapterContextMenuInfo;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.nanningzhuanqian.vscreenshot.MainActivity;
import com.nanningzhuanqian.vscreenshot.R;
import com.nanningzhuanqian.vscreenshot.adapter.ConversationAdapter;
import com.nanningzhuanqian.vscreenshot.base.util.SPUtils;
import com.nanningzhuanqian.vscreenshot.common.Constant;
import com.nanningzhuanqian.vscreenshot.item.ConversationItem;
import com.nanningzhuanqian.vscreenshot.item.ConversationItems;
import com.nanningzhuanqian.vscreenshot.m02_add_conversation.AddCustomConversationActivity;
import com.nanningzhuanqian.vscreenshot.model.ContractBmob;
import com.nanningzhuanqian.vscreenshot.model.ContractLite;
import com.nanningzhuanqian.vscreenshot.model.ConversationLite;
import com.nanningzhuanqian.vscreenshot.widget.NewActionSheetDialog;

import org.litepal.LitePal;

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
        conversationAdapter.notifyDataSetChanged();
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
                showConversationOptionSheetDialog(position);
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

    private void initData() {
        ConversationItems.getInstance().clear();
        String mobile = (String) SPUtils.get(getActivity(), Constant.KEY_MOBILE, "");
//        List<ConversationLite> conversationLites = LitePal.where("pointToUser", mobile).find
//                (ConversationLite.class);
        List<ConversationLite> conversationLites = LitePal.findAll(ConversationLite.class);
        for (int i = 0; i < conversationLites.size(); i++) {
            ConversationItem item = conversationLites.get(i).convertToConversationItem();
            ConversationItems.getInstance().add(item);
        }
        ConversationItems.getInstance().sort();
        conversationAdapter.notifyDataSetChanged();
    }

    public void notifyDataSetChanged(){
        conversationAdapter.notifyDataSetChanged();
    }

    private void showConversationOptionSheetDialog(final int position){
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
                Intent intent = new Intent(getActivity(),AddCustomConversationActivity.class);
                startActivity(intent);
            }
        });
        builder.addSheetItem("删除", NewActionSheetDialog
                .SheetItemColor.Blue, new NewActionSheetDialog.Builder
                .OnSheetItemClickListener() {
            @Override
            public void onClick(int which) {
                //删除
                ConversationItem conversationItem =ConversationItems.getInstance().get(position);
                ConversationItems.getInstance().remove(position);
                LitePal.deleteAll(ConversationLite.class,"name=?and content = ?",conversationItem.getName(),
                        conversationItem.getContent());
                conversationAdapter.notifyDataSetChanged();
            }
        });
        Dialog dialog = builder.create();
        dialog.show();
    }

}
