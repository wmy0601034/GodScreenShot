package com.nanningzhuanqian.vscreenshot.item;

import android.support.annotation.NonNull;

import com.nanningzhuanqian.vscreenshot.adapter.ChangeDetailAdapter;
import com.nanningzhuanqian.vscreenshot.model.ChangeDetail;

/**
 * 零钱明细
 * Created by WMY on 2018/9/18.
 */

public class ChangeDetailItem implements Comparable{

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

    public ChangeDetailItem(int type, String name,String amount, long tranTime) {
        this.type = type;
        this.name = name;
        this.amount = amount;
        this.tranTime = tranTime;
    }

    public ChangeDetailItem(int type,String amount, long tranTime) {
        this.type = type;
        this.amount = amount;
        this.tranTime = tranTime;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ChangeDetail convertToChangeDetail(){
        return new ChangeDetail(type,name,amount,tranTime);
    }

    @Override
    public int compareTo(@NonNull Object o) {
        ChangeDetailItem item = (ChangeDetailItem)o;
        return (int)this.tranTime-(int)item.getTranTime();
    }
}
