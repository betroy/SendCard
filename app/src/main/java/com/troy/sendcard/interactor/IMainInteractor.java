package com.troy.sendcard.interactor;

import android.content.Context;

import com.ogaclejapan.smarttablayout.utils.v4.FragmentPagerItems;
import com.troy.sendcard.bean.respone.TodoResult;

/**
 * Created by chenlongfei on 16/4/10.
 */
public interface IMainInteractor {
    public interface OnTodoListener {
        void onSuccessForTodo( TodoResult.TodoEntity todoEntity);

        void onFailForTodo();
    }

    void requestTodo();

    FragmentPagerItems getViewPagerItems(Context context);
}
