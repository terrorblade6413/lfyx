package com.liangfengyouxin.www.android.presenter.award.view;

import com.liangfengyouxin.www.android.frame.bean.award.AwardListBean;
import com.liangfengyouxin.www.android.frame.bean.joinActivity.JoinActBean;

import java.util.List;

/**
 * 参与的抽奖列表信息
 * Created by lin.woo on 2017/6/21.
 */

public interface IAwardList {
    void getAwardListSuccess(List<AwardListBean> list);

    void getAwardListFailure(boolean isRequest, int code, String msg);
}
