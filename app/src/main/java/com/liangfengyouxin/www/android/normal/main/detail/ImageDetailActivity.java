package com.liangfengyouxin.www.android.normal.main.detail;

import com.facebook.drawee.view.SimpleDraweeView;
import com.liangfengyouxin.www.android.R;
import com.liangfengyouxin.www.android.frame.base.BaseActivity;
import com.liangfengyouxin.www.android.frame.bean.home.ImageBean;
import com.liangfengyouxin.www.android.frame.contants.Constant;
import com.liangfengyouxin.www.android.frame.utils.ToastLX;

/**
 * Created by lin.woo on 2017/5/24.
 */

public class ImageDetailActivity extends BaseActivity {
    private SimpleDraweeView picture;

    private ImageBean bean;

    @Override
    protected int setBody() {
        return R.layout.activity_image_detail;
    }

    @Override
    protected void initValue() {
        setTitle("图片详情页");
        bean = (ImageBean) getIntent().getSerializableExtra(Constant.REQUEST_CONTENT);
        if (bean == null) {
            ToastLX.StringToast(this, "获取数据异常...");
            finish();
            return;
        }
    }

    @Override
    protected void initWidget() {
        picture = (SimpleDraweeView) findViewById(R.id.fresco_image);
    }

    @Override
    protected void initListener() {

    }

    @Override
    protected void initData() {
        picture.setImageURI(bean.Lujing);
    }
}
