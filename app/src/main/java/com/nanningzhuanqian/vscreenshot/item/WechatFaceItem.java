package com.nanningzhuanqian.vscreenshot.item;

public class WechatFaceItem {

    private int id;
    private int imgRes;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getImgRes() {
        return imgRes;
    }

    public void setImgRes(int imgRes) {
        this.imgRes = imgRes;
    }

    public WechatFaceItem(int id, int imgRes) {
        this.id = id;
        this.imgRes = imgRes;
    }
}
