package com.nanningzhuanqian.vscreenshot.m01_wechat_main;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.widget.TextView;

import com.nanningzhuanqian.vscreenshot.R;
import com.nanningzhuanqian.vscreenshot.adapter.WechatGlobalSettingPagerAdapter;
import com.nanningzhuanqian.vscreenshot.base.BaseActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * 微信全局设置界面
 */
public class WechatGlobalSettingActivity extends BaseActivity {

    private TabLayout tabLayout;
    private ViewPager viewPager;
    private List<String> tabs;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_wechat_global_setting;
    }

    @Override
    protected void initView() {
        initCommonTopBar();
        setCommonTitle("微信通用设置");
        tabLayout = (TabLayout) findViewById(R.id.tabLayout);
        viewPager = (ViewPager) findViewById(R.id.viewpager);
    }

    private void initViewPager() {
        viewPager.setAdapter(new WechatGlobalSettingPagerAdapter(getSupportFragmentManager()));
    }

    private void initTabLayout() {
        tabLayout.setTabMode(TabLayout.MODE_FIXED);
        //指示条的颜色
        tabLayout.setSelectedTabIndicatorColor(getResources().getColor(R.color.mifpay_blue));
        tabLayout.setSelectedTabIndicatorHeight((int) getResources().getDimension(R.dimen.common_indicator_height));
        //关联tabLayout和ViewPager,两者的选择和滑动状态会相互影响
        tabLayout.setupWithViewPager(viewPager);
        //自定义标签布局
        for (int i = 0; i < tabs.size(); i++) {
            TabLayout.Tab tab = tabLayout.getTabAt(i);
            TextView tv = (TextView) LayoutInflater.from(getThis()).inflate(R.layout.common_tab_layout_item, tabLayout, false);
            tv.setText(tabs.get(i));
            tab.setCustomView(tv);
        }
    }

    @Override
    protected void initEvent() {

    }

    @Override
    protected void initData() {
        tabs = new ArrayList<>();
        tabs.add("微信");
        tabs.add("通讯录");
        tabs.add("发现");
        tabs.add("我");
        initViewPager();
        initTabLayout();
    }
}
