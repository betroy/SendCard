package com.troy.sendcard.http.api;

import com.troy.sendcard.config.Constant;
import com.troy.sendcard.http.service.SendCardInfoService;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by chenlongfei on 16/4/13.
 */
public class SendCardInfoApi {
    private SendCardInfoService mSendCardInfoService;

    public SendCardInfoApi() {
        Retrofit retrofit = new Retrofit.Builder().baseUrl(Constant.getHost())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        mSendCardInfoService = retrofit.create(SendCardInfoService.class);
    }

    public static SendCardInfoApi getApi() {
        return ApiHolder.sendCardInfoApi;
    }

    public SendCardInfoService getService() {
        return mSendCardInfoService;
    }

    public static class ApiHolder {
        public static SendCardInfoApi sendCardInfoApi = new SendCardInfoApi();
    }
}
