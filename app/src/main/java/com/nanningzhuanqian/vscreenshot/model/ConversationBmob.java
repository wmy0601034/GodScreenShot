package com.nanningzhuanqian.vscreenshot.model;

import cn.bmob.v3.BmobObject;

/**
 * Created by WMY on 2018/9/17.
 */

public class ConversationBmob extends BmobObject {

    private int imgRes;
    private String imgUrl;
    private String name;
    private int badgeCount;
    private long updateTime;
    private String content;
    private boolean ignore;
    private boolean isPublic;   //0
    private boolean isBadge;
    private String pointToUser; //指向某个用户的手机号

    public ConversationBmob() {

    }

    public ConversationBmob(int imgRes,String imgUrl, String name, int badgeCount, long updateTime, String
            content, boolean ignore,boolean isPublic,boolean isBadge) {
        this.imgRes = imgRes;
        this.imgUrl = imgUrl;
        this.name = name;
        this.badgeCount = badgeCount;
        this.updateTime = updateTime;
        this.content = content;
        this.ignore = ignore;
        this.isPublic = isPublic;
        this.isBadge = isBadge;
    }

    public String getPointToUser() {
        return pointToUser;
    }

    public void setPointToUser(String pointToUser) {
        this.pointToUser = pointToUser;
    }

    public int getImgRes() {
        return imgRes;
    }

    public void setImgRes(int imgRes) {
        this.imgRes = imgRes;
    }

    public boolean isPublic() {
        return isPublic;
    }

    public void setPublic(boolean aPublic) {
        isPublic = aPublic;
    }

    public boolean isBadge() {
        return isBadge;
    }

    public void setBadge(boolean isBadge) {
        this.isBadge = isBadge;
    }

    public boolean isIgnore() {
        return ignore;
    }

    public void setIgnore(boolean ignore) {
        this.ignore = ignore;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getBadgeCount() {
        return badgeCount;
    }

    public void setBadgeCount(int badgeCount) {
        this.badgeCount = badgeCount;
    }

    public long getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(long updateTime) {
        this.updateTime = updateTime;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
