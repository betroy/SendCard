package com.troy.sendcard.interactor.Impl;

import android.util.Log;

import com.troy.sendcard.SendCardApp;
import com.troy.sendcard.bean.request.LogoutParam;
import com.troy.sendcard.bean.respone.LogoutResult;
import com.troy.sendcard.config.Constant;
import com.troy.sendcard.http.api.LogoutApi;
import com.troy.sendcard.interactor.ILoginInteractor;
import com.troy.sendcard.interactor.ILogoutInteractor;
import com.troy.sendcard.util.SignUtil;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by chenlongfei on 16/4/21.
 */
public class LogoutInteractorImpl implements ILogoutInteractor {
    private ILoginInteractor.OnLoginListener mLoginListener;

    public LogoutInteractorImpl(ILoginInteractor.OnLoginListener onLoginListener) {
        mLoginListener = onLoginListener;
    }

    @Override
    public void requestLogout() {
        SendCardApp.UserInfo userInfo = SendCardApp.getUserInfo();
        LogoutParam param = new LogoutParam();
        param.setUser_id(userInfo.getUserId());
        param.setTime(System.currentTimeMillis() + "");
        param.setSecret(userInfo.getSecret());
        param.setSign(SignUtil.sign(param.createLogoutParam()));

        LogoutApi.getApi().getService().getResult(param.createLogoutParam())
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<LogoutResult>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.i("Troy","onError:"+e);
                        mLoginListener.onFail();
                    }

                    @Override
                    public void onNext(LogoutResult logoutResult) {
                        Log.i("Troy","onNext");
                        if (logoutResult.getCode() == Constant.ResponeStatus.OK) {
                            SendCardApp.UserInfo userInfo = SendCardApp.getUserInfo();
                            userInfo.setSecret("");
                            userInfo.setUserId("");
                            userInfo.setUserName("");
                            userInfo.setPassWord("");
                            mLoginListener.onSuccess();
                        } else {
                            mLoginListener.onFail();
                        }
                    }
                });
    }
}
