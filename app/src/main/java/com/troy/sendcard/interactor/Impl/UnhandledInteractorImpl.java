package com.troy.sendcard.interactor.Impl;

import com.troy.sendcard.SendCardApp;
import com.troy.sendcard.bean.request.UnhandledParam;
import com.troy.sendcard.bean.response.UnhandledResult;
import com.troy.sendcard.config.Constant;
import com.troy.sendcard.http.api.UnhandledApi;
import com.troy.sendcard.interactor.IUnhandledInteractor;
import com.troy.sendcard.util.SignUtil;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by chenlongfei on 16/4/12.
 */
public class UnhandledInteractorImpl implements IUnhandledInteractor {
    private IUnhandledInteractor.UnhandledListener unhandledListener;

    public UnhandledInteractorImpl(UnhandledListener unhandledListener) {
        this.unhandledListener = unhandledListener;
    }

    @Override
    public void getUnhandledList(int page, final int type) {
        SendCardApp.UserInfo userInfo = new SendCardApp.UserInfo();
        UnhandledParam param = new UnhandledParam();
        param.setUser_id(userInfo.getUserId());
        param.setSecret(userInfo.getSecret());
        param.setTime(System.currentTimeMillis() + "");
        param.setPage(page + "");
        param.setPageSize(Constant.PAGE_SIZE);
        param.setSign(SignUtil.sign(param.createUnhandledParam()));

        UnhandledApi.getApi().getService().getResult(param.createUnhandledParam())
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<UnhandledResult>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        unhandledListener.onFail();
                    }

                    @Override
                    public void onNext(UnhandledResult unhandledResult) {
                        if (unhandledResult.getCode() == Constant.ResponeStatus.OK) {
                            if (type == Constant.DATA_REFRESH) {
                                unhandledListener.onSuccess(unhandledResult.getData());
                            } else if (type == Constant.DATA_LOADING_MORE) {
                                unhandledListener.onLoadMoreSuccess(unhandledResult.getData());
                            }
                        } else {
                            if (type == Constant.DATA_REFRESH) {
                                unhandledListener.onFail();
                            } else if (type == Constant.DATA_LOADING_MORE) {
                                unhandledListener.onLoadMoreFail();
                            }
                        }
                    }
                });
    }
}
