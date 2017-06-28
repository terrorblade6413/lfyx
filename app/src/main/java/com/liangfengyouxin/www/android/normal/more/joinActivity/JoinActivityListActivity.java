package com.liangfengyouxin.www.android.normal.more.joinActivity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.liangfengyouxin.www.android.R;
import com.liangfengyouxin.www.android.frame.base.BaseActivity;
import com.liangfengyouxin.www.android.frame.base.HeaderAndFooterRecyclerViewAdapter;
import com.liangfengyouxin.www.android.frame.bean.award.AwardListBean;
import com.liangfengyouxin.www.android.frame.bean.joinActivity.JoinActBean;
import com.liangfengyouxin.www.android.frame.bean.joinActivity.JoinActItemBean;
import com.liangfengyouxin.www.android.frame.utils.ToastLX;
import com.liangfengyouxin.www.android.frame.view.MSwipeRefreshLayout;
import com.liangfengyouxin.www.android.frame.view.recyclerview.LoadMoreRecyclerView;
import com.liangfengyouxin.www.android.normal.more.joinActivity.adapter.JoinActAdapter;
import com.liangfengyouxin.www.android.presenter.joinActivity.JoinActDetailPresenter;
import com.liangfengyouxin.www.android.presenter.joinActivity.JoinActivityListPresenter;
import com.liangfengyouxin.www.android.presenter.joinActivity.view.IJoinActDetail;
import com.liangfengyouxin.www.android.presenter.joinActivity.view.IJoinActivityList;

import java.util.List;

/**
 * Created by lin.woo on 2017/6/21.
 */

public class JoinActivityListActivity extends BaseActivity implements IJoinActivityList {

    private MSwipeRefreshLayout swipe;
    private LoadMoreRecyclerView recyclerView;

    private JoinActAdapter adapter;
    private JoinActivityListPresenter presenter;

    @Override
    protected int setBody() {
        return R.layout.activity_award_list;
    }

    @Override
    protected void initValue(@Nullable Bundle savedInstanceState) {
        super.initValue(savedInstanceState);
        adapter = new JoinActAdapter(this, null);
        presenter = new JoinActivityListPresenter(this, this);
    }

    @Override
    protected void initWidget(@Nullable Bundle savedInstanceState) {
        super.initWidget(savedInstanceState);
        swipe = (MSwipeRefreshLayout) findViewById(R.id.swipe_refresh);
        recyclerView = (LoadMoreRecyclerView) findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);
    }

    @Override
    protected void initListener(@Nullable Bundle savedInstanceState) {
        super.initListener(savedInstanceState);
        recyclerView.setOnItemClickListener(new HeaderAndFooterRecyclerViewAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(RecyclerView.ViewHolder holder, int position) {
//                Intent intent = new Intent(JoinActivityListActivity.this, AwardDetailActivity.class);
//                startActivityForResult(intent, Constant.REQUEST_CODE);
            }
        });
    }

    @Override
    protected void initData(@Nullable Bundle savedInstanceState) {
        super.initData(savedInstanceState);
        presenter.getFirst();
    }

    @Override
    public void getJoinActListSuccess(List<JoinActItemBean> list,boolean isMore) {
        if(presenter.page == 1){
            adapter.getData().clear();
            ToastLX.StringToast(this,"没有更多数据了");
            return;
        }
        if(!isMore){
            ToastLX.StringToast(this,"没有更多数据了");
            return;
        }
        adapter.getData().addAll(list);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void getJoinActListFailure(boolean isRequest, int code, String msg) {
            ToastLX.StringToast(this,"请求失败");
    }

}
