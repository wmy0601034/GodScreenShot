package com.nanningzhuanqian.vscreenshot.item;

public class DiscoveryItem {

    private String name;
    private int type;
    private int leftImgRes;
    private String leftImgUrl;
    private int leftImgType;
    private int rightImgRes;
    private String rightImgUrl;
    private int rightImgType;   //0 本地 1 网络
    private int badge = 0;
    private int isIgnore;
    private String rightContent;

    public DiscoveryItem( int type,String name, int leftImgRes) {
        this.name = name;
        this.type = type;
        this.leftImgRes = leftImgRes;
    }

    public DiscoveryItem( int type,String name, int leftImgRes,int rightImgType,int rightImgRes,String rightImgUrl,
                          String rightContent) {
        this.name = name;
        this.type = type;
        this.leftImgRes = leftImgRes;
        this.rightImgType = rightImgType;
        this.rightImgRes = rightImgRes;
        this.rightImgUrl = rightImgUrl;
        this.rightContent = rightContent;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getLeftImgRes() {
        return leftImgRes;
    }

    public void setLeftImgRes(int leftImgRes) {
        this.leftImgRes = leftImgRes;
    }

    public String getLeftImgUrl() {
        return leftImgUrl;
    }

    public void setLeftImgUrl(String leftImgUrl) {
        this.leftImgUrl = leftImgUrl;
    }

    public int getLeftImgType() {
        return leftImgType;
    }

    public void setLeftImgType(int leftImgType) {
        this.leftImgType = leftImgType;
    }

    public int getRightImgRes() {
        return rightImgRes;
    }

    public void setRightImgRes(int rightImgRes) {
        this.rightImgRes = rightImgRes;
    }

    public String getRightImgUrl() {
        return rightImgUrl;
    }

    public void setRightImgUrl(String rightImgUrl) {
        this.rightImgUrl = rightImgUrl;
    }

    public int getRightImgType() {
        return rightImgType;
    }

    public void setRightImgType(int rightImgType) {
        this.rightImgType = rightImgType;
    }

    public int getBadge() {
        return badge;
    }

    public void setBadge(int badge) {
        this.badge = badge;
    }

    public int getIsIgnore() {
        return isIgnore;
    }

    public void setIsIgnore(int isIgnore) {
        this.isIgnore = isIgnore;
    }

    public String getRightContent() {
        return rightContent;
    }

    public void setRightContent(String rightContent) {
        this.rightContent = rightContent;
    }
}
