package com.liangfengyouxin.www.android.presenter.award.view;

/**
 * 新增抽奖--编辑抽奖
 * Created by lin.woo on 2017/6/24.
 */

public interface IAddOrModifyAward {
    void addAwardSuccess();
    void modifyAwardSuccess();
    void addAwardFailure(boolean isRequest, int code, String msg);
    void modifyAwardFailure(boolean isRequest, int code, String msg);
}
