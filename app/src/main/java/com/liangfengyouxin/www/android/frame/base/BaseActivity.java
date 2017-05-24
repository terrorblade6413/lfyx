package com.liangfengyouxin.www.android.frame.base;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.liangfengyouxin.www.android.R;

/**
 * Created by lin.woo on 2017/5/15.
 */

public abstract class BaseActivity extends AppCompatActivity {

    private LinearLayout llHeader;
    private LinearLayout llBody;
    private LayoutInflater inflater;
    private ImageView imgBack;
    private TextView tvTitle;
    private TextView tvRight;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.base_activity);
        llHeader = (LinearLayout) findViewById(R.id.ll_header);
        llBody = (LinearLayout) findViewById(R.id.ll_body);
        inflater = LayoutInflater.from(this);
        setView();
    }

    @Override
    protected void onPostCreate(@Nullable Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        initValue();
        initWidget();
        initListener();
        initData();
    }

    protected int setHeader() {
        return R.layout.base_header_activity;
    }

    protected abstract int setBody();

    protected abstract void initValue();

    protected abstract void initWidget();

    protected abstract void initListener();

    protected abstract void initData();

    //设置界面结构
    private final void setView() {
        if (setHeader() == -1) {
            llHeader.setVisibility(View.GONE);
        } else {
            View header = inflater.inflate(setHeader(), null);
            imgBack = (ImageView) header.findViewById(R.id.base_activity_img_back);
            tvTitle = (TextView) header.findViewById(R.id.base_activity_tv_title);
            tvRight = (TextView) header.findViewById(R.id.base_activity_tv_right);
            close();
            llHeader.addView(header);
        }
        if (setBody() == -1) {
            llBody.setVisibility(View.GONE);
        } else {
            llBody.addView(inflater.inflate(setBody(), null));
        }
    }

    protected void setTitle(String title) {
        if (tvTitle != null && !TextUtils.isEmpty(title)) {
            tvTitle.setText(title);
        }
    }

    private void close() {
        imgBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }


    public ImageView getLeftView() {
        return imgBack;
    }

    public TextView getTvRight() {
        return tvRight;
    }
}
