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
import com.nanningzhuanqian.vscreenshot.item.WechatPayItems;

/**
 * Created by WMY on 2018/9/18.
 */

public class WechatPayAdapter extends BaseAdapter {

    public static final int ITEM_TYPE_WITHDRAW = 0;
    public static final int ITEM_TYPE_TRANSFER = 1;
    public static final int ITEM_TYPE_PAY = 2;

    public static final int ITEM_STATUS_FINISH = 0;
    public static final int ITEM_DURING = 1;
    public static final int ITEM_STATUS_CANCEL = 9;


    private Context context;

    public WechatPayAdapter(Context context) {
        this.context = context;
    }

    @Override
    public int getCount() {
        return WechatPayItems.getInstance().size();
    }

    @Override
    public Object getItem(int position) {
        return WechatPayItems.getInstance().get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.m01_wechat_pay_list_item, parent, false);
            viewHolder = new ViewHolder();
            viewHolder.tvTime = (TextView) convertView.findViewById(R.id.tvTime);
            viewHolder.tvType = (TextView) convertView.findViewById(R.id.tvType);
            viewHolder.tvTranTime = (TextView) convertView.findViewById(R.id.tvTranTime);
            viewHolder.tvAmountType = (TextView) convertView.findViewById(R.id.tvAmountType);
            viewHolder.tvAmount = (TextView) convertView.findViewById(R.id.tvAmount);
            viewHolder.tvShoukuanfang = (TextView) convertView.findViewById(R.id.tvShoukuanfang);
            viewHolder.tvPayStatus = (TextView) convertView.findViewById(R.id.tvPayStatus);
            viewHolder.llManager = (LinearLayout) convertView.findViewById(R.id.llManager);
            viewHolder.tvManager = (TextView) convertView.findViewById(R.id.tvManager);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }


        return convertView;
    }

    class ViewHolder {
        private TextView tvTime;
        private TextView tvType;
        private TextView tvTranTime;
        private TextView tvAmountType;
        private TextView tvAmount;
        private TextView tvShoukuanfang;
        private TextView tvPayStatus;
        private LinearLayout llManager;
        private TextView tvManager;
    }

}
