package com.liangfengyouxin.www.android.presenter.award.view;

import com.liangfengyouxin.www.android.frame.bean.award.AwardListBean;

/**
 * Created by lin.woo on 2017/6/24.
 */

public interface IAwardDetail {
    void AwardDetailSuccess(AwardListBean bean);

    void AwardDetailFailure(boolean isRequest, int code, String msg);
}
