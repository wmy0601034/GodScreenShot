package com.nanningzhuanqian.vscreenshot.base.bean;

import com.nanningzhuanqian.vscreenshot.item.DataStorageImpl;

import java.util.Collections;

/**
 * Created by lenovo on 2019/1/13.
 */

public class Conversations extends DataStorageImpl<Conversation> {

    private static Conversations mThis;

    private Conversations() {
        super();
    }

    public static Conversations getInstance() {

        if (mThis == null)
            mThis = new Conversations();

        return mThis;
    }

    public void sort(){
        Collections.sort(get());
    }

}
