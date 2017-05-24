package com.liangfengyouxin.www.android.normal.main;

import android.support.v4.view.PagerTabStrip;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.LinearLayout;

import com.liangfengyouxin.www.android.R;
import com.liangfengyouxin.www.android.frame.base.BaseActivity;
import com.liangfengyouxin.www.android.frame.base.BaseFragment;
import com.liangfengyouxin.www.android.frame.view.VPagerAdapter;
import com.liangfengyouxin.www.android.wxapi.WXUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lin.woo on 2017/5/15.
 */

public class HomeActivity extends BaseActivity {
    private ViewPager pager;
    private PagerTabStrip pagerTab;

    private LinearLayout llText;
    private LinearLayout llImage;

    @Override
    protected int setHeader() {
        return -1;
    }

    @Override
    protected int setBody() {
        return R.layout.activity_main;
    }

    @Override
    protected void initValue() {
    }

    @Override
    protected void initWidget() {
        pager = (ViewPager) findViewById(R.id.view_pager);
        pagerTab = (PagerTabStrip) findViewById(R.id.page_tag);

        pagerTab.setTabIndicatorColor(getResources().getColor(R.color.white));
        pagerTab.setTextColor(getResources().getColor(R.color.white));
        pagerTab.setClickable(false);
        pagerTab.setTextSpacing(40);
        pagerTab.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
        pagerTab.setDrawFullUnderline(true);

        llText = (LinearLayout) findViewById(R.id.ll_text);
        llImage = (LinearLayout) findViewById(R.id.ll_image);

    }

    @Override
    protected void initListener() {
        llText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                WXUtils.getInstance().sendReq(HomeActivity.this);
//                WXUtils.getInstance().sendResp(HomeActivity.this);
            }
        });
        llImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            }
        });
    }

    List<BaseFragment> fragments;

    @Override
    protected void initData() {

        List<String> titles = new ArrayList<>();
        titles.add("文字");
        titles.add("图片");
        titles.add("音频");
        titles.add("视频");
        TextFragment tf = new TextFragment();
        fragments = new ArrayList<>();
        fragments.add(tf);
        fragments.add(new ImageFragment());
        fragments.add(new AudioFragment());
        fragments.add(new VideoFragment());

        tf.setFragmentShow(true);
        VPagerAdapter adapter = new VPagerAdapter(getSupportFragmentManager(), fragments, titles);
        pager.setAdapter(adapter);
        pager.setCurrentItem(0);
    }
}
