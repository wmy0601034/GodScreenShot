package com.nanningzhuanqian.vscreenshot.item;

public class WechatMomentItems extends DataStorageImpl<WechatMomentItem>{

    private static WechatMomentItems mThis;

    private WechatMomentItems() {
        super();
    }

    public static WechatMomentItems getInstance() {

        if (mThis == null)
            mThis = new WechatMomentItems();

        return mThis;
    }

}