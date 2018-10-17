package com.nanningzhuanqian.vscreenshot.item;

import android.net.Uri;
import android.widget.ImageView;

import com.nanningzhuanqian.vscreenshot.model.WechatChatLite;

public class WechatChatItem {

    private int type;
    private String name;
    private int imgRes;
    private String content;
    private String imgType;
    private Uri avatarUri;
    private String imgUrl;
    private String pointToName;
    private String time;
    private long timeMillis;
    private boolean showTime;

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getImgRes() {
        return imgRes;
    }

    public void setImgRes(int imgRes) {
        this.imgRes = imgRes;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getImgType() {
        return imgType;
    }

    public void setImgType(String imgType) {
        this.imgType = imgType;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public String getPointToName() {
        return pointToName;
    }

    public void setPointToName(String pointToName) {
        this.pointToName = pointToName;
    }

    public long getTimeMillis() {
        return timeMillis;
    }

    public void setTimeMillis(long timeMillis) {
        this.timeMillis = timeMillis;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public Uri getAvatarUri() {
        return avatarUri;
    }

    public void setAvatarUri(Uri avatarUri) {
        this.avatarUri = avatarUri;
    }

    public boolean isShowTime() {
        return showTime;
    }

    public void setShowTime(boolean showTime) {
        this.showTime = showTime;
    }

    public WechatChatItem(int type, String name, int imgRes, String content, String pointToName) {
        this.type = type;
        this.name = name;
        this.imgRes = imgRes;
        this.content = content;
        this.pointToName = pointToName;
    }

    public WechatChatLite convertToWechatChatLite(){
        return new WechatChatLite(type,name,imgRes,content,pointToName);
    }

    public void setAvatar(ImageView imageView){

    }

}
