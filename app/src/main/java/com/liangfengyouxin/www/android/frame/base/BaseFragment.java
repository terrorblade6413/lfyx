package com.liangfengyouxin.www.android.frame.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.liangfengyouxin.www.android.R;

/**
 * Created by lin.woo on 2017/5/16.
 */

public abstract class BaseFragment extends Fragment {

    private View root;
    private LinearLayout llHeader;
    private LinearLayout llBody;
    private boolean firstShow;
    private boolean showFragment;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        root = inflater.inflate(R.layout.base_fragment, null);
        llHeader = (LinearLayout) root.findViewById(R.id.base_fragment_ll_header);
        llBody = (LinearLayout) root.findViewById(R.id.base_fragment_ll_body);
        if (setHeader() == 0) {
            llHeader.setVisibility(View.GONE);
        } else {
            llHeader.addView(inflater.inflate(setHeader(), null));
        }
        if (setBody() == -1) {
            llBody.setVisibility(View.GONE);
        } else {
            llBody.addView(inflater.inflate(setBody(), null));
        }
        if(showFragment){
            init();
        }else{
            firstShow = true;
        }
        return root;
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if(isVisibleToUser && firstShow && !showFragment){
            firstShow = false;
            init();
            Log.d("show---",getClass().getSimpleName());
        }
    }
    private void init(){
        initValue();
        initWidget();
        initListener();
        initData();
    }

    protected int setHeader() {
        return 0;
    }


    protected abstract int setBody();

    protected abstract void initValue();

    protected abstract void initWidget();

    protected abstract void initListener();

    protected abstract void initData();

    protected View findViewById(int id) {
        return root.findViewById(id);
    }

    public void setFragmentShow(boolean showFragment){
        this.showFragment = showFragment;
    }
}
