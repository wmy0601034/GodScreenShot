package com.nanningzhuanqian.vscreenshot.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.nanningzhuanqian.vscreenshot.R;
import com.nanningzhuanqian.vscreenshot.item.ContractItem;
import com.nanningzhuanqian.vscreenshot.item.ContractItems;

public class ContractAdapter extends RecyclerView.Adapter {

    public static final int ITEM_COMMON_TYPE = 0;
    public static final int ITEM_CONTRACT_TYPE = 1;

    private Context context;
    private OnItemClickListener listener;

    public ContractAdapter(Context context) {
        this.context = context;
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }

    public interface OnItemClickListener {
        void onItemClick(ContractItem item);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.m01_contract_list_item, parent, false);
        ViewHolder viewHolder = new ViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ViewHolder viewHolder = (ViewHolder) holder;
        int section = getSectionForPosition(position);
        Log.i("wmy", "section = " + section);
        final ContractItem item = ContractItems.getInstance().get(position);
        if (section == -1) {
            viewHolder.tvInitial.setVisibility(View.GONE);
            viewHolder.divider.setVisibility(View.VISIBLE);
        } else {
            if (position == getPositionForSection(section)) {
                viewHolder.tvInitial.setVisibility(View.VISIBLE);
                viewHolder.divider.setVisibility(View.GONE);
                viewHolder.tvInitial.setText(item.getLetters());
            } else {
                viewHolder.tvInitial.setVisibility(View.GONE);
                viewHolder.divider.setVisibility(View.VISIBLE);
            }
        }
        viewHolder.imgAvatar.setImageResource(item.getImgRes());
        viewHolder.tvName.setText(item.getName());
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
        if (TextUtils.isEmpty(ContractItems.getInstance().get(position).getLetters())) {
            return -1;
        } else {
            return ContractItems.getInstance().get(position).getLetters().charAt(0);
        }
    }

    /**
     * 根据分类的首字母的Char ascii值获取其第一次出现该首字母的位置
     */
    public int getPositionForSection(int section) {
        for (int i = 0; i < getItemCount(); i++) {
            String sortStr = ContractItems.getInstance().get(i).getLetters();
            if(!TextUtils.isEmpty(sortStr)) {
                char firstChar = sortStr.toUpperCase().charAt(0);
                if (firstChar == section) {
                    return i;
                }
            }
        }
        return -1;
    }


    @Override
    public int getItemViewType(int position) {
        return ContractItems.getInstance().get(position).getType();
    }

    @Override
    public int getItemCount() {
        return ContractItems.getInstance().size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        LinearLayout rootView;
        TextView tvInitial;
        TextView tvName;
        ImageView imgAvatar;
        View divider;

        public ViewHolder(View itemView) {
            super(itemView);
            rootView = (LinearLayout) itemView.findViewById(R.id.rootView);
            tvInitial = (TextView) itemView.findViewById(R.id.tvInitial);
            tvName = (TextView) itemView.findViewById(R.id.tvName);
            imgAvatar = (ImageView) itemView.findViewById(R.id.imgAvatar);
            divider = (View) itemView.findViewById(R.id.divider);
        }

    }
}
