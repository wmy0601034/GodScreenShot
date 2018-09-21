package com.nanningzhuanqian.vscreenshot.item;

public class WeixinCallItems  extends DataStorageImpl<WeixinCallItem>{

    private static WeixinCallItems mThis;

    private WeixinCallItems() {
        super();
    }

    public static WeixinCallItems getInstance() {

        if (mThis == null)
            mThis = new WeixinCallItems();

        return mThis;
    }

}