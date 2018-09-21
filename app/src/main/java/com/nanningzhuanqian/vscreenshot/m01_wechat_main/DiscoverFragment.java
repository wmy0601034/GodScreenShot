package com.nanningzhuanqian.vscreenshot.m01_wechat_main;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.nanningzhuanqian.vscreenshot.R;
import com.nanningzhuanqian.vscreenshot.adapter.ContractAdapter;
import com.nanningzhuanqian.vscreenshot.adapter.DiscoveryAdapter;
import com.nanningzhuanqian.vscreenshot.base.BaseFragment;
import com.nanningzhuanqian.vscreenshot.base.util.PinyinUtils;
import com.nanningzhuanqian.vscreenshot.base.util.TypeComparator;
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
        discoveryItems.add(new DiscoveryItem(DiscoveryAdapter.ITEM_1,"朋友圈",R.mipmap.app_views_pages_wechat_home_images_discovericon1));
        discoveryItems.add(new DiscoveryItem(DiscoveryAdapter.ITEM_2,"扫一扫",R.mipmap.app_views_pages_wechat_home_images_discovericon2));
        discoveryItems.add(new DiscoveryItem(DiscoveryAdapter.ITEM_2,"摇一摇",R.mipmap.app_views_pages_wechat_home_images_discovericon3));
        discoveryItems.add(new DiscoveryItem(DiscoveryAdapter.ITEM_3,"看一看",R.mipmap.app_views_pages_wechat_home_images_discovericon4));
        discoveryItems.add(new DiscoveryItem(DiscoveryAdapter.ITEM_3,"搜一搜",R.mipmap.app_views_pages_wechat_home_images_discovericon5));
        discoveryItems.add(new DiscoveryItem(DiscoveryAdapter.ITEM_4,"附近的人",R.mipmap.app_views_pages_wechat_home_images_discovericon6));
        discoveryItems.add(new DiscoveryItem(DiscoveryAdapter.ITEM_5,"购物",R.mipmap.app_views_pages_wechat_home_images_discovericon8));
        discoveryItems.add(new DiscoveryItem(DiscoveryAdapter.ITEM_5,"游戏",R.mipmap.app_views_pages_wechat_home_images_discovericon9));
        discoveryItems.add(new DiscoveryItem(DiscoveryAdapter.ITEM_6,"小程序",R.mipmap.app_views_pages_wechat_home_images_discovericon10));
//        List<DiscoveryItem> items = filledData(discoveryItems);
        DiscoveryItems.getInstance().add(discoveryItems);
        discoveryAdapter.notifyDataSetChanged();
    }

//    private List<DiscoveryItem> filledData(List<DiscoveryItem> discoveryItems) {
//        List<DiscoveryItem> items = new ArrayList<>();
//
//        for (int i = 0; i < discoveryItems.size(); i++) {
//            ContractItem item = discoveryItems.get(i);
//            item.setName(discoveryItems.get(i).getName());
//            //汉字转换成拼音
//            String pinyin = PinyinUtils.getPingYin(contractItems.get(i).getName());
//            String sortString = pinyin.substring(0, 1).toUpperCase();
//
//            // 正则表达式，判断首字母是否是英文字母
//            if (sortString.matches("[A-Z]")) {
//                item.setLetters(sortString.toUpperCase());
//            } else {
//                item.setLetters("#");
//            }
//
//            items.add(item);
//        }
//        return items;
//    }

}
