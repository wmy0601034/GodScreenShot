package com.nanningzhuanqian.vscreenshot.base;

import android.app.Application;
import android.database.sqlite.SQLiteDatabase;

import org.litepal.LitePal;
import org.litepal.LitePalApplication;
import org.litepal.tablemanager.Connector;

import cn.bmob.v3.Bmob;

public class VSSApplication extends LitePalApplication {

    @Override
    public void onCreate() {
        super.onCreate();
        Bmob.initialize(this, "96cc100b6c94f87eae41d2f3ca8e949c");
        SQLiteDatabase db = Connector.getDatabase();
//        LitePal.
    }
}
