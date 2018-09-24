package com.nanningzhuanqian.vscreenshot.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.nanningzhuanqian.vscreenshot.R;
import com.nanningzhuanqian.vscreenshot.item.WechatChatItem;
import com.nanningzhuanqian.vscreenshot.item.WechatChatItems;

public class WechatChatItemAdapter extends BaseAdapter {

    private Context context;
    public static final int TYPE_SELF = 0;
    public static final int TYPE_OTHER = 1;

    public WechatChatItemAdapter(Context context){
        this.context = context;
    }

    @Override
    public int getCount() {
        return WechatChatItems.getInstance().size();
    }

    @Override
    public Object getItem(int position) {
        return WechatChatItems.getInstance().get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.m01_wechat_chat_item, parent,
                    false);
            viewHolder = new ViewHolder();
            viewHolder.imgLeftAvatar = (ImageView) convertView.findViewById(R.id.imgLeftAvatar);
            viewHolder.imgRightAvatar = (ImageView)convertView.findViewById(R.id.imgRightAvatar);
            viewHolder.tvTime = (TextView)convertView.findViewById(R.id.tvTime);
            viewHolder.tvLeftContent = (TextView)convertView.findViewById(R.id.tvLeftContent);
            viewHolder.tvRightContent = (TextView)convertView.findViewById(R.id.tvRightContent);
            viewHolder.llLeft = (LinearLayout) convertView.findViewById(R.id.llLeft);
            viewHolder.llRight = (LinearLayout)convertView.findViewById(R.id.llRight);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        WechatChatItem item = WechatChatItems.getInstance().get(position);
        if(item.getType()==TYPE_SELF){
            viewHolder.llLeft.setVisibility(View.GONE);
            viewHolder.llRight.setVisibility(View.VISIBLE);
            viewHolder.imgRightAvatar.setImageResource(item.getImgRes());
            viewHolder.tvRightContent.setBackgroundResource(R.drawable.aei);
            viewHolder.tvRightContent.setText(item.getContent());
        }else{
            viewHolder.llLeft.setVisibility(View.VISIBLE);
            viewHolder.llRight.setVisibility(View.GONE);
            viewHolder.imgLeftAvatar.setImageResource(item.getImgRes());
            viewHolder.tvLeftContent.setBackgroundResource(R.drawable.aeq);
            viewHolder.tvLeftContent.setText(item.getContent());
        }
        return convertView;
    }

    class ViewHolder{
        ImageView imgLeftAvatar;
        ImageView imgRightAvatar;
        TextView tvLeftContent;
        TextView tvRightContent;
        TextView tvTime;
        LinearLayout llLeft;
        LinearLayout llRight;
    }
}
