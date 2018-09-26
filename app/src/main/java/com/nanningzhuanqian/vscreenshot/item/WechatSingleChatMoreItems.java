package com.nanningzhuanqian.vscreenshot.item;

public class WechatSingleChatMoreItems   extends DataStorageImpl<WechatSingleChatMoreItem>{

    private static WechatSingleChatMoreItems mThis;

    private WechatSingleChatMoreItems() {
        super();
    }

    public static WechatSingleChatMoreItems getInstance() {

        if (mThis == null)
            mThis = new WechatSingleChatMoreItems();

        return mThis;
    }

}
