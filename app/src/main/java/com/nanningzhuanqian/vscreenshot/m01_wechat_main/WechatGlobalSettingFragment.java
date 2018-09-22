package com.nanningzhuanqian.vscreenshot.m01_wechat_main;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.nanningzhuanqian.vscreenshot.R;
import com.nanningzhuanqian.vscreenshot.base.BaseFragment;

public class WechatGlobalSettingFragment extends BaseFragment {
    private View rootView;
    private LinearLayout llBadge;
    private LinearLayout llNumberMode;
    private LinearLayout llPCLoginPrompt;
    private LinearLayout llTimeMode;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_wechat_global_setting, container, false);
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
