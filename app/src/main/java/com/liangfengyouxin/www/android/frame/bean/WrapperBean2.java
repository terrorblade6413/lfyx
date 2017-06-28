package com.liangfengyouxin.www.android.frame.bean;

/**
 * Created by lin.woo on 2017/6/14.
 */

public class WrapperBean2<T> extends BaseBean {
    public int status;
    public T data;
    public String msg;

    private boolean isSuccess;

    public boolean getIsSuccess() {
        if (status == 200)
            return true;
        else
            return false;
    }

}
