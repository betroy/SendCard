package com.troy.sendcard.interactor;

import com.troy.sendcard.bean.respone.UnhandledResult;

import java.util.List;

/**
 * Created by chenlongfei on 16/4/12.
 */
public interface IUnhandledInteractor {
    public interface UnhandledListener {
        void onSuccess(List<UnhandledResult.UnhandledDataEntity> unhandledDataEntityList);

        void onFail();

        void onLoadMoreSuccess(List<UnhandledResult.UnhandledDataEntity> unhandledDataEntityList);

        void onLoadMoreFail();
    }

    void getUnhandledList(int page,int type);
}
