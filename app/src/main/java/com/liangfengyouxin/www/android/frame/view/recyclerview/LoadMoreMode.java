package com.liangfengyouxin.www.android.frame.view.recyclerview;

/**
 * 加载更多模式
 * Created by lin.woo on 2017/5/24.
 */
public enum LoadMoreMode {
    /**
     * 点击加载更多
     */
    CLICK,
    /**
     * 滑动到底部加载跟多
     */
    SCROLL;

    static LoadMoreMode mapIntToValue(int modeInt) {
        switch (modeInt) {
            case 0x0:
            default:
                return CLICK;
            case 0x1:
                return SCROLL;
        }
    }
}
