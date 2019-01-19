package com.nanningzhuanqian.vscreenshot.base.util;

import android.app.AppOpsManager;
import android.content.Context;
import android.os.Process;


/**
 * Created by keting on 5/2/17.
 */

public class Appops {

    //检查，Appops权限，AppOpsManager.OPSTR_FINE_LOCATION
    static public boolean checkOp(Context context,String opstr){
        int checkOp = 0;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.KITKAT) {
            AppOpsManager appOpsManager = (AppOpsManager) context.getSystemService(Context.APP_OPS_SERVICE);
            checkOp = appOpsManager.checkOp(opstr, Process.myUid(), context.getPackageName());
        }else {
            return true;
        }
//        if(BuildConfig.DEBUG)Log.e("checkOp",opstr+": "+checkOp);
        if (checkOp != AppOpsManager.MODE_ALLOWED) {
            // 权限被拒绝了 .
            return false;
        }
        return true;
    }
}
