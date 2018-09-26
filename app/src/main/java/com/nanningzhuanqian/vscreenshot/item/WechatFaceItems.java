package com.nanningzhuanqian.vscreenshot.item;

public class WechatFaceItems  extends DataStorageImpl<WechatFaceItem> {

    private static WechatFaceItems mThis;

    private WechatFaceItems() {
        super();
    }

    public static WechatFaceItems getInstance() {

        if (mThis == null)
            mThis = new WechatFaceItems();

        return mThis;
    }
}