package com.nanningzhuanqian.vscreenshot.item;

/**
 * Created by lenovo on 2019/1/13.
 */

public class NetworkAvatars  extends DataStorageImpl<NetworkAvatar> {

    private static NetworkAvatars mThis;

    private NetworkAvatars() {
        super();
    }

    public static NetworkAvatars getInstance() {

        if (mThis == null)
            mThis = new NetworkAvatars();

        return mThis;
    }

}
