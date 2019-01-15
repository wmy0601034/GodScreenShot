package com.nanningzhuanqian.vscreenshot.model;

import com.nanningzhuanqian.vscreenshot.item.WechatWalletItem;


public class WechatWalletConfigLite {

    private Integer type;
    private String name;
    private String imgUrl;
    private String configType;

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public String getConfigType() {
        return configType;
    }

    public void setConfigType(String configType) {
        this.configType = configType;
    }

    public WechatWalletConfigLite(Integer type, String name, String imgUrl, String configType) {
        this.type = type;
        this.name = name;
        this.imgUrl = imgUrl;
        this.configType = configType;
    }

    public WechatWalletItem coverToWechatWalletBaseItem(){
        return new WechatWalletItem(type,name,imgUrl,configType);
    }
}
