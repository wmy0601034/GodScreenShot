package com.nanningzhuanqian.vscreenshot.base.net;

import com.nanningzhuanqian.vscreenshot.model.ContractBmob;
import com.nanningzhuanqian.vscreenshot.model.ConversationBmob;
import com.nanningzhuanqian.vscreenshot.model.Feedback;

/**
 * Created by wmy on 2018/04/18.
 */

public interface API {

    /**
     * 登录
     *
     * @param username         用户名
     * @param password         密码
     * @param callbackListener 回调 返回是否成功
     */
    void login(String username, String password, CallbackListener callbackListener);

    void updatePassword(String oldPassword, String newPassword, CallbackListener callbackListener);

    void register(String mobile,String password,CallbackListener callbackListener);
    /**
     * 退出登录
     */
    void logout();

    /**
     * @param email            邮箱
     * @param password         密码
     * @param name             姓名
     * @param groupPosition    当前组别的position
     * @param group            组别enum的名称   如ADMIN
     * @param groupDisplayName 组别显示的名称  如管理员
     * @param status           账号状态
     * @param callbackListener 回调 返回是否成功
     */
    void createAccount(String email, String password, String name, int groupPosition,
                       String group, String groupDisplayName, int status,
                       CallbackListener callbackListener);

    void getConfig(CallbackListener callbackListener);

    void submitFeedback(Feedback feedback,CallbackListener callbackListener);

    void saveContract(ContractBmob contractBmob,CallbackListener callbackListener);

    void saveConversation(ConversationBmob conversationBmob,CallbackListener callbackListener);

    void getWechatWalletContent(CallbackListener callbackListener);
}
