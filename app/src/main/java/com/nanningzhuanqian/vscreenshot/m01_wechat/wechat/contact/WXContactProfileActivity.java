package com.nanningzhuanqian.vscreenshot.m01_wechat.wechat.contact;

import android.content.Intent;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.makeramen.roundedimageview.RoundedImageView;
import com.nanningzhuanqian.vscreenshot.R;
import com.nanningzhuanqian.vscreenshot.base.BaseActivity;
import com.nanningzhuanqian.vscreenshot.base.bean.Contact;
import com.nanningzhuanqian.vscreenshot.base.bean.Contacts;
import com.nanningzhuanqian.vscreenshot.common.Constant;
import com.nanningzhuanqian.vscreenshot.m01_wechat.custom.EditCustomContactActivity;
import com.nanningzhuanqian.vscreenshot.m01_wechat.wechat.chat.WechatMediaChatActivity;
import com.nanningzhuanqian.vscreenshot.m01_wechat.wechat.chat.WechatSingleChatActivity;
import com.nanningzhuanqian.vscreenshot.m01_wechat.wechat.moment.WxMomentVisitorActivity;
import com.nanningzhuanqian.vscreenshot.m01_wechat.wechat.tag.WxContactTagListActivity;
import com.squareup.picasso.Picasso;

/**
 * Created by lenovo on 2019/1/20.
 */

public class WXContactProfileActivity extends BaseActivity implements View.OnClickListener {
    private LinearLayout llBack;
    private ImageButton btnMore;
    private RoundedImageView imgAvatar;
    private ImageView imgGender;
    private ImageView imgAvoidLook;
    private ImageView imgLimitLook;
    private ImageView imgStar;
    private TextView tvRemarkName;
    private TextView tvWxNickname;
    private TextView tvWxAccount;
    private TextView tvWxRegion;
    private TextView tvTag;
    private RelativeLayout rlTag;
    private RelativeLayout rlMoment;
    private ImageView imgMoment1;
    private ImageView imgMoment2;
    private ImageView imgMoment3;
    private ImageView imgMoment4;
    private RelativeLayout rlMoreInfo;
    private LinearLayout llSendMessage;
    private LinearLayout llMediaCall;
    private RelativeLayout rlMobile;
    private TextView tvMobile;
    private View line1;

    private int allPos;
    private int subPos;

    private Contact contact;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_wx_contact_profile;
    }

    @Override
    protected void initView() {
        llBack = (LinearLayout) findViewById(R.id.llBack);
        btnMore = (ImageButton) findViewById(R.id.btnMore);
        imgAvatar = (RoundedImageView) findViewById(R.id.imgAvatar);
        imgGender = (ImageView) findViewById(R.id.imgGender);
        imgAvoidLook = (ImageView) findViewById(R.id.imgAvoidLook);
        imgLimitLook = (ImageView) findViewById(R.id.imgLimitLook);
        imgStar = (ImageView) findViewById(R.id.imgStar);
        tvRemarkName = (TextView) findViewById(R.id.tvRemarkName);
        tvWxNickname = (TextView) findViewById(R.id.tvWxNickname);
        tvWxAccount = (TextView) findViewById(R.id.tvWxAccount);
        tvWxRegion = (TextView) findViewById(R.id.tvWxRegion);
        tvTag = (TextView) findViewById(R.id.tvTag);
        rlTag = (RelativeLayout) findViewById(R.id.rlTag);
        rlMoment = (RelativeLayout) findViewById(R.id.rlMoment);
        imgMoment1 = (ImageView) findViewById(R.id.imgMoment1);
        imgMoment2 = (ImageView) findViewById(R.id.imgMoment2);
        imgMoment3 = (ImageView) findViewById(R.id.imgMoment3);
        imgMoment4 = (ImageView) findViewById(R.id.imgMoment4);
        rlMoreInfo = (RelativeLayout) findViewById(R.id.rlMoreInfo);
        llSendMessage = (LinearLayout) findViewById(R.id.llSendMessage);
        llMediaCall = (LinearLayout) findViewById(R.id.llMediaCall);
        rlMobile = (RelativeLayout) findViewById(R.id.rlMobile);
        tvMobile = (TextView)findViewById(R.id.tvMobile);
        line1 = findViewById(R.id.line1);
    }

    @Override
    protected void initEvent() {
        llBack.setOnClickListener(this);
        btnMore.setOnClickListener(this);
        imgAvatar.setOnClickListener(this);
        rlTag.setOnClickListener(this);
        rlMoment.setOnClickListener(this);
        rlMoreInfo.setOnClickListener(this);
        llSendMessage.setOnClickListener(this);
        llMediaCall.setOnClickListener(this);
    }

    @Override
    protected void initData() {
        allPos = getIntent().getIntExtra(Constant.INTENT_KEY_CONTACT_ALL_POS,4);
        subPos = getIntent().getIntExtra(Constant.INTENT_KEY_CONTACT_SUB_POS,0);
        if(Contacts.getInstance().size()<5){
            toast(getString(R.string.wx_internal_error));
            finish();
            return;
        }
        contact = Contacts.getInstance().get(allPos);
        setContactInfo();
    }

    private int fromType;
    private String wxMobile;
    private String personalitySign;
    private int gender;
    private int commonGroup;
    private String tag;
    private void setContactInfo(){
        if(contact==null){
            toast(getString(R.string.wx_internal_error));
            finish();
            return;
        }
        String wechatNickName = contact.getWechatNickName();
        String remarkName = contact.getRemarkName();
        String wechatAccount = contact.getWechatAccount();
        String wechatAddress = contact.getWechatAddress();
        fromType = contact.getFromType();
        wxMobile = contact.getMobile();
        personalitySign = contact.getPersonalitySign();
        int iconType = contact.getIconType();
        int iconRes = contact.getIconRes();
        String iconUrl = contact.getIconUrl();
        gender = contact.getGender();
        commonGroup = contact.getCommonGroup();
        tag = contact.getTag();
        tvRemarkName.setText(remarkName);
        tvWxNickname.setText(getString(R.string.wx_profile_nickname,wechatNickName));
        tvWxAccount.setText(getString(R.string.wx_profile_account,wechatAccount));
        tvWxRegion.setText(getString(R.string.wx_profile_region,wechatAddress));
        if(TextUtils.isEmpty(wxMobile)){
            line1.setVisibility(View.GONE);
            rlMobile.setVisibility(View.GONE);
        }else{
            rlMobile.setVisibility(View.VISIBLE);
            line1.setVisibility(View.VISIBLE);
            tvMobile.setText(wxMobile);
        }
        tvTag.setText(tag);
        if(iconType==Contact.ICON_TYPE_RESOURCE){
            imgAvatar.setImageResource(iconRes);
        }else if(iconType == Contact.ICON_TYPE_NETWORK){
            Picasso.with(getThis())
                    .load(iconUrl)
                    .error(R.mipmap.app_images_defaultface)
                    .placeholder(R.mipmap.app_images_defaultface)
                    .into(imgAvatar);
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.llBack:
                finish();
                break;
            case R.id.btnMore:
                Intent intent = new Intent(getThis(),EditCustomContactActivity.class);
                intent.putExtra(Constant.INTENT_KEY_CONTACT,contact);
                startActivityForResult(intent,Constant.REQUEST_CODE_EDIT_CONTACT);
                break;
            case R.id.imgAvatar:
                Intent intent1 = new Intent(getThis(),WxFullGraphActivity.class);
                startActivity(intent1);
                break;
            case R.id.rlTag:
//                Intent intent2 = new Intent(getThis(), WxContactSetTagRemarkActivity.class);
//                startActivity(intent2);
                break;
            case R.id.rlMoment:
                Intent intent3 = new Intent(getThis(),WxMomentVisitorActivity.class);
                startActivity(intent3);
                break;
            case R.id.rlMoreInfo:
                Intent intent4 = new Intent(getThis(),WXContactProfileMoreActivity.class);
                intent4.putExtra(Constant.INTENT_KEY_CONTACT_COMMON_GROUP,commonGroup);
                intent4.putExtra(Constant.INTENT_KEY_CONTACT_SIGNATURE,personalitySign);
                intent4.putExtra(Constant.INTENT_KEY_CONTACT_FROM,fromType);
                intent4.putExtra(Constant.INTENT_KEY_CONTACT_GENDER,gender);
                startActivity(intent4);
                break;
            case R.id.llSendMessage:
                Intent intent5 = new Intent(getThis(), WechatSingleChatActivity.class);
                startActivity(intent5);
                break;
            case R.id.llMediaCall:
                Intent intent6 = new Intent(getThis(),WechatMediaChatActivity.class);
                startActivity(intent6);
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent intent) {
        super.onActivityResult(requestCode, resultCode, intent);
        Log.i(TAG,"onActivityResult "+requestCode+" "+resultCode);
        if(requestCode==Constant.REQUEST_CODE_EDIT_CONTACT&&resultCode==Constant.RESULT_CODE_SUCCESS){
            contact = (Contact) intent.getSerializableExtra(Constant.INTENT_KEY_CONTACT);
            Log.i(TAG,"onActivityResult "+contact.toString());
            setContactInfo();
        }
    }
}
