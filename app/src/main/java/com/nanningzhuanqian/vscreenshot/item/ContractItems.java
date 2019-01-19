package com.nanningzhuanqian.vscreenshot.item;

import com.nanningzhuanqian.vscreenshot.R;
import com.nanningzhuanqian.vscreenshot.adapter.ContactAdapter;

import java.util.List;

public class ContractItems extends DataStorageImpl<ContractItem> {

    private static ContractItems mThis;

    private ContractItems() {
        super();
    }

    public static ContractItems getInstance() {

        if (mThis == null)
            mThis = new ContractItems();

        return mThis;
    }

    public static List<ContractItem> getWithOutTop(){
        mThis.remove(0);
        mThis.remove(0);
        mThis.remove(0);
        mThis.remove(0);
        return mThis.get();
    }

    public static void initTop(){
        ContractItems.getInstance().addFirst(new ContractItem(ContactAdapter.ITEM_COMMON_TYPE, "公众号", R.mipmap.app_views_pages_wechat_home_images_contacticon4));

        ContractItems.getInstance().addFirst(new ContractItem(ContactAdapter.ITEM_COMMON_TYPE, "标签", R.mipmap.app_views_pages_wechat_home_images_contacticon3));
        ContractItems.getInstance().addFirst(new ContractItem(ContactAdapter.ITEM_COMMON_TYPE, "群聊", R.mipmap.app_views_pages_wechat_home_images_contacticon2));
        if(WechatNewFriendItems.getInstance().size()==0) {
            ContractItems.getInstance().addFirst(new ContractItem(ContactAdapter.ITEM_COMMON_TYPE, "新的朋友", R.mipmap.app_views_pages_wechat_home_images_contacticon1));
        }else{
            ContractItems.getInstance().addFirst(new ContractItem(ContactAdapter.ITEM_NEW_FRIEND, "新的朋友", R.mipmap.app_views_pages_wechat_home_images_contacticon1));
        }
    }

}
