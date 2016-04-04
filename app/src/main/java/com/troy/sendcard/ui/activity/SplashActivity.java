package com.troy.sendcard.ui.activity;

import android.graphics.Bitmap;
import android.widget.ImageView;

import com.troy.sendcard.R;
import com.troy.sendcard.ui.base.BaseActivity;
import com.troy.sendcard.view.ISplashView;

import butterknife.Bind;

/**
 * Created by chenlongfei on 16/4/2.
 */
public class SplashActivity extends BaseActivity implements ISplashView {
    @Bind(R.id.iv_splash)
    ImageView mSplashImageView;

    @Override
    public void showSplash(Bitmap bitmap) {

    }

    @Override
    public void goToMain() {

    }

    @Override
    protected int getContentView() {
        return R.layout.activity_splash;
    }
}
