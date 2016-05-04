package com.troy.sendcard.http.service;

import com.troy.sendcard.bean.respone.CardVaildateResult;
import com.troy.sendcard.config.Constant;

import java.util.Map;

import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import rx.Observable;

/**
 * Created by chenlongfei on 16/4/21.
 */
public interface CardVaildateService {
    @FormUrlEncoded
    @POST(Constant.Api.CARDVALIDATE)
    Observable<CardVaildateResult> getResult(@FieldMap Map<String, String> map);
}
