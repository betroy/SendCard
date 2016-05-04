package com.troy.sendcard.view;

/**
 * Created by chenlongfei on 16/4/2.
 */
public interface LoginView {
    void showLoading(String tips);

    void hideLoading();

    void showToast(String tips);

    void loginFail();

    void loginSuccess();

    String getUserName();

    String getPassword();
}
