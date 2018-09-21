package com.nanningzhuanqian.vscreenshot.base;

/**
 * Created by keting on 16-12-10.
 */

public interface IModel {

    void    onDestroy();
    <O extends Object>O valueForKey(String key);
    void    setValueForKey(String key, Object v);
    boolean hasKey(String key);
}
