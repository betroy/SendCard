package com.troy.sendcard.http.service;

import com.troy.sendcard.bean.respone.CancleHandledResult;
import com.troy.sendcard.config.Constant;

import java.util.Map;

import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import rx.Observable;

/**
 * Created by chenlongfei on 16/4/20.
 */
public interface CancleHandledService {
    @FormUrlEncoded
    @POST(Constant.Api.APPLYUNLOCK)
    Observable<CancleHandledResult> getResult(@FieldMap Map<String, String> map);
}
