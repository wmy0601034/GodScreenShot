package com.nanningzhuanqian.vscreenshot.base.bean;

import android.net.Uri;


import java.util.List;

/**
 * 通讯录里的联系人
 * Created by lenovo on 2019/1/11.
 */

public class Contact{

    //本地图片
    public static final int ICON_TYPE_RESOURCE = 0;
    //网络图片
    public static final int ICON_TYPE_NETWORK = 1;
    //男
    public static final int GENDER_MALE = 0;
    //女
    public static final int GENDER_FEMALE = 1;
    //保密
    public static final int GENDER_PRIVATE = 2;
    //数据库id
    private String id;
    //图标类型 0 本地 1 网络
    private int iconType;
    //本地图片资源ID
    private int iconRes;
    //网络图片url
    private String iconUrl;
    //聊天小程序
    private List<MiniProgram> miniProgramList;
    //是否免打扰
    private boolean isIgnore;
    //是否置顶
    private boolean isImportant;
    //是否强提醒
    private boolean isStrongReminder;
    //聊天背景图片类型 0默认 1 官方背景 2 网络
    private int chatBackgroundType;
    //官方聊天背景图片本地资源id
    private int chatBackgroundRes;
    //网络聊天背景图片url
    private String chatBackgroundUrl;
    //关联的聊天记录
    private List<ChatRecord> chatRecordList;
    //显示的名字（备注）
    private String remarkName;
    //微信昵称
    private String wechatNickName;
    //微信号
    private String wechatAccount;
    //地区
    private String wechatAddress;
    //给该联系人添加的标签
    private List<Tag> tagList;
    //电话号码
    private String mobile;
    //描述
    private String description;
    //朋友圈集合
    private List<Moment> momentList;
    //共同的群聊
    private List<Group> commonGroupList;
    //个性签名
    private String personalitySign;
    //来源
    private String source;
    //是否星标好友
    private boolean isStarContact;
    //是否不让他看我
    private boolean isNoAccess;
    //是否不看他
    private boolean isAvoid;
    //朋友圈背景图类型 0 本地 1网络
    private int momentBackgroundType;
    //朋友圈背景图本地资源id
    private int momentBackgroundRes;
    //朋友圈背景图网络图片url
    private int momentBackgroundUrl;
    //男女
    private int gender;

    private String pointToUser;

    //共同好友个数
    private int commonGroup;
    //来源
    private int fromType;
    //0 来自QQ好友
    public static final int FROM_QQ = 0;
    //1通过搜索手机号添加
    public static final int FROM_MOBILE = 1;
    //2通过群聊添加
    public static final int FROM_GROUP = 2;
    //3 通过手机通讯录添加
    public static final int FROM_PHONE_CONTACT = 3;
    //4 通过扫一扫添加
    public static final int FROM_QRCODE = 4;
    //5通过名片分享添加
    public static final int FROM_CARD_SHARE = 5;
    //6通过附近的人添加
    public static final int FROM_NEAR = 6;
    //7通过摇一摇添加
    public static final int FROM_SHAKE = 7;
    //8通过漂流瓶添加
    public static final int FROM_DRIFT = 8;
    //9 无
    public static final int FROM_NONE = 9;

    public int getCommonGroup() {
        return commonGroup;
    }

    public void setCommonGroup(int commonGroup) {
        this.commonGroup = commonGroup;
    }

    public int getFromType() {
        return fromType;
    }

    public void setFromType(int fromType) {
        this.fromType = fromType;
    }

    public String getPointToUser() {
        return pointToUser;
    }

    public void setPointToUser(String pointToUser) {
        this.pointToUser = pointToUser;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }


    public List<ChatRecord> getChatRecordList() {
        return chatRecordList;
    }

    public void setChatRecordList(List<ChatRecord> chatRecordList) {
        this.chatRecordList = chatRecordList;
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

    public List<MiniProgram> getMiniProgramList() {
        return miniProgramList;
    }

    public void setMiniProgramList(List<MiniProgram> miniProgramList) {
        this.miniProgramList = miniProgramList;
    }

    public boolean isIgnore() {
        return isIgnore;
    }

    public void setIgnore(boolean ignore) {
        isIgnore = ignore;
    }

    public boolean isImportant() {
        return isImportant;
    }

    public void setImportant(boolean important) {
        isImportant = important;
    }

    public boolean isStrongReminder() {
        return isStrongReminder;
    }

    public void setStrongReminder(boolean strongReminder) {
        isStrongReminder = strongReminder;
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

    public String getRemarkName() {
        return remarkName;
    }

    public void setRemarkName(String remarkName) {
        this.remarkName = remarkName;
    }

    public String getWechatNickName() {
        return wechatNickName;
    }

    public void setWechatNickName(String wechatNickName) {
        this.wechatNickName = wechatNickName;
    }

    public String getWechatAccount() {
        return wechatAccount;
    }

    public void setWechatAccount(String wechatAccount) {
        this.wechatAccount = wechatAccount;
    }

    public String getWechatAddress() {
        return wechatAddress;
    }

    public void setWechatAddress(String wechatAddress) {
        this.wechatAddress = wechatAddress;
    }

    public int getGender() {
        return gender;
    }

    public void setGender(int gender) {
        this.gender = gender;
    }

    public List<Tag> getTagList() {
        return tagList;
    }

    public void setTagList(List<Tag> tagList) {
        this.tagList = tagList;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Moment> getMomentList() {
        return momentList;
    }

    public void setMomentList(List<Moment> momentList) {
        this.momentList = momentList;
    }

    public List<Group> getCommonGroupList() {
        return commonGroupList;
    }

    public void setCommonGroupList(List<Group> commonGroupList) {
        this.commonGroupList = commonGroupList;
    }

    public String getPersonalitySign() {
        return personalitySign;
    }

    public void setPersonalitySign(String personalitySign) {
        this.personalitySign = personalitySign;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public boolean isStarContact() {
        return isStarContact;
    }

    public void setStarContact(boolean starContact) {
        isStarContact = starContact;
    }

    public boolean isNoAccess() {
        return isNoAccess;
    }

    public void setNoAccess(boolean noAccess) {
        isNoAccess = noAccess;
    }

    public boolean isAvoid() {
        return isAvoid;
    }

    public void setAvoid(boolean avoid) {
        isAvoid = avoid;
    }

    public int getMomentBackgroundType() {
        return momentBackgroundType;
    }

    public void setMomentBackgroundType(int momentBackgroundType) {
        this.momentBackgroundType = momentBackgroundType;
    }

    public int getMomentBackgroundRes() {
        return momentBackgroundRes;
    }

    public void setMomentBackgroundRes(int momentBackgroundRes) {
        this.momentBackgroundRes = momentBackgroundRes;
    }

    public int getMomentBackgroundUrl() {
        return momentBackgroundUrl;
    }

    public void setMomentBackgroundUrl(int momentBackgroundUrl) {
        this.momentBackgroundUrl = momentBackgroundUrl;
    }
}
