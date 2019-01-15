package com.nanningzhuanqian.vscreenshot.base.bean;


import java.util.List;

/**
 * 微信好友标签 用于分组
 * Created by lenovo on 2019/1/11.
 */

public class Tag {
    //id
    private int id;
    //标签名称
    private String name;
    //标签下对应的联系人名单
    private List<Contact> contactList;
    //是否被选中 在选择标签时用
    private boolean isSelected;

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

    public boolean isSelected() {
        return isSelected;
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
