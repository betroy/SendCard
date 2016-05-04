package com.troy.sendcard.presenter.impl;

import android.content.Context;

import com.troy.sendcard.R;
import com.troy.sendcard.bean.respone.SendCardInfoResult;
import com.troy.sendcard.interactor.ISendCardInfoInteractor;
import com.troy.sendcard.interactor.Impl.SendCardInfoInteractorImpl;
import com.troy.sendcard.presenter.ISendCardInfoPresenter;
import com.troy.sendcard.ui.view.SendCardInfoView;

/**
 * Created by chenlongfei on 16/4/13.
 */
public class SendCardInfoPresenterImpl implements ISendCardInfoPresenter, ISendCardInfoInteractor.OnSendCardInfoListener {
    private Context mContext;
    private ISendCardInfoInteractor mSendCardInfoInteractor;
    private SendCardInfoView mSendCardInfoView;

    public SendCardInfoPresenterImpl(Context context, SendCardInfoView sendCardInfoView) {
        mContext = context;
        mSendCardInfoView = sendCardInfoView;
        mSendCardInfoInteractor = new SendCardInfoInteractorImpl(this);

    }

    @Override
    public void requestSendCardInfo(String applyId) {
        mSendCardInfoView.showProgressDialog(mContext.getString(R.string.request_handing));
        mSendCardInfoInteractor.getSendCardInfo(applyId);
    }

    @Override
    public void onSuccess(SendCardInfoResult.SendCardInfoDataEntity sendCardInfoDataEntity) {
        mSendCardInfoView.hideProgressDialog();
        mSendCardInfoView.showSuccess(sendCardInfoDataEntity);
    }

    @Override
    public void onFail(int errorCode, String msg) {
        mSendCardInfoView.hideProgressDialog();
        mSendCardInfoView.showError(errorCode,msg);
    }
}
