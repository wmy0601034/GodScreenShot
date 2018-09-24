package com.nanningzhuanqian.vscreenshot.item;

public class WechatChatItems extends DataStorageImpl<WechatChatItem> {

    private static WechatChatItems mThis;

    private WechatChatItems() {
        super();
    }

    public static WechatChatItems getInstance() {

        if (mThis == null)
            mThis = new WechatChatItems();

        return mThis;
    }
}
