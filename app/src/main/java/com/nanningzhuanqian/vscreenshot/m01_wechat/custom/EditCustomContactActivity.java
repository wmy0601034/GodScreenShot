package com.nanningzhuanqian.vscreenshot.m01_wechat.custom;

import android.Manifest;
import android.app.Dialog;
import android.content.Intent;
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
import com.nanningzhuanqian.vscreenshot.base.BaseActivity;
import com.nanningzhuanqian.vscreenshot.base.Util;
import com.nanningzhuanqian.vscreenshot.base.bean.Contact;
import com.nanningzhuanqian.vscreenshot.base.bean.Contacts;
import com.nanningzhuanqian.vscreenshot.base.bean.TagsCur;
import com.nanningzhuanqian.vscreenshot.base.util.DBManager;
import com.nanningzhuanqian.vscreenshot.base.util.PermissionUtils;
import com.nanningzhuanqian.vscreenshot.base.util.SPUtils;
import com.nanningzhuanqian.vscreenshot.common.Constant;
import com.nanningzhuanqian.vscreenshot.item.NetworkAvatar;
import com.nanningzhuanqian.vscreenshot.m00_base.LocalAvatarSelectActivity;
import com.nanningzhuanqian.vscreenshot.m00_base.NetworkAvatarSelectActivity;
import com.nanningzhuanqian.vscreenshot.m01_wechat.wechat.person.WXRegionSelectionActivity;
import com.nanningzhuanqian.vscreenshot.model.RandomManager;
import com.nanningzhuanqian.vscreenshot.widget.NewActionSheetDialog;
import com.nanningzhuanqian.vscreenshot.widget.PicassoImageLoader;
import com.squareup.picasso.Picasso;

import java.io.File;
import java.util.ArrayList;
import java.util.Map;

import cn.bmob.v3.datatype.BmobFile;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.SaveListener;
import cn.bmob.v3.listener.UploadFileListener;

/**
 * 修改自定义的联系人资料
 */
public class EditCustomContactActivity extends BaseActivity implements View.OnClickListener {
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

    private int id;
    private int imgRes;
    private String remarkName;
    private String wxNickname;
    private String wxAccount;
    private int gender = Contact.GENDER_PRIVATE;
    private String wxAddress;
    private String wxMobile = "18888888888";
    private String wxSignature;
    private int commonGroup = 0;
    private String tag;
    private int fromType = 0;
    private String imgUrl = "";
    private int iconType = Contact.ICON_TYPE_RESOURCE;

    private Contact contact;


    @Override
    protected int getLayoutId() {
        return R.layout.activity_add_custom_role_new;
    }

    protected void initView() {
        tvBack = (TextView) findViewById(R.id.tvBack);
        tvTitle = (TextView) findViewById(R.id.tvTitle);
        tvSubmit = (TextView) findViewById(R.id.tvSubmit);
        tvSubmit.setVisibility(View.GONE);
        llAvatar = (LinearLayout) findViewById(R.id.llAvatar);
        imgIcon = (ImageView) findViewById(R.id.imgIcon);
        edWXNickname = (EditText) findViewById(R.id.edWXNickname);
        edRemarkName = (EditText) findViewById(R.id.edRemarkName);
        edWechatAccount = (EditText) findViewById(R.id.edWechatAccount);
        tvGender = (TextView) findViewById(R.id.tvGender);
        llAddress = (LinearLayout) findViewById(R.id.llAddress);
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
        tvTitle.setText("编辑自定义联系人");
        contact = (Contact) getIntent().getSerializableExtra(Constant.INTENT_KEY_CONTACT);
        setContactInfo();
    }

    private void setContactInfo(){
        if(contact==null){
            toast(getString(R.string.wx_internal_error));
            finish();
            return;
        }
        id = contact.getId();
        String wechatNickName = contact.getWechatNickName();
        String remarkName = contact.getRemarkName();
        String wechatAccount = contact.getWechatAccount();
        String wechatAddress = contact.getWechatAddress();
        fromType = contact.getFromType();
        wxMobile = contact.getMobile();
        wxSignature = contact.getPersonalitySign();
        int iconType = contact.getIconType();
        int iconRes = contact.getIconRes();
        String iconUrl = contact.getIconUrl();
        gender = contact.getGender();
        commonGroup = contact.getCommonGroup();
        tag = contact.getTag();
        edRemarkName.setText(remarkName);
        edWXNickname.setText(getString(R.string.wx_profile_nickname,wechatNickName));
        edWechatAccount.setText(getString(R.string.wx_profile_account,wechatAccount));
        tvAddress.setText(getString(R.string.wx_profile_region,wechatAddress));
        if(!TextUtils.isEmpty(mobile)){
            edMobile.setText(mobile);
        }
        tvTag.setText(tag);
        if(iconType==Contact.ICON_TYPE_RESOURCE){
            imgIcon.setImageResource(iconRes);
        }else if(iconType == Contact.ICON_TYPE_NETWORK){
            Picasso.with(getThis())
                    .load(iconUrl)
                    .error(R.mipmap.app_images_defaultface)
                    .placeholder(R.mipmap.app_images_defaultface)
                    .into(imgIcon);
        }else {
            imgIcon.setImageResource(R.mipmap.app_images_defaultface);
        }
        edCommonGroup.setText(String.valueOf(commonGroup));
        if(gender==Contact.GENDER_MALE){
            tvGender.setText(getString(R.string.male));
        }else if(gender==Contact.GENDER_FEMALE){
            tvGender.setText(getString(R.string.female));
        }else{
            tvGender.setText(getString(R.string.no_public_show));
        }
        edSignatrue.setText(wxSignature);
        switch (fromType) {
            case Contact.FROM_QQ:
                tvFrom.setText(getString(R.string.from_qq));
                break;
            case Contact.FROM_MOBILE:
                tvFrom.setText(getString(R.string.from_mobile));
                break;
            case Contact.FROM_GROUP:
                tvFrom.setText(getString(R.string.from_group));
                break;
            case Contact.FROM_PHONE_CONTACT:
                tvFrom.setText(getString(R.string.from_phone_contact));
                break;
            case Contact.FROM_QRCODE:
                tvFrom.setText(getString(R.string.from_qrcode));
                break;
            case Contact.FROM_CARD_SHARE:
                tvFrom.setText(getString(R.string.from_card_share));
                break;
            case Contact.FROM_NEAR:
                tvFrom.setText(getString(R.string.from_near));
                break;
            case Contact.FROM_SHAKE:
                tvFrom.setText(getString(R.string.from_shake));
                break;
            case Contact.FROM_DRIFT:
                tvFrom.setText(getString(R.string.from_drift));
                break;
            case Contact.FROM_NONE:

                break;
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tvBack:
                finish();
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
        NewActionSheetDialog.Builder builder = new NewActionSheetDialog.Builder(EditCustomContactActivity.this);

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
                checkCamera();
            }
        });
        builder.addSheetItem("相册", NewActionSheetDialog
                .SheetItemColor.Blue, new NewActionSheetDialog.Builder
                .OnSheetItemClickListener() {
            @Override
            public void onClick(int which) {
                //相册
                checkStorage(false);
            }
        });
        builder.addSheetItem("头像库", NewActionSheetDialog
                .SheetItemColor.Blue, new NewActionSheetDialog.Builder
                .OnSheetItemClickListener() {
            @Override
            public void onClick(int which) {
                Intent intent = new Intent(EditCustomContactActivity.this, LocalAvatarSelectActivity.class);
                startActivityForResult(intent, Constant.REQUEST_CODE_SELECT_LOCAL_AVATAR);
            }
        });
        builder.addSheetItem("在线头像库", NewActionSheetDialog
                .SheetItemColor.Blue, new NewActionSheetDialog.Builder
                .OnSheetItemClickListener() {
            @Override
            public void onClick(int which) {
                //进入在线头像库
                Intent intent = new Intent(EditCustomContactActivity.this, NetworkAvatarSelectActivity.class);
                startActivityForResult(intent, Constant.REQUEST_CODE_SELECT_NETWORK_AVATAR);
            }
        });
        Dialog dialog = builder.create();
        dialog.show();
    }

    private void selectAddress() {
        Intent intent = new Intent(getThis(), WXRegionSelectionActivity.class);
        startActivityForResult(intent, Constant.REQUEST_CODE_SELECT_WX_REGION);
    }

    private void showFromSelection() {
        NewActionSheetDialog.Builder builder = new NewActionSheetDialog.Builder(EditCustomContactActivity.this);

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
        NewActionSheetDialog.Builder builder = new NewActionSheetDialog.Builder(EditCustomContactActivity.this);
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
        Contact contact = RandomManager.getInstance().getRandomContact(getThis());
        wxNickname = contact.getWechatNickName();
        remarkName = contact.getRemarkName();
        wxAccount = contact.getWechatAccount();
        wxAddress = contact.getWechatAddress();
        wxMobile = contact.getMobile();
        wxSignature = contact.getPersonalitySign();
        iconType = contact.getIconType();
        imgRes = contact.getIconRes();
        gender = contact.getGender();
        fromType = contact.getFromType();
        commonGroup = contact.getCommonGroup();
        tag = contact.getTag();
        edWXNickname.setText(wxNickname);
        edRemarkName.setText(remarkName);
        edWechatAccount.setText(wxAccount);
        tvAddress.setText(wxAddress);
        edMobile.setText(wxMobile);
        edSignatrue.setText(wxSignature);
        imgIcon.setImageResource(imgRes);
        switch (gender) {
            case Contact.GENDER_MALE:
                tvGender.setText(getString(R.string.male));
                break;
            case Contact.GENDER_FEMALE:
                tvGender.setText(getString(R.string.female));
                break;
            case Contact.GENDER_PRIVATE:
                tvGender.setText(getString(R.string.no_public_show));
                break;
        }
        switch (fromType) {
            case Contact.FROM_QQ:
                tvFrom.setText(getString(R.string.from_qq));
                break;
            case Contact.FROM_MOBILE:
                tvFrom.setText(getString(R.string.from_mobile));
                break;
            case Contact.FROM_GROUP:
                tvFrom.setText(getString(R.string.from_group));
                break;
            case Contact.FROM_PHONE_CONTACT:
                tvFrom.setText(getString(R.string.from_phone_contact));
                break;
            case Contact.FROM_QRCODE:
                tvFrom.setText(getString(R.string.from_qrcode));
                break;
            case Contact.FROM_CARD_SHARE:
                tvFrom.setText(getString(R.string.from_card_share));
                break;
            case Contact.FROM_NEAR:
                tvFrom.setText(getString(R.string.from_near));
                break;
            case Contact.FROM_SHAKE:
                tvFrom.setText(getString(R.string.from_shake));
                break;
            case Contact.FROM_DRIFT:
                tvFrom.setText(getString(R.string.from_drift));
                break;
            case Contact.FROM_NONE:

                break;
        }
        edCommonGroup.setText(commonGroup+"");
        tvTag.setText(tag);
    }

    private void checkCamera() {
        if (checkingPermission) {
            return;
        }
        //运行时权限申请
        PermissionUtils.requestPermissions(this, new PermissionUtils.OnRequestPermissionListener() {
            @Override
            public void onResult(boolean granted, Map<String, Boolean> denied) {
                if (granted) {
                    //拍照需要摄像头权限
                    PermissionUtils.requestPermissions(getThis(), new PermissionUtils.OnRequestPermissionListener() {
                        @Override
                        public void onResult(boolean granted, Map<String, Boolean> denied) {
//                            if(BuildConfig.DEBUG) Log.e("requestPermissions","denied: "+denied);
                            if (granted || denied.size() <= 1) {
                                checkingPermission = false;
                                checkStorage(true);
                            } else {
                                checkingPermission = false;
                                permissionDeniedDialog(R.string.open_permission, R.string.open_camera_permission);
                            }
                        }
                    }, Manifest.permission.CAMERA);
                } else {
                    checkingPermission = false;
                    permissionDeniedDialog(R.string.open_permission, R.string.open_camera_permission);
                }
            }
        }, Manifest.permission.CAMERA);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        PermissionUtils.onRequestPermissionsResult(requestCode, permissions, grantResults);
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    private void checkStorage(final boolean takePhoto) {
        if (checkingPermission) {
            return;
        }
        //运行时权限申请
        PermissionUtils.requestPermissions(this, new PermissionUtils.OnRequestPermissionListener() {
            @Override
            public void onResult(boolean granted, Map<String, Boolean> denied) {
                if (granted) {
                    //需要存储权限
                    PermissionUtils.requestPermissions(getThis(), new PermissionUtils.OnRequestPermissionListener() {
                        @Override
                        public void onResult(boolean granted, Map<String, Boolean> denied) {
//                            if(BuildConfig.DEBUG) Log.e("requestPermissions","denied: "+denied);
                            if (granted || denied.size() <= 1) {
                                checkingPermission = false;
                                if (takePhoto) {
                                    goCamera();
                                } else {
                                    goPickPictrue();
                                }
                            } else {
                                checkingPermission = false;
                                permissionDeniedDialog(R.string.open_permission, R.string.open_storage_permission);
                            }
                        }
                    }, Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE);
                } else {
                    checkingPermission = false;
                    permissionDeniedDialog(R.string.open_permission, R.string.open_storage_permission);
                }
            }
        }, Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE);
    }

    private void goCamera() {
        Intent intent = new Intent(this, ImageGridActivity.class);
        intent.putExtra(ImageGridActivity.EXTRAS_TAKE_PICKERS, true); // 是否是直接打开相机
        startActivityForResult(intent, Constant.REQUEST_CODE_SELECT_AVATAR_BY_CAMERA);
    }

    private void goPickPictrue() {
        Intent intent = new Intent(EditCustomContactActivity.this, ImageGridActivity.class);
        startActivityForResult(intent, Constant.REQUEST_CODE_SELECT_LOCAL_AVATAR);
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
                    uploadImage(path);
                } else {

                }
            } else if (requestCode == Constant.REQUEST_CODE_SELECT_AVATAR_BY_CAMERA) {
                Log.i(TAG, "intent != null " + (intent != null));
                ArrayList<ImageItem> images = (ArrayList<ImageItem>) intent.getSerializableExtra(ImagePicker.EXTRA_RESULT_ITEMS);
                if (images != null) {
                    ImageItem imageItem = images.get(0);
                    String path = imageItem.path;
                    uploadImage(path);
                } else {
                    Log.i(TAG, "images = null ");
                }

            } else {
                Log.i(TAG, "没有数据");
            }
        } else if (selectLocalAvatarFinish(requestCode, resultCode)) {
            imgRes = intent.getIntExtra("imgRes", R.mipmap.app_images_defaultface);
            iconType = Contact.ICON_TYPE_RESOURCE;
            imgIcon.setImageResource(imgRes);
        } else if (selectNetworkAvatarFinish(requestCode, resultCode)) {
            imgUrl = intent.getStringExtra("imgUrl");
            iconType = Contact.ICON_TYPE_NETWORK;
            Picasso.with(EditCustomContactActivity.this)
                    .load(imgUrl)
                    .into(imgIcon);
        } else if (selectTagFinish(requestCode, resultCode)) {
            String tag = intent.getStringExtra("tag");
            tvTag.setText(tag);
        } else if (requestCode == Constant.REQUEST_CODE_SELECT_WX_REGION) {
            if (resultCode == Constant.RESULT_CODE_SUCCESS) {
                String address = intent.getStringExtra(Constant.INTENT_KEY_ADDRESS);
                tvAddress.setText(address);
            } else {

            }
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
                    Picasso.with(EditCustomContactActivity.this)
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
        if (TextUtils.isEmpty(edCommonGroup.getText().toString())) {
            commonGroup = 0;
        }
        commonGroup = Integer.valueOf(edCommonGroup.getText().toString());
        tag = tvTag.getText().toString();
        if (TextUtils.isEmpty(wxNickname)) {
            toast("请输入微信昵称");
            return;
        }
        if (TextUtils.isEmpty(remarkName)) {
            if (Contacts.getInstance().contains("wechatNickName", wxNickname)) {
                toast(getString(R.string.wx_nickname_exists));
                return;
            }
            remarkName = wxNickname;
        } else {
            if (Contacts.getInstance().contains("remarkName", remarkName)) {
                toast(getString(R.string.wx_remark_exists));
                return;
            }
        }
        Log.i(TAG, "wxAccount " + wxAccount);
        boolean checkWxAccount = Util.checkWxAccount(wxAccount);
        if (!checkWxAccount) {
            toast(getString(R.string.wx_account_not_right));
            return;
        }
        boolean checkMobile = Util.isPhone(wxMobile);
        if (!checkMobile) {
            toast(getString(R.string.mobile_not_right));
            return;
        }

        Contact contact = new Contact();
        contact.setId(id);
        contact.setWechatNickName(wxNickname);
        contact.setRemarkName(remarkName);
        contact.setWechatAccount(wxAccount);
        contact.setWechatAddress(wxAddress);
        contact.setMobile(wxMobile);
        contact.setPersonalitySign(wxSignature);
        contact.setIconType(iconType);
        contact.setIconRes(imgRes);
        contact.setIconUrl(imgUrl);
        contact.setGender(gender);
        contact.setFromType(fromType);
        contact.setCommonGroup(commonGroup);
        contact.setTag(tag);
        String mobile = (String) SPUtils.get(getThis(), Constant.KEY_MOBILE, "");
        contact.setPointToUser(mobile);
        int result = DBManager.updateContact(getApplicationContext(), contact);
        Log.i(TAG,"save "+id+" "+result+" "+contact.toString());
        Intent intent = new Intent();
        intent.putExtra(Constant.INTENT_KEY_CONTACT,contact);
        setResult(Constant.RESULT_CODE_SUCCESS,intent);
        finish();
    }

}