package com.nanningzhuanqian.vscreenshot.m01_wechat.wechat.main;

import android.app.Dialog;
import android.content.Intent;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;

import com.nanningzhuanqian.vscreenshot.R;
import com.nanningzhuanqian.vscreenshot.adapter.ContractAdapter;
import com.nanningzhuanqian.vscreenshot.adapter.MainTabAdpter;
import com.nanningzhuanqian.vscreenshot.base.BaseActivity;
import com.nanningzhuanqian.vscreenshot.base.event.RefreshUnReadCountEvent;
import com.nanningzhuanqian.vscreenshot.base.util.SPUtils;
import com.nanningzhuanqian.vscreenshot.common.Constant;
import com.nanningzhuanqian.vscreenshot.item.ContractItem;
import com.nanningzhuanqian.vscreenshot.item.ContractItems;
import com.nanningzhuanqian.vscreenshot.item.ConversationItem;
import com.nanningzhuanqian.vscreenshot.item.ConversationItems;
import com.nanningzhuanqian.vscreenshot.m01_wechat.custom.AddCustomContactActivity;
import com.nanningzhuanqian.vscreenshot.m01_wechat.custom.AddCustomConversationActivity;
import com.nanningzhuanqian.vscreenshot.m01_wechat.custom.WechatGlobalSettingActivity;
import com.nanningzhuanqian.vscreenshot.m01_wechat.wechat.main.fragment.ContactListFragment;
import com.nanningzhuanqian.vscreenshot.m01_wechat.wechat.main.fragment.ConversationListFragment;
import com.nanningzhuanqian.vscreenshot.m01_wechat.wechat.main.fragment.DiscoverFragment;
import com.nanningzhuanqian.vscreenshot.m01_wechat.wechat.main.fragment.ProfileFragment;
import com.nanningzhuanqian.vscreenshot.model.ContractLite;
import com.nanningzhuanqian.vscreenshot.model.ConversationLite;
import com.nanningzhuanqian.vscreenshot.model.RandomManager;
import com.nanningzhuanqian.vscreenshot.widget.DMTabButton;
import com.nanningzhuanqian.vscreenshot.widget.DMTabHost;
import com.nanningzhuanqian.vscreenshot.widget.MFViewPager;
import com.nanningzhuanqian.vscreenshot.widget.NewActionSheetDialog;

import org.greenrobot.eventbus.EventBus;
import org.litepal.LitePal;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * 微信首页
 */
public class WechatMainActivity extends BaseActivity implements DMTabHost.OnCheckedChangeListener, ViewPager
        .OnPageChangeListener {

    private DMTabHost tab_host;
    private MFViewPager viewpager;
    private ImageButton btnAdd;
    //hi from book
    private MainTabAdpter adapter;
    private int currentPageIndex = 0;
    private ConversationListFragment conversationListFragment;
    private ContactListFragment contactListFragment;

    private DMTabButton rdoWechat;
    private DMTabButton rdoContract;
    private DMTabButton rdoDiscovery;
    private DMTabButton rdoUser;


    @Override
    protected int getLayoutId() {
        return R.layout.activity_wechat_main;
    }

    @Override
    protected void initView() {
        tab_host = (DMTabHost) findViewById(R.id.tab_host);
        viewpager = (MFViewPager) findViewById(R.id.viewpager);
        btnAdd = (ImageButton)findViewById(R.id.btnAdd);
        rdoWechat = (DMTabButton)findViewById(R.id.rdoWechat);
        rdoContract = (DMTabButton)findViewById(R.id.rdoContract);
        rdoDiscovery = (DMTabButton)findViewById(R.id.rdoDiscovery);
        rdoUser = (DMTabButton)findViewById(R.id.rdoUser);
        tab_host.setOnCheckedChangeListener(this);
        viewpager.setOnPageChangeListener(this);
        initMainTab();
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (currentPageIndex){
                    case 0:
                        showConversationOptionDialog();
                        break;
                    case 1:
                        showRoleOptionDialog();
                        break;
                }
            }
        });
        initStatusBar();
    }

    @Override
    public void handleEvent(EventBus eventBus) {
        if(eventBus instanceof RefreshUnReadCountEvent){
            initUnReadCount();
        }else {
            super.handleEvent(eventBus);
        }
    }

    @Override
    protected void initEvent() {

    }

    @Override
    protected void initData() {

    }

    @Override
    protected void onResume() {
        super.onResume();
        initUnReadCount();
    }

    private void initUnReadCount(){
        int conversationUnReadCount = (int) SPUtils.get(getThis(), Constant.KEY_CONVERSATION_UNREAD_COUNT,0);
        int contractUnReadCount = (int) SPUtils.get(getThis(),Constant.KEY_CONTRACT_UNREAD_COUNT,0);
        int discoveryUnReadCount = (int)SPUtils.get(getThis(),Constant.KEY_DISCOVERY_UNREAD_COUNT,0);
        rdoWechat.setUnreadCount(conversationUnReadCount);
        rdoContract.setUnreadCount(contractUnReadCount);
        rdoDiscovery.setUnreadCount(discoveryUnReadCount);
        rdoUser.setUnreadCount(0);
    }

    private void initMainTab() {
        tab_host.setChecked(0);
        adapter = new MainTabAdpter(getSupportFragmentManager());
        viewpager.setAdapter(adapter);
        adapter.clear();
        viewpager.setOffscreenPageLimit(4);
        conversationListFragment = new ConversationListFragment();
        contactListFragment = new ContactListFragment();
        adapter.addFragment(conversationListFragment, getString(R.string.app_name));
        adapter.addFragment(contactListFragment, getString(R.string.contacts));
        adapter.addFragment(new DiscoverFragment(), getString(R.string.discover));
        adapter.addFragment(new ProfileFragment(), getString(R.string.me));
        adapter.notifyDataSetChanged();
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        tab_host.setChecked(position);
        currentPageIndex = position;
        viewpager.setCurrentItem(position);
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    @Override
    public void onCheckedChange(int checkedPosition, boolean byUser) {
        currentPageIndex = checkedPosition;
        viewpager.setCurrentItem(checkedPosition);
    }

    private void showConversationOptionDialog(){
        NewActionSheetDialog.Builder builder = new NewActionSheetDialog.Builder(WechatMainActivity.this);

        builder.setCancelable(false);
        builder.setCancelButtonVisiable(true);
        builder.setCanceledOnTouchOutside(true);
        builder.setTitle(getString(R.string.action_settings));
        builder.addSheetItem(getResources().getString(R.string.sheet_item_add_custom_conversation), NewActionSheetDialog
                .SheetItemColor.Blue, new NewActionSheetDialog.Builder
                .OnSheetItemClickListener() {
            @Override
            public void onClick(int which) {
                //添加自定义对话
                Intent intent = new Intent(WechatMainActivity.this,AddCustomConversationActivity.class);
                startActivityForResult(intent,999);
            }
        });
        builder.addSheetItem(getResources().getString(R.string.sheet_item_add_1_conversation), NewActionSheetDialog
                .SheetItemColor.Blue, new NewActionSheetDialog.Builder
                .OnSheetItemClickListener() {
            @Override
            public void onClick(int which) {
                //随机添加1个对话
                randomSingleConversation();
            }
        });
        builder.addSheetItem(getResources().getString(R.string.sheet_item_add_20_conversation), NewActionSheetDialog
                .SheetItemColor.Blue, new NewActionSheetDialog.Builder
                .OnSheetItemClickListener() {
            @Override
            public void onClick(int which) {
                //随机添加20个对话
                random20Conversation();
            }
        });
        builder.addSheetItem(getResources().getString(R.string.sheet_item_page_setting), NewActionSheetDialog
                .SheetItemColor.Blue, new NewActionSheetDialog.Builder
                .OnSheetItemClickListener() {
            @Override
            public void onClick(int which) {
                //页面设置
                Intent intent = new Intent(getThis(), WechatGlobalSettingActivity.class);
                startActivity(intent);
            }
        });
        builder.addSheetItem(getResources().getString(R.string.sheet_item_clear_conversation), NewActionSheetDialog
                .SheetItemColor.Blue, new NewActionSheetDialog.Builder
                .OnSheetItemClickListener() {
            @Override
            public void onClick(int which) {
                //清空对话内容
                ConversationItems.getInstance().clear();
                LitePal.deleteAll(ConversationLite.class);
                conversationListFragment.notifyDataSetChanged();
            }
        });

        Dialog dialog = builder.create();
        dialog.show();
    }

    private void showRoleOptionDialog(){
        NewActionSheetDialog.Builder builder = new NewActionSheetDialog.Builder(WechatMainActivity.this);

        builder.setCancelable(false);
        builder.setCancelButtonVisiable(true);
        builder.setCanceledOnTouchOutside(true);
        builder.setTitle(getString(R.string.action_settings));
        builder.addSheetItem(getResources().getString(R.string.sheet_item_add_custom_role), NewActionSheetDialog
                .SheetItemColor.Blue, new NewActionSheetDialog.Builder
                .OnSheetItemClickListener() {
            @Override
            public void onClick(int which) {
                //添加自定义对话
                Intent intent = new Intent(WechatMainActivity.this,AddCustomContactActivity.class);
                startActivityForResult(intent,999);
            }
        });
        builder.addSheetItem(getResources().getString(R.string.sheet_item_add_1_role), NewActionSheetDialog
                .SheetItemColor.Blue, new NewActionSheetDialog.Builder
                .OnSheetItemClickListener() {
            @Override
            public void onClick(int which) {
                //随机添加1个角色
                randomSingleNewFriend();
            }
        });
        builder.addSheetItem(getResources().getString(R.string.sheet_item_add_20_role), NewActionSheetDialog
                .SheetItemColor.Blue, new NewActionSheetDialog.Builder
                .OnSheetItemClickListener() {
            @Override
            public void onClick(int which) {
                //随机添加20个角色
                random20NewFriend();
            }
        });
        builder.addSheetItem(getResources().getString(R.string.sheet_item_clear_role), NewActionSheetDialog
                .SheetItemColor.Blue, new NewActionSheetDialog.Builder
                .OnSheetItemClickListener() {
            @Override
            public void onClick(int which) {
                //清空所有角色
                ContractItems.getInstance().clear();
                conversationListFragment.notifyDataSetChanged();
            }
        });
        builder.addSheetItem(getResources().getString(R.string.sheet_item_page_setting), NewActionSheetDialog
                .SheetItemColor.Blue, new NewActionSheetDialog.Builder
                .OnSheetItemClickListener() {
            @Override
            public void onClick(int which) {
                //页面设置
            }
        });


        Dialog dialog = builder.create();
        dialog.show();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==999&&resultCode==999){
            adapter.notifyDataSetChanged();
        }
    }

    private void randomSingleNewFriend(){
        generateRandomNewFriend(1);
    }

    private void random20NewFriend(){
        generateRandomNewFriend(20);
    }

    private void generateRandomNewFriend(int count){
        List<ContractItem> cacheList ;
        cacheList = ContractItems.getInstance().getWithOutTop();
        if(cacheList==null){
            cacheList = new ArrayList<>();
        }
        Log.i(TAG,"generateRandomNewFriend count = "+count);
        ContractItems.getInstance().clear();
        for(int i = 0;i<count;i++){
            String name = RandomManager.getInstance().getRandomName();
            int imgRes =RandomManager.getInstance().getAvatarRes();
            String mobile = (String) SPUtils.get(getThis(), Constant.KEY_MOBILE,"");
            ContractItem item = new ContractItem();
            item.setName(name);
            item.setType(ContractAdapter.ITEM_CONTRACT_TYPE);
            item.setImgRes(imgRes);
            item.setPointToUser(mobile);
            cacheList.add(item);
            //保存到本地
            ContractLite contractLite = item.convertToLite();
            contractLite.save();
        }
        ContractItems.getInstance().add(cacheList);
        Log.i(TAG,"ContractItems add = "+ContractItems.getInstance().size()+" "+cacheList.size());
        ContractItems.getInstance().initTop();
        Log.i(TAG,"ContractItems initTop = "+ContractItems.getInstance().size());
        contactListFragment.notifyDataSetChanged();
    }

    private void randomSingleConversation(){
        generateRandomConversation(1);
    }

    private void random20Conversation(){
        generateRandomConversation(20);
    }

    private void generateRandomConversation(int count){
        int unReadCount = 0;
        for(int i = 0;i<count;i++){
            Random random = new Random();
            int badge = random.nextInt(10);
            boolean isPublic = random.nextBoolean();
            boolean isBadge = random.nextBoolean();
            boolean isIgnore = random.nextBoolean();
            String name = RandomManager.getInstance().getRandomName();
            long timeMillis = RandomManager.getRandomTime();
            int imgRes =RandomManager.getInstance().getAvatarRes();
            String content = RandomManager.getInstance().getRandomContent();
            ConversationItem item = new ConversationItem();
            item.setBadgeCount(badge);
            item.setName(name);
            item.setContent(content);
            item.setUpdateTime(timeMillis);
            item.setImgRes(imgRes);
            item.setIgnore(isIgnore);
            item.setBadge(isBadge);
            item.setPublic(isPublic);
            if(!isIgnore){
                unReadCount+=badge;
            }
            String mobile = (String) SPUtils.get(getThis(), Constant.KEY_MOBILE,"");
            item.setPointToUser(mobile);
            ConversationItems.getInstance().addFirst(item);

            //保存到本地
            ConversationLite conversationLite = item.convertToLite();
            conversationLite.save();
        }
        int lastUnreadCount = (int) SPUtils.get(getThis(),Constant.KEY_CONVERSATION_UNREAD_COUNT,0);
        unReadCount +=lastUnreadCount;
        SPUtils.put(getThis(),Constant.KEY_CONVERSATION_UNREAD_COUNT,unReadCount);
        ConversationItems.getInstance().sort();
        conversationListFragment.notifyDataSetChanged();
        rdoWechat.setUnreadCount(unReadCount);
    }
}