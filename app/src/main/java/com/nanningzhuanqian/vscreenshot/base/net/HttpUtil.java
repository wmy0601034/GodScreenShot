package com.nanningzhuanqian.vscreenshot.base.net;


import com.nanningzhuanqian.vscreenshot.model.Feedback;

/**
 * Created by DELL on 2018/4/18.
 */

public class HttpUtil {

    private API api = new BmobApi();    //以后换接口改这里

    private static HttpUtil httpUtil;

    private HttpUtil() {
        super();
    }

    public static HttpUtil getInstance() {

        if (httpUtil == null)
            httpUtil = new HttpUtil();

        return httpUtil;
    }

    public void login(String username, String password, CallbackListener callbackListener) {
        api.login(username, password, callbackListener);
    }

    public void updatePassword(String oldPassword,String newPassword,CallbackListener callbackListener){
        api.updatePassword(oldPassword,newPassword,callbackListener);
    }

    public void logout() {
        api.logout();
    }

    public void createAccount(String email, String password, String name, int groupPosition,
                              String group, String groupDisplayName, int status, final
                              CallbackListener callbackListener) {
        api.createAccount(email, password, name, groupPosition, group, groupDisplayName, status,
                callbackListener);
    }

    public void register(String mobile,String password,CallbackListener callbackListener){
        api.register(mobile,password,callbackListener);
    }

    public void getConfig(CallbackListener callbackListener){
        api.getConfig(callbackListener);
    }

    public void submitFeedback(Feedback feedback,CallbackListener callbackListener){
        api.submitFeedback(feedback,callbackListener);
    }

    public void getWechatWalletContent(CallbackListener callbackListener){
        api.getWechatWalletContent(callbackListener);
    }

    public void getNetworkAvatars(CallbackListener callbackListener){
        api.getNetworkAvatars(callbackListener);
    }

    public void resetPwd(String mobile,String pwd,String sms,CallbackListener callbackListener){
        api.resetPwd(mobile,pwd,sms,callbackListener);
    }
}
