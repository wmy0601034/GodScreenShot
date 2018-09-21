package com.nanningzhuanqian.vscreenshot.m00_launcher;

import android.app.Dialog;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.nanningzhuanqian.vscreenshot.R;
import com.nanningzhuanqian.vscreenshot.adapter.MainFragmentAdapter;
import com.nanningzhuanqian.vscreenshot.adapter.MainTabAdpter;
import com.nanningzhuanqian.vscreenshot.base.BaseActivity;
import com.nanningzhuanqian.vscreenshot.base.Util;
import com.nanningzhuanqian.vscreenshot.base.util.SPUtils;
import com.nanningzhuanqian.vscreenshot.common.Constant;
import com.nanningzhuanqian.vscreenshot.m01_wechat_main.ContactListFragment;
import com.nanningzhuanqian.vscreenshot.m01_wechat_main.ConversationListFragment;
import com.nanningzhuanqian.vscreenshot.m01_wechat_main.DiscoverFragment;
import com.nanningzhuanqian.vscreenshot.m01_wechat_main.ProfileFragment;

import java.lang.reflect.Field;

public class LauncherActivity extends BaseActivity {
    private ViewPager viewPager;
    private RadioGroup rdoTab;
    private TextView tvTitle;
    private MainTabAdpter adapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
//        setStatusBarTransparent();
        super.onCreate(savedInstanceState);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_launcher;
    }

    protected void initData() {
        int latestVersionCode = (int) SPUtils.get(getThis(), Constant.KEY_VERSION_CODE, 0);
        int versionCode = Util.getLocalVersion(getThis());
        if (latestVersionCode > versionCode) {
            if (Util.isForceUpdate(getThis())) {
                showAPKUpdateDialog(true);
            } else {
                showAPKUpdateDialog(false);
            }
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
//        setStatusBarTransparent();
    }

    protected void initEvent() {
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                switch (position) {
                    case 0:
                        rdoTab.check(R.id.rdoMain);
                        tvTitle.setText("主页");
                        break;
                    case 1:
                        rdoTab.check(R.id.rdoUser);
                        tvTitle.setText("用户");
                        break;
                    case 2:
                        rdoTab.check(R.id.rdoSetting);
                        tvTitle.setText("设置");
                        break;
                    default:
                        break;
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        rdoTab.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.rdoMain: //微信
                        viewPager.setCurrentItem(0);
                        break;
                    case R.id.rdoUser: //通讯录
                        viewPager.setCurrentItem(1);
                        break;
                    case R.id.rdoSetting: //发现
                        viewPager.setCurrentItem(2);
                        break;
                    default:
                        break;
                }
            }
        });
    }

    protected void initView() {
        tvTitle = (TextView) findViewById(R.id.tvTitle);
        tvTitle.setText("主页");
        viewPager = (ViewPager) findViewById(R.id.viewpager);
        rdoTab = (RadioGroup) findViewById(R.id.rdoTab);
        rdoTab.check(R.id.rdoMain);
        adapter = new MainTabAdpter(getSupportFragmentManager());
        viewPager.setAdapter(adapter);
        adapter.clear();
        viewPager.setOffscreenPageLimit(3);
        adapter.addFragment(new MainFragment(), "首页");
        adapter.addFragment(new UserFragment(), "用户");
        adapter.addFragment(new SettingFragment(), "设置");
        adapter.notifyDataSetChanged();
//        setFullScreen();
//        initStatusBar();
    }


    private void showAPKUpdateDialog(boolean isForce) {
        // TODO: 2018/9/16 升级
        Log.i(TAG, "是否需要升级" + isForce);
    }

}
