package com.nanningzhuanqian.vscreenshot.m01_wechat_main;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;

import com.nanningzhuanqian.vscreenshot.WechatMainActivity;
import com.nanningzhuanqian.vscreenshot.R;
import com.nanningzhuanqian.vscreenshot.adapter.WechatMomentAdapter;
import com.nanningzhuanqian.vscreenshot.base.BaseActivity;
import com.nanningzhuanqian.vscreenshot.item.ConversationItems;
import com.nanningzhuanqian.vscreenshot.item.WechatMomentItem;
import com.nanningzhuanqian.vscreenshot.item.WechatMomentItems;
import com.nanningzhuanqian.vscreenshot.m01_wechat_main.moment.AddImageMomentActivity;
import com.nanningzhuanqian.vscreenshot.m01_wechat_main.moment.AddTextMomentActivity;
import com.nanningzhuanqian.vscreenshot.m01_wechat_main.moment.AddVideoMomentActivity;
import com.nanningzhuanqian.vscreenshot.m02_add_conversation.AddCustomConversationActivity;
import com.nanningzhuanqian.vscreenshot.model.ConversationLite;
import com.nanningzhuanqian.vscreenshot.widget.NewActionSheetDialog;

import org.litepal.LitePal;

/**
 * 微信朋友圈界面
 */
public class WechatMomentActivity extends BaseActivity implements View.OnClickListener {
    private LinearLayout llRight;
    private RecyclerView rcvMoment;
    private WechatMomentAdapter wechatMomentAdapter;
    @Override
    protected int getLayoutId() {
        return R.layout.activity_wechat_moment;
    }

    @Override
    protected void initView() {
        initWechatTopBar();
        llRight = (LinearLayout)findViewById(R.id.llRight);
        rcvMoment = (RecyclerView)findViewById(R.id.rcvMoment);
        wechatMomentAdapter = new WechatMomentAdapter(getThis());
        rcvMoment.setLayoutManager(new LinearLayoutManager(getThis()));
        rcvMoment.setAdapter(wechatMomentAdapter);
    }

    @Override
    protected void onResume() {
        super.onResume();

    }

    @Override
    protected void initEvent() {
        llRight.setOnClickListener(this);
    }

    @Override
    protected void initData() {
        WechatMomentItem topItem = new WechatMomentItem(0,"",WechatMomentAdapter.TOP_MOMENT_TYPE);
        WechatMomentItem textItem = new WechatMomentItem(R.mipmap.app_images_role_10001,"吴MoonMoon",WechatMomentAdapter
                .TEXT_MOMENT_TYPE);
        textItem.setTextContent("测试，这是一个文字朋友圈");
        WechatMomentItem videoItem = new WechatMomentItem(R.mipmap.app_images_role_10002,"吴MoonMoon",
                WechatMomentAdapter.VEDIO_MOMENT_TYPE);
        videoItem.setVideoRes(R.mipmap.app_images_role_10052);
        videoItem.setTextContent("测试，这是一个小视频朋友圈");
        WechatMomentItem linkItem = new WechatMomentItem(R.mipmap.app_images_role_10003,"吴MoonMoon",
                WechatMomentAdapter.LINK_MOMENT_TYPE);
        linkItem.setTextContent("测试，这是一个文章朋友圈");
        linkItem.setLinkFrom("今日头条");
        linkItem.setLinkIcon(R.mipmap.app_images_role_10007);
        linkItem.setLinkContent("因为关注底层，我上了中国人民大学的“黑名单”");
        WechatMomentItem picItem = new WechatMomentItem(R.mipmap.app_images_role_10004,"吴MoonMoon",
                WechatMomentAdapter.PICTURE_MOMENT_TYPE);
        picItem.setTextContent("测试，这是一个图片朋友圈");
        WechatMomentItems.getInstance().add(topItem);
        WechatMomentItems.getInstance().add(textItem);
        WechatMomentItems.getInstance().add(videoItem);
        WechatMomentItems.getInstance().add(linkItem);
        WechatMomentItems.getInstance().add(picItem);
        wechatMomentAdapter.notifyDataSetChanged();
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
