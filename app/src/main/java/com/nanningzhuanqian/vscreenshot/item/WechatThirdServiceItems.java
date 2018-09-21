package com.nanningzhuanqian.vscreenshot.item;

public class WechatThirdServiceItems extends DataStorageImpl<WechatThirdServiceItem>{

    private static WechatThirdServiceItems mThis;

    private WechatThirdServiceItems() {
        super();
    }

    public static WechatThirdServiceItems getInstance() {

        if (mThis == null)
            mThis = new WechatThirdServiceItems();

        return mThis;
    }

}
