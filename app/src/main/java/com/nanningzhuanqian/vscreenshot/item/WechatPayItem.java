package com.nanningzhuanqian.vscreenshot.item;

/**
 * 微信支付记录
 */
public class WechatPayItem {

    private String amout;
    private int type;
    private int status;
    private long tranTime;
    private String bankCardNo;

    public String getAmout() {
        return amout;
    }

    public void setAmout(String amout) {
        this.amout = amout;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public long getTranTime() {
        return tranTime;
    }

    public void setTranTime(long tranTime) {
        this.tranTime = tranTime;
    }

    public String getBankCardNo() {
        return bankCardNo;
    }

    public void setBankCardNo(String bankCardNo) {
        this.bankCardNo = bankCardNo;
    }


}
