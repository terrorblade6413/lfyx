package com.liangfengyouxin.www.android.presenter.award;

import android.content.Context;

import com.liangfengyouxin.www.android.frame.application.LXApplication;
import com.liangfengyouxin.www.android.frame.bean.ListWrapperBean;
import com.liangfengyouxin.www.android.frame.bean.WrapperBean2;
import com.liangfengyouxin.www.android.frame.bean.award.AwardListBean;
import com.liangfengyouxin.www.android.frame.network.ApiExecutor;
import com.liangfengyouxin.www.android.presenter.BasePresenter;
import com.liangfengyouxin.www.android.presenter.award.view.IAwardList;

import java.util.List;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * 抽奖列表
 * Created by lin.woo on 2017/6/21.
 */

public class AwardListPresenter extends BasePresenter<IAwardList> {
    public int page = 0;
    public int limit = 10;

    public AwardListPresenter(Context context, IAwardList iView) {
        super(context, iView);
        setSign("Gamble", "mygamble");
    }

    public void getFirst() {
        joinActList();
    }

    public void getMore() {
        joinActList();
    }

    private void joinActList() {

        put("uid", LXApplication.uid);

        ApiExecutor.getInstance2().awardList(param)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<WrapperBean2<ListWrapperBean<AwardListBean>>>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(WrapperBean2<ListWrapperBean<AwardListBean>> data) {
                        if (data.status == 200)
                            iView.getAwardListSuccess(data.data.list);
                        else
                            iView.getAwardListFailure(false, data.status, data.msg);
                    }
                });
    }
}
