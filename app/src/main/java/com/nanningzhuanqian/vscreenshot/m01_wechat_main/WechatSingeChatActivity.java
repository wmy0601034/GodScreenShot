package com.nanningzhuanqian.vscreenshot.m01_wechat_main;

import android.content.Context;
import android.content.Intent;
import android.graphics.Rect;
import android.hardware.input.InputManager;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewTreeObserver;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.nanningzhuanqian.vscreenshot.R;
import com.nanningzhuanqian.vscreenshot.adapter.FaceAdapter;
import com.nanningzhuanqian.vscreenshot.adapter.FaceViewPagerAdapter;
import com.nanningzhuanqian.vscreenshot.adapter.WechatChatAdapter;
import com.nanningzhuanqian.vscreenshot.adapter.WechatChatItemAdapter;
import com.nanningzhuanqian.vscreenshot.adapter.WechatSingleChatMoreAdapter;
import com.nanningzhuanqian.vscreenshot.adapter.WechatSingleChatMoreViewPagerAdapter;
import com.nanningzhuanqian.vscreenshot.base.BaseActivity;
import com.nanningzhuanqian.vscreenshot.base.util.SPUtils;
import com.nanningzhuanqian.vscreenshot.common.Constant;
import com.nanningzhuanqian.vscreenshot.item.WechatChatItem;
import com.nanningzhuanqian.vscreenshot.item.WechatChatItems;
import com.nanningzhuanqian.vscreenshot.item.WechatFaceItem;
import com.nanningzhuanqian.vscreenshot.item.WechatFaceItems;
import com.nanningzhuanqian.vscreenshot.item.WechatSingleChatMoreItems;
import com.nanningzhuanqian.vscreenshot.widget.ArrowView;
import com.nanningzhuanqian.vscreenshot.widget.AutoGridView;
import com.nanningzhuanqian.vscreenshot.widget.CustomListview;

import org.w3c.dom.Text;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import cn.bmob.v3.b.V;

public class WechatSingeChatActivity extends BaseActivity implements View.OnClickListener {
    private LinearLayout parent;
    private RecyclerView rcvConversation;
    private WechatChatAdapter wechatChatAdapter;
    private LinearLayout llLeft;
    private LinearLayout llFaceSmall;
    private LinearLayout llAddSmall;
    private ImageView imgLeft;
    private ImageView imgFace;
    private EditText edContent;
    private TextView tvPressSpeak;
    private boolean isEditTextShowing = false;
    private boolean isFaceLayoutShowing = false;
    private int softInputKeyboradHeight = 0;
    private int bottomStatusBarHeight = 0;
    private LinearLayout llFace;
    private ViewPager viewpager;
    private LinearLayout ll_dot;
    private List<View> pagerList;
    private List<View> morePagerList;
    private LayoutInflater inflater;
    private LinearLayout llMore;
    private LinearLayout ll_dot_more;
    private ViewPager viewpagerMore;
    private View myLayout;

    /**
     * 总的页数
     */
    private int pageCount;
    /**
     * 每一页显示的个数
     */
    private int pageSize = 21;
    /**
     * 当前显示的是第几页
     */
    private int curIndex = 0;

    private int bottom_status_bar_height = 0;
    private int soft_input_keyborad_height = 0;

    /**
     * 总的页数
     */
    private int morePageCount = 2;
    /**
     * 每一页显示的个数
     */
    private int morePageSize = 8;
    /**
     * 当前显示的是第几页
     */
    private int moreCurIndex = 0;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_single_chat;
    }

    @Override
    protected void initView() {
        initWechatTopBar();
        btnRight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showConversationSettingDialog();
            }
        });
        parent = (LinearLayout)findViewById(R.id.parent);
        myLayout = getWindow().getDecorView();
        rcvConversation = (RecyclerView) findViewById(R.id.rcvConversation);
        rcvConversation.setLayoutManager(new LinearLayoutManager(getThis()));
        llLeft = (LinearLayout)findViewById(R.id.llLeft);
        llFaceSmall = (LinearLayout)findViewById(R.id.llFaceSmall);
        llAddSmall = (LinearLayout)findViewById(R.id.llAddSmall);
        imgLeft = (ImageView) findViewById(R.id.imgLeft);
        imgFace = (ImageView) findViewById(R.id.imgFace);
        edContent = (EditText) findViewById(R.id.edContent);
        edContent.setFocusable(true);
        edContent.setFocusableInTouchMode(true);
        tvPressSpeak = (TextView) findViewById(R.id.tvPressSpeak);
        if (isEditTextShowing) {
            edContent.setVisibility(View.VISIBLE);
            tvPressSpeak.setVisibility(View.GONE);
            edContent.requestFocus();
            imgLeft.setImageResource(R.mipmap.app_views_pages_wechat_common_images_chatbottomicon1);
        } else {
            tvPressSpeak.setVisibility(View.VISIBLE);
            edContent.setVisibility(View.GONE);
            tvPressSpeak.requestFocus();
            imgLeft.setImageResource(R.mipmap.app_views_pages_wechat_common_images_chatbottomicon4);
        }
        wechatChatAdapter = new WechatChatAdapter(getThis());
        rcvConversation.setAdapter(wechatChatAdapter);
        llFace = (LinearLayout)findViewById(R.id.llFace);
        viewpager = (ViewPager) findViewById(R.id.viewpager);
        ll_dot = (LinearLayout)findViewById(R.id.ll_dot);
        llMore = (LinearLayout)findViewById(R.id.llMore);
        viewpagerMore = (ViewPager)findViewById(R.id.viewpagerMore);
        ll_dot_more = (LinearLayout)findViewById(R.id.ll_dot_more);
        inflater = LayoutInflater.from(this);
        //总的页数=总数/每页数量，并取整
        initFaceData();
        pageCount = (int) Math.ceil(WechatFaceItems.getInstance().size() * 1.0 / pageSize);
//        morePageCount = (int)Math.ceil(WechatSingleChatMoreItems.getInstance().size()*1.0/morePageSize);
        pagerList = new ArrayList<View>();
        morePagerList = new ArrayList<View>();
        for (int i = 0; i < pageCount; i++) {
            //每个页面都是inflate出一个新实例
            LinearLayout rootView = (LinearLayout) inflater.inflate(R.layout.wechat_face_gridview, viewpager, false);
//            GridView gridView = (GridView) inflater.inflate(R.layout.wechat_face_gridview, viewpager, false);
            GridView gridView = rootView.findViewById(R.id.gvFace);
            gridView.setAdapter(new FaceAdapter(this, i, pageSize));
            //使用GridView作为每个ViewPager的页面，也就是说每个ViewPager的页面都是inflate出一个GridView新实例
            pagerList.add(rootView);

            gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    int pos = position + curIndex * pageSize;
                    Log.i(TAG,"click "+pos);
                }
            });
        }
        for(int i =0;i<morePageCount;i++){
            LinearLayout rootView = (LinearLayout)inflater.inflate(R.layout.wechat_single_chat_more_page1,
                    viewpagerMore,false);
            GridView gridView = rootView.findViewById(R.id.gvMore);
            gridView.setAdapter(new WechatSingleChatMoreAdapter(this, i, pageSize));
            //使用GridView作为每个ViewPager的页面，也就是说每个ViewPager的页面都是inflate出一个GridView新实例
            pagerList.add(rootView);

            gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    int pos = position + curIndex * pageSize;
                    Log.i(TAG,"click "+pos);
                }
            });
        }

        //设置适配器
        parent.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                Rect r = new Rect();                // 使用最外层布局填充，进行测算计算
                parent.getWindowVisibleDisplayFrame(r);
                int screenHeight = myLayout.getRootView().getHeight();
                int heightDiff = screenHeight - (r.bottom - r.top);
                if (heightDiff > 180) {
                    // 如果超过100个像素，它可能是一个键盘。获取状态栏的高度
                    statusBarHeight = 0;
                }
                try {
                    Class<?> c = Class.forName("com.android.internal.R$dimen");
                    Object obj = c.newInstance();
                    Field field = c.getField("status_bar_height");
                    int x = Integer.parseInt(field.get(obj).toString());
                    statusBarHeight = getResources().getDimensionPixelSize(x);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                int layoutHeight = heightDiff - statusBarHeight;
                if(0<layoutHeight&&layoutHeight<=180){
                    bottom_status_bar_height = layoutHeight;
                    Log.i(TAG, "虚拟导航键的高度为 height(单位像素) = " + bottom_status_bar_height);
                    SPUtils.put(getThis(), Constant.KEY_BOTTOM_STATUS_BAR_HEIGHT,bottom_status_bar_height);
                }else if(layoutHeight>180){
                    soft_input_keyborad_height = layoutHeight-bottom_status_bar_height;
                    int height = SPUtils.getSoftInputKeyboardHeight(getApplicationContext());
                    if(height!=softInputKeyboradHeight) {
                        Log.i(TAG, "键盘的高度为 height(单位像素) = " + soft_input_keyborad_height);
                        SPUtils.put(getThis(), Constant.KEY_SOFT_INPUT_KEYBORAD_HEIGHT, soft_input_keyborad_height);
                        setFaceHeight();
                    }
                }
            }
        });
        viewpager.setAdapter(new FaceViewPagerAdapter(pagerList));
        viewpagerMore.setAdapter(new WechatSingleChatMoreViewPagerAdapter());
        //设置圆点
        setOvalLayout();
        setFaceHeight();
    }

    private void setFaceHeight(){
        LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) llFace.getLayoutParams();
        int height = SPUtils.getSoftInputKeyboardHeight(getApplicationContext());
        params.height = height;
        Log.i(TAG,"height = "+height);
        llFace.setLayoutParams(params);
    }

    @Override
    protected void initEvent() {
        llLeft.setOnClickListener(this);
        llFaceSmall.setOnClickListener(this);
        llAddSmall.setOnClickListener(this);
    }

    @Override
    protected void initData() {
        bottomStatusBarHeight = SPUtils.getBottomStatusBarHeight(getThis());
        softInputKeyboradHeight = SPUtils.getSoftInputKeyboardHeight(getThis());
        String mobile = (String) SPUtils.get(getThis(), Constant.KEY_MOBILE, "");
        WechatChatItem item1 = new WechatChatItem(WechatChatAdapter.TYPE_SELF, "吴MoonMoon", R.mipmap
                .app_images_role_10000, "呵呵呵呵呵呵\n呵呵呵呵呵呵呵呵呵呵呵呵呵呵\n呵呵呵呵呵呵呵呵呵呵呵呵呵呵呵呵呵呵呵呵呵呵呵\n呵呵呵呵呵呵呵呵呵呵呵呵呵呵\n呵呵呵呵呵呵呵呵呵呵呵呵呵呵呵呵呵呵呵呵呵呵呵呵呵呵呵呵呵1", mobile);
        WechatChatItem item2 = new WechatChatItem(WechatChatAdapter.TYPE_OTHER, "吴MoonMoon", R.mipmap
                .app_images_role_10001,
                "呵呵呵呵呵呵呵呵呵呵呵呵呵呵呵呵呵呵呵呵呵呵呵呵呵呵\n呵呵呵呵呵呵呵呵呵呵呵呵呵呵呵呵呵呵呵呵呵呵呵呵呵呵\n呵呵呵呵呵呵呵呵呵呵呵呵呵呵呵呵呵呵呵呵呵呵呵呵呵呵呵呵呵呵呵呵呵呵2", mobile);
        WechatChatItem item3 = new WechatChatItem(WechatChatAdapter.TYPE_SELF, "吴MoonMoon", R.mipmap
                .app_images_role_10000,
                "呵呵呵呵呵呵呵呵呵呵呵呵呵呵呵呵\n呵呵呵呵呵呵呵呵呵呵呵呵呵呵呵\n呵呵呵呵呵呵呵呵呵呵呵呵呵呵呵呵呵呵呵呵呵呵呵呵\n呵呵呵呵呵呵呵呵呵呵呵呵呵呵呵呵呵呵呵呵呵呵呵呵呵呵呵呵呵呵呵3", mobile);
        WechatChatItem item4 = new WechatChatItem(WechatChatAdapter.TYPE_OTHER, "吴MoonMoon", R.mipmap
                .app_images_role_10001,
                "呵呵\n呵呵\n呵呵\n呵呵\n呵呵\n呵呵\n呵呵", mobile);
        WechatChatItem item5 = new WechatChatItem(WechatChatAdapter.TYPE_SELF, "吴MoonMoon", R.mipmap
                .app_images_role_10000,
                "呵呵\n呵呵\n呵呵\n呵呵\n呵呵\n呵呵\n呵呵", mobile);
        WechatChatItem item6 = new WechatChatItem(WechatChatAdapter.TYPE_OTHER, "吴MoonMoon", R.mipmap
                .app_images_role_10001,
                "呵呵\n呵呵\n呵呵呵呵呵呵呵呵呵呵呵呵呵呵呵呵\n呵呵\n呵呵\n呵呵\n呵呵", mobile);
        WechatChatItems.getInstance().add(item1);
        WechatChatItems.getInstance().add(item2);
        WechatChatItems.getInstance().add(item3);
        WechatChatItems.getInstance().add(item4);
        WechatChatItems.getInstance().add(item5);
        WechatChatItems.getInstance().add(item6);
        wechatChatAdapter.notifyDataSetChanged();
        rcvConversation.scrollToPosition(WechatChatItems.getInstance().size() - 1);
    }

    private void initFaceData(){
        WechatFaceItems.getInstance().clear();
        for (int i = 0; i < 126; i++) {
            //动态获取资源ID，第一个参数是资源名，第二个参数是资源类型例如drawable，string等，第三个参数包名
            if((i+1)%21!=0) {
                int imageId = getResources().getIdentifier("app_images_emoji_expression_" + (i+1), "mipmap",
                        getPackageName());
                WechatFaceItems.getInstance().add(new WechatFaceItem(i, imageId));
            }else{
                WechatFaceItems.getInstance().add(new WechatFaceItem(i, R.mipmap.app_views_pages_wechat_common_images_chatemojidel));
            }
        }
    }

    private void showConversationSettingDialog() {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.llLeft:
                isEditTextShowing = !isEditTextShowing;
                if (isEditTextShowing) {
                    edContent.setVisibility(View.VISIBLE);
                    tvPressSpeak.setVisibility(View.GONE);
                    edContent.setFocusable(true);
                    edContent.setFocusableInTouchMode(true);
                    edContent.requestFocus();
                    llFace.setVisibility(View.VISIBLE);
                    imgLeft.setImageResource(R.mipmap.app_views_pages_wechat_common_images_chatbottomicon1);
//                    InputMethodManager inputManager = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
//                    inputManager.showSoftInput(edContent, 0);
                    showSoftKeyBoard(edContent);
                } else {
                    tvPressSpeak.setVisibility(View.VISIBLE);
                    edContent.setVisibility(View.GONE);
                    llFace.setVisibility(View.GONE);
                    imgLeft.setImageResource(R.mipmap.app_views_pages_wechat_common_images_chatbottomicon4);
                    hideSoftKeyBoard();
                }
                break;
            case R.id.llFaceSmall:
                isFaceLayoutShowing = !isFaceLayoutShowing;
                if(isFaceLayoutShowing){
                    llFace.setVisibility(View.VISIBLE);
                    edContent.setVisibility(View.VISIBLE);
                    tvPressSpeak.setVisibility(View.GONE);
                    llFaceSmall.requestFocus();
                    imgFace.setImageResource(R.mipmap.app_views_pages_wechat_common_images_chatbottomicon4);
                    hideSoftKeyBoard();
                }else{
//                    llFace.setVisibility(View.GONE);
                    edContent.setVisibility(View.VISIBLE);
                    tvPressSpeak.setVisibility(View.GONE);
                    imgFace.setImageResource(R.mipmap.app_views_pages_wechat_common_images_chatbottomicon2);
                    edContent.requestFocus();
                    showSoftKeyBoard(edContent);
//                    InputMethodManager inputMethodManager = (InputMethodManager) edContent.getContext().getSystemService(Context
//                            .INPUT_METHOD_SERVICE);
//                    inputMethodManager.showSoftInput(edContent,0);
                }
                break;
            case R.id.llAddSmall:

                break;
        }
    }

    /**
     * ViewPager翻页时，正确显示目前选中的是那一页呢，也就是说在左右滑动时，下面小圆点的状态也要相应地做出改变：
     * 设置圆点
     */
    public void setOvalLayout() {
        for (int i = 0; i < pageCount; i++) {
            ll_dot.addView(inflater.inflate(R.layout.wechat_face_viewpage_dot, null));
        }
        // 默认显示第一页
        ll_dot.getChildAt(0).findViewById(R.id.v_dot)
                .setBackgroundResource(R.drawable.wechat_face_viewpager_dot_selected);
        viewpager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            public void onPageSelected(int position) {
                // 取消圆点选中
                ll_dot.getChildAt(curIndex)
                        .findViewById(R.id.v_dot)
                        .setBackgroundResource(R.drawable.wechat_face_viewpager_dot_normal);
                // 圆点选中
                ll_dot.getChildAt(position)
                        .findViewById(R.id.v_dot)
                        .setBackgroundResource(R.drawable.wechat_face_viewpager_dot_selected);
                curIndex = position;
            }

            public void onPageScrolled(int arg0, float arg1, int arg2) {
            }

            public void onPageScrollStateChanged(int arg0) {
            }
        });
    }



}
