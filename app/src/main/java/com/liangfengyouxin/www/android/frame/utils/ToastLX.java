package com.liangfengyouxin.www.android.frame.utils;

import android.content.Context;
import android.widget.Toast;

/**
 * Created by lin.woo on 2017/5/24.
 */

public class ToastLX {
    private static Toast toast;

    public static void StringToast(Context context, String content) {
        if (toast == null) {
            toast = Toast.makeText(context.getApplicationContext(), content, Toast.LENGTH_LONG);
        } else {
            toast.setText(content);
            toast.setDuration(Toast.LENGTH_LONG);
        }
        toast.show();
    }
}
