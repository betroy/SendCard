package com.troy.sendcard.interactor;

import com.troy.sendcard.bean.response.HandledResult;

import java.util.List;

/**
 * Created by chenlongfei on 16/4/12.
 */
public interface IHandledInteractor {
    public interface HandledListener {
        void onSuccess(List<HandledResult.HandledDataEntity> handledDataEntityList);

        void onFail();

        void onLoadMoreSuccess(List<HandledResult.HandledDataEntity> handledDataEntityList);

        void onLoadMoreFail();

    }

    void getHandledList(int page,int type);
}
