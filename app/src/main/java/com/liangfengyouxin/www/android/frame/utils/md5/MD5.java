package com.liangfengyouxin.www.android.frame.utils.md5;

import android.util.Log;

import com.liangfengyouxin.www.android.frame.contants.Constant;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/*
 * Created by zhulianggang on 16/5/24.
 */
public final class MD5 {

    public static String hexdigest(String string) {
        string = Constant.SECRET_KEY + string;
        if (string == null) {
            return null;
        }
        String s = null;
        final char hexDigits[] = { '0', '1', '2', '3', '4', '5', '6', '7', '8',
                '9', 'a', 'b', 'c', 'd', 'e', 'f' };
        try {
            final MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(string.getBytes());
            final byte tmp[] = md.digest();
            final char str[] = new char[16 * 2];
            int k = 0;
            for (int i = 0; i < 16; i++) {
                final byte byte0 = tmp[i];
                str[k++] = hexDigits[byte0 >>> 4 & 0xf];
                str[k++] = hexDigits[byte0 & 0xf];
            }
            s = new String(str);
        } catch (final NoSuchAlgorithmException e) {
            Log.e("MD5", e.toString());
        }
        return s;
    }

    public static String hexdigest_16(String string) {
        String s = hexdigest(string);
        if (null != s) {
            s = s.substring(8, 24);
        }
        return s;
    }
}
