package com.nanningzhuanqian.vscreenshot.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.nanningzhuanqian.vscreenshot.R;
import com.nanningzhuanqian.vscreenshot.item.WechatBillItem;
import com.nanningzhuanqian.vscreenshot.item.WechatBillItems;
import com.squareup.picasso.Picasso;

/**
 * Created by WMY on 2018/9/18.
 */

public class WechatBillAdapter extends RecyclerView.Adapter {

    private Context context;

    public WechatBillAdapter(Context context) {
        this.context = context;
    }

    public OnItemClickListener listener;


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout
                .m01_wechat_bill_item, parent, false);
        return new BillHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        BillHolder itemHolder = (BillHolder) holder;
        WechatBillItem item = WechatBillItems.getInstance().get(position);
        Picasso.with(context).load(item.getIconUrl())
                .centerCrop()
                .into(itemHolder.imgIcon);
        itemHolder.tvName.setText(item.getTranType());
        itemHolder.tvAmount.setText(item.getTranAmount());
        itemHolder.tvTime.setText(item.getTranTime());
        itemHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listener != null) {
                    listener.onItemClick(position);
                }
            }
        });
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }

    public interface OnItemClickListener {

        void onItemClick(int position);

    }

    @Override
    public int getItemCount() {
        return WechatBillItems.getInstance().size();
    }

    class BillHolder extends RecyclerView.ViewHolder {
        ImageView imgIcon;
        TextView tvName, tvTime, tvAmount;

        public BillHolder(View convertView) {
            super(convertView);
            imgIcon = (ImageView) convertView.findViewById(R.id.imgIcon);
            tvName = (TextView) convertView.findViewById(R.id.tvName);
            tvTime = (TextView) convertView.findViewById(R.id.tvTime);
            tvAmount = (TextView) convertView.findViewById(R.id.tvAmount);
        }
    }
}
