package com.nanningzhuanqian.vscreenshot.m01_wechat_main;

import android.widget.ListView;

import com.nanningzhuanqian.vscreenshot.R;
import com.nanningzhuanqian.vscreenshot.adapter.WechatPayAdapter;
import com.nanningzhuanqian.vscreenshot.base.BaseActivity;
import com.nanningzhuanqian.vscreenshot.item.WechatPayItem;
import com.nanningzhuanqian.vscreenshot.item.WechatPayItems;

/**
 * 微信支付界面
 */
public class WechatPayActivity extends BaseActivity {

    private ListView lvPay;
    private WechatPayAdapter wechatPayAdapter;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_wechat_pay;
    }

    @Override
    protected void initView() {
        lvPay = (ListView) findViewById(R.id.lvPay);
        wechatPayAdapter = new WechatPayAdapter(getThis());
        lvPay.setAdapter(wechatPayAdapter);
    }

    @Override
    protected void initEvent() {

    }

    @Override
    protected void initData() {
        for (int i = 0; i < 5; i++) {
            WechatPayItem item = new WechatPayItem();
            WechatPayItems.getInstance().add(item);
        }
        wechatPayAdapter.notifyDataSetChanged();
    }
}
