package com.liangfengyouxin.www.android.presenter.joinActivity.view;

/**
 * 参与的抽奖列表信息
 * Created by lin.woo on 2017/6/21.
 */

public interface IJoinActivityList {
    void getJoinActListSuccess();

    void getJoinActListFailure(boolean isRequest, int code, String msg);
}
