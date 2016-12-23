package com.kason.chaptermvp.mvp.view;

/**
 * Created by kason_zhang on 11/16/2016.
 */

public interface MainActivityView {

    void showLoading();
    void hideLoading();
    void toMainActivity(String data);
}
