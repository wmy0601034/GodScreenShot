package com.nanningzhuanqian.vscreenshot.adapter;

import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.nanningzhuanqian.vscreenshot.R;
import com.nanningzhuanqian.vscreenshot.common.Constant;
import com.nanningzhuanqian.vscreenshot.item.BankCardItem;
import com.nanningzhuanqian.vscreenshot.item.BankCardItems;
import com.squareup.picasso.Picasso;

public class BankCardAdapter  extends BaseAdapter {

    private Context context;

    public BankCardAdapter(Context context) {
        this.context = context;
    }

    @Override
    public int getCount() {
        return BankCardItems.getInstance().size();
    }

    @Override
    public Object getItem(int position) {
        return BankCardItems.getInstance().get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.bank_card_list_item, parent, false);
            viewHolder = new ViewHolder();
            viewHolder.imgIcon = (ImageView) convertView.findViewById(R.id.imgIcon);
            viewHolder.tvName = (TextView)convertView.findViewById(R.id.tvName);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        BankCardItem item = BankCardItems.getInstance().get(position);
        if(TextUtils.isEmpty(item.getIconType())|| Constant.VALUE_WECHAT_AVATAR_RES.equals(item.getIconType())){
            viewHolder.imgIcon.setImageResource(item.getIconRes());
        }else if(Constant.VALUE_WECHAT_AVATAR_LOCAL_PIC.equals(item.getIconType())){
            viewHolder.imgIcon.setImageURI(item.getIconUri());
        }else if(Constant.VALUE_WECHAT_AVATAR_NET_PIC.equals(item.getIconUrl())){
            Picasso.with(context).load(item.getIconUrl()).into(viewHolder.imgIcon);
        }
        viewHolder.tvName.setText(item.getName());
        return convertView;
    }

    class ViewHolder {
        ImageView imgIcon;
        TextView tvName;
    }
}
