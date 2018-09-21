package com.nanningzhuanqian.vscreenshot.base.util;

import android.app.Activity;
import android.content.Intent;

import com.nanningzhuanqian.vscreenshot.m00_launcher.LoginActivity;

/**
 * Created by zhul on 2017/3/13.
 */

public class LoginUtils {

    public static final void goLogin(Activity srcAct) {
        Intent intent = new Intent(srcAct, LoginActivity.class);
        srcAct.startActivity(intent);
    }

    public static final void goLogin(Activity srcAct,String className) {
        Intent intent = new Intent(srcAct, LoginActivity.class);
        intent.putExtra(LoginActivity.EXTRA_JUMP_TO,className);
        srcAct.startActivity(intent);
    }

    public static final void goLogin(Activity srcAct,Class<? extends Activity> cls) {
        goLogin(srcAct, cls.getName());
    }

}
