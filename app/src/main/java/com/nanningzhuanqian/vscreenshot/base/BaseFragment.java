package com.nanningzhuanqian.vscreenshot.base;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.widget.TextView;
import android.widget.Toast;

import com.nanningzhuanqian.vscreenshot.base.util.SPUtils;

public class BaseFragment extends Fragment {

    public String TAG = getClass().getSimpleName();

    public void toast(String msg){
        Toast.makeText(getActivity(),msg,Toast.LENGTH_SHORT).show();
    }

    public boolean isLogin(){
        String mobile = (String) SPUtils.get(getThis(),"mobile","");
        if(TextUtils.isEmpty(mobile)){
            return false;
        }else {
            return true;
        }
    }


    public Context getThis(){
        return getActivity();
    }
}
