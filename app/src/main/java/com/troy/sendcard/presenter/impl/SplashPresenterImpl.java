package com.troy.sendcard.presenter.impl;

import android.content.Context;

import com.troy.sendcard.interactor.ISplashInteractor;
import com.troy.sendcard.interactor.Impl.SplashInteractorImpl;
import com.troy.sendcard.presenter.ISplashPresenter;
import com.troy.sendcard.view.SplashView;

/**
 * Created by chenlongfei on 16/4/4.
 */
public class SplashPresenterImpl implements ISplashPresenter {
    private SplashView mSplashView;
    private ISplashInteractor mSplashInteractor;
    private Context mContext;

    public SplashPresenterImpl(SplashView splashView, Context context) {
        mContext = context;
        mSplashView = splashView;
        mSplashInteractor = new SplashInteractorImpl(this);
    }

    @Override
    public void initData() {
        mSplashView.showSplash(mSplashInteractor.getSplashPicture(mContext));
        mSplashInteractor.countDown();
    }

    @Override
    public void showMain() {
        mSplashView.goToMain();
    }

    @Override
    public void showLogin() {
        mSplashView.goToLogin();
    }
}
