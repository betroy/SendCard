package com.troy.sendcard.presenter.impl;

import android.content.Context;

import com.troy.sendcard.bean.respone.HandledResult;
import com.troy.sendcard.config.Constant;
import com.troy.sendcard.interactor.IHandledInteractor;
import com.troy.sendcard.interactor.Impl.HandledInteractorImpl;
import com.troy.sendcard.presenter.IHandledPresenter;
import com.troy.sendcard.view.HandledView;

import java.util.List;

/**
 * Created by chenlongfei on 16/4/12.
 */
public class HandledPresenterImpl implements IHandledPresenter, IHandledInteractor.HandledListener {
    private Context mContext;
    private IHandledInteractor mHandledInteractor;
    private HandledView mHandledView;

    public HandledPresenterImpl(Context context, HandledView handledView) {
        mContext = context;
        mHandledView = handledView;
        mHandledInteractor = new HandledInteractorImpl(this);
    }

    @Override
    public void requestHandledList(int page) {
        mHandledInteractor.getHandledList(page, Constant.DATA_REFRESH);
    }

    @Override
    public void requestMoreHandledList(int page) {
        mHandledInteractor.getHandledList(page, Constant.DATA_LOADING_MORE);
    }

    @Override
    public void onSuccess(List<HandledResult.HandledDataEntity> handledDataEntityList) {
        mHandledView.loadDataSuccess(handledDataEntityList);
    }

    @Override
    public void onFail() {
        mHandledView.loadDataFail();
    }

    @Override
    public void onLoadMoreSuccess(List<HandledResult.HandledDataEntity> handledDataEntityList) {
        mHandledView.loadMoreDataSuccess(handledDataEntityList);
    }

    @Override
    public void onLoadMoreFail() {
        mHandledView.loadMoreDataFail();
    }
}
