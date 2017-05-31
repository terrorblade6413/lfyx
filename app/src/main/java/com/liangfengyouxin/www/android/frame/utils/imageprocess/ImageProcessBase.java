package com.liangfengyouxin.www.android.frame.utils.imageprocess;

import android.graphics.Bitmap;
import android.os.AsyncTask;

import com.liangfengyouxin.www.android.frame.utils.imageprocess.view.IProcessImage;

/**
 * Created by lin.woo on 2017/5/25.
 */

public abstract class ImageProcessBase<T> {
    public T iView;
    public ImageProcessBase(T iView){
        this.iView = iView;
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
            output(bitmap);
        }
    }

    protected abstract void output(Bitmap bitmap);
}
