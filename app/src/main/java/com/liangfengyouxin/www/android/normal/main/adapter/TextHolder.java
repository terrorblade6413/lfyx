package com.liangfengyouxin.www.android.normal.main.adapter;

import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;

import com.liangfengyouxin.www.android.R;
import com.liangfengyouxin.www.android.frame.base.BaseRecyclerHolder;
import com.liangfengyouxin.www.android.frame.bean.home.ImageBean;
import com.liangfengyouxin.www.android.frame.bean.home.TextBean;

import java.util.List;

/**
 * Created by lin.woo on 2017/5/19.
 */

public class TextHolder extends BaseRecyclerHolder<TextBean> {
    private TextView content;

    public TextHolder(View itemView) {
        super(itemView);
        content = (TextView) itemView.findViewById(R.id.tv_text);
    }

    @Override
    protected void initView(int position, List<TextBean> data) {
        content.setText(TextUtils.isEmpty(data.get(position).Neirong) ? "" : data.get(position).Neirong);
    }
}
