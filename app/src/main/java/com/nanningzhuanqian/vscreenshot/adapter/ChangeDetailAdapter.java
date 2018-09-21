package com.nanningzhuanqian.vscreenshot.adapter;

import android.content.Context;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.nanningzhuanqian.vscreenshot.R;
import com.nanningzhuanqian.vscreenshot.base.Util;
import com.nanningzhuanqian.vscreenshot.base.util.LoginUtils;
import com.nanningzhuanqian.vscreenshot.item.ChangeDetailItem;
import com.nanningzhuanqian.vscreenshot.item.ChangeDetailItems;
import com.nanningzhuanqian.vscreenshot.item.LocalAvatarItem;
import com.nanningzhuanqian.vscreenshot.item.LocalAvatarItems;

/**
 * Created by WMY on 2018/9/18.
 */

public class ChangeDetailAdapter extends BaseAdapter {

    public static final int TYPE_GROUP_RECEIPTS = 0; //群收款  +
    public static final int TYPE_WECHAT_TRANSFER_PAY = 1;   //微信转账转出 -
    public static final int TYPE_WECHAT_TRANSFER_RECEIVED = 2;  //微信转账收取 +
    public static final int TYPE_WECHAT_RED_PACKET_SEND = 3; //发微信红包    -
    public static final int TYPE_WECHAT_RED_PACKET_RECEIVED = 4;   //收微信红包 +
    public static final int TYPE_QRCODE_PAY = 5;    //扫二维码付款 -
    public static final int TYPE_QRCODE_REWARD = 6; //扫二维码收款    +
    public static final int TYPE_REFUND = 7;    //退款    +

    private Context context;

    public ChangeDetailAdapter(Context context) {
        this.context = context;
    }

    @Override
    public int getCount() {
        return ChangeDetailItems.getInstance().size();
    }

    @Override
    public Object getItem(int position) {
        return ChangeDetailItems.getInstance().get(position);
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
                    .m01_wechat_change_detail_list_item, parent, false);
            viewHolder = new ViewHolder();
            viewHolder.divider = convertView.findViewById(R.id.divider);
            viewHolder.tvType = (TextView) convertView.findViewById(R.id.tvType);
            viewHolder.tvTime = (TextView) convertView.findViewById(R.id.tvTime);
            viewHolder.tvAmount = (TextView) convertView.findViewById(R.id.tvAmount);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        ChangeDetailItem item = ChangeDetailItems.getInstance().get(position);
        String name = Util.getChangeDetailNameByType(item.getType());
        viewHolder.tvType.setText(name);
        viewHolder.tvTime.setText(Util.stampToDate(item.getTranTime()));
        if(position==0){
            viewHolder.divider.setVisibility(View.GONE);
        }else{
            viewHolder.divider.setVisibility(View.VISIBLE);
        }
        if (TYPE_GROUP_RECEIPTS == item.getType() || TYPE_WECHAT_TRANSFER_RECEIVED == item.getType() ||
                TYPE_WECHAT_RED_PACKET_RECEIVED == item.getType() || TYPE_QRCODE_REWARD == item.getType() ||
                TYPE_REFUND == item.getType()) {
            viewHolder.tvAmount.setText("+ " + item.getAmount());
            viewHolder.tvAmount.setTextColor(context.getResources().getColor(R.color.wechat_theme_green));
        } else {
            viewHolder.tvAmount.setText("- " + item.getAmount());
            viewHolder.tvAmount.setTextColor(Color.BLACK);
        }
        return convertView;
    }

    class ViewHolder {
        View divider;
        TextView tvType;
        TextView tvTime;
        TextView tvAmount;
    }
}
