package com.liangfengyouxin.www.android.presenter.user;

import android.content.Context;
import android.util.Log;

import com.liangfengyouxin.www.android.frame.bean.WrapperBean;
import com.liangfengyouxin.www.android.frame.bean.WrapperBean2;
import com.liangfengyouxin.www.android.frame.bean.home.ImageBean;
import com.liangfengyouxin.www.android.frame.bean.user.RegisterBean;
import com.liangfengyouxin.www.android.frame.network.ApiExecutor;
import com.liangfengyouxin.www.android.frame.utils.DeviceUtil;
import com.liangfengyouxin.www.android.frame.utils.md5.MD5;
import com.liangfengyouxin.www.android.presenter.BasePresenter;
import com.liangfengyouxin.www.android.presenter.user.view.IRegisterView;
import com.liangfengyouxin.www.android.presenter.view.IGetImageView;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by lin.woo on 2017/5/5.
 */

public class RegisterPresenter extends BasePresenter<IRegisterView> {
    //个人
    private static final int USER_TYPE_PERSONAGE = 1;
    //公司
    private static final int USER_TYPE_COMPANY = 2;

    public RegisterPresenter(Context context, IRegisterView iView) {
        super(context, iView);
        setSign("User","adduser");
    }


    public void registerPersonage(String phone, String pw) {
        put("user_type", USER_TYPE_PERSONAGE);
        register(phone, pw);
    }

    public void registerCompany(String phone, String pw) {
        put("user_type", USER_TYPE_COMPANY);
        register(phone, pw);
    }

    private void register(String phone, String pw) {
        put("shouji", phone);
        put("password", pw);

        ApiExecutor.getInstance2().register(param)
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
