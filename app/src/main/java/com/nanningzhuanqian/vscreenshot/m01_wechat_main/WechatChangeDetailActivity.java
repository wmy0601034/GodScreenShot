package com.nanningzhuanqian.vscreenshot.m01_wechat_main;

import android.app.Dialog;
import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.gyf.barlibrary.ImmersionBar;
import com.nanningzhuanqian.vscreenshot.R;
import com.nanningzhuanqian.vscreenshot.adapter.ChangeDetailAdapter;
import com.nanningzhuanqian.vscreenshot.base.BaseActivity;
import com.nanningzhuanqian.vscreenshot.base.util.SPUtils;
import com.nanningzhuanqian.vscreenshot.common.Constant;
import com.nanningzhuanqian.vscreenshot.item.ChangeDetailItem;
import com.nanningzhuanqian.vscreenshot.item.ChangeDetailItems;
import com.nanningzhuanqian.vscreenshot.item.ConversationItem;
import com.nanningzhuanqian.vscreenshot.item.ConversationItems;
import com.nanningzhuanqian.vscreenshot.m02_add_conversation.AddCustomConversationActivity;
import com.nanningzhuanqian.vscreenshot.m02_add_conversation.LocalAvatarSelectActivity;
import com.nanningzhuanqian.vscreenshot.m03_add_role.AddCustomRoleActivity;
import com.nanningzhuanqian.vscreenshot.model.ChangeDetail;
import com.nanningzhuanqian.vscreenshot.model.ConversationLite;
import com.nanningzhuanqian.vscreenshot.widget.CustomListview;
import com.nanningzhuanqian.vscreenshot.widget.NewActionSheetDialog;

import org.litepal.LitePal;

import java.util.ArrayList;
import java.util.List;

public class WechatChangeDetailActivity extends BaseActivity {

    private CustomListview lvChangeDetail;
    private ChangeDetailAdapter changeDetailAdapter;
    private TextView tvAdd;

    @Override
    public boolean isInMultiWindowMode() {
        return false;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_wechat_change_detail;
    }

    @Override
    protected void initView() {
        mImmersionBar = ImmersionBar.with(this).statusBarDarkFont(true);  //状态栏字体是深色，不写默认为亮色;
        mImmersionBar.init();
        lvChangeDetail = (CustomListview) findViewById(R.id.lvChangeDetail);
        changeDetailAdapter = new ChangeDetailAdapter(getThis());
        tvAdd = (TextView)findViewById(R.id.tvAdd);
        lvChangeDetail.setAdapter(changeDetailAdapter);
    }

    @Override
    protected void initEvent() {
        lvChangeDetail.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                showChangeDetailOptionSheetDialog(false);
            }
        });
        tvAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showChangeDetailOptionSheetDialog(true);
            }
        });
    }

    private void showChangeDetailOptionSheetDialog(boolean isAdd) {
        NewActionSheetDialog.Builder builder = new NewActionSheetDialog.Builder(WechatChangeDetailActivity.this);
        builder.setCancelable(false);
        builder.setCancelButtonVisiable(true);
        builder.setCanceledOnTouchOutside(true);
        builder.addSheetItem("查看详情", NewActionSheetDialog
                .SheetItemColor.Blue, new NewActionSheetDialog.Builder
                .OnSheetItemClickListener() {
            @Override
            public void onClick(int which) {
                Intent intent = new Intent(getThis(), WechatSingleChangeDetailActivity.class);
                startActivity(intent);
            }
        });
        builder.addSheetItem("自定义零钱明细记录", NewActionSheetDialog
                .SheetItemColor.Blue, new NewActionSheetDialog.Builder
                .OnSheetItemClickListener() {
            @Override
            public void onClick(int which) {
                Intent intent = new Intent(getThis(), AddCustomWechatChangeDetailActivity.class);
                startActivity(intent);
            }
        });
        builder.addSheetItem("随机添加1个零钱明细记录", NewActionSheetDialog
                .SheetItemColor.Blue, new NewActionSheetDialog.Builder
                .OnSheetItemClickListener() {
            @Override
            public void onClick(int which) {
                //随机添加1个对话
                toast("暂未开放");
            }
        });
        builder.addSheetItem("随机添加20个零钱明细记录", NewActionSheetDialog
                .SheetItemColor.Blue, new NewActionSheetDialog.Builder
                .OnSheetItemClickListener() {
            @Override
            public void onClick(int which) {

            }
        });
        if (!isAdd) {
            builder.addSheetItem("编辑", NewActionSheetDialog
                    .SheetItemColor.Blue, new NewActionSheetDialog.Builder
                    .OnSheetItemClickListener() {
                @Override
                public void onClick(int which) {
                    //页面设置
                    toast("暂未开放");
                }
            });
            builder.addSheetItem("删除", NewActionSheetDialog
                    .SheetItemColor.Blue, new NewActionSheetDialog.Builder
                    .OnSheetItemClickListener() {
                @Override
                public void onClick(int which) {
                    //页面设置
                    toast("暂未开放");
                }
            });
            builder.addSheetItem("查看详情", NewActionSheetDialog
                    .SheetItemColor.Blue, new NewActionSheetDialog.Builder
                    .OnSheetItemClickListener() {
                @Override
                public void onClick(int which) {
                    //页面设置
                    toast("暂未开放");
                }
            });
        } else {

        }
        builder.addSheetItem("清空所有记录", NewActionSheetDialog
                .SheetItemColor.Blue, new NewActionSheetDialog.Builder
                .OnSheetItemClickListener() {
            @Override
            public void onClick(int which) {
                //页面设置
                toast("暂未开放");
            }
        });

        Dialog dialog = builder.create();
        dialog.show();
    }

    @Override
    protected void initData() {
        ChangeDetailItems.getInstance().clear();
        List<ChangeDetail> changeDetails = LitePal.order("tranTime desc").find(ChangeDetail.class);
        for(int i = 0;i<changeDetails.size();i++){
            ChangeDetailItem changeDetailItem = changeDetails.get(i).convertToChangeDetailItem();
            ChangeDetailItems.getInstance().add(changeDetailItem);
        }
        changeDetailAdapter.notifyDataSetChanged();
    }

    @Override
    protected void onResume() {
        super.onResume();
        if(isFirstShow){
            isFirstShow = false;
        }else {
            changeDetailAdapter.notifyDataSetChanged();
        }
    }
}
