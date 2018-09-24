package com.nanningzhuanqian.vscreenshot.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.nanningzhuanqian.vscreenshot.R;
import com.nanningzhuanqian.vscreenshot.item.WechatNewFriendItem;
import com.nanningzhuanqian.vscreenshot.item.WechatNewFriendItems;

public class WechatNewFriendListAdapter extends BaseAdapter {

    private Context context;

    public WechatNewFriendListAdapter(Context context){
        this.context = context;
    }

    public void setOnAcceptListener(){

    }

    @Override
    public int getCount() {
        return WechatNewFriendItems.getInstance().size();
    }

    @Override
    public Object getItem(int position) {
        return WechatNewFriendItems.getInstance().get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.m01_wechat_new_friend_list_item, parent,
                    false);
            viewHolder = new ViewHolder();
            viewHolder.imgAvatar = (ImageView) convertView.findViewById(R.id.imgAvatar);
            viewHolder.tvName = (TextView)convertView.findViewById(R.id.tvName);
            viewHolder.tvContent = (TextView)convertView.findViewById(R.id.tvContent);
            viewHolder.tvAccepted = (TextView)convertView.findViewById(R.id.tvAccepted);
            viewHolder.tvAccept = (TextView)convertView.findViewById(R.id.tvAccept);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        WechatNewFriendItem item = WechatNewFriendItems.getInstance().get(position);
        viewHolder.imgAvatar.setImageResource(item.getImgRes());
        viewHolder.tvName.setText(item.getName());
        viewHolder.tvContent.setText(item.getContent());
        if(item.isAdded()){
            viewHolder.tvAccepted.setVisibility(View.VISIBLE);
            viewHolder.tvAccept.setVisibility(View.GONE);
        }else{
            viewHolder.tvAccepted.setVisibility(View.GONE);
            viewHolder.tvAccept.setVisibility(View.VISIBLE);
        }
        return convertView;
    }

    class ViewHolder {
        ImageView imgAvatar;
        TextView tvName;
        TextView tvContent;
        TextView tvAccepted;
        TextView tvAccept;
    }

}
