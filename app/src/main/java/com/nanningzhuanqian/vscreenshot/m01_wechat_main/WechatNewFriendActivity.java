package com.nanningzhuanqian.vscreenshot.m01_wechat_main;

import android.app.Dialog;
import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.nanningzhuanqian.vscreenshot.WechatMainActivity;
import com.nanningzhuanqian.vscreenshot.R;
import com.nanningzhuanqian.vscreenshot.adapter.WechatNewFriendListAdapter;
import com.nanningzhuanqian.vscreenshot.base.BaseActivity;
import com.nanningzhuanqian.vscreenshot.base.util.SPUtils;
import com.nanningzhuanqian.vscreenshot.common.Constant;
import com.nanningzhuanqian.vscreenshot.item.WechatNewFriendItem;
import com.nanningzhuanqian.vscreenshot.item.WechatNewFriendItems;
import com.nanningzhuanqian.vscreenshot.model.ConversationLite;
import com.nanningzhuanqian.vscreenshot.model.RandomManager;
import com.nanningzhuanqian.vscreenshot.model.WechatNewFriendLite;
import com.nanningzhuanqian.vscreenshot.widget.CustomListview;
import com.nanningzhuanqian.vscreenshot.widget.NewActionSheetDialog;

import org.litepal.LitePal;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * 微信添加朋友界面
 */
public class WechatNewFriendActivity extends BaseActivity {

    private LinearLayout llAdd;
    private CustomListview lvNewFriend;
    private TextView tvDescription;
    private WechatNewFriendListAdapter wechatNewFriendListAdapter;
    @Override
    protected int getLayoutId() {
        return R.layout.activity_wechat_new_friend;
    }

    @Override
    protected void initView() {
        initWechatTopBar();
        btnOption.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showNewFriendOptionSheetDialog();
            }
        });
        llAdd = (LinearLayout)findViewById(R.id.llAdd);
        llAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showNewFriendOptionSheetDialog();
            }
        });
        lvNewFriend = (CustomListview)findViewById(R.id.lvNewFriend);
        tvDescription = (TextView)findViewById(R.id.tvDescription);
        wechatNewFriendListAdapter = new WechatNewFriendListAdapter(getThis());
        lvNewFriend.setAdapter(wechatNewFriendListAdapter);
    }

    @Override
    protected void onResume() {
        super.onResume();
        getLocalNewFriendData();
    }

    @Override
    protected void initEvent() {
        lvNewFriend.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getThis(),WechatCustomNewFriendActivity.class);
                intent.putExtra(Constant.INTENT_KEY_TYPE,Constant.INTENT_VALUE_EDIT);
                intent.putExtra(Constant.INTENT_KEY_POSITION,position);
                startActivity(intent);
            }

        });
    }

    @Override
    protected void initData() {
//        getLocalNewFriendData();
    }

    private void getLocalNewFriendData() {
        List<WechatNewFriendLite> newFriendLites = LitePal.findAll(WechatNewFriendLite.class);
        WechatNewFriendItems.getInstance().clear();
        for(int i = 0;i<newFriendLites.size();i++){
            WechatNewFriendItem item = newFriendLites.get(i).convertToWechatNewFriendItem();
            WechatNewFriendItems.getInstance().add(item);
        }
        if(WechatNewFriendItems.getInstance().size()==0){
            tvDescription.setVisibility(View.GONE);
        }else{
            tvDescription.setVisibility(View.VISIBLE);
        }
        wechatNewFriendListAdapter.notifyDataSetChanged();
    }

    private void showNewFriendOptionSheetDialog() {
        NewActionSheetDialog.Builder builder = new NewActionSheetDialog.Builder(WechatNewFriendActivity.this);

        builder.setCancelable(false);
        builder.setCancelButtonVisiable(true);
        builder.setCanceledOnTouchOutside(true);
        builder.setTitle(getString(R.string.action_settings));
        builder.addSheetItem("添加自定义朋友", NewActionSheetDialog
                .SheetItemColor.Blue, new NewActionSheetDialog.Builder
                .OnSheetItemClickListener() {
            @Override
            public void onClick(int which) {
                //添加自定义对话
                Intent intent = new Intent(getThis(),WechatCustomNewFriendActivity.class);
                intent.putExtra(Constant.INTENT_KEY_TYPE,Constant.INTENT_VALUE_ADD);
                startActivity(intent);

            }
        });
        builder.addSheetItem("随机添加1个朋友", NewActionSheetDialog
                .SheetItemColor.Blue, new NewActionSheetDialog.Builder
                .OnSheetItemClickListener() {
            @Override
            public void onClick(int which) {
                //随机添加1个对话
                randomSingleNewFriend();
            }
        });
        builder.addSheetItem("随机添加10个朋友", NewActionSheetDialog
                .SheetItemColor.Blue, new NewActionSheetDialog.Builder
                .OnSheetItemClickListener() {
            @Override
            public void onClick(int which) {
                //随机添加20个对话
                random10NewFriend();
            }
        });
        builder.addSheetItem("清空所有朋友", NewActionSheetDialog
                .SheetItemColor.Blue, new NewActionSheetDialog.Builder
                .OnSheetItemClickListener() {
            @Override
            public void onClick(int which) {
                LitePal.deleteAll(WechatNewFriendLite.class);
                WechatNewFriendItems.getInstance().clear();
                SPUtils.put(getThis(),Constant.KEY_CONTRACT_UNREAD_COUNT,0);
                tvDescription.setVisibility(View.GONE);
                wechatNewFriendListAdapter.notifyDataSetChanged();
            }
        });

        Dialog dialog = builder.create();
        dialog.show();
    }

    private void randomSingleNewFriend(){
        generateNewFriend(1);
    }

    private void random10NewFriend(){
        generateNewFriend(10);
    }

    private void generateNewFriend(int count){
        int unReadCount = 0;
        for(int i = 0;i<count;i++){
            boolean isInvaild = RandomManager.getBoolean();
            boolean isRead = RandomManager.getBoolean();
            boolean isAdded = RandomManager.getBoolean();
            String name = RandomManager.getInstance().getRandomName();
            long timeMillis = RandomManager.getRandomTime();
            int imgRes =RandomManager.getInstance().getAvatarRes();
            String content = RandomManager.getInstance().getRandomContent();
            WechatNewFriendItem item = new WechatNewFriendItem();
            item.setName(name);
            item.setContent(content);
            item.setUpdateTime(timeMillis);
            item.setImgRes(imgRes);
            item.setInvaild(isInvaild);
            item.setRead(isRead);
            item.setAdded(isAdded);
            if(!isAdded){
                unReadCount+=1;
            }

            WechatNewFriendItems.getInstance().addFirst(item);

            //保存到本地
            WechatNewFriendLite wechatNewFriendLite = item.converToWechatNewFriendLite();
            wechatNewFriendLite.save();
        }
        int lastUnreadCount = (int) SPUtils.get(getThis(),Constant.KEY_CONTRACT_UNREAD_COUNT,0);
        unReadCount +=lastUnreadCount;
        SPUtils.put(getThis(),Constant.KEY_CONTRACT_UNREAD_COUNT,unReadCount);
        wechatNewFriendListAdapter.notifyDataSetChanged();
    }


}
