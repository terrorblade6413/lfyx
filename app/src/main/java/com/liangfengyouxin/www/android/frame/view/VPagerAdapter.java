package com.liangfengyouxin.www.android.frame.view;

import android.app.Activity;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.liangfengyouxin.www.android.frame.base.BaseFragment;

import java.util.List;

/**
 * Created by lin.woo on 2017/5/16.
 */

public class VPagerAdapter extends FragmentPagerAdapter {
    private Activity activity;
    private List<BaseFragment> fragmentList;
    private List<String> titles;

    public VPagerAdapter(FragmentManager fm, List<BaseFragment> fragmentList, List<String> titles) {
        super(fm);
        this.fragmentList = fragmentList;
        this.titles = titles;
    }

    @Override
    public Fragment getItem(int position) {
        return fragmentList.get(position);
    }

    @Override
    public int getCount() {
        return fragmentList == null ? 0 : fragmentList.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return titles.get(position);
    }
}
