package com.troy.sendcard.http.service;

import com.troy.sendcard.bean.response.LoginResult;
import com.troy.sendcard.config.Constant;

import java.util.Map;

import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import rx.Observable;

/**
 * Created by chenlongfei on 16/4/8.
 */
public interface LoginService {
    @FormUrlEncoded
    @POST(Constant.Api.LOGIN)
    Observable<LoginResult> getResult(@FieldMap Map<String, String> map);
}
