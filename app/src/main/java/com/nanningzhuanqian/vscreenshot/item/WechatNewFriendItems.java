package com.nanningzhuanqian.vscreenshot.item;

public class WechatNewFriendItems   extends DataStorageImpl<WechatNewFriendItem>{

    private static WechatNewFriendItems mThis;

    private WechatNewFriendItems() {
        super();
    }

    public static WechatNewFriendItems getInstance() {

        if (mThis == null)
            mThis = new WechatNewFriendItems();

        return mThis;
    }

}
