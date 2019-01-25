package com.nanningzhuanqian.vscreenshot.base.bean;

import com.nanningzhuanqian.vscreenshot.item.DataStorageImpl;

import java.util.Collections;

public class Subcribes extends DataStorageImpl<Subcribe> {

    private static Subcribes mThis;

    private Subcribes() {
        super();
    }

    public static Subcribes getInstance() {

        if (mThis == null)
            mThis = new Subcribes();

        return mThis;
    }

    public void sort(){
        Collections.sort(get());
    }

}
