package com.nanningzhuanqian.vscreenshot.base.net;


import java.util.List;

public interface CallbackListener<E> {

    /**
     * 请求的响应结果为成功时调用
     */
    public void onSuccess();


    public void onGetSuccess(E e);
    /**
     * 请求的响应结果为失败时调用
     *
     * @param message 错误信息
     */
    public void onFailure(String message);

}
