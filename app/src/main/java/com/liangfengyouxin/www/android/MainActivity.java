package com.liangfengyouxin.www.android;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.liangfengyouxin.www.android.frame.base.BaseActivity;
import com.liangfengyouxin.www.android.normal.main.HomeActivity;

public class MainActivity extends BaseActivity {

    @Override
    protected int setHeader() {
        return -1;
    }

    @Override
    protected int setBody() {
        return R.layout.activity_main;
    }

    @Override
    protected void initValue() {

    }

    @Override
    protected void initWidget() {
    }

    @Override
    protected void initListener() {
    }

    @Override
    protected void initData() {

    }
}
