package com.nanningzhuanqian.vscreenshot.m01_wechat_main;

import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.alibaba.android.vlayout.DelegateAdapter;
import com.alibaba.android.vlayout.VirtualLayoutManager;
import com.alibaba.android.vlayout.layout.GridLayoutHelper;
import com.alibaba.android.vlayout.layout.LinearLayoutHelper;
import com.nanningzhuanqian.vscreenshot.MainActivity;
import com.nanningzhuanqian.vscreenshot.R;
import com.nanningzhuanqian.vscreenshot.adapter.BaseDelegateAdapter;
import com.nanningzhuanqian.vscreenshot.adapter.BaseViewHolder;
import com.nanningzhuanqian.vscreenshot.base.BaseActivity;
import com.nanningzhuanqian.vscreenshot.base.util.SPUtils;
import com.nanningzhuanqian.vscreenshot.common.Constant;
import com.nanningzhuanqian.vscreenshot.item.TencentServiceItem;
import com.nanningzhuanqian.vscreenshot.item.TencentServiceItems;
import com.nanningzhuanqian.vscreenshot.item.WechatItem;
import com.nanningzhuanqian.vscreenshot.item.WechatItems;
import com.nanningzhuanqian.vscreenshot.item.WechatSpreadItem;
import com.nanningzhuanqian.vscreenshot.item.WechatSpreadItems;
import com.nanningzhuanqian.vscreenshot.item.WechatThirdServiceItem;
import com.nanningzhuanqian.vscreenshot.item.WechatThirdServiceItems;
import com.squareup.picasso.Picasso;

import java.lang.reflect.Field;
import java.util.LinkedList;
import java.util.List;


public class WechatWalletActivity extends BaseActivity {

    private RecyclerView rcvWallet;
    private DelegateAdapter delegateAdapter;
    private List<DelegateAdapter.Adapter> mAdapters = new LinkedList<>();
    private BaseDelegateAdapter walletAdapter;
    private BaseDelegateAdapter tencentServiceItemDecorationAdapter;
    private BaseDelegateAdapter tencentServiceAdapter;
    private BaseDelegateAdapter spreadItemDecorationAdapter;
    private BaseDelegateAdapter spreadAdapter;
    private BaseDelegateAdapter thirdServiceItemDecorationAdapter;
    private BaseDelegateAdapter thirdServiceAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        Log.i(TAG, "WechatWalletActivity onCreate");
//        setStatusBarTransparent();
        super.onCreate(savedInstanceState);
    }

    @Override
    protected int getLayoutId() {
        Log.i(TAG, "WechatWalletActivity getLayoutId");
        return R.layout.activity_wechat_wallet;
    }

    @Override
    protected void initView() {
        initWechatTopBar();
        setWechatTitle("我的钱包");
        btnRight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i(TAG, "点击二维码");
            }
        });
        rcvWallet = (RecyclerView) findViewById(R.id.rcvWallet);
//        setFullScreen();
//        initStatusBar();
    }

    @Override
    protected void initEvent() {

    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void initData() {

        TencentServiceItems.getInstance().clear();
        TencentServiceItems.getInstance().add(new TencentServiceItem(R.mipmap.app_views_pages_wechat_common_images_walleticon1, "信用卡还款"));
        TencentServiceItems.getInstance().add(new TencentServiceItem(R.mipmap.app_views_pages_wechat_common_images_walleticon2, "手机充值"));
        TencentServiceItems.getInstance().add(new TencentServiceItem(R.mipmap.app_views_pages_wechat_common_images_walleticon3, "理财通"));
        TencentServiceItems.getInstance().add(new TencentServiceItem(R.mipmap.app_views_pages_wechat_common_images_walleticon4, "生活缴费"));
        TencentServiceItems.getInstance().add(new TencentServiceItem(R.mipmap.app_views_pages_wechat_common_images_walleticon5, "Q币充值"));
        TencentServiceItems.getInstance().add(new TencentServiceItem(R.mipmap.app_views_pages_wechat_common_images_walleticon6, "城市服务"));
        TencentServiceItems.getInstance().add(new TencentServiceItem(R.mipmap.app_views_pages_wechat_common_images_walleticon7, "腾讯公益"));
        TencentServiceItems.getInstance().add(new TencentServiceItem(R.mipmap.app_views_pages_wechat_common_images_walleticon8, "保险服务"));

        WechatThirdServiceItems.getInstance().clear();
//        WechatThirdServiceItems.getInstance().add(new WechatThirdServiceItem());

        VirtualLayoutManager layoutManager = new VirtualLayoutManager(getThis());
        rcvWallet.setLayoutManager(layoutManager);
        RecyclerView.RecycledViewPool viewPool = new RecyclerView.RecycledViewPool();
        rcvWallet.setRecycledViewPool(viewPool);
        viewPool.setMaxRecycledViews(BaseDelegateAdapter.ITEM_COMMON, 30);
        delegateAdapter = new DelegateAdapter(layoutManager, true);
        walletAdapter = getWalletAdapter();
        tencentServiceItemDecorationAdapter = getTencentServiceItemDecorationAdapter();
        tencentServiceAdapter = getTencentServiceAdapter();
        spreadItemDecorationAdapter = getSpreadItemDecorationAdapter();
        spreadAdapter = getSpreadAdapter();
        thirdServiceItemDecorationAdapter = getThirdServiceItemDecorationAdapter();
        thirdServiceAdapter = getThirdServiceAdapter();
        mAdapters.add(walletAdapter);
        mAdapters.add(tencentServiceItemDecorationAdapter);
        mAdapters.add(tencentServiceAdapter);
//        mAdapters.add(spreadItemDecorationAdapter);
//        mAdapters.add(spreadAdapter);
//        mAdapters.add(thirdServiceItemDecorationAdapter);
//        mAdapters.add(thirdServiceAdapter);
        delegateAdapter.setAdapters(mAdapters);
        rcvWallet.setAdapter(delegateAdapter);
    }

    private BaseDelegateAdapter getThirdServiceAdapter() {
        GridLayoutHelper gridLayoutHelper = new GridLayoutHelper(3);
        gridLayoutHelper.setSpanCount(3);
        gridLayoutHelper.setItemCount(getItemSize(WechatItems.getInstance().size()));
        BaseDelegateAdapter walletAdapter = new BaseDelegateAdapter(getThis(), gridLayoutHelper, R.layout.main_wechat_wallet_item, getItemSize(WechatItems.getInstance().size()),
                BaseDelegateAdapter.ITEM_WECHAT) {

            @Override
            public void onBindViewHolder(BaseViewHolder holder, int position) {
                super.onBindViewHolder(holder, position);
                Log.i(TAG, "getWechatAdapter onBindViewHolder" + position);
                if (position < WechatItems.getInstance().size()) {
                    //初始化控件
                    LinearLayout llRoot = holder.getView(R.id.llRoot);
                    ImageView imgIcon = holder.getView(R.id.imgIcon);
                    TextView tvName = holder.getView(R.id.tvName);
                    final WechatItem item = WechatItems.getInstance().get(position);
                    Picasso.with(getThis()).load(item.getImgRes()).into(imgIcon);
                    tvName.setText(item.getName());
                    llRoot.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {

                        }
                    });
                }
            }
        };
        return walletAdapter;
    }

    private BaseDelegateAdapter getThirdServiceItemDecorationAdapter() {
        LinearLayoutHelper linearLayoutHelper = new LinearLayoutHelper();
        BaseDelegateAdapter thirdItemDecorationAdapter = new BaseDelegateAdapter(getThis(), linearLayoutHelper, R.layout.wechat_wallet_item_decoration, 1,
                BaseDelegateAdapter.ITEM_COMMON) {

            @Override
            public void onBindViewHolder(BaseViewHolder holder, int position) {
                super.onBindViewHolder(holder, position);
                View divider = holder.getView(R.id.divider);
                TextView tvName = holder.getView(R.id.tvName);
                tvName.setText("第三方服务");
            }
        };
        return thirdItemDecorationAdapter;
    }

    private BaseDelegateAdapter getSpreadAdapter() {
        GridLayoutHelper gridLayoutHelper = new GridLayoutHelper(3);
        gridLayoutHelper.setSpanCount(3);
        gridLayoutHelper.setItemCount(getItemSize(WechatSpreadItems.getInstance().size()));
        BaseDelegateAdapter spreadAdapter = new BaseDelegateAdapter(getThis(), gridLayoutHelper, R.layout.main_wechat_wallet_item, getItemSize(WechatSpreadItems.getInstance().size()),
                BaseDelegateAdapter.ITEM_COMMON) {

            @Override
            public void onBindViewHolder(BaseViewHolder holder, int position) {
                super.onBindViewHolder(holder, position);
                if (position < WechatSpreadItems.getInstance().size()) {
                    //初始化控件
                    LinearLayout llRoot = holder.getView(R.id.llRoot);
                    ImageView imgIcon = holder.getView(R.id.imgIcon);
                    TextView tvName = holder.getView(R.id.tvName);
                    final WechatSpreadItem item = WechatSpreadItems.getInstance().get(position);
                    Picasso.with(getThis()).load(item.getImgRes()).into(imgIcon);
                    tvName.setText(item.getName());
                    llRoot.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {


                        }
                    });
                }
            }
        };
        return spreadAdapter;
    }

    private BaseDelegateAdapter getSpreadItemDecorationAdapter() {
        LinearLayoutHelper linearLayoutHelper = new LinearLayoutHelper();
        BaseDelegateAdapter spreadItemDecorationAdapter = new BaseDelegateAdapter(getThis(), linearLayoutHelper, R.layout.wechat_wallet_item_decoration, 1,
                BaseDelegateAdapter.ITEM_COMMON) {

            @Override
            public void onBindViewHolder(BaseViewHolder holder, int position) {
                super.onBindViewHolder(holder, position);
                View divider = holder.getView(R.id.divider);
                TextView tvName = holder.getView(R.id.tvName);
                tvName.setText("限时推广");
            }
        };
        return spreadItemDecorationAdapter;
    }

    private BaseDelegateAdapter getTencentServiceAdapter() {
        GridLayoutHelper gridLayoutHelper = new GridLayoutHelper(3);
        gridLayoutHelper.setSpanCount(3);
        gridLayoutHelper.setHGap(2);
        gridLayoutHelper.setVGap(2);
        gridLayoutHelper.setItemCount(getItemSize(TencentServiceItems.getInstance().size()));
        BaseDelegateAdapter tencentServiceAdapter = new BaseDelegateAdapter(getThis(), gridLayoutHelper, R.layout.main_wechat_wallet_item, getItemSize(TencentServiceItems.getInstance().size()),
                BaseDelegateAdapter.ITEM_COMMON) {

            @Override
            public void onBindViewHolder(BaseViewHolder holder, int position) {
                super.onBindViewHolder(holder, position);
                LinearLayout llRoot = holder.getView(R.id.llRoot);
                if (position < TencentServiceItems.getInstance().size()) {
                    ImageView imgIcon = holder.getView(R.id.imgIcon);
                    TextView tvName = holder.getView(R.id.tvName);
                    final TencentServiceItem item = TencentServiceItems.getInstance().get(position);
                    Picasso.with(getThis()).load(item.getImgRes()).into(imgIcon);
                    tvName.setText(item.getName());
                    llRoot.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {


                        }
                    });
                }else{
                    llRoot.setBackgroundResource(R.color.windows_color);
                }
            }
        };
        return tencentServiceAdapter;
    }

    private BaseDelegateAdapter getTencentServiceItemDecorationAdapter() {
        LinearLayoutHelper linearLayoutHelper = new LinearLayoutHelper();
        BaseDelegateAdapter tencentServiceItemDecorationAdapter = new BaseDelegateAdapter(getThis(), linearLayoutHelper, R.layout.wechat_wallet_item_decoration, 1,
                BaseDelegateAdapter.ITEM_COMMON) {

            @Override
            public void onBindViewHolder(BaseViewHolder holder, int position) {
                super.onBindViewHolder(holder, position);
                View divider = holder.getView(R.id.divider);
                TextView tvName = holder.getView(R.id.tvName);
                tvName.setText("腾讯服务");
            }
        };
        return tencentServiceItemDecorationAdapter;
    }

    private BaseDelegateAdapter getWalletAdapter() {
        LinearLayoutHelper linearLayoutHelper = new LinearLayoutHelper();
        BaseDelegateAdapter walletAdapter = new BaseDelegateAdapter(getThis(), linearLayoutHelper, R.layout.wechat_wallet_layout, 1,
                BaseDelegateAdapter.ITEM_COMMON) {

            @Override
            public void onBindViewHolder(BaseViewHolder holder, int position) {
                super.onBindViewHolder(holder, position);
                LinearLayout llShoufukuan = holder.getView(R.id.llShoufukuan);
                LinearLayout llLingqian = holder.getView(R.id.llLingqian);
                LinearLayout llyinhangka = holder.getView(R.id.llyinhangka);
                TextView tvBalance = holder.getView(R.id.tvBalance);
                String balance = (String) SPUtils.get(getApplicationContext(), Constant.KEY_BALANCE, "");
                tvBalance.setText("¥" + balance);
                llShoufukuan.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                    }
                });
                llLingqian.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                    }
                });
                llyinhangka.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                    }
                });
            }
        };
        return walletAdapter;
    }

    private int getItemSize(int size) {
        if (size == 0) {
            return 0;
        } else if (size <= 3) {
            return 3;
        } else {
            return (size / 3 + 1) * 3;
        }
    }
}
