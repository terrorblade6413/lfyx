package com.liangfengyouxin.www.android.normal.main.adapter;

import android.util.Log;
import android.view.View;

import com.facebook.drawee.view.SimpleDraweeView;
import com.liangfengyouxin.www.android.R;
import com.liangfengyouxin.www.android.frame.base.BaseRecyclerHolder;
import com.liangfengyouxin.www.android.frame.bean.home.ImageBean;

import java.util.List;

/**
 * Created by lin.woo on 2017/5/19.
 */

public class ImageHolder extends BaseRecyclerHolder<ImageBean> {
    private SimpleDraweeView picture;
    public ImageHolder(View itemView) {
        super(itemView);
        picture = (SimpleDraweeView) itemView.findViewById(R.id.picture);
    }

    @Override
    protected void initView(int position, List<ImageBean> data) {
        picture.setImageURI(data.get(position).Lujing);
        Log.d("picture===",picture.getWidth()+"");
        Log.d("picture===",picture.getHeight()+"");
    }
}
