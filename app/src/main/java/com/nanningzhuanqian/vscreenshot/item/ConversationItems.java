package com.nanningzhuanqian.vscreenshot.item;

/**
 * Created by WMY on 2018/9/14.
 */

public class ConversationItems extends DataStorageImpl<ConversationItem> {

    private static ConversationItems mThis;

    private ConversationItems() {
        super();
    }

    public static ConversationItems getInstance() {

        if (mThis == null)
            mThis = new ConversationItems();

        return mThis;
    }

}
