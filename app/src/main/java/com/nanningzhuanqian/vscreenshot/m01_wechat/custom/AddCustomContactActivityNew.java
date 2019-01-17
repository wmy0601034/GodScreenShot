package com.nanningzhuanqian.vscreenshot.m01_wechat.custom;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.content.FileProvider;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.lzy.imagepicker.ImagePicker;
import com.lzy.imagepicker.bean.ImageItem;
import com.lzy.imagepicker.ui.ImageGridActivity;
import com.lzy.imagepicker.view.CropImageView;
import com.nanningzhuanqian.vscreenshot.R;
import com.nanningzhuanqian.vscreenshot.adapter.ContractAdapter;
import com.nanningzhuanqian.vscreenshot.base.BaseActivity;
import com.nanningzhuanqian.vscreenshot.base.Util;
import com.nanningzhuanqian.vscreenshot.base.bean.Contact;
import com.nanningzhuanqian.vscreenshot.base.bean.TagsCur;
import com.nanningzhuanqian.vscreenshot.base.net.CallbackListener;
import com.nanningzhuanqian.vscreenshot.base.net.HttpUtil;
import com.nanningzhuanqian.vscreenshot.base.util.SPUtils;
import com.nanningzhuanqian.vscreenshot.common.Constant;
import com.nanningzhuanqian.vscreenshot.item.ContractItem;
import com.nanningzhuanqian.vscreenshot.item.ContractItems;
import com.nanningzhuanqian.vscreenshot.item.NetworkAvatar;
import com.nanningzhuanqian.vscreenshot.m00_base.LocalAvatarSelectActivity;
import com.nanningzhuanqian.vscreenshot.m00_base.NetworkAvatarSelectActivity;
import com.nanningzhuanqian.vscreenshot.m01_wechat.wechat.person.WXRegionSelectionActivity;
import com.nanningzhuanqian.vscreenshot.model.ContractBmob;
import com.nanningzhuanqian.vscreenshot.model.ContractLite;
import com.nanningzhuanqian.vscreenshot.widget.NewActionSheetDialog;
import com.nanningzhuanqian.vscreenshot.widget.PicassoImageLoader;
import com.squareup.picasso.Picasso;

import java.io.File;
import java.util.ArrayList;

import cn.bmob.v3.datatype.BmobFile;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.SaveListener;
import cn.bmob.v3.listener.UploadFileListener;

/**
 * 添加自定义联系人界面
 */
public class AddCustomContactActivityNew extends BaseActivity implements View.OnClickListener {
    private TextView tvBack;
    private TextView tvTitle;
    private TextView tvSubmit;
    private LinearLayout llAvatar;
    private ImageView imgIcon;
    private EditText edWXNickname;
    private EditText edRemarkName;
    private EditText edWechatAccount;
    private TextView tvGender;
    private LinearLayout llAddress;
    private TextView tvAddress;
    private EditText edMobile;
    private TextView tvFrom;
    private EditText edCommonGroup;
    private TextView tvTag;
    private EditText edSignatrue;
    private LinearLayout llFrom;
    private LinearLayout llGender;
    private LinearLayout llTag;
    private Button btnRandom;
    private Button btnSubmit;

    private int imgRes;
    private String remarkName;
    private String wxNickname;
    private String wxAccount;
    private int gender = Contact.GENDER_PRIVATE;
    private String wxAddress;
    private String wxMobile = "18888888888";
    private String wxSignature;
    private int commonGroup;
    private String tag;
    private int fromType = 0;
    private String avatarType = "";
    private String imgUrl = "";
    private int iconType = Contact.ICON_TYPE_RESOURCE;


    @Override
    protected int getLayoutId() {
        return R.layout.activity_add_custom_role_new;
    }

    protected void initView() {
        tvBack = (TextView) findViewById(R.id.tvBack);
        tvTitle = (TextView) findViewById(R.id.tvTitle);
        tvSubmit = (TextView) findViewById(R.id.tvSubmit);
        llAvatar = (LinearLayout) findViewById(R.id.llAvatar);
        imgIcon = (ImageView) findViewById(R.id.imgIcon);
        edWXNickname = (EditText) findViewById(R.id.edWXNickname);
        edRemarkName = (EditText) findViewById(R.id.edRemarkName);
        edWechatAccount = (EditText) findViewById(R.id.edWechatAccount);
        tvGender = (TextView) findViewById(R.id.tvGender);
        llAddress = (LinearLayout)findViewById(R.id.llAddress);
        tvAddress = (TextView) findViewById(R.id.tvAddress);
        edMobile = (EditText) findViewById(R.id.edMobile);
        tvFrom = (TextView) findViewById(R.id.tvFrom);
        edSignatrue = (EditText) findViewById(R.id.edSignatrue);
        edCommonGroup = (EditText) findViewById(R.id.edCommonGroup);
        tvTag = (TextView) findViewById(R.id.tvTag);
        llFrom = (LinearLayout) findViewById(R.id.llFrom);
        llGender = (LinearLayout) findViewById(R.id.llGender);
        llTag = (LinearLayout) findViewById(R.id.llTag);
        btnRandom = (Button) findViewById(R.id.btnRandom);
        btnSubmit = (Button) findViewById(R.id.btnSubmit);

        initImagePicker();
    }

    protected void initEvent() {
        tvBack.setOnClickListener(this);
        tvSubmit.setOnClickListener(this);
        llAvatar.setOnClickListener(this);
        llFrom.setOnClickListener(this);
        llGender.setOnClickListener(this);
        llAddress.setOnClickListener(this);
        llTag.setOnClickListener(this);
        tvTag.setOnClickListener(this);
        btnRandom.setOnClickListener(this);
        btnSubmit.setOnClickListener(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        getTag();
    }

    protected void initData() {
        tvTitle.setText("添加自定义联系人");
    }

    private void initImagePicker() {
        ImagePicker imagePicker = ImagePicker.getInstance();
        imagePicker.setImageLoader(new PicassoImageLoader());
        imagePicker.setShowCamera(true);  //显示拍照按钮
        imagePicker.setCrop(true);        //允许裁剪（单选才有效）
        imagePicker.setMultiMode(false);
        imagePicker.setSaveRectangle(true); //是否按矩形区域保存
        imagePicker.setSelectLimit(1);    //选中数量限制
        imagePicker.setStyle(CropImageView.Style.RECTANGLE);  //裁剪框的形状
        imagePicker.setFocusWidth(1000);   //裁剪框的宽度。单位像素（圆形自动取宽高最小值）
        imagePicker.setFocusHeight(1000);  //裁剪框的高度。单位像素（圆形自动取宽高最小值）
        imagePicker.setOutPutX(400);//保存文件的宽度。单位像素
        imagePicker.setOutPutY(400);//保存文件的高度。单位像素
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tvBack:
                finish();
                break;
            case R.id.tvSubmit:
                save();
                break;
            case R.id.llAvatar:
                showAvatarSheetDialog();
                break;
            case R.id.llAddress:
                selectAddress();
                break;
            case R.id.llFrom:
                showFromSelection();
                break;
            case R.id.llTag:
                showTagSelection();
                break;
            case R.id.tvTag:
                showTagSelection();
                break;
            case R.id.llGender:
                showGenderSelection();
                break;
            case R.id.btnRandom:
                randomCreate();
                break;
            case R.id.btnSubmit:
                save();
                break;
        }
    }


    private void getTag() {
        String tag = "";
        for (int i = 0; i < TagsCur.getInstance().size(); i++) {
            tag += TagsCur.getInstance().get(i).getName() + " ";
        }
        tvTag.setText(tag);
    }

    private void showAvatarSheetDialog() {
        NewActionSheetDialog.Builder builder = new NewActionSheetDialog.Builder(AddCustomContactActivityNew.this);

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
                takePhoto();
            }
        });
        builder.addSheetItem("相册", NewActionSheetDialog
                .SheetItemColor.Blue, new NewActionSheetDialog.Builder
                .OnSheetItemClickListener() {
            @Override
            public void onClick(int which) {
                //相册
                Intent intent = new Intent(AddCustomContactActivityNew.this, ImageGridActivity.class);
                startActivityForResult(intent, Constant.REQUEST_CODE_SELECT_LOCAL_AVATAR);
            }
        });
        builder.addSheetItem("头像库", NewActionSheetDialog
                .SheetItemColor.Blue, new NewActionSheetDialog.Builder
                .OnSheetItemClickListener() {
            @Override
            public void onClick(int which) {
                Intent intent = new Intent(AddCustomContactActivityNew.this, LocalAvatarSelectActivity.class);
                startActivityForResult(intent, Constant.REQUEST_CODE_SELECT_LOCAL_AVATAR);
            }
        });
        builder.addSheetItem("在线头像库", NewActionSheetDialog
                .SheetItemColor.Blue, new NewActionSheetDialog.Builder
                .OnSheetItemClickListener() {
            @Override
            public void onClick(int which) {
                //进入在线头像库
                Intent intent = new Intent(AddCustomContactActivityNew.this, NetworkAvatarSelectActivity.class);
                startActivityForResult(intent, Constant.REQUEST_CODE_SELECT_NETWORK_AVATAR);
            }
        });
        Dialog dialog = builder.create();
        dialog.show();
    }

    private void selectAddress(){
        Intent intent = new Intent(getThis(),WXRegionSelectionActivity.class);
        startActivity(intent);
    }

    private void showFromSelection() {
        NewActionSheetDialog.Builder builder = new NewActionSheetDialog.Builder(AddCustomContactActivityNew.this);

        builder.setCancelable(false);
        builder.setCancelButtonVisiable(true);
        builder.setCanceledOnTouchOutside(true);
        builder.setTitle("选择来源");
        builder.addSheetItem(getString(R.string.from_qq), NewActionSheetDialog
                .SheetItemColor.Blue, new NewActionSheetDialog.Builder
                .OnSheetItemClickListener() {
            @Override
            public void onClick(int which) {
                fromType = Contact.FROM_QQ;
                tvFrom.setText(getString(R.string.from_qq));
            }
        });
        builder.addSheetItem(getString(R.string.from_mobile), NewActionSheetDialog
                .SheetItemColor.Blue, new NewActionSheetDialog.Builder
                .OnSheetItemClickListener() {
            @Override
            public void onClick(int which) {
                fromType = Contact.FROM_MOBILE;
                tvFrom.setText(getString(R.string.from_mobile));
            }
        });
        builder.addSheetItem(getString(R.string.from_group), NewActionSheetDialog
                .SheetItemColor.Blue, new NewActionSheetDialog.Builder
                .OnSheetItemClickListener() {
            @Override
            public void onClick(int which) {
                fromType = Contact.FROM_GROUP;
                tvFrom.setText(getString(R.string.from_group));
            }
        });
        builder.addSheetItem(getString(R.string.from_phone_contact), NewActionSheetDialog
                .SheetItemColor.Blue, new NewActionSheetDialog.Builder
                .OnSheetItemClickListener() {
            @Override
            public void onClick(int which) {
                fromType = Contact.FROM_PHONE_CONTACT;
                tvFrom.setText(getString(R.string.from_phone_contact));
            }
        });
        builder.addSheetItem(getString(R.string.from_qrcode), NewActionSheetDialog
                .SheetItemColor.Blue, new NewActionSheetDialog.Builder
                .OnSheetItemClickListener() {
            @Override
            public void onClick(int which) {
                fromType = Contact.FROM_QRCODE;
                tvFrom.setText(getString(R.string.from_qrcode));
            }
        });
        builder.addSheetItem(getString(R.string.from_card_share), NewActionSheetDialog
                .SheetItemColor.Blue, new NewActionSheetDialog.Builder
                .OnSheetItemClickListener() {
            @Override
            public void onClick(int which) {
                fromType = Contact.FROM_CARD_SHARE;
                tvFrom.setText(getString(R.string.from_card_share));
            }
        });
        builder.addSheetItem(getString(R.string.from_near), NewActionSheetDialog
                .SheetItemColor.Blue, new NewActionSheetDialog.Builder
                .OnSheetItemClickListener() {
            @Override
            public void onClick(int which) {
                fromType = Contact.FROM_NEAR;
                tvFrom.setText(getString(R.string.from_near));
            }
        });
        builder.addSheetItem(getString(R.string.from_shake), NewActionSheetDialog
                .SheetItemColor.Blue, new NewActionSheetDialog.Builder
                .OnSheetItemClickListener() {
            @Override
            public void onClick(int which) {
                fromType = Contact.FROM_SHAKE;
                tvFrom.setText(getString(R.string.from_shake));
            }
        });
        builder.addSheetItem(getString(R.string.from_drift), NewActionSheetDialog
                .SheetItemColor.Blue, new NewActionSheetDialog.Builder
                .OnSheetItemClickListener() {
            @Override
            public void onClick(int which) {
                fromType = Contact.FROM_DRIFT;
                tvFrom.setText(getString(R.string.from_drift));
            }
        });
        builder.addSheetItem("无", NewActionSheetDialog
                .SheetItemColor.Blue, new NewActionSheetDialog.Builder
                .OnSheetItemClickListener() {
            @Override
            public void onClick(int which) {
                fromType = Contact.FROM_NONE;
                tvFrom.setText("无");
            }
        });

        Dialog dialog = builder.create();
        dialog.show();
    }

    private void showTagSelection() {
        Intent intent = new Intent(getThis(), WxContactTagSelectionActivity.class);
        startActivity(intent);
    }

    private void showGenderSelection() {
        NewActionSheetDialog.Builder builder = new NewActionSheetDialog.Builder(AddCustomContactActivityNew.this);
        builder.setTitle("选择性别");
        builder.setCanceledOnTouchOutside(true);
        builder.setCancelButtonVisiable(true);
        builder.addSheetItem(getString(R.string.male), NewActionSheetDialog.SheetItemColor.Blue, new NewActionSheetDialog.Builder.OnSheetItemClickListener() {
            @Override
            public void onClick(int which) {
                tvGender.setText(getString(R.string.male));
                gender = Contact.GENDER_MALE;
            }
        });
        builder.addSheetItem(getString(R.string.female), NewActionSheetDialog.SheetItemColor.Blue, new NewActionSheetDialog.Builder.OnSheetItemClickListener() {
            @Override
            public void onClick(int which) {
                tvGender.setText(getString(R.string.female));
                gender = Contact.GENDER_FEMALE;
            }
        });
        builder.addSheetItem(getString(R.string.no_public_show), NewActionSheetDialog.SheetItemColor.Blue, new NewActionSheetDialog.Builder.OnSheetItemClickListener() {
            @Override
            public void onClick(int which) {
                tvGender.setText(getString(R.string.no_public_show));
                gender = Contact.GENDER_PRIVATE;
            }
        });
        Dialog dialog = builder.create();
        dialog.show();
    }

    private void randomCreate() {

    }

    private void takePhoto() {
        Intent intent = new Intent(this, ImageGridActivity.class);
        intent.putExtra(ImageGridActivity.EXTRAS_TAKE_PICKERS, true); // 是否是直接打开相机
        startActivityForResult(intent, Constant.REQUEST_CODE_SELECT_AVATAR_BY_CAMERA);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent intent) {
        super.onActivityResult(requestCode, resultCode, intent);
        Log.i(TAG, "requestCode = " + requestCode + " resultCode = " + resultCode + " intent != null" + (intent != null));
        if (resultCode == ImagePicker.RESULT_CODE_ITEMS) {
            if (intent != null && requestCode == Constant.REQUEST_CODE_SELECT_LOCAL_AVATAR) {
                ArrayList<ImageItem> images = (ArrayList<ImageItem>) intent.getSerializableExtra(ImagePicker.EXTRA_RESULT_ITEMS);
                if (images != null) {
                    ImageItem imageItem = images.get(0);
                    String path = imageItem.path;
                    Log.i(TAG, "images size = " + images.size() + " path = " + path);
                    uploadImage(path);
                } else {

                }
            } else if (requestCode == Constant.REQUEST_CODE_SELECT_AVATAR_BY_CAMERA) {
                Log.i(TAG, "intent != null " + (intent != null));
                ArrayList<ImageItem> images = (ArrayList<ImageItem>) intent.getSerializableExtra(ImagePicker.EXTRA_RESULT_ITEMS);
                if (images != null) {
                    ImageItem imageItem = images.get(0);
                    String path = imageItem.path;
                    Log.i(TAG, "images size = " + images.size() + " path = " + path);
                    uploadImage(path);
                } else {
                    Log.i(TAG, "images = null ");
                }

            } else {
                Log.i(TAG, "没有数据");
            }
        } else if (selectLocalAvatarFinish(requestCode, resultCode)) {
            imgRes = intent.getIntExtra("imgRes", R.mipmap.app_images_defaultface);
            avatarType = Constant.VALUE_PIC_RES;
            iconType = Contact.ICON_TYPE_RESOURCE;
            imgIcon.setImageResource(imgRes);
        } else if (selectNetworkAvatarFinish(requestCode, resultCode)) {
            imgUrl = intent.getStringExtra("imgUrl");
            iconType = Contact.ICON_TYPE_NETWORK;
            Picasso.with(AddCustomContactActivityNew.this)
                    .load(imgUrl)
                    .into(imgIcon);
        } else if (selectTagFinish(requestCode, resultCode)) {
            String tag = intent.getStringExtra("tag");
            tvTag.setText(tag);
        }
    }

    private void uploadImage(String path) {
        showLoadingDialog();
        File file = new File(path);
        final BmobFile bmobFile = new BmobFile(file);
        bmobFile.uploadblock(new UploadFileListener() {
            @Override
            public void done(BmobException e) {

                if (e == null) {
                    imgUrl = bmobFile.getFileUrl();
                    iconType = Contact.ICON_TYPE_NETWORK;
                    uploadNetworkAvatar(imgUrl);
                } else {
                    hideLoadingDialog();
                    toast(e.getMessage());
                }
            }
        });
    }

    private void uploadNetworkAvatar(final String imgUrl) {
        NetworkAvatar networkAvatar = new NetworkAvatar();
        networkAvatar.setImgUrl(imgUrl);
        networkAvatar.save(new SaveListener<String>() {
            @Override
            public void done(String s, BmobException e) {
                hideLoadingDialog();
                if (e == null) {
                    Log.i("wmy", "networkAvatar save done " + s);
                    toast("保存成功");
                    Picasso.with(AddCustomContactActivityNew.this)
                            .load(imgUrl)
                            .into(imgIcon);
                } else {
                    toast(e.getMessage());
                }
            }
        });
    }

    private boolean selectLocalAvatarFinish(int requestCode, int resultCode) {
        return requestCode == Constant.REQUEST_CODE_SELECT_LOCAL_AVATAR && resultCode == 999;
    }

    private boolean selectNetworkAvatarFinish(int requestCode, int resultCode) {
        return requestCode == Constant.REQUEST_CODE_SELECT_NETWORK_AVATAR && resultCode == 999;
    }

    private boolean selectTagFinish(int requestCode, int resultCode) {
        return requestCode == Constant.REQUEST_CODE_SELECT_CONTACT_TAG && resultCode == Constant.RESULT_CODE_SUCCESS;
    }

    private void save() {
        wxNickname = edWXNickname.getText().toString();
        remarkName = edRemarkName.getText().toString();
        wxAccount = edWechatAccount.getText().toString();
        wxAddress = tvAddress.getText().toString();
        wxMobile = edMobile.getText().toString();
        wxSignature = edSignatrue.getText().toString();
        commonGroup = Integer.valueOf(edCommonGroup.getText().toString());
        tag = tvTag.getText().toString();
        if (TextUtils.isEmpty(wxNickname)) {
            toast("请输入微信昵称");
            return;
        }
        boolean checkWxAccount = Util.checkWxAccount(wxAccount);
        if(!checkWxAccount){
            toast(getString(R.string.wx_account_not_right));
            return;
        }
        boolean checkMobile = Util.isPhone(wxMobile);
        if(!checkMobile){
            toast(getString(R.string.mobile_not_right));
            return;
        }


        Contact contact = new Contact();
        contact.setWechatNickName(wxNickname);
        contact.setRemarkName(remarkName);
        contact.setWechatAccount(wxAccount);
        contact.setWechatAddress(wxAddress);
        contact.setMobile(wxMobile);
        contact.setPersonalitySign(wxSignature);
        contact.setIconType(iconType);
        contact.setIconRes(imgRes);
        contact.setIconUrl(imgUrl);
        String mobile = (String) SPUtils.get(getThis(), Constant.KEY_MOBILE, "");
        contact.setPointToUser(mobile);

        ContractItem item = new ContractItem();
        item.setName(wxNickname);
        item.setImgRes(imgRes);
        item.setType(ContractAdapter.ITEM_CONTRACT_TYPE);
        item.setPointToUser(mobile);
        item.setImgType(avatarType);
        ContractItems.getInstance().addFirst(item);
        //保存到本地
        ContractLite contractLite = item.convertToLite();
//        contractLite.save();

        //保存到服务器
        ContractBmob contractBmob = item.convertToBmob();
        HttpUtil.getInstance().saveContract(contractBmob, new CallbackListener() {
            @Override
            public void onSuccess() {

            }

            @Override
            public void onGetSuccess(Object o) {

            }

            @Override
            public void onFailure(String message) {

            }
        });
        toast("添加成功");
        setResult(999);
        finish();
    }
}
