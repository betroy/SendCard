package com.troy.sendcard.presenter;

/**
 * Created by chenlongfei on 16/4/12.
 */
public interface IUnhandledPresenter {
    void requestUnhandledList(int page);

    void requestMoreUnhandledList(int page);
}
