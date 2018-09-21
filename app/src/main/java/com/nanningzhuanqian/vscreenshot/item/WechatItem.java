package com.nanningzhuanqian.vscreenshot.item;

public class WechatItem {

    private int imgRes;
    private String imgUrl;
    private int imgType;
    private String name;
    private int versionCode;
    private int type;   //跳转

    public int getImgRes() {
        return imgRes;
    }

    public void setImgRes(int imgRes) {
        this.imgRes = imgRes;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public int getImgType() {
        return imgType;
    }

    public void setImgType(int imgType) {
        this.imgType = imgType;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getVersionCode() {
        return versionCode;
    }

    public void setVersionCode(int versionCode) {
        this.versionCode = versionCode;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public WechatItem(int imgRes, String name, int type) {
        this.imgRes = imgRes;
        this.name = name;
        this.type = type;
    }

    public WechatItem(int imgRes, String imgUrl, int imgType, String name, int versionCode) {
        this.imgRes = imgRes;
        this.imgUrl = imgUrl;
        this.imgType = imgType;
        this.name = name;
        this.versionCode = versionCode;
    }
}
