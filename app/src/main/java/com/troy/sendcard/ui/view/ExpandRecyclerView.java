package com.troy.sendcard.ui.view;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;

/**
 * Created by chenlongfei on 16/4/12.
 */
public class ExpandRecyclerView extends RecyclerView {
    private OnLoadMroeScrollListener mOnLoadMroeScrollListener;
    private boolean mIsLoading;

    public ExpandRecyclerView(Context context) {
        this(context, null);
    }

    public ExpandRecyclerView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ExpandRecyclerView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        setOnScrollListener(new LoadMroeScrollListener());
    }


    private class LoadMroeScrollListener extends OnScrollListener {
        @Override
        public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
        }

        @Override
        public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
            super.onScrolled(recyclerView, dx, dy);
            if (getLayoutManager() instanceof LinearLayoutManager) {
                LinearLayoutManager linearLayoutManager = (LinearLayoutManager) getLayoutManager();
                int lastVisibleItemPosition = linearLayoutManager.findLastVisibleItemPosition();
                int totalItemCount = linearLayoutManager.getItemCount();
                if (dy > 0 && lastVisibleItemPosition >= totalItemCount && !mIsLoading) {
                    if (mOnLoadMroeScrollListener != null) {
                        mOnLoadMroeScrollListener.loadMore();
                        mIsLoading = true;
                    }
                }
            }
        }
    }

    public void loadFinsh() {
        mIsLoading = false;
    }


    public void setLoadMoreScrollListener(OnLoadMroeScrollListener onLoadMroeScrollListener) {
        mOnLoadMroeScrollListener = onLoadMroeScrollListener;
    }

    public interface OnLoadMroeScrollListener {
        void loadMore();
    }

}
