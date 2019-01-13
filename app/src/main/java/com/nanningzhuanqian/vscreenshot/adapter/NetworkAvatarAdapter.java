package com.nanningzhuanqian.vscreenshot.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.nanningzhuanqian.vscreenshot.R;
import com.nanningzhuanqian.vscreenshot.item.LocalAvatarItem;
import com.nanningzhuanqian.vscreenshot.item.LocalAvatarItems;
import com.nanningzhuanqian.vscreenshot.item.NetworkAvatar;
import com.nanningzhuanqian.vscreenshot.item.NetworkAvatars;
import com.squareup.picasso.Picasso;

/**
 * Created by lenovo on 2019/1/13.
 */

public class NetworkAvatarAdapter extends BaseAdapter {

    private Context context;

    public NetworkAvatarAdapter(Context context) {
        this.context = context;
    }

    @Override
    public int getCount() {
        return NetworkAvatars.getInstance().size();
    }

    @Override
    public Object getItem(int position) {
        return NetworkAvatars.getInstance().get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.m01_avatar_list_item, parent, false);
            viewHolder = new ViewHolder();
            viewHolder.imgAvatar = (ImageView) convertView.findViewById(R.id.imgAvatar);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        NetworkAvatar item = NetworkAvatars.getInstance().get(position);
        String url = item.getImgUrl();
        Picasso.with(context)
                .load(url)
                .into(viewHolder.imgAvatar);
        return convertView;
    }

    class ViewHolder {
        ImageView imgAvatar;
    }
}
