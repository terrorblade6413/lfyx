package com.liangfengyouxin.www.android.frame.network;

import com.liangfengyouxin.www.android.frame.bean.WrapperBean;
import com.liangfengyouxin.www.android.frame.bean.home.ImageBean;
import com.liangfengyouxin.www.android.frame.bean.home.TextBean;

import java.util.List;

import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by lin.woo on 2017/5/19.
 */

public interface ApiService {
    String Url = "http://ec2-52-78-19-242.ap-northeast-2.compute.amazonaws.com/";

    //获取图片type t
    @GET("request_wtys_ios.php")
    Observable<WrapperBean<List<ImageBean>>> getImageList(@Query("leixing") String type, @Query("lastdi") String id, @Query("dizeng") String order);
    //获取文字type w
    @GET("request_wtys_ios.php")
    Observable<WrapperBean<List<TextBean>>> getTextList(@Query("leixing") String type, @Query("lastdi") String id, @Query("dizeng") String order);

//    //创建账号
//    @FormUrlEncoded
//    @POST("api/rest")
//    Observable<WrapperEntity> getRandomUser(@Field("args") String args);


}