package com.liangfengyouxin.www.android.normal.main.adapter;

import android.graphics.drawable.Animatable;
import android.net.Uri;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.controller.BaseControllerListener;
import com.facebook.drawee.controller.ControllerListener;
import com.facebook.drawee.interfaces.DraweeController;
import com.facebook.drawee.view.SimpleDraweeView;
import com.facebook.imagepipeline.image.ImageInfo;
import com.liangfengyouxin.www.android.R;
import com.liangfengyouxin.www.android.frame.base.BaseRecyclerHolder;
import com.liangfengyouxin.www.android.frame.bean.home.ImageBean;
import com.liangfengyouxin.www.android.frame.contants.DeviceInfo;
import com.liangfengyouxin.www.android.frame.utils.DeviceUtil;

import java.util.List;

/**
 * Created by lin.woo on 2017/5/19.
 */

public class ImageHolder extends BaseRecyclerHolder<ImageBean> {
    private SimpleDraweeView picture;
    public ImageHolder(View itemView) {
        super(itemView);
        picture = (SimpleDraweeView) itemView.findViewById(R.id.picture);
    }

    @Override
    protected void initView(int position, List<ImageBean> data) {
//        picture.setImageURI(data.get(position).Lujing);
//        Log.d("picture===",picture.getWidth()+"");
//        Log.d("picture===",picture.getHeight()+"");
//        Log.d("picture===Measure----",picture.getMeasuredWidth()+"");
//        Log.d("picture===Measure----",picture.getMeasuredHeight()+"");
        setControllerListener(picture,data.get(position).Lujing, (int) DeviceUtil.getScreenWidth());
    }

    /**
     * 通过imageWidth 的宽度，自动适应高度
     * * @param simpleDraweeView view
     * * @param imagePath  Uri
     * * @param imageWidth width
     */
    public static void setControllerListener(final SimpleDraweeView simpleDraweeView, String imagePath, final int imageWidth) {
        final ViewGroup.LayoutParams layoutParams = simpleDraweeView.getLayoutParams();
        ControllerListener controllerListener = new BaseControllerListener<ImageInfo>() {
            @Override
            public void onFinalImageSet(String id, @Nullable ImageInfo imageInfo, @Nullable Animatable anim) {
                if (imageInfo == null) {
                    return;
                }
                int height = imageInfo.getHeight();
                int width = imageInfo.getWidth();
                layoutParams.width = imageWidth;
                layoutParams.height = (int) ((float) (imageWidth * height) / (float) width);
                simpleDraweeView.setLayoutParams(layoutParams);
            }

            @Override
            public void onIntermediateImageSet(String id, @Nullable ImageInfo imageInfo) {
                Log.d("TAG", "Intermediate image received");
            }

            @Override
            public void onFailure(String id, Throwable throwable) {
                throwable.printStackTrace();
            }
        };
        DraweeController controller = Fresco.newDraweeControllerBuilder().setControllerListener(controllerListener).setUri(Uri.parse(imagePath)).build();
        simpleDraweeView.setController(controller);
    }
}
