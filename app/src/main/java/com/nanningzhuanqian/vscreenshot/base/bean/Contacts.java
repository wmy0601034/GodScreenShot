package com.nanningzhuanqian.vscreenshot.base.bean;

import com.nanningzhuanqian.vscreenshot.item.DataStorageImpl;

import java.util.Collections;

/**
 * Created by lenovo on 2019/1/19.
 */

public class Contacts  extends DataStorageImpl<Contact> {

    private static Contacts mThis;

    private Contacts() {
        super();
    }

    public static Contacts getInstance() {

        if (mThis == null)
            mThis = new Contacts();

        return mThis;
    }

}
