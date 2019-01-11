package com.nanningzhuanqian.vscreenshot.m01_wechat.wechat.wallet;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.alibaba.android.vlayout.DelegateAdapter;
import com.alibaba.android.vlayout.VirtualLayoutManager;
import com.alibaba.android.vlayout.layout.GridLayoutHelper;
import com.alibaba.android.vlayout.layout.LinearLayoutHelper;
import com.nanningzhuanqian.vscreenshot.R;
import com.nanningzhuanqian.vscreenshot.adapter.BaseDelegateAdapter;
import com.nanningzhuanqian.vscreenshot.adapter.BaseViewHolder;
import com.nanningzhuanqian.vscreenshot.base.BaseActivity;
import com.nanningzhuanqian.vscreenshot.base.net.CallbackListener;
import com.nanningzhuanqian.vscreenshot.base.net.HttpUtil;
import com.nanningzhuanqian.vscreenshot.base.util.SPUtils;
import com.nanningzhuanqian.vscreenshot.common.Constant;
import com.nanningzhuanqian.vscreenshot.item.WechatWalletItem;
import com.nanningzhuanqian.vscreenshot.model.WechatWalletConfig;
import com.nanningzhuanqian.vscreenshot.model.WechatWalletConfigLite;
import com.squareup.picasso.Picasso;

import org.litepal.LitePal;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * 微信钱包页面
 */
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
    private List<WechatWalletItem> tencentServiceItems = new ArrayList<>();
    private List<WechatWalletItem> spreadItems = new ArrayList<>();
    private List<WechatWalletItem> thirdServiceItems = new ArrayList<>();
    private  VirtualLayoutManager layoutManager;


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
        layoutManager = new VirtualLayoutManager(getThis());
        rcvWallet.setLayoutManager(layoutManager);
        RecyclerView.RecycledViewPool viewPool = new RecyclerView.RecycledViewPool();
        rcvWallet.setRecycledViewPool(viewPool);
        viewPool.setMaxRecycledViews(BaseDelegateAdapter.ITEM_COMMON, 30);
    }

    @Override
    protected void initEvent() {
    }

    @Override
    protected void onResume() {
        super.onResume();
        HttpUtil.getInstance().getWechatWalletContent(new CallbackListener() {
            @Override
            public void onSuccess() {

            }

            @Override
            public void onGetSuccess(Object o) {
                List<WechatWalletConfig> configs = (List<WechatWalletConfig>) o;
                LitePal.deleteAll(WechatWalletConfigLite.class);
                tencentServiceItems.clear();
                spreadItems.clear();
                thirdServiceItems.clear();
                for(int i = 0;i<configs.size();i++){
                    WechatWalletItem item = configs.get(i).coverToWechatWalletBaseItem();
                    WechatWalletConfigLite configLite = configs.get(i).coverToWechatWalletConfigLite();
                    configLite.save();
                    if("00".equals(item.getConfigType())){
                        tencentServiceItems.add(item);
                    }else if("01".equals(item.getConfigType())){
                        spreadItems.add(item);
                    }else if("02".equals(item.getConfigType())){
                        thirdServiceItems.add(item);
                    }
                }
                initAdapter();
            }

            @Override
            public void onFailure(String message) {
                getLocalConfig();
                initAdapter();
            }
        });
    }

    @Override
    protected void initData() {

    }

    private void getLocalConfig(){
        List<WechatWalletConfigLite> configLites = LitePal.findAll(WechatWalletConfigLite.class);
        tencentServiceItems.clear();
        spreadItems.clear();
        thirdServiceItems.clear();
        for(int i = 0;i<configLites.size();i++){
            WechatWalletItem item = configLites.get(i).coverToWechatWalletBaseItem();
            if("00".equals(item.getConfigType())){
                tencentServiceItems.add(item);
            }else if("01".equals(item.getConfigType())){
                spreadItems.add(item);
            }else if("02".equals(item.getConfigType())){
                thirdServiceItems.add(item);
            }
        }
    }

    private void initAdapter() {
        mAdapters.clear();
        delegateAdapter = new DelegateAdapter(layoutManager, true);
        walletAdapter = getWalletAdapter();
        mAdapters.add(walletAdapter);
        if(tencentServiceItems.size()!=0) {
            tencentServiceItemDecorationAdapter = getTencentServiceItemDecorationAdapter();
            tencentServiceAdapter = getTencentServiceAdapter();
            mAdapters.add(tencentServiceItemDecorationAdapter);
            mAdapters.add(tencentServiceAdapter);
        }
        if(spreadItems.size()!=0) {
            spreadItemDecorationAdapter = getSpreadItemDecorationAdapter();
            spreadAdapter = getSpreadAdapter();
            mAdapters.add(spreadItemDecorationAdapter);
            mAdapters.add(spreadAdapter);
        }
        if(thirdServiceItems.size()!=0) {
            thirdServiceItemDecorationAdapter = getThirdServiceItemDecorationAdapter();
            thirdServiceAdapter = getThirdServiceAdapter();
            mAdapters.add(thirdServiceItemDecorationAdapter);
            mAdapters.add(thirdServiceAdapter);
        }

        delegateAdapter.setAdapters(mAdapters);
        rcvWallet.setAdapter(delegateAdapter);
    }

    private BaseDelegateAdapter getThirdServiceAdapter() {
        GridLayoutHelper gridLayoutHelper = new GridLayoutHelper(3);
        gridLayoutHelper.setSpanCount(3);
        gridLayoutHelper.setItemCount(getItemSize(thirdServiceItems.size()));
        BaseDelegateAdapter walletAdapter = new BaseDelegateAdapter(getThis(), gridLayoutHelper, R.layout.main_wechat_wallet_item, getItemSize(thirdServiceItems.size()),
                BaseDelegateAdapter.ITEM_WECHAT) {

            @Override
            public void onBindViewHolder(BaseViewHolder holder, int position) {
                super.onBindViewHolder(holder, position);
                Log.i(TAG, "getWechatAdapter onBindViewHolder" + position);
                View dividerRight = holder.getView(R.id.dividerRight);
                View dividerBottom = holder.getView(R.id.dividerBottom);
                RelativeLayout llRoot = holder.getView(R.id.llRoot);
                if (position < thirdServiceItems.size()) {
                    //初始化控件
                    ImageView imgIcon = holder.getView(R.id.imgIcon);
                    TextView tvName = holder.getView(R.id.tvName);
                    final WechatWalletItem item = thirdServiceItems.get(position);
                    Picasso.with(getThis()).load(item.getImgUrl()).into(imgIcon);
                    tvName.setText(item.getName());
                    llRoot.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {

                        }
                    });
                    dividerRight.setVisibility(View.VISIBLE);
                    dividerBottom.setVisibility(View.VISIBLE);
                    if((position+1)%3==0){
                        dividerRight.setVisibility(View.GONE);
                    }else{
                        dividerRight.setVisibility(View.VISIBLE);
                    }
                    if( thirdServiceItems.size()<(position/3+1)*3){
                        dividerBottom.setVisibility(View.GONE);
                    }else{
                        dividerBottom.setVisibility(View.VISIBLE);
                    }
                }else{
                    llRoot.setBackgroundResource(R.color.windows_color);
                    dividerRight.setVisibility(View.GONE);
                    dividerBottom.setVisibility(View.GONE);
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
        gridLayoutHelper.setItemCount(getItemSize(spreadItems.size()));
        BaseDelegateAdapter spreadAdapter = new BaseDelegateAdapter(getThis(), gridLayoutHelper, R.layout.main_wechat_wallet_item, getItemSize(spreadItems.size()),
                BaseDelegateAdapter.ITEM_COMMON) {

            @Override
            public void onBindViewHolder(BaseViewHolder holder, int position) {
                super.onBindViewHolder(holder, position);
                View dividerRight = holder.getView(R.id.dividerRight);
                View dividerBottom = holder.getView(R.id.dividerBottom);
                RelativeLayout llRoot = holder.getView(R.id.llRoot);
                if (position < spreadItems.size()) {
                    //初始化控件
                    ImageView imgIcon = holder.getView(R.id.imgIcon);
                    TextView tvName = holder.getView(R.id.tvName);
                    final WechatWalletItem item = spreadItems.get(position);
                    Picasso.with(getThis()).load(item.getImgUrl()).into(imgIcon);
                    tvName.setText(item.getName());
                    llRoot.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {


                        }
                    });
                    dividerRight.setVisibility(View.VISIBLE);
                    dividerBottom.setVisibility(View.VISIBLE);
                    if((position+1)%3==0){
                        dividerRight.setVisibility(View.GONE);
                    }else{
                        dividerRight.setVisibility(View.VISIBLE);
                    }
                    if( spreadItems.size()<(position/3+1)*3){
                        dividerBottom.setVisibility(View.GONE);
                    }else{
                        dividerBottom.setVisibility(View.VISIBLE);
                    }
                }else{
                    llRoot.setBackgroundResource(R.color.windows_color);
                    dividerRight.setVisibility(View.GONE);
                    dividerBottom.setVisibility(View.GONE);
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
        gridLayoutHelper.setItemCount(getItemSize(tencentServiceItems.size()));
        BaseDelegateAdapter tencentServiceAdapter = new BaseDelegateAdapter(getThis(), gridLayoutHelper, R.layout.main_wechat_wallet_item, getItemSize(tencentServiceItems.size()),
                BaseDelegateAdapter.ITEM_COMMON) {

            @Override
            public void onBindViewHolder(BaseViewHolder holder, int position) {
                super.onBindViewHolder(holder, position);
                RelativeLayout llRoot = holder.getView(R.id.llRoot);
                View dividerRight = holder.getView(R.id.dividerRight);
                View dividerBottom = holder.getView(R.id.dividerBottom);
                if (position < tencentServiceItems.size()) {
                    ImageView imgIcon = holder.getView(R.id.imgIcon);
                    TextView tvName = holder.getView(R.id.tvName);
                    final WechatWalletItem item = tencentServiceItems.get(position);
                    Picasso.with(getThis()).load(item.getImgUrl()).into(imgIcon);
                    tvName.setText(item.getName());
                    llRoot.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {

                        }
                    });
                    dividerRight.setVisibility(View.VISIBLE);
                    dividerBottom.setVisibility(View.VISIBLE);
                    if((position+1)%3==0){
                        dividerRight.setVisibility(View.GONE);
                    }else{
                        dividerRight.setVisibility(View.VISIBLE);
                    }
                    if( tencentServiceItems.size()<(position/3+1)*3){
                        dividerBottom.setVisibility(View.GONE);
                    }else{
                        dividerBottom.setVisibility(View.VISIBLE);
                    }
                }else{
                    llRoot.setBackgroundResource(R.color.windows_color);
                    dividerRight.setVisibility(View.GONE);
                    dividerBottom.setVisibility(View.GONE);
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
