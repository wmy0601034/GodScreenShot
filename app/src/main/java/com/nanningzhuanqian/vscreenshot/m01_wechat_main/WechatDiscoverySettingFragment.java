package com.nanningzhuanqian.vscreenshot.m01_wechat_main;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.nanningzhuanqian.vscreenshot.R;
import com.nanningzhuanqian.vscreenshot.base.BaseFragment;

public class WechatDiscoverySettingFragment extends BaseFragment {

    private View rootView;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_wechat_discovery_setting, container, false);
        initView();
        initEvent();
        initData();
        return rootView;
    }

    private void initView() {

    }

    private void initEvent() {

    }

    private void initData() {

    }
}
