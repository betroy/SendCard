package com.troy.sendcard.http.service;

import com.troy.sendcard.bean.response.CardInfoSubmitResult;
import com.troy.sendcard.config.Constant;

import java.util.Map;

import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import rx.Observable;

/**
 * Created by chenlongfei on 16/4/20.
 */
public interface CardInfoSubmitService {
    @FormUrlEncoded
    @POST(Constant.Api.APPLYSUBMISSION)
    Observable<CardInfoSubmitResult> getResult(@FieldMap Map<String, String> map);
}
