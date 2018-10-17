package com.nanningzhuanqian.vscreenshot.base;

import android.content.Context;
import android.net.Uri;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.widget.TextView;
import android.widget.Toast;

import com.nanningzhuanqian.vscreenshot.base.util.SPUtils;
import com.nanningzhuanqian.vscreenshot.common.Constant;

public class BaseFragment extends Fragment {

    public String TAG = getClass().getSimpleName();
    public String mobile;
    public String wechatUserName;
    public String wechatUserAvatarType;
    public Uri wechatUserAvatarUri;
    public int wechatUserAvatarRes;

    public void toast(String msg){
        Toast.makeText(getActivity(),msg,Toast.LENGTH_SHORT).show();
    }

    public boolean isLogin(){
        String mobile = (String) SPUtils.get(getThis(),"mobile","");
        if(TextUtils.isEmpty(mobile)){
            return false;
        }else {
            return true;
        }
    }

    public void initUserMobile(){
        mobile = (String) SPUtils.get(getThis(), Constant.KEY_MOBILE, "");
    }

    public void initWechatUserName(){
        wechatUserName = (String) SPUtils.get(getThis(), Constant.KEY_PROFILE_NAME, "");
    }

    public void initWechatUserAvatarType(){
        wechatUserAvatarType = (String)SPUtils.get(getThis(),Constant.KEY_PROFILE_AVATAR_TYPE,"");
    }

    public void initWechatUserAvatarUri(){
        String avatarUri = (String)SPUtils.get(getThis(),Constant.KEY_PROFILE_AVATAR,"");
        wechatUserAvatarUri = Uri.parse(avatarUri);
    }

    public void initWechatUserImaRes(){
        String avatar = (String)SPUtils.get(getThis(),Constant.KEY_PROFILE_AVATAR,"");
        if(!TextUtils.isEmpty(avatar)){
            wechatUserAvatarRes = Integer.valueOf(avatar);
        }
    }

    public Context getThis(){
        return getActivity();
    }
}
