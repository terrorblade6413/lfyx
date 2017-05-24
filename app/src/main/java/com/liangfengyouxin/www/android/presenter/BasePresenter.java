package com.liangfengyouxin.www.android.presenter;

import android.content.Context;

/**
 * Created by lin.woo on 2017/5/19.
 */

public class BasePresenter<T> {
    protected T iView;
    protected Context mContext;

    protected BasePresenter(Context context, T iView) {
        this.mContext = context;
        this.iView = iView;
    }
}
