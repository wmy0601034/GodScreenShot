package com.nanningzhuanqian.vscreenshot.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.nanningzhuanqian.vscreenshot.R;
import com.nanningzhuanqian.vscreenshot.item.ConversationItem;
import com.nanningzhuanqian.vscreenshot.item.ConversationItems;
import com.squareup.picasso.Picasso;

public class ChooseConversationAdapter extends BaseAdapter {

    private Context context;

    public ChooseConversationAdapter(Context context){
        this.context = context;
    }

    @Override
    public int getCount() {
        return ConversationItems.getInstance().size();
    }

    @Override
    public Object getItem(int position) {
        return ConversationItems.getInstance().get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder ;
        if(convertView==null){
            convertView = LayoutInflater.from(context).inflate(R.layout.m01_wechat_choose_conversation_list_item,parent,false);
            viewHolder = new ViewHolder();
            viewHolder.imgAvatar = convertView.findViewById(R.id.imgAvatar);
            viewHolder.tvName = convertView.findViewById(R.id.tvName);
            convertView.setTag(viewHolder);
        }else{
            viewHolder = (ViewHolder) convertView.getTag();
        }
        ConversationItem item = ConversationItems.getInstance().get(position);
        Picasso.with(context).load(item.getImgRes()).into(viewHolder.imgAvatar);
        viewHolder.tvName.setText(item.getName());
        return convertView;
    }

    class ViewHolder{
        TextView tvName;
        ImageView imgAvatar;
    }
}
