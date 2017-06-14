package com.liangfengyouxin.www.android.frame.utils;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.text.TextUtils;
import android.widget.Toast;

import com.liangfengyouxin.www.android.R;
import com.umeng.socialize.ShareAction;
import com.umeng.socialize.UMShareListener;
import com.umeng.socialize.bean.SHARE_MEDIA;
import com.umeng.socialize.media.UMImage;

import java.io.File;
import java.util.List;
import java.util.Locale;

/**
 * 需要在onActivityResult中添加
 * UMShareAPI.get(this).onActivityResult(requestCode, resultCode, data);
 * ---------------------------------------------------------------------------
 * Created by wulin on 2016/6/21.
 */
public class UMSharedUtil {
    private Activity myContext;
    private ShareAction shareAction;
    private static UMSharedUtil umSharedUtil;

    private UMSharedUtil() {
    }

    private String title;
    private String content;
    private String imgUrl;
    private int imgId;
    private String link;

    public static UMSharedUtil getInstance() {
        if (null == umSharedUtil) {
            umSharedUtil = new UMSharedUtil();
        }
        return umSharedUtil;
    }

    public UMSharedUtil setContext(Activity activity) {
        this.myContext = activity;
        return umSharedUtil;
    }

    public void shareWX() {
        build(SHARE_MEDIA.WEIXIN);

    }

    public void shareQQ() {
        build(SHARE_MEDIA.QQ);
    }

    public void shareQzone() {
        build(SHARE_MEDIA.QZONE);
    }

    public void shareWXCircle() {
        build(SHARE_MEDIA.WEIXIN_CIRCLE);
    }

    /**
     * 注意新浪分享的title是不显示的，URL链接只能加在分享文字后显示，并且需要确保withText()不为空
     */
    public void shareSina() {
        build(SHARE_MEDIA.SINA);
    }

    private void build(SHARE_MEDIA shareMedia) {
        shareAction = new ShareAction(myContext);
        if (null != shareListener) {
            shareAction.setPlatform(shareMedia).setCallback(shareListener);
        } else
            shareAction.setPlatform(shareMedia).setCallback(new MyShareListener());

        if (TextUtils.isEmpty(title) && TextUtils.isEmpty(content)
                && TextUtils.isEmpty(link) && TextUtils.isEmpty(imgUrl) && imgId == 0) {
            title = strTitle;
            content = strContent;
            link = strLink;
            imgId = intImgId;
        }
        if (SHARE_MEDIA.SINA.equals(shareMedia)) {
            shareAction.withText((TextUtils.isEmpty(title) ? myContext.getString(R.string.app_name) : title) + (TextUtils.isEmpty(content) ? "" : content) + (TextUtils.isEmpty(link) ? "" : link));
        } else if (SHARE_MEDIA.WEIXIN.equals(shareMedia) || SHARE_MEDIA.WEIXIN_CIRCLE.equals(shareMedia)) {
            shareAction.withTitle((TextUtils.isEmpty(title) ? myContext.getString(R.string.app_name) : title));
            shareAction.withText((TextUtils.isEmpty(content) ? " " : content));
            if (!TextUtils.isEmpty(link))
                shareAction.withTargetUrl(link);
            if (TextUtils.isEmpty(imgUrl) && imgId == 0) {
                imgId = intImgId;
            } else {

            }
        } else {
            shareAction.withTitle(TextUtils.isEmpty(title) ? myContext.getString(R.string.app_name) : title);
            shareAction.withText((TextUtils.isEmpty(content) ? " " : content));
            if (!TextUtils.isEmpty(link))
                shareAction.withTargetUrl(link);
        }

        if (SHARE_MEDIA.SINA.equals(shareMedia) && !isWeiboInstalled(myContext)) {

        } else {
            if (!TextUtils.isEmpty(imgUrl)) {
                UMImage umImage = new UMImage(myContext, imgUrl);
                shareAction.withMedia(umImage);
            } else {
                imgId = intImgId;
            }
//            if (!shareMedia.equals(SHARE_MEDIA.QZONE) && imgId > 0) {
//                UMImage umImage = new UMImage(myContext, imgId);
//                shareAction.withMedia(umImage);
//            }
        }
        shareAction.share();
        initData();
    }


    class MyShareListener implements UMShareListener {
        @Override
        public void onResult(SHARE_MEDIA platform) {
            Toast.makeText(myContext, platformName(platform) + " 分享成功啦", Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onError(SHARE_MEDIA platform, Throwable t) {
            Toast.makeText(myContext, platformName(platform) + " 分享失败啦", Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onCancel(SHARE_MEDIA platform) {
            Toast.makeText(myContext, platformName(platform) + " 分享取消了", Toast.LENGTH_SHORT).show();
        }
    }

    /**
     * Sina 不支持<br>
     * QQ好友 支持，最多只显示20个字符<br>
     * QQ空间 支持，最多只显示20个字符<br>
     * 微信好友  支持
     *
     * @param title
     * @return
     */
    public UMSharedUtil setTitle(String title) {
        this.title = title;
        return this;
    }

    /**
     * Sina 新浪编辑页面，限制最多140个字符（1个数字或者英文算半个字符，中文算一个字符）<br>
     * QQ好友 最多只显示30个字符<br>
     * QQ空间 最多只显示30个字符<br>
     * 微信好友  支持超长字符和特殊字符
     *
     * @param content
     * @return
     */
    public UMSharedUtil setContent(String content) {
        this.content = content;
        return this;
    }

    /**
     * Sina 支持JPG\PNG\GIF格式的本地及URL图片，GIF图片无动态效果<br>
     * QQ好友 支持JPG\PNG\GIF格式的本地及URL图片，URL形式及resource内的GIF图片有动态效果，SD卡中的GIF图片无动态效果<br>
     * QQ空间 不支持纯图片分享，支持JPG\PNG\GIF格式的本地及URL图片，URL形式及resource内的GIF图片有动态效果，SD卡中的GIF图片无动态效果<br>
     * 微信好友  支持JPG\PNG\GIF格式的本地及URL图片，图文分享类型时GIF图片无动态效果
     *
     * @param imgUrl
     * @return
     */
    public UMSharedUtil setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
        return this;
    }

    public UMSharedUtil setImgUrl(int imgId) {
        this.imgId = imgId;
        return this;
    }

    /**
     * SINA TatgetUrl只能加在文字中间或后面，当同时传递视频/音频时无效 <br>
     * QQ 	http链接分享后无法打开targetURL，https链接可以打开，纯图片分享时点击无效<br>
     * QQ空间 不支持www开头的URL链接<br>
     * 微信好友 纯图片分享时targetURL无效
     *
     * @param link
     * @return
     */
    public UMSharedUtil setLink(String link) {
        this.link = link;
        return this;
    }

    public UMSharedUtil normalShare(String title, String content, String imgUrl, String link) {
        this.title = title;
        this.content = content;
        this.imgUrl = imgUrl;
        this.link = link;
        return this;
    }

    public UMSharedUtil normalShare(String title, String content, int imgId, String link) {
        this.title = title;
        this.content = content;
        this.imgId = imgId;
        this.link = link;
        return this;
    }

    UMShareListener shareListener;

    public void setUMShareListener(UMShareListener shareListener) {
        this.shareListener = shareListener;
    }


    private void initData() {
        title = "";
        content = "";
        imgUrl = "";
        imgId = 0;
        link = "";
        shareListener = null;
    }


    public static boolean isWeiboInstalled(@NonNull Context context) {
        PackageManager pm;
        if ((pm = context.getApplicationContext().getPackageManager()) == null) {
            return false;
        }
        List<PackageInfo> packages = pm.getInstalledPackages(0);
        for (PackageInfo info : packages) {
            String name = info.packageName.toLowerCase(Locale.ENGLISH);
            if ("com.sina.weibo".equals(name)) {
                return true;
            }
        }
        return false;
    }

    public static String platformName(SHARE_MEDIA platform) {
        String name = "";
        switch (platform) {
            case WEIXIN:
                name = "微信好友";
                break;
            case WEIXIN_CIRCLE:
                name = "微信朋友圈";
                break;
            case SINA:
                name = "新浪微博";
                break;
            default:
                name = platform.name();
                break;
        }
        return name;
    }

    public static final String strTitle = "分享标题";
    public static final String strContent = "分享文字。";
    public static final String strLink = "http://www.liangfengyouxin.com";
    public static final int intImgId = R.mipmap.ic_launcher;
}
