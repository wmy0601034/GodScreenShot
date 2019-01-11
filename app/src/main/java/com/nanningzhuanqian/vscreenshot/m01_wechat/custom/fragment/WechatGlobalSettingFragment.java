package com.nanningzhuanqian.vscreenshot.m01_wechat.custom.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.nanningzhuanqian.vscreenshot.R;
import com.nanningzhuanqian.vscreenshot.base.BaseFragment;
import com.suke.widget.SwitchButton;

public class WechatGlobalSettingFragment extends BaseFragment implements View.OnClickListener {
    private View rootView;
    private LinearLayout llBadge;
    private LinearLayout llNumberMode;
    private LinearLayout llPCLoginPrompt;
    private LinearLayout llTimeMode;
    private TextView tvBadge;
    private TextView tvBadgeMode;
    private TextView tvPromotStatus;
    private SwitchButton tgl24Time;

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
        llBadge = rootView.findViewById(R.id.llBadge);
        llNumberMode = rootView.findViewById(R.id.llNumberMode);
        llPCLoginPrompt = rootView.findViewById(R.id.llPCLoginPrompt);
        llTimeMode = rootView.findViewById(R.id.llTimeMode);
        tvBadge = rootView.findViewById(R.id.tvBadge);
        tvBadgeMode = rootView.findViewById(R.id.tvBadgeMode);
        tvPromotStatus = rootView.findViewById(R.id.tvPromotStatus);
        tgl24Time = rootView.findViewById(R.id.tgl24Time);
    }

    private void initEvent() {
        llBadge.setOnClickListener(this);
        llNumberMode.setOnClickListener(this);
        llPCLoginPrompt.setOnClickListener(this);
//        llTimeMode.setOnClickListener(this);
    }

    private void initData() {

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.llBadge:
                // TODO: 2018/10/5 弹出角标类型选择
                break;
            case R.id.llNumberMode:
                // TODO: 2018/10/5 弹出数字模式选择
                break;
            case R.id.llPCLoginPrompt:
                // TODO: 2018/10/5 弹出登录提示选择
                break;
        }
        tgl24Time.setOnCheckedChangeListener(new SwitchButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(SwitchButton view, boolean isChecked) {
                // TODO: 2018/10/5 改变存在本地的时间模式
            }
        });
    }
}
