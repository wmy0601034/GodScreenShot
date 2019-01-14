package com.nanningzhuanqian.vscreenshot.base.bean;

import android.text.TextUtils;

import com.nanningzhuanqian.vscreenshot.item.DataStorageImpl;

import java.util.Collections;

/**
 * 微信好友标签的单例
 * Created by lenovo on 2019/1/14.
 */

public class Tags extends DataStorageImpl<Tag> {

    private static Tags mThis;

    private Tags() {
        super();
    }

    public static Tags getInstance() {

        if (mThis == null)
            mThis = new Tags();

        return mThis;
    }

    public void resetSelection(){
        for (int i = 0;i<size();i++) {
            Tag tag = get(i);
            if (TagsCur.getInstance().has(tag)) {
                get(i).setSelected(true);
            } else {
                get(i).setSelected(false);
            }
        }
    }

    public boolean has(String name){
        for (int i = 0;i<size();i++) {
            Tag tag = get(i);
            if(TextUtils.equals(name,tag.getName())){
                return true;
            }
        }
        return false;
    }

}
