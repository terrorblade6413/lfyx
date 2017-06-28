package com.liangfengyouxin.www.android.normal.award;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.google.zxing.WriterException;
import com.liangfengyouxin.www.android.R;
import com.liangfengyouxin.www.android.frame.base.BaseActivity;
import com.liangfengyouxin.www.android.frame.bean.award.AddAwardBean;
import com.liangfengyouxin.www.android.frame.bean.award.AwardListBean;
import com.liangfengyouxin.www.android.frame.utils.DateUtils;
import com.liangfengyouxin.www.android.frame.utils.ToastLX;
import com.liangfengyouxin.www.android.frame.utils.zxing.QRCode;
import com.liangfengyouxin.www.android.frame.view.recyclerview.LoadMoreRecyclerView;
import com.liangfengyouxin.www.android.normal.award.adapter.AddAwardAdapter;
import com.liangfengyouxin.www.android.presenter.award.AddOrModifyAwardPresenter;
import com.liangfengyouxin.www.android.presenter.award.view.IAddOrModifyAward;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by lin.woo on 2017/6/6.
 */

public class AwardCreateActivity extends BaseActivity implements IAddOrModifyAward {
    private ImageView imgCode;
    private TextView tvTime;
    private LinearLayout llRight;
    private EditText etRate;
    private EditText etMaxNum;
    private EditText etWinNum;
    private EditText etStartTime;
    private EditText etEndTime;
    private Switch swDrawCode;
    private TextView tvAdd;//添加奖品
    private LoadMoreRecyclerView recyclerView;
    private AddAwardAdapter adapter;

    private AwardListBean bean;
    private AddOrModifyAwardPresenter presenter;

    @Override
    protected int setBody() {
        return R.layout.activity_award_create;
    }

    @Override
    protected void initValue(@Nullable Bundle savedInstanceState) {
        super.initValue(savedInstanceState);
        setTitle("创建抽奖二维码");

        llRight = getLlRight();
        TextView tvRight = (TextView) llRight.getChildAt(0);
        tvRight.setVisibility(View.VISIBLE);
        tvRight.setText("完成");

        adapter = new AddAwardAdapter(this, null);
        presenter = new AddOrModifyAwardPresenter(this, this);
    }

    @Override
    protected void initWidget(@Nullable Bundle savedInstanceState) {
        super.initWidget(savedInstanceState);
        imgCode = (ImageView) findViewById(R.id.tv_qr_code);
        tvTime = (TextView) findViewById(R.id.tv_time);
        etRate = (EditText) findViewById(R.id.et_rate);
        etMaxNum = (EditText) findViewById(R.id.et_max_number);
        etWinNum = (EditText) findViewById(R.id.et_win_number);
        etStartTime = (EditText) findViewById(R.id.et_start);
        etEndTime = (EditText) findViewById(R.id.et_end);
        swDrawCode = (Switch) findViewById(R.id.switch_draw_code);
        tvAdd = (TextView) findViewById(R.id.tv_add);
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
//                try {
//                    Bitmap log = BitmapFactory.decodeResource(getResources(), R.mipmap.timg);
//                    String strTate = etTate.getText().toString().trim();
//                    String strMax = etMaxNum.getText().toString().trim();
//                    String strWin = etWinNum.getText().toString().trim();
//                    String content = "中奖率：" + strTate + " 抽奖次数限制：" + strMax + " 中奖次数：" + strWin;
//                    imgCode.setImageBitmap(QRCode.createCode(content, log));
//                } catch (WriterException e) {
//                    e.printStackTrace();
//                }
                if (bean != null) {
                    modify();
                } else
                    add();
//                modify();
            }
        });

        tvAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogAddAward dialog = new DialogAddAward(AwardCreateActivity.this);
                dialog.setOnListener(new DialogAddAward.IAddAward() {
                    @Override
                    public void onCancel() {

                    }

                    @Override
                    public void onConfirm(AddAwardBean bean) {
                        adapter.getData().add(bean);
                        adapter.notifyDataSetChanged();
                    }
                });
                dialog.show();
            }
        });
    }

    @Override
    protected void initData(@Nullable Bundle savedInstanceState) {
        super.initData(savedInstanceState);

        bean = (AwardListBean) getIntent().getSerializableExtra(AwardDetailActivity.AWARD_DETAIL_DATA);
        if (bean != null) {
            showData();
        }

        try {
            Bitmap log = BitmapFactory.decodeResource(super.getResources(), R.mipmap.ic_launcher);
            imgCode.setImageBitmap(QRCode.createCode("https://www.baidu.com", log));
        } catch (WriterException e) {
            e.printStackTrace();
        }

        try {
            String time = DateUtils.longToString(System.currentTimeMillis(), DateUtils.DATE_SMALL_STR);
            tvTime.setText("创建时间：" + time);
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    private void showData() {
        if (bean.is_needcode.equals("0"))
            swDrawCode.setChecked(false);
        else
            swDrawCode.setChecked(true);

        etRate.setText(bean.win_percent);
        etMaxNum.setText(bean.gamblecode_num);
        etWinNum.setText(bean.gamble_times_limit.equals("-100") ? "" : bean.gamble_times_limit);
        long startTime = Long.valueOf(bean.start_time) * 1000;
        etStartTime.setText(DateUtils.unixTimesToDate(startTime, DateUtils.DATE_FULL));
        long endTime = Long.valueOf(bean.end_time) * 1000;
        etEndTime.setText(DateUtils.unixTimesToDate(endTime, DateUtils.DATE_FULL));
    }


    public void delAward(int position) {
        adapter.getData().remove(position);
        adapter.notifyDataSetChanged();
    }

    private void add() {
        //中奖率
        int rate = Integer.valueOf(etRate.getText().toString());
        //限制抽奖次数
        int limit = Integer.valueOf(etWinNum.getText().toString());
        //中的奖品数
        int total = Integer.valueOf(etMaxNum.getText().toString());
        long startTime;
        long endTime;
        try {
            startTime = DateUtils.stringToDate(etStartTime.getText().toString(), DateUtils.DATE_FULL).getTime() / 1000;
        } catch (ParseException e) {
            e.printStackTrace();
            ToastLX.StringToast(this, "开始时间格式有误");
            return;
        }
        try {
            endTime = DateUtils.stringToDate(etEndTime.getText().toString(), DateUtils.DATE_FULL).getTime() / 1000;
        } catch (ParseException e) {
            e.printStackTrace();
            ToastLX.StringToast(this, "结束时间格式有误");
            return;
        }

        presenter.addAward(limit, rate, total, swDrawCode.isChecked(), startTime, endTime, adapter.getData());

    }

    private void modify() {
//中奖率
        int rate = Integer.valueOf(etRate.getText().toString());
        //限制抽奖次数
        int limit = Integer.valueOf(etWinNum.getText().toString());
        //中的奖品数
        int total = Integer.valueOf(etMaxNum.getText().toString());
        long startTime;
        long endTime;
        try {
            startTime = DateUtils.stringToDate(etStartTime.getText().toString(), DateUtils.DATE_FULL).getTime() / 1000;
        } catch (ParseException e) {
            e.printStackTrace();
            ToastLX.StringToast(this, "开始时间格式有误");
            return;
        }
        try {
            endTime = DateUtils.stringToDate(etEndTime.getText().toString(), DateUtils.DATE_FULL).getTime() / 1000;
        } catch (ParseException e) {
            e.printStackTrace();
            ToastLX.StringToast(this, "结束时间格式有误");
            return;
        }

        presenter.modifyAward(bean.id, limit, rate, total, swDrawCode.isChecked(), startTime, endTime, adapter.getData());
    }


    @Override
    public void addAwardSuccess() {
        ToastLX.StringToast(AwardCreateActivity.this, "添加成功");
        finish();
    }

    @Override
    public void modifyAwardSuccess() {
        ToastLX.StringToast(AwardCreateActivity.this, "修改成功");
        finish();
    }

    @Override
    public void addAwardFailure(boolean isRequest, int code, String msg) {
        ToastLX.StringToast(AwardCreateActivity.this, "添加失败");
    }

    @Override
    public void modifyAwardFailure(boolean isRequest, int code, String msg) {
        ToastLX.StringToast(AwardCreateActivity.this, "修改失败");
    }
}
