package com.nanningzhuanqian.vscreenshot.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.alibaba.android.vlayout.DelegateAdapter;
import com.alibaba.android.vlayout.LayoutHelper;

/**
 * 自定义搭配阿里VLayout使用的DelegateAdapter的BaseDelegateAdapter
 * Created by WMY on 2018/5/15.
 */

public class BaseDelegateAdapter extends DelegateAdapter.Adapter<BaseViewHolder> {
    public static final int ITEM_ALIPAY = 99;//支付宝
    public static final int ITEM_WECHAT = 100;//微信
    public static final int ITEM_WEIXIN_CALL = 101;
    public static final int ITEM_HELP = 102;
    public static final int ITEM_COMMON_ITEMDECOIRATION = 103;
    public static final int ITEM_TOP_ITEMDECORATION = 104;
    public static final int ITEM_BANNER = 101;//轮播
    public static final int ITEM_NAVIGATION = 102;//导航
    public static final int ITEM_COMMON = 999;
    private LayoutHelper mLayoutHelper;
    private int mCount = -1;
    private int mLayoutId = -1;
    private Context mContext;
    private int mViewTypeItem = -1;

    /**
     * BaseDelegateAdapter的构造方法
     *
     * @param context      上下文
     * @param layoutHelper 取决于这个Item打算采用什么布局 如LinearLayoutHelper、GridLayoutHelper等
     * @param layoutId     传入的xml布局文件的资源id
     * @param count        该Item所容纳的所有子控件数
     * @param viewTypeItem 控件类型
     */
    protected BaseDelegateAdapter(Context context, LayoutHelper layoutHelper, int layoutId, int
            count, int viewTypeItem) {
        this.mContext = context;
        this.mCount = count;
        this.mLayoutHelper = layoutHelper;
        this.mLayoutId = layoutId;
        this.mViewTypeItem = viewTypeItem;
    }

    @Override
    public LayoutHelper onCreateLayoutHelper() {
        return mLayoutHelper;
    }

    @Override
    public BaseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == mViewTypeItem) {
            return new BaseViewHolder(LayoutInflater.from(mContext).inflate(mLayoutId, parent,
                    false));
        }
        return null;
    }

    /**
     * 子类adapter实现
     *
     * @param holder   holder
     * @param position 索引
     */
    @Override
    public void onBindViewHolder(BaseViewHolder holder, int position) {

    }

    /**
     * 必须重写不然会出现滑动不流畅的情况
     */
    @Override
    public int getItemViewType(int position) {
        return mViewTypeItem;
    }

    /**
     * 条目数量
     */
    @Override
    public int getItemCount() {
        return mCount;
    }
}
