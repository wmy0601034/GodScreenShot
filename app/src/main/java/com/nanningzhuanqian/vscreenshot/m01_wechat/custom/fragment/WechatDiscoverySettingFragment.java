package com.nanningzhuanqian.vscreenshot.m01_wechat.custom.fragment;

import android.app.Dialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.linchaolong.android.imagepicker.ImagePicker;
import com.nanningzhuanqian.vscreenshot.R;
import com.nanningzhuanqian.vscreenshot.base.BaseFragment;
import com.nanningzhuanqian.vscreenshot.base.util.SPUtils;
import com.nanningzhuanqian.vscreenshot.common.Constant;
import com.nanningzhuanqian.vscreenshot.m00_base.LocalAvatarSelectActivity;
import com.nanningzhuanqian.vscreenshot.widget.NewActionSheetDialog;
import com.suke.widget.SwitchButton;

public class WechatDiscoverySettingFragment extends BaseFragment {

    private View rootView;
    private EditText edBadgeCount;
    private EditText edUnReadCount;
    private LinearLayout llAvatar;
    private ImageView imgAvatar;
    private SwitchButton tglShowKanyikan;
    private SwitchButton tglShowSouyisou;
    private SwitchButton tglShowNearby;
    private SwitchButton tglPiaoliuping;
    private boolean showKanyikan;
    private boolean showSouyisou;
    private boolean showNearBy;
    private boolean showPiaoliuping;
    private int discoveryUnReadCount;
    private int momentUnReadCount;
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
        edBadgeCount = rootView.findViewById(R.id.edBadgeCount);
        edUnReadCount = rootView.findViewById(R.id.edUnReadCount);
        llAvatar = rootView.findViewById(R.id.llAvatar);
        imgAvatar = rootView.findViewById(R.id.imgAvatar);
        tglShowKanyikan = rootView.findViewById(R.id.tglShowKanyikan);
        tglShowSouyisou = rootView.findViewById(R.id.tglShowSouyisou);
        tglShowNearby = rootView.findViewById(R.id.tglShowNearby);
        tglPiaoliuping = rootView.findViewById(R.id.tglPiaoliuping);
    }

    private void initEvent() {
        edBadgeCount.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                String number = edBadgeCount.getText().toString();
                if(!TextUtils.isEmpty(number)){
                    discoveryUnReadCount = Integer.valueOf(number);
                    SPUtils.put(getActivity(),Constant.KEY_DISCOVERY_UNREAD_COUNT,discoveryUnReadCount);
                }
            }
        });

        edUnReadCount.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                String number = edUnReadCount.getText().toString();
                if(!TextUtils.isEmpty(number)){
                    momentUnReadCount = Integer.valueOf(number);
                    SPUtils.put(getActivity(),Constant.KEY_MOMENT_UNREAD_COUNT,momentUnReadCount);
                }
            }
        });
        tglShowKanyikan.setOnCheckedChangeListener(new SwitchButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(SwitchButton view, boolean isChecked) {
                showKanyikan = isChecked;
                SPUtils.put(getActivity(),Constant.KEY_SHOW_KANYIKAN,showKanyikan);
            }
        });
        tglShowSouyisou.setOnCheckedChangeListener(new SwitchButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(SwitchButton view, boolean isChecked) {
                showSouyisou = isChecked;
                SPUtils.put(getActivity(),Constant.KEY_SHOW_SOUYISOU,showSouyisou);
            }
        });
        tglShowNearby.setOnCheckedChangeListener(new SwitchButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(SwitchButton view, boolean isChecked) {
                showNearBy  =isChecked;
                SPUtils.put(getActivity(),Constant.KEY_SHOW_NEARBY,showNearBy);
            }
        });
        tglPiaoliuping.setOnCheckedChangeListener(new SwitchButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(SwitchButton view, boolean isChecked) {
                showPiaoliuping = isChecked;
                SPUtils.put(getActivity(),Constant.KEY_SHOW_PIAOLIUPING,showPiaoliuping);
            }
        });
        llAvatar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showAvatarSheetDialog();
            }
        });
    }

    private void initData() {
        showKanyikan = (boolean) SPUtils.get(getThis(), Constant.KEY_SHOW_KANYIKAN,false);
        showSouyisou = (boolean) SPUtils.get(getThis(), Constant.KEY_SHOW_SOUYISOU,false);
        showNearBy = (boolean) SPUtils.get(getThis(), Constant.KEY_SHOW_NEARBY,false);
        showPiaoliuping = (boolean) SPUtils.get(getThis(), Constant.KEY_SHOW_PIAOLIUPING,false);
        momentUnReadCount = (int)SPUtils.get(getThis(),Constant.KEY_MOMENT_UNREAD_COUNT,0);
        discoveryUnReadCount = (int)SPUtils.get(getThis(),Constant.KEY_DISCOVERY_UNREAD_COUNT,0);
        tglShowKanyikan.setChecked(showKanyikan);
        tglShowSouyisou.setChecked(showSouyisou);
        tglShowNearby.setChecked(showNearBy);
        tglPiaoliuping.setChecked(showPiaoliuping);
        edBadgeCount.setText(discoveryUnReadCount==0?"":String.valueOf(discoveryUnReadCount));
        edUnReadCount.setText(momentUnReadCount==0?"":String.valueOf(momentUnReadCount));
    }

    @Override
    public void onResume() {
        super.onResume();
        String imgResource = (String) SPUtils.get(getActivity(),Constant.KEY_MOMENT_AVATAR,"");
        if(TextUtils.isEmpty(imgResource)){

        }else{
            int imgRes = Integer.valueOf(imgResource);
            imgAvatar.setImageResource(imgRes);
        }
    }

    ImagePicker imagePicker = new ImagePicker();
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
                //相册
                imagePicker.setTitle("设置头像");
                // 设置是否裁剪图片
                imagePicker.setCropImage(true);
                imagePicker.startChooser(getActivity(), new ImagePicker.Callback() {
                    // 选择图片回调
                    @Override public void onPickImage(Uri imageUri) {

                    }
                    // 裁剪图片回调
                    @Override public void onCropImage(Uri imageUri) {
                        SPUtils.put(getThis(),Constant.KEY_WECHAT_TRANSFER_AVATAR_TYPE,Constant.VALUE_PIC_LOCAL);
                        SPUtils.put(getThis(),Constant.KEY_TRANSFER_AVATAR,imageUri.toString());
                        imgAvatar.setImageURI(imageUri);
                    }
                });
            }
        });
        builder.addSheetItem("头像库", NewActionSheetDialog
                .SheetItemColor.Blue, new NewActionSheetDialog.Builder
                .OnSheetItemClickListener() {
            @Override
            public void onClick(int which) {
                Intent intent = new Intent(getActivity(),LocalAvatarSelectActivity.class);
                intent.putExtra(Constant.INTENT_KEY_TYPE,Constant.INTENT_VALUE_MOMENT_AVATAR);
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
