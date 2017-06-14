package com.liangfengyouxin.www.android.normal.award;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.zxing.WriterException;
import com.liangfengyouxin.www.android.R;
import com.liangfengyouxin.www.android.frame.base.BaseActivity;
import com.liangfengyouxin.www.android.frame.utils.DateUtils;
import com.liangfengyouxin.www.android.frame.utils.zxing.QRCode;

import java.text.ParseException;

/**
 * Created by lin.woo on 2017/6/6.
 */

public class AwardCreateActivity extends BaseActivity {
    private ImageView imgCode;
    private TextView tvTime;
    private LinearLayout llRight;
    private EditText etTate;
    private EditText etMaxNum;
    private EditText etWinNum;

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
    }

    @Override
    protected void initWidget(@Nullable Bundle savedInstanceState) {
        super.initWidget(savedInstanceState);
        imgCode = (ImageView) findViewById(R.id.tv_qr_code);
        tvTime = (TextView) findViewById(R.id.tv_time);
        etTate = (EditText) findViewById(R.id.et_rate);
        etMaxNum = (EditText) findViewById(R.id.et_max_number);
        etWinNum = (EditText) findViewById(R.id.et_win_number);
    }

    @Override
    protected void initListener(@Nullable Bundle savedInstanceState) {
        super.initListener(savedInstanceState);
        llRight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    Bitmap log = BitmapFactory.decodeResource(getResources(), R.mipmap.timg);
                    String strTate = etTate.getText().toString().trim();
                    String strMax = etMaxNum.getText().toString().trim();
                    String strWin = etWinNum.getText().toString().trim();
                    String content = "中奖率：" + strTate + " 抽奖次数限制：" + strMax + " 中奖次数：" + strWin;
                    imgCode.setImageBitmap(QRCode.createCode(content, log));
                } catch (WriterException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    @Override
    protected void initData(@Nullable Bundle savedInstanceState) {
        super.initData(savedInstanceState);
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
}
