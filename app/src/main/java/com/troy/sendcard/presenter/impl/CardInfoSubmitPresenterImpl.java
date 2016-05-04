package com.troy.sendcard.presenter.impl;

import android.content.Context;

import com.troy.sendcard.R;
import com.troy.sendcard.interactor.ICardInfoSubmitInteractor;
import com.troy.sendcard.interactor.Impl.CardInfoSubmitInteractorImpl;
import com.troy.sendcard.presenter.ICardInfoSubmitPresenter;
import com.troy.sendcard.ui.view.CardInfoSubmitView;

import java.util.List;

/**
 * Created by chenlongfei on 16/4/20.
 */
public class CardInfoSubmitPresenterImpl implements ICardInfoSubmitPresenter, ICardInfoSubmitInteractor.CardInfoSubmitListener {
    private Context mContext;
    private CardInfoSubmitView mCardInfoSubmitView;
    private ICardInfoSubmitInteractor mCardInfoSubmitInteractor;

    public CardInfoSubmitPresenterImpl(Context context, CardInfoSubmitView cardInfoSubmitView) {
        mContext = context;
        mCardInfoSubmitView = cardInfoSubmitView;
        mCardInfoSubmitInteractor = new CardInfoSubmitInteractorImpl(this);
    }

    @Override
    public void onSuccess() {
        mCardInfoSubmitView.hideProgressDialog();
        mCardInfoSubmitView.onSubmitSuccess();
    }

    @Override
    public void onFail(String msg) {
        mCardInfoSubmitView.hideProgressDialog();
        mCardInfoSubmitView.onSubmitFail(msg);
    }

    @Override
    public void cardInfoSubmit(String applyId, String cardnoList, String sendno, List<String> pics) {
        mCardInfoSubmitInteractor.requestCardInfoSubmit(applyId, cardnoList, sendno, pics);
        mCardInfoSubmitView.showProgressDialog(mContext.getString(R.string.submiting));
    }
}
