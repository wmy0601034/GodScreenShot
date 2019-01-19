package com.nanningzhuanqian.vscreenshot.base.util;

import android.Manifest;
import android.app.Activity;
import android.app.AppOpsManager;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Handler;
import android.os.Looper;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by WeiKeting on 2017/03/14.
 */

public class PermissionUtils {

    public interface OnRequestPermissionListener{
        void onResult(boolean granted, Map<String, Boolean> denied);
    }

    static private Handler mHandler = null;
    static private Context applicationContext = null;
    static private class Request{
        public final Map<String,Boolean>result = new HashMap<>();
        public final int requestCode;
        public final boolean checkOp;
        public OnRequestPermissionListener listener;

        protected Request(int requestCode,boolean checkOp)
        {
            this.requestCode = requestCode;
            this.checkOp = checkOp;
        }
    }

    static private final List<Request>requests = new ArrayList<>();

    static public void requestPermissions (Activity activity,
                                           OnRequestPermissionListener l,
                                           String... permissions)
    {
        requestPermissions(activity,false, l, permissions);
    }

    static public void requestPermissions (Activity activity,
                                           boolean checkOp,
                                           OnRequestPermissionListener l,
                                           String... permissions)
    {
        applicationContext = activity.getApplicationContext();
        //try{String d=null;d.length();}catch (Throwable e){e.printStackTrace();}
        if(permissions == null)return;
        Request req = new Request((int) ((System.currentTimeMillis()/500)%Short.MAX_VALUE)+1024,checkOp);

        for(String s:permissions){
            if(ContextCompat.checkSelfPermission(activity,s) != PackageManager.PERMISSION_GRANTED) {
                req.result.put(s, false);
            }else if(checkOp){
                if(s.equals(Manifest.permission.ACCESS_COARSE_LOCATION) && !Appops.checkOp(activity,AppOpsManager.OPSTR_COARSE_LOCATION)){
                    req.result.put(s, false);
                }else if(s.equals(Manifest.permission.ACCESS_FINE_LOCATION) && !Appops.checkOp(activity,AppOpsManager.OPSTR_FINE_LOCATION)){
                    req.result.put(s, false);
                }
            }
        }
        req.listener = l;
        if(req.result.size() == 0){
            req.listener.onResult(true,req.result);
            return;
        }
        requests.add(req);
        if(mHandler == null)
            mHandler = new Handler(Looper.getMainLooper());
        ActivityCompat.requestPermissions(activity,permissions,req.requestCode);
    }


    static public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        for(final Request r:requests){
            if(r.requestCode == requestCode){
                for(int i=0;i<permissions.length;i++){
//                    if(BuildConfig.DEBUG) Log.e("requestPermissions",permissions[i]+":"+grantResults[i]);
                    if(grantResults[i] == PackageManager.PERMISSION_GRANTED) {
                        if(r.checkOp){
                            if(permissions[i].equals(Manifest.permission.ACCESS_FINE_LOCATION)){
                                if(Appops.checkOp(applicationContext,AppOpsManager.OPSTR_FINE_LOCATION)){
                                    r.result.remove(permissions[i]);
                                }
                                continue;
                            }else if(permissions[i].equals(Manifest.permission.ACCESS_COARSE_LOCATION)){
                                if(Appops.checkOp(applicationContext,AppOpsManager.OPSTR_COARSE_LOCATION)){
                                    r.result.remove(permissions[i]);
                                }
                                continue;
                            }
                        }
                        r.result.remove(permissions[i]);
                    }
                }
                if(r.listener!=null){
                    mHandler.post(new Runnable() {
                        @Override
                        public void run() {
                            r.listener.onResult(r.result.size() ==0,r.result);
                        }
                    });
                }
                requests.remove(r);
                break;
            }
        }
    }
}
