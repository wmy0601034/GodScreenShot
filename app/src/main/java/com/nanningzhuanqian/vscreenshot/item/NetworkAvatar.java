package com.nanningzhuanqian.vscreenshot.item;

import cn.bmob.v3.BmobObject;

/**
 * 在线头像
 * Created by lenovo on 2019/1/13.
 */

public class NetworkAvatar extends BmobObject {

    private String imgUrl;

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }
}
