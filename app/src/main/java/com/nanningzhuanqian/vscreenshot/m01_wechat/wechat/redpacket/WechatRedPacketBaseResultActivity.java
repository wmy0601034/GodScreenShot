package com.nanningzhuanqian.vscreenshot.m01_wechat.wechat.redpacket;

import android.app.Dialog;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;

import com.linchaolong.android.imagepicker.ImagePicker;
import com.makeramen.roundedimageview.RoundedImageView;
import com.nanningzhuanqian.vscreenshot.R;
import com.nanningzhuanqian.vscreenshot.base.BaseActivity;
import com.nanningzhuanqian.vscreenshot.base.util.SPUtils;
import com.nanningzhuanqian.vscreenshot.common.Constant;
import com.nanningzhuanqian.vscreenshot.m00_base.LocalAvatarSelectActivity;
import com.nanningzhuanqian.vscreenshot.widget.NewActionSheetDialog;

/**
 * 微信发红包结果界面 基类
 */
public abstract class WechatRedPacketBaseResultActivity extends BaseActivity {

    public RoundedImageView imgAvatar;
    private TextView tvName;
    private TextView tvAmount;
    private TextView tvMark;
    private TextView tvDescription;
    public String name;
    public String amount;
    public String mark;
    public ImagePicker imagePicker = new ImagePicker();
    public int imgRes;

    public void setAvatar(){
        imgAvatar = findViewById(R.id.imgAvatar);
        String type = (String)SPUtils.get(getThis(),Constant.KEY_WECHAT_RED_PACKET_AVATAR_TYPE,"");
        String avatar = (String)SPUtils.get(getThis(),Constant.KEY_REDPACKET_AVATAR,"");
        if(imgAvatar!=null) {
            if (TextUtils.isEmpty(type) || Constant.VALUE_PIC_RES.equals(type)) {
                if (!TextUtils.isEmpty(avatar)) {
                    imgRes = Integer.valueOf(avatar);
                    imgAvatar.setImageResource(imgRes);
                } else {
                    imgAvatar.setImageResource(R.mipmap.app_images_defaultface);
                }
            } else if (Constant.VALUE_PIC_LOCAL.equals(type)) {
                Uri avatarUri = Uri.parse(avatar);
                imgAvatar.setImageURI(avatarUri);
            } else {
                imgAvatar.setImageResource(R.mipmap.app_images_defaultface);
            }
            imgAvatar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    showAvatarSheetDialog();
                }
            });
        }
    }

    public void setAmount(){
        tvAmount = findViewById(R.id.tvAmount);
        amount = getIntent().getStringExtra(Constant.INTENT_KEY_AMOUNT);
        if(tvAmount!=null){
            tvAmount.setText(amount);
        }
    }

    public void setName(){
        tvName = findViewById(R.id.tvName);
        name = getIntent().getStringExtra(Constant.INTENT_KEY_NAME);
        if(TextUtils.isEmpty(name)){
            name = (String)SPUtils.get(getThis(),Constant.KEY_REDPACKET_NAME,"");
        }
        if(TextUtils.isEmpty(name)){
            name = (String)SPUtils.get(getThis(),Constant.KEY_PROFILE_NAME,"");
        }
        if(TextUtils.isEmpty(name)){
            name = "吴MoonMoon";
        }
        if(tvName!=null){
            tvName.setText(name);
            tvName.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //编辑名称
                }
            });
        }
    }

    public void setMark(){
        tvMark = findViewById(R.id.tvMark);
        mark = getIntent().getStringExtra(Constant.INTENT_KEY_MARK);
        if(tvMark!=null){
            tvMark.setText(mark);
            tvMark.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //编辑备注
                }
            });
        }
    }

    public void setDescription(String description){
        tvDescription = findViewById(R.id.tvDescription);
        if(tvDescription!=null){
            tvDescription.setText(description);
        }
    }

    public void showAvatarSheetDialog(){
        NewActionSheetDialog.Builder builder = new NewActionSheetDialog.Builder(getThis());

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
                imagePicker.startChooser(getThis(), new ImagePicker.Callback() {
                    // 选择图片回调
                    @Override public void onPickImage(Uri imageUri) {

                    }
                    // 裁剪图片回调
                    @Override public void onCropImage(Uri imageUri) {
                        SPUtils.put(getThis(), Constant.KEY_WECHAT_RED_PACKET_AVATAR_TYPE,Constant.VALUE_PIC_LOCAL);
                        SPUtils.put(getThis(),Constant.KEY_REDPACKET_AVATAR,imageUri.toString());
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
                Intent intent = new Intent(getThis(),LocalAvatarSelectActivity.class);
                startActivityForResult(intent,999);
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

    @Override public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
                                                     @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        imagePicker.onRequestPermissionsResult(this, requestCode, permissions, grantResults);
    }

    private boolean selectAvatarFinish(int requestCode, int resultCode) {
        return requestCode==999&&resultCode ==999;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent intent) {
        super.onActivityResult(requestCode, resultCode, intent);
        if(selectAvatarFinish(requestCode,resultCode)){
            imgRes = intent.getIntExtra("imgRes", R.mipmap.app_images_defaultface);
            SPUtils.put(getThis(),Constant.KEY_WECHAT_RED_PACKET_AVATAR_TYPE,Constant.VALUE_PIC_RES);
            SPUtils.put(getThis(),Constant.KEY_REDPACKET_AVATAR,String.valueOf(imgRes));
            imgAvatar.setImageResource(imgRes);
        }else{
            imagePicker.onActivityResult(this, requestCode, resultCode, intent);
        }
    }

}
