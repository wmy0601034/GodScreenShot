package com.nanningzhuanqian.vscreenshot.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.nanningzhuanqian.vscreenshot.m01_wechat.custom.fragment.WechatContractSettingFragment;
import com.nanningzhuanqian.vscreenshot.m01_wechat.custom.fragment.WechatDiscoverySettingFragment;
import com.nanningzhuanqian.vscreenshot.m01_wechat.custom.fragment.WechatGlobalSettingFragment;
import com.nanningzhuanqian.vscreenshot.m01_wechat.custom.fragment.WechatProfileSettingFragment;

import java.util.ArrayList;
import java.util.List;

public class WechatGlobalSettingPagerAdapter extends FragmentPagerAdapter {

    private List <Fragment> fragments = new ArrayList<>();

    public WechatGlobalSettingPagerAdapter(FragmentManager fm) {
        super(fm);
        initFragment();
    }

    private void initFragment() {
        fragments.clear();
        fragments.add(new WechatGlobalSettingFragment());
        fragments.add(new WechatContractSettingFragment());
        fragments.add(new WechatDiscoverySettingFragment());
        fragments.add(new WechatProfileSettingFragment());
    }

    @Override
    public Fragment  getItem(int position) {
        return fragments.get(position);
    }

    @Override
    public int getCount() {
        return fragments.size();
    }
}
