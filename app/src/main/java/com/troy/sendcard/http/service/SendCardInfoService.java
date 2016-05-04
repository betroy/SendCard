package com.troy.sendcard.http.service;

import com.troy.sendcard.bean.respone.SendCardInfoResult;
import com.troy.sendcard.config.Constant;

import java.util.Map;

import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import rx.Observable;

/**
 * Created by chenlongfei on 16/4/13.
 */
public interface SendCardInfoService {
    @FormUrlEncoded
    @POST(Constant.Api.APPLYFILL)
    Observable<SendCardInfoResult> getResult(@FieldMap Map<String, String> map);
}
