package com.liangfengyouxin.www.android.presenter.award;

import android.content.Context;

import com.liangfengyouxin.www.android.frame.application.LXApplication;
import com.liangfengyouxin.www.android.frame.bean.WrapperBean2;
import com.liangfengyouxin.www.android.frame.bean.award.AddAwardBean;
import com.liangfengyouxin.www.android.frame.bean.award.AwardListBean;
import com.liangfengyouxin.www.android.frame.network.ApiExecutor;
import com.liangfengyouxin.www.android.presenter.BasePresenter;
import com.liangfengyouxin.www.android.presenter.award.view.IAddOrModifyAward;
import com.liangfengyouxin.www.android.presenter.award.view.IAwardDetail;

import java.util.ArrayList;
import java.util.List;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by lin.woo on 2017/6/24.
 */

public class AwardDetailPresenter extends BasePresenter<IAwardDetail> {

    public AwardDetailPresenter(Context context, IAwardDetail iView) {
        super(context, iView);
        setSign("Gamble", "gambledetail");
    }

    public void getDetail(String id) {
        put("id",id);
        ApiExecutor.getInstance2().awardDetail(param)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<WrapperBean2<AwardListBean>>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(WrapperBean2<AwardListBean> listWrapperBean2) {

                    }
                });

    }
}
