package com.nanningzhuanqian.vscreenshot.base.bean;

import android.support.annotation.NonNull;

import org.litepal.crud.LitePalSupport;

/**
 * 微信首页 对话
 * Created by lenovo on 2019/1/11.
 */

public class Conversation extends LitePalSupport implements Comparable {

    //单人聊天
    public static final int TYPE_SINGLE_CHAT = 0;
    //群聊
    public static final int TYPE_GROUP_CHAT = 1;
    //微信服务号
    public static final int TYPE_WECHAT_SERVICE = 2;
    //微信系统功能
    public static final int TYPE_WECHAT_SYSTEM = 3;
    //订阅号
    public static final int TYPE_WECHAT_SUBCRIBE = 4;
    //本地图片
    public static final int ICON_TYPE_RESOURCE = 0;
    //网络图片
    public static final int ICON_TYPE_NETWORK = 1;
    //数据库id
    private String id;
    //图标类型 0 本地 1 网络
    private int iconType;
    //本地图片资源ID
    private int iconRes;
    //网络图片url
    private String iconUrl;
    //对话显示的名称
    private String name;
    //显示的角标数
    private int badgeCount;
    //更新时间
    private long updateTime;
    //显示在微信会话列表的内容
    private String displayContent;
    //是否显示免打扰图标
    private boolean ignore;
    //是否置顶
    private boolean isImportant;
    //关联的单人聊天
    private Contact contact;
    //关联的群组
    private Group group;

    private int type;

    public Conversation() {

    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getIconType() {
        return iconType;
    }

    public void setIconType(int iconType) {
        this.iconType = iconType;
    }

    public int getIconRes() {
        return iconRes;
    }

    public void setIconRes(int iconRes) {
        this.iconRes = iconRes;
    }

    public String getIconUrl() {
        return iconUrl;
    }

    public void setIconUrl(String iconUrl) {
        this.iconUrl = iconUrl;
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

    public String getDisplayContent() {
        return displayContent;
    }

    public void setDisplayContent(String displayContent) {
        this.displayContent = displayContent;
    }

    public boolean isIgnore() {
        return ignore;
    }

    public void setIgnore(boolean ignore) {
        this.ignore = ignore;
    }

    public boolean isImportant() {
        return isImportant;
    }

    public void setImportant(boolean important) {
        isImportant = important;
    }

    public Contact getContact() {
        return contact;
    }

    public void setContact(Contact contact) {
        this.contact = contact;
    }

    public Group getGroup() {
        return group;
    }

    public void setGroup(Group group) {
        this.group = group;
    }

    @Override
    public int compareTo(@NonNull Object o) {
        Conversation item = (Conversation) o;
        return (int) item.getUpdateTime() - (int) this.updateTime;
    }
}
