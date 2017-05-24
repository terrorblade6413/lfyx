package com.liangfengyouxin.www.android.normal.main.detail;

import android.view.View;
import android.widget.TextView;

import com.liangfengyouxin.www.android.R;
import com.liangfengyouxin.www.android.frame.base.BaseActivity;
import com.liangfengyouxin.www.android.frame.bean.home.TextBean;
import com.liangfengyouxin.www.android.frame.utils.ToastLX;

/**
 * Created by lin.woo on 2017/5/24.
 */

public class TextDetailActivity extends BaseActivity {
    public static final String TEXT_CONTENT = "textContent";
    private TextBean bean;
    private TextView tvRight;

    @Override
    protected int setBody() {
        return R.layout.activity_text_detail;
    }

    @Override
    protected void initValue() {
        setTitle("文本编辑");
        bean = (TextBean) getIntent().getSerializableExtra(TEXT_CONTENT);
        if (bean == null) {
            ToastLX.StringToast(this, "载入失败...");
            return;
        }
        tvRight = getTvRight();
        tvRight.setText("保存");
    }

    @Override
    protected void initWidget() {

    }

    @Override
    protected void initListener() {
        tvRight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

    @Override
    protected void initData() {

    }
}
