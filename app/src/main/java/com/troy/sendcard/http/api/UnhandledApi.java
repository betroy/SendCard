package com.troy.sendcard.http.api;

import com.facebook.stetho.okhttp3.StethoInterceptor;
import com.troy.sendcard.config.Constant;
import com.troy.sendcard.http.service.UnhandledService;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by chenlongfei on 16/4/12.
 */
public class UnhandledApi {
    private UnhandledService mUnhandledService;

    public UnhandledApi() {
        OkHttpClient okHttpClient = new OkHttpClient.Builder().addNetworkInterceptor(new StethoInterceptor()).build();
        Retrofit retrofit = new Retrofit
                .Builder()
                .client(okHttpClient)
                .baseUrl(Constant.getHost())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        mUnhandledService = retrofit.create(UnhandledService.class);
    }

    public static UnhandledApi getApi() {
        return ApiHolder.apiHolder;
    }

    public UnhandledService getService() {
        return mUnhandledService;
    }

    public static class ApiHolder {
        public static UnhandledApi apiHolder = new UnhandledApi();
    }
}
