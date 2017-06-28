package com.liangfengyouxin.www.android.normal.award.adapter;

import android.text.TextUtils;
import android.view.TextureView;
import android.view.View;
import android.widget.TextView;

import com.liangfengyouxin.www.android.R;
import com.liangfengyouxin.www.android.frame.base.BaseRecyclerHolder;
import com.liangfengyouxin.www.android.frame.bean.award.AddAwardBean;
import com.liangfengyouxin.www.android.frame.bean.award.AwardListBean;

import java.util.List;

/**
 * Created by lin.woo on 2017/6/6.
 */

public class AwardInfoHolder extends BaseRecyclerHolder<AddAwardBean> {
    private TextView tvName;
    private TextView tvNumber;
    private AddAwardBean bean;
    public AwardInfoHolder(View itemView) {
        super(itemView);
        tvName = (TextView) itemView.findViewById(R.id.tv_award_name);
        tvNumber = (TextView) itemView.findViewById(R.id.tv_number);
    }

    @Override
    protected void initView(int position, List<AddAwardBean> data) {
        bean = data.get(position);
        tvName.setText(TextUtils.isEmpty(bean.prize_name)?"暂无名字":bean.prize_name);
        tvNumber.setText((TextUtils.isEmpty(bean.prize_overplus)?"0":bean.prize_overplus)+"/"
                +bean.prize_number);
    }
}
