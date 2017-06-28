package com.liangfengyouxin.www.android.presenter.joinActivity.view;

import com.liangfengyouxin.www.android.frame.bean.joinActivity.JoinActBean;
import com.liangfengyouxin.www.android.frame.bean.joinActivity.JoinActItemBean;

import java.util.List;

/**
 * 参与的抽奖列表信息
 * Created by lin.woo on 2017/6/21.
 */

public interface IJoinActivityList {
    void getJoinActListSuccess(List<JoinActItemBean> list, boolean isMore);

    void getJoinActListFailure(boolean isRequest, int code, String msg);
}
