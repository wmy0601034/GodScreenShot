package com.nanningzhuanqian.vscreenshot.item;

import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

/**
 * 微信每条朋友圈
 */
public class WechatMomentItem {

    private int publisherImgRes;
    private String publisherName;
    private int type;
    private String textContent;
    private String linkContent;
    private int linkIcon;
    private String linkFrom;
    private String location;
    private List<Integer> picRes = new ArrayList<>();
    private int VideoRes;


    public WechatMomentItem(int publisherImgRes, String publisherName, int type) {
        this.publisherImgRes = publisherImgRes;
        this.publisherName = publisherName;
        this.type = type;
    }

    public int getPublisherImgRes() {
        return publisherImgRes;
    }

    public void setPublisherImgRes(int publisherImgRes) {
        this.publisherImgRes = publisherImgRes;
    }

    public String getPublisherName() {
        return publisherName;
    }

    public void setPublisherName(String publisherName) {
        this.publisherName = publisherName;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getTextContent() {
        return textContent;
    }

    public void setTextContent(String textContent) {
        this.textContent = textContent;
    }

    public String getLinkContent() {
        return linkContent;
    }

    public void setLinkContent(String linkContent) {
        this.linkContent = linkContent;
    }

    public int getLinkIcon() {
        return linkIcon;
    }

    public void setLinkIcon(int linkIcon) {
        this.linkIcon = linkIcon;
    }

    public String getLinkFrom() {
        return linkFrom;
    }

    public void setLinkFrom(String linkFrom) {
        this.linkFrom = linkFrom;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public List<Integer> getPicRes() {
        return picRes;
    }

    public void setPicRes(List<Integer> picRes) {
        this.picRes = picRes;
    }

    public int getVideoRes() {
        return VideoRes;
    }

    public void setVideoRes(int videoRes) {
        VideoRes = videoRes;
    }
}
