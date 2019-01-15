package com.nanningzhuanqian.vscreenshot.base.util;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import com.nanningzhuanqian.vscreenshot.base.bean.Tag;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lenovo on 2019/1/15.
 */

public class DBManager {

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

    //联系人

    //对话



}
