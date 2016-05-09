package com.troy.sendcard.http.service;

import com.troy.sendcard.bean.response.UnhandledResult;
import com.troy.sendcard.config.Constant;

import java.util.Map;

import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import rx.Observable;

/**
 * Created by chenlongfei on 16/4/12.
 */
public interface UnhandledService {
    @FormUrlEncoded
    @POST(Constant.Api.UNHANDLED)
    Observable<UnhandledResult> getResult(@FieldMap Map<String, String> map);
}
