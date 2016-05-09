package com.troy.sendcard.view;

import com.troy.sendcard.bean.response.UnhandledResult;

import java.util.List;

/**
 * Created by chenlongfei on 16/4/12.
 */
public interface UnhandledView {

    void loadDataSuccess(List<UnhandledResult.UnhandledDataEntity> unhandledDataEntityList);

    void loadDataFail();

    void loadMoreDataSuccess(List<UnhandledResult.UnhandledDataEntity> unhandledDataEntityList);

    void loadMoreDataFail();

    void loadUnhandledData();

    void loadMoreUnhandledData();

}
