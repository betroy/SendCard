package com.troy.sendcard.presenter.impl;

import android.content.Context;

import com.troy.sendcard.R;
import com.troy.sendcard.interactor.ILoginInteractor;
import com.troy.sendcard.interactor.Impl.LoginInteractorImpl;
import com.troy.sendcard.presenter.ILoginPresenter;
import com.troy.sendcard.view.LoginView;

/**
 * Created by chenlongfei on 16/4/2.
 */
public class LoginPresenterImpl implements ILoginPresenter, ILoginInteractor.OnLoginListener {
    private Context context;
    private LoginView loginView;
    private ILoginInteractor loginInteractor;

    public LoginPresenterImpl(LoginView loginView, Context context) {
        this.context = context;
        this.loginView = loginView;
        loginInteractor = new LoginInteractorImpl(this);
    }

    @Override
    public void requestLogin(String username, String password) {
        loginView.showLoading(context.getString(R.string.login_loading_tips));
        loginInteractor.Login(username, password);
    }

    @Override
    public void onSuccess() {
        loginView.hideLoading();
        loginView.showToast(context.getString(R.string.login_success));
        loginView.loginSuccess();
    }

    @Override
    public void onFail() {
        loginView.hideLoading();
        loginView.showToast(context.getString(R.string.login_fail));
        loginView.loginFail();
    }
}
