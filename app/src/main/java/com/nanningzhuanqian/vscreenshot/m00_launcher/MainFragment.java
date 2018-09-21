package com.nanningzhuanqian.vscreenshot.m00_launcher;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.alibaba.android.vlayout.DelegateAdapter;
import com.alibaba.android.vlayout.LayoutHelper;
import com.alibaba.android.vlayout.VirtualLayoutAdapter;
import com.alibaba.android.vlayout.VirtualLayoutManager;
import com.alibaba.android.vlayout.layout.GridLayoutHelper;
import com.alibaba.android.vlayout.layout.LinearLayoutHelper;
import com.nanningzhuanqian.vscreenshot.MainActivity;
import com.nanningzhuanqian.vscreenshot.R;
import com.nanningzhuanqian.vscreenshot.adapter.BaseDelegateAdapter;
import com.nanningzhuanqian.vscreenshot.adapter.BaseViewHolder;
import com.nanningzhuanqian.vscreenshot.base.BaseFragment;
import com.nanningzhuanqian.vscreenshot.base.Util;
import com.nanningzhuanqian.vscreenshot.base.util.LoginUtils;
import com.nanningzhuanqian.vscreenshot.item.AliPayItem;
import com.nanningzhuanqian.vscreenshot.item.AliPayItems;
import com.nanningzhuanqian.vscreenshot.item.HelpItem;
import com.nanningzhuanqian.vscreenshot.item.HelpItems;
import com.nanningzhuanqian.vscreenshot.item.WechatItem;
import com.nanningzhuanqian.vscreenshot.item.WechatItems;
import com.nanningzhuanqian.vscreenshot.item.WeixinCallItem;
import com.nanningzhuanqian.vscreenshot.item.WeixinCallItems;
import com.nanningzhuanqian.vscreenshot.m01_wechat_main.WechatBillActivity;
import com.nanningzhuanqian.vscreenshot.m01_wechat_main.WechatChangeActivity;
import com.nanningzhuanqian.vscreenshot.m01_wechat_main.WechatChangeDetailActivity;
import com.nanningzhuanqian.vscreenshot.m01_wechat_main.WechatChangeWithdrawActivity;
import com.nanningzhuanqian.vscreenshot.m01_wechat_main.WechatChooseSingleChatActivity;
import com.nanningzhuanqian.vscreenshot.m01_wechat_main.WechatGlobalSettingActivity;
import com.nanningzhuanqian.vscreenshot.m01_wechat_main.WechatGroupChatActivity;
import com.nanningzhuanqian.vscreenshot.m01_wechat_main.WechatMomentActivity;
import com.nanningzhuanqian.vscreenshot.m01_wechat_main.WechatNewFriendActivity;
import com.nanningzhuanqian.vscreenshot.m01_wechat_main.WechatPayActivity;
import com.nanningzhuanqian.vscreenshot.m01_wechat_main.WechatRedPacketActivity;
import com.nanningzhuanqian.vscreenshot.m01_wechat_main.WechatSingeChatActivity;
import com.nanningzhuanqian.vscreenshot.m01_wechat_main.WechatTransferActivity;
import com.nanningzhuanqian.vscreenshot.m01_wechat_main.WechatWalletActivity;
import com.squareup.picasso.Picasso;

import java.util.LinkedList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class MainFragment extends BaseFragment {

    private View rootView;
    private RecyclerView rcvService;
    private SwipeRefreshLayout srlRefresh;
    private BaseDelegateAdapter wechatAdapter;
    private BaseDelegateAdapter aliPayAdapter;
    private BaseDelegateAdapter warmingAdapter;
    private BaseDelegateAdapter bannerAdapter;
    private BaseDelegateAdapter weixinItemDecoration;
    private BaseDelegateAdapter aliPayItemDecoration;
    private BaseDelegateAdapter weixinCallItemDecoration;
    private BaseDelegateAdapter weixinCallAdapter;
    private BaseDelegateAdapter helpAndWaterMarkItemDecoration;
    private BaseDelegateAdapter helpAndWaterMarkAdapter;

    private DelegateAdapter delegateAdapter;
    private List<DelegateAdapter.Adapter> mAdapters = new LinkedList<>();
    private String qq;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_main, container, false);
        initView();
        initData();
        initEvent();
        return rootView;
    }

    private void initEvent() {

    }

    private void initData() {
        WechatItems.getInstance().clear();
        WechatItems.getInstance().add(new WechatItem(R.mipmap.app_images_iconwechathome, "微信主页", 0));
        WechatItems.getInstance().add(new WechatItem(R.mipmap.app_images_iconwechat, "微信（单聊）", 1));
        WechatItems.getInstance().add(new WechatItem(R.mipmap.app_images_iconwechat, "微信（群聊）", 2));
        WechatItems.getInstance().add(new WechatItem(R.mipmap.app_images_iconwechatc2c, "微信转账", 3));
        WechatItems.getInstance().add(new WechatItem(R.mipmap.app_images_iconwechatwallet, "微信钱包", 4));
        WechatItems.getInstance().add(new WechatItem(R.mipmap.app_images_iconwechatfd, "新的朋友", 5));
        WechatItems.getInstance().add(new WechatItem(R.mipmap.app_images_iconwechatredpacket, "微信红包", 6));
        WechatItems.getInstance().add(new WechatItem(R.mipmap.app_images_iconpoketmoney, "零钱", 7));
        WechatItems.getInstance().add(new WechatItem(R.mipmap.app_images_iconmoneydetail, "零钱明细", 8));
        WechatItems.getInstance().add(new WechatItem(R.mipmap.app_images_iconwechatmoneyout, "零钱提现", 9));
//        WechatItems.getInstance().add(new WechatItem(R.mipmap.app_images_iconwechattimeline, "朋友圈", 10));
        WechatItems.getInstance().add(new WechatItem(R.mipmap.app_images_iconwechatwallettrans, "账单", 11));
        WechatItems.getInstance().add(new WechatItem(R.mipmap.app_images_iconwechat, "微信支付", 12));
        WechatItems.getInstance().add(new WechatItem(R.mipmap.app_images_iconwechat, "微信设置", 13));

        AliPayItems.getInstance().clear();
        AliPayItems.getInstance().add(new AliPayItem(R.mipmap.app_images_iconalipaychat, "支付宝聊天", 0));
        AliPayItems.getInstance().add(new AliPayItem(R.mipmap.app_images_iconalipayhome, "支付宝我的", 1));
        AliPayItems.getInstance().add(new AliPayItem(R.mipmap.app_images_iconalipayfriends, "支付宝朋友", 2));
        AliPayItems.getInstance().add(new AliPayItem(R.mipmap.app_images_iconalipaycontact, "支付宝通讯录", 3));
        AliPayItems.getInstance().add(new AliPayItem(R.mipmap.app_images_iconalipaynew, "支付宝转账", 4));
        AliPayItems.getInstance().add(new AliPayItem(R.mipmap.app_images_iconalipaybalance, "支付宝余额", 5));
        AliPayItems.getInstance().add(new AliPayItem(R.mipmap.app_images_iconalipaymoneyout, "支付宝提现", 6));
        AliPayItems.getInstance().add(new AliPayItem(R.mipmap.app_images_iconalipaymeinfo, "个人信息", 7));
        AliPayItems.getInstance().add(new AliPayItem(R.mipmap.app_images_iconalipaytocard, "转到银行卡", 8));
        AliPayItems.getInstance().add(new AliPayItem(R.mipmap.app_images_iconalipaybill, "支付宝账单", 9));

        VirtualLayoutManager layoutManager = new VirtualLayoutManager(getActivity());
        rcvService.setLayoutManager(layoutManager);
        RecyclerView.RecycledViewPool viewPool = new RecyclerView.RecycledViewPool();
        rcvService.setRecycledViewPool(viewPool);
        viewPool.setMaxRecycledViews(BaseDelegateAdapter.ITEM_ALIPAY, 30);
        viewPool.setMaxRecycledViews(BaseDelegateAdapter.ITEM_WECHAT, 30);
        delegateAdapter = new DelegateAdapter(layoutManager, true);
        wechatAdapter = getWechatAdapter();
        aliPayAdapter = getAliPayAdapter();
        weixinItemDecoration = getWeixinItemDecoration();
        aliPayItemDecoration = getAliPayItemDecoration();
        weixinCallItemDecoration = getWeixinCallItemDecoration();
        weixinCallAdapter = getWeixinCallAdapter();
        helpAndWaterMarkItemDecoration = getHelpAndWaterMarkItemDecoration();
        helpAndWaterMarkAdapter = getHelpAndWaterMarkAdapter();
        warmingAdapter = getWarmingAdapter();
        bannerAdapter = getBannerAdapter();
        mAdapters.add(warmingAdapter);
        mAdapters.add(bannerAdapter);
        mAdapters.add(weixinItemDecoration);
        mAdapters.add(wechatAdapter);
        mAdapters.add(aliPayItemDecoration);
        mAdapters.add(aliPayAdapter);
        mAdapters.add(weixinCallItemDecoration);
        mAdapters.add(weixinCallAdapter);
        mAdapters.add(helpAndWaterMarkItemDecoration);
        mAdapters.add(helpAndWaterMarkAdapter);
        delegateAdapter.setAdapters(mAdapters);
        rcvService.setAdapter(delegateAdapter);
        qq = Util.getContractQQ(getThis());
    }

    private void initView() {
        rcvService = (RecyclerView) rootView.findViewById(R.id.rcvService);
        srlRefresh = (SwipeRefreshLayout) rootView.findViewById(R.id.srlRefresh);
    }

    private BaseDelegateAdapter getWechatAdapter() {
        Log.i(TAG, "getWechatAdapter size = " + WechatItems.getInstance().size());
        GridLayoutHelper gridLayoutHelper = new GridLayoutHelper(4);
        gridLayoutHelper.setSpanCount(4);
        gridLayoutHelper.setItemCount(getItemSize(WechatItems.getInstance().size()));
        BaseDelegateAdapter wechatAdapter = new BaseDelegateAdapter(getActivity(), gridLayoutHelper, R.layout.main_home_fragment_item, getItemSize(WechatItems.getInstance().size()),
                BaseDelegateAdapter.ITEM_WECHAT) {

            @Override
            public void onBindViewHolder(BaseViewHolder holder, int position) {
                super.onBindViewHolder(holder, position);
                Log.i(TAG, "getWechatAdapter onBindViewHolder" + position);
                if (position < WechatItems.getInstance().size()) {
                    //初始化控件
                    LinearLayout llRoot = holder.getView(R.id.llRoot);
                    CircleImageView imgIcon = holder.getView(R.id.imgIcon);
                    TextView tvName = holder.getView(R.id.tvName);
                    final WechatItem item = WechatItems.getInstance().get(position);
                    Picasso.with(getActivity()).load(item.getImgRes()).into(imgIcon);
                    tvName.setText(item.getName());
                    llRoot.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent intent;
                            switch (item.getType()) {
                                case 0:
                                    intent = new Intent(getThis(), MainActivity.class);
                                    startActivity(intent);
                                    break;
                                case 1:
                                    intent = new Intent(getThis(), WechatChooseSingleChatActivity.class);
                                    startActivity(intent);
                                    break;
                                case 2:
                                    intent = new Intent(getThis(), WechatGroupChatActivity.class);
                                    startActivity(intent);
                                    break;
                                case 3:
                                    intent = new Intent(getThis(), WechatTransferActivity.class);
                                    startActivity(intent);
                                    break;
                                case 4:
                                    intent = new Intent(getThis(), WechatWalletActivity.class);
                                    startActivity(intent);
                                    break;
                                case 5:
                                    intent = new Intent(getThis(), WechatNewFriendActivity.class);
                                    startActivity(intent);
                                    break;
                                case 6:
                                    intent = new Intent(getThis(), WechatRedPacketActivity.class);
                                    startActivity(intent);
                                    break;
                                case 7:
                                    intent = new Intent(getThis(), WechatChangeActivity.class);
                                    startActivity(intent);
                                    break;
                                case 8:
                                    intent = new Intent(getThis(), WechatChangeDetailActivity.class);
                                    startActivity(intent);
                                    break;
                                case 9:
                                    intent = new Intent(getThis(), WechatChangeWithdrawActivity.class);
                                    startActivity(intent);
                                    break;
                                case 10:
                                    intent = new Intent(getThis(), WechatMomentActivity.class);
                                    startActivity(intent);
                                    break;
                                case 11:
                                    intent = new Intent(getThis(), WechatBillActivity.class);
                                    startActivity(intent);
                                    break;
                                case 12:
                                    intent = new Intent(getThis(), WechatPayActivity.class);
                                    startActivity(intent);
                                    break;
                                case 13:
                                    intent = new Intent(getThis(), WechatGlobalSettingActivity.class);
                                    startActivity(intent);
                                    break;
                            }

                        }
                    });
                }
            }
        };
        return wechatAdapter;
    }

    private BaseDelegateAdapter getAliPayAdapter() {
        GridLayoutHelper gridLayoutHelper = new GridLayoutHelper(4);
        gridLayoutHelper.setSpanCount(4);
        gridLayoutHelper.setItemCount(getItemSize(AliPayItems.getInstance().size()));
        BaseDelegateAdapter aliPayAdapter = new BaseDelegateAdapter(getActivity(), gridLayoutHelper, R.layout.main_home_fragment_item, getItemSize(AliPayItems.getInstance().size()),
                BaseDelegateAdapter.ITEM_ALIPAY) {

            @Override
            public void onBindViewHolder(BaseViewHolder holder, int position) {
                super.onBindViewHolder(holder, position);
                //初始化控件
                if (position < AliPayItems.getInstance().size()) {
                    LinearLayout llRoot = holder.getView(R.id.llRoot);
                    CircleImageView imgIcon = holder.getView(R.id.imgIcon);
                    TextView tvName = holder.getView(R.id.tvName);
                    AliPayItem item = AliPayItems.getInstance().get(position);
                    Picasso.with(getActivity()).load(item.getImgRes()).into(imgIcon);
                    tvName.setText(item.getName());
                    llRoot.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {

                        }
                    });
                }
            }
        };
        return aliPayAdapter;
    }

    private BaseDelegateAdapter getWeixinCallAdapter() {
        GridLayoutHelper gridLayoutHelper = new GridLayoutHelper(4);
        gridLayoutHelper.setSpanCount(4);
        gridLayoutHelper.setItemCount(getItemSize(WeixinCallItems.getInstance().size()));
        BaseDelegateAdapter weixinCallAdapter = new BaseDelegateAdapter(getActivity(), gridLayoutHelper, R.layout.main_home_fragment_item, getItemSize(WeixinCallItems.getInstance().size()),
                BaseDelegateAdapter.ITEM_WEIXIN_CALL) {

            @Override
            public void onBindViewHolder(BaseViewHolder holder, int position) {
                super.onBindViewHolder(holder, position);
                //初始化控件
                if (position < WeixinCallItems.getInstance().size()) {
                    LinearLayout llRoot = holder.getView(R.id.llRoot);
                    CircleImageView imgIcon = holder.getView(R.id.imgIcon);
                    TextView tvName = holder.getView(R.id.tvName);
                    WeixinCallItem item = WeixinCallItems.getInstance().get(position);
                    Picasso.with(getActivity()).load(item.getImgRes()).into(imgIcon);
                    tvName.setText(item.getName());
                    llRoot.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {

                        }
                    });
                }
            }
        };
        return weixinCallAdapter;
    }

    private BaseDelegateAdapter getHelpAndWaterMarkAdapter() {
        GridLayoutHelper gridLayoutHelper = new GridLayoutHelper(4);
        gridLayoutHelper.setSpanCount(4);
        gridLayoutHelper.setItemCount(getItemSize(HelpItems.getInstance().size()));
        BaseDelegateAdapter helpAndWaterMarkAdapter = new BaseDelegateAdapter(getActivity(), gridLayoutHelper, R.layout.main_home_fragment_item, getItemSize(HelpItems.getInstance().size()),
                BaseDelegateAdapter.ITEM_HELP) {

            @Override
            public void onBindViewHolder(BaseViewHolder holder, int position) {
                super.onBindViewHolder(holder, position);
                //初始化控件
                if (position < HelpItems.getInstance().size()) {
                    LinearLayout llRoot = holder.getView(R.id.llRoot);
                    CircleImageView imgIcon = holder.getView(R.id.imgIcon);
                    TextView tvName = holder.getView(R.id.tvName);
                    HelpItem item = HelpItems.getInstance().get(position);
                    Picasso.with(getActivity()).load(item.getImgRes()).into(imgIcon);
                    tvName.setText(item.getName());
                    llRoot.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {

                        }
                    });
                }
            }
        };
        return helpAndWaterMarkAdapter;
    }

    private BaseDelegateAdapter getWarmingAdapter() {
        LinearLayoutHelper linearLayoutHelper = new LinearLayoutHelper();
        BaseDelegateAdapter warmingAdapter = new BaseDelegateAdapter(getActivity(), linearLayoutHelper, R.layout.main_home_warming_item, 1,
                BaseDelegateAdapter.ITEM_TOP_ITEMDECORATION) {
            @Override
            public void onBindViewHolder(BaseViewHolder holder, int position) {
                super.onBindViewHolder(holder, position);
            }
        };
        return warmingAdapter;
    }

    private BaseDelegateAdapter getBannerAdapter() {
        LinearLayoutHelper linearLayoutHelper = new LinearLayoutHelper();
        BaseDelegateAdapter bannerAdapter = new BaseDelegateAdapter(getActivity(), linearLayoutHelper, R.layout.main_home_banner_item, 1,
                BaseDelegateAdapter.ITEM_TOP_ITEMDECORATION) {
            @Override
            public void onBindViewHolder(BaseViewHolder holder, int position) {
                super.onBindViewHolder(holder, position);
                LinearLayout llRoot = holder.getView(R.id.llRoot);
                llRoot.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (Util.isQQClientAvailable(getThis())) {
                            final String qqUrl = "mqqwpa://im/chat?chat_type=wpa&uin=" + qq + "&version=1";
                            startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(qqUrl)));
                        } else {
                            toast("请安装QQ客户端");
                        }
                    }
                });
            }
        };
        return bannerAdapter;
    }

    private BaseDelegateAdapter getWeixinItemDecoration() {
        LinearLayoutHelper linearLayoutHelper = new LinearLayoutHelper();
        BaseDelegateAdapter weixinItemDecoration = new BaseDelegateAdapter(getActivity(), linearLayoutHelper, R.layout.main_home_itemdecoration, 1,
                BaseDelegateAdapter.ITEM_TOP_ITEMDECORATION) {
            @Override
            public void onBindViewHolder(BaseViewHolder holder, int position) {
                super.onBindViewHolder(holder, position);
                //初始化控件
                View divider = holder.getView(R.id.divider);
                TextView tvName = holder.getView(R.id.tvName);
                divider.setVisibility(View.GONE);
                tvName.setText("微信");
            }
        };
        return weixinItemDecoration;
    }

    private BaseDelegateAdapter getAliPayItemDecoration() {
        LinearLayoutHelper linearLayoutHelper = new LinearLayoutHelper();
        BaseDelegateAdapter aliPayItemDecoration = new BaseDelegateAdapter(getActivity(), linearLayoutHelper, R.layout.main_home_itemdecoration, 1,
                BaseDelegateAdapter.ITEM_COMMON_ITEMDECOIRATION) {
            @Override
            public void onBindViewHolder(BaseViewHolder holder, int position) {
                super.onBindViewHolder(holder, position);
                //初始化控件
                View divider = holder.getView(R.id.divider);
                TextView tvName = holder.getView(R.id.tvName);
                tvName.setText("支付宝");
            }
        };
        return aliPayItemDecoration;
    }

    private BaseDelegateAdapter getWeixinCallItemDecoration() {
        LinearLayoutHelper linearLayoutHelper = new LinearLayoutHelper();
        BaseDelegateAdapter weixinCallItemDecoration = new BaseDelegateAdapter(getActivity(), linearLayoutHelper, R.layout.main_home_itemdecoration, 1,
                BaseDelegateAdapter.ITEM_COMMON_ITEMDECOIRATION) {
            @Override
            public void onBindViewHolder(BaseViewHolder holder, int position) {
                super.onBindViewHolder(holder, position);
                //初始化控件
                TextView tvName = holder.getView(R.id.tvName);
                tvName.setText("微信通话");
            }
        };
        return weixinCallItemDecoration;
    }

    private BaseDelegateAdapter getHelpAndWaterMarkItemDecoration() {
        LinearLayoutHelper linearLayoutHelper = new LinearLayoutHelper();
        BaseDelegateAdapter helpItemDecoration = new BaseDelegateAdapter(getActivity(), linearLayoutHelper, R.layout.main_home_itemdecoration, 1,
                BaseDelegateAdapter.ITEM_COMMON_ITEMDECOIRATION) {
            @Override
            public void onBindViewHolder(BaseViewHolder holder, int position) {
                super.onBindViewHolder(holder, position);
                //初始化控件
                TextView tvName = holder.getView(R.id.tvName);
                tvName.setText("帮助与去水印");
            }
        };
        return helpItemDecoration;
    }

    private int getItemSize(int size) {
        if (size == 0) {
            return 0;
        } else if ((size % 4) == 0) {
            Log.i(TAG, "getItemSize = " + size);
            return size;
        } else {
            return (size / 4 + 1) * 4;
        }
    }


}
