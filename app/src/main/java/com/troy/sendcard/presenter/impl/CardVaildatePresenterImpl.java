package com.troy.sendcard.presenter.impl;

import android.content.Context;

import com.troy.sendcard.interactor.ICardVaildateInteractor;
import com.troy.sendcard.interactor.Impl.CardVaildateInteractorImpl;
import com.troy.sendcard.presenter.ICardVaildatePresenter;
import com.troy.sendcard.view.CardVaildateView;

/**
 * Created by chenlongfei on 16/4/21.
 */
public class CardVaildatePresenterImpl implements ICardVaildatePresenter, ICardVaildateInteractor.CardVaildateListener {
    private Context mContext;
    private ICardVaildateInteractor mCardVaildateInteractor;
    private CardVaildateView mCardVaildateView;

    public CardVaildatePresenterImpl(Context context, CardVaildateView cardVaildateView) {
        mContext = context;
        mCardVaildateView = cardVaildateView;
        mCardVaildateInteractor = new CardVaildateInteractorImpl(this);
    }

    @Override
    public void cardVaildate(String cardno, int index) {
        mCardVaildateInteractor.requestCardVaildate(cardno, index);
    }

    @Override
    public void onSuccess(int index) {
        mCardVaildateView.onCardVaildateSuccess(index);
    }

    @Override
    public void onFail(int index, String msg) {
        mCardVaildateView.onCardVaildateFail(index, msg);
    }
}
