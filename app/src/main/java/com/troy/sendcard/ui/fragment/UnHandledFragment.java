package com.troy.sendcard.ui.fragment;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.text.TextUtils;
import android.view.View;

import com.jcodecraeer.xrecyclerview.ProgressStyle;
import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.troy.sendcard.R;
import com.troy.sendcard.bean.respone.SendCardInfoResult;
import com.troy.sendcard.bean.respone.UnhandledResult;
import com.troy.sendcard.config.Constant;
import com.troy.sendcard.presenter.ICancleHandledPresenter;
import com.troy.sendcard.presenter.ISendCardInfoPresenter;
import com.troy.sendcard.presenter.IUnhandledPresenter;
import com.troy.sendcard.presenter.impl.CancleHandledPresenterImpl;
import com.troy.sendcard.presenter.impl.SendCardInfoPresenterImpl;
import com.troy.sendcard.presenter.impl.UnhandledPresenterImpl;
import com.troy.sendcard.ui.activity.SendCardActivity;
import com.troy.sendcard.ui.adapter.UnhandledAdapter;
import com.troy.sendcard.ui.view.SendCardInfoView;
import com.troy.sendcard.view.CancleHandledView;
import com.troy.sendcard.view.UnhandledView;
import com.yqritc.recyclerviewflexibledivider.HorizontalDividerItemDecoration;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import me.drakeet.materialdialog.MaterialDialog;

/**
 * Created by chenlongfei on 16/4/10.
 */
public class UnhandledFragment extends BaseFragment implements UnhandledView, CancleHandledView, SendCardInfoView, XRecyclerView.LoadingListener, View.OnClickListener {
    @Bind(R.id.recycle_view)
    XRecyclerView mRecyclerView;
    private ProgressDialog mProgressDialog;

    private List<UnhandledResult.UnhandledDataEntity> mUnhandledDataEntityList = new ArrayList<UnhandledResult.UnhandledDataEntity>();
    private UnhandledAdapter mUnhandledAdapter;
    private IUnhandledPresenter mUnhandledPresenter;
    private ISendCardInfoPresenter mSendCardInfoPresenter;
    private ICancleHandledPresenter mCancleHandledPresenter;
    private int page = 1;
    private static final int REQUEST_CODE_SEND_CARD = 0;
    private String applyId;

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mUnhandledPresenter = new UnhandledPresenterImpl(getContext(), this);
        mCancleHandledPresenter = new CancleHandledPresenterImpl(getContext(), this);
        mSendCardInfoPresenter = new SendCardInfoPresenterImpl(getContext(), this);
        initView();
    }

    private void initView() {
        mProgressDialog = new ProgressDialog(getContext());
        mProgressDialog.setCancelable(false);
        mUnhandledAdapter = new UnhandledAdapter(getContext(), mUnhandledDataEntityList);
        mUnhandledAdapter.setOnClickListener(this);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        mRecyclerView.addItemDecoration(new HorizontalDividerItemDecoration.Builder(getContext()).build());
        mRecyclerView.setAdapter(mUnhandledAdapter);
        mRecyclerView.setRefreshProgressStyle(ProgressStyle.BallSpinFadeLoader);
        mRecyclerView.setLoadingMoreProgressStyle(ProgressStyle.BallRotate);
        mRecyclerView.setLoadingListener(this);
        mRecyclerView.setRefreshing(true);
    }

    @Override
    int getContentView() {
        return R.layout.fragment_unhandled;
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE_SEND_CARD) {
            if (resultCode == getActivity().RESULT_OK) {
                mRecyclerView.setRefreshing(true);
            } else if (requestCode == getActivity().RESULT_CANCELED) {
                if (!TextUtils.isEmpty(applyId)) {
                    mCancleHandledPresenter.requestCancleHandled(applyId);
                }
            }
        }

    }

    @Override
    public void loadDataSuccess(List<UnhandledResult.UnhandledDataEntity> unhandledDataEntityList) {
        mUnhandledDataEntityList.clear();
        mUnhandledDataEntityList.addAll(unhandledDataEntityList);
        mUnhandledAdapter.notifyDataSetChanged();
        mRecyclerView.refreshComplete();
    }

    @Override
    public void loadDataFail() {
        mRecyclerView.refreshComplete();
        showToast(getString(R.string.loading_fail));
    }

    @Override
    public void loadMoreDataSuccess(List<UnhandledResult.UnhandledDataEntity> unhandledDataEntityList) {
        mUnhandledDataEntityList.addAll(unhandledDataEntityList);
        mUnhandledAdapter.notifyDataSetChanged();
        mRecyclerView.loadMoreComplete();
    }

    @Override
    public void loadMoreDataFail() {
        mRecyclerView.loadMoreComplete();
        showToast(getString(R.string.loading_more_fail));
    }


    @Override
    public void loadUnhandledData() {
        page = 1;
        mUnhandledPresenter.requestUnhandledList(page);
    }

    @Override
    public void loadMoreUnhandledData() {
        mUnhandledPresenter.requestMoreUnhandledList(++page);
    }

    @Override
    public void onRefresh() {
        loadUnhandledData();
    }

    @Override
    public void onLoadMore() {
        loadMoreUnhandledData();
    }

    @Override
    public void onClick(final View view) {
        switch (view.getId()) {
            case R.id.btn_go_handled:
                applyId = (String) view.getTag();
                mSendCardInfoPresenter.requestSendCardInfo(applyId);
                break;
            case R.id.root_view:
                final MaterialDialog materialDialog = new MaterialDialog(getContext());
                materialDialog.setMessage(getString(R.string.cancle_handled));
                materialDialog.setPositiveButton("确定", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String applyId = (String) view.getTag();
                        ICancleHandledPresenter cancleHandledPresenter = new CancleHandledPresenterImpl(getContext(), UnhandledFragment.this);
                        cancleHandledPresenter.requestCancleHandled(applyId);
                        materialDialog.dismiss();
                    }
                });
                materialDialog.setNegativeButton("取消", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        materialDialog.dismiss();
                    }
                });
                materialDialog.show();
                break;
        }
    }

    @Override
    public void onCancleHandledSuccess() {
        showToast(getString(R.string.cancle_apply_success));
        mRecyclerView.setRefreshing(true);
    }

    @Override
    public void onCancleHandledFail() {
        showToast(getString(R.string.cancle_apply_fail));
    }

    @Override
    public void showProgressDialog(String tips) {
        if (!mProgressDialog.isShowing()) {
            mProgressDialog.setMessage(tips);
            mProgressDialog.show();
        }
    }

    @Override
    public void hideProgressDialog() {
        if (mProgressDialog.isShowing()) {
            mProgressDialog.dismiss();
        }
    }


    @Override
    public void showSuccess(SendCardInfoResult.SendCardInfoDataEntity sendCardInfoDataEntity) {
        Intent intent = new Intent(getActivity(), SendCardActivity.class);
        intent.putExtra(Constant.Intent.KEY_APPLY_ID, applyId);
        intent.putExtra(Constant.Intent.KEY_SENDCARDINFO, sendCardInfoDataEntity);
        startActivityForResult(intent, REQUEST_CODE_SEND_CARD);
    }

    @Override
    public void showError(int errorCode, String msg) {
        switch (errorCode) {
            case Constant.SendCardStatusCode.APPLY_ID_NULL:
            case Constant.SendCardStatusCode.APPLY_ID_INFO_NULL:
            case Constant.SendCardStatusCode.CARD_NO_TOTAL_EMPTY:
                showToast(msg);
                break;
            case Constant.SendCardStatusCode.APPLY_HANDLING:
                showToast(getString(R.string.sendcard_handing));
                break;
            default:
                showToast(getString(R.string.handled_fail));
                break;

        }
        mRecyclerView.setRefreshing(true);
    }
}
