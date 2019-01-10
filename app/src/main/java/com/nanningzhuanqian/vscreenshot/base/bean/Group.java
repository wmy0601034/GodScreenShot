package com.nanningzhuanqian.vscreenshot.base.bean;

import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;

import java.util.List;

/**
 * 微信群组
 * Created by lenovo on 2019/1/11.
 */

public class Group {

    //默认
    public static final int TYPE_CHAT_BACKGROUND_DEFAULT = 0;
    //官方本地图片
    public static final int TYPE_CHAT_BACKGROUND_RESOURCE = 1;
    //拍照上传后的图片
    public static final int TYPE_CHAT_BACKGROUND_NETWORK = 2;
    //数据库id
    private String id;
    //群组名称
    private String name;
    //群组图标
    private Drawable icon;
    //群二维码
    private Bitmap groupQrcode;
    //群公告
    private String notice;
    //群主 关联联系人
    private Contact groupOwner;
    //聊天小程序
    private List<MiniProgram> miniProgramList;
    //是否置顶
    private boolean isImportant;
    //是否保存到通讯录
    private boolean isSaveToContactList;
    //我在本群的昵称
    private String userNickname;
    //是否显示成员昵称
    private boolean isShowMemberNickname;
    //聊天背景图片类型 0默认 1 官方背景 2 网络
    private int chatBackgroundType;
    //官方聊天背景图片本地资源id
    private int chatBackgroundRes;
    //网络聊天背景图片url
    private String chatBackgroundUrl;

    //是否免打扰
    private boolean isIgnore;
    //关联的聊天记录
    private List<ChatRecord> chatRecordList;
    //关联的群组成员列表
    private List<Contact> contactList;

    public Group(){

    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Drawable getIcon() {
        return icon;
    }

    public void setIcon(Drawable icon) {
        this.icon = icon;
    }

    public Bitmap getGroupQrcode() {
        return groupQrcode;
    }

    public void setGroupQrcode(Bitmap groupQrcode) {
        this.groupQrcode = groupQrcode;
    }

    public String getNotice() {
        return notice;
    }

    public void setNotice(String notice) {
        this.notice = notice;
    }

    public Contact getGroupOwner() {
        return groupOwner;
    }

    public void setGroupOwner(Contact groupOwner) {
        this.groupOwner = groupOwner;
    }

    public List<MiniProgram> getMiniProgramList() {
        return miniProgramList;
    }

    public void setMiniProgramList(List<MiniProgram> miniProgramList) {
        this.miniProgramList = miniProgramList;
    }

    public boolean isImportant() {
        return isImportant;
    }

    public void setImportant(boolean important) {
        isImportant = important;
    }

    public boolean isSaveToContactList() {
        return isSaveToContactList;
    }

    public void setSaveToContactList(boolean saveToContactList) {
        isSaveToContactList = saveToContactList;
    }

    public String getUserNickname() {
        return userNickname;
    }

    public void setUserNickname(String userNickname) {
        this.userNickname = userNickname;
    }

    public boolean isShowMemberNickname() {
        return isShowMemberNickname;
    }

    public void setShowMemberNickname(boolean showMemberNickname) {
        isShowMemberNickname = showMemberNickname;
    }

    public int getChatBackgroundType() {
        return chatBackgroundType;
    }

    public void setChatBackgroundType(int chatBackgroundType) {
        this.chatBackgroundType = chatBackgroundType;
    }

    public int getChatBackgroundRes() {
        return chatBackgroundRes;
    }

    public void setChatBackgroundRes(int chatBackgroundRes) {
        this.chatBackgroundRes = chatBackgroundRes;
    }

    public String getChatBackgroundUrl() {
        return chatBackgroundUrl;
    }

    public void setChatBackgroundUrl(String chatBackgroundUrl) {
        this.chatBackgroundUrl = chatBackgroundUrl;
    }

    public boolean isIgnore() {
        return isIgnore;
    }

    public void setIgnore(boolean ignore) {
        isIgnore = ignore;
    }

    public List<Contact> getContactList() {
        return contactList;
    }

    public void setContactList(List<Contact> contactList) {
        this.contactList = contactList;
    }

    public List<ChatRecord> getChatRecordList() {
        return chatRecordList;
    }

    public void setChatRecordList(List<ChatRecord> chatRecordList) {
        this.chatRecordList = chatRecordList;
    }
}
