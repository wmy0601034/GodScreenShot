package com.nanningzhuanqian.vscreenshot.item;

public class WechatItems extends DataStorageImpl<WechatItem>{

    private static WechatItems mThis;

    private WechatItems() {
        super();
    }

    public static WechatItems getInstance() {

        if (mThis == null)
            mThis = new WechatItems();

        return mThis;
    }

}
