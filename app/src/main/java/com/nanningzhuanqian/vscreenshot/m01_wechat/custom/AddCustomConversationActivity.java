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

import com.borax12.materialdaterangepicker.date.DatePickerDialog;
import com.borax12.materialdaterangepicker.time.RadialPickerLayout;
import com.borax12.materialdaterangepicker.time.TimePickerDialog;
import com.lzy.imagepicker.ImagePicker;
import com.lzy.imagepicker.bean.ImageItem;
import com.lzy.imagepicker.ui.ImageGridActivity;
import com.nanningzhuanqian.vscreenshot.R;
import com.nanningzhuanqian.vscreenshot.base.BaseActivity;
import com.nanningzhuanqian.vscreenshot.base.Util;
import com.nanningzhuanqian.vscreenshot.base.bean.Contact;
import com.nanningzhuanqian.vscreenshot.base.bean.Conversation;
import com.nanningzhuanqian.vscreenshot.base.bean.Conversations;
import com.nanningzhuanqian.vscreenshot.base.net.CallbackListener;
import com.nanningzhuanqian.vscreenshot.base.net.HttpUtil;
import com.nanningzhuanqian.vscreenshot.base.util.DBManager;
import com.nanningzhuanqian.vscreenshot.base.util.PermissionUtils;
import com.nanningzhuanqian.vscreenshot.base.util.SPUtils;
import com.nanningzhuanqian.vscreenshot.common.Constant;
import com.nanningzhuanqian.vscreenshot.item.NetworkAvatar;
import com.nanningzhuanqian.vscreenshot.m00_base.LocalAvatarSelectActivity;
import com.nanningzhuanqian.vscreenshot.m00_base.NetworkAvatarSelectActivity;
import com.nanningzhuanqian.vscreenshot.model.RandomManager;
import com.nanningzhuanqian.vscreenshot.widget.NewActionSheetDialog;
import com.squareup.picasso.Picasso;
import com.suke.widget.SwitchButton;

import java.io.File;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Map;

import cn.bmob.v3.datatype.BmobFile;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.SaveListener;
import cn.bmob.v3.listener.UploadFileListener;

/**
 * 添加自定义对话界面
 * Created by WMY on 2018/9/14.
 */

public class AddCustomConversationActivity extends BaseActivity implements View.OnClickListener {

    private TextView tvBack;
    private TextView tvTitle;
    private TextView tvSubmit;
    private LinearLayout llType;
    private LinearLayout llAvatar;
    private ImageView imgIcon;
    private EditText edName;
    private EditText edContent;
    //    private SwitchButton switchPublic;
    private SwitchButton switchIgnore;
//    private SwitchButton switchRedIndicator;
    private EditText edBadge;
    private LinearLayout llTime;
    private TextView tvTime;
    private Button btnRandom;
    private Button btnSubmit;
    private TextView tvType;
    private int type = Conversation.TYPE_SINGLE_CHAT;

    private long contactId;
    private Contact contact;

    private int imgType;
    private int imgRes;
    private String imgUrl;
    private String name;
    private String content;
    //    private boolean isPublic;
    private boolean isIgnore;
//    private boolean isBadge;
    private int badge;
    private long timeMillis;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_add_conversation;
    }

    protected void initView() {
        tvBack = (TextView) findViewById(R.id.tvBack);
        tvTitle = (TextView) findViewById(R.id.tvTitle);
        tvSubmit = (TextView) findViewById(R.id.tvSubmit);
        tvSubmit.setVisibility(View.GONE);
        llType = (LinearLayout) findViewById(R.id.llType);
        llAvatar = (LinearLayout) findViewById(R.id.llAvatar);
        imgIcon = (ImageView) findViewById(R.id.imgIcon);
        edName = (EditText) findViewById(R.id.edName);
        edContent = (EditText) findViewById(R.id.edContent);
//        switchPublic = (SwitchButton) findViewById(R.id.switchPublic);
        switchIgnore = (SwitchButton) findViewById(R.id.switchIgnore);
//        switchRedIndicator = (SwitchButton) findViewById(R.id.switchRedIndicator);
        edBadge = (EditText) findViewById(R.id.edBadge);
        llTime = (LinearLayout) findViewById(R.id.llTime);
        tvTime = (TextView) findViewById(R.id.tvTime);
        btnRandom = (Button) findViewById(R.id.btnRandom);
        btnSubmit = (Button) findViewById(R.id.btnSubmit);
    }

    protected void initEvent() {
        tvBack.setOnClickListener(this);
        llType.setOnClickListener(this);
        llAvatar.setOnClickListener(this);
        llTime.setOnClickListener(this);
        btnRandom.setOnClickListener(this);
        btnSubmit.setOnClickListener(this);
//        switchPublic.setOnCheckedChangeListener(new SwitchButton.OnCheckedChangeListener() {
//            @Override
//            public void onCheckedChanged(SwitchButton view, boolean isChecked) {
//                isPublic = isChecked;
//            }
//        });
        switchIgnore.setOnCheckedChangeListener(new SwitchButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(SwitchButton view, boolean isChecked) {
                isIgnore = isChecked;
            }
        });
//        switchRedIndicator.setOnCheckedChangeListener(new SwitchButton.OnCheckedChangeListener() {
//            @Override
//            public void onCheckedChanged(SwitchButton view, boolean isChecked) {
//                isBadge = isChecked;
//            }
//        });
    }

    protected void initData() {
        tvTitle.setText("添加自定义对话");
        randomCreate();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tvBack:
                finish();
                break;
            case R.id.llType:
                showTypeSelectionDialog();
                break;
            case R.id.llAvatar:
                showAvatarSheetDialog();
                break;
            case R.id.llTime:
                showDateDialog();
                break;
            case R.id.btnRandom:
                randomCreate();
                break;
            case R.id.btnSubmit:
                save();
                break;
        }
    }

    private void showTypeSelectionDialog() {
        NewActionSheetDialog.Builder builder = new NewActionSheetDialog.Builder(AddCustomConversationActivity.this);
        builder.setCancelable(false);
        builder.setCancelButtonVisiable(true);
        builder.setCanceledOnTouchOutside(true);
        builder.addSheetItem("单人聊天", NewActionSheetDialog.SheetItemColor.Blue, new NewActionSheetDialog.Builder.OnSheetItemClickListener() {
            @Override
            public void onClick(int which) {
                //选择单人聊天
                type = Conversation.TYPE_SINGLE_CHAT;
                tvType.setText("单人聊天");
            }
        });
        builder.addSheetItem("群聊", NewActionSheetDialog.SheetItemColor.Blue, new NewActionSheetDialog.Builder.OnSheetItemClickListener() {
            @Override
            public void onClick(int which) {
                //选择群聊
                type = Conversation.TYPE_GROUP_CHAT;
                tvType.setText("群聊");
            }
        });
        builder.addSheetItem("服务号", NewActionSheetDialog.SheetItemColor.Blue, new NewActionSheetDialog.Builder.OnSheetItemClickListener() {
            @Override
            public void onClick(int which) {
                //服务号
                type = Conversation.TYPE_WECHAT_SERVICE;
                tvType.setText("服务号");
            }
        });
        builder.addSheetItem("微信系统功能", NewActionSheetDialog.SheetItemColor.Blue, new NewActionSheetDialog.Builder.OnSheetItemClickListener() {
            @Override
            public void onClick(int which) {
                //微信系统功能
                type = Conversation.TYPE_WECHAT_SYSTEM;
                tvType.setText("微信系统功能");
            }
        });
        Dialog dialog = builder.create();
        dialog.show();
    }

    private void showAvatarSheetDialog() {
        NewActionSheetDialog.Builder builder = new NewActionSheetDialog.Builder(AddCustomConversationActivity.this);

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
                Intent intent = new Intent(AddCustomConversationActivity.this, LocalAvatarSelectActivity.class);
                startActivityForResult(intent, Constant.REQUEST_CODE_SELECT_LOCAL_AVATAR);
            }
        });
        builder.addSheetItem("在线头像库", NewActionSheetDialog
                .SheetItemColor.Blue, new NewActionSheetDialog.Builder
                .OnSheetItemClickListener() {
            @Override
            public void onClick(int which) {
                //进入在线头像库
                Intent intent = new Intent(AddCustomConversationActivity.this, NetworkAvatarSelectActivity.class);
                startActivityForResult(intent, Constant.REQUEST_CODE_SELECT_NETWORK_AVATAR);
            }
        });
        Dialog dialog = builder.create();
        dialog.show();
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
        Intent intent = new Intent(AddCustomConversationActivity.this, ImageGridActivity.class);
        startActivityForResult(intent, Constant.REQUEST_CODE_SELECT_LOCAL_AVATAR);
    }

    private String yyyy;
    private String MM;
    private String dd;

    private void showDateDialog() {
        Calendar now = Calendar.getInstance();
        DatePickerDialog dpd = DatePickerDialog.newInstance(
                new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePickerDialog dialog, int year, int monthOfYear, int dayOfMonth, int yearEnd, int monthOfYearEnd, int dayOfMonthEnd) {
                        Log.i(TAG, "year = " + year + " monthOfYear = " + monthOfYear + " dayOfMonth = " + dayOfMonth);
                        yyyy = String.valueOf(year);
                        MM = String.valueOf(monthOfYear + 1);
                        dd = String.valueOf(dayOfMonth);
                        dialog.dismiss();
                        showTimeDialog();
                    }

                },
                now.get(Calendar.YEAR),
                now.get(Calendar.MONTH),
                now.get(Calendar.DAY_OF_MONTH)
        );

        dpd.show(getFragmentManager(), "Datepickerdialog");
    }

    private String hh;
    private String mm;
    private String ss = "00";
    private String updateTime;

    private void showTimeDialog() {
        Calendar now = Calendar.getInstance();
        TimePickerDialog tpd = TimePickerDialog.newInstance(
                new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(RadialPickerLayout dialog, int hourOfDay, int minute, int hourOfDayEnd, int minuteEnd) {
                        hh = String.valueOf(hourOfDay);
                        mm = String.valueOf(minute);
                        updateTime = yyyy + "-" + MM + "-" + dd + " " + hh + ":" + mm + ":" + ss;
                        tvTime.setText(updateTime);
                        Log.i(TAG, "hourOfDay = " + hourOfDay + " minute = " + minute);
                    }
                },
                now.get(Calendar.HOUR_OF_DAY),
                now.get(Calendar.MINUTE),
                true
        );
        tpd.show(getFragmentManager(), "Timepickerdialog");
    }

    /**
     * 随机创建一个对话
     * 先随机创建一个联系人
     * 再随机生成对话的其他属性
     */
    private void randomCreate() {
        Conversation conversation = RandomManager.getInstance().getRandomConversation(getApplicationContext());
        contact = conversation.getContact();
        name = contact.getRemarkName();
        imgType = conversation.getIconType();
        imgRes = conversation.getIconRes();
        imgUrl = conversation.getIconUrl();
        content = conversation.getDisplayContent();
        isIgnore = conversation.isIgnore();
        badge = conversation.getBadgeCount();
        timeMillis = conversation.getUpdateTime();
        edName.setText(name);
        if(imgType==Contact.ICON_TYPE_RESOURCE){
            imgIcon.setImageResource(imgRes);
        }else if(imgType==Contact.ICON_TYPE_NETWORK){
            Picasso.with(getThis())
                    .load(imgUrl)
                    .error(R.mipmap.app_images_defaultface)
                    .placeholder(R.mipmap.app_images_defaultface)
                    .into(imgIcon);
        }else {
            imgIcon.setImageResource(R.mipmap.app_images_defaultface);
        }
        edContent.setText(content);
        switchIgnore.setChecked(isIgnore);
        edBadge.setText(String.valueOf(badge));
        updateTime = Util.timeStampToDate(timeMillis);
        tvTime.setText(updateTime);
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
            imgType = Contact.ICON_TYPE_RESOURCE;
            imgIcon.setImageResource(imgRes);
        } else if (selectNetworkAvatarFinish(requestCode, resultCode)) {
            imgUrl = intent.getStringExtra("imgUrl");
            imgType = Contact.ICON_TYPE_NETWORK;
            Picasso.with(AddCustomConversationActivity.this)
                    .load(imgUrl)
                    .into(imgIcon);
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
                    imgType = Contact.ICON_TYPE_NETWORK;
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
                    Picasso.with(AddCustomConversationActivity.this)
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

    private void save() {
        name = edName.getText().toString();
        if (TextUtils.isEmpty(name)) {
            toast("请输入昵称");
            return;
        }
        content = edContent.getText().toString();
        if (TextUtils.isEmpty(content)) {
            toast("请输入聊天内容");
            return;
        }

        String badgeCount = edBadge.getText().toString();
        if (TextUtils.isEmpty(badgeCount)) {
            badge = 0 ;
        } else {
            badge = Integer.valueOf(badgeCount);
        }
        String time = tvTime.getText().toString();
        if (TextUtils.isEmpty(time)) {
            toast("请选择时间");
            return;
        } else {
            timeMillis = getTimeMillis(time);
            Log.i(TAG, "timeMillis = " + timeMillis);
        }
        Conversation conversation = new Conversation();
        conversation.setContact(contact);
        conversation.setBadgeCount(badge);
        conversation.setName(name);
        conversation.setDisplayContent(content);
        conversation.setUpdateTime(timeMillis);
        conversation.setIconType(imgType);
        conversation.setIconRes(imgRes);
        conversation.setIgnore(isIgnore);
        String mobile = (String) SPUtils.get(getThis(), Constant.KEY_MOBILE, "");
        conversation.setPointToUser(mobile);
        long contactId = DBManager.saveContact(getApplicationContext(), contact);
        if (contactId == -1) {
            Log.i(TAG,"562 wx_internal_error");
            toast(getString(R.string.wx_internal_error));
            return;
        }
        conversation.setContactId(contactId);
        long conversationId = DBManager.saveConversation(getApplicationContext(), conversation);
        if (conversationId == -1) {
            Log.i(TAG,"562 wx_internal_error");
            toast(getString(R.string.wx_internal_error));
            return;
        }
        if(!isIgnore) {
            int conversationUnReadCount = (int) SPUtils.get(getThis(), Constant.KEY_CONVERSATION_UNREAD_COUNT, 0);
            int unreadCount = conversationUnReadCount + badge;
            SPUtils.put(getApplicationContext(), Constant.KEY_CONVERSATION_UNREAD_COUNT, unreadCount);
        }
        setResult(Constant.RESULT_CODE_SUCCESS);
        finish();
    }

    private long getTimeMillis(String time) {
        SimpleDateFormat sdf = new SimpleDateFormat("yy-MM-dd HH:mm:ss");
        Date d;
        try {
            d = sdf.parse(time);
            long l = d.getTime();
            return l;
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return System.currentTimeMillis();
    }


}
