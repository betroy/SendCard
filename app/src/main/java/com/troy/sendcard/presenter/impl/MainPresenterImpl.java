package com.troy.sendcard.presenter.impl;

import android.content.Context;

import com.troy.sendcard.bean.response.TodoResult;
import com.troy.sendcard.interactor.IMainInteractor;
import com.troy.sendcard.interactor.Impl.MainInteractorImpl;
import com.troy.sendcard.presenter.IMainPresenter;
import com.troy.sendcard.view.MainView;

/**
 * Created by chenlongfei on 16/4/10.
 */
public class MainPresenterImpl implements IMainPresenter, IMainInteractor.OnTodoListener {
    private MainView mMainView;
    private Context mContext;
    private IMainInteractor mMainInteractor;

    public MainPresenterImpl(MainView mainView, Context context) {
        mMainView = mainView;
        mContext = context;
        mMainInteractor = new MainInteractorImpl(this);
    }

    @Override
    public void initData() {

    }

    @Override
    public void getTodoInfo() {
        mMainInteractor.requestTodo();
    }

    @Override
    public void initViewPager() {
        mMainView.initViewPager(mMainInteractor.getViewPagerItems(mContext));
    }


    @Override
    public void onSuccessForTodo(TodoResult.TodoEntity todoEntity) {
        mMainView.hideLoading();
    }

    @Override
    public void onFailForTodo() {

    }
}
