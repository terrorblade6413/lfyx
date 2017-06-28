package com.liangfengyouxin.www.android.normal.award.adapter;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import com.liangfengyouxin.www.android.R;
import com.liangfengyouxin.www.android.frame.base.BaseRecyclerHolder;
import com.liangfengyouxin.www.android.frame.bean.award.AddAwardBean;
import com.liangfengyouxin.www.android.normal.award.AwardCreateActivity;

import java.util.List;

/**
 * Created by lin.woo on 2017/6/24.
 */

public class AddAwardHolder extends BaseRecyclerHolder<AddAwardBean> {
    private Context context;
    private TextView name;//礼品名字
    private TextView number;//数量
    private TextView tvDel;//移除礼品

    private AddAwardBean bean;

    public AddAwardHolder(Context context, View itemView) {
        super(itemView);
        this.context = context;
        name = (TextView) itemView.findViewById(R.id.tv_name);
        number = (TextView) itemView.findViewById(R.id.tv_number);
        tvDel = (TextView) itemView.findViewById(R.id.tv_del);
    }

    @Override
    protected void initView(final int position, List<AddAwardBean> data) {
        bean = data.get(position);
        name.setText(bean.prize_name);
        number.setText(bean.prize_number+"");
        if (bean.isShowDel)
            tvDel.setVisibility(View.VISIBLE);
        else
            tvDel.setVisibility(View.GONE);

        tvDel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((AwardCreateActivity)context).delAward(position);
            }
        });


    }
}
