package com.nanningzhuanqian.vscreenshot.m00_launcher;

import com.nanningzhuanqian.vscreenshot.base.BaseFragment;
import com.nanningzhuanqian.vscreenshot.m00_launcher.main.MainFragment;
import com.nanningzhuanqian.vscreenshot.m00_launcher.main.SettingFragment;
import com.nanningzhuanqian.vscreenshot.m00_launcher.main.UserFragment;

import java.util.HashMap;

public class MainFragmentFactory {
    private static HashMap<Integer, BaseFragment> mSavedFragment = new HashMap<Integer, BaseFragment>();

    //根据position得到对应的fragment
    public static BaseFragment getFragment(int position) {
        BaseFragment fragment = mSavedFragment.get(position);
        if(fragment == null) {
            switch (position) {
                case 0:
                    fragment =  new MainFragment();
                    break;
                case 1:
                    fragment = new UserFragment();
                    break;
                case 2:
                    fragment = new SettingFragment();
                    break;

            }
            //创建之后保存
            mSavedFragment.put(position, fragment);
        }


        return fragment;
    }
    public static void deleteFragment(){
        for (int i = 0 ; i < 3 ; i++){
            mSavedFragment.remove(i);
        }

    }

}
