package com.nanningzhuanqian.vscreenshot.widget;

import android.app.Dialog;
import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.nanningzhuanqian.vscreenshot.R;

/**
 * Created by lenovo on 2018/10/7.
 */

public class WechatPromptDialog extends Dialog {
    private Context context;
    private TextView btnCancel;
    private TextView btnOk;

    public WechatPromptDialog(@NonNull Context context) {
        super(context);
        this.context = context;
        init();
    }

    private void init(){
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View dialogLayout = inflater.inflate(R.layout.wechat_common_prompt_dialog, null);
        this.addContentView(dialogLayout, new ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));

        btnCancel = dialogLayout.findViewById(R.id.btnCancel);
        btnOk = dialogLayout.findViewById(R.id.btnOk);
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dismiss();
            }
        });
        btnOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dismiss();
            }
        });
    }

}
