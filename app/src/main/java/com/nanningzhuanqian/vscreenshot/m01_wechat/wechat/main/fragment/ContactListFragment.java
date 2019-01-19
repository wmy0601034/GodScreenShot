package com.nanningzhuanqian.vscreenshot.m01_wechat.wechat.main.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.nanningzhuanqian.vscreenshot.R;
import com.nanningzhuanqian.vscreenshot.adapter.ContactAdapter;
import com.nanningzhuanqian.vscreenshot.base.BaseFragment;
import com.nanningzhuanqian.vscreenshot.base.bean.Contact;
import com.nanningzhuanqian.vscreenshot.base.bean.Contacts;
import com.nanningzhuanqian.vscreenshot.base.util.DBManager;
import com.nanningzhuanqian.vscreenshot.base.util.PinyinComparator;
import com.nanningzhuanqian.vscreenshot.base.util.PinyinUtils;
import com.nanningzhuanqian.vscreenshot.m01_wechat.wechat.contract.WechatNewFriendActivity;


import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by WMY on 2018/9/14.
 */

public class ContactListFragment extends BaseFragment {

    private RecyclerView rcvContract;
    private View rootView;
    private ContactAdapter contactAdapter;
    private PinyinComparator pinyinComparator;

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
        contactAdapter.setOnItemClickListener(new ContactAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position,Contact item) {
                if(position==0){
                    Intent intent = new Intent(getActivity(),WechatNewFriendActivity.class);
                    startActivity(intent);
                }else {
                    toast(item.getRemarkName());
                }
            }
        });
    }

    private void initView() {
        rcvContract = (RecyclerView) rootView.findViewById(R.id.rcvContract);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        contactAdapter = new ContactAdapter(getActivity());
        //设置布局管理器
        rcvContract.setLayoutManager(layoutManager);
        //设置为垂直布局，这也是默认的
        layoutManager.setOrientation(OrientationHelper.VERTICAL);
        //设置Adapter
        rcvContract.setAdapter(contactAdapter);
        //设置增加或删除条目的动画
        rcvContract.setItemAnimator(new DefaultItemAnimator());
    }

    public void initData() {
        pinyinComparator = new PinyinComparator();
        List<Contact> contacts = DBManager.getContacts(getContext());
        Log.i(TAG,"initData = "+contacts.size());
        Contacts.getInstance().clear();
        contacts= filledData(contacts);
        Collections.sort(contacts, pinyinComparator);

        Contacts.getInstance().add(contacts);
        for(int i = 0;i<Contacts.getInstance().size();i++){
            Log.i(TAG,i+ " "+Contacts.getInstance().get(i).getIconType()+" "+Contacts.getInstance().get(i)
                    .getIconRes()+" "+Contacts.getInstance().get(i).getIconUrl());
        }
        Contacts.getInstance().initTop();
        contactAdapter.notifyDataSetChanged();
    }

    private List<Contact> filledData(List<Contact> contacts) {
        List<Contact> contactList = new ArrayList<>();

        for (int i = 0; i < contacts.size(); i++) {
            Contact contact = contacts.get(i);
            contact.setRemarkName(contacts.get(i).getRemarkName());
            contact.setWechatNickName(contacts.get(i).getWechatNickName());
            //汉字转换成拼音
            String pinyin = PinyinUtils.getPingYin(contacts.get(i).getRemarkName());
            String sortString = pinyin.substring(0, 1).toUpperCase();

            // 正则表达式，判断首字母是否是英文字母
            if (sortString.matches("[A-Z]")) {
                contact.setLetters(sortString.toUpperCase());
            } else {
                contact.setLetters("#");
            }

            contactList.add(contact);
        }
        return contactList;
    }
}
