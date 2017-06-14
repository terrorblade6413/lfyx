package com.liangfengyouxin.www.android.normal.award;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.liangfengyouxin.www.android.R;
import com.liangfengyouxin.www.android.frame.base.BaseActivity;

/**
 * 添加奖品
 * Created by lin.woo on 2017/6/10.
 */

public class AddAwardActivity extends BaseActivity {
    public static final int RESULT_CODE_ADD_AWARD = 5003;
    private LinearLayout llRight;
    @Override
    protected int setBody() {
        return R.layout.activity_award_add;
    }

    @Override
    protected void initValue(@Nullable Bundle savedInstanceState) {
        super.initValue(savedInstanceState);
        setTitle("添加奖品");
        llRight = getLlRight();
        TextView tvRight = (TextView) llRight.getChildAt(0);
        tvRight.setVisibility(View.VISIBLE);
        tvRight.setText("保存");
    }


    @Override
    protected void initListener(@Nullable Bundle savedInstanceState) {
        super.initListener(savedInstanceState);
        llRight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
