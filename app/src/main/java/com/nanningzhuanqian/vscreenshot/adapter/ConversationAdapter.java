package com.nanningzhuanqian.vscreenshot.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.nanningzhuanqian.vscreenshot.R;
import com.nanningzhuanqian.vscreenshot.base.Util;
import com.nanningzhuanqian.vscreenshot.base.bean.Contact;
import com.nanningzhuanqian.vscreenshot.base.bean.Conversation;
import com.nanningzhuanqian.vscreenshot.base.bean.Conversations;
import com.squareup.picasso.Picasso;

/**
 * Created by WMY on 2018/9/14.
 */

public class ConversationAdapter extends BaseAdapter {

    private Context context;

    public ConversationAdapter(Context context) {
        this.context = context;
    }

    @Override
    public int getCount() {
        return Conversations.getInstance().size();
    }

    @Override
    public Object getItem(int position) {
        return Conversations.getInstance().get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout
                    .m01_wechat_main_vonversation_list_item, parent, false);
            viewHolder = new ViewHolder();
            viewHolder.imgIcon = convertView.findViewById(R.id.imgIcon);
            viewHolder.tvName = convertView.findViewById(R.id.tvName);
            viewHolder.tvBadge = convertView.findViewById(R.id.tvBadge);
            viewHolder.tvIndicator = convertView.findViewById(R.id.tvIndicator);
            viewHolder.tvDescription = convertView.findViewById(R.id.tvDescription);
            viewHolder.tvUpdateTime = convertView.findViewById(R.id.tvUpdateTime);
            viewHolder.imgIgnore = convertView.findViewById(R.id.imgIgnore);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        Conversation conversation = Conversations.getInstance().get(position);

        int iconType = conversation.getIconType();
        if(iconType== Contact.ICON_TYPE_RESOURCE) {
            viewHolder.imgIcon.setImageResource(conversation.getIconRes());
        }else if(iconType==Contact.ICON_TYPE_NETWORK){
            String imgUrl = conversation.getIconUrl();
            Picasso.with(context)
                    .load(imgUrl)
                    .error(R.mipmap.app_images_defaultface)
                    .placeholder(R.mipmap.app_images_defaultface)
                    .into(viewHolder.imgIcon);
        }else{
            viewHolder.imgIcon.setImageResource(R.mipmap.app_images_defaultface);
        }
        viewHolder.tvName.setText(conversation.getName());
        viewHolder.tvDescription.setText(conversation.getDisplayContent());
        if(conversation.isIgnore()){
            viewHolder.imgIgnore.setVisibility(View.VISIBLE);
            viewHolder.tvBadge.setVisibility(View.GONE);
            if(conversation.getBadgeCount()!=0) {
                viewHolder.tvIndicator.setVisibility(View.VISIBLE);
            }else {
                viewHolder.tvIndicator.setVisibility(View.GONE);
            }
        }else{
            viewHolder.imgIgnore.setVisibility(View.GONE);
            if(conversation.getBadgeCount()!=0) {
                viewHolder.tvBadge.setVisibility(View.VISIBLE);
                viewHolder.tvBadge.setText(conversation.getBadgeCount() + "");
            }else{
                viewHolder.tvBadge.setVisibility(View.GONE);
            }
            viewHolder.tvIndicator.setVisibility(View.GONE);
        }
        String displayTime = Util.getDisplayTime(conversation.getUpdateTime());
        viewHolder.tvUpdateTime.setText(displayTime);
        return convertView;
    }

    class ViewHolder {
        ImageView imgIcon;
        TextView tvName;
        TextView tvIndicator;
        TextView tvBadge;
        TextView tvDescription;
        TextView tvUpdateTime;
        ImageView imgIgnore;
    }
}
