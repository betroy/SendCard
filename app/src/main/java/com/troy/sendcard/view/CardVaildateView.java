package com.troy.sendcard.view;

/**
 * Created by chenlongfei on 16/4/21.
 */
public interface CardVaildateView {
    void onCardVaildateSuccess(int index);

    void onCardVaildateFail(int index, String msg);
}
