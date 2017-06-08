package com.liangfengyouxin.www.android.normal.more;

import android.content.Intent;
import android.view.View;
import android.widget.LinearLayout;

import com.liangfengyouxin.www.android.R;
import com.liangfengyouxin.www.android.frame.base.BaseActivity;

/**
 * Created by lin.woo on 2017/6/6.
 */

public class MoreActivity extends BaseActivity {
    private LinearLayout llRight;
    @Override
    protected int setBody() {
        return R.layout.activity_more;
    }

    @Override
    protected void initValue() {

        llRight = getLlRight();
        llRight.getChildAt(0).setVisibility(View.VISIBLE);

    }

    @Override
    protected void initWidget() {

    }

    @Override
    protected void initListener() {
        llRight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MoreActivity.this, SettingActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void initData() {

    }
}
