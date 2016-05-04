package com.troy.sendcard.http.api;

import com.troy.sendcard.config.Constant;
import com.troy.sendcard.http.service.UnhandledService;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by chenlongfei on 16/4/12.
 */
public class UnhandledApi {
    private UnhandledService mUnhandledService;

    public UnhandledApi() {
        Retrofit retrofit = new Retrofit.Builder().baseUrl(Constant.getHost())
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
