package com.nanningzhuanqian.vscreenshot.m00_launcher.bank_card;

import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.nanningzhuanqian.vscreenshot.R;
import com.nanningzhuanqian.vscreenshot.adapter.BankCardAdapter;
import com.nanningzhuanqian.vscreenshot.base.BaseActivity;
import com.nanningzhuanqian.vscreenshot.base.util.SPUtils;
import com.nanningzhuanqian.vscreenshot.common.Constant;
import com.nanningzhuanqian.vscreenshot.item.BankCardItem;
import com.nanningzhuanqian.vscreenshot.item.BankCardItems;

/**
 * 银行卡管理页面
 */
public class BankCardManagerActivity extends BaseActivity {

    private ListView lvBankCard;
    private BankCardAdapter bankCardAdapter;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_bank_card_manager;
    }

    @Override
    protected void initView() {
        setCommonRightContent("添加", new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getThis(),EditBankCardActivity.class);
                startActivity(intent);
            }
        });
        lvBankCard = findViewById(R.id.lvBankCard);
        bankCardAdapter = new BankCardAdapter(getThis());
        lvBankCard.setAdapter(bankCardAdapter);
    }

    @Override
    protected void initEvent() {
        lvBankCard.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                BankCardItem bankCardItem = BankCardItems.getInstance().get(position);
                String bank = bankCardItem.getName();
                SPUtils.put(getThis(), Constant.KEY_BANK,bank);
                finish();
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        bankCardAdapter.notifyDataSetChanged();
    }

    @Override
    protected void initData() {
        BankCardItem bankCardItem1 = new BankCardItem("中国农业银行",R.mipmap.app_images_bank_abc);
        BankCardItem bankCardItem2 = new BankCardItem("中国银行",R.mipmap.app_images_bank_boc);
        BankCardItem bankCardItem3 = new BankCardItem("中国建设银行",R.mipmap.app_images_bank_ccb);
        BankCardItem bankCardItem4 = new BankCardItem("招商银行",R.mipmap.app_images_bank_cmb);
        BankCardItem bankCardItem5 = new BankCardItem("民生银行",R.mipmap.app_images_bank_cmbc);
        BankCardItem bankCardItem6 = new BankCardItem("交通银行",R.mipmap.app_images_bank_comm);
        BankCardItem bankCardItem7 = new BankCardItem("华夏银行",R.mipmap.app_images_bank_hxbank);
        BankCardItem bankCardItem8 = new BankCardItem("中国工商银行",R.mipmap.app_images_bank_icbc);
        BankCardItem bankCardItem9 = new BankCardItem("邮政储蓄",R.mipmap.app_images_bank_psbc);
        BankCardItem bankCardItem10 = new BankCardItem("浦发银行",R.mipmap.app_images_bank_spdb);
        BankCardItem bankCardItem11 = new BankCardItem("农村信用社",R.mipmap.app_images_bank_ydrcb);
        BankCardItem bankCardItem12 = new BankCardItem("兴业银行",R.mipmap.app_images_bank_cib);
        BankCardItem bankCardItem13 = new BankCardItem("光大银行",R.mipmap.app_images_bank_ceb);
        BankCardItem bankCardItem14 = new BankCardItem("广发银行",R.mipmap.app_images_bank_gdb);
        BankCardItem bankCardItem15 = new BankCardItem("平安银行",R.mipmap.app_images_bank_spabank);
        BankCardItem bankCardItem16 = new BankCardItem("深圳农村商业银行",R.mipmap.app_images_bank_szrcb);
        BankCardItem bankCardItem17 = new BankCardItem("中信银行",R.mipmap.app_images_bank_citic);
        BankCardItems.getInstance().add(bankCardItem1);
        BankCardItems.getInstance().add(bankCardItem2);
        BankCardItems.getInstance().add(bankCardItem3);
        BankCardItems.getInstance().add(bankCardItem4);
        BankCardItems.getInstance().add(bankCardItem5);
        BankCardItems.getInstance().add(bankCardItem6);
        BankCardItems.getInstance().add(bankCardItem7);
        BankCardItems.getInstance().add(bankCardItem8);
        BankCardItems.getInstance().add(bankCardItem9);
        BankCardItems.getInstance().add(bankCardItem10);
        BankCardItems.getInstance().add(bankCardItem11);
        BankCardItems.getInstance().add(bankCardItem12);
        BankCardItems.getInstance().add(bankCardItem13);
        BankCardItems.getInstance().add(bankCardItem14);
        BankCardItems.getInstance().add(bankCardItem15);
        BankCardItems.getInstance().add(bankCardItem16);
        BankCardItems.getInstance().add(bankCardItem17);
        bankCardAdapter.notifyDataSetChanged();
    }
}
