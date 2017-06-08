package com.liangfengyouxin.www.android.frame.bean.award;

import android.content.Context;
import android.view.ViewGroup;

import com.liangfengyouxin.www.android.R;
import com.liangfengyouxin.www.android.frame.base.BaseRecyclerAdapter;
import com.liangfengyouxin.www.android.frame.base.BaseRecyclerHolder;
import com.liangfengyouxin.www.android.normal.award.adapter.AwardHolder;

import java.util.List;

/**
 * Created by lin.woo on 2017/6/6.
 */

public class AwardAdapter extends BaseRecyclerAdapter<AwardListBean> {
    public AwardAdapter(Context context, List<AwardListBean> data) {
        super(context, data);
    }

    @Override
    public BaseRecyclerHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new AwardHolder(inflater.inflate(R.layout.item_award, null));
    }
}
