package com.nanningzhuanqian.vscreenshot.base.bean;

/**
 * 微信小程序
 * Created by lenovo on 2019/1/11.
 */

public class MiniProgram {
    //数据库id
    private String id;
    //小程序图标 必须从网络拉取
    private String iconUrl;
    //小程序名称
    private String name;
    //点击跳转的url
    private String url;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
