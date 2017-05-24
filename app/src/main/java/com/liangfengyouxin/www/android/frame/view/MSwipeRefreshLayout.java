package com.liangfengyouxin.www.android.frame.view;

import android.content.Context;
import android.support.annotation.ColorInt;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.AttributeSet;

/**
 * Created by lin.woo on 2017/5/24.
 */

public class MSwipeRefreshLayout extends SwipeRefreshLayout {
    public MSwipeRefreshLayout(Context context) {
        super(context);
        init();
    }

    public MSwipeRefreshLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        setColorSchemeColors(getResources().getColor(android.R.color.holo_red_light),
                getResources().getColor(android.R.color.holo_blue_light),
                getResources().getColor(android.R.color.holo_orange_light),
                getResources().getColor(android.R.color.holo_green_light));
    }

    public void completeRefresh(){
        setRefreshing(false);
    }


}
