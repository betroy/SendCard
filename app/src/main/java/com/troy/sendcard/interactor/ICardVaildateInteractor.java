package com.troy.sendcard.interactor;

/**
 * Created by chenlongfei on 16/4/21.
 */
public interface ICardVaildateInteractor {
    interface CardVaildateListener {
        void onSuccess(int index);

        void onFail(int index, String msg);
    }

    void requestCardVaildate(String cardno, int index);
}
