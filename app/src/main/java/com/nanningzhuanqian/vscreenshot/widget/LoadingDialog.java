package com.nanningzhuanqian.vscreenshot.widget;

import android.app.Dialog;
import android.content.Context;
import android.graphics.drawable.AnimationDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.nanningzhuanqian.vscreenshot.R;


/**
 * Created by zhul on 2016/3/4.
 */
public class LoadingDialog extends Dialog {

    private ImageView ivLoadingView;

    public LoadingDialog(Context context, int theme) {
        super(context, theme);

        this.setCanceledOnTouchOutside(false);

        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View dialogLayout = inflater.inflate(R.layout.common_loading_dialog_layout, null);
        this.addContentView(dialogLayout, new ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));

        // 开启动画
        ivLoadingView = (ImageView) dialogLayout.findViewById(R.id.ivLoadingView);
        AnimationDrawable animDrawable = (AnimationDrawable) ivLoadingView.getDrawable();
        animDrawable.start();
    }


}
