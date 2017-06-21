package com.liangfengyouxin.www.android.normal.more;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.liangfengyouxin.www.android.R;
import com.liangfengyouxin.www.android.frame.base.BaseActivity;
import com.liangfengyouxin.www.android.normal.award.AwardListActivity;
import com.liangfengyouxin.www.android.normal.more.joinActivity.JoinActivityListActivity;

/**
 * Created by lin.woo on 2017/6/6.
 */

public class MoreActivity extends BaseActivity implements View.OnClickListener {
    private LinearLayout llRight;
    private RelativeLayout rlAward;
    private RelativeLayout rlJoinAct;

    @Override
    protected int setBody() {
        return R.layout.activity_more;
    }

    @Override
    protected void initValue(@Nullable Bundle savedInstanceState) {
        super.initValue(savedInstanceState);
        llRight = getLlRight();
        llRight.getChildAt(0).setVisibility(View.VISIBLE);

    }

    @Override
    protected void initWidget(@Nullable Bundle savedInstanceState) {
        super.initWidget(savedInstanceState);
        rlAward = (RelativeLayout) findViewById(R.id.rl_award);
        rlJoinAct = (RelativeLayout) findViewById(R.id.rl_join_act);
    }

    @Override
    protected void initListener(@Nullable Bundle savedInstanceState) {
        super.initListener(savedInstanceState);
        rlAward.setOnClickListener(this);
        rlJoinAct.setOnClickListener(this);

        llRight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MoreActivity.this, SettingActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void initData(@Nullable Bundle savedInstanceState) {
        super.initData(savedInstanceState);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.rl_award:
                Intent intent = new Intent(this, AwardListActivity.class);
                startActivity(intent);
                break;
            case R.id.rl_join_act:
                Intent joinAct = new Intent(this, JoinActivityListActivity.class);
                startActivity(joinAct);
                break;
        }
    }
}
