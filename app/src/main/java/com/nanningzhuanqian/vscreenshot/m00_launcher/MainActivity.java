package com.nanningzhuanqian.vscreenshot.m00_launcher;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DownloadManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Color;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.Nullable;
import android.support.v4.content.FileProvider;
import android.support.v4.view.ViewPager;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.nanningzhuanqian.vscreenshot.R;
import com.nanningzhuanqian.vscreenshot.adapter.MainFragmentAdapter;
import com.nanningzhuanqian.vscreenshot.adapter.MainTabAdpter;
import com.nanningzhuanqian.vscreenshot.base.BaseActivity;
import com.nanningzhuanqian.vscreenshot.base.Util;
import com.nanningzhuanqian.vscreenshot.base.util.SPUtils;
import com.nanningzhuanqian.vscreenshot.common.Constant;
import com.nanningzhuanqian.vscreenshot.m01_wechat_main.ContactListFragment;
import com.nanningzhuanqian.vscreenshot.m01_wechat_main.ConversationListFragment;
import com.nanningzhuanqian.vscreenshot.m01_wechat_main.DiscoverFragment;
import com.nanningzhuanqian.vscreenshot.m01_wechat_main.ProfileFragment;

import java.io.File;
import java.lang.reflect.Field;

/**
 * 主页 这个界面管理所有功能业务的入口 不单只微信
 */
public class MainActivity extends BaseActivity {
    private ViewPager viewPager;
    private RadioGroup rdoTab;
    private TextView tvTitle;
    private MainTabAdpter adapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
//        setStatusBarTransparent();
        super.onCreate(savedInstanceState);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_launcher;
    }

    protected void initData() {
        checkAppUpgrade();
    }

    private void checkAppUpgrade(){
        int latestVersionCode = (int) SPUtils.get(getThis(), Constant.KEY_VERSION_CODE, 0);
        int versionCode = Util.getLocalVersion(getThis());
        String message = (String)SPUtils.get(getThis(),Constant.KEY_UPGRADE_MESSAGE,"");
        String url = (String)SPUtils.get(getThis(),Constant.KEY_APK_DOWNLOAD_URL,"");
        if(TextUtils.isEmpty(url)){
            return;
        }
        if(TextUtils.isEmpty(message)){
            message = "APP有新版本可供下载，下载新版本可获得更优的体验和使用更全面的功能！";
        }
        if (latestVersionCode > versionCode) {
            boolean isForce = Util.isForceUpdate(getThis());
            showAPKUpdateDialog(message,isForce,url);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
//        setStatusBarTransparent();
    }

    protected void initEvent() {
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                switch (position) {
                    case 0:
                        rdoTab.check(R.id.rdoMain);
                        tvTitle.setText("主页");
                        break;
                    case 1:
                        rdoTab.check(R.id.rdoUser);
                        tvTitle.setText("用户");
                        break;
                    case 2:
                        rdoTab.check(R.id.rdoSetting);
                        tvTitle.setText("设置");
                        break;
                    default:
                        break;
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        rdoTab.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.rdoMain: //微信
                        viewPager.setCurrentItem(0);
                        break;
                    case R.id.rdoUser: //通讯录
                        viewPager.setCurrentItem(1);
                        break;
                    case R.id.rdoSetting: //发现
                        viewPager.setCurrentItem(2);
                        break;
                    default:
                        break;
                }
            }
        });
    }

    protected void initView() {
        tvTitle = (TextView) findViewById(R.id.tvTitle);
        tvTitle.setText("主页");
        viewPager = (ViewPager) findViewById(R.id.viewpager);
        rdoTab = (RadioGroup) findViewById(R.id.rdoTab);
        rdoTab.check(R.id.rdoMain);
        adapter = new MainTabAdpter(getSupportFragmentManager());
        viewPager.setAdapter(adapter);
        adapter.clear();
        viewPager.setOffscreenPageLimit(3);
        adapter.addFragment(new MainFragment(), "首页");
        adapter.addFragment(new UserFragment(), "用户");
        adapter.addFragment(new SettingFragment(), "设置");
        adapter.notifyDataSetChanged();
    }

    private void showAPKUpdateDialog(String msg, final boolean isForce, final String url) {
        // TODO: 2018/9/16 升级
        Log.i(TAG, "是否需要升级" + isForce);
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(false);
        builder.setTitle("");
        builder.setMessage(msg);
        builder.setPositiveButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // 点击确认按钮，关闭对话框
                dialog.dismiss();
                if(isForce){
                    finish();
                }
            }
        });
        builder.setPositiveButton("立即升级", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // 点击确认按钮，关闭对话框
                dialog.dismiss();
                boolean downloadByWeb = true;
                install(url,downloadByWeb);
            }
        });
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }

    private void install(String apkUrl,boolean downloadByWebview){
        if(downloadByWebview){
            downloadApkByWeb(apkUrl);
        }else{
            downloadApkByApp(apkUrl);
        }
    }

    private void downloadApkByWeb(String apkUrl){
        Uri uri = Uri.parse(apkUrl);
        //String android.intent.action.VIEW 比较通用，会根据用户的数据类型打开相应的Activity。如:浏览器,电话,播放器,地图
        Intent intent = new Intent(Intent.ACTION_VIEW, uri);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }

    private String downloadUpdateApkFilePath;
    private void downloadApkByApp(String apkUrl){
        try {
            Uri uri = Uri.parse(apkUrl);
            DownloadManager downloadManager = (DownloadManager) getSystemService(Context.DOWNLOAD_SERVICE);
            DownloadManager.Request request = new DownloadManager.Request(uri);
            //在通知栏中显示
            request.setVisibleInDownloadsUi(true);
            request.setTitle("应用更新");
            request.setDescription("本次更新描述");
            //MIME_MapTable是所有文件的后缀名所对应的MIME类型的一个String数组  {".apk",    "application/vnd.android.package-archive"},
            request.setMimeType("application/vnd.android.package-archive");
            // 在通知栏通知下载中和下载完成
            // 下载完成后该Notification才会被显示
            if (Build.VERSION.SDK_INT > Build.VERSION_CODES.HONEYCOMB) {
                // Android 3.0版本 以后才有该方法
                //在下载过程中通知栏会一直显示该下载的Notification，在下载完成后该Notification会继续显示，直到用户点击该Notification或者消除该Notification
                request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);
            }
            String filePath = null;
            if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {//外部存储卡
                filePath = Environment.getExternalStorageDirectory().getAbsolutePath();
            } else {
                Log.i(TAG, "没有SD卡");
                return;
            }
            downloadUpdateApkFilePath = filePath + File.separator + "screenShotMasterapk" + System.currentTimeMillis
                    () + ".apk";
            // 若存在，则删除 (这里具体逻辑具体看,我这里是删除)
            deleteFile(downloadUpdateApkFilePath);
            Uri fileUri = Uri.fromFile(new File(downloadUpdateApkFilePath));
            request.setDestinationUri(fileUri);
            //下载管理Id
            downloadManager.enqueue(request);
            DownloadReceiver mDownloaderReceiver = new DownloadReceiver();
            //注册下载完成广播
            registerReceiver(mDownloaderReceiver, new IntentFilter(DownloadManager.ACTION_DOWNLOAD_COMPLETE));

        } catch (Exception e) {
            e.printStackTrace();
            //注意:如果文件下载失败则 使用浏览器下载
            // downloadByWeb(context, apkUrl);
        }
    }

    /**
     * 下载完成的广播
     */
    public class DownloadReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            if (!TextUtils.isEmpty(downloadUpdateApkFilePath)) {
                installNormal(context, downloadUpdateApkFilePath);
            }
        }
    }

    /**
     * 提示安装
     * @param context 上下文
     * @param apkPath apk下载完成在手机中的路径
     */
    private static void installNormal(Context context, String apkPath) {
        Intent intent = new Intent(Intent.ACTION_VIEW);
        //版本在7.0以上是不能直接通过uri访问的
        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.N) {
            File file = (new File(apkPath));
            // 由于没有在Activity环境下启动Activity,设置下面的标签
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            //参数1:上下文, 参数2:Provider主机地址 和配置文件中保持一致,参数3:共享的文件
            Uri apkUri = FileProvider.getUriForFile(context, "com.xinxianshi.fileprovider", file);
            //添加这一句表示对目标应用临时授权该Uri所代表的文件
            intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
            intent.setDataAndType(apkUri, "application/vnd.android.package-archive");
        } else {
            intent.setDataAndType(Uri.fromFile(new File(apkPath)),
                    "application/vnd.android.package-archive");
        }
        context.startActivity(intent);
    }

}
