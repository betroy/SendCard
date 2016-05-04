package com.troy.sendcard.interactor.Impl;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.CountDownTimer;
import android.text.TextUtils;

import com.troy.sendcard.R;
import com.troy.sendcard.SendCardApp;
import com.troy.sendcard.interactor.ISplashInteractor;
import com.troy.sendcard.presenter.ISplashPresenter;

/**
 * Created by chenlongfei on 16/4/4.
 */
public class SplashInteractorImpl implements ISplashInteractor {
    private ISplashPresenter mSplashPresenter;

    public SplashInteractorImpl(ISplashPresenter splashPresenter) {
        mSplashPresenter = splashPresenter;
    }

    @Override
    public Bitmap getSplashPicture(Context context) {
        return BitmapFactory.decodeResource(context.getResources(), R.mipmap.splash_pic);
    }

    @Override
    public void countDown() {
        CountDownTimer countDownTimer = new CountDownTimer(1000, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {

            }

            @Override
            public void onFinish() {
                if (existUser()) {
                    mSplashPresenter.showMain();
                } else {
                    mSplashPresenter.showLogin();
                }
            }
        };
        countDownTimer.start();
    }

    private boolean existUser() {
        SendCardApp.UserInfo userInfo = SendCardApp.getUserInfo();
        String username = userInfo.getUserName();
        return !TextUtils.isEmpty(username) || !"".equals(username);
    }
}
