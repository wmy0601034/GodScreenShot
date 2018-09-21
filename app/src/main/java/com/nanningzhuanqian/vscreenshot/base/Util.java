package com.nanningzhuanqian.vscreenshot.base;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.text.TextUtils;
import android.util.Log;

import com.nanningzhuanqian.vscreenshot.adapter.ChangeDetailAdapter;
import com.nanningzhuanqian.vscreenshot.base.util.SPUtils;
import com.nanningzhuanqian.vscreenshot.common.Constant;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class Util {

    /**
     * 先拿到当前时间戳 根据时间戳先转换为yyyy/MM/dd HH:mm:ss的格式 如果是周一以前 则直接显示MM月dd日 如9月9日 如果是昨天
     * 则显示昨天 如果是昨天指一周以前 则显示周X 如果是当天的凌晨0-6点 则显示凌晨XXX 如果是当天上午6-12点 则显示上午XX 如果是下午
     * 则显示 刚刚 XX分钟前
     *
     * @param timeMillis
     * @return
     */
    public static String getDisplayTime(long timeMillis) {
        long currentTimeMillis = System.currentTimeMillis();
        String displayTime = "";
        // 凌晨 昨天 周X 9月9日 1分钟前
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        // 根据当前时间戳
        Date currentDate = new Date(currentTimeMillis);
        String currentDateStr = simpleDateFormat.format(currentDate);
        Date updateDate = new Date(timeMillis);
        String updateDateStr = simpleDateFormat.format(updateDate);
        System.out.println(getYearByTimeStamp(timeMillis) + " " + getMonthByTimeStamp(timeMillis) + " "
                + getDayByTimeStamp(timeMillis) + " " + getHourByTimeStamp(timeMillis) + " "
                + getMinuteByTimeStamp(timeMillis) + " " + getSecondByTimeStamp(timeMillis) + " " + currentDateStr + " "
                + updateDateStr);
        int currentWeekDayPosition = getWeekDayPos(currentDateStr);
        int updateWeekDayPosition = getWeekDayPos(updateDateStr);
        System.out.println("currentWeekDayPosition = " + currentWeekDayPosition + " updateWeekDayPosition = "
                + updateWeekDayPosition);
        if (updateWeekDayPosition > currentWeekDayPosition) {
            // 用X月X日表示
            displayTime = getMonthByTimeStamp(timeMillis) + "月" + getDayByTimeStamp(timeMillis) + "日";
            return displayTime;
        }

        String currentYYYYMMDD = timeStampToYMD(currentTimeMillis);
        String updateYYYYMMDD = timeStampToYMD(timeMillis);
        if (currentYYYYMMDD.equals(updateYYYYMMDD)) {
            // 同一天
            if (currentTimeMillis - timeMillis < 2 * 60 * 1000) {
                long timediff = (currentTimeMillis - timeMillis) / 60 / 1000;
                if (timediff == 0) {
                    displayTime = "刚刚";
                } else {
                    displayTime = (currentTimeMillis - timeMillis) / 60 / 1000 + "分钟前";
                }

            } else {
                int hour = getHourByTimeStamp(timeMillis);
                int min = getMinuteByTimeStamp(timeMillis);
                if (0 <= hour && hour <= 6) {
                    displayTime = "凌晨" + hour + ":" + min;
                } else if (7 < hour && hour <= 12) {
                    displayTime = "早上" + hour + ":" + min;
                } else {
                    displayTime = hour + ":" + min;
                }
            }
            return displayTime;

        } else {
            // 同一周 不同一天
            displayTime = getWeekDay(updateDateStr);
            return displayTime;
        }
    }

    ;

    /*
     * 将时间戳转换为时间
     */
    public static String stampToDate(long timeMillis) {
        String res;
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = new Date(timeMillis);
        res = simpleDateFormat.format(date);
        return res;
    }

    /*
     * 将时间戳转换为时间
     */
    public static String stampToYYYYMMDD(long timeMillis) {
        String res;
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date(timeMillis);
        res = simpleDateFormat.format(date);
        return res;
    }

    public static int getWeekDayPos(String time) {
        String[] weekDays = {"周日", "周一", "周二", "周三", "周四", "周五", "周六"};
        Calendar cal = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        try {
            cal.setTime(sdf.parse(time));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        System.out.println(cal.DAY_OF_WEEK);
        int w = cal.get(Calendar.DAY_OF_WEEK) - 1;
        if (w < 0) {
            w = 0;
        }
        System.out.println(w);
        System.out.println(weekDays[w]);
        return w;
    }

    public static String getWeekDay(String time) {
        String[] weekDays = {"周日", "周一", "周二", "周三", "周四", "周五", "周六"};
        Calendar cal = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        try {
            cal.setTime(sdf.parse(time));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        System.out.println(cal.DAY_OF_WEEK);
        int w = cal.get(Calendar.DAY_OF_WEEK) - 1;
        if (w < 0) {
            w = 0;
        }
        System.out.println(w);
        System.out.println(weekDays[w]);
        return weekDays[w];
    }

    public static String timeStampToDate(long timeStamp) {
        Date date = new Date(timeStamp);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String dateStr = simpleDateFormat.format(date);
        return dateStr;
    }

    public static String timeStampToYMD(long timeStamp) {
        Date date = new Date(timeStamp);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String dateStr = simpleDateFormat.format(date);
        return dateStr;
    }

    public static int getYearByTimeStamp(long timeStamp) {
        String date = timeStampToDate(timeStamp);
        String year = date.substring(0, 4);
        return Integer.parseInt(year);
    }

    public static int getMonthByTimeStamp(long timeStamp) {
        String date = timeStampToDate(timeStamp);
        String month = date.substring(5, 7);
        return Integer.parseInt(month);
    }

    public static int getDayByTimeStamp(long timeStamp) {
        String date = timeStampToDate(timeStamp);
        String day = date.substring(8, 10);
        return Integer.parseInt(day);
    }

    public static int getHourByTimeStamp(long timeStamp) {
        String date = timeStampToDate(timeStamp);
        String hour = date.substring(11, 13);
        return Integer.parseInt(hour);
    }

    public static int getMinuteByTimeStamp(long timeStamp) {
        String date = timeStampToDate(timeStamp);
        String minute = date.substring(14, 16);
        return Integer.parseInt(minute);
    }

    public static int getSecondByTimeStamp(long timeStamp) {
        String date = timeStampToDate(timeStamp);
        String second = date.substring(17, 19);
        return Integer.parseInt(second);
    }

    public static long getTimeMillis(String time){
        SimpleDateFormat sdf = new SimpleDateFormat("yy-MM-dd HH:mm:ss");
        Date d;
        try{
            d = sdf.parse(time);
            long l = d.getTime();
            return l;
        } catch(ParseException e){
            e.printStackTrace();
        }
        return System.currentTimeMillis();
    }

    // 判断两个时间戳是否为同一天
    public static boolean isTwoTimeStampDayEqual(long firstTimeStamp, long secondTimeStamp) {
        if (getYearByTimeStamp(firstTimeStamp) == getYearByTimeStamp(secondTimeStamp)
                && getMonthByTimeStamp(firstTimeStamp) == getMonthByTimeStamp(secondTimeStamp)
                && getDayByTimeStamp(firstTimeStamp) == getDayByTimeStamp(secondTimeStamp)) {
            return true;
        }
        return false;
    }


    public static boolean isPhone(String phone) {
        String regex = "^((13[0-9])|(14[5,7,9])|(15([0-3]|[5-9]))|(166)|(17[0,1,3,5,6,7,8])|(18[0-9])|(19[8|9]))\\d{8}$";
        if (phone.length() != 11) {
            return false;
        } else {
            Pattern p = Pattern.compile(regex);
            Matcher m = p.matcher(phone);
            return m.matches();
        }
    }

    public static int getLocalVersion(Context ctx) {
        int localVersion = 0;
        try {
            PackageInfo packageInfo = ctx.getApplicationContext()
                    .getPackageManager()
                    .getPackageInfo(ctx.getPackageName(), 0);
            localVersion = packageInfo.versionCode;
            Log.i("TAG", "本软件的版本号。。" + localVersion);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return localVersion;
    }

    /**
     * 获取本地软件版本号名称
     */
    public static String getLocalVersionName(Context ctx) {
        String localVersion = "";
        try {
            PackageInfo packageInfo = ctx.getApplicationContext()
                    .getPackageManager()
                    .getPackageInfo(ctx.getPackageName(), 0);
            localVersion = packageInfo.versionName;
            Log.i("TAG", "本软件的版本号。。" + localVersion);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return localVersion;
    }

    /**
     * 是否强制升级
     *
     * @return
     */
    public static final boolean isForceUpdate(Context ctx) {
        boolean isForce = false;
        try {
            String latestVersionName = (String) SPUtils.get(ctx, Constant.KEY_VERSION_NAME, "");
            String versionName = Util.getLocalVersionName(ctx);
            String[] latestVersionPoints = latestVersionName.split(".");
            String[] versionPoints = versionName.split(".");
            if (latestVersionPoints.length != 0 && versionPoints.length != 0) {
                int latestbigVersion = Integer.valueOf(latestVersionPoints[0]);
                int bigVersion = Integer.valueOf(versionPoints[0]);
                if (latestbigVersion > bigVersion) {
                    isForce = true;
                }
            }
        } catch (Exception e) {

        }
        return isForce;
    }

    public static boolean isQQClientAvailable(Context context) {
        final PackageManager packageManager = context.getPackageManager();
        List<PackageInfo> pinfo = packageManager.getInstalledPackages(0);
        if (pinfo != null) {
            for (int i = 0; i < pinfo.size(); i++) {
                String pn = pinfo.get(i).packageName;
                if (pn.equals("com.tencent.mobileqq")) {
                    return true;
                }
            }
        }
        return false;
    }

    public static String getContractQQ(Context context) {
        String qq = (String) SPUtils.get(context, Constant.KEY_CONTRACT_QQ, "");
        if (TextUtils.isEmpty(qq)) {
            return Constant.CONTRACT_QQ;
        } else {
            return qq;
        }
    }

    public static int dip2px(Context context, float dipValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dipValue * scale + 0.5f);
    }

    public static String getChangeDetailNameByType(int type) {
        String name = "";
        switch (type) {
            case ChangeDetailAdapter.TYPE_GROUP_RECEIPTS:
                name = "群收款";
                break;
            case ChangeDetailAdapter.TYPE_WECHAT_TRANSFER_PAY:
                name = "微信转账";
                break;
            case ChangeDetailAdapter.TYPE_WECHAT_TRANSFER_RECEIVED:
                name = "微信转账";
                break;
            case ChangeDetailAdapter.TYPE_WECHAT_RED_PACKET_SEND:
                name = "微信红包";
                break;
            case ChangeDetailAdapter.TYPE_WECHAT_RED_PACKET_RECEIVED:
                name = "微信红包";
                break;
            case ChangeDetailAdapter.TYPE_QRCODE_PAY:
                name = "扫二维码付款";
                break;
            case ChangeDetailAdapter.TYPE_QRCODE_REWARD:
                name = "二维码收款";
                break;
            case ChangeDetailAdapter.TYPE_REFUND:
                name = "退款";
                break;
        }
        return name;
    }

}
