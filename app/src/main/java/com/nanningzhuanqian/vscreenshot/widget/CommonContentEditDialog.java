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

public class CommonContentEditDialog  extends Dialog {
    private Context context;
    private EditText edMark;
    private TextView btnCancel;
    private TextView btnOk;
    private OnCancelListener cancelListener;
    private OnOkListener okListener;

    public CommonContentEditDialog(@NonNull Context context) {
        super(context);
        this.context = context;
        init();
    }

    private void init(){
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View dialogLayout = inflater.inflate(R.layout.common_content_edit_dialog, null);
        this.addContentView(dialogLayout, new ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));

        edMark = dialogLayout.findViewById(R.id.edMark);
        btnCancel = dialogLayout.findViewById(R.id.btnCancel);
        btnOk = dialogLayout.findViewById(R.id.btnOk);
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(cancelListener!=null){
                    cancelListener.OnCancel();
                }
            }
        });
        btnOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(okListener!=null){
                    String mark = edMark.getText().toString();
                    okListener.OnOk(mark);
                }
            }
        });
    }

    public void setOnCancelListener(OnCancelListener listener){
        this.cancelListener = listener;
    }

    public void setHint(String hint){
        if(edMark!=null){
            edMark.setText(hint);
            edMark.setSelection(hint.length());
        }
    }

    public void setOnOkListener(OnOkListener listener){
        this.okListener = listener;
    }

    public interface OnCancelListener{
        void OnCancel();
    }

    public interface OnOkListener{
        void OnOk(String mark);
    }

}
