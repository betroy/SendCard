package com.troy.sendcard.http.api;

import com.troy.sendcard.config.Constant;
import com.troy.sendcard.http.service.CardInfoSubmitService;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by chenlongfei on 16/4/20.
 */
public class CardInfoSubmitApi {
    private CardInfoSubmitService mSendCardInfoSubmitService;

    public CardInfoSubmitApi() {
        Retrofit retrofit = new Retrofit.Builder().baseUrl(Constant.getHost())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        mSendCardInfoSubmitService = retrofit.create(CardInfoSubmitService.class);
    }

    public static CardInfoSubmitApi getApi() {
        return ApiHolder.sendCardInfoSubmitApi;
    }

    public CardInfoSubmitService getService() {
        return mSendCardInfoSubmitService;
    }

    public static class ApiHolder {
        public static CardInfoSubmitApi sendCardInfoSubmitApi = new CardInfoSubmitApi();
    }
}
