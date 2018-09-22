package com.nanningzhuanqian.vscreenshot.m01_wechat_main;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.nanningzhuanqian.vscreenshot.R;
import com.nanningzhuanqian.vscreenshot.base.BaseFragment;
import com.nanningzhuanqian.vscreenshot.base.util.SPUtils;
import com.nanningzhuanqian.vscreenshot.common.Constant;
import com.nanningzhuanqian.vscreenshot.m02_add_conversation.LocalAvatarSelectActivity;
import com.nanningzhuanqian.vscreenshot.m03_add_role.AddCustomRoleActivity;
import com.nanningzhuanqian.vscreenshot.widget.NewActionSheetDialog;

public class WechatProfileSettingFragment extends BaseFragment {

    private View rootView;
    private LinearLayout llAvatar;
    private ImageView imgAvatar;
    private EditText edNickName;
    private EditText edWechatAccount;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_wechat_profile_setting, container, false);
        initView();
        initEvent();
        initData();
        return rootView;
    }

    private void initView() {
        llAvatar = rootView.findViewById(R.id.llAvatar);
        imgAvatar = rootView.findViewById(R.id.imgAvatar);
        edNickName = rootView.findViewById(R.id.edNickName);
        edWechatAccount = rootView.findViewById(R.id.edWechatAccount);
    }

    private void initEvent() {
        llAvatar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showAvatarSheetDialog();
            }
        });
        edNickName.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                SPUtils.put(getActivity(),Constant.KEY_PROFILE_NAME,edNickName.getText().toString());
            }
        });
        edWechatAccount.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                SPUtils.put(getActivity(),Constant.KEY_PROFILE_ACCOUNT,edWechatAccount.getText().toString());
            }
        });
    }

    private void initData() {

    }

    @Override
    public void onResume() {
        super.onResume();
        getLocalConfig();
    }

    private void getLocalConfig() {
        int imgRes = (int) SPUtils.get(getActivity(),Constant.KEY_PROFILE_AVATAR,0);
        if(imgRes!=0){
            imgAvatar.setImageResource(imgRes);
        }
        String nickName = (String) SPUtils.get(getActivity(),Constant.KEY_PROFILE_NAME,"");
        edNickName.setText(nickName);
        String account = (String)SPUtils.get(getActivity(),Constant.KEY_PROFILE_ACCOUNT,"");
        edWechatAccount.setText(account);
    }

    private void showAvatarSheetDialog() {
        NewActionSheetDialog.Builder builder = new NewActionSheetDialog.Builder(getActivity());

        builder.setCancelable(false);
        builder.setCancelButtonVisiable(true);
        builder.setCanceledOnTouchOutside(true);
        builder.setTitle("选择头像");
        builder.addSheetItem("拍照", NewActionSheetDialog
                .SheetItemColor.Blue, new NewActionSheetDialog.Builder
                .OnSheetItemClickListener() {
            @Override
            public void onClick(int which) {
                //拍照
                toast("暂未开放");
            }
        });
        builder.addSheetItem("相册", NewActionSheetDialog
                .SheetItemColor.Blue, new NewActionSheetDialog.Builder
                .OnSheetItemClickListener() {
            @Override
            public void onClick(int which) {
                //随机添加1个对话
                toast("暂未开放");
            }
        });
        builder.addSheetItem("头像库", NewActionSheetDialog
                .SheetItemColor.Blue, new NewActionSheetDialog.Builder
                .OnSheetItemClickListener() {
            @Override
            public void onClick(int which) {
                Intent intent = new Intent(getActivity(),LocalAvatarSelectActivity.class);
                intent.putExtra(Constant.INTENT_KEY_TYPE,Constant.INTENT_VALUE_PROFILE_AVATAR);
                startActivity(intent);
            }
        });
        builder.addSheetItem("在线头像库", NewActionSheetDialog
                .SheetItemColor.Blue, new NewActionSheetDialog.Builder
                .OnSheetItemClickListener() {
            @Override
            public void onClick(int which) {
                //页面设置
                toast("暂未开放");
            }
        });


        Dialog dialog = builder.create();
        dialog.show();
    }

}
