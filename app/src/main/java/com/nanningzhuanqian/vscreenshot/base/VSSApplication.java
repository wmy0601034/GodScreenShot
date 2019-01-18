package com.nanningzhuanqian.vscreenshot.base;

import android.app.Application;
import android.database.sqlite.SQLiteDatabase;


import com.nanningzhuanqian.vscreenshot.base.util.DBManager;

import cn.bmob.v3.Bmob;

public class VSSApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        Bmob.initialize(this, "96cc100b6c94f87eae41d2f3ca8e949c");
        DBManager.init(getApplicationContext());
    }
}
