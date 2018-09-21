package com.nanningzhuanqian.vscreenshot.item;

import java.util.Collections;

/**
 * Created by WMY on 2018/9/18.
 */

public class ChangeDetailItems extends DataStorageImpl<ChangeDetailItem> {

    private static ChangeDetailItems mThis;

    private ChangeDetailItems() {
        super();
    }

    public static ChangeDetailItems getInstance() {

        if (mThis == null)
            mThis = new ChangeDetailItems();

        return mThis;
    }

    public void sort(){
        Collections.sort(get());
    }
}
