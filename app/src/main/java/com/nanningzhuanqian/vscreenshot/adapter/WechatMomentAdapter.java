package com.nanningzhuanqian.vscreenshot.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.nanningzhuanqian.vscreenshot.R;
import com.nanningzhuanqian.vscreenshot.base.util.SPUtils;
import com.nanningzhuanqian.vscreenshot.common.Constant;
import com.nanningzhuanqian.vscreenshot.item.WechatMomentItem;
import com.nanningzhuanqian.vscreenshot.item.WechatMomentItems;

import cn.bmob.v3.b.I;

public class WechatMomentAdapter extends RecyclerView.Adapter {

    public static final int TOP_MOMENT_TYPE = 999;
    public static final int TEXT_MOMENT_TYPE = 0;
    public static final int VEDIO_MOMENT_TYPE = 1;
    public static final int PICTURE_MOMENT_TYPE = 2;
    public static final int LINK_MOMENT_TYPE = 3;

    private Context context;
    private OnWechatMomentAvatarClickListener listener;

    public WechatMomentAdapter(Context context) {
        this.context = context;
    }

    public void setOnWechatMomentAvatarClickListener(OnWechatMomentAvatarClickListener listener) {
        this.listener = listener;
    }

    public interface OnWechatMomentAvatarClickListener {

        void onClickAvatar();

        void onClickItem(int position);

    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        if (viewType == TOP_MOMENT_TYPE) {
            View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.m01_wechat_moment_top_item, parent, false);
            MomentTopViewHolder momentTopViewHolder = new MomentTopViewHolder(v);
            return momentTopViewHolder;
        } else {
            View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.m01_wechat_moment_common_item, parent, false);
            MomentTopViewHolder momentTopViewHolder = new MomentTopViewHolder(v);
            return momentTopViewHolder;
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof MomentTopViewHolder) {
            MomentTopViewHolder topViewHolder = (MomentTopViewHolder) holder;
            String avatar = (String) SPUtils.get(context, Constant.KEY_MOMENT_AVATAR, "");
            if (!TextUtils.isEmpty(avatar)) {
                int imgRes = Integer.valueOf(avatar);
                topViewHolder.imgAvatar.setImageResource(imgRes);
            }
            String name = (String) SPUtils.get(context, Constant.KEY_PROFILE_NAME, "");
            if (!TextUtils.isEmpty(name)) {
                topViewHolder.tvName.setText(name);
            }
            String background = (String) SPUtils.get(context, Constant.KEY_MOMENT_BG, "");
            if (!TextUtils.isEmpty(background)) {
                int bg = Integer.valueOf(background);
                topViewHolder.imgBg.setImageResource(bg);
            }
        } else if (holder instanceof MomentCommonViewHolder) {
            WechatMomentItem item =  WechatMomentItems.getInstance().get(position);
            int type = item.getType();
            MomentCommonViewHolder commonViewHolder = (MomentCommonViewHolder) holder;
            commonViewHolder.imgAvatar.setImageResource(item.getPublisherImgRes());
            String location = item.getLocation();
            if(!TextUtils.isEmpty(location)){
                commonViewHolder.tvLocation.setText(location);
            }
            if(!TextUtils.isEmpty(item.getTextContent())) {
                commonViewHolder.tvContent.setVisibility(View.VISIBLE);
                commonViewHolder.tvContent.setText(item.getTextContent());
            }
            if (type == TEXT_MOMENT_TYPE) {
                commonViewHolder.llImg.setVisibility(View.GONE);
            } else if (type == VEDIO_MOMENT_TYPE) {
                commonViewHolder.llImg.setVisibility(View.VISIBLE);
                commonViewHolder.rlImg1.setVisibility(View.VISIBLE);
                commonViewHolder.img1.setImageResource(item.getVideoRes());
                commonViewHolder.imgPlay.setVisibility(View.VISIBLE);
            } else if (type == PICTURE_MOMENT_TYPE) {
                int count = item.getPicRes().size();
                if(count ==0){
                    commonViewHolder.llImg.setVisibility(View.GONE);
                }else if(count ==4){
                    commonViewHolder.llImg.setVisibility(View.VISIBLE);
                    commonViewHolder.gvImg4.setVisibility(View.VISIBLE);
                    commonViewHolder.rlImg1.setVisibility(View.GONE);
                    commonViewHolder.gvImg9.setVisibility(View.GONE);
                }else if(count == 1){
                    commonViewHolder.llImg.setVisibility(View.VISIBLE);
                    commonViewHolder.rlImg1.setVisibility(View.VISIBLE);
                    commonViewHolder.gvImg4.setVisibility(View.GONE);
                    commonViewHolder.gvImg9.setVisibility(View.GONE);
                }else{
                    commonViewHolder.llImg.setVisibility(View.VISIBLE);
                    commonViewHolder.gvImg9.setVisibility(View.VISIBLE);
                    commonViewHolder.gvImg4.setVisibility(View.GONE);
                    commonViewHolder.rlImg1.setVisibility(View.GONE);
                }
            } else if (type == LINK_MOMENT_TYPE) {
                commonViewHolder.llLink.setVisibility(View.VISIBLE);
                commonViewHolder.imgLinkIcon.setImageResource(item.getLinkIcon());
                commonViewHolder.tvLink.setText(item.getLinkContent());
                commonViewHolder.tvFrom.setText(item.getLinkFrom());
            }

        }
    }

    @Override
    public int getItemViewType(int position) {
        return WechatMomentItems.getInstance().get(position).getType();
    }

    @Override
    public int getItemCount() {
        return WechatMomentItems.getInstance().size();
    }

    class MomentTopViewHolder extends RecyclerView.ViewHolder {

        ImageView imgBg;
        LinearLayout llAvatar;
        ImageView imgAvatar;
        TextView tvName;

        public MomentTopViewHolder(View itemView) {
            super(itemView);
            imgBg = itemView.findViewById(R.id.imgBg);
            llAvatar = itemView.findViewById(R.id.llAvatar);
            imgAvatar = itemView.findViewById(R.id.imgAvatar);
            tvName = itemView.findViewById(R.id.tvName);
        }
    }

    class MomentCommonViewHolder extends RecyclerView.ViewHolder {

        TextView tvName;
        TextView tvContent;
        ImageView imgAvatar;
        TextView tvTime;
        ImageButton btnComment;
        LinearLayout llImg;
        RelativeLayout rlImg1;
        ImageView imgPlay;
        ImageView img1;
        GridView gvImg9;
        GridView gvImg4;
        TextView tvLocation;
        LinearLayout llLink;
        TextView tvLink;
        ImageView imgLinkIcon;
        TextView tvFrom;
        TextView tvLikes;
        ListView lvComment;


        public MomentCommonViewHolder(View itemView) {
            super(itemView);
            tvName = itemView.findViewById(R.id.tvName);
            tvContent = itemView.findViewById(R.id.tvContent);
            imgAvatar = itemView.findViewById(R.id.imgAvatar);
            tvTime = itemView.findViewById(R.id.tvTime);
            btnComment = itemView.findViewById(R.id.btnComment);
            llImg = itemView.findViewById(R.id.llImg);
            rlImg1 = itemView.findViewById(R.id.rlImg1);
            imgPlay = itemView.findViewById(R.id.imgPlay);
            img1 = itemView.findViewById(R.id.img1);
            gvImg9 = itemView.findViewById(R.id.gvImg9);
            gvImg4 = itemView.findViewById(R.id.gvImg4);
            tvLocation = itemView.findViewById(R.id.tvLocation);
            tvFrom = itemView.findViewById(R.id.tvFrom);
            tvLikes = itemView.findViewById(R.id.tvLikes);
            lvComment = itemView.findViewById(R.id.lvComment);
            llLink = itemView.findViewById(R.id.llLink);
            tvLink = itemView.findViewById(R.id.tvLink);
            imgLinkIcon = itemView.findViewById(R.id.imgLinkIcon);
        }
    }


}
