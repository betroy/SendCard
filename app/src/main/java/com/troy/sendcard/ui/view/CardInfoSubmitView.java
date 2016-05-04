package com.troy.sendcard.ui.view;

/**
 * Created by chenlongfei on 16/4/20.
 */
public interface CardInfoSubmitView {
    void showProgressDialog(String tips);

    void hideProgressDialog();

    void onSubmitSuccess();

    void onSubmitFail(String msg);
}
