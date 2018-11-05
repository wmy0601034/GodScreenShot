package com.nanningzhuanqian.vscreenshot.item;

/**
 * 微信聊天页面里的更多选项
 */
public class WechatSingleChatMoreItem {

    private String imgUrl;
    private int imgRes;
    private String name;
    private int type;

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public int getImgRes() {
        return imgRes;
    }

    public void setImgRes(int imgRes) {
        this.imgRes = imgRes;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public WechatSingleChatMoreItem(int imgRes, String name, int type) {
        this.imgRes = imgRes;
        this.name = name;
        this.type = type;
    }

    public WechatSingleChatMoreItem(String imgUrl, String name, int type) {
        this.imgUrl = imgUrl;
        this.name = name;
        this.type = type;
    }
}
