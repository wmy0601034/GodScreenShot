package com.nanningzhuanqian.vscreenshot.item;

public class WechatSpreadItems extends DataStorageImpl<WechatSpreadItem>{

    private static WechatSpreadItems mThis;

    private WechatSpreadItems() {
        super();
    }

    public static WechatSpreadItems getInstance() {

        if (mThis == null)
            mThis = new WechatSpreadItems();

        return mThis;
    }

}
