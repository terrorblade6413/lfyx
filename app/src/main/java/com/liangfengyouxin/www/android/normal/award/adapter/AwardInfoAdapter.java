package com.liangfengyouxin.www.android.normal.award.adapter;

import android.content.Context;
import android.view.ViewGroup;

import com.liangfengyouxin.www.android.R;
import com.liangfengyouxin.www.android.frame.base.BaseRecyclerAdapter;
import com.liangfengyouxin.www.android.frame.base.BaseRecyclerHolder;
import com.liangfengyouxin.www.android.frame.bean.award.AwardListBean;

import java.util.List;

/**
 * Created by lin.woo on 2017/6/6.
 */

public class AwardInfoAdapter extends BaseRecyclerAdapter<AwardListBean> {
    public AwardInfoAdapter(Context context, List<AwardListBean> data) {
        super(context, data);
    }

    @Override
    public BaseRecyclerHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new AwardInfoHolder(inflater.inflate(R.layout.item_award_info, parent, false));
    }
}
