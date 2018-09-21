package com.nanningzhuanqian.vscreenshot.item;

public class WechatPayItems  extends DataStorageImpl<WechatPayItem>{

    private static WechatPayItems mThis;

    private WechatPayItems() {
        super();
    }

    public static WechatPayItems getInstance() {

        if (mThis == null)
            mThis = new WechatPayItems();

        return mThis;
    }

}
