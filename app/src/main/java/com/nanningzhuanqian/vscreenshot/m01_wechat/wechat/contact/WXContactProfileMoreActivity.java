package com.nanningzhuanqian.vscreenshot.m01_wechat.wechat.contact;

import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.nanningzhuanqian.vscreenshot.R;
import com.nanningzhuanqian.vscreenshot.base.BaseActivity;
import com.nanningzhuanqian.vscreenshot.base.bean.Contact;
import com.nanningzhuanqian.vscreenshot.common.Constant;


/**
 * 微信联系人资料 更多信息页面
 */
public class WXContactProfileMoreActivity extends BaseActivity {

    private LinearLayout llBack;
    private LinearLayout llCommonGroup;
    private TextView tvCommonGroupDes;
    private TextView tvCommonGroup;
    private TextView tvSignature;
    private TextView tvFrom;
    private View line1;
    private LinearLayout llFrom;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_wx_contact_profile_more;
    }

    @Override
    protected void initView() {
        llBack = (LinearLayout) findViewById(R.id.llBack);
        llCommonGroup = (LinearLayout) findViewById(R.id.llCommonGroup);
        tvCommonGroupDes = (TextView) findViewById(R.id.tvCommonGroupDes);
        tvCommonGroup = (TextView) findViewById(R.id.tvCommonGroup);
        tvSignature = (TextView) findViewById(R.id.tvSignature);
        tvFrom = (TextView) findViewById(R.id.tvFrom);
        line1 = findViewById(R.id.line1);
        llFrom = (LinearLayout)findViewById(R.id.llFrom);
    }

    @Override
    protected void initEvent() {
        llBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    @Override
    protected void initData() {
        int commonGroup = getIntent().getIntExtra(Constant.INTENT_KEY_CONTACT_COMMON_GROUP, 0);
        int gender = getIntent().getIntExtra(Constant.INTENT_KEY_CONTACT_GENDER, Contact.GENDER_MALE);
        String signature = getIntent().getStringExtra(Constant.INTENT_KEY_CONTACT_SIGNATURE);
        int fromType = getIntent().getIntExtra(Constant.INTENT_KEY_CONTACT_FROM, Contact.FROM_QQ);
        switch (gender) {
            case Contact.GENDER_MALE:
                tvCommonGroupDes.setText(getString(R.string.wx_common_group_male));
                break;
            case Contact.GENDER_FEMALE:
                tvCommonGroupDes.setText(getString(R.string.wx_common_group_female));
                break;
            case Contact.GENDER_PRIVATE:
                tvCommonGroupDes.setText(getString(R.string.wx_common_group_private));
                break;
        }
        tvCommonGroup.setText(getString(R.string.wx_ge_unit,commonGroup));
        tvSignature.setText(signature);
        line1.setVisibility(View.VISIBLE);
        llFrom.setVisibility(View.VISIBLE);
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
                line1.setVisibility(View.GONE);
                llFrom.setVisibility(View.GONE);
                break;
        }
    }
}
