package com.nanningzhuanqian.vscreenshot.model;

import com.nanningzhuanqian.vscreenshot.item.AliPayItems;

import cn.bmob.v3.BmobObject;

public class Config extends BmobObject{

    private String right;
    private String versionName;
    private int versionCode;
    private String contractQQ;
    private String adUrl;

    private static Config mThis;

    private Config() {
        super();
    }

    public static Config getInstance() {

        if (mThis == null)
            mThis = new Config();

        return mThis;
    }



    public String getRight() {
        return right;
    }

    public void setRight(String right) {
        this.right = right;
    }

    public String getVersionName() {
        return versionName;
    }

    public void setVersionName(String versionName) {
        this.versionName = versionName;
    }

    public int getVersionCode() {
        return versionCode;
    }

    public void setVersionCode(int versionCode) {
        this.versionCode = versionCode;
    }

    public String getContractQQ() {
        return contractQQ;
    }

    public void setContractQQ(String contractQQ) {
        this.contractQQ = contractQQ;
    }

    public String getAdUrl() {
        return adUrl;
    }

    public void setAdUrl(String adUrl) {
        this.adUrl = adUrl;
    }
}
