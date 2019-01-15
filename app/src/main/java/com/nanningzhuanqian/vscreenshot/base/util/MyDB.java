package com.nanningzhuanqian.vscreenshot.base.util;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by lenovo on 2019/1/15.
 */

public class MyDB extends SQLiteOpenHelper {

    //表名
    private static final String DB_NAME = "vss.db";
    //版本号
    public static final int DB_VERSION = 1;
    //微信联系人标签表
    public static final String TABLE_WX_TAG = "wx_tag";
    //创建微信联系人标签表的命令行
    public static final String CREATE_WX_TAG_TABLE_SQL =  "create table if not exists " + TABLE_WX_TAG + "("
            + "id integer primary key autoincrement,"
            + "name vachar(20) not null)";

    private static Map<String, MyDB> dbMaps = new HashMap<String, MyDB>();
    private OnSqliteUpdateListener onSqliteUpdateListener;
    /**
     * 建表语句列表
     */
    private List<String> createTableList;

    private MyDB(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
        createTableList = new ArrayList<String>();
        createTableList.add(CREATE_WX_TAG_TABLE_SQL);
    }

    /**
     *
     * @Title: getInstance
     * @Description: 获取数据库实例
     * @param @param context
     * @param @param userId
     * @param @return
     * @return DataBaseOpenHelper
     * @author lihy
     */
    public static MyDB getInstance(Context context) {
        MyDB dataBaseOpenHelper = dbMaps.get(DB_NAME);
        if (dataBaseOpenHelper == null) {
            dataBaseOpenHelper = new MyDB(context);
        }
        dbMaps.put(DB_NAME, dataBaseOpenHelper);
        return dataBaseOpenHelper;
    };

    @Override
    public void onCreate(SQLiteDatabase db) {
        for (String sqlString : createTableList) {
            db.execSQL(sqlString);
        }
    }

    /**
     *
     * @Title: execSQL
     * @Description: Sql写入
     * @param @param sql
     * @param @param bindArgs
     * @return void
     * @author lihy
     */
    public void execSQL(String sql, Object[] bindArgs) {
        MyDB dataBaseOpenHelper = dbMaps.get(DB_NAME);
        synchronized (dataBaseOpenHelper) {
            SQLiteDatabase database = dataBaseOpenHelper.getWritableDatabase();
            database.execSQL(sql, bindArgs);
        }
    }

    /**
     *
     * @Title: rawQuery
     * @Description:
     * @param @param sql查询
     * @param @param bindArgs
     * @param @return
     * @return Cursor
     * @author lihy
     */
    public Cursor rawQuery(String sql, String[] bindArgs) {
        MyDB dataBaseOpenHelper = dbMaps.get(DB_NAME);
        synchronized (dataBaseOpenHelper) {
            SQLiteDatabase database = dataBaseOpenHelper.getReadableDatabase();
            Cursor cursor = database.rawQuery(sql, bindArgs);
            return cursor;
        }
    }

    /**
     *
     * @Title: insert
     * @Description: 插入数据
     * @param @param table
     * @param @param contentValues 设定文件
     * @return void 返回类型
     * @author lihy
     * @throws
     */
    public void insert(String table, ContentValues contentValues) {
        MyDB dataBaseOpenHelper = dbMaps.get(DB_NAME);
        synchronized (dataBaseOpenHelper) {
            SQLiteDatabase database = dataBaseOpenHelper.getWritableDatabase();
            database.insert(table, null, contentValues);
        }
    }

    /**
     *
     * @Title: update
     * @Description: 更新
     * @param @param table
     * @param @param values
     * @param @param whereClause
     * @param @param whereArgs 设定文件
     * @return void 返回类型
     * @throws
     */
    public void update(String table, ContentValues values, String whereClause, String[] whereArgs) {
        MyDB dataBaseOpenHelper = dbMaps.get(DB_NAME);
        synchronized (dataBaseOpenHelper) {
            SQLiteDatabase database = dataBaseOpenHelper.getWritableDatabase();
            database.update(table, values, whereClause, whereArgs);
        }
    }
    /**
     *
     * @Title: delete
     * @Description:删除
     * @param @param table
     * @param @param whereClause
     * @param @param whereArgs
     * @return void
     * @author lihy
     */
    public void delete(String table, String whereClause, String[] whereArgs) {
        MyDB dataBaseOpenHelper = dbMaps.get(DB_NAME);
        synchronized (dataBaseOpenHelper) {
            SQLiteDatabase database = dataBaseOpenHelper.getWritableDatabase();
            database.delete(table, whereClause, whereArgs);
        }
    }

    /**
     *
     * @Title: query
     * @Description: 查
     * @param @param table
     * @param @param columns
     * @param @param selection
     * @param @param selectionArgs
     * @param @param groupBy
     * @param @param having
     * @param @param orderBy
     * @return void
     * @author lihy
     */
    public Cursor query(String table, String[] columns, String selection, String[] selectionArgs, String groupBy, String having,
                        String orderBy) {
        MyDB dataBaseOpenHelper = dbMaps.get(DB_NAME);
        synchronized (dataBaseOpenHelper) {
            SQLiteDatabase database = dataBaseOpenHelper.getReadableDatabase();
            // Cursor cursor = database.rawQuery("select * from "
            // + TableName.TABLE_NAME_USER + " where userId =" + userId, null);
            Cursor cursor = database.query(table, columns, selection, selectionArgs, groupBy, having, orderBy);
            return cursor;
        }
    }
    /**
     *
     * @Description:查
     * @param table
     * @param columns
     * @param selection
     * @param selectionArgs
     * @param groupBy
     * @param having
     * @param orderBy
     * @param limit
     * @return
     * Cursor
     * @exception:
     * @author: lihy
     * @time:2015-4-3 上午9:37:29
     */
    public Cursor query(String table, String[] columns, String selection, String[] selectionArgs, String groupBy, String having,
                        String orderBy,String limit) {
        MyDB dataBaseOpenHelper = dbMaps.get(DB_NAME);
        synchronized (dataBaseOpenHelper) {
            SQLiteDatabase database = dataBaseOpenHelper.getReadableDatabase();
            // Cursor cursor = database.rawQuery("select * from "
            // + TableName.TABLE_NAME_USER + " where userId =" + userId, null);
            Cursor cursor = database.query(table, columns, selection, selectionArgs, groupBy, having, orderBy, limit);
            return cursor;
        }
    }

    /**
     *
     * @Description 查询，方法重载,table表名，sqlString条件
     * @param @return
     * @return Cursor
     * @author lihy
     */
    public Cursor query(String tableName) {
        MyDB dataBaseOpenHelper = dbMaps.get(DB_NAME);
        synchronized (dataBaseOpenHelper) {
            SQLiteDatabase database = dataBaseOpenHelper.getReadableDatabase();
            Cursor cursor = database.rawQuery("select * from " + tableName, null);
            return cursor;
        }
    }

    /**
     *
     * @Description 查询，方法重载,table表名，sqlString条件
     * @param @return
     * @return Cursor
     * @author lihy
     */
    public Cursor query(String tableName, String sqlString) {
        MyDB dataBaseOpenHelper = dbMaps.get(DB_NAME);
        synchronized (dataBaseOpenHelper) {
            SQLiteDatabase database = dataBaseOpenHelper.getReadableDatabase();
            Cursor cursor = database.rawQuery("select * from " + tableName + " " + sqlString, null);

            return cursor;
        }
    }

    /**
     * @see android.database.sqlite.SQLiteOpenHelper#close()
     */
    public void clear() {
        MyDB dataBaseOpenHelper = dbMaps.get(DB_NAME);
        dataBaseOpenHelper.close();
        dbMaps.remove(dataBaseOpenHelper);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int arg1, int arg2) {
        if (onSqliteUpdateListener != null) {
            onSqliteUpdateListener.onSqliteUpdateListener(db, arg1, arg2);
        }
    }

    public interface OnSqliteUpdateListener {
        public void onSqliteUpdateListener(SQLiteDatabase db, int oldVersion, int newVersion);
    }

    public void setOnSqliteUpdateListener(OnSqliteUpdateListener onSqliteUpdateListener) {
        this.onSqliteUpdateListener = onSqliteUpdateListener;
    }
}