package com.nanningzhuanqian.vscreenshot.m00_base;

import android.content.Intent;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.TextView;

import com.nanningzhuanqian.vscreenshot.R;
import com.nanningzhuanqian.vscreenshot.adapter.AvatarAdapter;
import com.nanningzhuanqian.vscreenshot.adapter.NetworkAvatarAdapter;
import com.nanningzhuanqian.vscreenshot.base.BaseActivity;
import com.nanningzhuanqian.vscreenshot.base.net.CallbackListener;
import com.nanningzhuanqian.vscreenshot.base.net.HttpUtil;
import com.nanningzhuanqian.vscreenshot.base.util.SPUtils;
import com.nanningzhuanqian.vscreenshot.common.Constant;
import com.nanningzhuanqian.vscreenshot.item.LocalAvatarItem;
import com.nanningzhuanqian.vscreenshot.item.LocalAvatarItems;
import com.nanningzhuanqian.vscreenshot.item.NetworkAvatar;
import com.nanningzhuanqian.vscreenshot.item.NetworkAvatars;

import java.util.List;

/**
 * 在线头像选择界面
 * Created by lenovo on 2019/1/13.
 */

public class NetworkAvatarSelectActivity extends BaseActivity {

    private TextView tvBack;
    private TextView tvTitle;
    private GridView gvAvatar;
    private NetworkAvatarAdapter avatarAdapter;
    private String type;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_network_avatar_selection;
    }

    protected void initView() {
        tvBack = (TextView)findViewById(R.id.tvBack);
        tvTitle = (TextView)findViewById(R.id.tvTitle);
        gvAvatar = (GridView)findViewById(R.id.gvAvatar);
        avatarAdapter = new NetworkAvatarAdapter(NetworkAvatarSelectActivity.this);
        gvAvatar.setAdapter(avatarAdapter);
        type = getIntent().getStringExtra(Constant.INTENT_KEY_TYPE);

    }

    protected void initEvent() {
        tvBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        gvAvatar.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if(TextUtils.isEmpty(type)){
                    Intent intent = new Intent();
                    intent.putExtra("imgUrl", NetworkAvatars.getInstance().get(position).getImgUrl());
                    setResult(Constant.RESULT_CODE_SUCCESS,intent);
                    finish();
                }else if(Constant.INTENT_VALUE_MOMENT_AVATAR.equals(type)){
                    String imgRes = String.valueOf(LocalAvatarItems.getInstance().get(position).getImgRes());
                    SPUtils.put(getThis(),Constant.KEY_MOMENT_AVATAR,imgRes);
                    finish();
                }else if(Constant.INTENT_VALUE_PROFILE_AVATAR.equals(type)){
                    int imgRes = LocalAvatarItems.getInstance().get(position).getImgRes();
                    SPUtils.put(getThis(),Constant.KEY_PROFILE_AVATAR,imgRes);
                    finish();
                }
            }
        });
    }

    protected void initData() {
        tvTitle.setText("在线头像库");
        showLoadingDialog();
        NetworkAvatars.getInstance().clear();
        HttpUtil.getInstance().getNetworkAvatars(new CallbackListener() {
            @Override
            public void onSuccess() {

            }

            @Override
            public void onGetSuccess(Object o) {
                List<NetworkAvatar> networkAvatars = (List<NetworkAvatar>) o;
                NetworkAvatars.getInstance().add(networkAvatars);
                hideLoadingDialog();
                avatarAdapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(String message) {
                hideLoadingDialog();
                toast(message);
            }
        });
    }

}
