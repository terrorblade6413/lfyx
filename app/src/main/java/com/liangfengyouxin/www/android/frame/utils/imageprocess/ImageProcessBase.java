package com.liangfengyouxin.www.android.frame.utils.imageprocess;

import android.graphics.Bitmap;
import android.os.AsyncTask;

import com.liangfengyouxin.www.android.frame.utils.imageprocess.view.IProcessImage;

/**
 * Created by lin.woo on 2017/5/25.
 */

public abstract class ImageProcessBase<T> {
    public static final int IMAGE_PROCESS_REMINISCENCE = 1;
    public T iView;
    public int type;
    public ImageProcessBase(T iView, int type){
        this.iView = iView;
        this.type = type;
    }
    public void execute(Bitmap bmp){
        new ImageProcess().execute(bmp);
    }
    protected abstract Bitmap operation(Bitmap bitmap);

    private class ImageProcess extends AsyncTask<Bitmap,Void,Bitmap> {
        public ImageProcess(){

        }

        @Override
        protected Bitmap doInBackground(Bitmap... params) {
            return operation(params[0]);
        }

        @Override
        protected void onPostExecute(Bitmap bitmap) {
            super.onPostExecute(bitmap);
            output(bitmap, type);
        }
    }

    protected abstract void output(Bitmap bitmap , int type);
}
