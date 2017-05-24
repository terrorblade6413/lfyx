package com.liangfengyouxin.www.android.frame.base;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.liangfengyouxin.www.android.frame.base.BaseRecyclerHolder;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lin.woo on 2017/5/19.
 */

public class BaseRecyclerAdapter<T> extends RecyclerView.Adapter<BaseRecyclerHolder> {
    protected Context mContext;
    protected LayoutInflater inflater;
    protected List<T> mData;

    public BaseRecyclerAdapter(Context context, List<T> data) {
        this.mContext = context;
        inflater = LayoutInflater.from(context);
        mData = new ArrayList<>();
        if (data != null) {
            this.mData = data;
        }
    }

    @Override
    public BaseRecyclerHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(BaseRecyclerHolder holder, int position) {
        holder.initView(position, mData);
    }

    @Override
    public int getItemCount() {
        return mData == null ? 0 : mData.size();
    }

    public List<T> getData() {
        return mData;
    }

    public void setData(List<T> data) {
        if (data != null)
            this.mData = data;
    }

}
