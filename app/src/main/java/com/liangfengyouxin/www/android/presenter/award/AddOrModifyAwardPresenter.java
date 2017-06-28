package com.liangfengyouxin.www.android.presenter.award;

import android.content.Context;

import com.liangfengyouxin.www.android.frame.application.LXApplication;
import com.liangfengyouxin.www.android.frame.bean.WrapperBean2;
import com.liangfengyouxin.www.android.frame.bean.award.AddAwardBean;
import com.liangfengyouxin.www.android.frame.bean.award.AwardListBean;
import com.liangfengyouxin.www.android.frame.network.ApiExecutor;
import com.liangfengyouxin.www.android.presenter.BasePresenter;
import com.liangfengyouxin.www.android.presenter.award.view.IAddOrModifyAward;

import java.util.ArrayList;
import java.util.List;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by lin.woo on 2017/6/24.
 */

public class AddOrModifyAwardPresenter extends BasePresenter<IAddOrModifyAward> {
    private List<String> awardName;
    private List<String> awardNumber;

    private boolean isAdd;

    public AddOrModifyAwardPresenter(Context context, IAddOrModifyAward iView) {
        super(context, iView);
        setSign("Gamble", "addgamble");
        put("uid", LXApplication.uid);
    }

    public void addAward(int limit, int prizePercent, int number, boolean needCode, long startTime, long endTime, List<AddAwardBean> aBean) {
        isAdd = true;
        //0新增
        put("id", 0);
        execute(limit, prizePercent, number, needCode, startTime, endTime, aBean);
    }

    public void modifyAward(String id, int limit, int prizePercent, int number, boolean needCode, long startTime, long endTime, List<AddAwardBean> aBean) {
        //0新增
        put("id", id);
        execute(limit, prizePercent, number, needCode, startTime, endTime, aBean);
    }

    private void execute(int limit, int prizePercent, int number, boolean needCode, long startTime, long endTime, List<AddAwardBean> aBean) {
        //不限制-100
        if (limit == 0)
            put("gamble_limit", -100);
        else
            put("gamble_limit", limit);
        put("win_percent", prizePercent);
        put("gamblecode_num", number);
        put("start_time", startTime);
        put("end_time", endTime);
        if (needCode)
            put("is_needcode", 1);
        else
            put("is_needcode", 0);

        //设置奖品 和 奖品数量
        if (awardName == null) {
            awardName = new ArrayList<>();
            awardNumber = new ArrayList<>();
        } else {
            awardName.clear();
            awardNumber.clear();
        }
        if (aBean != null) {
            for (int i = 0; i < aBean.size(); i++) {
                awardName.add(aBean.get(i).prize_name);
                awardNumber.add(aBean.get(i).prize_number + "");
            }
        }
        put("prize_name", awardName);
        put("prize_num", awardNumber);

        ApiExecutor.getInstance2().addOrModifyAward(param)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<WrapperBean2>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(WrapperBean2 response) {
                        if (response.getIsSuccess()) {
                            if (isAdd)
                                iView.addAwardSuccess();
                            else
                                iView.modifyAwardSuccess();
                        } else {
                            if (isAdd)
                                iView.addAwardFailure(false, response.status, response.msg);
                            else
                                iView.modifyAwardFailure(false, response.status, response.msg);
                        }
                    }
                });

    }
}
