package com.troy.sendcard.interactor;

/**
 * Created by chenlongfei on 16/4/2.
 */
public interface ILoginInteractor {
    interface OnLoginListener {
        void onSuccess();

        void onFail();
    }

    void Login(String username, String password);
}
