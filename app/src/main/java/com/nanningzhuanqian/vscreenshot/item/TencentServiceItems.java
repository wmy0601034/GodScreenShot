package com.nanningzhuanqian.vscreenshot.item;

public class TencentServiceItems extends DataStorageImpl<TencentServiceItem> {

    private static TencentServiceItems mThis;

    private TencentServiceItems() {
        super();
    }

    public static TencentServiceItems getInstance() {

        if (mThis == null)
            mThis = new TencentServiceItems();

        return mThis;
    }

}
