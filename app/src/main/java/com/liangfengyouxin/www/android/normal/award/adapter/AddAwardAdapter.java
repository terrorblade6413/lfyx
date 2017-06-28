package com.liangfengyouxin.www.android.normal.award.adapter;

import android.content.Context;
import android.view.ViewGroup;

import com.liangfengyouxin.www.android.R;
import com.liangfengyouxin.www.android.frame.base.BaseRecyclerAdapter;
import com.liangfengyouxin.www.android.frame.base.BaseRecyclerHolder;
import com.liangfengyouxin.www.android.frame.bean.award.AddAwardBean;

import java.util.List;

/**
 * Created by lin.woo on 2017/6/24.
 */

public class AddAwardAdapter extends BaseRecyclerAdapter<AddAwardBean> {
    public AddAwardAdapter(Context context, List<AddAwardBean> data) {
        super(context, data);
    }

    @Override
    public BaseRecyclerHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new AddAwardHolder(mContext,inflater.inflate(R.layout.item_add_award,parent,false));
    }
}
