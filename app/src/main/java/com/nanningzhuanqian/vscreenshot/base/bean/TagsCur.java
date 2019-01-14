package com.nanningzhuanqian.vscreenshot.base.bean;

import com.nanningzhuanqian.vscreenshot.item.DataStorageImpl;

/**
 * 当前正在使用的一个标签集合
 * Created by lenovo on 2019/1/15.
 */

public class TagsCur extends DataStorageImpl<Tag> {

    private static TagsCur mThis;

    private TagsCur() {
        super();
    }

    public static TagsCur getInstance() {

        if (mThis == null)
            mThis = new TagsCur();

        return mThis;
    }

    public boolean has(Tag tag){
        for (int i = 0;i<size();i++){
           if(tag.getName().equals(get(i).getName())){
               return true;
           }
        }
        return false;
    }

}