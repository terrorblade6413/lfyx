package com.liangfengyouxin.www.android.presenter.joinActivity;

import android.content.Context;

import com.liangfengyouxin.www.android.frame.bean.WrapperBean2;
import com.liangfengyouxin.www.android.frame.bean.joinActivity.JoinActBean;
import com.liangfengyouxin.www.android.frame.network.ApiExecutor;
import com.liangfengyouxin.www.android.presenter.BasePresenter;
import com.liangfengyouxin.www.android.presenter.joinActivity.view.IJoinActDetail;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * 参与的活动
 * Created by lin.woo on 2017/6/21.
 */

public class JoinActDetailPresenter extends BasePresenter<IJoinActDetail> {
    public JoinActDetailPresenter(Context context, IJoinActDetail iView) {
        super(context, iView);
        setSign("Gamble", "gambledetail");
    }

    public void joinActDetail(String id) {

        put("id",id);

        ApiExecutor.getInstance2().joinActDetail(param)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<WrapperBean2<JoinActBean>>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(WrapperBean2<JoinActBean> joinActBeanWrapperBean2) {

                    }
                });
    }
}
