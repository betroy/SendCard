package com.troy.sendcard.interactor;

import android.content.Context;
import android.graphics.Bitmap;

/**
 * Created by chenlongfei on 16/4/4.
 */
public interface ISplashInteractor {
    Bitmap getSplashPicture(Context context);

    void countDown();
}
