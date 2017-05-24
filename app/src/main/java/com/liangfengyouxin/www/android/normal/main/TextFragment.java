package com.liangfengyouxin.www.android.normal.main;

import android.app.Activity;
import android.content.Intent;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.liangfengyouxin.www.android.R;
import com.liangfengyouxin.www.android.frame.base.BaseFragment;
import com.liangfengyouxin.www.android.frame.base.HeaderAndFooterRecyclerViewAdapter;
import com.liangfengyouxin.www.android.frame.bean.home.TextBean;
import com.liangfengyouxin.www.android.frame.view.MSwipeRefreshLayout;
import com.liangfengyouxin.www.android.frame.view.recyclerview.ILoadMoreView;
import com.liangfengyouxin.www.android.frame.view.recyclerview.LoadMoreRecyclerView;
import com.liangfengyouxin.www.android.frame.view.recyclerview.OnLoadMoreListener;
import com.liangfengyouxin.www.android.normal.main.adapter.TextAdapter;
import com.liangfengyouxin.www.android.normal.main.detail.TextDetailActivity;
import com.liangfengyouxin.www.android.presenter.GetTextPresenter;
import com.liangfengyouxin.www.android.presenter.view.IGetTextView;

import java.util.List;

/**
 * 首页文本
 * Created by lin.woo on 2017/5/16.
 */

public class TextFragment extends BaseFragment implements IGetTextView {
    public static final int TEXT_REQUEST_CODE = 1001;
    private LoadMoreRecyclerView rView;
    private MSwipeRefreshLayout swipe;
    private TextAdapter adapter;
    private GetTextPresenter presenter;

    private int index;//记录跳转详情页选择的item

    @Override
    protected int setBody() {
        return R.layout.fragment_text;
    }

    @Override
    protected void initValue() {
        adapter = new TextAdapter(getContext(), null);
        presenter = new GetTextPresenter(getContext(), this);
    }

    @Override
    protected void initWidget() {
        rView = (LoadMoreRecyclerView) findViewById(R.id.rec_list_view);
        rView.setLayoutManager(new LinearLayoutManager(getContext()));
        rView.setAdapter(adapter);
        swipe = (MSwipeRefreshLayout) findViewById(R.id.swipe);
        swipe.setRefreshing(true);
    }

    @Override
    protected void initListener() {
        swipe.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                presenter.getTextFirst();
            }
        });
        rView.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void loadMore() {
                presenter.getTextMore(adapter.getData().get(adapter.getData().size() - 1).Di);
            }
        });
        rView.setOnItemClickListener(new HeaderAndFooterRecyclerViewAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(RecyclerView.ViewHolder holder, int position) {
                index = position;
                Intent intent = new Intent(getContext(), TextDetailActivity.class);
                intent.putExtra(TextDetailActivity.TEXT_CONTENT, adapter.getData().get(position));
                startActivityForResult(intent, TEXT_REQUEST_CODE);
            }
        });
    }

    @Override
    protected void initData() {
        presenter.getTextFirst();
    }

    @Override
    public void getTextListSuccess(List<TextBean> list) {
        swipe.completeRefresh();
        if (!presenter.isLoadMore())
            adapter.getData().clear();

        if (list.size() < 10)
            rView.setHasLoadMore(false);
        else
            rView.setHasLoadMore(true);
        adapter.getData().addAll(list);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == TextDetailActivity.BACK_CALL_RESULT) {
            adapter.getData().get(index).Neirong = ((TextBean) data.getSerializableExtra(TextDetailActivity.TEXT_CONTENT)).Neirong;
            adapter.notifyDataSetChanged();
        }
    }
}
