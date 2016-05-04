package com.troy.sendcard.http.api;

import com.troy.sendcard.config.Constant;
import com.troy.sendcard.http.service.TodoService;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by chenlongfei on 16/4/11.
 */
public class TodoApi {
    private TodoService mTodoService;

    public TodoApi() {
        Retrofit retrofit = new Retrofit.Builder().baseUrl(Constant.getHost())
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
