package com.nanningzhuanqian.vscreenshot.m01_wechat_main;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.nanningzhuanqian.vscreenshot.R;
import com.nanningzhuanqian.vscreenshot.base.BaseFragment;

public class WechatContractSettingFragment extends BaseFragment implements View.OnClickListener {

    private View rootView;
    private LinearLayout llBadge;
    private LinearLayout llNumberMode;
    private TextView tvBadge;
    private TextView tvBadgeMode;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_wechat_contract_setting, container, false);
        initView();
        initEvent();
        initData();
        return rootView;
    }

    private void initView() {
        llBadge = rootView.findViewById(R.id.llBadge);
        llNumberMode = rootView.findViewById(R.id.llNumberMode);
        tvBadge = rootView.findViewById(R.id.tvBadge);
        tvBadgeMode = rootView.findViewById(R.id.tvBadgeMode);
    }

    private void initEvent() {
        llBadge.setOnClickListener(this);
        llNumberMode.setOnClickListener(this);
    }

    private void initData() {

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.llBadge:
                // TODO: 2018/10/5 弹出角标选择
                break;
            case R.id.llNumberMode:
                // TODO: 2018/10/5 弹出数字模式选择
                break;
        }
    }
}
