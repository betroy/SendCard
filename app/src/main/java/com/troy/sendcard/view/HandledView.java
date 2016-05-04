package com.troy.sendcard.view;

import com.troy.sendcard.bean.respone.HandledResult;

import java.util.List;

/**
 * Created by chenlongfei on 16/4/12.
 */
public interface HandledView {

    void loadDataSuccess(List<HandledResult.HandledDataEntity> handledDataEntityList);

    void loadDataFail();

    void loadMoreDataSuccess(List<HandledResult.HandledDataEntity> handledDataEntityList);

    void loadMoreDataFail();


    void loadHandledData();

    void loadMoreHandledData();
}
