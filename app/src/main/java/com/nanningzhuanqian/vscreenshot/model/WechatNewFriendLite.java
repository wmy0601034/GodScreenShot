package com.nanningzhuanqian.vscreenshot.model;

import com.nanningzhuanqian.vscreenshot.item.WechatNewFriendItem;


public class WechatNewFriendLite {
    private int imgRes;
    private String imgUrl;
    private int imgType;
    private String name;
    private String content;
    private boolean isAdded;
    private boolean isInvaild;
    private boolean isRead;
    private long updateTime;

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

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public boolean isAdded() {
        return isAdded;
    }

    public void setAdded(boolean added) {
        isAdded = added;
    }

    public boolean isInvaild() {
        return isInvaild;
    }

    public void setInvaild(boolean invaild) {
        isInvaild = invaild;
    }

    public boolean isRead() {
        return isRead;
    }

    public void setRead(boolean read) {
        isRead = read;
    }

    public long getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(long updateTime) {
        this.updateTime = updateTime;
    }

//    public WechatNewFriendLite(int imgRes, String name, String content, boolean isAdded) {
//        this.imgRes = imgRes;
//        this.name = name;
//        this.content = content;
//        this.isAdded = isAdded;
//    }

    public WechatNewFriendLite(int imgRes, String name, String content, boolean isAdded, boolean isInvaild, boolean isRead, long updateTime) {
        this.imgRes = imgRes;
        this.name = name;
        this.content = content;
        this.isAdded = isAdded;
        this.isInvaild = isInvaild;
        this.isRead = isRead;
        this.updateTime = updateTime;
    }

    public WechatNewFriendItem convertToWechatNewFriendItem(){
        return new WechatNewFriendItem(imgRes,name,content,isAdded,isInvaild,isRead,updateTime);
    }

}
