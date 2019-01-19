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
import com.nanningzhuanqian.vscreenshot.base.bean.Contact;
import com.nanningzhuanqian.vscreenshot.base.bean.Contacts;
import com.nanningzhuanqian.vscreenshot.base.util.SPUtils;
import com.nanningzhuanqian.vscreenshot.common.Constant;
import com.nanningzhuanqian.vscreenshot.item.WechatNewFriendItems;
import com.squareup.picasso.Picasso;

public class ContactAdapter extends RecyclerView.Adapter {

    public static final int ITEM_COMMON_TYPE = 0;
    public static final int ITEM_GONGZHONGHAO_TYPE = 1;
    public static final int ITEM_TAG_TYPE = 2;
    public static final int ITEM_GROUP_TYPE = 3;
    public static final int ITEM_NEW_FRIEND = 4;
    private Context context;
    private OnItemClickListener listener;

    public ContactAdapter(Context context) {
        this.context = context;
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }

    public interface OnItemClickListener {
        void onItemClick(int allPos,int subPos,Contact contact);
    }

    @Override
    public RecyclerView.ViewHolder  onCreateViewHolder(ViewGroup parent, int viewType) {
        if(viewType==ITEM_NEW_FRIEND){
            View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.m01_contract_new_friend_item,
                    parent, false);
            NewFriendViewHolder viewHolder = new NewFriendViewHolder(v);
            return viewHolder;
        }else{
            View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.m01_contract_list_item, parent, false);
            CommonViewHolder viewHolder = new CommonViewHolder(v);
            return viewHolder;
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        if(holder instanceof CommonViewHolder) {
            CommonViewHolder viewHolder = (CommonViewHolder) holder;
            int section = getSectionForPosition(position);
            Log.i("wmy", "section = " + section);
            final Contact contact = Contacts.getInstance().get(position);
            if (section == -1) {
                viewHolder.tvInitial.setVisibility(View.GONE);
                viewHolder.divider.setVisibility(View.VISIBLE);
            } else {
                if (position == getPositionForSection(section)) {
                    viewHolder.tvInitial.setVisibility(View.VISIBLE);
                    viewHolder.divider.setVisibility(View.GONE);
                    viewHolder.tvInitial.setText(contact.getLetters());
                } else {
                    viewHolder.tvInitial.setVisibility(View.GONE);
                    viewHolder.divider.setVisibility(View.VISIBLE);
                }
            }
            int iconType = contact.getIconType();
            if(iconType==Contact.ICON_TYPE_RESOURCE) {
                viewHolder.imgAvatar.setImageResource(contact.getIconRes());
            }else if(iconType==Contact.ICON_TYPE_NETWORK){
                String imgUrl = contact.getIconUrl();
                Picasso.with(context)
                        .load(imgUrl)
                        .into(viewHolder.imgAvatar);
            }
            int type = contact.getType();
            String remarkName = contact.getRemarkName();
            String wxNickName = contact.getWechatNickName();
            if(type == ITEM_COMMON_TYPE) {
                if (TextUtils.isEmpty(remarkName)) {
                    viewHolder.tvName.setText(wxNickName);
                } else {
                    viewHolder.tvName.setText(remarkName);
                }
            }else if(type == ITEM_TAG_TYPE){
                viewHolder.tvName.setText(context.getString(R.string.wx_tag));
                viewHolder.imgAvatar.setImageResource(R.mipmap.app_views_pages_wechat_home_images_contacticon3);
            }else if(type == ITEM_GROUP_TYPE){
                viewHolder.tvName.setText(context.getString(R.string.wx_group_chat));
                viewHolder.imgAvatar.setImageResource(R.mipmap.app_views_pages_wechat_home_images_contacticon2);
            }else if(type ==ITEM_GONGZHONGHAO_TYPE){
                viewHolder.tvName.setText(context.getString(R.string.wx_gongzhonghao));
                viewHolder.imgAvatar.setImageResource(R.mipmap.app_views_pages_wechat_home_images_contacticon4);
            }
            viewHolder.rootView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (listener != null) {
                        listener.onItemClick(position,position>3?-1:position-4,contact);
                    }
                }
            });
        }else if(holder instanceof NewFriendViewHolder){
            NewFriendViewHolder viewHolder = (NewFriendViewHolder) holder;
            int contractUnReadCount = (int) SPUtils.get(context, Constant.KEY_CONTRACT_UNREAD_COUNT,0);
            viewHolder.unread_count.setText(String.valueOf(contractUnReadCount));
            int size = WechatNewFriendItems.getInstance().size();
            if(size>=1){
                viewHolder.imgAvatar.setVisibility(View.VISIBLE);
                viewHolder.imgAvatar.setImageResource(WechatNewFriendItems.getInstance().get(0).getImgRes());
            }else{
                viewHolder.imgAvatar1.setVisibility(View.GONE);
                viewHolder.imgAvatar2.setVisibility(View.GONE);
                viewHolder.imgAvatar3.setVisibility(View.GONE);
            }
            if(size>=2){
                viewHolder.imgAvatar1.setVisibility(View.VISIBLE);
                viewHolder.imgAvatar1.setImageResource(WechatNewFriendItems.getInstance().get(1).getImgRes());
            }else {
                viewHolder.imgAvatar2.setVisibility(View.GONE);
                viewHolder.imgAvatar3.setVisibility(View.GONE);
            }
            if(size>=3){
                viewHolder.imgAvatar2.setVisibility(View.VISIBLE);
                viewHolder.imgAvatar2.setImageResource(WechatNewFriendItems.getInstance().get(2).getImgRes());
            }else {
                viewHolder.imgAvatar3.setVisibility(View.GONE);
            }
            if(size>=4){
                viewHolder.imgAvatar3.setVisibility(View.VISIBLE);
                viewHolder.imgAvatar3.setImageResource(WechatNewFriendItems.getInstance().get(3).getImgRes());
            }
            viewHolder.rootView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (listener != null) {
                        listener.onItemClick(position,-1,null);
                    }
                }
            });
        }
    }

    /**
     * 根据ListView的当前位置获取分类的首字母的char ascii值
     */
    public int getSectionForPosition(int position) {
        if (TextUtils.isEmpty(Contacts.getInstance().get(position).getLetters())) {
            return -1;
        } else {
            return Contacts.getInstance().get(position).getLetters().charAt(0);
        }
    }

    /**
     * 根据分类的首字母的Char ascii值获取其第一次出现该首字母的位置
     */
    public int getPositionForSection(int section) {
        for (int i = 0; i < getItemCount(); i++) {
            String sortStr = Contacts.getInstance().get(i).getLetters();
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
        return Contacts.getInstance().get(position).getType();
    }

    @Override
    public int getItemCount() {
        return Contacts.getInstance().size();
    }

    public class CommonViewHolder extends RecyclerView.ViewHolder {

        LinearLayout rootView;
        TextView tvInitial;
        TextView tvName;
        ImageView imgAvatar;
        View divider;

        public CommonViewHolder(View itemView) {
            super(itemView);
            rootView = (LinearLayout) itemView.findViewById(R.id.rootView);
            tvInitial = (TextView) itemView.findViewById(R.id.tvInitial);
            tvName = (TextView) itemView.findViewById(R.id.tvName);
            imgAvatar = (ImageView) itemView.findViewById(R.id.imgAvatar);
            divider = (View) itemView.findViewById(R.id.divider);
        }
    }

    public class NewFriendViewHolder extends RecyclerView.ViewHolder {

        LinearLayout rootView;
        ImageView imgAvatar;
        ImageView imgAvatar1;
        ImageView imgAvatar2;
        ImageView imgAvatar3;
        TextView unread_count;


        public NewFriendViewHolder(View itemView) {
            super(itemView);
            rootView = (LinearLayout) itemView.findViewById(R.id.rootView);
            imgAvatar = (ImageView) itemView.findViewById(R.id.imgAvatar);
            imgAvatar1 = (ImageView) itemView.findViewById(R.id.imgAvatar1);
            imgAvatar2 = (ImageView) itemView.findViewById(R.id.imgAvatar2);
            imgAvatar3 = (ImageView) itemView.findViewById(R.id.imgAvatar3);
            unread_count = (TextView) itemView.findViewById(R.id.unread_count);
        }
    }

}
