package com.nanningzhuanqian.vscreenshot.model;

import com.nanningzhuanqian.vscreenshot.item.ConversationItem;
import com.nanningzhuanqian.vscreenshot.item.ConversationItems;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by WMY on 2018/9/14.
 */

public class ConversationFactory {

    private static ConversationFactory mThis;

    private ConversationFactory() {
        super();
    }

    public static ConversationFactory getInstance() {

        if (mThis == null)
            mThis = new ConversationFactory();

        return mThis;
    }

    public ConversationItem createSingle() {

        ConversationItem item = new ConversationItem();
        //从随机池里面取一个名字
        return item;
    }

    public List<ConversationItem> create0() {
        List<ConversationItem> items = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            ConversationItem item = new ConversationItem();
        }
        return items;
    }


}
