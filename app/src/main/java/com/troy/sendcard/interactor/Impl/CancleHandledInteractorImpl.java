package com.troy.sendcard.interactor.Impl;

import android.util.Log;

import com.troy.sendcard.SendCardApp;
import com.troy.sendcard.bean.request.CancleHandledParam;
import com.troy.sendcard.bean.response.CancleHandledResult;
import com.troy.sendcard.config.Constant;
import com.troy.sendcard.http.api.CancleHandledApi;
import com.troy.sendcard.interactor.ICancleHandledInteractor;
import com.troy.sendcard.util.SignUtil;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by chenlongfei on 16/4/20.
 */
public class CancleHandledInteractorImpl implements ICancleHandledInteractor {
    private ICancleHandledInteractor.CancleHandledListener mCancleHandledListener;

    public CancleHandledInteractorImpl(CancleHandledListener cancleHandledListener) {
        mCancleHandledListener = cancleHandledListener;
    }

    @Override
    public void cancelHandled(String applyId) {
        SendCardApp.UserInfo userInfo = SendCardApp.getUserInfo();
        CancleHandledParam param = new CancleHandledParam();
        param.setUser_id(userInfo.getUserId());
        param.setSecret(userInfo.getSecret());
        param.setApply_id(applyId);
        param.setTime(System.currentTimeMillis() + "");
        param.setSign(SignUtil.sign(param.crateCancleHandledParam()));

        CancleHandledApi.getApi().getService().getResult(param.crateCancleHandledParam())
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<CancleHandledResult>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        mCancleHandledListener.onFail();
                    }

                    @Override
                    public void onNext(CancleHandledResult cancleHandledResult) {
                        Log.i("Troy","msg:"+cancleHandledResult.getMsg());
                        if (cancleHandledResult.getCode() == Constant.ResponeStatus.OK) {
                            mCancleHandledListener.onSuccess();
                        } else {
                            mCancleHandledListener.onFail();
                        }
                    }
                });
    }
}
