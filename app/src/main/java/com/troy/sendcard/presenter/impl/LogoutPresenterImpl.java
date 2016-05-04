package com.troy.sendcard.presenter.impl;

import com.troy.sendcard.interactor.ILoginInteractor;
import com.troy.sendcard.interactor.ILogoutInteractor;
import com.troy.sendcard.interactor.Impl.LogoutInteractorImpl;
import com.troy.sendcard.presenter.ILogoutPresenter;
import com.troy.sendcard.view.LogoutView;

/**
 * Created by chenlongfei on 16/4/21.
 */
public class LogoutPresenterImpl implements ILogoutPresenter, ILoginInteractor.OnLoginListener {
    private ILogoutInteractor mLogoutInteractor;
    private LogoutView mLogoutView;

    public LogoutPresenterImpl(LogoutView logoutView) {
        mLogoutInteractor = new LogoutInteractorImpl(this);
        mLogoutView = logoutView;
    }

    @Override
    public void logout() {
        mLogoutInteractor.requestLogout();
    }

    @Override
    public void onSuccess() {
        mLogoutView.onLogoutSuccess();
    }

    @Override
    public void onFail() {
        mLogoutView.onLogoutFail();
    }
}
