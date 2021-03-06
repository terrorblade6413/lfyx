package com.liangfengyouxin.www.android.frame.base;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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
    private LinearLayout llRight;

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
        initValue(savedInstanceState);
        initWidget(savedInstanceState);
        initListener(savedInstanceState);
        initData(savedInstanceState);
    }

    protected int setHeader() {
        return R.layout.base_header_activity;
    }

    protected int setBody(){
        return -1;
    }

    protected void initValue(@Nullable Bundle savedInstanceState){

    }

    protected void initWidget(@Nullable Bundle savedInstanceState){

    }

    protected void initListener(@Nullable Bundle savedInstanceState){

    }

    protected  void initData(@Nullable Bundle savedInstanceState){}

    //设置界面结构
    private final void setView() {
        if (setHeader() == -1 || setBody() == 0) {
            llHeader.setVisibility(View.GONE);
        } else {
            View header = inflater.inflate(setHeader(), null);
            imgBack = (ImageView) header.findViewById(R.id.base_activity_img_back);
            tvTitle = (TextView) header.findViewById(R.id.base_activity_tv_title);
            llRight = (LinearLayout) header.findViewById(R.id.base_activity_ll_right);
            close();
            llHeader.addView(header);
        }
        if (setBody() == -1 || setBody() == 0) {
            llBody.setVisibility(View.GONE);
        } else {
            llBody.addView(inflater.inflate(setBody(), llBody,false));
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

    //获取头部的左边布局
    public ImageView getLeftView() {
        return imgBack;
    }
    //获取头部右边布局
    public LinearLayout getLlRight() {
        return llRight;
    }
    //获取当前根布局
    public LinearLayout getBodyLayout(){
        return llBody;
    }
}
