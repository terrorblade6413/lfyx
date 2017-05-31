package com.liangfengyouxin.www.android;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.TextPaint;
import android.text.TextUtils;
import android.text.method.LinkMovementMethod;
import android.text.style.CharacterStyle;
import android.text.style.ClickableSpan;
import android.text.style.URLSpan;
import android.view.GestureDetector;
import android.view.Menu;
import android.view.ScaleGestureDetector;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.liangfengyouxin.www.android.frame.base.BaseActivity;
import com.liangfengyouxin.www.android.normal.main.HomeActivity;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MainActivity extends BaseActivity {
    TextView textView;
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
        textView = (TextView) findViewById(R.id.tv);
    }

    @Override
    protected void initListener() {
    }

    @Override
    protected void initData() {
//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                try {
//                    Thread.sleep(2000);
                    Intent intent = new Intent(MainActivity.this, HomeActivity.class);
                    startActivity(intent);
                    finish();
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//            }
//        }).start();

    }
}
