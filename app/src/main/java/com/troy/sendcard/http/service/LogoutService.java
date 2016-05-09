package com.troy.sendcard.http.service;

import com.troy.sendcard.bean.response.LogoutResult;
import com.troy.sendcard.config.Constant;

import java.util.Map;

import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import rx.Observable;

/**
 * Created by chenlongfei on 16/4/21.
 */
public interface LogoutService {
    @FormUrlEncoded
    @POST(Constant.Api.LOGOUT)
    Observable<LogoutResult> getResult(@FieldMap Map<String, String> map);
}
