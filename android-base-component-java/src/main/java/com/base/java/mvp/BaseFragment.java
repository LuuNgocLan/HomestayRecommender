package com.base.java.mvp;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public abstract class BaseFragment extends Fragment implements IBaseView {

    private IBasePresenter mIBasePresenter;
    public IBaseContainerFragment mFragmentListener;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mIBasePresenter = initPresenter();
        if (mIBasePresenter != null) {
            mIBasePresenter.onCreate(this);
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(getLayoutResourceId(), container, false);
        return view;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mIBasePresenter != null) {
            mIBasePresenter.onDestroy();
        }
    }

    protected abstract IBasePresenter initPresenter();

    protected abstract int getLayoutResourceId();

    public void setFragmentListener(IBaseContainerFragment mFragmentListener) {
        this.mFragmentListener = mFragmentListener;
    }

}
