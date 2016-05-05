package com.troy.sendcard.presenter.impl;

import android.content.Context;

import com.troy.sendcard.bean.respone.UnhandledResult;
import com.troy.sendcard.config.Constant;
import com.troy.sendcard.interactor.IUnhandledInteractor;
import com.troy.sendcard.interactor.Impl.UnhandledInteractorImpl;
import com.troy.sendcard.presenter.IUnhandledPresenter;
import com.troy.sendcard.view.UnhandledView;

import java.util.List;

/**
 * Created by chenlongfei on 16/4/12.
 */
public class UnhandledPresenterImpl implements IUnhandledPresenter, IUnhandledInteractor.UnhandledListener {
    private Context mContext;
    private IUnhandledInteractor mUnhandledInteractor;
    private UnhandledView mUnhandledView;

    public UnhandledPresenterImpl(Context context, UnhandledView unhandledView) {
        mContext = context;
        mUnhandledView = unhandledView;
        mUnhandledInteractor = new UnhandledInteractorImpl(this);
    }

    @Override
    public void onSuccess(List<UnhandledResult.UnhandledDataEntity> unhandledDataEntityList) {
        mUnhandledView.loadDataSuccess(unhandledDataEntityList);
    }

    @Override
    public void onFail() {
        mUnhandledView.loadDataFail();
    }

    @Override
    public void onLoadMoreSuccess(List<UnhandledResult.UnhandledDataEntity> unhandledDataEntityList) {
        mUnhandledView.loadMoreDataSuccess(unhandledDataEntityList);
    }

    @Override
    public void onLoadMoreFail() {
        mUnhandledView.loadMoreDataFail();
    }

    @Override
    public void requestUnhandledList(int page) {
        mUnhandledInteractor.getUnhandledList(page, Constant.DATA_REFRESH);
    }

    @Override
    public void requestMoreUnhandledList(int page) {
        mUnhandledInteractor.getUnhandledList(page, Constant.DATA_LOADING_MORE);
    }
}
