package com.nanningzhuanqian.vscreenshot.m01_wechat.custom;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.content.FileProvider;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.linchaolong.android.imagepicker.ImagePicker;
import com.lzy.imagepicker.bean.ImageItem;
import com.lzy.imagepicker.ui.ImageGridActivity;
import com.nanningzhuanqian.vscreenshot.R;
import com.nanningzhuanqian.vscreenshot.adapter.ContractAdapter;
import com.nanningzhuanqian.vscreenshot.base.BaseActivity;
import com.nanningzhuanqian.vscreenshot.base.bean.Contact;
import com.nanningzhuanqian.vscreenshot.base.bean.TagsCur;
import com.nanningzhuanqian.vscreenshot.base.net.CallbackListener;
import com.nanningzhuanqian.vscreenshot.base.net.HttpUtil;
import com.nanningzhuanqian.vscreenshot.base.util.SPUtils;
import com.nanningzhuanqian.vscreenshot.common.Constant;
import com.nanningzhuanqian.vscreenshot.item.ContractItem;
import com.nanningzhuanqian.vscreenshot.item.ContractItems;
import com.nanningzhuanqian.vscreenshot.m00_base.LocalAvatarSelectActivity;
import com.nanningzhuanqian.vscreenshot.m00_base.NetworkAvatarSelectActivity;
import com.nanningzhuanqian.vscreenshot.model.ContractBmob;
import com.nanningzhuanqian.vscreenshot.model.ContractLite;
import com.nanningzhuanqian.vscreenshot.widget.NewActionSheetDialog;
import com.squareup.picasso.Picasso;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

import cn.bmob.v3.datatype.BmobFile;
import cn.bmob.v3.exception.BmobException;
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
    private EditText edAddress;
    private EditText edMobile;
    private TextView tvFrom;
    private EditText edCommon;
    private TextView tvTag;
    private EditText edSignatrue;
    private LinearLayout llFrom;
    private LinearLayout llGender;
    private LinearLayout llTag;
    private Button btnRandom;
    private Button btnSubmit;

    private int fromType = 0;
    private String avatarType = "";
    private Uri avatarUri;
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
        edAddress = (EditText) findViewById(R.id.edAddress);
        edMobile = (EditText) findViewById(R.id.edMobile);
        tvFrom = (TextView) findViewById(R.id.tvFrom);
        edSignatrue = (EditText) findViewById(R.id.edSignatrue);
        edCommon = (EditText) findViewById(R.id.edCommon);
        tvTag = (TextView) findViewById(R.id.tvTag);
        llFrom = (LinearLayout) findViewById(R.id.llFrom);
        llGender = (LinearLayout) findViewById(R.id.llGender);
        llTag = (LinearLayout) findViewById(R.id.llTag);
        btnRandom = (Button) findViewById(R.id.btnRandom);
        btnSubmit = (Button) findViewById(R.id.btnSubmit);
    }

    protected void initEvent() {
        tvBack.setOnClickListener(this);
        tvSubmit.setOnClickListener(this);
        llAvatar.setOnClickListener(this);
        llFrom.setOnClickListener(this);
        llGender.setOnClickListener(this);
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

    ImagePicker imagePicker = new ImagePicker();

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
                imagePicker.setTitle("设置头像");
                // 设置是否裁剪图片
                imagePicker.setCropImage(true);
                imagePicker.startChooser(getThis(), new ImagePicker.Callback() {
                    // 选择图片回调
                    @Override
                    public void onPickImage(Uri imageUri) {

                    }

                    // 裁剪图片回调
                    @Override
                    public void onCropImage(Uri imageUri) {
                        avatarType = Constant.VALUE_PIC_LOCAL;
                        avatarUri = imageUri;
                        imgIcon.setImageURI(imageUri);
                    }
                });
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
            }
        });
        builder.addSheetItem(getString(R.string.female), NewActionSheetDialog.SheetItemColor.Blue, new NewActionSheetDialog.Builder.OnSheetItemClickListener() {
            @Override
            public void onClick(int which) {
                tvGender.setText(getString(R.string.female));
            }
        });
        Dialog dialog = builder.create();
        dialog.show();
    }

    private void randomCreate() {

    }

    private File tempFile;
    private void takePhoto() {
        tempFile = new File(getFilesDir(), System.currentTimeMillis() + ".jpg");
        //跳转到调用系统相机
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        //判断版本
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {   //如果在Android7.0以上,使用FileProvider获取Uri
            intent.setFlags(Intent.FLAG_GRANT_WRITE_URI_PERMISSION);
            Uri contentUri = FileProvider.getUriForFile(AddCustomContactActivityNew.this, "com.nanningzhuanqian.vscreenshot.fileprovider", tempFile);
            intent.putExtra(MediaStore.EXTRA_OUTPUT, contentUri);
        } else {    //否则使用Uri.fromFile(file)方法获取Uri
            intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(tempFile));
        }
        startActivityForResult(intent, Constant.REQUEST_CODE_SELECT_AVATAR_BY_CAMERA);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent intent) {
        super.onActivityResult(requestCode, resultCode, intent);
        if (selectLocalAvatarFinish(requestCode, resultCode)) {
            imgRes = intent.getIntExtra("imgRes", R.mipmap.app_images_defaultface);
            avatarType = Constant.VALUE_PIC_RES;
            iconType = Contact.ICON_TYPE_RESOURCE;
            imgIcon.setImageResource(imgRes);
        } else if (selectNetworkAvatarFinish(requestCode, resultCode)) {
            imgUrl = intent.getStringExtra("imgUrl");
            Picasso.with(AddCustomContactActivityNew.this)
                    .load(imgUrl)
                    .into(imgIcon);
        } else if (requestCode == Constant.REQUEST_CODE_SELECT_LOCAL_AVATAR) {
            imagePicker.onActivityResult(this, requestCode, resultCode, intent);
        } else if (requestCode == Constant.REQUEST_CODE_SELECT_AVATAR_BY_CAMERA) {
            //用相机返回的照片去调用剪裁也需要对Uri进行处理
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                Uri contentUri = FileProvider.getUriForFile(AddCustomContactActivityNew.this, "com.nanningzhuanqian.vscreenshot.fileprovider", tempFile);
                cropPhoto(contentUri);
            } else {
                cropPhoto(Uri.fromFile(tempFile));
            }
        } else if (selectTagFinish(requestCode, resultCode)) {
            String tag = intent.getStringExtra("tag");
            tvTag.setText(tag);
        }else if(requestCode ==Constant.REQUEST_CODE_CROP){
            Bundle bundle = intent.getExtras();
            if (bundle != null) {
                //在这里获得了剪裁后的Bitmap对象，可以用于上传
                Bitmap image = bundle.getParcelable("data");
                //设置到ImageView上
                imgIcon.setImageBitmap(image);
                //也可以进行一些保存、压缩等操作后上传
                String path = saveImage("crop", image);
                if(TextUtils.isEmpty(path)){
                    toast("保存失败");
                    return;
                }
                showLoadingDialog();
                File file = new File(path);
                BmobFile bmobFile = new BmobFile(file);
                bmobFile.uploadblock(new UploadFileListener() {
                    @Override
                    public void done(BmobException e) {
                        hideLoadingDialog();
                        if(e==null){
                            imgUrl = e.getMessage();
                        }else {

                        }
                    }
                });
            }
        }
    }

    public String saveImage(String name, Bitmap bmp) {
        File appDir = new File(Environment.getExternalStorageDirectory().getPath());
        if (!appDir.exists()) {
            appDir.mkdir();
        }
        String fileName = name + ".jpg";
        File file = new File(appDir, fileName);
        try {
            FileOutputStream fos = new FileOutputStream(file);
            bmp.compress(Bitmap.CompressFormat.PNG, 100, fos);
            fos.flush();
            fos.close();
            return file.getAbsolutePath();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 裁剪图片
     */
    private void cropPhoto(Uri uri) {
        Intent intent = new Intent("com.android.camera.action.CROP");
        intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
        intent.addFlags(Intent.FLAG_GRANT_WRITE_URI_PERMISSION);
        intent.setDataAndType(uri, "image/*");
        intent.putExtra("crop", "true");
        intent.putExtra("aspectX", 1);
        intent.putExtra("aspectY", 1);
        intent.putExtra("outputX", 300);
        intent.putExtra("outputY", 300);
        intent.putExtra("return-data", true);
        startActivityForResult(intent, Constant.REQUEST_CODE_CROP);
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

    private int imgRes;
    private String remarkName;
    private String wxNickname;

    private void save() {
        wxNickname = edWXNickname.getText().toString();
        remarkName = edRemarkName.getText().toString();
        if (TextUtils.isEmpty(wxNickname)) {
            toast("请输入微信昵称");
            return;
        }
        Contact contact = new Contact();
        contact.setWechatNickName(wxNickname);
        contact.setRemarkName(remarkName);
        contact.setIconType(iconType);
        contact.setIconRes(imgRes);
        contact.setIconUrl(imgUrl);
        String mobile = (String) SPUtils.get(getThis(), Constant.KEY_MOBILE, "");
        contact.setPointToUser(mobile);
        contact.setAvatarUri(avatarUri);
//        contact.save();

        ContractItem item = new ContractItem();
        item.setName(wxNickname);
        item.setImgRes(imgRes);
        item.setType(ContractAdapter.ITEM_CONTRACT_TYPE);
        item.setPointToUser(mobile);
        item.setImgType(avatarType);
        item.setAvatarUri(avatarUri);
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
