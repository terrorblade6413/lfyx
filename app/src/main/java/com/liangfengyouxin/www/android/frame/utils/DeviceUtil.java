package com.liangfengyouxin.www.android.frame.utils;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.util.DisplayMetrics;

import com.liangfengyouxin.www.android.frame.application.LXApplication;

/**
 * Created by wulin on 2017/5/25.
 */
public class DeviceUtil {
    private static final String DEVICE_ALI_UTDID = "deviceAllUtdid";
    private static int sDensityDpi;
    private static float sDensity;
    private static float sScreenHeight;
    private static float sScreenWidth;
    private static float sScreenWidthDpi;
    private static float sScreenHeightDpi;

    private static String mAliUtdid = "";

    /**
     * dp---> px
     */
    public static int dip2px(Context context, float dpValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }

    /**
     * px-->dp
     */
    public static int px2dip(Context context, float pxValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (pxValue / scale + 0.5f);
    }


    private static void getInfoFromDisplayMetrics() {
        DisplayMetrics display = LXApplication.getInstance().getResources()
                .getDisplayMetrics();
        sDensityDpi = display.densityDpi;
        sDensity = display.density;
        sScreenHeight = display.heightPixels;
        sScreenWidth = display.widthPixels;
        sScreenWidthDpi = (sScreenWidth / sDensity);
        sScreenHeightDpi = (sScreenHeight / sDensity);
    }

    public static float getDensity() {
        return sDensity;
    }

    public static float getScreenWidth() {
        getInfoFromDisplayMetrics();
        return sScreenWidth;
    }

    public static float getScreenHeight() {
        getInfoFromDisplayMetrics();
        return sScreenHeight;
    }

    public static Float getScreenWidthDpi() {
        if (sScreenWidthDpi == 0) {
            getInfoFromDisplayMetrics();
        }
        return sScreenWidthDpi;
    }

    public static Float getScreenHeightDpi() {
        if (sScreenHeightDpi == 0) {
            getInfoFromDisplayMetrics();
        }
        return sScreenHeightDpi;
    }

    /**
     * 获取IMEI号
     */
    public static String getDeviceId(Context context) {
        TelephonyManager mTm = (TelephonyManager) context.getSystemService(context.TELEPHONY_SERVICE);
        return mTm.getDeviceId();
    }

    //版本名
    public static String getVersionName(Context context) {
        return getPackageInfo(context).versionName;
    }

    //版本号
    public static int getVersionCode(Context context) {
        return getPackageInfo(context).versionCode;
    }

    private static PackageInfo getPackageInfo(Context context) {
        PackageInfo pi = null;

        try {
            PackageManager pm = context.getPackageManager();
            pi = pm.getPackageInfo(context.getPackageName(),
                    PackageManager.GET_CONFIGURATIONS);

            return pi;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return pi;
    }

    public static boolean isWifi(Context mContext) {
        ConnectivityManager connectivityManager = (ConnectivityManager) mContext
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetInfo = connectivityManager.getActiveNetworkInfo();
        if (activeNetInfo != null
                && activeNetInfo.getType() == ConnectivityManager.TYPE_WIFI) {
            return true;
        }
        return false;
    }

    /**
     * 手机系统版本
     */
    public static String getSdkVersion() {
        return android.os.Build.VERSION.RELEASE;
    }

    /**
     * @param @return
     * @return String
     * @Description 采用阿里方式生成的设备唯一标识
     * @author lin.woo
     */
    public static String getDeviceUtdid() {
        if (TextUtils.isEmpty(mAliUtdid)) {
            String utdid = PreferencesUtils.getString(LXApplication.getInstance(), DEVICE_ALI_UTDID, "");
            if (TextUtils.isEmpty(utdid)) {
                utdid = UTUtdid.instance(LXApplication.getInstance()).getValue();
                if (!TextUtils.isEmpty(utdid)) {
                    PreferencesUtils.putString(LXApplication.getInstance(), DEVICE_ALI_UTDID, utdid);
                }
            }
            mAliUtdid = utdid;
        }
        return mAliUtdid;
    }

//    public static int getNetWorkType() {
//        ConnectivityManager cm = (ConnectivityManager) LXApplication.getInstance().getSystemService(Context.CONNECTIVITY_SERVICE);
//        NetworkInfo networkInfo = cm.getActiveNetworkInfo();
//        if (networkInfo == null) {
//            return GlobalConstants.NO_NETWORK;
//        }
//        int netType = networkInfo.getType();
//        if (netType == ConnectivityManager.TYPE_WIFI) {
//            return GlobalConstants.NETWORK_WIFI;
//        } else if (netType == ConnectivityManager.TYPE_MOBILE) {
//            int type = networkInfo.getSubtype();
//            switch (type) {
//                case TelephonyManager.NETWORK_TYPE_UNKNOWN:
//                    //未知网络类型
//                    break;
//                case TelephonyManager.NETWORK_TYPE_GPRS://(2.5G）移动和联通
//                case TelephonyManager.NETWORK_TYPE_EDGE: //(2.75G)2.5G到3G的过渡    移动和联通
//                case TelephonyManager.NETWORK_TYPE_CDMA://(2G 电信)
//                case TelephonyManager.NETWORK_TYPE_1xRTT://( 2G )
//                case TelephonyManager.NETWORK_TYPE_IDEN://(2G )
//                    return GlobalConstants.NETWORK_2G;
//
//                case TelephonyManager.NETWORK_TYPE_UMTS://(3G)联通
//                case TelephonyManager.NETWORK_TYPE_EVDO_0://( 3G )电信
//                case TelephonyManager.NETWORK_TYPE_EVDO_A://(3.5G) 属于3G过渡
//                case TelephonyManager.NETWORK_TYPE_HSDPA://(3.5G )
//                case TelephonyManager.NETWORK_TYPE_HSUPA: //( 3.5G )
//                case TelephonyManager.NETWORK_TYPE_HSPA://(( 3G )联通
//                case TelephonyManager.NETWORK_TYPE_EVDO_B://3G-3.5G
//                case TelephonyManager.NETWORK_TYPE_EHRPD:  //3G(3G到4G的升级产物)
//                case TelephonyManager.NETWORK_TYPE_HSPAP://3G
//                    return GlobalConstants.NETWORK_3G;
//
//                case TelephonyManager.NETWORK_TYPE_LTE://(4G)
//                    return GlobalConstants.NETWORK_4G;
//
//                default:
//                    return GlobalConstants.NETWORK_UNKNOWN;
//
//            }
//        }
//        return GlobalConstants.NETWORK_UNKNOWN;
//    }
}