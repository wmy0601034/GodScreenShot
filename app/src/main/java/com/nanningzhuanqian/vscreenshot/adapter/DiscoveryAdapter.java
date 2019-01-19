package com.nanningzhuanqian.vscreenshot.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.nanningzhuanqian.vscreenshot.R;
import com.nanningzhuanqian.vscreenshot.item.DiscoveryItem;
import com.nanningzhuanqian.vscreenshot.item.DiscoveryItems;

public class DiscoveryAdapter extends RecyclerView.Adapter {
    public static final int ITEM_1 = 1;
    public static final int ITEM_2 = 2;
    public static final int ITEM_3 = 3;
    public static final int ITEM_4 = 4;
    public static final int ITEM_5 = 5;
    public static final int ITEM_6 = 6;

    private Context context;
    private OnItemClickListener listener;

    public DiscoveryAdapter(Context context) {
        this.context = context;
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }

    public interface OnItemClickListener {
        void onItemClick(DiscoveryItem item);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.m01_wechat_main_discovery_list_item, parent, false);
        ViewHolder viewHolder = new ViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ViewHolder viewHolder = (ViewHolder) holder;
        int section = getSectionForPosition(position);
        final DiscoveryItem item = DiscoveryItems.getInstance().get(position);
        if (section == -1) {
            viewHolder.tvInitial.setVisibility(View.GONE);
            viewHolder.divider.setVisibility(View.VISIBLE);
        } else {
            if (position == getPositionForSection(section)) {
                viewHolder.tvInitial.setVisibility(View.VISIBLE);
                viewHolder.divider.setVisibility(View.GONE);
            } else {
                viewHolder.tvInitial.setVisibility(View.GONE);
                viewHolder.divider.setVisibility(View.VISIBLE);
            }
        }
        viewHolder.imgIcon.setImageResource(item.getLeftImgRes());
        viewHolder.tvName.setText(item.getName());
        if(item.getBadge()!=0){
            viewHolder.tvBadge.setText(String.valueOf(item.getBadge()));
            viewHolder.tvBadge.setVisibility(View.VISIBLE);
        }else{

        }
        if(item.getRightImgRes()!=0){
            viewHolder.rlRight.setVisibility(View.VISIBLE);
            viewHolder.imgRight.setImageResource(item.getRightImgRes());
        }else{

        }
        if(!TextUtils.isEmpty(item.getRightContent())){
            viewHolder.rlRight.setVisibility(View.VISIBLE);
            viewHolder.tvRight.setText(item.getRightContent());
            viewHolder.tvRight.setVisibility(View.VISIBLE);
        }
        viewHolder.rootView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listener != null) {
                    listener.onItemClick(item);
                }
            }
        });
    }

    /**
     * 根据ListView的当前位置获取分类的首字母的char ascii值
     */
    public int getSectionForPosition(int position) {
        return DiscoveryItems.getInstance().get(position).getType();
    }

    /**
     * 根据分类的首字母的Char ascii值获取其第一次出现该首字母的位置
     */
    public int getPositionForSection(int section) {
        for (int i = 0; i < getItemCount(); i++) {
            if (DiscoveryItems.getInstance().get(i).getType() == section) {
                return i;
            }
        }
        return -1;
    }


    @Override
    public int getItemViewType(int position) {
        return DiscoveryItems.getInstance().get(position).getType();
    }

    @Override
    public int getItemCount() {
        return DiscoveryItems.getInstance().size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        LinearLayout rootView;
        TextView tvInitial;
        TextView tvName;
        TextView tvBadge;
        ImageView imgIcon;
        RelativeLayout rlRight;
        ImageView imgRight;
        TextView tvRight;
        View divider;

        public ViewHolder(View itemView) {
            super(itemView);
            rootView = (LinearLayout) itemView.findViewById(R.id.rootView);
            tvInitial = (TextView) itemView.findViewById(R.id.tvInitial);
            tvName = (TextView) itemView.findViewById(R.id.tvName);
            tvBadge = (TextView)itemView.findViewById(R.id.tvBadge);
            imgIcon = (ImageView) itemView.findViewById(R.id.imgIcon);
            rlRight = (RelativeLayout)itemView.findViewById(R.id.rlRight);
            imgRight = (ImageView) itemView.findViewById(R.id.imgRight);
            tvRight = (TextView) itemView.findViewById(R.id.tvRight);
            divider = (View) itemView.findViewById(R.id.divider);
        }

    }
}
