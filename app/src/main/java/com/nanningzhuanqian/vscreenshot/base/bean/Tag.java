package com.nanningzhuanqian.vscreenshot.base.bean;

import java.util.List;

/**
 * 微信好友标签 用于分组
 * Created by lenovo on 2019/1/11.
 */

public class Tag {

    //数据库id
    private String id;
    //标签名称
    private String name;
    //标签下对应的联系人名单
    private List<Contact> contactList;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Contact> getContactList() {
        return contactList;
    }

    public void setContactList(List<Contact> contactList) {
        this.contactList = contactList;
    }
}
