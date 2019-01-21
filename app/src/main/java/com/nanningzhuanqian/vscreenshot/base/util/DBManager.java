package com.nanningzhuanqian.vscreenshot.base.util;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.util.Log;

import com.nanningzhuanqian.vscreenshot.base.bean.Contact;
import com.nanningzhuanqian.vscreenshot.base.bean.Conversation;
import com.nanningzhuanqian.vscreenshot.base.bean.Group;
import com.nanningzhuanqian.vscreenshot.base.bean.Tag;
import com.nanningzhuanqian.vscreenshot.common.Constant;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lenovo on 2019/1/15.
 */

public class DBManager {

    public static void init(Context context){
        MyDB.getInstance(context).init();
    }

    public static final List<Tag> getTags(Context context){
        Cursor cursor = MyDB.getInstance(context).query(MyDB.TABLE_WX_TAG);
        List<Tag> tags = new ArrayList<>();
        while (cursor.moveToNext()) {
            String name = cursor.getString(cursor.getColumnIndex("name"));
            int id = cursor.getInt(cursor.getColumnIndex("id"));
            Tag tag = new Tag();
            tag.setName(name);
            tag.setId(id);
            tags.add(tag);
        }
        cursor.close();
        MyDB.getInstance(context).close();
        return tags;
    }

    public static final void saveTag(Context context,Tag tag){
        String name = tag.getName();
        ContentValues contentValues = new ContentValues();
        contentValues.put("name",name);
        MyDB.getInstance(context).insert(MyDB.TABLE_WX_TAG,contentValues);
    }

    public static final void deleteTag(Context context,Tag tag){
        String id = String.valueOf(tag.getId());
        String name = tag.getName();
        ContentValues contentValues = new ContentValues();
        contentValues.put("id",id);
        contentValues.put("name",name);
        MyDB.getInstance(context).delete(MyDB.TABLE_WX_TAG,"id = ?&name = ?",new String[]{id,name});
    }

    public static final void updateTag(Context context,Tag tag){

    }

    //添加联系人
    public static final long saveContact(Context context, Contact contact){
        String wechatNickName = contact.getWechatNickName();
        String remarkName = contact.getRemarkName();
        String wechatAccount = contact.getWechatAccount();
        String wechatAddress = contact.getWechatAddress();
        int fromType = contact.getFromType();
        String mobile = contact.getMobile();
        String personalitySign = contact.getPersonalitySign();
        int iconType = contact.getIconType();
        int iconRes = contact.getIconRes();
        String iconUrl = contact.getIconUrl();
        int gender = contact.getGender();
        int commonGroup = contact.getCommonGroup();
        String tag = contact.getTag();
        String pointToUser = contact.getPointToUser();
        ContentValues contentValues = new ContentValues();
        contentValues.put("wechatNickName",wechatNickName);
        contentValues.put("remarkName",remarkName);
        contentValues.put("wechatAccount",wechatAccount);
        contentValues.put("wechatAddress",wechatAddress);
        contentValues.put("mobile",mobile);
        contentValues.put("personalitySign",personalitySign);
        contentValues.put("iconType",iconType);
        contentValues.put("iconRes",iconRes);
        contentValues.put("iconUrl",iconUrl);
        contentValues.put("gender",gender);
        contentValues.put("fromType",fromType);
        contentValues.put("commonGroup",commonGroup);
        contentValues.put("tag",tag);
        contentValues.put("pointToUser",pointToUser);
        long id = MyDB.getInstance(context).insert(MyDB.TABLE_WX_CONTACT,contentValues);
        return id;
    }

    public static final int updateContact(Context context,Contact contact){
        int id = contact.getId();
        String wechatNickName = contact.getWechatNickName();
        String remarkName = contact.getRemarkName();
        String wechatAccount = contact.getWechatAccount();
        String wechatAddress = contact.getWechatAddress();
        int fromType = contact.getFromType();
        String mobile = contact.getMobile();
        String personalitySign = contact.getPersonalitySign();
        int iconType = contact.getIconType();
        int iconRes = contact.getIconRes();
        String iconUrl = contact.getIconUrl();
        int gender = contact.getGender();
        int commonGroup = contact.getCommonGroup();
        String tag = contact.getTag();
        String pointToUser = contact.getPointToUser();
        ContentValues contentValues = new ContentValues();
        contentValues.put("wechatNickName",wechatNickName);
        contentValues.put("remarkName",remarkName);
        contentValues.put("wechatAccount",wechatAccount);
        contentValues.put("wechatAddress",wechatAddress);
        contentValues.put("mobile",mobile);
        contentValues.put("personalitySign",personalitySign);
        contentValues.put("iconType",iconType);
        contentValues.put("iconRes",iconRes);
        contentValues.put("iconUrl",iconUrl);
        contentValues.put("gender",gender);
        contentValues.put("fromType",fromType);
        contentValues.put("commonGroup",commonGroup);
        contentValues.put("tag",tag);
        contentValues.put("pointToUser",pointToUser);
        String whereClause = "id = ?";
        String[] whereArgs={String.valueOf(id)};
        return MyDB.getInstance(context).update(MyDB.TABLE_WX_CONTACT,contentValues,whereClause,whereArgs);
    }

    public static final int deleteContact(Context context,String contactId){
        return MyDB.getInstance(context).delete(MyDB.TABLE_WX_TAG,"id = ?",new String[]{contactId});
    }

    public static final Contact getContact(Context context,String contactId){
        String selection = "id = ?";
        String[] selectionArgs = new String[]{contactId};
        Cursor cursor = MyDB.getInstance(context).query(MyDB.TABLE_WX_CONTACT,null,selection,selectionArgs,null,null,null);
        Contact contact = new Contact();
        while (cursor.moveToNext()){
            contact = new Contact();
            int id = cursor.getInt(cursor.getColumnIndex("id"));
            String wechatNickName = cursor.getString(cursor.getColumnIndex("wechatNickName"));
            String remarkName = cursor.getString(cursor.getColumnIndex("remarkName"));
            String wechatAccount = cursor.getString(cursor.getColumnIndex("wechatAccount"));
            String wechatAddress = cursor.getString(cursor.getColumnIndex("wechatAddress"));
            String mobile = cursor.getString(cursor.getColumnIndex("mobile"));
            String personalitySign = cursor.getString(cursor.getColumnIndex("personalitySign"));
            int iconType = cursor.getInt(cursor.getColumnIndex("iconType"));
            int iconRes = cursor.getInt(cursor.getColumnIndex("iconRes"));
            String iconUrl = cursor.getString(cursor.getColumnIndex("iconUrl"));
            int gender = cursor.getInt(cursor.getColumnIndex("gender"));
            int commonGroup = cursor.getInt(cursor.getColumnIndex("commonGroup"));
            int fromType = cursor.getInt(cursor.getColumnIndex("fromType"));
            String tag = cursor.getString(cursor.getColumnIndex("tag"));
            String pointToUser = cursor.getString(cursor.getColumnIndex("pointToUser"));
            contact.setId(id);
            contact.setWechatNickName(wechatNickName);
            contact.setRemarkName(remarkName);
            contact.setWechatAccount(wechatAccount);
            contact.setWechatAddress(wechatAddress);
            contact.setMobile(mobile);
            contact.setPersonalitySign(personalitySign);
            contact.setIconType(iconType);
            contact.setIconRes(iconRes);
            contact.setIconUrl(iconUrl);
            contact.setGender(gender);
            contact.setCommonGroup(commonGroup);
            contact.setTag(tag);
            contact.setFromType(fromType);
            contact.setPointToUser(pointToUser);
            return contact;
        }
        return contact;
    }

    //获取所有联系人
    public static final List<Contact> getContacts(Context context){
        Cursor cursor = MyDB.getInstance(context).query(MyDB.TABLE_WX_CONTACT);
        List<Contact> contacts = new ArrayList<>();
        while (cursor.moveToNext()) {
            int id = cursor.getInt(cursor.getColumnIndex("id"));
            String wechatNickName = cursor.getString(cursor.getColumnIndex("wechatNickName"));
            String remarkName = cursor.getString(cursor.getColumnIndex("remarkName"));
            String wechatAccount = cursor.getString(cursor.getColumnIndex("wechatAccount"));
            String wechatAddress = cursor.getString(cursor.getColumnIndex("wechatAddress"));
            String mobile = cursor.getString(cursor.getColumnIndex("mobile"));
            String personalitySign = cursor.getString(cursor.getColumnIndex("personalitySign"));
            int iconType = cursor.getInt(cursor.getColumnIndex("iconType"));
            int iconRes = cursor.getInt(cursor.getColumnIndex("iconRes"));
            String iconUrl = cursor.getString(cursor.getColumnIndex("iconUrl"));
            int gender = cursor.getInt(cursor.getColumnIndex("gender"));
            int commonGroup = cursor.getInt(cursor.getColumnIndex("commonGroup"));
            int fromType = cursor.getInt(cursor.getColumnIndex("fromType"));
            String tag = cursor.getString(cursor.getColumnIndex("tag"));
            String pointToUser = cursor.getString(cursor.getColumnIndex("pointToUser"));
            Contact contact = new Contact();
            contact.setId(id);
            contact.setWechatNickName(wechatNickName);
            contact.setRemarkName(remarkName);
            contact.setWechatAccount(wechatAccount);
            contact.setWechatAddress(wechatAddress);
            contact.setMobile(mobile);
            contact.setPersonalitySign(personalitySign);
            contact.setIconType(iconType);
            contact.setIconRes(iconRes);
            contact.setIconUrl(iconUrl);
            contact.setGender(gender);
            contact.setCommonGroup(commonGroup);
            contact.setTag(tag);
            contact.setFromType(fromType);
            contact.setPointToUser(pointToUser);
            contacts.add(contact);
        }
        Log.i("wmy","mydb contacts = "+contacts.size());
        cursor.close();
        MyDB.getInstance(context).close();
        return contacts;
    }

    public static final int clearContact(Context context){
        return MyDB.getInstance(context).delete(MyDB.TABLE_WX_CONTACT,null,null);
    }

    //对话
    public static final long saveConversation(Context context, Conversation conversation){
        String name = conversation.getName();
        int type = conversation.getType();
        int iconType = conversation.getIconType();
        int iconRes = conversation.getIconRes();
        String iconUrl = conversation.getIconUrl();
        int badgeCount = conversation.getBadgeCount();
        long updateTime = conversation.getUpdateTime();
        String displayContent = conversation.getDisplayContent();
        boolean ignore = conversation.isIgnore();
        boolean isImportant = conversation.isImportant();
        long contactId = conversation.getContactId();
        String pointToUser = conversation.getPointToUser();
        ContentValues contentValues = new ContentValues();
        contentValues.put("name",name);
        contentValues.put("type",type);
        contentValues.put("badgeCount",badgeCount);
        contentValues.put("updateTime",updateTime);
        contentValues.put("displayContent",displayContent);
        contentValues.put("iconType",iconType);
        contentValues.put("iconRes",iconRes);
        contentValues.put("iconUrl",iconUrl);
        contentValues.put("isIgnore",ignore?0:1);
        contentValues.put("isImportant",isImportant?0:1);
        contentValues.put("contactId",contactId);
        contentValues.put("pointToUser",pointToUser);
        long id = MyDB.getInstance(context).insert(MyDB.TABLE_WX_CONVERSATION,contentValues);
        return id;
    }

    public static List<Conversation> getConversations(Context context){
        Cursor cursor = MyDB.getInstance(context).query(MyDB.TABLE_WX_CONVERSATION);
        List<Conversation> conversations = new ArrayList<>();
        while (cursor.moveToNext()) {
            int id = cursor.getInt(cursor.getColumnIndex("id"));
            String name = cursor.getString(cursor.getColumnIndex("name"));
            int type = cursor.getInt(cursor.getColumnIndex("type"));
            int iconType = cursor.getInt(cursor.getColumnIndex("iconType"));
            int iconRes = cursor.getInt(cursor.getColumnIndex("iconRes"));
            String iconUrl = cursor.getString(cursor.getColumnIndex("iconUrl"));
            int badgeCount = cursor.getInt(cursor.getColumnIndex("badgeCount"));
            long updateTime = cursor.getLong(cursor.getColumnIndex("updateTime"));
            String displayContent = cursor.getString(cursor.getColumnIndex("displayContent"));
            boolean ignore = (cursor.getInt(cursor.getColumnIndex("isIgnore"))==0);
            boolean isImportant = (cursor.getInt(cursor.getColumnIndex("isImportant"))==0);
            long contactId = cursor.getInt(cursor.getColumnIndex("contactId"));
            String pointToUser = cursor.getString(cursor.getColumnIndex("pointToUser"));
            Contact contact = getContact(context,String.valueOf(contactId));
            Conversation conversation = new Conversation();
            conversation.setId(id);
            conversation.setType(Conversation.TYPE_SINGLE_CHAT);
            conversation.setIconType(iconType);
            conversation.setIconUrl(iconUrl);
            conversation.setIconRes(iconRes);
            conversation.setImportant(isImportant);
            conversation.setContactId(contactId);
            conversation.setContact(contact);
            conversation.setType(type);
            conversation.setBadgeCount(badgeCount);
            conversation.setName(name);
            conversation.setDisplayContent(displayContent);
            conversation.setUpdateTime(updateTime);
            conversation.setIgnore(ignore);
            conversation.setPointToUser(pointToUser);
            conversations.add(conversation);
        }
        return conversations;
    }

    public static final int clearConversation(Context context){
        return MyDB.getInstance(context).delete(MyDB.TABLE_WX_CONVERSATION,null,null);
    }

}
