package com.liangfengyouxin.www.android.presenter.joinActivity.view;

/**
 * 参与的抽奖详情
 * Created by lin.woo on 2017/6/21.
 */

public interface IJoinActDetail {
    void getJoinActSuccess();

    void getJoinActFailure(boolean isRequest, int code, String msg);
}
