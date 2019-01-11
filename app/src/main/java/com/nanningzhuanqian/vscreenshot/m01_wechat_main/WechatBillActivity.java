package com.nanningzhuanqian.vscreenshot.m01_wechat_main;

import android.graphics.Color;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.borax12.materialdaterangepicker.Utils;
import com.nanningzhuanqian.vscreenshot.R;
import com.nanningzhuanqian.vscreenshot.adapter.WechatBillAdapter;
import com.nanningzhuanqian.vscreenshot.base.BaseActivity;
import com.nanningzhuanqian.vscreenshot.base.Util;
import com.nanningzhuanqian.vscreenshot.item.WechatBillItems;
import com.nanningzhuanqian.vscreenshot.widget.stickylistview.PowerfulStickyDecoration;
import com.nanningzhuanqian.vscreenshot.widget.stickylistview.listener.PowerGroupListener;

/**
 * 微信账单界面
 */
public class WechatBillActivity extends BaseActivity {

    private RecyclerView rcvBill;
    private PowerfulStickyDecoration decoration;
    private WechatBillAdapter wechatBillAdapter;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_wechat_bill;
    }

    @Override
    protected void initView() {
        rcvBill = (RecyclerView) findViewById(R.id.rcvBill);
        rcvBill.setLayoutManager(new LinearLayoutManager(getThis()));
        PowerGroupListener listener = new PowerGroupListener() {
            @Override
            public String getGroupName(int position) {
                //获取组名，用于判断是否是同一组
                if (WechatBillItems.getInstance().size() > position) {
                    return WechatBillItems.getInstance().get(position).getMonth();
                }
                return null;
            }

            @Override
            public View getGroupView(int position) {
                //获取自定定义的组View
                if (WechatBillItems.getInstance().size() > position) {
                    View view = getLayoutInflater().inflate(R.layout
                                    .m01_wechat_bill_list_item_head,
                            null, false);
                    ((TextView) view.findViewById(R.id.tvName)).setText(WechatBillItems
                            .getInstance().get(position).getMonth());
//                    ((TextView)view.findViewById(R.id.tvDescription)).setText(WechatBillItems.getInstance().get(position).get);
                    return view;
                } else {
                    Log.i(TAG, position + " getGroupView return null");
                    return null;
                }
            }
        };
        decoration = PowerfulStickyDecoration.Builder
                .init(listener)
                .setGroupHeight(Util.dip2px(getThis(), 48))     //设置高度
                .setGroupBackground(Color.parseColor("#ffffff"))        //设置背景
                .setDivideColor(Color.parseColor("#00000000"))            //分割线颜色
                .setDivideHeight(Util.dip2px(getThis(), 0))     //分割线高度
                .setCacheEnable(true)
//                .setOnClickListener(new OnGroupClickListener() {
//                    //点击事件，返回当前分组下的第一个item的position
//                    @Override
//                    public void onClick(int position, int id) {
//                        //Group点击事件
//                        String content = "onGroupClick --> " + CouponItems.getInstance().get
//                                (position).getGroupName();
//                        toast(content);
//                    }
//                })
                .build();

        rcvBill.addItemDecoration(decoration);
        wechatBillAdapter = new WechatBillAdapter(getThis());
        rcvBill.setAdapter(wechatBillAdapter);
    }

    @Override
    protected void initEvent() {

    }

    @Override
    protected void initData() {

    }
}
