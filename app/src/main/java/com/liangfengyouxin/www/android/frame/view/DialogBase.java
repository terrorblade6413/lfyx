package com.liangfengyouxin.www.android.frame.view;

import android.app.Dialog;
import android.content.Context;
import android.support.annotation.NonNull;

import com.liangfengyouxin.www.android.R;

/**
 * Created by lin.woo on 2017/5/23.
 */

public class DialogBase extends Dialog {
//    public DialogBase(@NonNull Context context, @StyleRes int themeResId) {
//        super(context, themeResId);
//    }
    public DialogBase(@NonNull Context context) {
        super(context);
        setContentView(R.layout.dialog_base);
    }

}
