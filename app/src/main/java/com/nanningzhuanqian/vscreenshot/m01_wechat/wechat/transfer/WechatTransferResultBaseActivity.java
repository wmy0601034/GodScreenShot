package com.nanningzhuanqian.vscreenshot.m01_wechat.wechat.transfer;

import android.net.Uri;
import android.os.Build;
import android.text.TextUtils;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import com.nanningzhuanqian.vscreenshot.R;
import com.nanningzhuanqian.vscreenshot.base.BaseActivity;
import com.nanningzhuanqian.vscreenshot.base.util.SPUtils;
import com.nanningzhuanqian.vscreenshot.common.Constant;

/**
 * 微信转账结果状态 基类
 * Created by lenovo on 2018/10/7.
 */

public abstract class WechatTransferResultBaseActivity extends BaseActivity {

    public static final int RESULT_SCAN_PERSON = 0; //扫码转账成功 个人
    public static final int RESULT_SCAN_MERCHAIN = 1;   //扫码转账成功 商户
    public static final int RESULT_PAY_SUCCESS = 2; //付款成功
    public static final int RESULT_WAIT_OTHER = 3;    //待对方确认收款
    public static final int RESULT_OTHER_RECEIVED = 4;  //对方已收钱
    public static final int RESULT_WAIT_SELF = 5;   //待我确认收款
    public static final int RESULT_SELF_RECEIVED = 6;   //我已收钱
    public static final int RESULT_SELF_RETURN = 7; //我已退还
    public static final int RESULT_OTHER_RETURN = 8;    //对方已退还

    private TextView tvAmount;
    private ImageView imgAvatar;
    public int avatarRes;
    public Uri avatarUri;
    public String name;
    public String amount;
    private TextView tvReceiver;
    private TextView tvName;
    private TextView tvTime;
    private TextView tvTime1;

    private TextView tvFinish;

    @Override
    protected boolean needNavigationBartransparent() {
        return true;
    }

    public void initFinishButton(){
        tvFinish = findViewById(R.id.tvFinish);
        if(tvFinish!=null){
            tvFinish.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    finish();
                }
            });
        }
    }

    public void transparentNavigationBar(int color){
//        mImmersionBar.transparentNavigationBar();
//        Log.i(TAG,"transparentNavigationBar");
//        mImmersionBar.navigationBarColor(color); //导航栏颜色，不写默认黑色
        try {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                Window window = getWindow();
                window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
                //底部导航栏
                window.setNavigationBarColor(getResources().getColor(R.color.white));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void initAvatarRes(){
        imgAvatar = findViewById(R.id.imgAvatar);
        String type = (String) SPUtils.get(getThis(),Constant.KEY_WECHAT_TRANSFER_AVATAR_TYPE,"");
        String avatar = (String) SPUtils.get(getThis(), Constant.KEY_TRANSFER_AVATAR,"");
        if(TextUtils.isEmpty(type)||Constant.VALUE_PIC_RES.equals(type)){
            if(TextUtils.isEmpty(avatar)){
                avatarRes = R.mipmap.app_images_defaultface;
            }else{
                avatarRes = Integer.valueOf(avatar);
            }

            if(imgAvatar!=null){
                imgAvatar.setImageResource(avatarRes);
            }
        }else if(Constant.VALUE_PIC_LOCAL.equals(type)){
            if(TextUtils.isEmpty(avatar)){
                avatarRes = R.mipmap.app_images_defaultface;
                if(imgAvatar!=null){
                    imgAvatar.setImageResource(avatarRes);
                }
            }else{
                avatarUri = Uri.parse(avatar);
                if(imgAvatar!=null){
                    imgAvatar.setImageURI(avatarUri);
                }
            }

        }
    }

    public void initName(){
        name = (String)SPUtils.get(getThis(),Constant.KEY_TRANSFER_NAME,"");
    }

    public void initAmount(){
        tvAmount = findViewById(R.id.tvAmount);
        amount = getIntent().getStringExtra(Constant.INTENT_KEY_AMOUNT);
        if(tvAmount!=null){
            tvAmount.setText(amount);
        }
    }

    public void setName(String name){
        tvName = findViewById(R.id.tvName);
        if (tvName!=null){
            tvName.setText(name);
        }
    }

    public void initReceiver(){
        tvName = findViewById(R.id.tvName);
        if (tvName!=null){
            tvName.setText(name);
        }
    }

    public void setTime(String time){
        tvTime = findViewById(R.id.tvTime);
        if(tvTime!=null){
            tvTime.setText(time);
        }
    }

    public void setTime1(String time1){
        tvTime1 = findViewById(R.id.tvTime1);
        if(tvTime1!=null){
            tvTime1.setText(time1);
        }
    }

}