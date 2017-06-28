package com.liangfengyouxin.www.android.normal.award;

import android.content.Context;
import android.support.annotation.NonNull;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.liangfengyouxin.www.android.R;
import com.liangfengyouxin.www.android.frame.bean.award.AddAwardBean;
import com.liangfengyouxin.www.android.frame.utils.ToastLX;
import com.liangfengyouxin.www.android.frame.view.DialogBase;

/**
 * Created by lin.woo on 2017/6/24.
 */

public class DialogAddAward extends DialogBase {
    private IAddAward iAddAward;
    private EditText awardName;
    private EditText awardNumber;
    private TextView tvCancel;
    private TextView tvConfirm;

    public DialogAddAward(@NonNull Context context) {
        super(context, R.layout.dialog_add_award);
        initLayout();
    }


    private DialogAddAward initLayout() {

        awardName = (EditText) findViewById(R.id.et_award_name);
        awardNumber = (EditText) findViewById(R.id.et_award_number);
        tvCancel = (TextView) findViewById(R.id.tv_cancel);
        tvConfirm = (TextView) findViewById(R.id.tv_confirm);

        tvCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
                if (iAddAward != null) {
                    iAddAward.onCancel();
                }
            }
        });

        tvConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (iAddAward != null) {
                    AddAwardBean bean = new AddAwardBean();
                    bean.prize_name = awardName.getText().toString();
                    String number = awardNumber.getText().toString();
                    if (TextUtils.isEmpty(bean.prize_name)) {
                        ToastLX.StringToast(mContext, "请输入奖品");
                        return;
                    }
                    if (TextUtils.isEmpty(number)) {
                        ToastLX.StringToast(mContext, "请输入奖品数");
                        return;
                    }
                    bean.prize_number = Integer.valueOf(number);
                    if (bean.prize_number == 0) {
                        ToastLX.StringToast(mContext, "请输入奖品数");
                        return;
                    }
                    bean.isShowDel = true;
                    iAddAward.onConfirm(bean);

                }
                dismiss();
            }
        });


        return this;
    }

    public DialogAddAward setOnListener(IAddAward iAddAward) {
        this.iAddAward = iAddAward;
        return this;
    }


    public interface IAddAward {
        void onCancel();

        void onConfirm(AddAwardBean bean);
    }

}
