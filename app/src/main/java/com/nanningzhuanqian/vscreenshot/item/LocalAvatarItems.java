package com.nanningzhuanqian.vscreenshot.item;

public class LocalAvatarItems extends DataStorageImpl<LocalAvatarItem> {

    private static LocalAvatarItems mThis;

    private LocalAvatarItems() {
        super();
    }

    public static LocalAvatarItems getInstance() {

        if (mThis == null)
            mThis = new LocalAvatarItems();

        return mThis;
    }

}
