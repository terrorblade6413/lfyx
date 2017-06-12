package com.liangfengyouxin.www.android.normal.main.detail;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;

import com.facebook.binaryresource.FileBinaryResource;
import com.facebook.cache.common.SimpleCacheKey;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.generic.GenericDraweeHierarchy;
import com.facebook.drawee.view.SimpleDraweeView;
import com.liangfengyouxin.www.android.R;
import com.liangfengyouxin.www.android.frame.base.BaseActivity;
import com.liangfengyouxin.www.android.frame.bean.home.ImageBean;
import com.liangfengyouxin.www.android.frame.contants.Constant;
import com.liangfengyouxin.www.android.frame.utils.ToastLX;
import com.liangfengyouxin.www.android.frame.utils.imageprocess.BurlImage;
import com.liangfengyouxin.www.android.frame.utils.imageprocess.EmbossImage;
import com.liangfengyouxin.www.android.frame.utils.imageprocess.ImageProcessBase;
import com.liangfengyouxin.www.android.frame.utils.imageprocess.ReminiscenceImage;
import com.liangfengyouxin.www.android.frame.utils.imageprocess.view.IProcessImage;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

/**
 * Created by lin.woo on 2017/5/24.
 */

public class ImageDetailActivity extends BaseActivity implements IProcessImage {
    private SimpleDraweeView picture;
    private ImageView imgReminiscence;
    private ImageView imgBurl;
    private ImageView imgEmboss;

    private ImageBean bean;

    private Bitmap bitReminiscence;
    private Bitmap bitBurl;
    private Bitmap bitEmboss;

    private ReminiscenceImage oldRemeber;

    @Override
    protected int setBody() {
        return R.layout.activity_image_detail;
    }

    @Override
    protected void initValue(@Nullable Bundle savedInstanceState) {
        super.initValue(savedInstanceState);
        setTitle("图片详情页");
        bean = (ImageBean) getIntent().getSerializableExtra(Constant.REQUEST_CONTENT);
        if (bean == null) {
            ToastLX.StringToast(this, "获取数据异常...");
            finish();
            return;
        }
        oldRemeber = new ReminiscenceImage(this, ImageProcessBase.IMAGE_PROCESS_REMINISCENCE);

    }

    @Override
    protected void initWidget(@Nullable Bundle savedInstanceState) {
        super.initWidget(savedInstanceState);
        picture = (SimpleDraweeView) findViewById(R.id.fresco_image);
        imgReminiscence = (ImageView) findViewById(R.id.img_reminiscence);
        imgBurl = (ImageView) findViewById(R.id.img_blur);
        imgEmboss = (ImageView) findViewById(R.id.img_emboss);
    }

    @Override
    protected void initListener(@Nullable Bundle savedInstanceState) {
        super.initListener(savedInstanceState);
        picture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                picture.setImageBitmap(ReminiscenceImage.oldRemeber(getLoacalBitmap(file.getAbsolutePath())));
                Bitmap bitmap = getLoacalBitmap(file.getAbsolutePath());
                oldRemeber.execute(bitmap);
//                bitReminiscence = ReminiscenceImage.oldRemeber(bitmap);
//                imgReminiscence.setImageBitmap(bitReminiscence);
//                bitBurl = BurlImage.blurImageAmeliorate(bitmap);
//                imgBurl.setImageBitmap(bitBurl);
//                bitEmboss = EmbossImage.emboss(bitmap);
//                imgEmboss.setImageBitmap(bitEmboss);
            }
        });
        imgBurl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                picture.setImageBitmap(bitBurl);
            }
        });
        imgReminiscence.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                picture.setImageBitmap(bitReminiscence);
            }
        });
        imgEmboss.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                picture.setImageBitmap(bitEmboss);
            }
        });
    }

    @Override
    protected void initData(@Nullable Bundle savedInstanceState) {
        super.initData(savedInstanceState);
        picture.setImageURI(bean.Lujing);

//        GenericDraweeHierarchy hierarchy = picture.getHierarchy();
//        hierarchy.setPlaceholderImage(context.getResources().getDrawable(photos[position]));

        FileBinaryResource resource = (FileBinaryResource) Fresco.getImagePipelineFactory()
                .getMainDiskStorageCache().getResource(new SimpleCacheKey(bean.Lujing));
        file = resource.getFile();
    }

    File file;

    public static Bitmap getLoacalBitmap(String url) {
        try {
            FileInputStream fis = new FileInputStream(url);
            return BitmapFactory.decodeStream(fis);  ///把流转化为Bitmap图片

        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public void onBitmap(Bitmap bitmap, int type) {
        switch (type){
            case ImageProcessBase.IMAGE_PROCESS_REMINISCENCE:
                bitReminiscence = bitmap;
                imgReminiscence.setImageBitmap(bitmap);
                break;
        }

    }
}
