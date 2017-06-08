package com.liangfengyouxin.www.android.normal.more;

import android.content.Intent;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.liangfengyouxin.www.android.R;
import com.liangfengyouxin.www.android.frame.base.BaseActivity;
import com.liangfengyouxin.www.android.normal.award.AwardListActivity;

/**
 * Created by lin.woo on 2017/6/6.
 */

public class MoreActivity extends BaseActivity implements View.OnClickListener {
    private LinearLayout llRight;
    private RelativeLayout rlAward;

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
        rlAward = (RelativeLayout) findViewById(R.id.rl_award);
    }

    @Override
    protected void initListener() {
        rlAward.setOnClickListener(this);

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

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.rl_award:
                Intent intent = new Intent(this, AwardListActivity.class);
                startActivity(intent);
                break;
        }
    }
}
