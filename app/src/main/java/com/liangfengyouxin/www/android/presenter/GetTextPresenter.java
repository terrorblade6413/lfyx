package com.liangfengyouxin.www.android.presenter;

import android.content.Context;

import com.liangfengyouxin.www.android.frame.bean.WrapperBean;
import com.liangfengyouxin.www.android.frame.bean.home.ImageBean;
import com.liangfengyouxin.www.android.frame.bean.home.TextBean;
import com.liangfengyouxin.www.android.frame.network.ApiExecutor;
import com.liangfengyouxin.www.android.presenter.view.IGetImageView;
import com.liangfengyouxin.www.android.presenter.view.IGetTextView;

import java.util.List;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by lin.woo on 2017/5/5.
 */

public class GetTextPresenter extends BasePresenter<IGetTextView> {
    public GetTextPresenter(Context context, IGetTextView iView) {
        super(context, iView);
    }

    public void getTextList() {

        ApiExecutor.getInstance().getTextList("w", "", "y")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<WrapperBean<List<TextBean>>>() {
                    @Override
                    public void onCompleted() {
                    }

                    @Override
                    public void onError(Throwable e) {
                        e.printStackTrace();
                    }

                    @Override
                    public void onNext(WrapperBean<List<TextBean>> bean) {
                        iView.getTextListSuccess(bean.wtys);
                    }
                });
    }

}
