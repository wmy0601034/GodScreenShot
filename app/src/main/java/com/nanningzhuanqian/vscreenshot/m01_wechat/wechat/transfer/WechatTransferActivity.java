package com.nanningzhuanqian.vscreenshot.m01_wechat.wechat.transfer;

import android.app.Dialog;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.linchaolong.android.imagepicker.ImagePicker;
import com.nanningzhuanqian.vscreenshot.R;
import com.nanningzhuanqian.vscreenshot.base.BaseActivity;
import com.nanningzhuanqian.vscreenshot.base.util.SPUtils;
import com.nanningzhuanqian.vscreenshot.common.Constant;
import com.nanningzhuanqian.vscreenshot.m00_base.LocalAvatarSelectActivity;
import com.nanningzhuanqian.vscreenshot.widget.CommonContentEditDialog;
import com.nanningzhuanqian.vscreenshot.widget.NewActionSheetDialog;
import com.nanningzhuanqian.vscreenshot.widget.WechatPromptDialog;
import com.nanningzhuanqian.vscreenshot.widget.WechatTransferMarkDialog;

/**
 * 微信转账界面
 */
public class WechatTransferActivity extends BaseActivity implements View.OnClickListener {

    private ImageView imgAvatar;
    private TextView tvName;
    private EditText edMoney;
    private TextView tvDescription;
    private TextView tvDes;
    private TextView tvEditDes;
    private TextView btnSubmit;
    private int imgRes;
    public Uri avatarUri;
    private String name;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_wechat_transfer;
    }

    @Override
    protected void initView() {
        initWechatTopBar();
        btnRight.setVisibility(View.GONE);
        imgAvatar = (ImageView) findViewById(R.id.imgAvatar);
        tvName = (TextView) findViewById(R.id.tvName);
        edMoney = (EditText) findViewById(R.id.edMoney);
        tvDescription = (TextView) findViewById(R.id.tvDescription);
        btnSubmit = (TextView) findViewById(R.id.btnSubmit);
        tvDes = (TextView) findViewById(R.id.tvDes);
        tvEditDes = (TextView) findViewById(R.id.tvEditDes);
    }

    @Override
    protected void initEvent() {
        tvDescription.setOnClickListener(this);
        btnSubmit.setOnClickListener(this);
        tvEditDes.setOnClickListener(this);
        imgAvatar.setOnClickListener(this);
        tvName.setOnClickListener(this);
    }

    @Override
    protected void initData() {
        String avatarType = (String)SPUtils.get(getThis(),Constant.KEY_WECHAT_TRANSFER_AVATAR_TYPE,"");
        String avatar = (String) SPUtils.get(getThis(), Constant.KEY_TRANSFER_AVATAR, "");
        if(TextUtils.isEmpty(avatarType)||Constant.VALUE_PIC_RES.equals(avatarType)){
            if(TextUtils.isEmpty(avatar)){
                imgRes = R.mipmap.app_images_defaultface;
            }else{
                imgRes = Integer.valueOf(avatar);
            }

            if(imgAvatar!=null){
                imgAvatar.setImageResource(imgRes);
            }
        }else if(Constant.VALUE_PIC_LOCAL.equals(avatarType)){
            if(TextUtils.isEmpty(avatar)){
                imgRes = R.mipmap.app_images_defaultface;
                if(imgAvatar!=null){
                    imgAvatar.setImageResource(imgRes);
                }
            }else{
                avatarUri = Uri.parse(avatar);
                if(imgAvatar!=null){
                    imgAvatar.setImageURI(avatarUri);
                }
            }

        }
        name = (String) SPUtils.get(getThis(), Constant.KEY_TRANSFER_NAME, "");
        if (!TextUtils.isEmpty(name)) {
            tvName.setText(name);
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tvDescription:
                // TODO: 2018/9/18 暂不做处理
                showEditDialog("");
                break;
            case R.id.btnSubmit:
                // TODO: 2018/9/18 点击转账后 弹出选择转账成功还是 待收取的页面
                String amount = edMoney.getText().toString();
                String transferName = tvName.getText().toString();
                if(TextUtils.isEmpty(amount)){
                    toast("请输入金额");
                    return;
                }
                if(TextUtils.isEmpty(transferName)){
                    toast("请输入收款人");
                    return;
                }
                SPUtils.put(getThis(),Constant.KEY_TRANSFER_NAME, transferName);
                showJumpSheetDialog(amount);
                break;
            case R.id.tvEditDes:
                String hint = tvDes.getText().toString();
                showEditDialog(hint);
                break;
            case R.id.imgAvatar:
                showAvatarSheetDialog();
                break;
            case R.id.tvName:
                String name = tvName.getText().toString();
                showEditNameDialog(name);
                break;
        }
    }

    ImagePicker imagePicker = new ImagePicker();
    private void showAvatarSheetDialog() {
        NewActionSheetDialog.Builder builder = new NewActionSheetDialog.Builder(WechatTransferActivity.this);

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
            imgRes = intent.getIntExtra("imgRes",R.mipmap.app_images_defaultface);
            SPUtils.put(getThis(),Constant.KEY_WECHAT_TRANSFER_AVATAR_TYPE,Constant.VALUE_PIC_RES);
            SPUtils.put(getThis(),Constant.KEY_TRANSFER_AVATAR,String.valueOf(imgRes));
            imgAvatar.setImageResource(imgRes);
        }else{
            imagePicker.onActivityResult(this, requestCode, resultCode, intent);
        }
    }

    private WechatTransferMarkDialog wechatTransferMarkDialog;

    private void showEditDialog(String hint) {
        wechatTransferMarkDialog = new WechatTransferMarkDialog(WechatTransferActivity.this);
        wechatTransferMarkDialog.setCancelable(false);
        wechatTransferMarkDialog.setCanceledOnTouchOutside(false);
        if (!TextUtils.isEmpty(hint)) {
            wechatTransferMarkDialog.setHint(hint);
        }
        wechatTransferMarkDialog.setOnCancelListener(new WechatTransferMarkDialog.OnCancelListener() {
            @Override
            public void OnCancel() {
                wechatTransferMarkDialog.dismiss();
            }
        });
        wechatTransferMarkDialog.setOnOkListener(new WechatTransferMarkDialog.OnOkListener() {
            @Override
            public void OnOk(String mark) {
                wechatTransferMarkDialog.dismiss();
                if (TextUtils.isEmpty(mark)) {
                    tvDes.setVisibility(View.GONE);
                    tvEditDes.setVisibility(View.GONE);
                    tvDescription.setVisibility(View.VISIBLE);
                } else {
                    tvDes.setText(mark);
                    tvDes.setVisibility(View.VISIBLE);
                    tvEditDes.setVisibility(View.VISIBLE);
                    tvDescription.setVisibility(View.GONE);
                }
            }
        });
        wechatTransferMarkDialog.show();
    }

    private CommonContentEditDialog commonContentEditDialog;
    private void showEditNameDialog(String hint){
        commonContentEditDialog = new CommonContentEditDialog(WechatTransferActivity.this);
        commonContentEditDialog.setCancelable(false);
        commonContentEditDialog.setCanceledOnTouchOutside(false);
        if (!TextUtils.isEmpty(hint)) {
            commonContentEditDialog.setHint(hint);
        }
        commonContentEditDialog.setOnCancelListener(new CommonContentEditDialog.OnCancelListener() {
            @Override
            public void OnCancel() {
                commonContentEditDialog.dismiss();
            }
        });
        commonContentEditDialog.setOnOkListener(new CommonContentEditDialog.OnOkListener() {
            @Override
            public void OnOk(String name) {
                commonContentEditDialog.dismiss();
//                String content = getTransferName(name);
                SPUtils.put(getThis(),Constant.KEY_TRANSFER_NAME,name);
                tvName.setText(name);
            }
        });
        commonContentEditDialog.show();
    }

    private void showJumpSheetDialog(final String amount){
        NewActionSheetDialog.Builder builder = new NewActionSheetDialog.Builder(WechatTransferActivity.this);

        builder.setCancelable(false);
        builder.setCancelButtonVisiable(true);
        builder.setCanceledOnTouchOutside(true);
        builder.setTitle("选择转账跳转页面");
        builder.addSheetItem("转账成功（更多7个页面）", NewActionSheetDialog
                .SheetItemColor.Blue, new NewActionSheetDialog.Builder
                .OnSheetItemClickListener() {
            @Override
            public void onClick(int which) {
                showMoreJumpSheetDialog(amount);
            }
        });
        builder.addSheetItem("扫码转账成功（个人）", NewActionSheetDialog
                .SheetItemColor.Blue, new NewActionSheetDialog.Builder
                .OnSheetItemClickListener() {
            @Override
            public void onClick(int which) {

                Intent intent = new Intent(getThis(),WechatTransferResultActivity.class);
                intent.putExtra(Constant.INTENT_KEY_TYPE,WechatTransferResultActivity.RESULT_SCAN_PERSON);
                intent.putExtra(Constant.INTENT_KEY_AMOUNT,amount);
                startActivity(intent);
            }
        });
        builder.addSheetItem("扫码转账成功（商户）", NewActionSheetDialog
                .SheetItemColor.Blue, new NewActionSheetDialog.Builder
                .OnSheetItemClickListener() {
            @Override
            public void onClick(int which) {
                Intent intent = new Intent(getThis(),WechatTransferResultActivity1.class);
                intent.putExtra(Constant.INTENT_KEY_TYPE,WechatTransferResultActivity.RESULT_SCAN_MERCHAIN);
                intent.putExtra(Constant.INTENT_KEY_AMOUNT,amount);
                startActivity(intent);
            }
        });
        builder.addSheetItem("年转账限额", NewActionSheetDialog
                .SheetItemColor.Blue, new NewActionSheetDialog.Builder
                .OnSheetItemClickListener() {
            @Override
            public void onClick(int which) {
                showAmountLimitDialog();
            }
        });
        Dialog dialog = builder.create();
        dialog.show();
    }

    private void showMoreJumpSheetDialog(final String amount){
        NewActionSheetDialog.Builder builder = new NewActionSheetDialog.Builder(WechatTransferActivity.this);

        builder.setCancelable(false);
        builder.setCancelButtonVisiable(true);
        builder.setCanceledOnTouchOutside(true);
        builder.setTitle("选择转账跳转页面");
        builder.addSheetItem("付款成功页面", NewActionSheetDialog
                .SheetItemColor.Blue, new NewActionSheetDialog.Builder
                .OnSheetItemClickListener() {
            @Override
            public void onClick(int which) {
                Intent intent = new Intent(getThis(),WechatTransferResultActivity2.class);
                intent.putExtra(Constant.INTENT_KEY_TYPE,WechatTransferResultActivity.RESULT_PAY_SUCCESS);
                intent.putExtra(Constant.INTENT_KEY_AMOUNT,amount);
                startActivity(intent);
            }
        });
        builder.addSheetItem("待对方确认收款", NewActionSheetDialog
                .SheetItemColor.Blue, new NewActionSheetDialog.Builder
                .OnSheetItemClickListener() {
            @Override
            public void onClick(int which) {
                Intent intent = new Intent(getThis(),WechatTransferResultActivity3.class);
                intent.putExtra(Constant.INTENT_KEY_TYPE,WechatTransferResultActivity.RESULT_WAIT_OTHER);
                intent.putExtra(Constant.INTENT_KEY_AMOUNT,amount);
                startActivity(intent);
            }
        });
        builder.addSheetItem("对方已收钱", NewActionSheetDialog
                .SheetItemColor.Blue, new NewActionSheetDialog.Builder
                .OnSheetItemClickListener() {
            @Override
            public void onClick(int which) {
                Intent intent = new Intent(getThis(),WechatTransferResultActivity4.class);
                intent.putExtra(Constant.INTENT_KEY_TYPE,WechatTransferResultActivity.RESULT_OTHER_RECEIVED);
                intent.putExtra(Constant.INTENT_KEY_AMOUNT,amount);
                startActivity(intent);
            }
        });
        builder.addSheetItem("待我确认收款", NewActionSheetDialog
                .SheetItemColor.Blue, new NewActionSheetDialog.Builder
                .OnSheetItemClickListener() {
            @Override
            public void onClick(int which) {
                Intent intent = new Intent(getThis(),WechatTransferResultActivity5.class);
                intent.putExtra(Constant.INTENT_KEY_TYPE,WechatTransferResultActivity.RESULT_WAIT_SELF);
                intent.putExtra(Constant.INTENT_KEY_AMOUNT,amount);
                startActivity(intent);
            }
        });
        builder.addSheetItem("我已收钱", NewActionSheetDialog
                .SheetItemColor.Blue, new NewActionSheetDialog.Builder
                .OnSheetItemClickListener() {
            @Override
            public void onClick(int which) {
                Intent intent = new Intent(getThis(),WechatTransferResultActivity6.class);
                intent.putExtra(Constant.INTENT_KEY_TYPE,WechatTransferResultActivity.RESULT_SELF_RECEIVED);
                intent.putExtra(Constant.INTENT_KEY_AMOUNT,amount);
                startActivity(intent);
            }
        });
        builder.addSheetItem("我已退还", NewActionSheetDialog
                .SheetItemColor.Blue, new NewActionSheetDialog.Builder
                .OnSheetItemClickListener() {
            @Override
            public void onClick(int which) {
                Intent intent = new Intent(getThis(),WechatTransferResultActivity7.class);
                intent.putExtra(Constant.INTENT_KEY_TYPE,WechatTransferResultActivity.RESULT_SELF_RETURN);
                intent.putExtra(Constant.INTENT_KEY_AMOUNT,amount);
                startActivity(intent);
            }
        });
        builder.addSheetItem("对方已退还", NewActionSheetDialog
                .SheetItemColor.Blue, new NewActionSheetDialog.Builder
                .OnSheetItemClickListener() {
            @Override
            public void onClick(int which) {
                Intent intent = new Intent(getThis(),WechatTransferResultActivity8.class);
                intent.putExtra(Constant.INTENT_KEY_TYPE,WechatTransferResultActivity.RESULT_OTHER_RETURN);
                intent.putExtra(Constant.INTENT_KEY_AMOUNT,amount);
                startActivity(intent);
            }
        });
        Dialog dialog = builder.create();
        dialog.show();
    }

    private WechatPromptDialog wechatPromptDialog;
    private void showAmountLimitDialog(){
        wechatPromptDialog = new WechatPromptDialog(WechatTransferActivity.this);
        wechatPromptDialog.setCancelable(false);
        wechatPromptDialog.setCanceledOnTouchOutside(false);
        wechatPromptDialog.show();
    }

}
