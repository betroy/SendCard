package com.troy.sendcard.interactor.Impl;

import android.util.Log;

import com.troy.sendcard.SendCardApp;
import com.troy.sendcard.bean.request.LoginParam;
import com.troy.sendcard.bean.respone.LoginResult;
import com.troy.sendcard.config.Constant;
import com.troy.sendcard.http.api.LoginApi;
import com.troy.sendcard.interactor.ILoginInteractor;
import com.troy.sendcard.util.SignUtil;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by chenlongfei on 16/4/2.
 */
public class LoginInteractorImpl implements ILoginInteractor {
    private OnLoginListener loginListener;

    public LoginInteractorImpl(OnLoginListener loginListener) {
        this.loginListener = loginListener;
    }

    @Override
    public void Login(final String username, final String password) {
        LoginParam param = new LoginParam();
        param.setTime(System.currentTimeMillis() + "");
        param.setUrl_req_type(LoginParam.REQ_TYPE);
        param.setUser_account(username);
        param.setUser_pwd(password);
        param.setSign(SignUtil.sign(param.createLoginParams()));
        LoginApi.getApi().getService().getResult(param.createLoginParams())
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<LoginResult>() {
                    @Override
                    public void onCompleted() {
                    }

                    @Override
                    public void onError(Throwable e) {
                        loginListener.onFail();
                        Log.i("Troy", "onError:" + e);
                    }

                    @Override
                    public void onNext(LoginResult loginResult) {
                        if (loginResult.getCode() == Constant.ResponeStatus.OK) {
                            LoginResult.UserDataEntity userDataEntity = loginResult.getData();
                            if (userDataEntity != null) {
                                loginListener.onSuccess();
                                //保存用户名、密码、userid、secret
                                SendCardApp.UserInfo userInfo = SendCardApp.getUserInfo();
                                userInfo.setUserName(username);
                                userInfo.setPassWord(password);
                                userInfo.setUserId(userDataEntity.getUser_id());
                                userInfo.setSecret(userDataEntity.getSecret());
                            } else {
                                loginListener.onFail();
                            }
                        } else {
                            loginListener.onFail();
                        }
                    }
                });
    }
}
