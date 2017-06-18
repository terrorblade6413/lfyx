package com.liangfengyouxin.www.android.frame.network;

import com.liangfengyouxin.www.android.frame.bean.WrapperBean;
import com.liangfengyouxin.www.android.frame.bean.WrapperBean2;
import com.liangfengyouxin.www.android.frame.bean.home.ImageBean;
import com.liangfengyouxin.www.android.frame.bean.home.TextBean;
import com.liangfengyouxin.www.android.frame.bean.user.RegisterBean;

import java.util.List;

import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;
import retrofit2.http.Url;
import rx.Observable;

/**
 * Created by lin.woo on 2017/5/19.
 */

public interface ApiService {
    String Url = "http://ec2-52-78-19-242.ap-northeast-2.compute.amazonaws.com/";
    String Url2 = "http://120.132.26.162:82";

    //获取图片type t
    @GET("request_wtys_ios.php")
    Observable<WrapperBean<List<ImageBean>>> getImageList(@Query("leixing") String type, @Query("lastdi") String id, @Query("dizeng") String order);

    //获取文字type w
    @GET("request_wtys_ios.php")
    Observable<WrapperBean<List<TextBean>>> getTextList(@Query("leixing") String type, @Query("lastdi") String id, @Query("dizeng") String order);

    @POST("/")
    @FormUrlEncoded
    Observable<WrapperBean2<RegisterBean>> register(@Field("api_type") String apiType, @Field("api_method") String apiMethod, @Field("api_dev") String serviceId, @Field("shouji") String phone,
                                                    @Field("password") String password, @Field("user_type") int type, @Field("api_sign") String url);

//    //创建账号
//    @FormUrlEncoded
//    @POST("api/rest")
//    Observable<WrapperEntity> getRandomUser(@Field("args") String args);


}