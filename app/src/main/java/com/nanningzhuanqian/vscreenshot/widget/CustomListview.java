package com.nanningzhuanqian.vscreenshot.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ListView;

public class CustomListview extends ListView {

    public CustomListview(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    public CustomListview(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public CustomListview(Context context) {
        super(context);
    }

    /**
     * 重寫onMeasure方法决定了组件显示的高度与宽度； makeMeasureSpec函数中第一个參數决定布局空间的大小，第二个参数是布局模式
     * MeasureSpec.AT_MOST的意思就是子控件需要多大的控件就扩展到多大的空间
     */
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {

        int expandSpec = MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE >> 2,
                MeasureSpec.AT_MOST);
        super.onMeasure(widthMeasureSpec, expandSpec);
    }


}
