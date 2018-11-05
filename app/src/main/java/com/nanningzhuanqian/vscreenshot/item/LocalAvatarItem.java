package com.nanningzhuanqian.vscreenshot.item;

/**
 * 本地头像
 */
public class LocalAvatarItem {

    public LocalAvatarItem(int imgRes) {
        this.imgRes = imgRes;
    }

    private int imgRes;

    public int getImgRes() {
        return imgRes;
    }

    public void setImgRes(int imgRes) {
        this.imgRes = imgRes;
    }
}
