package com.nanningzhuanqian.vscreenshot.item;

import com.nanningzhuanqian.vscreenshot.base.util.SPUtils;
import com.nanningzhuanqian.vscreenshot.common.Constant;
import com.nanningzhuanqian.vscreenshot.model.ConversationBmob;
import com.nanningzhuanqian.vscreenshot.model.ConversationLite;

/**
 * Created by WMY on 2018/9/14.
 */

public class ConversationItem {

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

    public ConversationItem() {

    }

    public ConversationItem(int imgRes,String imgUrl, String name, int badgeCount, long updateTime, String
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

    public ConversationLite convertToLite(){
        ConversationLite conversationLite = new ConversationLite();
        conversationLite.setBadgeCount(badgeCount);
        conversationLite.setName(name);
        conversationLite.setContent(content);
        conversationLite.setUpdateTime(updateTime);
        conversationLite.setImgRes(imgRes);
        conversationLite.setIgnore(ignore);
        conversationLite.setBadge(isBadge);
        conversationLite.setPublic(isPublic);
        conversationLite.setPointToUser(pointToUser);
        return conversationLite;
    }

    public ConversationBmob convertToBmob(){
        ConversationBmob conversationBmob = new ConversationBmob();
        conversationBmob.setBadgeCount(badgeCount);
        conversationBmob.setName(name);
        conversationBmob.setContent(content);
        conversationBmob.setUpdateTime(updateTime);
        conversationBmob.setImgRes(imgRes);
        conversationBmob.setIgnore(ignore);
        conversationBmob.setBadge(isBadge);
        conversationBmob.setPublic(isPublic);
        conversationBmob.setPointToUser(pointToUser);
        return conversationBmob;
    }
}
