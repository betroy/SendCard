package com.troy.sendcard.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;

import com.jcodecraeer.xrecyclerview.ProgressStyle;
import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.troy.sendcard.R;
import com.troy.sendcard.bean.response.HandledResult;
import com.troy.sendcard.presenter.IHandledPresenter;
import com.troy.sendcard.presenter.impl.HandledPresenterImpl;
import com.troy.sendcard.ui.adapter.HandledAdapter;
import com.troy.sendcard.view.HandledView;
import com.yqritc.recyclerviewflexibledivider.HorizontalDividerItemDecoration;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;

/**
 * Created by chenlongfei on 16/4/10.
 */
public class HandledFragment extends BaseFragment implements HandledView, XRecyclerView.LoadingListener {
    @Bind(R.id.recycle_view)
    XRecyclerView mRecyclerView;

    private List<HandledResult.HandledDataEntity> mHandledDataEntityList = new ArrayList<HandledResult.HandledDataEntity>();
    private HandledAdapter mHandledAdapter;
    private IHandledPresenter mHandledPresenter;
    private int page = 1;

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mHandledPresenter = new HandledPresenterImpl(getContext(), this);
        initView();
    }


    private void initView() {
        mHandledAdapter = new HandledAdapter(getContext(), mHandledDataEntityList);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        mRecyclerView.addItemDecoration(new HorizontalDividerItemDecoration.Builder(getContext()).build());
        mRecyclerView.setAdapter(mHandledAdapter);
        mRecyclerView.setRefreshProgressStyle(ProgressStyle.BallSpinFadeLoader);
        mRecyclerView.setLoadingMoreProgressStyle(ProgressStyle.BallRotate);
        mRecyclerView.setLoadingListener(this);
        mRecyclerView.setRefreshing(true);
    }

    @Override
    int getContentView() {
        return R.layout.fragment_handled;
    }


    @Override
    public void loadDataSuccess(List<HandledResult.HandledDataEntity> handledDataEntityList) {
        if (null != handledDataEntityList) {
            mHandledDataEntityList.clear();
            mHandledDataEntityList.addAll(handledDataEntityList);
            mHandledAdapter.notifyDataSetChanged();
        }
        mRecyclerView.refreshComplete();
    }

    @Override
    public void loadDataFail() {
        mRecyclerView.refreshComplete();
        showToast(getString(R.string.loading_fail));
    }

    @Override
    public void loadMoreDataSuccess(List<HandledResult.HandledDataEntity> handledDataEntityList) {
        if (null != handledDataEntityList) {
            mHandledDataEntityList.addAll(handledDataEntityList);
            mHandledAdapter.notifyDataSetChanged();
        }
        mRecyclerView.loadMoreComplete();
    }

    @Override
    public void loadMoreDataFail() {
        mRecyclerView.loadMoreComplete();
        showToast(getString(R.string.loading_more_fail));
    }

    @Override
    public void loadHandledData() {
        page = 1;
        mHandledPresenter.requestHandledList(page);
    }

    @Override
    public void loadMoreHandledData() {
        mHandledPresenter.requestMoreHandledList(++page);
    }

    @Override
    public void onRefresh() {
        loadHandledData();
    }

    @Override
    public void onLoadMore() {
        loadMoreHandledData();
    }
}
