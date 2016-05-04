package com.troy.sendcard.view;

import com.ogaclejapan.smarttablayout.utils.v4.FragmentPagerItems;

/**
 * Created by chenlongfei on 16/4/10.
 */
public interface MainView {
    void showLoading();

    void hideLoading();

    void showError(String msg);

    void initViewPager(FragmentPagerItems fragmentPagerItems);

}
