package com.nanningzhuanqian.vscreenshot.model;

import com.nanningzhuanqian.vscreenshot.item.ChangeDetailItem;
import com.nanningzhuanqian.vscreenshot.item.ContractItem;


/**
 * Created by WMY on 2018/9/18.
 */

public class ChangeDetail {

    private int type;
    private String name;
    private String amount;
    private long tranTime;

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public long getTranTime() {
        return tranTime;
    }

    public void setTranTime(long tranTime) {
        this.tranTime = tranTime;
    }

    public ChangeDetail(int type, String name,String amount, long tranTime) {
        this.type = type;
        this.name = name;
        this.amount = amount;
        this.tranTime = tranTime;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ChangeDetailItem convertToChangeDetailItem(){
        return new ChangeDetailItem(type,name,amount,tranTime);
    }
}
