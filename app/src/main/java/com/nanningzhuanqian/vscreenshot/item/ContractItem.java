package com.nanningzhuanqian.vscreenshot.item;

import com.nanningzhuanqian.vscreenshot.model.ContractBmob;
import com.nanningzhuanqian.vscreenshot.model.ContractLite;

public class ContractItem{

    private int type;   // 根据ContractAdapter里面的来
    private String initial;
    private String name;
    private int imgRes;
    private String imgUrl;
    private int imgType;    // 0 本地图片 1 来自网络
    private String letters;//显示拼音的首字母
    private String realName;    //真名
    private String pointToUser; //指向某个用户的手机

    public String getLetters() {
        return letters;
    }

    public void setLetters(String letters) {
        this.letters = letters;
    }

    public ContractItem(int type, String name, int imgRes, String imgUrl, int imgType) {
        this.type = type;
        this.name = name;
        this.imgRes = imgRes;
        this.imgUrl = imgUrl;
        this.imgType = imgType;
    }

    public ContractItem(int type, String name, int imgRes) {
        this.type = type;
        this.name = name;
        this.imgRes = imgRes;
    }

    public ContractItem(int type, String name, int imgRes,String pointToUser) {
        this.type = type;
        this.name = name;
        this.imgRes = imgRes;
        this.pointToUser = pointToUser;
    }

    public ContractItem() {
    }

    public String getPointToUser() {
        return pointToUser;
    }

    public void setPointToUser(String pointToUser) {
        this.pointToUser = pointToUser;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getInitial() {
        return initial;
    }

    public void setInitial(String initial) {
        this.initial = initial;
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

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public int getImgType() {
        return imgType;
    }

    public void setImgType(int imgType) {
        this.imgType = imgType;
    }

    public ContractLite convertToLite(){
        return new ContractLite(type,name,imgRes,pointToUser);
    }

    public ContractBmob convertToBmob(){
        return new ContractBmob(type,name,imgRes,pointToUser);
    }

}
