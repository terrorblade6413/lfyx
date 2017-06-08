package com.liangfengyouxin.www.android.normal.main.detail;

import android.content.Intent;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
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
    public static final int BACK_CALL_RESULT = 1010;
    private TextBean bean;
    private LinearLayout llRight;
    private EditText etContent;
    private TextView total;

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
            finish();
            return;
        }
        llRight = getLlRight();
        TextView tvRight = (TextView) llRight.getChildAt(0);
        tvRight.setVisibility(View.VISIBLE);
        tvRight.setText("保存");
    }

    @Override
    protected void initWidget() {
        etContent = (EditText) findViewById(R.id.et_content);
        total = (TextView) findViewById(R.id.tv_total);
    }

    @Override
    protected void initListener() {
        llRight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bean.Neirong = etContent.getText().toString().trim();
                Intent intent = new Intent();
                intent.putExtra(TEXT_CONTENT, bean);
                setResult(BACK_CALL_RESULT, intent);
                finish();
            }
        });
        etContent.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                total.setText(s.length() + "  字");
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    @Override
    protected void initData() {
        if (!TextUtils.isEmpty(bean.Neirong)) {
            etContent.setText(bean.Neirong);
            etContent.setSelection(bean.Neirong.length());
            total.setText(bean.Neirong.length() + "  字");
        }
    }
}
