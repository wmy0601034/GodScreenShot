package com.nanningzhuanqian.vscreenshot.item;

/**
 * 微信账单
 * Created by WMY on 2018/9/18.
 */

public class WechatBillItem {

    private boolean isNoMoreData = false;
    private int iconLeftImage;
    private String tranGroupName;
    private String tranNum;
    private String tranName;
    private String tranAmount;
    private String tranTime;
    private String tranStatus;
    private String tranStatusName;
    private String tranType;
    private String channelCode;
    private String balance;
    private String promotionType;
    private String orderAmount;
    private String iconUrl;
    private String month;


    public WechatBillItem(String month,int iconLeftImage, String tranNum, String tranName, String tranAmount, String tranTime, String tranStatus, String tranType, String channelCode, String tranGroupName, String tranStatusName,String promotionType,String orderAmount) {
        this.month = month;
        this.iconLeftImage = iconLeftImage;
        this.tranNum = tranNum;
        this.tranName = tranName;
        this.tranAmount = tranAmount;
        this.tranTime = tranTime;
        this.tranStatus = tranStatus;
        this.tranStatusName = tranStatusName;
        this.tranType = tranType;
        this.channelCode = channelCode;
        this.tranGroupName = tranGroupName;
        this.promotionType = promotionType;
        this.orderAmount = orderAmount;
    }

    public String getIconUrl() {
        return iconUrl;
    }

    public void setIconUrl(String iconUrl) {
        this.iconUrl = iconUrl;
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public boolean isNoMoreData() {
        return isNoMoreData;
    }

    public void setIsNoMoreData(boolean isNoMoreData) {
        this.isNoMoreData = isNoMoreData;
    }

    public String getTranStatusName() {
        return tranStatusName;
    }

    public int getIconLeftImage() {
        return iconLeftImage;
    }

    public String getTranNum() {
        return tranNum;
    }

    public String getTranName() {
        return tranName;
    }

    public String getTranAmount() {
        return tranAmount;
    }

    public String getTranTime() {
        return tranTime;
    }

    public String getTranStatus() {
        return tranStatus;
    }

    public String getTranType() {
        return tranType;
    }

    public String getChannelCode() {
        return channelCode;
    }

    public String getTranGroupName() {
        return tranGroupName;
    }


    public String getBalance() {
        return balance;
    }

    public void setBalance(String balance) {
        this.balance = balance;
    }

    public String getPromotionType(){
        return promotionType;
    }

    public String getOrderAmount(){
        return orderAmount;
    }

}
