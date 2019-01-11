package com.nanningzhuanqian.vscreenshot.m00_launcher.main;

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
import com.nanningzhuanqian.vscreenshot.base.util.SPUtils;
import com.nanningzhuanqian.vscreenshot.m00_launcher.LoginActivity;
import com.nanningzhuanqian.vscreenshot.m04_recharge.RechargeActivity;
import com.nanningzhuanqian.vscreenshot.m05_user.UserInfoActivity;

import de.hdodenhof.circleimageview.CircleImageView;

public class UserFragment extends BaseFragment implements View.OnClickListener {
    private View rootView;
    private LinearLayout llUser;
    private CircleImageView imgAvatar;
    private TextView tvName;
    private LinearLayout llMobile;
    private TextView tvMobile;
    private TextView tvStatus;
    private TextView tvType;
    private TextView tvExpires;
    private LinearLayout llWaterMark;
    private TextView tvWaterMark;
    private LinearLayout llContract;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_user, container, false);
        initView();
        initEvent();
        initData();
        return rootView;
    }

    private void initEvent() {
        llUser.setOnClickListener(this);
        llWaterMark.setOnClickListener(this);
        llContract.setOnClickListener(this);
    }

    private void initData() {

    }

    private void initView() {
        llUser = rootView.findViewById(R.id.llUser);
        imgAvatar = rootView.findViewById(R.id.imgAvatar);
        tvName = rootView.findViewById(R.id.tvName);
        llMobile = rootView.findViewById(R.id.llMobile);
        tvMobile = rootView.findViewById(R.id.tvMobile);
        tvStatus = rootView.findViewById(R.id.tvStatus);
        tvType = rootView.findViewById(R.id.tvType);
        tvExpires = rootView.findViewById(R.id.tvExpires);
        llWaterMark = rootView.findViewById(R.id.llWaterMark);
        tvWaterMark = rootView.findViewById(R.id.tvWaterMark);
        llContract = rootView.findViewById(R.id.llContract);
    }

    @Override
    public void onResume() {
        super.onResume();
        refreshUserInfo();
    }

    private void refreshUserInfo() {
        if (isLogin()) {
            String mobile = (String) SPUtils.get(getThis(), "mobile", "");
            tvName.setText(mobile);
            tvStatus.setText("正常");
            tvMobile.setText(mobile);
            tvType.setText("免费用户");
            tvExpires.setText("永久用户");
            tvWaterMark.setText("未去水印");
            tvStatus.setVisibility(View.VISIBLE);
            tvMobile.setVisibility(View.VISIBLE);
            tvType.setVisibility(View.VISIBLE);
            tvExpires.setVisibility(View.VISIBLE);
            tvWaterMark.setVisibility(View.VISIBLE);
        } else {
            tvName.setText("去登录");
            tvStatus.setVisibility(View.GONE);
            tvMobile.setVisibility(View.GONE);
            tvType.setVisibility(View.GONE);
            tvExpires.setVisibility(View.GONE);
            tvWaterMark.setVisibility(View.GONE);
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.llUser:
                if (isLogin()) {
                    goUserInfo();
                } else {
                    goLogin();
                }
                break;
            case R.id.llWaterMark:
                goRecharge();
                break;
            case R.id.llContract:
                String qq = Util.getContractQQ(getThis());
                if(Util.isQQClientAvailable(getThis())){
                    final String qqUrl = "mqqwpa://im/chat?chat_type=wpa&uin="+qq+"&version=1";
                    startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(qqUrl)));
                }else{
                    toast("请安装QQ客户端");
                }
                break;
        }
    }

    private void goRecharge() {
        Intent intent = new Intent(getThis(), RechargeActivity.class);
        startActivity(intent);
    }

    private void goLogin() {
        Intent intent = new Intent(getThis(), LoginActivity.class);
        startActivity(intent);
    }

    private void goUserInfo() {
        Intent intent = new Intent(getThis(), UserInfoActivity.class);
        startActivity(intent);
    }
}
