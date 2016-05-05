package com.troy.sendcard.http.api;

import com.facebook.stetho.okhttp3.StethoInterceptor;
import com.troy.sendcard.config.Constant;
import com.troy.sendcard.http.service.TodoService;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by chenlongfei on 16/4/11.
 */
public class TodoApi {
    private TodoService mTodoService;

    public TodoApi() {
        OkHttpClient okHttpClient = new OkHttpClient.Builder().addNetworkInterceptor(new StethoInterceptor()).build();
        Retrofit retrofit = new Retrofit
                .Builder()
                .client(okHttpClient)
                .baseUrl(Constant.getHost())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        mTodoService = retrofit.create(TodoService.class);
    }

    public TodoService getService() {
        return mTodoService;
    }

    public static TodoApi getApi() {
        return ApiHolder.apiHolder;
    }

    public static class ApiHolder {
        private static TodoApi apiHolder = new TodoApi();
    }
}
