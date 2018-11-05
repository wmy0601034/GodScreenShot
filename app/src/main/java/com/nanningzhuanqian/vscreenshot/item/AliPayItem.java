package com.nanningzhuanqian.vscreenshot.item;

/**
 * 支付宝功能
 */
public class AliPayItem extends BaseItem{


    public AliPayItem(int imgRes, String name, int type) {
        this.imgRes = imgRes;
        this.name = name;
        this.type = type;
    }

    public AliPayItem(int imgRes, String imgUrl, String imgType, String name, int versionCode) {
        this.imgRes = imgRes;
        this.imgUrl = imgUrl;
        this.imgType = imgType;
        this.name = name;
        this.versionCode = versionCode;
    }
}
