package com.nanningzhuanqian.vscreenshot.base.util;

import android.app.Activity;
import android.content.Intent;
import android.text.TextUtils;

import com.nanningzhuanqian.vscreenshot.common.Constant;
import com.nanningzhuanqian.vscreenshot.m00_launcher.user.LoginActivity;

import cn.bmob.v3.BmobUser;

/**
 * Created by zhul on 2017/3/13.
 */

public class LoginUtils {

    public static final void goLogin(Activity srcAct) {
        Intent intent = new Intent(srcAct, LoginActivity.class);
        srcAct.startActivity(intent);
    }

    public static final void goLogin(Activity srcAct,String className) {
        String mobile = (String)SPUtils.get(srcAct, Constant.KEY_MOBILE,"");
        if(TextUtils.isEmpty(mobile)){
            Intent intent = new Intent(srcAct, LoginActivity.class);
            intent.putExtra(LoginActivity.EXTRA_JUMP_TO, className);
            srcAct.startActivity(intent);
        }else {
            Intent intent = new Intent();
            intent.setClassName(srcAct,className);
            srcAct.startActivity(intent);
        }
    }

    public static final void goLogin(Activity srcAct,Class<? extends Activity> cls) {
        goLogin(srcAct, cls.getName());
    }

}
