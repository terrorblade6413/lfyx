package com.liangfengyouxin.www.android.normal.award;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.liangfengyouxin.www.android.R;
import com.liangfengyouxin.www.android.frame.base.BaseActivity;
import com.liangfengyouxin.www.android.frame.base.HeaderAndFooterRecyclerViewAdapter;
import com.liangfengyouxin.www.android.frame.bean.award.AwardListBean;
import com.liangfengyouxin.www.android.frame.contants.Constant;
import com.liangfengyouxin.www.android.frame.view.MSwipeRefreshLayout;
import com.liangfengyouxin.www.android.frame.view.recyclerview.LoadMoreRecyclerView;
import com.liangfengyouxin.www.android.normal.award.adapter.AwardAdapter;

/**
 * Created by lin.woo on 2017/6/6.
 */

public class AwardListActivity extends BaseActivity {

    private LinearLayout llRight;
    private MSwipeRefreshLayout swipe;
    private LoadMoreRecyclerView recyclerView;

    private AwardAdapter adapter;

    @Override
    protected int setBody() {
        return R.layout.activity_award_list;
    }

    @Override
    protected void initValue(@Nullable Bundle savedInstanceState) {
        super.initValue(savedInstanceState);
        setTitle("抽奖二维码");
        llRight = getLlRight();
        TextView tvRight = (TextView) llRight.getChildAt(0);
        tvRight.setVisibility(View.VISIBLE);
        tvRight.setText("创建");

        adapter = new AwardAdapter(this, null);
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
        llRight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AwardListActivity.this, AwardCreateActivity.class);
                startActivity(intent);
            }
        });
        recyclerView.setOnItemClickListener(new HeaderAndFooterRecyclerViewAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(RecyclerView.ViewHolder holder, int position) {
                Intent intent = new Intent(AwardListActivity.this, AwardDetailActivity.class);
                startActivityForResult(intent, Constant.REQUEST_CODE);
            }
        });
    }

    @Override
    protected void initData(@Nullable Bundle savedInstanceState) {
        super.initData(savedInstanceState);
        adapter.getData().add(new AwardListBean());
        adapter.notifyDataSetChanged();
    }
}
