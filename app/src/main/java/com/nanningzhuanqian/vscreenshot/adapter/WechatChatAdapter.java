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
import com.nanningzhuanqian.vscreenshot.common.Constant;
import com.nanningzhuanqian.vscreenshot.item.WechatChatItem;
import com.nanningzhuanqian.vscreenshot.item.WechatChatItems;

import cn.bmob.v3.b.I;

public class WechatChatAdapter extends RecyclerView.Adapter {

    public static final int TYPE_SELF = 0;
    public static final int TYPE_OTHER = 1;
    public static final int TYPE_TIME = 2;
    

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
        }else if(viewType==TYPE_TIME){
            View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.wechat_chat_time_item,parent,false);
            TimeViewHolder timeViewHolder = new TimeViewHolder(v);
            return timeViewHolder;
        }
        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        if(holder instanceof SelfViewHolder) {
            SelfViewHolder viewHolder = (SelfViewHolder) holder;
            final WechatChatItem item = WechatChatItems.getInstance().get(position);
            setAvatar(viewHolder.imgAvatar,item);
            viewHolder.tvContent.setBackgroundResource(R.drawable.aei);
            viewHolder.tvContent.setText(item.getContent());
            viewHolder.rootView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                }
            });
            viewHolder.rootView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    if(longClickListener!=null){
                        longClickListener.onItemLongClick(position);
                    }
                    return true;
                }
            });
        }else if(holder instanceof OtherViewHolder){
            OtherViewHolder viewHolder = (OtherViewHolder) holder;
            final WechatChatItem item = WechatChatItems.getInstance().get(position);
//            viewHolder.imgAvatar.setImageResource(item.getImgRes());
            setAvatar(viewHolder.imgAvatar,item);
            viewHolder.tvContent.setText(item.getContent());
            viewHolder.tvContent.setBackgroundResource(R.drawable.aeq);
            viewHolder.rootView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                }
            });
            viewHolder.rootView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    if(longClickListener!=null){
                        longClickListener.onItemLongClick(position);
                    }
                    return true;
                }
            });
        }else if(holder instanceof TimeViewHolder){
            TimeViewHolder timeViewHolder = (TimeViewHolder)holder;
            final WechatChatItem item = WechatChatItems.getInstance().get(position);
            timeViewHolder.tvTime.setText(item.getTime());
            timeViewHolder.tvTime.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    if(longClickListener!=null){
                        longClickListener.onItemLongClick(position);
                    }
                    return true;
                }
            });
        }
    }

    private void setAvatar(ImageView imageView,WechatChatItem item){
        if(TextUtils.isEmpty(item.getImgType())|| Constant.VALUE_WECHAT_AVATAR_RES.equals(item.getImgType())){
            imageView.setImageResource(item.getImgRes());
        }else{
            imageView.setImageURI(item.getAvatarUri());
        }
    }

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
        ImageView imgAvatar;
        TextView tvContent;

        public SelfViewHolder(View itemView) {
            super(itemView);
            rootView = (RelativeLayout) itemView.findViewById(R.id.rootView);
            tvContent = (TextView) itemView.findViewById(R.id.tvContent);
            imgAvatar = (ImageView) itemView.findViewById(R.id.imgAvatar);
        }
    }

    public class OtherViewHolder extends RecyclerView.ViewHolder {

        RelativeLayout rootView;
        ImageView imgAvatar;
        TextView tvContent;


        public OtherViewHolder(View itemView) {
            super(itemView);
            rootView = (RelativeLayout) itemView.findViewById(R.id.rootView);
            imgAvatar = (ImageView) itemView.findViewById(R.id.imgAvatar);
            tvContent = (TextView) itemView.findViewById(R.id.tvContent);
        }
    }

    public class TimeViewHolder extends RecyclerView.ViewHolder{
        TextView tvTime;
        public TimeViewHolder(View itemView) {
            super(itemView);
            tvTime =  itemView.findViewById(R.id.tvTime);
        }
    }


    public OnItemClickListener clickListener;
    public OnItemLongClickListener longClickListener;

    public void setOnItemClickListener(OnItemClickListener clickListener){
        this.clickListener = clickListener;
    }

    public void setOnItemLongClickListener(OnItemLongClickListener longClickListener){
        this.longClickListener = longClickListener;
    }

    public interface OnItemClickListener{

        void onItemClick(int position);

    }

    public interface OnItemLongClickListener{

        void onItemLongClick(int position);

    }

}
