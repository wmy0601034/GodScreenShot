package com.nanningzhuanqian.vscreenshot.base.net;

import android.util.Log;

import com.nanningzhuanqian.vscreenshot.model.Config;
import com.nanningzhuanqian.vscreenshot.model.ContractBmob;
import com.nanningzhuanqian.vscreenshot.model.ConversationBmob;
import com.nanningzhuanqian.vscreenshot.model.Feedback;
import com.nanningzhuanqian.vscreenshot.model.User;
import com.nanningzhuanqian.vscreenshot.model.WechatWalletConfig;

import java.util.List;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.BmobUser;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;
import cn.bmob.v3.listener.QueryListener;
import cn.bmob.v3.listener.SaveListener;
import cn.bmob.v3.listener.UpdateListener;

/**
 * Created by DELL on 2018/4/18.
 */

public class BmobApi implements API {

    @Override
    public void login(String username, String password, final CallbackListener callbackListener) {
        final User user = new User();
        user.setUsername(username);
        user.setPassword(password);
        user.login(new SaveListener<User>() {
            @Override
            public void done(User user, BmobException e) {
                if (e == null) {
                    callbackListener.onSuccess();
                } else {
                    Log.i("wmy", "登录失败 " + e.toString());
                    callbackListener.onFailure(e.toString());
                }
            }
        });
    }

    @Override
    public void register(String mobile, String password, final CallbackListener callbackListener) {
        final User user = new User();
        user.setUsername(mobile);
        user.setPassword(password);
        user.signUp(new SaveListener<User>() {
            @Override
            public void done(User user, BmobException e) {
                if (e == null) {
                    callbackListener.onSuccess();
                } else {
                    Log.i("wmy", "登录失败 " + e.toString());
                    callbackListener.onFailure(e.toString());
                }
            }
        });
    }

    @Override
    public void updatePassword(String oldPassword, String newPassword, final CallbackListener callbackListener) {
        BmobUser.updateCurrentUserPassword(oldPassword, newPassword, new UpdateListener() {
            @Override
            public void done(BmobException e) {
                if (e == null) {
                    callbackListener.onSuccess();
                } else {
                    callbackListener.onFailure(e.toString());
                }
            }
        });
    }

    @Override
    public void logout() {
        BmobUser.logOut();
    }

    @Override
    public void createAccount(String email, String password, String name, final int
            groupPosition, String group, String groupDisplayName, int status, final
                              CallbackListener callbackListener) {

    }

    @Override
    public void getConfig(final CallbackListener callbackListener) {
        //uq0A999K
        BmobQuery<Config> bmobQuery = new BmobQuery<Config>();
        bmobQuery.getObject("uq0A999K", new QueryListener<Config>() {

            @Override
            public void done(Config config, BmobException e) {
                if (e == null) {
                    callbackListener.onGetSuccess(config);
                } else {
                    callbackListener.onFailure(e.toString());
                }
            }
        });
    }

    @Override
    public void submitFeedback(Feedback feedback, final CallbackListener callbackListener) {
        feedback.save(new SaveListener<String>() {
            @Override
            public void done(String objectId, BmobException e) {
                if (e == null) {
                    callbackListener.onSuccess();
                } else {
                    callbackListener.onFailure(e.toString());
                }
            }
        });
    }

    @Override
    public void saveContract(ContractBmob contractBmob, CallbackListener callbackListener) {
        contractBmob.save(new SaveListener<String>() {
            @Override
            public void done(String objectId, BmobException e) {

            }
        });
    }

    @Override
    public void saveConversation(ConversationBmob conversationBmob, CallbackListener callbackListener) {
        conversationBmob.save(new SaveListener<String>() {
            @Override
            public void done(String s, BmobException e) {

            }
        });
    }

    @Override
    public void getWechatWalletContent(final CallbackListener callbackListener) {
        BmobQuery<WechatWalletConfig> query = new BmobQuery<WechatWalletConfig>();
        query.findObjects(new FindListener<WechatWalletConfig>() {
            @Override
            public void done(List<WechatWalletConfig> list, BmobException e) {
                if(e==null){
                    callbackListener.onGetSuccess(list);
                }else{
                    callbackListener.onFailure(e.toString());
                }
            }
        });
    }
}
