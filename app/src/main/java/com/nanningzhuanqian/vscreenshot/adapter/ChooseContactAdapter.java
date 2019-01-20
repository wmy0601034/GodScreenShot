package com.nanningzhuanqian.vscreenshot.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.nanningzhuanqian.vscreenshot.R;
import com.nanningzhuanqian.vscreenshot.base.bean.Contact;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class ChooseContactAdapter extends BaseAdapter {

    private Context context;

    private List<Contact> contacts = new ArrayList<>();

    public ChooseContactAdapter(Context context){
        this.context = context;
    }

    public void setContacts(List<Contact> contacts){
        this.contacts = contacts;
    }

    @Override
    public int getCount() {
        return contacts.size();
    }

    @Override
    public Object getItem(int position) {
        return contacts.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder ;
        if(convertView==null){
            convertView = LayoutInflater.from(context).inflate(R.layout.m00_wechat_choose_single_chat_list_item,parent,false);
            viewHolder = new ViewHolder();
            viewHolder.imgAvatar = convertView.findViewById(R.id.imgAvatar);
            viewHolder.tvName = convertView.findViewById(R.id.tvName);
            convertView.setTag(viewHolder);
        }else{
            viewHolder = (ViewHolder) convertView.getTag();
        }
        Contact contact = contacts.get(position);
        int iconType = contact.getIconType();
        if(iconType==Contact.ICON_TYPE_NETWORK){
            Picasso.with(context)
                    .load(contact.getIconUrl())
                    .error(R.mipmap.app_images_defaultface)
                    .placeholder(R.mipmap.app_images_defaultface)
                    .into(viewHolder.imgAvatar);
        }else if(iconType==Contact.ICON_TYPE_RESOURCE){
            viewHolder.imgAvatar.setImageResource(contact.getIconRes());
        }
        viewHolder.tvName.setText(contact.getRemarkName());
        return convertView;
    }

    class ViewHolder{
        TextView tvName;
        ImageView imgAvatar;
    }
}
