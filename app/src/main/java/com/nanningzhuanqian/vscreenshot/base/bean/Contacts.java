package com.nanningzhuanqian.vscreenshot.base.bean;

import com.nanningzhuanqian.vscreenshot.adapter.ContactAdapter;
import com.nanningzhuanqian.vscreenshot.item.DataStorageImpl;

import java.util.List;

/**
 * Created by lenovo on 2019/1/19.
 */

public class Contacts extends DataStorageImpl<Contact> {

    private static Contacts mThis;

    private Contacts() {
        super();
    }

    public static Contacts getInstance() {

        if (mThis == null)
            mThis = new Contacts();

        return mThis;
    }

    public List<Contact> getWithOutTop(){
        mThis.remove(0);
        mThis.remove(0);
        mThis.remove(0);
        mThis.remove(0);
        return mThis.get();
    }

    public void initTop(){
        Contact publicItem = new Contact();//公众号 R.mipmap.app_views_pages_wechat_home_images_contacticon4
        publicItem.setType(ContactAdapter.ITEM_GONGZHONGHAO_TYPE);
        addFirst(publicItem);

        Contact tagItem = new Contact();    //标签 R.mipmap.app_views_pages_wechat_home_images_contacticon3)
        tagItem.setType(ContactAdapter.ITEM_TAG_TYPE);
        addFirst(tagItem);

        Contact groupItem = new Contact();  //群聊 R.mipmap.app_views_pages_wechat_home_images_contacticon2)
        groupItem.setType(ContactAdapter.ITEM_GROUP_TYPE);
        addFirst(groupItem);

        Contact newFriendItem = new Contact();  //新的朋友  R.mipmap.app_views_pages_wechat_home_images_contacticon1
        newFriendItem.setType(ContactAdapter.ITEM_NEW_FRIEND);
        addFirst(newFriendItem);

    }

}
