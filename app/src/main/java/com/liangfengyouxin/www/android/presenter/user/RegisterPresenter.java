package com.liangfengyouxin.www.android.presenter.user;

import android.content.Context;

import com.liangfengyouxin.www.android.frame.bean.WrapperBean;
import com.liangfengyouxin.www.android.frame.bean.WrapperBean2;
import com.liangfengyouxin.www.android.frame.bean.home.ImageBean;
import com.liangfengyouxin.www.android.frame.bean.user.RegisterBean;
import com.liangfengyouxin.www.android.frame.network.ApiExecutor;
import com.liangfengyouxin.www.android.frame.utils.md5.MD5;
import com.liangfengyouxin.www.android.presenter.BasePresenter;
import com.liangfengyouxin.www.android.presenter.user.view.IRegisterView;
import com.liangfengyouxin.www.android.presenter.view.IGetImageView;

import java.util.List;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by lin.woo on 2017/5/5.
 */

public class RegisterPresenter extends BasePresenter<IRegisterView> {
    private boolean isLoadMore;
    public RegisterPresenter(Context context, IRegisterView iView) {
        super(context, iView);
    }

    public void register() {
        String url = MD5.hexdigest("Useradduser");
        ApiExecutor.getInstance2().register("test","15882306413","123456",1,url)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<WrapperBean2<RegisterBean>>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(WrapperBean2<RegisterBean> registerBeanWrapperBean2) {

                    }
                });
    }

}
