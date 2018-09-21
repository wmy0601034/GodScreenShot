package com.nanningzhuanqian.vscreenshot.model;

import cn.bmob.v3.BmobUser;

public class User extends BmobUser{

    private int status;
    private int level;
    private int expires;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public int getExpires() {
        return expires;
    }

    public void setExpires(int expires) {
        this.expires = expires;
    }
}
