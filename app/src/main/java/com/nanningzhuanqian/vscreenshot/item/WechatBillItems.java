package com.nanningzhuanqian.vscreenshot.item;

/**
 * Created by WMY on 2018/9/18.
 */

public class WechatBillItems extends DataStorageImpl<WechatBillItem> {

    private static WechatBillItems mThis;

    private WechatBillItems() {
        super();
    }

    public static WechatBillItems getInstance() {

        if (mThis == null)
            mThis = new WechatBillItems();

        return mThis;
    }
}
