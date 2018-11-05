package com.nanningzhuanqian.vscreenshot.item;

import android.net.Uri;

/**
 * 银行卡列表
 */
public class BankCardItem {

    private String name;
    private String iconType;
    private String iconUrl;
    private int iconRes;
    private Uri iconUri;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIconType() {
        return iconType;
    }

    public void setIconType(String iconType) {
        this.iconType = iconType;
    }

    public String getIconUrl() {
        return iconUrl;
    }

    public void setIconUrl(String iconUrl) {
        this.iconUrl = iconUrl;
    }

    public int getIconRes() {
        return iconRes;
    }

    public void setIconRes(int iconRes) {
        this.iconRes = iconRes;
    }

    public Uri getIconUri() {
        return iconUri;
    }

    public void setIconUri(Uri iconUri) {
        this.iconUri = iconUri;
    }

    public BankCardItem(String name, int iconRes) {
        this.name = name;
        this.iconRes = iconRes;
    }
}
