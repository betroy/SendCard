package com.troy.sendcard.http.api;

import com.troy.sendcard.config.Constant;
import com.troy.sendcard.http.service.CancleHandledService;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by chenlongfei on 16/4/20.
 */
public class CancleHandledApi {
    private CancleHandledService mCancleHandledService;

    public CancleHandledApi() {
        Retrofit retrofit = new Retrofit.Builder().baseUrl(Constant.getHost())
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
