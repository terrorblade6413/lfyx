package com.liangfengyouxin.www.android.normal.award;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.liangfengyouxin.www.android.R;
import com.liangfengyouxin.www.android.frame.base.BaseActivity;
import com.liangfengyouxin.www.android.frame.base.HeaderAndFooterRecyclerViewAdapter;
import com.liangfengyouxin.www.android.frame.bean.award.AwardListBean;
import com.liangfengyouxin.www.android.frame.contants.Constant;
import com.liangfengyouxin.www.android.frame.utils.DateUtils;
import com.liangfengyouxin.www.android.frame.utils.ToastLX;
import com.liangfengyouxin.www.android.frame.view.recyclerview.LoadMoreRecyclerView;
import com.liangfengyouxin.www.android.normal.award.adapter.AwardInfoAdapter;
import com.liangfengyouxin.www.android.presenter.award.AwardDetailPresenter;
import com.liangfengyouxin.www.android.presenter.award.view.IAwardDetail;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lin.woo on 2017/6/6.
 */

public class AwardDetailActivity extends BaseActivity implements IAwardDetail{
    public static final String AWARD_DETAIL_DATA = "awardDetailData";
    private AwardInfoAdapter adapter;
    private LoadMoreRecyclerView recyclerView;
    private LinearLayout llRight;

    //头部信息---抽奖基本信息
    private TextView tvTime;
    private TextView tvRate;
    private TextView tvLotteryNumber;
    private TextView tvWinningNumber;
    private SimpleDraweeView logo;
    //------------------------------

    private AwardListBean bean;
    private AwardDetailPresenter presenter;

    @Override
    protected int setBody() {
        return R.layout.activity_award_detail;
    }

    @Override
    protected void initValue(@Nullable Bundle savedInstanceState) {
        super.initValue(savedInstanceState);
        llRight = getLlRight();
        TextView edit = (TextView) llRight.getChildAt(0);
        edit.setText("编辑");
        edit.setVisibility(View.VISIBLE);


        adapter = new AwardInfoAdapter(this, null);
        bean = (AwardListBean) getIntent().getSerializableExtra(AWARD_DETAIL_DATA);
        presenter = new AwardDetailPresenter(this,this);
    }

    @Override
    protected void initWidget(@Nullable Bundle savedInstanceState) {
        super.initWidget(savedInstanceState);
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
                adapter.getData().remove(position);
                adapter.notifyDataSetChanged();
                ToastLX.StringToast(AwardDetailActivity.this,"移除成功");
            }
        });


        llRight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AwardDetailActivity.this,AwardCreateActivity.class);
                intent.putExtra(AWARD_DETAIL_DATA,bean);
                startActivityForResult(intent,Constant.REQUEST_CODE);

            }
        });
    }

    @Override
    protected void initData(@Nullable Bundle savedInstanceState) {
        super.initData(savedInstanceState);
        initHeader();
        presenter.getDetail(bean.id);
    }

    private void initHeader() {
        View view = LayoutInflater.from(this).inflate(R.layout.item_award, getBodyLayout(),false);
        tvTime = (TextView) view.findViewById(R.id.tv_date);
        tvRate = (TextView) view.findViewById(R.id.tv_rate);
        tvLotteryNumber = (TextView) view.findViewById(R.id.tv_lottery_number);
        logo = (SimpleDraweeView) view.findViewById(R.id.pic_sdv);
        tvWinningNumber = (TextView) view.findViewById(R.id.tv_winning_number);

        tvTime.setText(DateUtils.unixTimesToDate(Long.valueOf(bean.addtime) * 1000, DateUtils.DATE_SMALL_STR));
        //未使用
        int unused = Integer.valueOf(TextUtils.isEmpty(bean.prize_overplus) ? bean.gamblecode_num : bean.prize_overplus);
        //总数
        int total = Integer.valueOf(TextUtils.isEmpty(bean.gamblecode_num) ? "0" : bean.gamblecode_num);
        tvLotteryNumber.setText(unused + "/" + bean.gamblecode_num);
        logo.setImageURI(bean.url);
        tvRate.setText("中奖率: 1/" + bean.win_percent);

        tvWinningNumber.setText("中奖次数: " + (total - unused));









        recyclerView.addHeaderView(view);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode == AddAwardActivity.RESULT_CODE_ADD_AWARD){

        }
    }

    @Override
    public void AwardDetailSuccess(AwardListBean bean) {
        adapter.getData().addAll(bean.prize_list);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void AwardDetailFailure(boolean isRequest, int code, String msg) {

    }
}
