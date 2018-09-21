package com.nanningzhuanqian.vscreenshot;

import android.app.Dialog;
import android.content.Intent;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import com.nanningzhuanqian.vscreenshot.adapter.MainTabAdpter;
import com.nanningzhuanqian.vscreenshot.base.BaseActivity;
import com.nanningzhuanqian.vscreenshot.m01_wechat_main.ContactListFragment;
import com.nanningzhuanqian.vscreenshot.m01_wechat_main.ConversationListFragment;
import com.nanningzhuanqian.vscreenshot.m01_wechat_main.DiscoverFragment;
import com.nanningzhuanqian.vscreenshot.m01_wechat_main.ProfileFragment;
import com.nanningzhuanqian.vscreenshot.m02_add_conversation.AddCustomConversationActivity;
import com.nanningzhuanqian.vscreenshot.m03_add_role.AddCustomRoleActivity;
import com.nanningzhuanqian.vscreenshot.widget.DMTabHost;
import com.nanningzhuanqian.vscreenshot.widget.MFViewPager;
import com.nanningzhuanqian.vscreenshot.widget.NewActionSheetDialog;

public class MainActivity extends BaseActivity implements DMTabHost.OnCheckedChangeListener, ViewPager.OnPageChangeListener {

    private DMTabHost tab_host;
    private MFViewPager viewpager;
    private ImageButton btnAdd;

    private MainTabAdpter adapter;
    private int currentPageIndex = 0;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void initView() {
        tab_host = (DMTabHost) findViewById(R.id.tab_host);
        viewpager = (MFViewPager) findViewById(R.id.viewpager);
        btnAdd = (ImageButton)findViewById(R.id.btnAdd);
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
    protected void initEvent() {

    }

    @Override
    protected void initData() {

    }

    private void initMainTab() {
        tab_host.setChecked(0);
        adapter = new MainTabAdpter(getSupportFragmentManager());
        viewpager.setAdapter(adapter);
        adapter.clear();
        viewpager.setOffscreenPageLimit(4);
        adapter.addFragment(new ConversationListFragment(), getString(R.string.app_name));
        adapter.addFragment(new ContactListFragment(), getString(R.string.contacts));
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
        NewActionSheetDialog.Builder builder = new NewActionSheetDialog.Builder(MainActivity.this);

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
                Intent intent = new Intent(MainActivity.this,AddCustomConversationActivity.class);
                startActivityForResult(intent,999);
            }
        });
        builder.addSheetItem(getResources().getString(R.string.sheet_item_add_1_conversation), NewActionSheetDialog
                .SheetItemColor.Blue, new NewActionSheetDialog.Builder
                .OnSheetItemClickListener() {
            @Override
            public void onClick(int which) {
                //随机添加1个对话
            }
        });
        builder.addSheetItem(getResources().getString(R.string.sheet_item_add_20_conversation), NewActionSheetDialog
                .SheetItemColor.Blue, new NewActionSheetDialog.Builder
                .OnSheetItemClickListener() {
            @Override
            public void onClick(int which) {
                //随机添加20个对话
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
        builder.addSheetItem(getResources().getString(R.string.sheet_item_clear_conversation), NewActionSheetDialog
                .SheetItemColor.Blue, new NewActionSheetDialog.Builder
                .OnSheetItemClickListener() {
            @Override
            public void onClick(int which) {
                //清空对话内容
            }
        });

        Dialog dialog = builder.create();
        dialog.show();
    }

    private void showRoleOptionDialog(){
        NewActionSheetDialog.Builder builder = new NewActionSheetDialog.Builder(MainActivity.this);

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
                Intent intent = new Intent(MainActivity.this,AddCustomRoleActivity.class);
                startActivityForResult(intent,999);
            }
        });
        builder.addSheetItem(getResources().getString(R.string.sheet_item_add_1_role), NewActionSheetDialog
                .SheetItemColor.Blue, new NewActionSheetDialog.Builder
                .OnSheetItemClickListener() {
            @Override
            public void onClick(int which) {
                //随机添加1个对话
            }
        });
        builder.addSheetItem(getResources().getString(R.string.sheet_item_add_20_role), NewActionSheetDialog
                .SheetItemColor.Blue, new NewActionSheetDialog.Builder
                .OnSheetItemClickListener() {
            @Override
            public void onClick(int which) {
                //随机添加20个对话
            }
        });
        builder.addSheetItem(getResources().getString(R.string.sheet_item_clear_role), NewActionSheetDialog
                .SheetItemColor.Blue, new NewActionSheetDialog.Builder
                .OnSheetItemClickListener() {
            @Override
            public void onClick(int which) {
                //清空对话内容
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
}
