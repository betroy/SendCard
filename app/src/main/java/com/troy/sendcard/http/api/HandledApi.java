package com.troy.sendcard.http.api;

import com.troy.sendcard.config.Constant;
import com.troy.sendcard.http.service.HandledService;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by chenlongfei on 16/4/12.
 */
public class HandledApi {
    private HandledService mHandledService;

    public HandledApi() {
        Retrofit retrofit = new Retrofit.Builder().baseUrl(Constant.getHost())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        mHandledService = retrofit.create(HandledService.class);
    }

    public HandledService getService() {
        return mHandledService;
    }

    public static HandledApi getApi() {
        return ApiHolder.handledApi;
    }

    public static class ApiHolder {
        public static HandledApi handledApi = new HandledApi();
    }

}
