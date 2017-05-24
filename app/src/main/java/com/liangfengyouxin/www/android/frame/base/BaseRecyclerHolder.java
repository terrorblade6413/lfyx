package com.liangfengyouxin.www.android.frame.base;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import java.util.List;

/**
 * Created by lin.woo on 2017/5/19.
 */

public abstract class BaseRecyclerHolder<T> extends RecyclerView.ViewHolder {
    protected Context mContext;
    public BaseRecyclerHolder(View itemView) {
        super(itemView);
        mContext = itemView.getContext();
    }
    protected abstract void initView(int position, List<T> data);
}
