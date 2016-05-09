package com.troy.sendcard.interactor.Impl;

import com.troy.sendcard.SendCardApp;
import com.troy.sendcard.bean.request.SendCardInfoParam;
import com.troy.sendcard.bean.response.SendCardInfoResult;
import com.troy.sendcard.config.Constant;
import com.troy.sendcard.http.api.SendCardInfoApi;
import com.troy.sendcard.interactor.ISendCardInfoInteractor;
import com.troy.sendcard.util.SignUtil;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by chenlongfei on 16/4/13.
 */
public class SendCardInfoInteractorImpl implements ISendCardInfoInteractor {
    private OnSendCardInfoListener mSendCardInfoListener;

    public SendCardInfoInteractorImpl(ISendCardInfoInteractor.OnSendCardInfoListener sendCardInfoListener) {
        mSendCardInfoListener = sendCardInfoListener;
    }

    @Override
    public void getSendCardInfo(String applyId) {
        SendCardApp.UserInfo userInfo = new SendCardApp.UserInfo();
        SendCardInfoParam param = new SendCardInfoParam();
        param.setUser_id(userInfo.getUserId());
        param.setSecret(userInfo.getSecret());
        param.setTime(System.currentTimeMillis() + "");
        param.setApply_id(applyId);
        param.setSign(SignUtil.sign(param.createSendCardInfoParam()));

        SendCardInfoApi.getApi().getService().getResult(param.createSendCardInfoParam())
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<SendCardInfoResult>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        mSendCardInfoListener.onFail(Constant.ResponeStatus.NET_ERROR, "");
                    }

                    @Override
                    public void onNext(SendCardInfoResult sendCardInfoResult) {
                        if (sendCardInfoResult.getCode() == Constant.ResponeStatus.OK) {
                            mSendCardInfoListener.onSuccess(sendCardInfoResult.getData());
                        } else {
                            mSendCardInfoListener.onFail(sendCardInfoResult.getCode(), sendCardInfoResult.getMsg());
                        }
                    }
                });
    }
}
