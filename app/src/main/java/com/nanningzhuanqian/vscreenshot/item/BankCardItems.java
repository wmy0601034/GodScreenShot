package com.nanningzhuanqian.vscreenshot.item;

public class BankCardItems extends DataStorageImpl<BankCardItem> {

    private static BankCardItems mThis;

    private BankCardItems() {
        super();
    }

    public static BankCardItems getInstance() {

        if (mThis == null)
            mThis = new BankCardItems();

        return mThis;
    }


}
