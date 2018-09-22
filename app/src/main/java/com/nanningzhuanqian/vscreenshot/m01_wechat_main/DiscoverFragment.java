package com.nanningzhuanqian.vscreenshot.m01_wechat_main;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.nanningzhuanqian.vscreenshot.R;
import com.nanningzhuanqian.vscreenshot.adapter.ContractAdapter;
import com.nanningzhuanqian.vscreenshot.adapter.DiscoveryAdapter;
import com.nanningzhuanqian.vscreenshot.base.BaseFragment;
import com.nanningzhuanqian.vscreenshot.base.util.PinyinUtils;
import com.nanningzhuanqian.vscreenshot.base.util.SPUtils;
import com.nanningzhuanqian.vscreenshot.base.util.TypeComparator;
import com.nanningzhuanqian.vscreenshot.common.Constant;
import com.nanningzhuanqian.vscreenshot.item.ContractItem;
import com.nanningzhuanqian.vscreenshot.item.DiscoveryItem;
import com.nanningzhuanqian.vscreenshot.item.DiscoveryItems;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by WMY on 2018/9/14.
 */

public class DiscoverFragment extends BaseFragment {

    private RecyclerView rcvDiscovery;
    private View rootView;
    private DiscoveryAdapter discoveryAdapter;
    private List<DiscoveryItem> discoveryItems = new ArrayList<>();
    private TypeComparator typeComparator  = new TypeComparator();
    private boolean showKanyikan;
    private boolean showSouyisou;
    private boolean showNearBy;
    private boolean showPiaoliuping;
    private int discoveryUnReadCount;
    private int momentUnReadCount;
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_discover,container,false);
        initView();
        initEvent();
        initData();
        return rootView;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onResume() {
        super.onResume();
        getShowConfig();
    }

    private void getShowConfig() {
        showKanyikan = (boolean) SPUtils.get(getThis(), Constant.KEY_SHOW_KANYIKAN,false);
        showSouyisou = (boolean) SPUtils.get(getThis(), Constant.KEY_SHOW_SOUYISOU,false);
        showNearBy = (boolean) SPUtils.get(getThis(), Constant.KEY_SHOW_NEARBY,false);
        showPiaoliuping = (boolean) SPUtils.get(getThis(), Constant.KEY_SHOW_PIAOLIUPING,false);
        momentUnReadCount = (int)SPUtils.get(getThis(),Constant.KEY_MOMENT_UNREAD_COUNT,0);
        discoveryUnReadCount = (int)SPUtils.get(getThis(),Constant.KEY_DISCOVERY_UNREAD_COUNT,0);
        discoveryItems.clear();
        String imgResource = (String) SPUtils.get(getActivity(),Constant.KEY_MOMENT_AVATAR,"");
        int imgRes = 0;
        if(TextUtils.isEmpty(imgResource)){

        }else{
            imgRes = Integer.valueOf(imgResource);
        }
        int momentUnReadCount = (int) SPUtils.get(getActivity(),Constant.KEY_MOMENT_UNREAD_COUNT,0);
        DiscoveryItem momentDiscoveryItem = new DiscoveryItem(DiscoveryAdapter.ITEM_1,"朋友圈",R.mipmap
                .app_views_pages_wechat_home_images_discovericon1,0,imgRes,"","");
        momentDiscoveryItem.setBadge(momentUnReadCount);
        discoveryItems.add(momentDiscoveryItem);
        discoveryItems.add(new DiscoveryItem(DiscoveryAdapter.ITEM_2,"扫一扫",R.mipmap.app_views_pages_wechat_home_images_discovericon2));
        discoveryItems.add(new DiscoveryItem(DiscoveryAdapter.ITEM_2,"摇一摇",R.mipmap.app_views_pages_wechat_home_images_discovericon3));
        if(showKanyikan) {
            discoveryItems.add(new DiscoveryItem(DiscoveryAdapter.ITEM_3, "看一看", R.mipmap.app_views_pages_wechat_home_images_discovericon4));
        }
        if(showSouyisou) {
            discoveryItems.add(new DiscoveryItem(DiscoveryAdapter.ITEM_3, "搜一搜", R.mipmap.app_views_pages_wechat_home_images_discovericon5));
        }
        if(showNearBy) {
            discoveryItems.add(new DiscoveryItem(DiscoveryAdapter.ITEM_4, "附近的人", R.mipmap.app_views_pages_wechat_home_images_discovericon6));
        }
        if(showPiaoliuping) {
            discoveryItems.add(new DiscoveryItem(DiscoveryAdapter.ITEM_4, "漂流瓶", R.mipmap
                    .app_views_pages_wechat_home_images_discovericon7));
        }
        discoveryItems.add(new DiscoveryItem(DiscoveryAdapter.ITEM_5, "购物", R.mipmap.app_views_pages_wechat_home_images_discovericon8));
        discoveryItems.add(new DiscoveryItem(DiscoveryAdapter.ITEM_5,"游戏",R.mipmap
                .app_views_pages_wechat_home_images_discovericon9,0,R.mipmap
                .app_views_pages_wechat_home_images_discovericongame,"","跳伞开车捡装备"));
        discoveryItems.add(new DiscoveryItem(DiscoveryAdapter.ITEM_6,"小程序",R.mipmap
                .app_views_pages_wechat_home_images_discovericon10));
        DiscoveryItems.getInstance().clear();
        DiscoveryItems.getInstance().add(discoveryItems);
        discoveryAdapter.notifyDataSetChanged();
    }

    private void initView() {
        rcvDiscovery = (RecyclerView)rootView.findViewById(R.id.rcvDiscovery);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        discoveryAdapter = new DiscoveryAdapter(getActivity());
        //设置布局管理器
        rcvDiscovery.setLayoutManager(layoutManager);
        //设置为垂直布局，这也是默认的
        layoutManager.setOrientation(OrientationHelper.VERTICAL);
        //设置Adapter
        rcvDiscovery.setAdapter(discoveryAdapter);
        //设置增加或删除条目的动画
        rcvDiscovery.setItemAnimator(new DefaultItemAnimator());
    }

    private void initEvent() {
        discoveryAdapter.setOnItemClickListener(new DiscoveryAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(DiscoveryItem item) {
                toast(item.getName());
            }
        });
    }

    private void initData() {

    }

}
