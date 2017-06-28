package com.liangfengyouxin.www.android.frame.bean.award;

import com.liangfengyouxin.www.android.frame.bean.BaseBean;

import java.util.List;

/**
 * 抽奖列表
 * Created by lin.woo on 2017/6/6.
 */

public class AwardListBean extends BaseBean {
    public String id;
    public String uid;
    public String activity_name;
    public String is_needcode;
    public String gamblecode_num;
    public String custom;
    public String gamble_times_limit;
    public String win_percent;
    public String url;
    public String gamble_times_used;
    public String qrcode_img_url;
    public String start_time;
    public String end_time;
    public String addtime;
    public String status;

    public String prize_overplus;
    public List<AddAwardBean> prize_list;
}
