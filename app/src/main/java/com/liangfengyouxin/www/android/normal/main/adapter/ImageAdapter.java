package com.liangfengyouxin.www.android.normal.main.adapter;

import android.content.Context;
import android.view.ViewGroup;

import com.liangfengyouxin.www.android.R;
import com.liangfengyouxin.www.android.frame.base.BaseRecyclerAdapter;
import com.liangfengyouxin.www.android.frame.base.BaseRecyclerHolder;
import com.liangfengyouxin.www.android.frame.bean.home.ImageBean;

import java.util.List;

/**
 * Created by lin.woo on 2017/5/18.
 */

public class ImageAdapter extends BaseRecyclerAdapter<ImageBean> {

    public ImageAdapter(Context context, List<ImageBean> mData) {
        super(context, mData);
    }

    @Override
    public BaseRecyclerHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ImageHolder(inflater.inflate(R.layout.item_home_image, parent, false));
    }
}
