package com.liangfengyouxin.www.android.normal.award.luck;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.RadioButton;

import com.liangfengyouxin.www.android.R;
import com.liangfengyouxin.www.android.frame.view.LuckyPanView;

import java.util.Random;

public class LuckActivity extends Activity {
    private LuckyPanView mLuckyPanView;
    private ImageView mStartBtn;

    private RadioButton rb1, rb2;
    private int select;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_luck);

        mLuckyPanView = (LuckyPanView) findViewById(R.id.id_luckypan);
        mStartBtn = (ImageView) findViewById(R.id.id_start_btn);
        rb1 = (RadioButton) findViewById(R.id.rb_1);
        rb2 = (RadioButton) findViewById(R.id.rb_2);
        select = -1;

        mStartBtn.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {


                if (!mLuckyPanView.isStart()) {

                    if (rb1.isChecked()) {
                        select = -1;
                    } else {
                        select = new Random().nextInt(6) - 1;
                    }
                    Log.d("select====", select + "");
                    mStartBtn.setImageResource(R.drawable.stop);
                    mLuckyPanView.luckyStart(select);
                } else {
                    if (!mLuckyPanView.isShouldEnd())

                    {
                        mStartBtn.setImageResource(R.drawable.start);
                        mLuckyPanView.luckyEnd();
                    }
                }
            }
        });


    }

}
