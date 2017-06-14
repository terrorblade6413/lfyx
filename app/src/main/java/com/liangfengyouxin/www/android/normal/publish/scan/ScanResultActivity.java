//package com.liangfengyouxin.www.android.normal.publish.scan;
//
//import android.support.annotation.Nullable;
//import android.support.v7.app.AppCompatActivity;
//import android.os.Bundle;
//import android.widget.TextView;
//
//import com.liangfengyouxin.www.android.frame.base.BaseActivity;
//import com.redstar.mainapp.R;
//import com.redstar.mainapp.frame.base.HxBaseActivity;
//
//public class ScanResultActivity extends BaseActivity {
//
//    TextView tvScanResult;
//
//    @Override
//    protected int setBody() {
//        return R.layout.activity_scan_result;
//    }
//
//    @Override
//    protected void initValue(@Nullable Bundle savedInstanceState) {
//        super.initValue(savedInstanceState);
//
//        String result=getIntent().getStringExtra("result");
//        tvScanResult=getTextView(R.id.tv_scan_result);
//        tvScanResult.setText("扫描结果:"+result);
//    }
//}
