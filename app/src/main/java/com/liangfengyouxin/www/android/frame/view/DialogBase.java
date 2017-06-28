package com.liangfengyouxin.www.android.frame.view;

import android.app.Dialog;
import android.content.Context;
import android.support.annotation.NonNull;

import com.liangfengyouxin.www.android.R;

/**
 * Created by lin.woo on 2017/5/23.
 */

public class DialogBase extends Dialog {
    protected Context mContext;
    public DialogBase(@NonNull Context context) {
        super(context);
        mContext = context;
        setContentView(R.layout.dialog_base);
    }
    public DialogBase(Context context,int layout){
        super(context);
        mContext = context;
        setContentView(layout);
    }
}
