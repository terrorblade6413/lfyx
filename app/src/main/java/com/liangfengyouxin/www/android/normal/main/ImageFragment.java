package com.liangfengyouxin.www.android.normal.main;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.liangfengyouxin.www.android.R;
import com.liangfengyouxin.www.android.frame.base.BaseFragment;
import com.liangfengyouxin.www.android.frame.bean.home.ImageBean;
import com.liangfengyouxin.www.android.normal.main.adapter.ImageAdapter;
import com.liangfengyouxin.www.android.presenter.GetImagePresenter;
import com.liangfengyouxin.www.android.presenter.view.IGetImageView;

import java.util.List;

/**
 * 首页图片
 * Created by lin.woo on 2017/5/16.
 */

public class ImageFragment extends BaseFragment implements IGetImageView {

    private RecyclerView rView;
    private ImageAdapter adapter;
    private GetImagePresenter presenter;

    @Override
    protected int setBody() {
        return R.layout.fragment_image;
    }

    @Override
    protected void initValue() {

        adapter = new ImageAdapter(getContext(), null);
        presenter = new GetImagePresenter(getContext(), this);
    }

    @Override
    protected void initWidget() {
        rView = (RecyclerView) findViewById(R.id.rec_list_view);
        rView.setLayoutManager(new LinearLayoutManager(getContext()));
        rView.setAdapter(adapter);
    }

    @Override
    protected void initListener() {

    }

    @Override
    protected void initData() {
        presenter.getImageList();
    }

    @Override
    public void getImageListSuccess(List<ImageBean> list) {
        adapter.getData().addAll(list);
        adapter.notifyDataSetChanged();
    }
}
