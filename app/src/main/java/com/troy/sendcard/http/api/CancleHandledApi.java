package com.troy.sendcard.http.api;

import com.facebook.stetho.okhttp3.StethoInterceptor;
import com.troy.sendcard.config.Constant;
import com.troy.sendcard.http.service.CancleHandledService;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by chenlongfei on 16/4/20.
 */
public class CancleHandledApi {
    private CancleHandledService mCancleHandledService;

    public CancleHandledApi() {
        OkHttpClient okHttpClient = new OkHttpClient.Builder().addNetworkInterceptor(new StethoInterceptor()).build();
        Retrofit retrofit = new Retrofit
                .Builder()
                .client(okHttpClient)
                .baseUrl(Constant.getHost())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        mCancleHandledService = retrofit.create(CancleHandledService.class);
    }

    public CancleHandledService getService() {
        return mCancleHandledService;
    }

    public static CancleHandledApi getApi() {
        return ApiHolder.handledApi;
    }

    public static class ApiHolder {
        public static CancleHandledApi handledApi = new CancleHandledApi();
    }
}
