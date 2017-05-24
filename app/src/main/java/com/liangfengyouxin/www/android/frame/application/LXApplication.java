package com.liangfengyouxin.www.android.frame.application;

import android.app.Application;
import android.content.Context;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.liangfengyouxin.www.android.wxapi.WXUtils;
import com.tencent.mm.opensdk.openapi.IWXAPI;

/**
 * Created by lin.woo on 2017/5/15.
 */

public class LXApplication extends Application {

    private static LXApplication lxApplication;
    public static IWXAPI api;

    public static Context getInstance() {
        if (lxApplication == null) {
            lxApplication = new LXApplication();
        }
        return lxApplication;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        initFresco();
        initWX();
    }


    private void initFresco() {
        Fresco.initialize(this);
    }

    private void initWX() {
        api = WXUtils.getInstance().regToWx(this);
    }
}
