package com.liangfengyouxin.www.android.normal.more.joinActivity.adapter;

import android.text.TextUtils;
import android.view.TextureView;
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

public class JoinActHolder extends BaseRecyclerHolder<JoinActBean> {
    private TextView tvTime;
    private TextView tvStatus;
    private TextView tvAward;
    private TextView tvGet;
    private SimpleDraweeView logo;

    private JoinActBean bean;
    public JoinActHolder(View itemView) {
        super(itemView);
        tvTime = (TextView) itemView.findViewById(R.id.tv_time);
        tvStatus = (TextView) itemView.findViewById(R.id.tv_status);
        tvAward = (TextView) itemView.findViewById(R.id.tv_award);
        logo = (SimpleDraweeView) itemView.findViewById(R.id.fresco_logo);
        tvGet = (TextView) itemView.findViewById(R.id.tv_get);
    }

    @Override
    protected void initView(int position, List<JoinActBean> data) {
        bean = data.get(position);
//        tvTime.setText(DateUtils.unixTimesToDate(Long.valueOf(bean.start_time)*1000,DateUtils.DATE_FULL));
//        tvAward.setText("奖品："+ (TextUtils.isEmpty(bean.custom)?"":bean.custom));
    }
}
