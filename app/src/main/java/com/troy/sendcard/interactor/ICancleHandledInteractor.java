package com.troy.sendcard.interactor;

/**
 * Created by chenlongfei on 16/4/20.
 */
public interface ICancleHandledInteractor {
    public interface CancleHandledListener {
        void onSuccess();

        void onFail();
    }

    void cancelHandled(String applyId);
}
