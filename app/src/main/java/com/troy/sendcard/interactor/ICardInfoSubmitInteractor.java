package com.troy.sendcard.interactor;

import java.util.List;

/**
 * Created by chenlongfei on 16/4/20.
 */
public interface ICardInfoSubmitInteractor {
    public interface CardInfoSubmitListener {
        void onSuccess();

        void onFail(String msg);
    }

    void requestCardInfoSubmit(String applyId, String cardnoList, String sendno, List<String> pics);

}
