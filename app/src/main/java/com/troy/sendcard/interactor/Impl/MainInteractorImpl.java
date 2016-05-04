package com.troy.sendcard.interactor.Impl;

import android.content.Context;

import com.ogaclejapan.smarttablayout.utils.v4.FragmentPagerItems;
import com.troy.sendcard.R;
import com.troy.sendcard.SendCardApp;
import com.troy.sendcard.bean.request.TodoParam;
import com.troy.sendcard.bean.respone.TodoResult;
import com.troy.sendcard.config.Constant;
import com.troy.sendcard.http.api.TodoApi;
import com.troy.sendcard.interactor.IMainInteractor;
import com.troy.sendcard.ui.fragment.HandledFragment;
import com.troy.sendcard.ui.fragment.UnhandledFragment;
import com.troy.sendcard.util.SignUtil;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by chenlongfei on 16/4/10.
 */
public class MainInteractorImpl implements IMainInteractor {
    private OnTodoListener onTodoListener;

    public MainInteractorImpl(OnTodoListener onTodoListener) {
        this.onTodoListener = onTodoListener;
    }

    @Override
    public void requestTodo() {
        SendCardApp.UserInfo userInfo = SendCardApp.getUserInfo();
        TodoParam param = new TodoParam();
        param.setUser_id(userInfo.getUserId());
        param.setSecret(userInfo.getSecret());
        param.setTime(System.currentTimeMillis() + "");
        param.setSign(SignUtil.sign(param.createTodoParam()));

        TodoApi.getApi().getService().getResult(param.createTodoParam())
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<TodoResult>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        onTodoListener.onFailForTodo();
                    }

                    @Override
                    public void onNext(TodoResult todoResult) {
                        if (todoResult.getCode() == Constant.ResponeStatus.OK) {
                            TodoResult.TodoEntity todoEntity = todoResult.getData();
                            onTodoListener.onSuccessForTodo(todoEntity);
                        } else {
                            onTodoListener.onFailForTodo();
                        }
                    }
                });
    }

    @Override
    public FragmentPagerItems getViewPagerItems(Context context) {
        FragmentPagerItems fragmentPagerItems = FragmentPagerItems.with(context).add(context.getString(R.string.unhandled_title), UnhandledFragment.class).add(context.getString(R.string.handled_title), HandledFragment.class).create();
        return fragmentPagerItems;
    }
}
