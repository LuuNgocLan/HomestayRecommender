package com.base.java.mvp;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

public abstract class BaseActivity extends AppCompatActivity implements IBaseView {

    private IBasePresenter mIBasePresenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutResourceId());
        mIBasePresenter = initPresenter();
        if (mIBasePresenter != null) {
            mIBasePresenter.onCreate(this);
        }
    }

    protected abstract IBasePresenter initPresenter();

    protected abstract int getLayoutResourceId();

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mIBasePresenter != null) {
            mIBasePresenter.onDestroy();
        }
    }
}
