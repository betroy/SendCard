package com.troy.sendcard.presenter;

import java.util.List;

/**
 * Created by chenlongfei on 16/4/20.
 */
public interface ICardInfoSubmitPresenter {
    void cardInfoSubmit(String applyId, String cardnoList, String sendno, List<String> pics);
}
