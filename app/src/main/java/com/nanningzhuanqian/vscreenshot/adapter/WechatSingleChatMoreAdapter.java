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
import com.nanningzhuanqian.vscreenshot.item.WechatSingleChatMoreItems;

public class WechatSingleChatMoreAdapter  extends BaseAdapter {

    private LayoutInflater inflater;
    /**
     * 页数下标,从0开始(当前是第几页)
     */
    private int curIndex;
    /**
     * 每一页显示的个数
     */
    private int pageSize;

    public WechatSingleChatMoreAdapter(Context context, int curIndex, int pageSize) {
        inflater = LayoutInflater.from(context);
        this.curIndex = curIndex;
        this.pageSize = pageSize;
    }

    /**
     * 先判断数据集的大小是否足够显示满本页？mDatas.size() > (curIndex+1)*pageSize,
     * 如果够，则直接返回每一页显示的最大条目个数pageSize,
     * 如果不够，则有几项返回几,(mDatas.size() - curIndex * pageSize);(也就是最后一页的时候就显示剩余item)
     */
    @Override
    public int getCount() {
        return WechatSingleChatMoreItems.getInstance().size() > (curIndex + 1) * pageSize ? pageSize : (WechatSingleChatMoreItems.getInstance()
                .size() - curIndex * pageSize);

    }

    @Override
    public Object getItem(int position) {
        return WechatSingleChatMoreItems.getInstance().get(position + curIndex * pageSize);
    }

    @Override
    public long getItemId(int position) {
        return position + curIndex * pageSize;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder = null;
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.wechat_single_chat_more_item, parent, false);
            viewHolder = new ViewHolder();
            viewHolder.imgIcon = (ImageView) convertView.findViewById(R.id.imgIcon);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        /**根据当前下标curIndex和每页显示最大数pageSize
         * 在给View绑定显示的数据时，计算正确的position = position + curIndex * pageSize，
         */
        int pos = position + curIndex * pageSize;
        viewHolder.imgIcon.setImageResource(WechatSingleChatMoreItems.getInstance().get(pos).getImgRes());
        return convertView;
    }


    class ViewHolder {
        LinearLayout rootView;
        ImageView imgIcon;
        TextView tvName;
    }

}
