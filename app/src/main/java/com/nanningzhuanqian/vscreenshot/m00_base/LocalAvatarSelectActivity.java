package com.nanningzhuanqian.vscreenshot.m00_base;

import android.content.Intent;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.TextView;

import com.nanningzhuanqian.vscreenshot.R;
import com.nanningzhuanqian.vscreenshot.adapter.AvatarAdapter;
import com.nanningzhuanqian.vscreenshot.base.BaseActivity;
import com.nanningzhuanqian.vscreenshot.base.util.SPUtils;
import com.nanningzhuanqian.vscreenshot.common.Constant;
import com.nanningzhuanqian.vscreenshot.item.LocalAvatarItem;
import com.nanningzhuanqian.vscreenshot.item.LocalAvatarItems;

/**
 * 头像选择页面
 * 根据传入的type 分辨类型 选择完毕后保存到不同的字段中
 */
public class LocalAvatarSelectActivity extends BaseActivity {

    private TextView tvBack;
    private TextView tvTitle;
    private GridView gvAvatar;
    private AvatarAdapter avatarAdapter;
    private String type;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_local_avatar_select;
    }

    protected void initView() {
        tvBack = (TextView)findViewById(R.id.tvBack);
        tvTitle = (TextView)findViewById(R.id.tvTitle);
        gvAvatar = (GridView)findViewById(R.id.gvAvatar);
        avatarAdapter = new AvatarAdapter(LocalAvatarSelectActivity.this);
        gvAvatar.setAdapter(avatarAdapter);
        type = getIntent().getStringExtra(Constant.INTENT_KEY_TYPE);

    }

    protected void initEvent() {
        tvBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        gvAvatar.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if(TextUtils.isEmpty(type)){
                    Intent intent = new Intent();
                    intent.putExtra("imgRes",LocalAvatarItems.getInstance().get(position).getImgRes());
                    setResult(999,intent);
                    finish();
                }else if(Constant.INTENT_VALUE_MOMENT_AVATAR.equals(type)){
                    String imgRes = String.valueOf(LocalAvatarItems.getInstance().get(position).getImgRes());
                    SPUtils.put(getThis(),Constant.KEY_MOMENT_AVATAR,imgRes);
                    finish();
                }else if(Constant.INTENT_VALUE_PROFILE_AVATAR.equals(type)){
                    int imgRes = LocalAvatarItems.getInstance().get(position).getImgRes();
                    SPUtils.put(getThis(),Constant.KEY_PROFILE_AVATAR,imgRes);
                    finish();
                }
            }
        });
    }

    protected void initData() {
        tvTitle.setText("头像库");
        LocalAvatarItems.getInstance().clear();
        LocalAvatarItems.getInstance().add(new LocalAvatarItem(R.mipmap.app_images_role_10000));
        LocalAvatarItems.getInstance().add(new LocalAvatarItem(R.mipmap.app_images_role_10001));
        LocalAvatarItems.getInstance().add(new LocalAvatarItem(R.mipmap.app_images_role_10002));
        LocalAvatarItems.getInstance().add(new LocalAvatarItem(R.mipmap.app_images_role_10003));
        LocalAvatarItems.getInstance().add(new LocalAvatarItem(R.mipmap.app_images_role_10004));
        LocalAvatarItems.getInstance().add(new LocalAvatarItem(R.mipmap.app_images_role_10005));
        LocalAvatarItems.getInstance().add(new LocalAvatarItem(R.mipmap.app_images_role_10006));
        LocalAvatarItems.getInstance().add(new LocalAvatarItem(R.mipmap.app_images_role_10007));
        LocalAvatarItems.getInstance().add(new LocalAvatarItem(R.mipmap.app_images_role_10008));
        LocalAvatarItems.getInstance().add(new LocalAvatarItem(R.mipmap.app_images_role_10009));
        LocalAvatarItems.getInstance().add(new LocalAvatarItem(R.mipmap.app_images_role_10010));
        LocalAvatarItems.getInstance().add(new LocalAvatarItem(R.mipmap.app_images_role_10011));
        LocalAvatarItems.getInstance().add(new LocalAvatarItem(R.mipmap.app_images_role_10012));
        LocalAvatarItems.getInstance().add(new LocalAvatarItem(R.mipmap.app_images_role_10013));
        LocalAvatarItems.getInstance().add(new LocalAvatarItem(R.mipmap.app_images_role_10014));
        LocalAvatarItems.getInstance().add(new LocalAvatarItem(R.mipmap.app_images_role_10015));
        LocalAvatarItems.getInstance().add(new LocalAvatarItem(R.mipmap.app_images_role_10016));
        LocalAvatarItems.getInstance().add(new LocalAvatarItem(R.mipmap.app_images_role_10017));
        LocalAvatarItems.getInstance().add(new LocalAvatarItem(R.mipmap.app_images_role_10018));
        LocalAvatarItems.getInstance().add(new LocalAvatarItem(R.mipmap.app_images_role_10019));
        LocalAvatarItems.getInstance().add(new LocalAvatarItem(R.mipmap.app_images_role_10020));
        LocalAvatarItems.getInstance().add(new LocalAvatarItem(R.mipmap.app_images_role_10021));
        LocalAvatarItems.getInstance().add(new LocalAvatarItem(R.mipmap.app_images_role_10022));
        LocalAvatarItems.getInstance().add(new LocalAvatarItem(R.mipmap.app_images_role_10023));
        LocalAvatarItems.getInstance().add(new LocalAvatarItem(R.mipmap.app_images_role_10024));
        LocalAvatarItems.getInstance().add(new LocalAvatarItem(R.mipmap.app_images_role_10025));
        LocalAvatarItems.getInstance().add(new LocalAvatarItem(R.mipmap.app_images_role_10026));
        LocalAvatarItems.getInstance().add(new LocalAvatarItem(R.mipmap.app_images_role_10027));
        LocalAvatarItems.getInstance().add(new LocalAvatarItem(R.mipmap.app_images_role_10028));
        LocalAvatarItems.getInstance().add(new LocalAvatarItem(R.mipmap.app_images_role_10029));
        LocalAvatarItems.getInstance().add(new LocalAvatarItem(R.mipmap.app_images_role_10030));
        LocalAvatarItems.getInstance().add(new LocalAvatarItem(R.mipmap.app_images_role_10031));
        LocalAvatarItems.getInstance().add(new LocalAvatarItem(R.mipmap.app_images_role_10032));
        LocalAvatarItems.getInstance().add(new LocalAvatarItem(R.mipmap.app_images_role_10033));
        LocalAvatarItems.getInstance().add(new LocalAvatarItem(R.mipmap.app_images_role_10034));
        LocalAvatarItems.getInstance().add(new LocalAvatarItem(R.mipmap.app_images_role_10035));
        LocalAvatarItems.getInstance().add(new LocalAvatarItem(R.mipmap.app_images_role_10036));
        LocalAvatarItems.getInstance().add(new LocalAvatarItem(R.mipmap.app_images_role_10037));
        LocalAvatarItems.getInstance().add(new LocalAvatarItem(R.mipmap.app_images_role_10038));
        LocalAvatarItems.getInstance().add(new LocalAvatarItem(R.mipmap.app_images_role_10039));
        LocalAvatarItems.getInstance().add(new LocalAvatarItem(R.mipmap.app_images_role_10040));
        LocalAvatarItems.getInstance().add(new LocalAvatarItem(R.mipmap.app_images_role_10041));
        LocalAvatarItems.getInstance().add(new LocalAvatarItem(R.mipmap.app_images_role_10042));
        LocalAvatarItems.getInstance().add(new LocalAvatarItem(R.mipmap.app_images_role_10043));
        LocalAvatarItems.getInstance().add(new LocalAvatarItem(R.mipmap.app_images_role_10044));
        LocalAvatarItems.getInstance().add(new LocalAvatarItem(R.mipmap.app_images_role_10045));
        LocalAvatarItems.getInstance().add(new LocalAvatarItem(R.mipmap.app_images_role_10046));
        LocalAvatarItems.getInstance().add(new LocalAvatarItem(R.mipmap.app_images_role_10047));
        LocalAvatarItems.getInstance().add(new LocalAvatarItem(R.mipmap.app_images_role_10048));
        LocalAvatarItems.getInstance().add(new LocalAvatarItem(R.mipmap.app_images_role_10049));
        LocalAvatarItems.getInstance().add(new LocalAvatarItem(R.mipmap.app_images_role_10050));
        LocalAvatarItems.getInstance().add(new LocalAvatarItem(R.mipmap.app_images_role_10051));
        LocalAvatarItems.getInstance().add(new LocalAvatarItem(R.mipmap.app_images_role_10052));
        LocalAvatarItems.getInstance().add(new LocalAvatarItem(R.mipmap.app_images_role_10053));
        LocalAvatarItems.getInstance().add(new LocalAvatarItem(R.mipmap.app_images_role_10054));
        LocalAvatarItems.getInstance().add(new LocalAvatarItem(R.mipmap.app_images_role_10055));
        LocalAvatarItems.getInstance().add(new LocalAvatarItem(R.mipmap.app_images_role_10056));
        LocalAvatarItems.getInstance().add(new LocalAvatarItem(R.mipmap.app_images_role_10057));
        LocalAvatarItems.getInstance().add(new LocalAvatarItem(R.mipmap.app_images_role_10058));
        LocalAvatarItems.getInstance().add(new LocalAvatarItem(R.mipmap.app_images_role_10059));
        LocalAvatarItems.getInstance().add(new LocalAvatarItem(R.mipmap.app_images_role_10060));
        LocalAvatarItems.getInstance().add(new LocalAvatarItem(R.mipmap.app_images_role_10061));
        LocalAvatarItems.getInstance().add(new LocalAvatarItem(R.mipmap.app_images_role_10062));
        LocalAvatarItems.getInstance().add(new LocalAvatarItem(R.mipmap.app_images_role_10063));
        LocalAvatarItems.getInstance().add(new LocalAvatarItem(R.mipmap.app_images_role_10064));
        LocalAvatarItems.getInstance().add(new LocalAvatarItem(R.mipmap.app_images_role_10065));
        LocalAvatarItems.getInstance().add(new LocalAvatarItem(R.mipmap.app_images_role_10066));
        LocalAvatarItems.getInstance().add(new LocalAvatarItem(R.mipmap.app_images_role_10067));
        LocalAvatarItems.getInstance().add(new LocalAvatarItem(R.mipmap.app_images_role_10068));
        LocalAvatarItems.getInstance().add(new LocalAvatarItem(R.mipmap.app_images_role_10069));
        LocalAvatarItems.getInstance().add(new LocalAvatarItem(R.mipmap.app_images_role_10070));
        LocalAvatarItems.getInstance().add(new LocalAvatarItem(R.mipmap.app_images_role_10071));
        LocalAvatarItems.getInstance().add(new LocalAvatarItem(R.mipmap.app_images_role_10072));
        LocalAvatarItems.getInstance().add(new LocalAvatarItem(R.mipmap.app_images_role_10073));
        LocalAvatarItems.getInstance().add(new LocalAvatarItem(R.mipmap.app_images_role_10074));
        LocalAvatarItems.getInstance().add(new LocalAvatarItem(R.mipmap.app_images_role_10075));
        LocalAvatarItems.getInstance().add(new LocalAvatarItem(R.mipmap.app_images_role_10076));
        LocalAvatarItems.getInstance().add(new LocalAvatarItem(R.mipmap.app_images_role_10077));
        LocalAvatarItems.getInstance().add(new LocalAvatarItem(R.mipmap.app_images_role_10078));
        LocalAvatarItems.getInstance().add(new LocalAvatarItem(R.mipmap.app_images_role_10079));
        LocalAvatarItems.getInstance().add(new LocalAvatarItem(R.mipmap.app_images_role_10080));
        LocalAvatarItems.getInstance().add(new LocalAvatarItem(R.mipmap.app_images_role_10081));
        LocalAvatarItems.getInstance().add(new LocalAvatarItem(R.mipmap.app_images_role_10082));
        LocalAvatarItems.getInstance().add(new LocalAvatarItem(R.mipmap.app_images_role_10083));
        LocalAvatarItems.getInstance().add(new LocalAvatarItem(R.mipmap.app_images_role_10084));
        LocalAvatarItems.getInstance().add(new LocalAvatarItem(R.mipmap.app_images_role_10085));
        LocalAvatarItems.getInstance().add(new LocalAvatarItem(R.mipmap.app_images_role_10086));
        LocalAvatarItems.getInstance().add(new LocalAvatarItem(R.mipmap.app_images_role_10087));
        LocalAvatarItems.getInstance().add(new LocalAvatarItem(R.mipmap.app_images_role_10088));
        LocalAvatarItems.getInstance().add(new LocalAvatarItem(R.mipmap.app_images_role_10089));
        LocalAvatarItems.getInstance().add(new LocalAvatarItem(R.mipmap.app_images_role_10090));
        LocalAvatarItems.getInstance().add(new LocalAvatarItem(R.mipmap.app_images_role_10091));
        LocalAvatarItems.getInstance().add(new LocalAvatarItem(R.mipmap.app_images_role_10092));
        LocalAvatarItems.getInstance().add(new LocalAvatarItem(R.mipmap.app_images_role_10093));
        LocalAvatarItems.getInstance().add(new LocalAvatarItem(R.mipmap.app_images_role_10094));
        LocalAvatarItems.getInstance().add(new LocalAvatarItem(R.mipmap.app_images_role_10095));
        LocalAvatarItems.getInstance().add(new LocalAvatarItem(R.mipmap.app_images_role_10096));
        LocalAvatarItems.getInstance().add(new LocalAvatarItem(R.mipmap.app_images_role_10097));
        LocalAvatarItems.getInstance().add(new LocalAvatarItem(R.mipmap.app_images_role_10098));
        LocalAvatarItems.getInstance().add(new LocalAvatarItem(R.mipmap.app_images_role_10099));
        LocalAvatarItems.getInstance().add(new LocalAvatarItem(R.mipmap.app_images_wechat_mp_1));
        LocalAvatarItems.getInstance().add(new LocalAvatarItem(R.mipmap.app_images_wechat_mp_2));
        LocalAvatarItems.getInstance().add(new LocalAvatarItem(R.mipmap.app_images_wechat_mp_3));
        LocalAvatarItems.getInstance().add(new LocalAvatarItem(R.mipmap.app_images_wechat_mp_4));
        LocalAvatarItems.getInstance().add(new LocalAvatarItem(R.mipmap.app_images_wechat_mp_5));
        LocalAvatarItems.getInstance().add(new LocalAvatarItem(R.mipmap.app_images_wechat_mp_6));
        LocalAvatarItems.getInstance().add(new LocalAvatarItem(R.mipmap.app_images_wechat_mp_7));
        LocalAvatarItems.getInstance().add(new LocalAvatarItem(R.mipmap.app_images_wechat_mp_8));
        LocalAvatarItems.getInstance().add(new LocalAvatarItem(R.mipmap.app_images_wechat_mp_9));
        LocalAvatarItems.getInstance().add(new LocalAvatarItem(R.mipmap.app_images_wechat_mp_10));
        LocalAvatarItems.getInstance().add(new LocalAvatarItem(R.mipmap.app_images_wechat_mp_11));
        LocalAvatarItems.getInstance().add(new LocalAvatarItem(R.mipmap.app_images_wechat_mp_12));
        LocalAvatarItems.getInstance().add(new LocalAvatarItem(R.mipmap.app_images_wechat_mp_13));
        LocalAvatarItems.getInstance().add(new LocalAvatarItem(R.mipmap.app_images_wechat_mp_14));
        LocalAvatarItems.getInstance().add(new LocalAvatarItem(R.mipmap.app_images_wechat_mp_15));
        LocalAvatarItems.getInstance().add(new LocalAvatarItem(R.mipmap.app_images_wechat_mp_16));
        LocalAvatarItems.getInstance().add(new LocalAvatarItem(R.mipmap.app_images_wechat_mp_17));
        LocalAvatarItems.getInstance().add(new LocalAvatarItem(R.mipmap.app_images_wechat_mp_18));
        LocalAvatarItems.getInstance().add(new LocalAvatarItem(R.mipmap.app_images_wechat_mp_20));
        LocalAvatarItems.getInstance().add(new LocalAvatarItem(R.mipmap.app_images_wechat_mp_21));
        LocalAvatarItems.getInstance().add(new LocalAvatarItem(R.mipmap.app_images_wechat_mp_22));
        LocalAvatarItems.getInstance().add(new LocalAvatarItem(R.mipmap.app_images_wechat_mp_23));
        LocalAvatarItems.getInstance().add(new LocalAvatarItem(R.mipmap.app_images_wechat_mp_24));
        LocalAvatarItems.getInstance().add(new LocalAvatarItem(R.mipmap.app_images_wechat_mp_25));
        avatarAdapter.notifyDataSetChanged();
    }
}
