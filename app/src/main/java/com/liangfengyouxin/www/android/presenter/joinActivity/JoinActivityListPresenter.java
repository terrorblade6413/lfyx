package com.liangfengyouxin.www.android.presenter.joinActivity;

import android.content.Context;

import com.liangfengyouxin.www.android.frame.bean.WrapperBean2;
import com.liangfengyouxin.www.android.frame.bean.joinActivity.JoinActBean;
import com.liangfengyouxin.www.android.frame.network.ApiExecutor;
import com.liangfengyouxin.www.android.presenter.BasePresenter;
import com.liangfengyouxin.www.android.presenter.joinActivity.view.IJoinActivityList;

import java.util.List;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * 参与的活动
 * Created by lin.woo on 2017/6/21.
 */

public class JoinActivityListPresenter extends BasePresenter<IJoinActivityList> {
    public int page = 1;
    public int limit = 10;
    private JoinActBean bean;

    public JoinActivityListPresenter(Context context, IJoinActivityList iView) {
        super(context, iView);
        setSign("Gamble", "mygamblejoined");
        put("page_size", limit);
    }

    public void getFirst() {
        put("page", page = 1);
        joinActList();
    }

    public void getMore() {
        put("page", page += 1);
        joinActList();
    }

    private void joinActList() {

        put("uid", "19");

        ApiExecutor.getInstance2().joinActList(param)
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
                    public void onNext(WrapperBean2<JoinActBean> data) {
                        if (data.status == 200) {
                            bean = data.data;
                            if (bean.total_record <= bean.page * bean.page_size)
                                iView.getJoinActListSuccess(bean.list, false);
                            else
                                iView.getJoinActListSuccess(bean.list, true);
                        } else
                            iView.getJoinActListFailure(false, data.status, data.msg);
                    }
                });
    }
}
