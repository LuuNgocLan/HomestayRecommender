package com.base.java.mvp;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

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
