package com.nanningzhuanqian.vscreenshot.m01_wechat_main;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Rect;
import android.hardware.input.InputManager;
import android.net.Uri;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
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

import com.nanningzhuanqian.vscreenshot.MainActivity;
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
import com.nanningzhuanqian.vscreenshot.item.ConversationItem;
import com.nanningzhuanqian.vscreenshot.item.ConversationItems;
import com.nanningzhuanqian.vscreenshot.item.WechatChatItem;
import com.nanningzhuanqian.vscreenshot.item.WechatChatItems;
import com.nanningzhuanqian.vscreenshot.item.WechatFaceItem;
import com.nanningzhuanqian.vscreenshot.item.WechatFaceItems;
import com.nanningzhuanqian.vscreenshot.item.WechatSingleChatMoreItem;
import com.nanningzhuanqian.vscreenshot.item.WechatSingleChatMoreItems;
import com.nanningzhuanqian.vscreenshot.m02_add_conversation.AddCustomConversationActivity;
import com.nanningzhuanqian.vscreenshot.model.ConversationLite;
import com.nanningzhuanqian.vscreenshot.widget.ArrowView;
import com.nanningzhuanqian.vscreenshot.widget.AutoGridView;
import com.nanningzhuanqian.vscreenshot.widget.CustomListview;
import com.nanningzhuanqian.vscreenshot.widget.NewActionSheetDialog;

import org.litepal.LitePal;
import org.w3c.dom.Text;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import cn.bmob.v3.b.V;

/*

 */
public class WechatSingeChatActivity extends BaseActivity implements View.OnClickListener {
    private ImageButton btnRight;
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
    private TextView btnSend;
    private ImageView imgAdd;
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

    private String otherName = "";
    private String wechatUserAvatarType = "";
    private String wechatOtherAvatarType = "";
    private Uri wechatSelfAvatarUri;
    private Uri wechatOtherAvatarUri;
    private int wechatSelfAvatarRes;
    private int wechatOtherAvatarRes;

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
    private boolean isFirstSet = true;
    private boolean isHideByClickFace = false;
    private boolean isCallByClickLeft = false;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_single_chat;
    }

    @Override
    protected void initView() {
        initWechatTopBar();
        btnRight = (ImageButton)findViewById(R.id.btnRight);
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
        btnSend = (TextView)findViewById(R.id.btnSend);
        imgAdd = (ImageView)findViewById(R.id.imgAdd);
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
        initMoreItem();
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
            gridView.setAdapter(new WechatSingleChatMoreAdapter(this, i, morePageSize));
            //使用GridView作为每个ViewPager的页面，也就是说每个ViewPager的页面都是inflate出一个GridView新实例
            morePagerList.add(rootView);

            gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    int pos = position + moreCurIndex * morePageSize;
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
                Log.i(TAG,"getViewTreeObserver layoutHeight = "+layoutHeight);
                if(0<layoutHeight&&layoutHeight<=180){
                    bottom_status_bar_height = layoutHeight;
                    Log.i(TAG, "监听到键盘隐藏 虚拟导航键的高度为 height(单位像素) = " + bottom_status_bar_height+
                            (isHideByClickFace?"点击表情按钮隐藏":"点击软键盘的隐藏按钮隐藏"));
                    if(!isHideByClickFace&&!isCallByClickLeft){
                        isEditTextShowing = false;
                        tvPressSpeak.setVisibility(View.VISIBLE);
                        edContent.setVisibility(View.GONE);
                        llFace.setVisibility(View.GONE);
                        imgLeft.setImageResource(R.mipmap.app_views_pages_wechat_common_images_chatbottomicon4);
                    }
                    isHideByClickFace = false;
                    isCallByClickLeft = false;
                    SPUtils.put(getThis(), Constant.KEY_BOTTOM_STATUS_BAR_HEIGHT,bottom_status_bar_height);
                }else if(layoutHeight>180){
                    isCallByClickLeft = false;
                    soft_input_keyborad_height = layoutHeight-bottom_status_bar_height;
                    int height = SPUtils.getSoftInputKeyboardHeight(getApplicationContext());
                    if(height!=softInputKeyboradHeight) {
                        Log.i(TAG, "键盘的高度为 height(单位像素) = " + soft_input_keyborad_height);
                        SPUtils.put(getThis(), Constant.KEY_SOFT_INPUT_KEYBORAD_HEIGHT, soft_input_keyborad_height);
                        setFaceHeight();
                        isFirstSet = true;
                    }else if(height==softInputKeyboradHeight&&isFirstSet){
                        isFirstSet = false;
                        isCallByClickLeft = false;
                        Log.i(TAG, "键盘的高度为 height(单位像素) = " + soft_input_keyborad_height
                        +"键盘高度虽然相同但是是第一次设置，所以也要改变 isFirstSet = "+isFirstSet);
                        setFaceHeight();
                    }else{
                        isCallByClickLeft = false;
                        Log.i(TAG,"监听到键盘弹出");
                    }
                }
            }
        });
        viewpager.setAdapter(new FaceViewPagerAdapter(pagerList));
        viewpagerMore.setAdapter(new WechatSingleChatMoreViewPagerAdapter(morePagerList));
        //设置圆点
        setOvalLayout();
        setFaceHeight();
    }

    private void setFaceHeight(){
        LinearLayout.LayoutParams llFaceLayoutParams = (LinearLayout.LayoutParams) llFace.getLayoutParams();
        int llFaceHeight = SPUtils.getSoftInputKeyboardHeight(getApplicationContext());
        llFaceLayoutParams.height = llFaceHeight;
        Log.i(TAG,"llFaceHeight = "+llFaceHeight);
        llFace.setLayoutParams(llFaceLayoutParams);

        LinearLayout.LayoutParams llMoreLayoutParams = (LinearLayout.LayoutParams) llMore.getLayoutParams();
        int llMoreHeight = SPUtils.getSoftInputKeyboardHeight(getApplicationContext());
        llMoreLayoutParams.height = llMoreHeight;
        Log.i(TAG,"llMoreHeight = "+llMoreHeight);
        llMore.setLayoutParams(llMoreLayoutParams);
    }

    @Override
    protected void initEvent() {
        llLeft.setOnClickListener(this);
        llFaceSmall.setOnClickListener(this);
        llAddSmall.setOnClickListener(this);
        btnRight.setOnClickListener(this);
        btnSend.setOnClickListener(this);
        wechatChatAdapter.setOnItemLongClickListener(new WechatChatAdapter.OnItemLongClickListener() {
            @Override
            public void onItemLongClick(int position) {

            }
        });
        wechatChatAdapter.setOnItemClickListener(new WechatChatAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                showWechatChatItemSheetDialog(position);
            }
        });
        edContent.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable e) {
                String content = edContent.getText().toString();
                if(!TextUtils.isEmpty(content)){
                    imgAdd.setVisibility(View.GONE);
                    btnSend.setVisibility(View.VISIBLE);
                }else {
                    imgAdd.setVisibility(View.VISIBLE);
                    btnSend.setVisibility(View.GONE);
                }
            }
        });
    }

    @Override
    protected void initData() {
        int position = getIntent().getIntExtra("position",0);
        ConversationItem conversationItem = ConversationItems.getInstance().get(position);
        otherName = conversationItem.getName();
        wechatOtherAvatarType = conversationItem.getAvatarType();
        wechatOtherAvatarUri = conversationItem.getAvatarUri();
        wechatOtherAvatarRes = conversationItem.getImgRes();
        bottomStatusBarHeight = SPUtils.getBottomStatusBarHeight(getThis());
        softInputKeyboradHeight = SPUtils.getSoftInputKeyboardHeight(getThis());
        WechatChatItem item = new WechatChatItem(WechatChatAdapter.TYPE_TIME,"",0,"",mobile);
        item.setTime("周二 00:22");
        WechatChatItem item1 = new WechatChatItem(WechatChatAdapter.TYPE_SELF, wechatUserName, R.mipmap
                .app_images_role_10000, "呵呵呵呵呵呵\n呵呵呵呵呵呵呵呵呵呵呵呵呵呵\n呵呵呵呵呵呵呵呵呵呵呵呵呵呵呵呵呵呵呵呵呵呵呵\n呵呵呵呵呵呵呵呵呵呵呵呵呵呵\n呵呵呵呵呵呵呵呵呵呵呵呵呵呵呵呵呵呵呵呵呵呵呵呵呵呵呵呵呵1", mobile);
        WechatChatItem item2 = new WechatChatItem(WechatChatAdapter.TYPE_OTHER, otherName, wechatOtherAvatarRes,
                "呵呵呵呵呵呵呵呵呵呵呵呵呵呵呵呵呵呵呵呵呵呵呵呵呵呵\n呵呵呵呵呵呵呵呵呵呵呵呵呵呵呵呵呵呵呵呵呵呵呵呵呵呵\n呵呵呵呵呵呵呵呵呵呵呵呵呵呵呵呵呵呵呵呵呵呵呵呵呵呵呵呵呵呵呵呵呵呵2", mobile);
        WechatChatItem item3 = new WechatChatItem(WechatChatAdapter.TYPE_SELF, wechatUserName, R.mipmap
                .app_images_role_10000,
                "呵呵呵呵呵呵呵呵呵呵呵呵呵呵呵呵\n呵呵呵呵呵呵呵呵呵呵呵呵呵呵呵\n呵呵呵呵呵呵呵呵呵呵呵呵呵呵呵呵呵呵呵呵呵呵呵呵\n呵呵呵呵呵呵呵呵呵呵呵呵呵呵呵呵呵呵呵呵呵呵呵呵呵呵呵呵呵呵呵3", mobile);
        WechatChatItem item4 = new WechatChatItem(WechatChatAdapter.TYPE_OTHER, otherName, wechatOtherAvatarRes,
                "呵呵\n呵呵\n呵呵\n呵呵\n呵呵", mobile);
        WechatChatItem item5 = new WechatChatItem(WechatChatAdapter.TYPE_SELF, wechatUserName, R.mipmap
                .app_images_role_10000,
                "呵呵", mobile);
        WechatChatItem item6 = new WechatChatItem(WechatChatAdapter.TYPE_OTHER, otherName, wechatOtherAvatarRes,
                "呵呵", mobile);
        WechatChatItems.getInstance().add(item);
//        WechatChatItems.getInstance().add(item1);
        WechatChatItems.getInstance().add(item2);
        WechatChatItems.getInstance().add(item3);
        WechatChatItems.getInstance().add(item4);
        WechatChatItems.getInstance().add(item5);
//        WechatChatItems.getInstance().add(item6);
        notifyWechatData();
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

    private void initMoreItem(){
        WechatSingleChatMoreItems.getInstance().clear();
//        for(int i = 0;i<10;i++){
//            int imageId = getResources().getIdentifier("app_views_pages_wechat_common_images_chatmore" + (i+1), "mipmap",
//                    getPackageName());
//        }
        WechatSingleChatMoreItems.getInstance().add(new WechatSingleChatMoreItem(R.mipmap.app_views_pages_wechat_common_images_chatmore1,"相册",0));
        WechatSingleChatMoreItems.getInstance().add(new WechatSingleChatMoreItem(R.mipmap.app_views_pages_wechat_common_images_chatmore2,"拍照",1));
        WechatSingleChatMoreItems.getInstance().add(new WechatSingleChatMoreItem(R.mipmap.app_views_pages_wechat_common_images_chatmore3,"视频通话",2));
        WechatSingleChatMoreItems.getInstance().add(new WechatSingleChatMoreItem(R.mipmap.app_views_pages_wechat_common_images_chatmore4,"位置",3));
        WechatSingleChatMoreItems.getInstance().add(new WechatSingleChatMoreItem(R.mipmap.app_views_pages_wechat_common_images_chatmore5,"红包",4));
        WechatSingleChatMoreItems.getInstance().add(new WechatSingleChatMoreItem(R.mipmap.app_views_pages_wechat_common_images_chatmore6,"转账",5));
        WechatSingleChatMoreItems.getInstance().add(new WechatSingleChatMoreItem(R.mipmap.app_views_pages_wechat_common_images_chatmore7,"语音输入",6));
        WechatSingleChatMoreItems.getInstance().add(new WechatSingleChatMoreItem(R.mipmap.app_views_pages_wechat_common_images_chatmore9,"我的收藏",7));
        WechatSingleChatMoreItems.getInstance().add(new WechatSingleChatMoreItem(R.mipmap.app_views_pages_wechat_common_images_chatmore8,"名片",8));
        WechatSingleChatMoreItems.getInstance().add(new WechatSingleChatMoreItem(R.mipmap.app_views_pages_wechat_common_images_chatmore10,"文件",9));

    }

    private void showConversationSettingDialog() {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.llLeft:
                isEditTextShowing = !isEditTextShowing;
                if (isEditTextShowing) {
                    Log.i(TAG,"点击了左边按钮 显示软键盘");
                    edContent.setVisibility(View.VISIBLE);
                    tvPressSpeak.setVisibility(View.GONE);
                    edContent.setFocusable(true);
                    edContent.setFocusableInTouchMode(true);
                    edContent.requestFocus();
                    llFace.setVisibility(View.VISIBLE);
                    llMore.setVisibility(View.GONE);
                    imgLeft.setImageResource(R.mipmap.app_views_pages_wechat_common_images_chatbottomicon1);
                    isCallByClickLeft = true;
                    showSoftKeyBoard(edContent);
                } else {
                    Log.i(TAG,"点击了左边按钮 隐藏软键盘");
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
                    llMore.setVisibility(View.GONE);
                    edContent.setVisibility(View.VISIBLE);
                    tvPressSpeak.setVisibility(View.GONE);
                    llFaceSmall.requestFocus();
                    imgFace.setImageResource(R.mipmap.app_views_pages_wechat_common_images_chatbottomicon4);
                    imgLeft.setImageResource(R.mipmap.app_views_pages_wechat_common_images_chatbottomicon1);
                    isHideByClickFace = true;
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
                llMore.setVisibility(View.VISIBLE);
                llFace.setVisibility(View.GONE);
                hideSoftKeyBoard();
                break;
            case R.id.btnRight:
                showChatSettingSheetDialog();
                break;
            case R.id.btnSend:
                showSendOptionSheetDialog();
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

        for (int i = 0; i < morePageCount; i++) {
            ll_dot_more.addView(inflater.inflate(R.layout.wechat_face_viewpage_dot, null));
        }
        // 默认显示第一页
        ll_dot_more.getChildAt(0).findViewById(R.id.v_dot)
                .setBackgroundResource(R.drawable.wechat_face_viewpager_dot_selected);
        viewpagerMore.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            public void onPageSelected(int position) {
                // 取消圆点选中
                ll_dot_more.getChildAt(moreCurIndex)
                        .findViewById(R.id.v_dot)
                        .setBackgroundResource(R.drawable.wechat_face_viewpager_dot_normal);
                // 圆点选中
                ll_dot_more.getChildAt(position)
                        .findViewById(R.id.v_dot)
                        .setBackgroundResource(R.drawable.wechat_face_viewpager_dot_selected);
                moreCurIndex = position;
            }

            public void onPageScrolled(int arg0, float arg1, int arg2) {
            }

            public void onPageScrollStateChanged(int arg0) {
            }
        });
    }

    private void showChatSettingSheetDialog(){
        NewActionSheetDialog.Builder builder = new NewActionSheetDialog.Builder(WechatSingeChatActivity.this);

        builder.setCancelable(false);
        builder.setCancelButtonVisiable(true);
        builder.setCanceledOnTouchOutside(true);
        builder.setTitle(getString(R.string.action_settings));
        builder.addSheetItem("设置", NewActionSheetDialog
                .SheetItemColor.Blue, new NewActionSheetDialog.Builder
                .OnSheetItemClickListener() {
            @Override
            public void onClick(int which) {
                //跳转到设置页面
                Intent intent = new Intent(getThis(),WechatConversationSettingActivity.class);
                startActivity(intent);
            }
        });
        builder.addSheetItem("用户管理", NewActionSheetDialog
                .SheetItemColor.Blue, new NewActionSheetDialog.Builder
                .OnSheetItemClickListener() {
            @Override
            public void onClick(int which) {
                //用户管理 编辑用户 选择自己发言还是对方发言 语音模式
                // TODO: 2018/10/2 实现用户 管理  
            }
        });
        builder.addSheetItem(getResources().getString(R.string.sheet_item_clear_conversation), NewActionSheetDialog
                .SheetItemColor.Blue, new NewActionSheetDialog.Builder
                .OnSheetItemClickListener() {
            @Override
            public void onClick(int which) {
                //清空对话内容
                WechatChatItems.getInstance().clear();
                notifyWechatData();
            }
        });
        builder.addSheetItem("删除用户", NewActionSheetDialog
                .SheetItemColor.Blue, new NewActionSheetDialog.Builder
                .OnSheetItemClickListener() {
            @Override
            public void onClick(int which) {
                //根据传入的position删除该用户

            }
        });

        Dialog dialog = builder.create();
        dialog.show();
    }

    private void showWechatChatItemSheetDialog(final int position){
        NewActionSheetDialog.Builder builder = new NewActionSheetDialog.Builder(WechatSingeChatActivity.this);

        builder.setCancelable(false);
        builder.setCancelButtonVisiable(true);
        builder.setCanceledOnTouchOutside(true);
        builder.setTitle(getString(R.string.action_settings));
        builder.addSheetItem("编辑", NewActionSheetDialog
                .SheetItemColor.Blue, new NewActionSheetDialog.Builder
                .OnSheetItemClickListener() {
            @Override
            public void onClick(int which) {

            }
        });
        builder.addSheetItem("删除", NewActionSheetDialog
                .SheetItemColor.Blue, new NewActionSheetDialog.Builder
                .OnSheetItemClickListener() {
            @Override
            public void onClick(int which) {
                //根据传入的position删除该item
                showWechatChatItemDeleteConfirmDialog(position);
            }
        });

        Dialog dialog = builder.create();
        dialog.show();
    }

    private void showWechatChatItemDeleteConfirmDialog(final int position){
        AlertDialog.Builder builder = new AlertDialog.Builder(getThis());
        builder.setTitle("提示")
                .setMessage("是否删除该项发言")
                .setCancelable(true)
                .setNegativeButton("取消",null)
                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                        WechatChatItems.getInstance().remove(position);
                        notifyWechatData();
                    }
                });
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }

    private void showSendOptionSheetDialog(){
        NewActionSheetDialog.Builder builder = new NewActionSheetDialog.Builder(WechatSingeChatActivity.this);

        builder.setCancelable(false);
        builder.setCancelButtonVisiable(true);
        builder.setCanceledOnTouchOutside(true);
        builder.setTitle(getString(R.string.action_settings));
        builder.addSheetItem("自己发送", NewActionSheetDialog
                .SheetItemColor.Blue, new NewActionSheetDialog.Builder
                .OnSheetItemClickListener() {
            @Override
            public void onClick(int which) {
                String content = edContent.getText().toString();
                WechatChatItem item = new WechatChatItem(WechatChatAdapter.TYPE_SELF, wechatUserName,wechatSelfAvatarRes,
                        content, mobile);
                item.setImgType(wechatUserAvatarType);
                item.setAvatarUri(wechatSelfAvatarUri);
                WechatChatItems.getInstance().add(item);
                notifyWechatData();
                resetChatEditText();
            }
        });
        builder.addSheetItem("对方发送", NewActionSheetDialog
                .SheetItemColor.Blue, new NewActionSheetDialog.Builder
                .OnSheetItemClickListener() {
            @Override
            public void onClick(int which) {
                String content = edContent.getText().toString();
                WechatChatItem item = new WechatChatItem(WechatChatAdapter.TYPE_OTHER, otherName,wechatOtherAvatarRes,
                        content, mobile);
                item.setImgType(wechatOtherAvatarType);
                item.setAvatarUri(wechatOtherAvatarUri);
                WechatChatItems.getInstance().add(item);
                notifyWechatData();
                resetChatEditText();
            }
        });
        builder.addSheetItem("单聊设置", NewActionSheetDialog
                .SheetItemColor.Blue, new NewActionSheetDialog.Builder
                .OnSheetItemClickListener() {
            @Override
            public void onClick(int which) {

            }
        });

        Dialog dialog = builder.create();
        dialog.show();
    }

    private void notifyWechatData(){
        wechatChatAdapter.notifyDataSetChanged();
        if(WechatChatItems.getInstance().size()>2) {
            rcvConversation.scrollToPosition(WechatChatItems.getInstance().size() - 1);
        }
    }

    private void resetChatEditText(){
        edContent.setText("");
        btnSend.setVisibility(View.GONE);
        imgAdd.setVisibility(View.VISIBLE);
    }

}
