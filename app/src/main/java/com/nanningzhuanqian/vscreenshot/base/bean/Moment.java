package com.nanningzhuanqian.vscreenshot.base.bean;

import java.util.List;

/**
 * 朋友圈 以条为单位
 * Created by lenovo on 2019/1/11.
 */

public class Moment {

    //纯文本（包括表情）
    public static final int TYPE_TEXT = 0;
    //图片
    public static final int TYPE_PICTRUE = 1;
    //小视频
    public static final int TYPE_VEDIO = 2;
    //链接
    public static final int TYPE_LINK = 3;
    //歌曲分享
    public static final int TYPE_MUSIC = 4;
    //本地图片
    public static final int ICON_TYPE_RESOURCE = 0;
    //网络图片
    public static final int ICON_TYPE_NETWORK = 1;
    //数据库id
    private String id;
    //发布者
    private Contact publisher;
    //朋友圈类型 0文本 1图片 2 视频 3链接 4音乐
    private int type;
    //预览图片类型  视频 链接 音乐都加这个  0 本地图片资源 1 网络图片
    private int previewImgType;
    //本地图片资源
    private int previewImgRes;
    //网络图片url
    private String previewImgUrl;
    //分享内容的简介
    private String description;
    //朋友圈文字
    private String text;
    //发布时间戳
    private long publishTimeMillis;
    //发布日期
    private String publishDate;
    //地点
    private String location;
    //点赞联系人的集合
    private List<Contact> likeContactList;
    //评论集合
    private List<Comment> commentList;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Contact getPublisher() {
        return publisher;
    }

    public void setPublisher(Contact publisher) {
        this.publisher = publisher;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getPreviewImgType() {
        return previewImgType;
    }

    public void setPreviewImgType(int previewImgType) {
        this.previewImgType = previewImgType;
    }

    public int getPreviewImgRes() {
        return previewImgRes;
    }

    public void setPreviewImgRes(int previewImgRes) {
        this.previewImgRes = previewImgRes;
    }

    public String getPreviewImgUrl() {
        return previewImgUrl;
    }

    public void setPreviewImgUrl(String previewImgUrl) {
        this.previewImgUrl = previewImgUrl;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public long getPublishTimeMillis() {
        return publishTimeMillis;
    }

    public void setPublishTimeMillis(long publishTimeMillis) {
        this.publishTimeMillis = publishTimeMillis;
    }

    public String getPublishDate() {
        return publishDate;
    }

    public void setPublishDate(String publishDate) {
        this.publishDate = publishDate;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public List<Contact> getLikeContactList() {
        return likeContactList;
    }

    public void setLikeContactList(List<Contact> likeContactList) {
        this.likeContactList = likeContactList;
    }

    public List<Comment> getCommentList() {
        return commentList;
    }

    public void setCommentList(List<Comment> commentList) {
        this.commentList = commentList;
    }
}
