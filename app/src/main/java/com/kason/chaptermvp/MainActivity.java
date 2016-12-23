package com.kason.chaptermvp;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.kason.chaptermvp.mvp.presenter.MainPresenter;
import com.kason.chaptermvp.mvp.view.MainActivityView;

public class MainActivity extends AppCompatActivity implements MainActivityView{

    private ProgressDialog pd;
    private Button getDataByNetwork;
    private TextView textView;
    private MainPresenter mainPresenter;//Activity View持有Presenter对象
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        pd = new ProgressDialog(this);
        pd.setMessage("jiazaizhong......");
        mainPresenter = new MainPresenter(this);
        getDataByNetwork = (Button)findViewById(R.id.getByNetwork);
        textView = (TextView)findViewById(R.id.textView);
        getDataByNetwork.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //mainPresenter
                mainPresenter.pushDataToView();


            }
        });
    }

    @Override
    public void showLoading() {
        pd.show();
    }

    @Override
    public void hideLoading() {
        pd.cancel();
    }

    @Override
    public void toMainActivity(String data) {
        textView.setText(data);
    }

    @Override
    protected void onDestroy() {
        mainPresenter.detachView();
        super.onDestroy();
    }
}
