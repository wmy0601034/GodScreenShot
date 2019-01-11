package com.nanningzhuanqian.vscreenshot.base.bean;

/**
 * 朋友圈评论 以条为单位
 * Created by lenovo on 2019/1/11.
 */

public class Comment {

    //评论发送的人
    private Contact sender;
    //评论时间戳
    private long commentTimeMillis;
    //评论显示时间
    private String commentDisplayTime;
    //评论的朋友圈
    private Moment commentToMoment;
    //评论回复的对象
    private Contact commentToPeople;

    public Contact getSender() {
        return sender;
    }

    public void setSender(Contact sender) {
        this.sender = sender;
    }

    public long getCommentTimeMillis() {
        return commentTimeMillis;
    }

    public void setCommentTimeMillis(long commentTimeMillis) {
        this.commentTimeMillis = commentTimeMillis;
    }

    public String getCommentDisplayTime() {
        return commentDisplayTime;
    }

    public void setCommentDisplayTime(String commentDisplayTime) {
        this.commentDisplayTime = commentDisplayTime;
    }

    public Moment getCommentToMoment() {
        return commentToMoment;
    }

    public void setCommentToMoment(Moment commentToMoment) {
        this.commentToMoment = commentToMoment;
    }

    public Contact getCommentToPeople() {
        return commentToPeople;
    }

    public void setCommentToPeople(Contact commentToPeople) {
        this.commentToPeople = commentToPeople;
    }
}
