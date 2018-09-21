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
import com.nanningzhuanqian.vscreenshot.base.BaseFragment;
import com.nanningzhuanqian.vscreenshot.base.util.PinyinComparator;
import com.nanningzhuanqian.vscreenshot.base.util.PinyinUtils;
import com.nanningzhuanqian.vscreenshot.base.util.SPUtils;
import com.nanningzhuanqian.vscreenshot.common.Constant;
import com.nanningzhuanqian.vscreenshot.item.ContractItem;
import com.nanningzhuanqian.vscreenshot.item.ContractItems;
import com.nanningzhuanqian.vscreenshot.model.ContractLite;
import com.nanningzhuanqian.vscreenshot.model.ConversationLite;
import com.nanningzhuanqian.vscreenshot.model.SortModel;

import org.litepal.LitePal;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by WMY on 2018/9/14.
 */

public class ContactListFragment extends BaseFragment {

    private RecyclerView rcvContract;
    private View rootView;
    private ContractAdapter contractAdapter;
    private PinyinComparator pinyinComparator;

    private List<ContractItem> contractItems = new ArrayList<>();

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_contract_list, container, false);
        initView();
        initEvent();
        initData();
        return rootView;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    private void initEvent() {
        contractAdapter.setOnItemClickListener(new ContractAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(ContractItem item) {
                toast(item.getName());
            }
        });
    }

    private void initView() {
        rcvContract = (RecyclerView) rootView.findViewById(R.id.rcvContract);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        contractAdapter = new ContractAdapter(getActivity());
        //设置布局管理器
        rcvContract.setLayoutManager(layoutManager);
        //设置为垂直布局，这也是默认的
        layoutManager.setOrientation(OrientationHelper.VERTICAL);
        //设置Adapter
        rcvContract.setAdapter(contractAdapter);
        //设置增加或删除条目的动画
        rcvContract.setItemAnimator(new DefaultItemAnimator());
    }

    private void initData() {
        ContractItems.getInstance().clear();
        contractItems.clear();
        pinyinComparator = new PinyinComparator();
//        ContractItems.getInstance().add(new ContractItem(ContractAdapter.ITEM_CONTRACT_TYPE,"A0鹤鸣九州",R.mipmap.app_images_role_10000));
        String mobile = (String) SPUtils.get(getActivity(), Constant.KEY_MOBILE, "");
        List<ContractLite> contractLites = LitePal.findAll(ContractLite.class);
        for(int i = 0;i<contractLites.size();i++){
            ContractItem item = contractLites.get(i).convertToContractItem();
            contractItems.add(item);
        }
        List<ContractItem> items = filledData(contractItems);
        Collections.sort(items, pinyinComparator);
        ContractItems.getInstance().add(items);
        ContractItems.getInstance().addFirst(new ContractItem(ContractAdapter.ITEM_COMMON_TYPE, "公众号", R.mipmap.app_views_pages_wechat_home_images_contacticon4));
        ContractItems.getInstance().addFirst(new ContractItem(ContractAdapter.ITEM_COMMON_TYPE, "标签", R.mipmap.app_views_pages_wechat_home_images_contacticon3));
        ContractItems.getInstance().addFirst(new ContractItem(ContractAdapter.ITEM_COMMON_TYPE, "群聊", R.mipmap.app_views_pages_wechat_home_images_contacticon2));
        ContractItems.getInstance().addFirst(new ContractItem(ContractAdapter.ITEM_COMMON_TYPE, "新的朋友", R.mipmap.app_views_pages_wechat_home_images_contacticon1));
        contractAdapter.notifyDataSetChanged();
    }

    private List<ContractItem> filledData(List<ContractItem> contractItems) {
        List<ContractItem> items = new ArrayList<>();

        for (int i = 0; i < contractItems.size(); i++) {
            ContractItem item = contractItems.get(i);
            item.setName(contractItems.get(i).getName());
            //汉字转换成拼音
            String pinyin = PinyinUtils.getPingYin(contractItems.get(i).getName());
            String sortString = pinyin.substring(0, 1).toUpperCase();

            // 正则表达式，判断首字母是否是英文字母
            if (sortString.matches("[A-Z]")) {
                item.setLetters(sortString.toUpperCase());
            } else {
                item.setLetters("#");
            }

            items.add(item);
        }
        return items;
    }
}
