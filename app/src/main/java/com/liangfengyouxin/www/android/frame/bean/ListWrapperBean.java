package com.liangfengyouxin.www.android.frame.bean;

import com.liangfengyouxin.www.android.frame.bean.joinActivity.JoinActItemBean;

import java.util.List;

/**
 * Created by lin.woo on 2017/6/24.
 */

public class ListWrapperBean<T> extends BaseBean {
    public int total_record;
    public List<T> list;
    public int page;
    public int page_size;
}
