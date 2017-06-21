package com.liangfengyouxin.www.android.presenter.joinActivity;

import android.content.Context;

import com.liangfengyouxin.www.android.frame.bean.WrapperBean2;
import com.liangfengyouxin.www.android.frame.bean.joinActivity.JoinActBean;
import com.liangfengyouxin.www.android.frame.network.ApiExecutor;
import com.liangfengyouxin.www.android.frame.utils.DeviceUtil;
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
    public int page = 0;
    public int limit = 10;

    public JoinActivityListPresenter(Context context, IJoinActivityList iView) {
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

        param.put("api_dev", DeviceUtil.getDeviceUtdid());
        param.put("uid", "19");

        ApiExecutor.getInstance2().joinActList(param)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<WrapperBean2<List<JoinActBean>>>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(WrapperBean2<List<JoinActBean>> joinActBeanWrapperBean2) {

                    }
                });
    }
}
