package com.nanningzhuanqian.vscreenshot.m01_wechat.wechat.subcribe;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.nanningzhuanqian.vscreenshot.R;
import com.nanningzhuanqian.vscreenshot.adapter.ConversationAdapter;
import com.nanningzhuanqian.vscreenshot.base.BaseActivity;
import com.nanningzhuanqian.vscreenshot.base.Util;
import com.nanningzhuanqian.vscreenshot.base.bean.Contact;
import com.nanningzhuanqian.vscreenshot.base.bean.Subcribe;
import com.nanningzhuanqian.vscreenshot.base.bean.Subcribes;
import com.squareup.picasso.Picasso;

public class WxSubcribeListActivity extends BaseActivity {

    private TextView tvBack;
    private ImageButton btnRight;
    private ListView lvSubcribe;
    private SubcribeAdapter subcribeAdapter;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_wx_subcribe_list;
    }

    @Override
    protected void initView() {
        tvBack = (TextView) findViewById(R.id.tvBack);
        btnRight = (ImageButton)findViewById(R.id.btnRight);
        lvSubcribe = (ListView) findViewById(R.id.lvSubcribe);
        subcribeAdapter = new SubcribeAdapter();
        lvSubcribe.setAdapter(subcribeAdapter);
    }

    @Override
    protected void initEvent() {
        tvBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        btnRight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

    }

    @Override
    protected void initData() {
        
    }

    public class SubcribeAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            return Subcribes.getInstance().size();
        }

        @Override
        public Object getItem(int position) {
            return Subcribes.getInstance().get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ViewHolder viewHolder;
            if (convertView == null) {
                convertView = LayoutInflater.from(getThis()).inflate(R.layout
                        .m01_wechat_main_vonversation_list_item, parent, false);
                viewHolder = new ViewHolder();
                viewHolder.imgIcon = convertView.findViewById(R.id.imgIcon);
                viewHolder.tvName = convertView.findViewById(R.id.tvName);
                viewHolder.tvBadge = convertView.findViewById(R.id.tvBadge);
                viewHolder.tvIndicator = convertView.findViewById(R.id.tvIndicator);
                viewHolder.tvDescription = convertView.findViewById(R.id.tvDescription);
                viewHolder.tvUpdateTime = convertView.findViewById(R.id.tvUpdateTime);
                convertView.setTag(viewHolder);
            } else {
                viewHolder = (ViewHolder) convertView.getTag();
            }
            Subcribe subcribe = Subcribes.getInstance().get(position);

            int iconType = subcribe.getIconType();
            if (iconType == Contact.ICON_TYPE_RESOURCE) {
                viewHolder.imgIcon.setImageResource(subcribe.getIconRes());
            } else if (iconType == Contact.ICON_TYPE_NETWORK) {
                String imgUrl = subcribe.getIconUrl();
                Picasso.with(getThis())
                        .load(imgUrl)
                        .error(R.mipmap.app_images_defaultface)
                        .placeholder(R.mipmap.app_images_defaultface)
                        .into(viewHolder.imgIcon);
            } else {
                viewHolder.imgIcon.setImageResource(R.mipmap.app_images_defaultface);
            }
            viewHolder.tvName.setText(subcribe.getName());
            viewHolder.tvDescription.setText(subcribe.getDisplayContent());
            if (subcribe.isIgnore()) {
                viewHolder.tvBadge.setVisibility(View.GONE);
                if (subcribe.getBadgeCount() != 0) {
                    viewHolder.tvIndicator.setVisibility(View.VISIBLE);
                } else {
                    viewHolder.tvIndicator.setVisibility(View.GONE);
                }
            } else {
                if (subcribe.getBadgeCount() != 0) {
                    viewHolder.tvBadge.setVisibility(View.VISIBLE);
                    viewHolder.tvBadge.setText(subcribe.getBadgeCount() + "");
                } else {
                    viewHolder.tvBadge.setVisibility(View.GONE);
                }
                viewHolder.tvIndicator.setVisibility(View.GONE);
            }
            String displayTime = Util.getDisplayTime(subcribe.getUpdateTime());
            viewHolder.tvUpdateTime.setText(displayTime);
            return convertView;
        }

        class ViewHolder {
            ImageView imgIcon;
            TextView tvName;
            TextView tvIndicator;
            TextView tvBadge;
            TextView tvDescription;
            TextView tvUpdateTime;
        }
    }
}
