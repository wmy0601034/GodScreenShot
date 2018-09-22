package com.nanningzhuanqian.vscreenshot.base;

import android.content.Context;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.gyf.barlibrary.ImmersionBar;
import com.nanningzhuanqian.vscreenshot.R;
import com.nanningzhuanqian.vscreenshot.widget.ArrowView;
import com.nanningzhuanqian.vscreenshot.widget.LoadingDialog;

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
    public ImageButton imgBack;
    public TextView tvToolbarTitle;
    public ImageButton btnRight;
    public Button btnOption;

    private InputMethodManager imm;
    protected ImmersionBar mImmersionBar;
    public boolean isFirstShow = true;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        Log.i(TAG,"onCreate");
        super.onCreate(savedInstanceState);
        setContentView(getLayoutId());
        //初始化沉浸式
        if (isImmersionBarEnabled()) {
            initImmersionBar();
        }
        initView();
        initEvent();
        initData();
        initStatusBar();
        EventBus.getDefault().register(this);

        }

    //定义处理接收的方法
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void handleEvent(EventBus eventBus){
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

    protected void initImmersionBar() {
        //在BaseActivity里初始化
        mImmersionBar = ImmersionBar.with(this);
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

    public void getStatusBarHeight() {
        int resourceId = getApplicationContext().getResources().getIdentifier("status_bar_height","dimen","android");
        if(resourceId>0){
            statusBarHeight = getApplicationContext().getResources().getDimensionPixelSize(resourceId);
        }else{

        }
        // 获取屏幕密度（方法2）

        DisplayMetrics dm = getResources().getDisplayMetrics();
        float density  = dm.density;		// 屏幕密度（像素比例：0.75/1.0/1.5/2.0）

        int densityDPI = dm.densityDpi;		// 屏幕密度（每寸像素：120/160/240/320）
        Log.i(TAG,"statusBarHeight = "+statusBarHeight+ " density = "+density+" densityDPI = "+densityDPI);
    }

    public void initStatusBar(){
        statusBar = findViewById(R.id.statusBar);
        if(statusBar!=null){
            getStatusBarHeight();
            Log.i(TAG,"initStatusBar "+statusBarHeight);
            LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) statusBar.getLayoutParams();
            params.height = statusBarHeight;
            statusBar.setLayoutParams(params);
            statusBar.setVisibility(View.VISIBLE);
        }
    }

    public void initCommonTopBar(){
        tvBack = (TextView)findViewById(R.id.tvBack);
        tvBack.setVisibility(View.VISIBLE);
        tvTitle = (TextView)findViewById(R.id.tvTitle);
        tvAdd = (TextView)findViewById(R.id.tvSubmit);
        tvBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    public void initWechatTopBar(){
        arrowBack = (ArrowView)findViewById(R.id.arrowBack);
        imgBack = (ImageButton)findViewById(R.id.imgBack);
        tvToolbarTitle = (TextView)findViewById(R.id.tvToolbarTitle);
        btnRight = (ImageButton)findViewById(R.id.btnRight);
        btnOption = (Button)findViewById(R.id.btnOption);
        if(arrowBack!=null){
            arrowBack.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    finish();
                }
            });
        }
        if(imgBack!=null){
            imgBack.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    finish();
                }
            });
        }
    }

    public void setWechatTitle(String title){
        if(tvToolbarTitle!=null){
            tvToolbarTitle.setText(title);
        }
    }

    public void setCommonRightContent(String content,View.OnClickListener listener){
        if(tvAdd!=null){
            tvAdd.setText(content);
            tvAdd.setVisibility(View.VISIBLE);
            tvAdd.setOnClickListener(listener);
        }
    }

    public void setCommonTitle(String title){
        if(tvTitle!=null){
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

    public void toast(String msg){
        Toast.makeText(getApplicationContext(),msg,Toast.LENGTH_SHORT).show();
    }

    /** 加载中的对话框 */
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
        }catch (Throwable e){
            if(e instanceof  OutOfMemoryError){
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
    public void showLoadingDialog(){
        showLoadingDialog(false);
    }

    /**
     * 取消显示处理框(即隐藏)
     */
    public void hideLoadingDialog(){

        if(mLoadingDialog == null)return;
        // 一直执行隐藏Loading框，直到成功隐藏
        final Handler handler = new Handler();
        handler.post(new Runnable() {

            @Override
            public void run() {
                if(null == mLoadingDialog){
                    return;
                }
                if(mLoadingDialog.isShowing() == false){
                    return;
                }else{
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
     * @return
     */
    protected BaseActivity getThis(){
        return this;
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.i(TAG,"onDestroy");
        EventBus.getDefault().unregister(this);
    }
}
