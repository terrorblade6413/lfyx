package com.liangfengyouxin.www.android.normal.more.joinActivity.adapter;

import android.content.Context;
import android.view.ViewGroup;

import com.liangfengyouxin.www.android.R;
import com.liangfengyouxin.www.android.frame.base.BaseRecyclerAdapter;
import com.liangfengyouxin.www.android.frame.base.BaseRecyclerHolder;
import com.liangfengyouxin.www.android.frame.bean.award.AwardListBean;
import com.liangfengyouxin.www.android.normal.award.adapter.AwardHolder;

import java.util.List;

/**
 * Created by lin.woo on 2017/6/6.
 */

public class JoinActAdapter extends BaseRecyclerAdapter<AwardListBean> {
    public JoinActAdapter(Context context, List<AwardListBean> data) {
        super(context, data);
    }

    @Override
    public BaseRecyclerHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new JoinActHolder(inflater.inflate(R.layout.item_join_act, parent, false));
    }
}
