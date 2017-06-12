package com.liangfengyouxin.www.android.normal.award;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.liangfengyouxin.www.android.R;
import com.liangfengyouxin.www.android.frame.base.BaseActivity;
import com.liangfengyouxin.www.android.frame.base.HeaderAndFooterRecyclerViewAdapter;
import com.liangfengyouxin.www.android.frame.bean.award.AwardListBean;
import com.liangfengyouxin.www.android.frame.contants.Constant;
import com.liangfengyouxin.www.android.frame.utils.ToastLX;
import com.liangfengyouxin.www.android.frame.view.recyclerview.LoadMoreRecyclerView;
import com.liangfengyouxin.www.android.normal.award.adapter.AwardInfoAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lin.woo on 2017/6/6.
 */

public class AwardDetailActivity extends BaseActivity {
    private AwardInfoAdapter adapter;
    private LoadMoreRecyclerView recyclerView;
    private TextView tvAdd;

    @Override
    protected int setBody() {
        return R.layout.activity_award_detail;
    }

    @Override
    protected void initValue(@Nullable Bundle savedInstanceState) {
        super.initValue(savedInstanceState);

        adapter = new AwardInfoAdapter(this, null);
    }

    @Override
    protected void initWidget(@Nullable Bundle savedInstanceState) {
        super.initWidget(savedInstanceState);
        recyclerView = (LoadMoreRecyclerView) findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);

        tvAdd = (TextView) findViewById(R.id.tv_add);
    }

    @Override
    protected void initListener(@Nullable Bundle savedInstanceState) {
        super.initListener(savedInstanceState);
        recyclerView.setOnItemClickListener(new HeaderAndFooterRecyclerViewAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(RecyclerView.ViewHolder holder, int position) {
                adapter.getData().remove(position);
                adapter.notifyDataSetChanged();
                ToastLX.StringToast(AwardDetailActivity.this,"移除成功");
            }
        });

        tvAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AwardDetailActivity.this, AddAwardActivity.class);
                startActivityForResult(intent, Constant.REQUEST_CODE);
            }
        });
    }

    @Override
    protected void initData(@Nullable Bundle savedInstanceState) {
        super.initData(savedInstanceState);
        initHeader();

        List<AwardListBean> list = new ArrayList<>();
        AwardListBean bean = new AwardListBean();
        list.add(bean);
        bean = new AwardListBean();
        list.add(bean);
        bean = new AwardListBean();
        list.add(bean);
        bean = new AwardListBean();
        list.add(bean);
        bean = new AwardListBean();
        list.add(bean);
        bean = new AwardListBean();
        list.add(bean);
        bean = new AwardListBean();
        list.add(bean);
        bean = new AwardListBean();
        list.add(bean);
        bean = new AwardListBean();
        list.add(bean);
        bean = new AwardListBean();
        list.add(bean);
        bean = new AwardListBean();
        list.add(bean);
        bean = new AwardListBean();
        list.add(bean);
        bean = new AwardListBean();
        list.add(bean);
        bean = new AwardListBean();
        list.add(bean);
        adapter.getData().addAll(list);
        adapter.notifyDataSetChanged();

    }

    private void initHeader() {
        View view = LayoutInflater.from(this).inflate(R.layout.item_award, getBodyLayout(),false);
        recyclerView.addHeaderView(view);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode == AddAwardActivity.RESULT_CODE_ADD_AWARD){

        }
    }
}
