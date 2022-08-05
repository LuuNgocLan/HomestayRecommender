package com.ltn.travel.module.empty;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.base.java.IBaseListener;
import com.base.java.mvp.BaseFragment;
import com.base.java.mvp.IBasePresenter;
import com.ltn.travel.R;

import butterknife.ButterKnife;

public class EmptyFragment extends BaseFragment implements IEmptyContract.IEmptyView {

    private IEmptyContract.IEmptyPresenter mIEmptyPresenter;
    private IBaseListener mIBaseListener;

    public static EmptyFragment newInstance(IBaseListener iBaseListener) {
        EmptyFragment fragment = new EmptyFragment();
        fragment.setIBaseListener(iBaseListener);
        Bundle bundle = new Bundle();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = super.onCreateView(inflater, container, savedInstanceState);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    protected IBasePresenter initPresenter() {
        return mIEmptyPresenter = new EmptyPresenter(getContext());
    }

    @Override
    protected int getLayoutResourceId() {
        return R.layout.fragment_empty;
    }

    public void setIBaseListener(IBaseListener IBaseListener) {
        mIBaseListener = IBaseListener;
    }
}
