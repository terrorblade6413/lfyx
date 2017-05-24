package com.liangfengyouxin.www.android.presenter;

import android.content.Context;

import com.liangfengyouxin.www.android.frame.bean.WrapperBean;
import com.liangfengyouxin.www.android.frame.bean.home.ImageBean;
import com.liangfengyouxin.www.android.frame.network.ApiExecutor;
import com.liangfengyouxin.www.android.presenter.view.IGetImageView;

import java.util.List;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by lin.woo on 2017/5/5.
 */

public class GetImagePresenter extends BasePresenter<IGetImageView> {
    public GetImagePresenter(Context context, IGetImageView iView) {
        super(context, iView);
    }

    public void getImageList() {

        ApiExecutor.getInstance().getImageList("t", "", "y")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<WrapperBean<List<ImageBean>>>() {
                    @Override
                    public void onCompleted() {
                    }

                    @Override
                    public void onError(Throwable e) {
                        e.printStackTrace();
                    }

                    @Override
                    public void onNext(WrapperBean<List<ImageBean>> bean) {
                        iView.getImageListSuccess(bean.wtys);
                    }
                });
    }

}
