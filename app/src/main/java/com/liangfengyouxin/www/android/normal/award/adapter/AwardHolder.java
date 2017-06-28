package com.liangfengyouxin.www.android.normal.award.adapter;

import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.liangfengyouxin.www.android.R;
import com.liangfengyouxin.www.android.frame.base.BaseRecyclerHolder;
import com.liangfengyouxin.www.android.frame.bean.award.AwardListBean;
import com.liangfengyouxin.www.android.frame.bean.joinActivity.JoinActBean;
import com.liangfengyouxin.www.android.frame.utils.DateUtils;

import java.util.List;

/**
 * Created by lin.woo on 2017/6/6.
 */

public class AwardHolder extends BaseRecyclerHolder<AwardListBean> {
    private TextView tvTime;
    private TextView tvRate;
    private TextView tvLotteryNumber;
    private TextView tvWinningNumber;
    private SimpleDraweeView logo;

    private AwardListBean bean;

    public AwardHolder(View itemView) {
        super(itemView);
        tvTime = (TextView) itemView.findViewById(R.id.tv_date);
        tvRate = (TextView) itemView.findViewById(R.id.tv_rate);
        tvLotteryNumber = (TextView) itemView.findViewById(R.id.tv_lottery_number);
        logo = (SimpleDraweeView) itemView.findViewById(R.id.pic_sdv);
        tvWinningNumber = (TextView) itemView.findViewById(R.id.tv_winning_number);
    }

    @Override
    protected void initView(int position, List<AwardListBean> data) {
        bean = data.get(position);
        tvTime.setText(DateUtils.unixTimesToDate(Long.valueOf(bean.addtime) * 1000, DateUtils.DATE_SMALL_STR));
        //未使用
        int unused = Integer.valueOf(TextUtils.isEmpty(bean.prize_overplus) ? bean.gamblecode_num : bean.prize_overplus);
        //总数
        int total = Integer.valueOf(TextUtils.isEmpty(bean.gamblecode_num) ? "0" : bean.gamblecode_num);
        tvLotteryNumber.setText(unused + "/" + bean.gamblecode_num);
        logo.setImageURI(bean.url);
        tvRate.setText("中奖率: 1/" + bean.win_percent);

        tvWinningNumber.setText("中奖次数: " + (total - unused));
    }
}
