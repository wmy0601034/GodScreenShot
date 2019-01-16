package com.nanningzhuanqian.vscreenshot.m00_launcher.bank_card;

import android.app.Dialog;
import android.content.Intent;
import android.net.Uri;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.nanningzhuanqian.vscreenshot.R;
import com.nanningzhuanqian.vscreenshot.base.BaseActivity;
import com.nanningzhuanqian.vscreenshot.base.util.SPUtils;
import com.nanningzhuanqian.vscreenshot.common.Constant;
import com.nanningzhuanqian.vscreenshot.item.BankCardItem;
import com.nanningzhuanqian.vscreenshot.item.BankCardItems;
import com.nanningzhuanqian.vscreenshot.m00_base.LocalAvatarSelectActivity;
import com.nanningzhuanqian.vscreenshot.widget.NewActionSheetDialog;

/**
 * 编辑银行卡界面
 */
public class EditBankCardActivity extends BaseActivity {
    private EditText edBank;
    private LinearLayout llBankCarkLogo;
    private ImageView imgIcon;
    private Uri iconUri;
    @Override
    protected int getLayoutId() {
        return R.layout.activity_bank_card_edit;
    }

    @Override
    protected void initView() {
        edBank = findViewById(R.id.edBank);
        llBankCarkLogo = findViewById(R.id.llBankCarkLogo);
        imgIcon = findViewById(R.id.imgIcon);
        setCommonRightContent("添加", new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = edBank.getText().toString();
                if(TextUtils.isEmpty(name)){
                    toast("请输入银行名称");
                    return;
                }
                if(iconUri==null){
                    toast("请选择图标");
                    return;
                }
                BankCardItem bankCardItem= new BankCardItem(name,0);
                bankCardItem.setIconType(Constant.VALUE_PIC_LOCAL);
                bankCardItem.setIconUri(iconUri);
                BankCardItems.getInstance().add(bankCardItem);
                finish();
            }
        });
    }

    @Override
    protected void initEvent() {
        llBankCarkLogo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//相册
//                imagePicker.setTitle("设置Logo");
//                // 设置是否裁剪图片
//                imagePicker.setCropImage(false);
//                imagePicker.startChooser(getThis(), new ImagePicker.Callback() {
//                    // 选择图片回调
//                    @Override public void onPickImage(Uri imageUri) {
//                        iconUri = imageUri;
//                        imgIcon.setImageURI(imageUri);
//                    }
//                    // 裁剪图片回调
//                    @Override public void onCropImage(Uri imageUri) {
//                        iconUri = imageUri;
//                        imgIcon.setImageURI(imageUri);
//                    }
//                });
            }
        });
    }

    @Override
    protected void initData() {

    }

//    ImagePicker imagePicker = new ImagePicker();

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent intent) {
        super.onActivityResult(requestCode, resultCode, intent);
//        imagePicker.onActivityResult(this, requestCode, resultCode, intent);
    }
}
