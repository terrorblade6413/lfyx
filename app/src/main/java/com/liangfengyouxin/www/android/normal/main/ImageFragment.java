package com.liangfengyouxin.www.android.normal.main;

import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.liangfengyouxin.www.android.R;
import com.liangfengyouxin.www.android.frame.base.BaseFragment;
import com.liangfengyouxin.www.android.frame.bean.home.ImageBean;
import com.liangfengyouxin.www.android.frame.view.MSwipeRefreshLayout;
import com.liangfengyouxin.www.android.frame.view.recyclerview.LoadMoreRecyclerView;
import com.liangfengyouxin.www.android.frame.view.recyclerview.OnLoadMoreListener;
import com.liangfengyouxin.www.android.normal.main.adapter.ImageAdapter;
import com.liangfengyouxin.www.android.presenter.GetImagePresenter;
import com.liangfengyouxin.www.android.presenter.view.IGetImageView;

import java.util.List;

/**
 * 首页图片
 * Created by lin.woo on 2017/5/16.
 */

public class ImageFragment extends BaseFragment implements IGetImageView {

    private LoadMoreRecyclerView rView;
    private MSwipeRefreshLayout swipe;
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
        rView = (LoadMoreRecyclerView) findViewById(R.id.rec_list_view);
        rView.setLayoutManager(new LinearLayoutManager(getContext()));
        rView.setAdapter(adapter);
        swipe = (MSwipeRefreshLayout) findViewById(R.id.swipe_refresh);
        swipe.setRefreshing(true);
    }

    @Override
    protected void initListener() {
        swipe.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                presenter.getImageFirst();
            }
        });
        rView.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void loadMore() {
                presenter.getImageMore(adapter.getData().get(adapter.getData().size() - 1).Di);
            }
        });
    }

    @Override
    protected void initData() {
        presenter.getImageFirst();
    }

    @Override
    public void getImageListSuccess(List<ImageBean> list) {
        swipe.completeRefresh();
        if (list.size() < 10)
            rView.setHasLoadMore(false);
        else
            rView.setHasLoadMore(true);
        if (!presenter.isLoadMore())
            adapter.getData().clear();

        adapter.getData().addAll(list);
        adapter.notifyDataSetChanged();
    }
}
