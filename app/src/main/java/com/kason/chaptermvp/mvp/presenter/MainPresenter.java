package com.kason.chaptermvp.mvp.presenter;

import android.os.Handler;

import com.kason.chaptermvp.mvp.model.MainActivityModelInterImpl;
import com.kason.chaptermvp.mvp.model.MainActivityModelnter;
import com.kason.chaptermvp.mvp.view.MainActivityView;

/**
 * Created by kason_zhang on 11/16/2016.
 */

public class MainPresenter {
    private MainActivityView mainActivityView;
    private MainActivityModelnter mainActivityModelnter;
    private Handler myHandler = new Handler();
    private Runnable runnable;
    public MainPresenter(MainActivityView mainActivityView){
        this.mainActivityView = mainActivityView;
        mainActivityModelnter = new MainActivityModelInterImpl();
    }

    public void detachView(){
        this.mainActivityView = null;
        if(runnable != null)
            myHandler.removeCallbacks(runnable);
    }

    public String pushDataToView(){
        String data = null;
        mainActivityView.showLoading();
        new Thread(new Runnable() {
            @Override
            public void run() {
                final String data = mainActivityModelnter.getNetworkData();
                Runnable runnable = new Runnable() {
                    @Override
                    public void run() {
                        mainActivityView.toMainActivity(data);
                        mainActivityView.hideLoading();
                    }
                };
                myHandler.post(runnable);

            }
        }).start();

        return data;
    }
}
