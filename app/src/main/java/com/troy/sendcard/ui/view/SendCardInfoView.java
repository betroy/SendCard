package com.troy.sendcard.ui.view;

import com.troy.sendcard.bean.respone.SendCardInfoResult;

/**
 * Created by chenlongfei on 16/4/13.
 */
public interface SendCardInfoView {
    void showProgressDialog(String tips);

    void hideProgressDialog();

    void showSuccess(SendCardInfoResult.SendCardInfoDataEntity sendCardInfoDataEntity);

    void showError(int errorCode, String msg);

}
