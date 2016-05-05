package com.troy.sendcard.http.api;

import com.facebook.stetho.okhttp3.StethoInterceptor;
import com.troy.sendcard.config.Constant;
import com.troy.sendcard.http.service.CardVaildateService;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by chenlongfei on 16/4/21.
 */
public class CardVaildateApi {
    private CardVaildateService mCardVaildateService;

    public CardVaildateApi() {
        OkHttpClient okHttpClient = new OkHttpClient.Builder().addNetworkInterceptor(new StethoInterceptor()).build();
        Retrofit retrofit = new Retrofit
                .Builder()
                .client(okHttpClient)
                .baseUrl(Constant.getHost())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        mCardVaildateService = retrofit.create(CardVaildateService.class);
    }

    public CardVaildateService getService() {
        return mCardVaildateService;
    }

    public static CardVaildateApi getApi() {
        return ApiHolder.cardVaildateApi;
    }

    public static class ApiHolder {
        public static CardVaildateApi cardVaildateApi = new CardVaildateApi();
    }
}
