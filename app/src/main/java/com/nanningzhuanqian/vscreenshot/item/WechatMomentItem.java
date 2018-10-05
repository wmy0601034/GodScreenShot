package com.nanningzhuanqian.vscreenshot.item;

public class WechatMomentItem {

    private int publisherImgRes;
    private String publisherName;
    private int type;
    private String textContent;

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


}
