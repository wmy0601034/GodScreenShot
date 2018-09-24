package com.nanningzhuanqian.vscreenshot.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.nanningzhuanqian.vscreenshot.R;
import com.nanningzhuanqian.vscreenshot.item.WechatChatItem;
import com.nanningzhuanqian.vscreenshot.item.WechatChatItems;

public class WechatChatAdapter extends RecyclerView.Adapter {

    public static final int TYPE_SELF = 0;
    public static final int TYPE_OTHER = 1;

    private Context context;

    public WechatChatAdapter(Context context) {
        this.context = context;
    }


    @Override
    public RecyclerView.ViewHolder  onCreateViewHolder(ViewGroup parent, int viewType) {
        if(viewType==TYPE_SELF) {
            View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.wechat_chat_self_item, parent,
                    false);
            SelfViewHolder viewHolder = new SelfViewHolder(v);
            return viewHolder;
        }else if(viewType==TYPE_OTHER){
            View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.wechat_chat_other_item,
                    parent, false);
            OtherViewHolder viewHolder = new OtherViewHolder(v);
            return viewHolder;
        }
        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if(holder instanceof SelfViewHolder) {
            SelfViewHolder viewHolder = (SelfViewHolder) holder;
//            int section = getSectionForPosition(position);
//            Log.i("wmy", "section = " + section);
            final WechatChatItem item = WechatChatItems.getInstance().get(position);
//            if (section == -1) {
//                viewHolder.tvInitial.setVisibility(View.GONE);
//                viewHolder.divider.setVisibility(View.VISIBLE);
//            } else {
//                if (position == getPositionForSection(section)) {
//                    viewHolder.tvInitial.setVisibility(View.VISIBLE);
//                    viewHolder.divider.setVisibility(View.GONE);
//                    viewHolder.tvInitial.setText(item.getLetters());
//                } else {
//                    viewHolder.tvInitial.setVisibility(View.GONE);
//                    viewHolder.divider.setVisibility(View.VISIBLE);
//                }
//            }
            viewHolder.imgAvatar.setImageResource(item.getImgRes());
            viewHolder.tvContent.setBackgroundResource(R.drawable.aei);
            viewHolder.tvContent.setText(item.getContent());
            viewHolder.rootView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                }
            });
        }else if(holder instanceof OtherViewHolder){
            OtherViewHolder viewHolder = (OtherViewHolder) holder;
//            int section = getSectionForPosition(position);
//            Log.i("wmy", "section = " + section);
            final WechatChatItem item = WechatChatItems.getInstance().get(position);
//            if (section == -1) {
//                viewHolder.tvInitial.setVisibility(View.GONE);
//                viewHolder.divider.setVisibility(View.VISIBLE);
//            } else {
//                if (position == getPositionForSection(section)) {
//                    viewHolder.tvInitial.setVisibility(View.VISIBLE);
//                    viewHolder.divider.setVisibility(View.GONE);
//                    viewHolder.tvInitial.setText(item.getLetters());
//                } else {
//                    viewHolder.tvInitial.setVisibility(View.GONE);
//                    viewHolder.divider.setVisibility(View.VISIBLE);
//                }
//            }
            viewHolder.imgAvatar.setImageResource(item.getImgRes());
            viewHolder.tvContent.setText(item.getContent());
            viewHolder.tvContent.setBackgroundResource(R.drawable.aeq);
            viewHolder.rootView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                }
            });
        }
    }

    /**
     * 根据ListView的当前位置获取分类的首字母的char ascii值
     */
//    public int getSectionForPosition(int position) {
//        if (TextUtils.isEmpty(ContractItems.getInstance().get(position).getLetters())) {
//            return -1;
//        } else {
//            return ContractItems.getInstance().get(position).getLetters().charAt(0);
//        }
//    }

    /**
     * 根据分类的首字母的Char ascii值获取其第一次出现该首字母的位置
     */
//    public int getPositionForSection(int section) {
//        for (int i = 0; i < getItemCount(); i++) {
//            String sortStr = WechatChatItems.getInstance().get(i).getLetters();
//            if(!TextUtils.isEmpty(sortStr)) {
//                char firstChar = sortStr.toUpperCase().charAt(0);
//                if (firstChar == section) {
//                    return i;
//                }
//            }
//        }
//        return -1;
//    }


    @Override
    public int getItemViewType(int position) {
        return WechatChatItems.getInstance().get(position).getType();
    }

    @Override
    public int getItemCount() {
        return WechatChatItems.getInstance().size();
    }

    public class SelfViewHolder extends RecyclerView.ViewHolder {

        RelativeLayout rootView;
//        TextView tvTime;
        ImageView imgAvatar;
        TextView tvContent;

        public SelfViewHolder(View itemView) {
            super(itemView);
            rootView = (RelativeLayout) itemView.findViewById(R.id.rootView);
//            tvTime = (TextView) itemView.findViewById(R.id.tvTime);
            tvContent = (TextView) itemView.findViewById(R.id.tvContent);
            imgAvatar = (ImageView) itemView.findViewById(R.id.imgAvatar);
        }
    }

    public class OtherViewHolder extends RecyclerView.ViewHolder {

        RelativeLayout rootView;
//        TextView tvTime;
        ImageView imgAvatar;
        TextView tvContent;


        public OtherViewHolder(View itemView) {
            super(itemView);
            rootView = (RelativeLayout) itemView.findViewById(R.id.rootView);
            imgAvatar = (ImageView) itemView.findViewById(R.id.imgAvatar);
//            tvTime = (TextView) itemView.findViewById(R.id.tvTime);
            tvContent = (TextView) itemView.findViewById(R.id.tvContent);
        }
    }



}
