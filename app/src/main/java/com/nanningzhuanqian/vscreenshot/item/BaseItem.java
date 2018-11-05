package com.nanningzhuanqian.vscreenshot.item;

import android.content.Context;
import android.net.Uri;
import android.widget.ImageView;

import com.nanningzhuanqian.vscreenshot.base.util.SPUtils;
import com.nanningzhuanqian.vscreenshot.common.Constant;
import com.squareup.picasso.Picasso;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * 一个基类 用于让各个Item都继承自他
 * 复用一些属性和方法
 * 包括头像 图标 图标类型 转换为可存储的SqlLite
 */
public class BaseItem<V, R> {

    int imgRes; //本地资源路径
    String imgUrl;  //图片url路径
    Uri imgUri; //图片uri路径
    String imgType;    //图标类型
    String name;    //名称
    int versionCode;    //适用版本
    int type;   //类型

    int avatarRes;
    String avatarUrl;
    Uri avatarUri;
    String avatarType;

    public int getImgRes() {
        return imgRes;
    }

    public void setImgRes(int imgRes) {
        this.imgRes = imgRes;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public String getImgType() {
        return imgType;
    }

    public void setImgType(String imgType) {
        this.imgType = imgType;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getVersionCode() {
        return versionCode;
    }

    public void setVersionCode(int versionCode) {
        this.versionCode = versionCode;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public Uri getImgUri() {
        return imgUri;
    }

    public void setImgUri(Uri imgUri) {
        this.imgUri = imgUri;
    }

    public int getAvatarRes() {
        return avatarRes;
    }

    public void setAvatarRes(int avatarRes) {
        this.avatarRes = avatarRes;
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }

    public Uri getAvatarUri() {
        return avatarUri;
    }

    public void setAvatarUri(Uri avatarUri) {
        this.avatarUri = avatarUri;
    }

    public String getAvatarType() {
        return avatarType;
    }

    public void setAvatarType(String avatarType) {
        this.avatarType = avatarType;
    }

    public void setImage(Context context, String key,int defaultRes,int imgRes, R r, V v) {
        imgType = (String) SPUtils.get(context, key, Constant.VALUE_PIC_RES);
        if (v instanceof ImageView) {
            switch (imgType) {
                case Constant.VALUE_PIC_LOCAL:
                    ((ImageView) v).setImageURI((Uri) r);
                    break;
                case Constant.VALUE_PIC_NET:
                    Picasso.with(context)
                            .load((String)r)
                            .placeholder(defaultRes)
                            .error(defaultRes)
                            .into((ImageView) v);
                    break;
                case Constant.VALUE_PIC_RES:
                    ((ImageView) v).setImageResource(imgRes);
                    break;
            }
        } else if (v instanceof CircleImageView) {
            switch (imgType) {
                case Constant.VALUE_PIC_LOCAL:
                    ((CircleImageView) v).setImageURI((Uri) r);
                    break;
                case Constant.VALUE_PIC_NET:
                    Picasso.with(context)
                            .load((String)r)
                            .placeholder(defaultRes)
                            .error(defaultRes)
                            .into((CircleImageView) v);
                    break;
                case Constant.VALUE_PIC_RES:
                    ((CircleImageView) v).setImageResource(imgRes);
                    break;
            }
        }
    }

    public void setAvatar(Context context, String key,int defaultRes,int imgRes, R r, V v) {
        avatarType = (String) SPUtils.get(context, key, Constant.VALUE_PIC_RES);
        if (v instanceof ImageView) {
            switch (avatarType) {
                case Constant.VALUE_PIC_LOCAL:
                    ((ImageView) v).setImageURI((Uri) r);
                    break;
                case Constant.VALUE_PIC_NET:
                    Picasso.with(context)
                            .load((String)r)
                            .placeholder(defaultRes)
                            .error(defaultRes)
                            .into((ImageView) v);
                    break;
                case Constant.VALUE_PIC_RES:
                    ((ImageView) v).setImageResource(imgRes);
                    break;
            }
        } else if (v instanceof CircleImageView) {
            switch (avatarType) {
                case Constant.VALUE_PIC_LOCAL:
                    ((CircleImageView) v).setImageURI((Uri) r);
                    break;
                case Constant.VALUE_PIC_NET:
                    Picasso.with(context)
                            .load((String)r)
                            .placeholder(defaultRes)
                            .error(defaultRes)
                            .into((CircleImageView) v);
                    break;
                case Constant.VALUE_PIC_RES:
                    ((CircleImageView) v).setImageResource(imgRes);
                    break;
            }
        }
    }
}
