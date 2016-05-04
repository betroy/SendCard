package com.troy.sendcard.interactor.Impl;

import com.troy.sendcard.R;
import com.troy.sendcard.SendCardApp;
import com.troy.sendcard.bean.request.CardValidateParam;
import com.troy.sendcard.bean.respone.CardVaildateResult;
import com.troy.sendcard.config.Constant;
import com.troy.sendcard.http.api.CardVaildateApi;
import com.troy.sendcard.interactor.ICardVaildateInteractor;
import com.troy.sendcard.util.SignUtil;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by chenlongfei on 16/4/21.
 */
public class CardVaildateInteractorImpl implements ICardVaildateInteractor {
    private CardVaildateListener mCardVaildateListener;

    public CardVaildateInteractorImpl(ICardVaildateInteractor.CardVaildateListener cardVaildateListener) {
        mCardVaildateListener = cardVaildateListener;
    }

    @Override
    public void requestCardVaildate(String cardno, final int index) {
        SendCardApp.UserInfo userInfo = new SendCardApp.UserInfo();
        CardValidateParam param = new CardValidateParam();
        param.setUser_id(userInfo.getUserId());
        param.setSecret(userInfo.getSecret());
        param.setTime(System.currentTimeMillis() + "");
        param.setCard_no(cardno);
        param.setSign(SignUtil.sign(param.createParam()));
        CardVaildateApi.getApi().getService().getResult(param.createParam())
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<CardVaildateResult>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        mCardVaildateListener.onFail(index, SendCardApp.mContext.getString(R.string.network_error));
                    }

                    @Override
                    public void onNext(CardVaildateResult cardVaildateResult) {
                        if (cardVaildateResult.getCode() == Constant.ResponeStatus.OK) {
                            mCardVaildateListener.onSuccess(index);
                        } else {
                            mCardVaildateListener.onFail(index, cardVaildateResult.getMsg());
                        }
                    }
                });
    }
}
