package com.troy.sendcard.http.api;

import com.facebook.stetho.okhttp3.StethoInterceptor;
import com.troy.sendcard.config.Constant;
import com.troy.sendcard.http.service.LoginService;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by chenlongfei on 16/4/7.
 */
public class LoginApi {
    private LoginService mLoginService;

    private LoginApi() {
        OkHttpClient okHttpClient = new OkHttpClient.Builder().addNetworkInterceptor(new StethoInterceptor()).build();
        Retrofit retrofit = new Retrofit
                .Builder()
                .client(okHttpClient)
                .baseUrl(Constant.getHost())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        mLoginService = retrofit.create(LoginService.class);
    }

    public static LoginApi getApi() {
        return ApiHolder.loginApi;
    }

    public static class ApiHolder {
        private static LoginApi loginApi = new LoginApi();
    }

    public LoginService getService() {
        return mLoginService;
    }
}
