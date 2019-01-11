package com.nanningzhuanqian.vscreenshot.base.bean;

/**
 * 银行卡
 */
public class BankCard {
    //借记卡
    public static final int TYPE_DEBIT_CARD = 0;
    //信用卡
    public static final int TYPE_CREDIT_CARD = 1;

    //小图标路径
    private String logoUrl;
    //大图标路径
    private String logo2Url;
    //背景图片
    private String bgUrl;
    //银行名称
    private String bankName;
    //银行卡号
    private String bankCardNo;
    //银行卡类型 0借记卡 1信用卡
    private int cardType;

    public String getLogoUrl() {
        return logoUrl;
    }

    public void setLogoUrl(String logoUrl) {
        this.logoUrl = logoUrl;
    }

    public String getLogo2Url() {
        return logo2Url;
    }

    public void setLogo2Url(String logo2Url) {
        this.logo2Url = logo2Url;
    }

    public String getBgUrl() {
        return bgUrl;
    }

    public void setBgUrl(String bgUrl) {
        this.bgUrl = bgUrl;
    }


}
