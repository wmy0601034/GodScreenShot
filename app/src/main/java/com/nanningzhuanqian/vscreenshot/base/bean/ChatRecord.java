package com.nanningzhuanqian.vscreenshot.base.bean;

/**
 *  每一条聊天记录
 * Created by lenovo on 2019/1/11.
 */

public class ChatRecord {
    //文字
    public static final int TYPE_TEXT = 0;
    //文字+表情
    public static final int TYPE_TEXT_FACE = 1;
    //表情
    public static final int TYPE_FACE = 2;
    //图片
    public static final int TYPE_PICTRUE = 3;
    //小视频
    public static final int TYPE_VEDIO = 4;
    //链接
    public static final int TYPE_LINK = 5;
    //小程序
    public static final int TYPE_MINI_PROGRAM = 6;
    //数据库id
    private String id;
    //该条聊天记录的类型 0 文字 1 文字+表情 2表情 3图片 4 小视频 5链接 6小程序
    private int type;
    //该条聊天记录关联的发送人
    private Contact sender;
    //发送时间戳
    private long sendTimeMillis;
    //发送时间字符串   X月x日 晚上20：19
    private String sendTimeStr;
    //显示时间
    private String displaySendTime;

    public ChatRecord() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public Contact getSender() {
        return sender;
    }

    public void setSender(Contact sender) {
        this.sender = sender;
    }

    public long getSendTimeMillis() {
        return sendTimeMillis;
    }

    public void setSendTimeMillis(long sendTimeMillis) {
        this.sendTimeMillis = sendTimeMillis;
    }

    public String getSendTimeStr() {
        return sendTimeStr;
    }

    public void setSendTimeStr(String sendTimeStr) {
        this.sendTimeStr = sendTimeStr;
    }

    public String getDisplaySendTime() {
        return displaySendTime;
    }

    public void setDisplaySendTime(String displaySendTime) {
        this.displaySendTime = displaySendTime;
    }
}
