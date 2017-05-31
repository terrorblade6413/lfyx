package com.liangfengyouxin.www.android.frame.utils.imageprocess;

import android.graphics.Bitmap;
import android.graphics.Color;

/**
 * 浮雕样式
 * Created by lin.woo on 2017/5/25.
 */

public class EmbossImage {
    private static int width, height;
    private static int[] oldPixels;
    private static int[] newPixels;
    private static int color, color2;
    private static int pixelsR, pixelsG, pixelsB, pixelsA, pixelsR2, pixelsG2, pixelsB2;

    public static Bitmap emboss(Bitmap bitmap) {

        width = bitmap.getWidth();
        height = bitmap.getHeight();
        oldPixels = new int[width * height];
        newPixels = new int[width * height];
        Bitmap bt = Bitmap.createBitmap(width, height, Bitmap.Config.RGB_565);
        //获取像素
        bitmap.getPixels(oldPixels, 0, width, 0, 0, width, height);

        for (int i = 1; i < height * width; i++) {
            color = oldPixels[i - 1];
            //前一个像素
            pixelsR = Color.red(color);
            pixelsG = Color.green(color);
            pixelsB = Color.blue(color);
            //当前像素
            color2 = oldPixels[i];
            pixelsR2 = Color.red(color2);
            pixelsG2 = Color.green(color2);
            pixelsB2 = Color.blue(color2);

            pixelsR = (pixelsR - pixelsR2 + 127);
            pixelsG = (pixelsG - pixelsG2 + 127);
            pixelsB = (pixelsB - pixelsB2 + 127);
            //均小于等于255
            if (pixelsR > 255) {
                pixelsR = 255;
            }

            if (pixelsG > 255) {
                pixelsG = 255;
            }

            if (pixelsB > 255) {
                pixelsB = 255;
            }

            newPixels[i] = Color.argb(pixelsA, pixelsR, pixelsG, pixelsB);

        }
        bt.setPixels(newPixels,
                0,
                width,
                0,
                0,
                width,
                height);
        return bt;
    }

}
