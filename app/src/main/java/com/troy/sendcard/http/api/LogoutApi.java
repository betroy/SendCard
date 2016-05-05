package com.troy.sendcard.http.api;

import com.facebook.stetho.okhttp3.StethoInterceptor;
import com.troy.sendcard.config.Constant;
import com.troy.sendcard.http.service.LogoutService;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by chenlongfei on 16/4/21.
 */
public class LogoutApi {
    private LogoutService mLogoutService;

    public LogoutApi() {
        OkHttpClient okHttpClient = new OkHttpClient.Builder().addNetworkInterceptor(new StethoInterceptor()).build();
        Retrofit retrofit = new Retrofit
                .Builder()
                .client(okHttpClient)
                .baseUrl(Constant.getHost())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        mLogoutService=retrofit.create(LogoutService.class);
    }

    public LogoutService getService() {
        return mLogoutService;
    }

    public static LogoutApi getApi() {
        return ApiHolder.logoutApi;
    }

    public static class ApiHolder {
        public static LogoutApi logoutApi = new LogoutApi();
    }
}
