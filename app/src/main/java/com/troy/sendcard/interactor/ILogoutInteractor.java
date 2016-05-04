package com.troy.sendcard.interactor;

/**
 * Created by chenlongfei on 16/4/21.
 */
public interface ILogoutInteractor {
    public interface LogoutListener {
        void onSuccess();

        void onFail();
    }

    void requestLogout();
}
