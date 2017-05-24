package com.liangfengyouxin.www.android.normal.main;

import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.liangfengyouxin.www.android.R;
import com.liangfengyouxin.www.android.frame.base.BaseFragment;
import com.liangfengyouxin.www.android.frame.bean.home.TextBean;
import com.liangfengyouxin.www.android.normal.main.adapter.ImageAdapter;
import com.liangfengyouxin.www.android.normal.main.adapter.TextAdapter;
import com.liangfengyouxin.www.android.presenter.GetTextPresenter;
import com.liangfengyouxin.www.android.presenter.view.IGetTextView;

import java.util.List;

/**
 * 首页文本
 * Created by lin.woo on 2017/5/16.
 */

public class TextFragment extends BaseFragment implements IGetTextView {
    private RecyclerView rView;
    private SwipeRefreshLayout swipe;
    private TextAdapter adapter;
    private GetTextPresenter presenter;

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
        rView = (RecyclerView) findViewById(R.id.rec_list_view);
        rView.setLayoutManager(new LinearLayoutManager(getContext()));
        rView.setAdapter(adapter);
        swipe = (SwipeRefreshLayout) findViewById(R.id.swipe);
        swipe.setRefreshing(true);
        swipe.setColorSchemeResources(android.R.color.holo_red_light,
                android.R.color.holo_blue_light,
                android.R.color.holo_orange_light,
                android.R.color.holo_green_light);
    }

    @Override
    protected void initListener() {
        swipe.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                presenter.getTextList();
            }
        });
    }

    @Override
    protected void initData() {
        presenter.getTextList();
    }

    @Override
    public void getTextListSuccess(List<TextBean> list) {
        swipe.post(new Runnable() {
            @Override
            public void run() {
                swipe.setRefreshing(false);
            }
        });
        adapter.getData().clear();
        adapter.getData().addAll(list);
        adapter.notifyDataSetChanged();
    }
}
