package com.troy.sendcard.presenter.impl;

import android.content.Context;

import com.troy.sendcard.interactor.ICancleHandledInteractor;
import com.troy.sendcard.interactor.Impl.CancleHandledInteractorImpl;
import com.troy.sendcard.presenter.ICancleHandledPresenter;
import com.troy.sendcard.view.CancleHandledView;

/**
 * Created by chenlongfei on 16/4/20.
 */
public class CancleHandledPresenterImpl implements ICancleHandledPresenter, ICancleHandledInteractor.CancleHandledListener {
    private Context mContext;
    private ICancleHandledInteractor mCancleHandledInteractor;
    private CancleHandledView mCancleHandledView;

    public CancleHandledPresenterImpl(Context context, CancleHandledView cancleHandledView) {
        mContext = context;
        mCancleHandledView = cancleHandledView;
        mCancleHandledInteractor = new CancleHandledInteractorImpl(this);
    }

    @Override
    public void requestCancleHandled(String applyId) {
        mCancleHandledInteractor.cancelHandled(applyId);
    }

    @Override
    public void onSuccess() {
        if (null != mCancleHandledView)
            mCancleHandledView.onCancleHandledSuccess();
    }

    @Override
    public void onFail() {
        if (null != mCancleHandledView)
            mCancleHandledView.onCancleHandledFail();
    }
}
