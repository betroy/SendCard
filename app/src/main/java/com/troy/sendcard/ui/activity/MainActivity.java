package com.troy.sendcard.ui.activity;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.ogaclejapan.smarttablayout.SmartTabLayout;
import com.ogaclejapan.smarttablayout.utils.v4.FragmentPagerItemAdapter;
import com.ogaclejapan.smarttablayout.utils.v4.FragmentPagerItems;
import com.troy.sendcard.R;
import com.troy.sendcard.presenter.ILogoutPresenter;
import com.troy.sendcard.presenter.IMainPresenter;
import com.troy.sendcard.presenter.impl.LogoutPresenterImpl;
import com.troy.sendcard.presenter.impl.MainPresenterImpl;
import com.troy.sendcard.view.LogoutView;
import com.troy.sendcard.view.MainView;

import butterknife.Bind;

public class MainActivity extends BaseActivity implements MainView, LogoutView {
    @Bind(R.id.viewpager_tab)
    SmartTabLayout mSmartTabLayout;
    @Bind(R.id.viewpager)
    ViewPager mViewPager;
    @Bind(R.id.progress_bar)
    ProgressBar mProgressBar;
    @Bind(R.id.layout_content)
    View mContentView;
    @Bind(R.id.tv_msg)
    TextView mTxtMsg;

    private IMainPresenter mMainPresenter;
    private ILogoutPresenter mLogoutPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mMainPresenter = new MainPresenterImpl(this, this);
        mMainPresenter.initViewPager();
        mLogoutPresenter = new LogoutPresenterImpl(this);
    }

    @Override
    public void initViewPager(FragmentPagerItems fragmentPagerItems) {
        FragmentPagerItemAdapter fragmentPagerItemAdapter = new FragmentPagerItemAdapter(getSupportFragmentManager(), fragmentPagerItems);
        mViewPager.setAdapter(fragmentPagerItemAdapter);
        mSmartTabLayout.setViewPager(mViewPager);
    }

    @Override
    protected int getContentView() {
        return R.layout.activity_main;
    }


    @Override
    public void showLoading() {
        mContentView.setVisibility(View.GONE);
        mProgressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.logout:
                mLogoutPresenter.logout();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void hideLoading() {
        mContentView.setVisibility(View.VISIBLE);
        mProgressBar.setVisibility(View.GONE);
    }

    @Override
    public void showError(String msg) {
        mProgressBar.setVisibility(View.GONE);
        mTxtMsg.setText(msg);
    }

    @Override
    public void onLogoutSuccess() {
        go(LoginActivity.class);
    }

    @Override
    public void onLogoutFail() {
        showToast(getString(R.string.logout_fail));
    }
}
