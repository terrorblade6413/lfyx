package com.liangfengyouxin.www.android.wxapi;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.provider.Settings;
import android.support.v7.app.AlertDialog;

import com.liangfengyouxin.www.android.frame.view.DialogBase;
import com.tencent.mm.opensdk.modelmsg.GetMessageFromWX;
import com.tencent.mm.opensdk.modelmsg.SendMessageToWX;
import com.tencent.mm.opensdk.modelmsg.WXMediaMessage;
import com.tencent.mm.opensdk.modelmsg.WXTextObject;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;

/**
 * Created by lin.woo on 2017/5/21.
 */

public class WXUtils {
    private static final String APP_ID = "";

    private static WXUtils wxUtils;
    private IWXAPI api;
    private Context context;

    public static WXUtils getInstance() {
        if (wxUtils == null) {
            wxUtils = new WXUtils();
        }
        return wxUtils;
    }

    public IWXAPI regToWx(Context context) {
        api = WXAPIFactory.createWXAPI(context, APP_ID, false);
        api.registerApp(APP_ID);
        return api;
    }
    SendMessageToWX.Req req;
    public void sendReq(/*SendMessageToWX.Req req*/Context context) {
        this.context = context;
        dialog();
        WXTextObject textObject = new WXTextObject();
        textObject.text = "洗嘛...我又没说不洗";

        WXMediaMessage msg = new WXMediaMessage();
        msg.mediaObject = textObject;
        msg.description = "厉害了";

        req = new SendMessageToWX.Req();
        req.transaction = String.valueOf(System.currentTimeMillis());
        req.message = msg;
        req.scene = SendMessageToWX.Req.WXSceneSession;
//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                try {
//                    Thread.sleep(3000);
//
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//            }
//        }).start();
        dialog.dismiss();
        api.sendReq(req);
    }

    public void sendResp(Activity context) {
        WXTextObject textObject = new WXTextObject();
        textObject.text = "厉害了丹丹";

        WXMediaMessage msg = new WXMediaMessage(textObject);
        msg.description = "哈哈---";

        GetMessageFromWX.Resp resp = new GetMessageFromWX.Resp();
        resp.transaction = new GetMessageFromWX.Req(new Bundle()).transaction;
        resp.message = msg;

        api.sendResp(resp);
    }

    Dialog dialog;

    public void dialog() {
        dialog = new DialogBase(context);
        dialog.show();
    }
}
