package com.nanningzhuanqian.vscreenshot.m00_launcher;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.nanningzhuanqian.vscreenshot.R;
import com.nanningzhuanqian.vscreenshot.base.BaseFragment;
import com.nanningzhuanqian.vscreenshot.base.Util;
import com.nanningzhuanqian.vscreenshot.base.util.LoginUtils;
import com.nanningzhuanqian.vscreenshot.m06_setting.FeedbackActivity;
import com.nanningzhuanqian.vscreenshot.model.Feedback;

public class SettingFragment extends BaseFragment implements View.OnClickListener {
    private View rootView;
    private LinearLayout llFeedback;
    private LinearLayout llQQ;
    private TextView tvQQ;
    private LinearLayout llRoleManager;
    private LinearLayout llBankCarkManager;
    private LinearLayout llReset;
    private LinearLayout llProtocol;
    private LinearLayout llVersion;
    private LinearLayout llRight;
    private TextView btnQuit;
    private String qq;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_setting, container, false);
        initView();
        initEvent();
        initData();
        return rootView;
    }

    private void initEvent() {
        llFeedback.setOnClickListener(this);
        llQQ.setOnClickListener(this);
        llRoleManager.setOnClickListener(this);
        llBankCarkManager.setOnClickListener(this);
        llReset.setOnClickListener(this);
        llProtocol.setOnClickListener(this);
        llVersion.setOnClickListener(this);
        llRight.setOnClickListener(this);
        btnQuit.setOnClickListener(this);
    }

    private void initData() {
        qq = Util.getContractQQ(getThis());
        tvQQ.setText(qq);
    }

    private void initView() {
        llFeedback = (LinearLayout) rootView.findViewById(R.id.llFeedback);
        llQQ = (LinearLayout) rootView.findViewById(R.id.llQQ);
        tvQQ = (TextView)rootView.findViewById(R.id.tvQQ);
        llRoleManager = (LinearLayout) rootView.findViewById(R.id.llRoleManager);
        llBankCarkManager = (LinearLayout) rootView.findViewById(R.id.llBankCarkManager);
        llReset = (LinearLayout) rootView.findViewById(R.id.llReset);
        llProtocol = (LinearLayout) rootView.findViewById(R.id.llProtocol);
        llVersion = (LinearLayout) rootView.findViewById(R.id.llVersion);
        llRight = (LinearLayout) rootView.findViewById(R.id.llRight);
        btnQuit = (TextView) rootView.findViewById(R.id.btnQuit);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.llFeedback:
                // TODO: 2018/9/16 跳转去QQ 236690125
                if(isLogin()){
                    Intent intent = new Intent(getThis(), FeedbackActivity.class);
                    startActivity(intent);
                }else{
                    LoginUtils.goLogin(getActivity(),FeedbackActivity.class);
                }
                break;
            case R.id.llQQ:
                // TODO: 2018/9/16 跳转去QQ 236690125
                if (Util.isQQClientAvailable(getThis())) {
                    final String qqUrl = "mqqwpa://im/chat?chat_type=wpa&uin=" + qq + "&version=1";
                    startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(qqUrl)));
                } else {
                    toast("请安装QQ客户端");
                }
                break;
            case R.id.llRoleManager:
                // TODO: 2018/9/16 跳转去微信
//                Intent intent = new Intent(getActivity(),);
                break;
            case R.id.llBankCarkManager:

                break;
            case R.id.llReset:

                break;
            case R.id.llProtocol:

                break;
            case R.id.llVersion:

                break;
            case R.id.llRight:

                break;
            case R.id.btnQuit:

                break;
        }
    }
}
