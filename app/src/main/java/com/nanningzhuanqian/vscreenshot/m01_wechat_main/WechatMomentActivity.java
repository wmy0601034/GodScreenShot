package com.nanningzhuanqian.vscreenshot.m01_wechat_main;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;

import com.nanningzhuanqian.vscreenshot.MainActivity;
import com.nanningzhuanqian.vscreenshot.R;
import com.nanningzhuanqian.vscreenshot.base.BaseActivity;
import com.nanningzhuanqian.vscreenshot.item.ConversationItems;
import com.nanningzhuanqian.vscreenshot.m01_wechat_main.moment.AddImageMomentActivity;
import com.nanningzhuanqian.vscreenshot.m01_wechat_main.moment.AddTextMomentActivity;
import com.nanningzhuanqian.vscreenshot.m01_wechat_main.moment.AddVideoMomentActivity;
import com.nanningzhuanqian.vscreenshot.m02_add_conversation.AddCustomConversationActivity;
import com.nanningzhuanqian.vscreenshot.model.ConversationLite;
import com.nanningzhuanqian.vscreenshot.widget.NewActionSheetDialog;

import org.litepal.LitePal;

public class WechatMomentActivity extends BaseActivity implements View.OnClickListener {
    private LinearLayout llRight;
    private RecyclerView rcvMoment;
    @Override
    protected int getLayoutId() {
        return R.layout.activity_wechat_moment;
    }

    @Override
    protected void initView() {
        initWechatTopBar();
        llRight = (LinearLayout)findViewById(R.id.llRight);
        rcvMoment = (RecyclerView)findViewById(R.id.rcvMoment);
    }

    @Override
    protected void initEvent() {
        llRight.setOnClickListener(this);
    }

    @Override
    protected void initData() {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.llRight:
                showMomentOptionSheetDialog();
                break;
        }
    }

    private void showMomentOptionSheetDialog(){
        NewActionSheetDialog.Builder builder = new NewActionSheetDialog.Builder(WechatMomentActivity.this);

        builder.setCancelable(false);
        builder.setCancelButtonVisiable(true);
        builder.setCanceledOnTouchOutside(true);
        builder.setTitle("发朋友圈");
        builder.addSheetItem("文字", NewActionSheetDialog
                .SheetItemColor.Blue, new NewActionSheetDialog.Builder
                .OnSheetItemClickListener() {
            @Override
            public void onClick(int which) {
                //文字
                Intent intent = new Intent(WechatMomentActivity.this,AddTextMomentActivity.class);
                startActivity(intent);
            }
        });
        builder.addSheetItem("图片", NewActionSheetDialog
                .SheetItemColor.Blue, new NewActionSheetDialog.Builder
                .OnSheetItemClickListener() {
            @Override
            public void onClick(int which) {
                //图片
                Intent intent = new Intent(WechatMomentActivity.this,AddImageMomentActivity.class);
                startActivity(intent);
            }
        });
        builder.addSheetItem("视频", NewActionSheetDialog
                .SheetItemColor.Blue, new NewActionSheetDialog.Builder
                .OnSheetItemClickListener() {
            @Override
            public void onClick(int which) {
                //视频
                Intent intent = new Intent(WechatMomentActivity.this,AddVideoMomentActivity.class);
                startActivity(intent);
            }
        });
        builder.addSheetItem("链接", NewActionSheetDialog
                .SheetItemColor.Blue, new NewActionSheetDialog.Builder
                .OnSheetItemClickListener() {
            @Override
            public void onClick(int which) {
                //链接
                Intent intent = new Intent(WechatMomentActivity.this,AddVideoMomentActivity.class);
                startActivity(intent);
            }
        });
        builder.addSheetItem("清空所有", NewActionSheetDialog
                .SheetItemColor.Blue, new NewActionSheetDialog.Builder
                .OnSheetItemClickListener() {
            @Override
            public void onClick(int which) {
                //清空
                showClearConfirmDialog();
            }
        });

        Dialog dialog = builder.create();
        dialog.show();
    }

    private void showClearConfirmDialog(){
        AlertDialog.Builder builder = new AlertDialog.Builder(getThis()).setTitle("提示").setMessage("是否要清空所有朋友圈？")
                .setNegativeButton("取消",null).setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();

                    }
                });
        AlertDialog dialog = builder.create();
        dialog.show();
    }
}
