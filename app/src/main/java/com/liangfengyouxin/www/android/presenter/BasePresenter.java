package com.liangfengyouxin.www.android.presenter;

import android.content.Context;

import com.liangfengyouxin.www.android.frame.utils.md5.MD5;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by lin.woo on 2017/5/19.
 */

public class BasePresenter<T> {
    protected T iView;
    protected Context mContext;
    protected HashMap<String, Object> param;

    public BasePresenter(Context context, T iView) {
        this.mContext = context;
        this.iView = iView;
    }

    protected HashMap<String, Object> put(String key, Object value) {
        if (param == null) {
            param = new HashMap<>();
        }
        param.put(key,value);
        return param;
    }
    protected void setSign(String type,String method){
        put("api_sign", MD5.hexdigest(type+method));
        put("api_type", type);
        put("api_method", method);
    }
}
