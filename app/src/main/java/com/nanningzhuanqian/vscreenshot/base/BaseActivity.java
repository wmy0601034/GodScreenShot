package com.nanningzhuanqian.vscreenshot.base;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.gyf.barlibrary.ImmersionBar;
import com.lzy.imagepicker.ImagePicker;
import com.lzy.imagepicker.view.CropImageView;
import com.nanningzhuanqian.vscreenshot.R;
import com.nanningzhuanqian.vscreenshot.base.util.PermissionUtils;
import com.nanningzhuanqian.vscreenshot.base.util.SPUtils;
import com.nanningzhuanqian.vscreenshot.common.Constant;
import com.nanningzhuanqian.vscreenshot.widget.ArrowView;
import com.nanningzhuanqian.vscreenshot.widget.LoadingDialog;
import com.nanningzhuanqian.vscreenshot.widget.PicassoImageLoader;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.lang.reflect.Field;

public abstract class BaseActivity extends AppCompatActivity {
    public TextView tvBack;
    public TextView tvTitle;
    public TextView tvAdd;
    public int statusBarHeight = 0;
    public View statusBar;

    public ArrowView arrowBack;
    public ArrowView arrow;
    public ImageButton imgBack;
    public TextView tvToolbarTitle;
    public ImageButton btnRight;
    public Button btnOption;
    public TextView tvRight;
    public String mobile;
    public String wechatUserName;
    public String wechatUserAvatarType;
    public String wechatUserAvatarUri;
    public int wechatUserAvatarRes;

    private InputMethodManager imm;
    protected ImmersionBar mImmersionBar;
    public boolean isFirstShow = true;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        Log.i(TAG, "onCreate");
        super.onCreate(savedInstanceState);
        setContentView(getLayoutId());
        //初始化沉浸式
        if (isImmersionBarEnabled()) {
            initImmersionBar();
        }
        initView();
        initEvent();
        initData();
        initUserMobile();
        initWechatUserName();
        initWechatUserAvatarType();
//        initWechatUserAvatarUri();
        initWechatUserImaRes();
        initStatusBar();
        EventBus.getDefault().register(this);

    }

    //定义处理接收的方法
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void handleEvent(EventBus eventBus) {
    }


    /**
     * 是否可以使用沉浸式
     * Is immersion bar enabled boolean.
     *
     * @return the boolean
     */
    protected boolean isImmersionBarEnabled() {
        return true;
    }

    protected boolean needNavigationBartransparent() {
        return false;
    }

    protected void initImmersionBar() {
        //在BaseActivity里初始化
        mImmersionBar = ImmersionBar.with(this);
//        if(needNavigationBartransparent()){
////            mImmersionBar.transparentNavigationBar();
////            Log.i(TAG,"需要透明导航栏");
//        }else{
//            Log.i(TAG,"不需要透明导航栏");
//        }
//        ImmersionBar.with(this)
//                .transparentStatusBar()  //透明状态栏，不写默认透明色
//                .transparentNavigationBar()  //透明导航栏，不写默认黑色(设置此方法，fullScreen()方法自动为true)
//                .transparentBar()             //透明状态栏和导航栏，不写默认状态栏为透明色，导航栏为黑色（设置此方法，fullScreen()方法自动为true）
//                .statusBarColor(R.color.colorPrimary)     //状态栏颜色，不写默认透明色
//                .navigationBarColor(R.color.colorPrimary) //导航栏颜色，不写默认黑色
//                .barColor(R.color.colorPrimary)  //同时自定义状态栏和导航栏颜色，不写默认状态栏为透明色，导航栏为黑色
//                .statusBarAlpha(0.3f)  //状态栏透明度，不写默认0.0f
//                .navigationBarAlpha(0.4f)  //导航栏透明度，不写默认0.0F
//                .barAlpha(0.3f)  //状态栏和导航栏透明度，不写默认0.0f
//                .statusBarDarkFont(true)   //状态栏字体是深色，不写默认为亮色
//                .flymeOSStatusBarFontColor(R.color.btn3)  //修改flyme OS状态栏字体颜色
//                .fullScreen(true)      //有导航栏的情况下，activity全屏显示，也就是activity最下面被导航栏覆盖，不写默认非全屏
//                .hideBar(BarHide.FLAG_HIDE_BAR)  //隐藏状态栏或导航栏或两者，不写默认不隐藏
//                .addViewSupportTransformColor(toolbar)  //设置支持view变色，可以添加多个view，不指定颜色，默认和状态栏同色，还有两个重载方法
//                .titleBar(view)    //解决状态栏和布局重叠问题，任选其一
//                .statusBarView(view)  //解决状态栏和布局重叠问题，任选其一
//                .fitsSystemWindows(true)    //解决状态栏和布局重叠问题，任选其一，默认为false，当为true时一定要指定statusBarColor()，不然状态栏为透明色
//                .supportActionBar(true) //支持ActionBar使用
//                .statusBarColorTransform(R.color.orange)  //状态栏变色后的颜色
//                .navigationBarColorTransform(R.color.orange) //导航栏变色后的颜色
//                .barColorTransform(R.color.orange)  //状态栏和导航栏变色后的颜色
//                .removeSupportView(toolbar)  //移除指定view支持
//                .removeSupportAllView() //移除全部view支持
//                .addTag("tag")  //给以上设置的参数打标记
//                .getTag("tag")  //根据tag获得沉浸式参数
//                .reset()  //重置所以沉浸式参数
//                .keyboardEnable(true)  //解决软键盘与底部输入框冲突问题，默认为false
//                .setOnKeyboardListener(new OnKeyboardListener() {    //软键盘监听回调
//                    @Override
//                    public void onKeyboardChange(boolean isPopup, int keyboardHeight) {
//                        LogUtils.e(isPopup);  //isPopup为true，软键盘弹出，为false，软键盘关闭
//                    }
//                })
//                .keyboardMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE)  //单独指定软键盘模式
//                .init();  //必须调用方可沉浸式

        mImmersionBar.init();
    }

    public void hideSoftKeyBoard() {
        View localView = getCurrentFocus();
        if (this.imm == null) {
            this.imm = ((InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE));
        }
        if ((localView != null) && (this.imm != null)) {
            this.imm.hideSoftInputFromWindow(localView.getWindowToken(), 2);
        }
    }

    public void showSoftKeyBoard(EditText editText) {
        InputMethodManager inputManager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        inputManager.showSoftInput(editText, 0);
    }

    public void getStatusBarHeight() {
        int resourceId = getApplicationContext().getResources().getIdentifier("status_bar_height", "dimen", "android");
        if (resourceId > 0) {
            statusBarHeight = getApplicationContext().getResources().getDimensionPixelSize(resourceId);
        } else {

        }
        // 获取屏幕密度（方法2）

        DisplayMetrics dm = getResources().getDisplayMetrics();
        float density = dm.density;        // 屏幕密度（像素比例：0.75/1.0/1.5/2.0）

        int densityDPI = dm.densityDpi;        // 屏幕密度（每寸像素：120/160/240/320）
        Log.i(TAG, "statusBarHeight = " + statusBarHeight + " density = " + density + " densityDPI = " + densityDPI);
    }

    public void initStatusBar() {
        statusBar = findViewById(R.id.statusBar);
        if (statusBar != null) {
            getStatusBarHeight();
            Log.i(TAG, "initStatusBar " + statusBarHeight);
            LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) statusBar.getLayoutParams();
            params.height = statusBarHeight;
            statusBar.setLayoutParams(params);
            statusBar.setVisibility(View.VISIBLE);
        }
    }

    public void initCommonTopBar() {
        tvBack = (TextView) findViewById(R.id.tvBack);
        tvBack.setVisibility(View.VISIBLE);
        tvTitle = (TextView) findViewById(R.id.tvTitle);
        tvAdd = (TextView) findViewById(R.id.tvSubmit);
        tvBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    public void initWechatTopBar() {
        arrowBack = (ArrowView) findViewById(R.id.arrowBack);
        arrow = (ArrowView) findViewById(R.id.arrow);
        imgBack = (ImageButton) findViewById(R.id.imgBack);
        tvToolbarTitle = (TextView) findViewById(R.id.tvToolbarTitle);
        btnRight = (ImageButton) findViewById(R.id.btnRight);
        btnOption = (Button) findViewById(R.id.btnOption);
        if (arrowBack != null) {
            arrowBack.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    finish();
                }
            });
        }
        if (arrow != null) {
            arrow.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    finish();
                }
            });
        }
        if (imgBack != null) {
            imgBack.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    finish();
                }
            });
        }
    }

    public void setWechatTitle(String title) {
        if (tvToolbarTitle != null) {
            tvToolbarTitle.setText(title);
        }
    }

    public void setCommonRightContent(String content, View.OnClickListener listener) {
        if (tvAdd != null) {
            tvAdd.setText(content);
            tvAdd.setVisibility(View.VISIBLE);
            tvAdd.setOnClickListener(listener);
        }
    }

    public void setCommonTitle(String title) {
        if (tvTitle != null) {
            tvTitle.setText(title);
        }
    }

    protected abstract int getLayoutId();

    protected abstract void initView();

    protected abstract void initEvent();

    protected abstract void initData();

    @Override
    protected void onResume() {
        super.onResume();
    }

    public String TAG = getClass().getSimpleName();

    public void toast(String msg) {
        Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_SHORT).show();
    }

    /**
     * 加载中的对话框
     */
    private LoadingDialog mLoadingDialog;

    /**
     * 有网络访问或者进行耗时操作时，显示处理框
     */
    public void showLoadingDialog(final boolean isCancelable) {

        try {
            if (null == mLoadingDialog) {
                // 初始化加载中的dialog
                mLoadingDialog = new LoadingDialog(getThis(), R.style.LoadingDialogStyle);
            }
        } catch (Throwable e) {
            if (e instanceof OutOfMemoryError) {
                System.gc();
            }
            return;
        }
        mLoadingDialog.setCancelable(isCancelable);
        mLoadingDialog.show();
    }

    /**
     * 有网络访问或者进行耗时操作时，显示处理框
     * 默认不可点击取消键，将处理框隐藏
     */
    public void showLoadingDialog() {
        showLoadingDialog(false);
    }

    /**
     * 取消显示处理框(即隐藏)
     */
    public void hideLoadingDialog() {

        if (mLoadingDialog == null) return;
        // 一直执行隐藏Loading框，直到成功隐藏
        final Handler handler = new Handler();
        handler.post(new Runnable() {

            @Override
            public void run() {
                if (null == mLoadingDialog) {
                    return;
                }
                if (mLoadingDialog.isShowing() == false) {
                    return;
                } else {
                    mLoadingDialog.dismiss();
                    handler.postDelayed(this, 10);
                }
            }
        });
//		// 这句话不能加，否则会关不掉
//		handler.removeCallbacksAndMessages(null);
    }


    /**
     * 获取自身
     *
     * @return
     */
    protected BaseActivity getThis() {
        return this;
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.i(TAG, "onDestroy");
        EventBus.getDefault().unregister(this);
    }

    /**
     * 隐藏软键盘(只适用于Activity，不适用于Fragment)
     */
    public static void hideSoftKeyboard(Activity activity) {
        View view = activity.getCurrentFocus();
        if (view != null) {
            InputMethodManager inputMethodManager = (InputMethodManager) activity.getSystemService(Activity.INPUT_METHOD_SERVICE);
            inputMethodManager.hideSoftInputFromWindow(view.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
        }
    }

    public void initUserMobile(){
        mobile = (String) SPUtils.get(getThis(), Constant.KEY_MOBILE, "");
    }

    public void initWechatUserName(){
        wechatUserName = (String) SPUtils.get(getThis(), Constant.KEY_PROFILE_NAME, "");
    }

    public void initWechatUserAvatarType(){
        wechatUserAvatarType = (String)SPUtils.get(getThis(),Constant.KEY_PROFILE_AVATAR_TYPE,"");
    }

    public void initWechatUserAvatarUri(){
        wechatUserAvatarUri = (String)SPUtils.get(getThis(),Constant.KEY_PROFILE_AVATAR_URI,"");
    }

    public void initWechatUserImaRes(){
        wechatUserAvatarRes = (Integer)SPUtils.get(getThis(),Constant.KEY_PROFILE_AVATAR,0);
    }

    public boolean checkingPermission = false;
    public void permissionDeniedDialog(int titleRes,int msgRes) {
        new AlertDialog.Builder(getThis())
                .setTitle(titleRes)
                .setMessage(msgRes)
                .setCancelable(false)
                .setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                        finish();
                    }
                })
                .setPositiveButton(R.string.setting_now, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                        Util.showAppPermissions(getThis());
                        checkingPermission = false;
                    }
                })
                .create().show();
    }

    protected void initImagePicker() {
        ImagePicker imagePicker = ImagePicker.getInstance();
        imagePicker.setImageLoader(new PicassoImageLoader());
        imagePicker.setShowCamera(true);  //显示拍照按钮
        imagePicker.setCrop(true);        //允许裁剪（单选才有效）
        imagePicker.setMultiMode(false);
        imagePicker.setSaveRectangle(true); //是否按矩形区域保存
        imagePicker.setSelectLimit(1);    //选中数量限制
        imagePicker.setStyle(CropImageView.Style.RECTANGLE);  //裁剪框的形状
        imagePicker.setFocusWidth(1000);   //裁剪框的宽度。单位像素（圆形自动取宽高最小值）
        imagePicker.setFocusHeight(1000);  //裁剪框的高度。单位像素（圆形自动取宽高最小值）
        imagePicker.setOutPutX(400);//保存文件的宽度。单位像素
        imagePicker.setOutPutY(400);//保存文件的高度。单位像素
    }


}
