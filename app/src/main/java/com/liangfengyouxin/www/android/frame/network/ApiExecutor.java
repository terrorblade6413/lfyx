package com.liangfengyouxin.www.android.frame.network;

import android.util.Log;
import android.util.TimeUtils;

import com.liangfengyouxin.www.android.frame.application.LXApplication;
import com.liangfengyouxin.www.android.frame.utils.ToastLX;

import java.io.IOException;
import java.net.SocketTimeoutException;
import java.net.URLDecoder;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import java.util.regex.Matcher;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import okio.Timeout;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by lin.woo on 2017/5/19.
 */
public class ApiExecutor {

    private static class SingletonHolder {
        private static final ApiService INSTANCE = create();
    }

    public static final ApiService getInstance() {
        return SingletonHolder.INSTANCE;
    }
    public static final ApiService getInstance2() {
        return create2();
    }

    private static HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor()
            .setLevel(HttpLoggingInterceptor.Level.BODY);
    private static OkHttpClient httpClient = new OkHttpClient.Builder()
            .connectTimeout(1, TimeUnit.SECONDS)
            .writeTimeout(1, TimeUnit.SECONDS)
            .readTimeout(1,TimeUnit.SECONDS)
            .addInterceptor(loggingInterceptor)
            .addInterceptor(new ParamsInterceptor())
            .build();

    public static ApiService create() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(ApiService.Url)
                .client(httpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();
        return retrofit.create(ApiService.class);
    }

    public static ApiService create2() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(ApiService.Url2)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .client(httpClient)
                .build();
        return retrofit.create(ApiService.class);
    }

    static class ParamsInterceptor implements Interceptor {

        @Override
        public Response intercept(Chain chain) throws IOException {
            Request oldRequest = chain.request();
            HttpUrl.Builder authorizedUrlBuilder = oldRequest.url()
                    .newBuilder()
                    .scheme(oldRequest.url().scheme())
                    .host(oldRequest.url().host());

            Request newRequest = oldRequest.newBuilder()
                    .header("token","http://ec2-52-78-19-242.ap-northeast-2.compute.amazonaws.com/request_qiniu_token.php")
                    .method(oldRequest.method(), oldRequest.body())
                    .url(authorizedUrlBuilder.build())
                    .build();
            Response response = null;
            try {
                response = chain.proceed(newRequest);
            }catch (SocketTimeoutException e){
                Log.d("=========","请求超时....");

                return response;
            }
            return response;
        }
    }


}



