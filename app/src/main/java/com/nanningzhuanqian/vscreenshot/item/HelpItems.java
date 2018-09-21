package com.nanningzhuanqian.vscreenshot.item;

public class HelpItems  extends DataStorageImpl<HelpItem> {

    private static HelpItems mThis;

    private HelpItems() {
        super();
    }

    public static HelpItems getInstance() {

        if (mThis == null)
            mThis = new HelpItems();

        return mThis;
    }

}
