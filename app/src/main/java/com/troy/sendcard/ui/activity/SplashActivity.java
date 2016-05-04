package com.troy.sendcard.ui.activity;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.widget.ImageView;

import com.troy.sendcard.R;
import com.troy.sendcard.presenter.ISplashPresenter;
import com.troy.sendcard.presenter.impl.SplashPresenterImpl;
import com.troy.sendcard.view.SplashView;

import butterknife.Bind;

/**
 * Created by chenlongfei on 16/4/2.
 */
public class SplashActivity extends BaseActivity implements SplashView {
    @Bind(R.id.iv_splash)
    ImageView mSplashImageView;

    private ISplashPresenter mSplashPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mSplashPresenter = new SplashPresenterImpl(this, this);
        mSplashPresenter.initData();
    }

    @Override
    public void showSplash(Bitmap bitmap) {
        mSplashImageView.setImageBitmap(bitmap);
    }

    @Override
    public void goToMain() {
        go(MainActivity.class);
    }

    @Override
    public void goToLogin() {
        go(LoginActivity.class);
    }

    @Override
    protected int getContentView() {
        return R.layout.activity_splash;
    }
}
