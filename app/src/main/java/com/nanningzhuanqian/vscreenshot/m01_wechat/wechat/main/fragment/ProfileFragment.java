package com.nanningzhuanqian.vscreenshot.m01_wechat.wechat.main.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.nanningzhuanqian.vscreenshot.R;
import com.nanningzhuanqian.vscreenshot.base.util.SPUtils;
import com.nanningzhuanqian.vscreenshot.common.Constant;
import com.nanningzhuanqian.vscreenshot.m01_wechat.custom.WechatGlobalSettingActivity;

/**
 * Created by WMY on 2018/9/14.
 */

public class ProfileFragment extends Fragment implements View.OnClickListener {
    private View rootView;
    private RelativeLayout rlMoment;
    private ImageView imgAvatar;
    private TextView tvName;
    private TextView tvAccount;
    private LinearLayout llWallet;
    private LinearLayout llCollect;
    private LinearLayout llPictrue;
    private LinearLayout llPacket;
    private LinearLayout llFace;
    private LinearLayout llSetting;

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_profile, container, false);
        initView();
        initEvent();
        initData();
        return rootView;
    }

    private void initView() {
        rlMoment = (RelativeLayout)rootView.findViewById(R.id.rlMoment);
        imgAvatar = (ImageView) rootView.findViewById(R.id.imgAvatar);
        tvName = (TextView) rootView.findViewById(R.id.tvName);
        tvAccount = (TextView) rootView.findViewById(R.id.tvAccount);
        llWallet = (LinearLayout) rootView.findViewById(R.id.llWallet);
        llCollect = (LinearLayout) rootView.findViewById(R.id.llCollect);
        llPictrue = (LinearLayout) rootView.findViewById(R.id.llPictrue);
        llPacket = (LinearLayout) rootView.findViewById(R.id.llPacket);
        llFace = (LinearLayout) rootView.findViewById(R.id.llFace);
        llSetting = (LinearLayout) rootView.findViewById(R.id.llSetting);
        imgAvatar.setImageResource(R.mipmap.app_images_role_10054);
    }

    private void initEvent() {
        rlMoment.setOnClickListener(this);
        llWallet.setOnClickListener(this);
        llCollect.setOnClickListener(this);
        llPictrue.setOnClickListener(this);
        llPacket.setOnClickListener(this);
        llFace.setOnClickListener(this);
        llSetting.setOnClickListener(this);
    }

    private void initData() {

    }

    @Override
    public void onResume() {
        super.onResume();
        getLocalProfileData();
    }

    private void getLocalProfileData() {
        int imgRes = (int) SPUtils.get(getActivity(), Constant.KEY_PROFILE_AVATAR,0);
        imgAvatar.setImageResource(imgRes);
        String name = (String) SPUtils.get(getActivity(),Constant.KEY_PROFILE_NAME,"");
        String account = (String)SPUtils.get(getActivity(),Constant.KEY_PROFILE_ACCOUNT,"");
        if(!TextUtils.isEmpty(name)) {
            tvName.setText(name);
        }
        if(!TextUtils.isEmpty(account)) {
            tvAccount.setText("微信号：" + account);
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case -1:

                break;
            default:
                Intent intent = new Intent(getActivity(), WechatGlobalSettingActivity.class);
                startActivity(intent);
                break;
        }
    }
}
