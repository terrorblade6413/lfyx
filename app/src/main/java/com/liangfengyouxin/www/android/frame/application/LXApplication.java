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
    public static IWXAPI api;
    private static Context context;

    public static Context getInstance() {
        return context;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        context = this;
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
