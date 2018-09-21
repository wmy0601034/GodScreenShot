package com.nanningzhuanqian.vscreenshot.item;

public class AliPayItems extends DataStorageImpl<AliPayItem> {

    private static AliPayItems mThis;

    private AliPayItems() {
        super();
    }

    public static AliPayItems getInstance() {

        if (mThis == null)
            mThis = new AliPayItems();

        return mThis;
    }


}
