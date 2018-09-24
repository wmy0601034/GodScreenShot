package com.nanningzhuanqian.vscreenshot.model;

import com.nanningzhuanqian.vscreenshot.item.WechatChatItem;

import org.litepal.crud.LitePalSupport;

public class WechatChatLite extends LitePalSupport {

    private int type;
    private String name;
    private int imgRes;
    private String content;
    private int imgType;
    private String imgUrl;
    private String pointToName;

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getImgRes() {
        return imgRes;
    }

    public void setImgRes(int imgRes) {
        this.imgRes = imgRes;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getImgType() {
        return imgType;
    }

    public void setImgType(int imgType) {
        this.imgType = imgType;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public String getPointToName() {
        return pointToName;
    }

    public void setPointToName(String pointToName) {
        this.pointToName = pointToName;
    }

    public WechatChatLite(int type, String name, int imgRes, String content, String pointToName) {
        this.type = type;
        this.name = name;
        this.imgRes = imgRes;
        this.content = content;
        this.pointToName = pointToName;
    }

    public WechatChatItem convertToWechatChatItem(){
        return new WechatChatItem(type,name,imgRes,content,pointToName);
    }


}
