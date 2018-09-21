package com.nanningzhuanqian.vscreenshot.model;

import cn.bmob.v3.Bmob;
import cn.bmob.v3.BmobObject;

public class Feedback extends BmobObject{

    private String mobile;
    private String content;

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Feedback() {
    }

    public Feedback(String mobile, String content) {
        this.mobile = mobile;
        this.content = content;
    }
}
