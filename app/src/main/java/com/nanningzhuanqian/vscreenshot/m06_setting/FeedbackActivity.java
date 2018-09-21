package com.nanningzhuanqian.vscreenshot.m06_setting;

import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.nanningzhuanqian.vscreenshot.R;
import com.nanningzhuanqian.vscreenshot.base.BaseActivity;
import com.nanningzhuanqian.vscreenshot.base.net.CallbackListener;
import com.nanningzhuanqian.vscreenshot.base.net.HttpUtil;
import com.nanningzhuanqian.vscreenshot.base.util.SPUtils;
import com.nanningzhuanqian.vscreenshot.common.Constant;
import com.nanningzhuanqian.vscreenshot.model.Feedback;

public class FeedbackActivity extends BaseActivity {
    private TextView tvBack;
    private TextView tvTitle;
    private EditText edContent;
    private Button btnSubmit;
    @Override
    protected int getLayoutId() {
        return R.layout.activity_feedback;
    }

    @Override
    protected void initView() {
        tvBack = (TextView)findViewById(R.id.tvBack);
        tvBack.setVisibility(View.VISIBLE);
        tvTitle = (TextView)findViewById(R.id.tvTitle);
        tvTitle.setText("意见反馈");
        edContent = (EditText)findViewById(R.id.edContent);
        btnSubmit = (Button)findViewById(R.id.btnSubmit);
    }

    @Override
    protected void initEvent() {
        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String content = edContent.getText().toString();
                if(TextUtils.isEmpty(content)){
                    toast("请输入反馈内容");
                    return;
                }
                showLoadingDialog();
                String mobile = (String) SPUtils.get(getThis(), Constant.KEY_MOBILE,"");
                Feedback feedback = new Feedback(mobile,content);
                HttpUtil.getInstance().submitFeedback(feedback, new CallbackListener() {
                    @Override
                    public void onSuccess() {
                        hideLoadingDialog();
                        toast("提交成功");
                        finish();
                    }

                    @Override
                    public void onGetSuccess(Object o) {
                        hideLoadingDialog();
                        toast("提交成功");
                        finish();
                    }

                    @Override
                    public void onFailure(String message) {
                        hideLoadingDialog();
                        toast(message);
                    }
                });
            }
        });
    }

    @Override
    protected void initData() {

    }
}
