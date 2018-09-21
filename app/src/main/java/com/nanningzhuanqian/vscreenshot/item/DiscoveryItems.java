package com.nanningzhuanqian.vscreenshot.item;

public class DiscoveryItems extends DataStorageImpl<DiscoveryItem> {

    private static DiscoveryItems mThis;

    private DiscoveryItems() {
        super();
    }

    public static DiscoveryItems getInstance() {

        if (mThis == null)
            mThis = new DiscoveryItems();

        return mThis;
    }

}
