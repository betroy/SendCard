package com.troy.sendcard.interactor;

import com.troy.sendcard.bean.response.SendCardInfoResult;

/**
 * Created by chenlongfei on 16/4/13.
 */
public interface ISendCardInfoInteractor {
    public interface OnSendCardInfoListener {
        void onSuccess(SendCardInfoResult.SendCardInfoDataEntity sendCardInfoDataEntity);

        void onFail(int errorCode, String msg);
    }

    void getSendCardInfo(String applyId);
}
